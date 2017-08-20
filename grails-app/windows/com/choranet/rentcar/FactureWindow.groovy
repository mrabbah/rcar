
package com.choranet.rentcar

import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef

import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import java.util.HashMap;
import java.util.Map;
import org.zkoss.zk.ui.Executions

/**
 * Facture Window Object
 **/
class FactureWindow extends SuperWindow {

    private Log logger = LogFactory.getLog(FactureWindow.class)

    def factureService
    def paternCompteurService
    def jasperService
    def messageDocumentService
    def clientService
    //def paiementService

    def clients	
    //def paiements	
    //def paiementsSelected
    def clientSelected
    
    /**
     * Constructeur
     **/
    public FactureWindow (clientService, paternCompteurService, factureService) {
        super(Facture.class, true, false)
        this.clientService = clientService
        this.paternCompteurService = paternCompteurService
        //this.paiementService = paiementService
        this.factureService = factureService
        specialInitialiserAssociation()
    }    
    
    def specialInitialiserAssociation() {
        
        objet.numeroFacture = paternCompteurService.getProchainNumFacture()       
        objet.date = new Date()
        clients = clientService.list()
        
        def map
        if(attributsAFiltrer == null) {
            map = getService().filtrer(clazz, filtre, sortedHeader, sortedDirection, ofs, maxNb)
        } else {
            map = getService().filtrer(clazz, filtre, attributsAFiltrer, sortedHeader, sortedDirection, ofs, maxNb)
        }
        tailleListe = map["tailleListe"]
        listeObjets = map["listeObjets"]
    }
    
    
    def add() {
        try {  
            objet.client = clientSelected
            //objet.paiements = paiementsSelected
            objet = getService().save(objet)
            Messagebox.show("Enregistrement effecuté avec succès")
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
        } finally {
            rafraichirList()   
            activerBoutons(true)
        }
    }
    
    def update() {
        try {
            //objet.paiements = paiementsSelected
            objet = getService().update(objet)
            Messagebox.show("Modification effectuée avec succès")
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("Problème lors de la mise à jour", "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            rafraichirList()     
            activerBoutons(true)
        }
    }
    
    def delete() {
        getService().delete(objet)
        rafraichirList()          
        cancel()
    }
    

    def genererDocument(Boolean defaut,Boolean avecEntetePied,Boolean sansEntetePied, Boolean avecCachet, Boolean avecCopie, Boolean avecDestinataire, String destinataire) {
        //def parametrage = parametrageService.list().toArray()[0]
        def DocumentSubReport
        def JasperReportname  
        
        //        if (parametrage.afficherRemiseFacture && parametrage.afficherTvaFacture){
        //            JasperReportname ="rapport_des_DocumentsFacture.jasper"
        //            DocumentSubReport = Utilitaire.getDocumentSubReport(getClass())
        //        }
        //        else{
            
        JasperReportname ="rapport_des_DocumentsFactureWRT.jasper" 
        DocumentSubReport = Utilitaire.getDocumentSubReportWRT(getClass())
        //        }
        
        def typedocument
        if(estAvoir) {
            typedocument = messageDocumentService.getMessageParType("AVOIR", type);
        } else {
            typedocument = messageDocumentService.getMessageParType("FACTURE", type);
        }

        def document = objet.trans_ligneProduits
        def societe = session.societe;
        def nomsociete = societe.raisonSociale ;
        def image = societe.trans_logo.getStreamData()
        
        def imageEntetePied = societe.trans_entetepied.getStreamData()
        def imageCachet = societe.trans_cachet.getStreamData()
        def imageCopie = societe.trans_copie.getStreamData()
        
        Boolean avecDetails = true
        Boolean avecHeader  
        Boolean avecFooter 
        
        if(defaut){
            avecHeader = true   
            avecFooter = true   
        }
        if (avecEntetePied){
            avecHeader = false   
            avecFooter = false
        }
        if (sansEntetePied){
            avecHeader = false   
            avecFooter = false
        }
        
        def titrerapport = "Facture"
        if(estAvoir) {
            titrerapport = "Avoir"
        }
        def adressesociete = societe.adresse ;
        def codePostalesociete = societe.codePostale;
        def villesociete = societe.ville;
        def payssociete = societe.pays;
        def telephonesociete = societe.telephone;
        def emailsociete = societe.email;
        def faxsociete = societe.fax;
        def rcsociete = societe.rc;
        def idfsociete = societe.idF; 
        def sitesociete = societe.site; 
        def cnsssociete = societe.cnss;
        def patentesociete = societe.patente;
        
        def client = objet.partenaire;
        if(!client.isAttached()) {
            logger.debug("client n est pas attache")
            try {
                client.attach()
            }catch(Exception ex) {
                logger.error(ex)
            }
        }
        if(client.ville && !client.ville.isAttached()) {
            logger.debug("client.ville n est pas attachée")
            try {
                client.ville.attach()
            }catch(Exception ex) {
                logger.error(ex)
            }
        }
        def nomclient = client.raisonSociale;
        def telclient = client.telephone;
        def faxclient = client.fax ;
        def adresseclient = client.adresseFacturation;
        def villeclient = (client.ville != null)?client.ville.intitule: "";
        def paysclient = (client.ville != null)?client.ville.pays.intitule: "";

        def slogan ;
                          
        String champ1Nom = "N° Facture"
        if(estAvoir) {
            champ1Nom = "N° Avoir"
        }
        String champ1Valeur  = objet.numeroFacture
        String champ1Date = DateUtil.convertDateToStringByPattern("dd-MM-yyyy",objet.date)
        
        String champ3Nom
        String champ3Valeur  
        String champ3Date 
        
        String champ2Nom ="Lise des bons de commandes :"
        String champ2Valeur  = ""
        String champ2Date 
        
        String entete = typedocument.entete
        String pied = typedocument.pied
        
        //Double autresfrais = factureService.getFraisTransportFacture(objet);
        Double autresfrais = 0;
        Double totalht = objet.trans_totalht
        Double totaltva = objet.trans_totaltva 
        Double totalttc = objet.trans_totalttc
        Double TotalDocument = totalttc + autresfrais
        TotalDocument = Utilitaire.arrondir(TotalDocument, 2);
        
        Double apresLaVergule = Utilitaire.getNbApresLaVirgule(TotalDocument)
        Double avantLaVirgule = Utilitaire.getNbAvantLaVirgule(TotalDocument);
        
        String TotalDocumentEncaractere = FrenchNumberToWords.convert(avantLaVirgule)
       
        String arretermessage = "Arrêté la présente facture à la somme de : <br /> " + TotalDocumentEncaractere + " dirhams";
        if(estAvoir) {
            arretermessage = "Arrêté le présent avoir à la somme de : <br /> " + TotalDocumentEncaractere + " dirhams";
        }
        
        if(apresLaVergule != 0) {
            String apresLaVerguleEncarac = FrenchNumberToWords.convert(apresLaVergule)
            arretermessage += " " + apresLaVerguleEncarac + " centime(s)"             
        }

        def codes = []
        for(p in objet.paiements) {
            if(!codes.contains(p.bonCommande.numBC)) {
                codes.add(p.bonCommande.numBC)
                if(type.equals("VENTE")) {
                    if (p.bonCommande.reference && p.bonCommande.reference.length() >0){
                        champ2Valeur = champ2Valeur + "[" + p.bonCommande.reference + "] "
                    }
                }
                else{
                    if(p.bonCommande.numBC && p.bonCommande.numBC.length() >0){
                        champ2Valeur = champ2Valeur + "[" + p.bonCommande.numBC + "] "
                    }
                }
                
            }
        }
        
        if (codes.size() > 1) {
            //champ2Valeur = "Plusieurs BC";
        }
        else if (codes.size() == 1) {
            def paiement1 = objet.paiements.toArray()[0]
            if(type.equals("VENTE")) {
                champ2Valeur = "[" + paiement1.bonCommande.reference + " : " + DateUtil.convertDateToStringByPattern("dd-MM-yyyy",paiement1.bonCommande.date) + "] "
            }
            else{
                champ2Valeur = "[" + paiement1.bonCommande.numBC + " : " + DateUtil.convertDateToStringByPattern("dd-MM-yyyy",paiement1.bonCommande.date) + "] "       
            }
            champ2Date = DateUtil.convertDateToStringByPattern("dd-MM-yyyy",paiement1.bonCommande.date)
        }
        
        def reportDef = new JasperReportDef(name:JasperReportname,
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : [objet],
            parameters : ['nomsociete' : nomsociete,'titrerapport' : titrerapport , 'adressesociete' : adressesociete,'codePostalesociete' : codePostalesociete,'villesociete' : villesociete, 'payssociete' : payssociete, 'telephonesociete':telephonesociete,'emailsociete': emailsociete,'faxsociete' : faxsociete,'rcsociete' :rcsociete ,'idfsociete':idfsociete,'sitesociete':sitesociete ,'cnsssociete':cnsssociete,'patentesociete':patentesociete,'nomclient' :nomclient,'telclient':telclient,'faxclient':faxclient,'adresseclient':adresseclient,'villeclient':villeclient,'paysclient':paysclient,'slogan':slogan,'arretermessage':arretermessage,'autresfrais':autresfrais,'champ1Nom':champ1Nom,'champ1Valeur':champ1Valeur,'champ1Date':champ1Date,'champ2Nom':champ2Nom,'champ2Valeur':champ2Valeur,'champ2Date':champ2Date,'champ3Nom':champ3Nom,'champ3Valeur':champ3Valeur,'champ3Date':champ3Date,'entete':entete,'pied':pied,'document':document,'DocumentSubReport':DocumentSubReport,'Image' : image,'totalht' :totalht,'totaltva' :totaltva,'totalttc' :TotalDocument, 'ImageEntetePied':imageEntetePied,'ImageCachet': imageCachet, 'ImageCopie':imageCopie,'avecHeader':avecHeader, 'avecFooter' : avecFooter,'avecDetails':avecDetails,'avecEntetePied':avecEntetePied,'avecCachet':avecCachet,'avecCopie':avecCopie, 'avecDestinataire' : avecDestinataire, 'destinataire': destinataire]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "rapport_des_Documents_"+objet.numeroFacture+"_"+nomclient+".pdf"
        //Filedownload.save(bit, "application/file", nom_fichier);
        Media amedia = new AMedia(nom_fichier, "pdf", "application/pdf", bit);
        Map map = new HashMap();
        map.put("content", amedia);
        Executions.createComponents("/zul/util/pdfviewer.zul", null, map).doModal();
    }
    protected SuperService getService() {
        return this.factureService
    }
  
}

