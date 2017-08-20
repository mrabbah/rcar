
package com.choranet.rentcar


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory

import org.zkoss.image.Image
import org.zkoss.zk.ui.Executions
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import java.io.InputStream;
import com.choranet.zk.*;
import org.springframework.web.context.request.RequestContextHolder

/**
 * Location Window Object
 **/
class LocationWindow extends SuperWindow {
    def jasperService
    def reservationService
    def voitureService
    /**
     * Service pour la gestion de l'objet Location
     **/
    def locationService
    
    def factureService
    def paternCompteurService
    def factureDejaGeneree = false
    def numFactureLocation
    
    /**
     * Logger de la class LocationWindow
     **/
    private Log logger = LogFactory.getLog(LocationWindow.class)
	
    /**
     * liste de premierConducteur
     **/	
    def premierConducteurs	
    /**
     * premierConducteur  selectionn�
     **/
    def premierConducteurSelected
	
    /**
     * liste de deuxiemeConducteur
     **/	
    def deuxiemeConducteurs	
    /**
     * deuxiemeConducteur  selectionn�
     **/
    def deuxiemeConducteurSelected
	
    /**
     * liste de voiture
     **/	
    def voitures	
    /**
     * voiture  selectionn�
     **/
    def voitureSelected
	
    /**
     * liste de agentLoueurResponsable
     **/	
    def agentLoueurResponsables	
    /**
     * agentLoueurResponsable  selectionn�
     **/
    def agentLoueurResponsableSelected
	
    /**
     * Constructeur
     **/
    public LocationWindow (paternCompteurService, factureService) {
        super(Location.class, 14)
        this.paternCompteurService = paternCompteurService
        this.factureService = factureService
    }  

    protected SuperService getService() {
        return this.locationService
    }
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
		
        premierConducteurs = Client.list()		
        if(premierConducteurs.size() > 0)
        premierConducteurSelected = premierConducteurs.get(0)
        else
        premierConducteurSelected = null
		
        deuxiemeConducteurs = Client.list()		
        deuxiemeConducteurSelected = null
		
        voitures = Voiture.findAllByVendu(false)		
        if(voitures.size() > 0) {
            voitureSelected = voitures.get(0)
            objet.kilometrageDepart = voitureSelected.kilometrage
            objet.prixParJour = voitureSelected.prixLocation
        }
        else {
            voitureSelected = null
            objet.kilometrageDepart = null
            objet.prixParJour = null
	}	
        agentLoueurResponsables = Utilisateur.list()		
        if(agentLoueurResponsables.size() > 0) {
            def session = RequestContextHolder.currentRequestAttributes().getSession()
            agentLoueurResponsableSelected = agentLoueurResponsables.find{ it.id == session.utilisateur.id }
        }
        else
        agentLoueurResponsableSelected = null
		
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
			
        if(del) {
            premierConducteurs = Client.list()
        }	
        if(premierConducteurs.size() > 0)
        premierConducteurSelected = premierConducteurs.get(0)
        else
        premierConducteurSelected = null
			
        if(del) {
            deuxiemeConducteurs = Client.list()
        }	
        deuxiemeConducteurSelected = null
			
        if(del) {
            voitures = Voiture.findAllByVendu(false)
        }	
        if(voitures.size() > 0) {
            voitureSelected = voitures.get(0)
            objet.kilometrageDepart = voitureSelected.kilometrage
            objet.prixParJour = voitureSelected.prixLocation
        }
        else {
            voitureSelected = null
            objet.kilometrageDepart = null
            objet.prixParJour = null
	}
			
        if(del) {
            agentLoueurResponsables = Utilisateur.list()
        }	
        if(agentLoueurResponsables.size() > 0){
            def session = RequestContextHolder.currentRequestAttributes().getSession()
            agentLoueurResponsableSelected = agentLoueurResponsables.find{ it.id == session.utilisateur.id }
        }
        else
        agentLoueurResponsableSelected = null
		
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
			
        objet.premierConducteur = premierConducteurSelected
        if(premierConducteurs.size() > 0) {
            def binderpremierConducteur = new AnnotateDataBinder(this.getFellow("copremierConducteurs"))
            premierConducteurSelected = premierConducteurs.get(0)
            binderpremierConducteur.loadAll()
        }
        else
        premierConducteurSelected = null
				
        objet.deuxiemeConducteur = deuxiemeConducteurSelected
				
        objet.voiture = voitureSelected
        
        if(voitures.size() > 0) {
            def bindervoiture = new AnnotateDataBinder(this.getFellow("covoitures"))
            voitureSelected = voitures.get(0)
            //objet.kilometrageDepart = voitureSelected.kilometrage
            //objet.prixParJour = voitureSelected.prixLocation
            bindervoiture.loadAll()
        }
        else {
            voitureSelected = null
            objet.kilometrageDepart = null
            objet.prixParJour = null
	}
				
        objet.agentLoueurResponsable = agentLoueurResponsableSelected
        if(agentLoueurResponsables.size() > 0) {
            def binderagentLoueurResponsable = new AnnotateDataBinder(this.getFellow("coagentLoueurResponsables"))
            def session = RequestContextHolder.currentRequestAttributes().getSession()
            agentLoueurResponsableSelected = agentLoueurResponsables.find{ it.id == session.utilisateur.id }
            binderagentLoueurResponsable.loadAll()
        }
        else
        agentLoueurResponsableSelected = null
		
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
				
        def binderpremierConducteur = new AnnotateDataBinder(this.getFellow("copremierConducteurs"))
        premierConducteurSelected = premierConducteurs.find{ it.id == Location.findById(objet.id).premierConducteur.id }
        binderpremierConducteur.loadAll()
				
        def binderdeuxiemeConducteur = new AnnotateDataBinder(this.getFellow("codeuxiemeConducteurs"))
        //deuxiemeConducteurSelected = deuxiemeConducteurs.find{ it.id == Location.findById(objet.id).deuxiemeConducteur.id }
        deuxiemeConducteurSelected = objet.deuxiemeConducteur
        binderdeuxiemeConducteur.loadAll()
				
        def bindervoiture = new AnnotateDataBinder(this.getFellow("covoitures"))
        voitureSelected = voitures.find{ it.id == Location.findById(objet.id).voiture.id }
        if(objetSelected == null) {
            objet.kilometrageDepart = voitureSelected.kilometrage
            objet.prixParJour = voitureSelected.prixLocation
        }
        bindervoiture.loadAll()
				
        def binderagentLoueurResponsable = new AnnotateDataBinder(this.getFellow("coagentLoueurResponsables"))
        agentLoueurResponsableSelected = agentLoueurResponsables.find{ it.id == Location.findById(objet.id).agentLoueurResponsable.id }
        binderagentLoueurResponsable.loadAll()
		
    }
    
    def updateImageContrat(Object media) {        
        if (media != null) {                   
            
            if (media instanceof Image) {
                this.getFellow("appercuContrat").setContent(media);   
                objet.setTrans_contrat((Image) media);
            } else {
                Messagebox.show("Le fichier choisi n'est pas une image: " + media, "Erreur",
                    Messagebox.OK, Messagebox.ERROR);
                //break; //not to show too many errors
            }

        } else {
            Messagebox.show("Impossible d'envoyer le fichier choisi: " + media, "Erreur",
                Messagebox.OK, Messagebox.ERROR);
        }
    }
    
    def updateImageDeclaration(Object media) {        
        if (media != null) {                   
            
            if (media instanceof Image) {
                this.getFellow("appercuDeclaration").setContent(media);   
                objet.setTrans_declaration((Image) media);
            } else {
                Messagebox.show("Le fichier choisi n'est pas une image: " + media, "Erreur",
                    Messagebox.OK, Messagebox.ERROR);
                //break; //not to show too many errors
            }

        } else {
            Messagebox.show("Impossible d'envoyer le fichier choisi: " + media, "Erreur",
                Messagebox.OK, Messagebox.ERROR);
        }
    }
    
    /**
     *  Cette fonction est appel�e lorsque un �l�ment de la liste est selectionn�
     **/
    def select() { 
        super.select()
        rafraichirApercuImage()
        
        numFactureLocation = null
        def result = Facture.list().find{it.location.id == objet.id}
        if(result != null){
            factureDejaGeneree = true
            numFactureLocation = result.numeroFacture
            this.getFellow("btnFacture").label = "Duplicata"
            this.getFellow("btnCopieFacture").visible = true
        }else{
            factureDejaGeneree = false
            this.getFellow("btnFacture").label = "Facture"
            this.getFellow("btnCopieFacture").visible = false
        }
        new AnnotateDataBinder(this.getFellow("btnFacture")).loadAll()    
        new AnnotateDataBinder(this.getFellow("btnCopieFacture")).loadAll()
    }
    /**
     * Fonction qui se charge de sauveguarder un nouveau �l�ment de article
     **/
    def add() {
        try {
            def isReservationValid = reservationService.validatedReservation(objet, voitureSelected)
            def isResForLocationValid = reservationService.validatedReservationForLocation(objet, voitureSelected)
            if (isReservationValid && isResForLocationValid) {
                if(objet.kilometrageRetour != null && objet.kilometrageDepart < objet.kilometrageRetour) {
                    //voitureSelected.kilometrage = objet.kilometrageRetour
                    voitureSelected.kilometrage = locationService.getKilometrageVoiture(voitureSelected, objet.kilometrageRetour) 
                    voitureService.update(voitureSelected)
                }
                if(objet.trans_contrat == null) {
                    objet.setTrans_contrat(null);
                }
                if(objet.trans_declaration == null) {
                    objet.setTrans_declaration(null);
                }
                // au lieu de super.add()
                actualiserValeurAssociation()
                try {     
                    getService().save(objet)
                } catch(Exception ex) {
                    logger.error "Error: ${ex.message}", ex
                    Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
                } finally {
                    objet = clazz.newInstance()
                    objet.kilometrageDepart = voitureSelected.kilometrage
                    objet.prixParJour = voitureSelected.prixLocation
                    objet.prixAchatParJour = null
                    rafraichirField()
                    rafraichirList()
                    activerBoutons(false)
                }
                //fin au lieu super.add()
            } else {
                Messagebox.show("Echec lors de l'ajout d'une location : voiture déjà réservée ou alouée pendant cette période \n[" +objet.dateDepart +" , "+ objet.dateRestitution+"] \n", "Erreur", Messagebox.OK, Messagebox.ERROR)
            }
            
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
        } finally {
            rafraichirApercuImage()
        }
        //reinitialiserAssociation()
    }
    /**
     * Pour annuler la modification ou la supression et pour basculer en mode ajout d'un nouveau �l�ment
     **/
    def cancel() {        
        super.cancel()
        rafraichirApercuImage()
    }
    /**
     * Fonction qui se charge de mettre � jour un �l�ment selectionn� de article
     **/
    def update() {
        try {
            if(objet.kilometrageRetour != null  && objet.kilometrageDepart < objet.kilometrageRetour) {
                //objet.voiture.kilometrage = objet.kilometrageRetour
                objet.voiture.kilometrage = locationService.getKilometrageVoitureForUpdate(objet.id, objet.voiture, objet.kilometrageRetour) 
                voitureService.update(objet.voiture)
            }
            super.update()
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("Probleme lors de la mise à jour", "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            rafraichirApercuImage()
        }
        //reinitialiserAssociation()
    }
    /**
     * Fonction qui se charge de supprimer un �l�ment selectinn� de article
     **/
    def delete() {
        if(objet.voiture.kilometrage == objet.kilometrageRetour) {
            objet.voiture.kilometrage = objet.kilometrageDepart
            voitureService.update(objet.voiture)
        }
        super.delete()
        rafraichirApercuImage()
    }
    
    /**
     *Reinitialiser affichage image
     * */
    def rafraichirApercuImage() {
        this.getFellow("appercuContrat").content = objet.trans_contrat
        this.getFellow("appercuDeclaration").content = objet.trans_declaration
    }
    
    def zoomerImage(img) {
        def win = Executions.createComponents("/zul/location/zoomer.zul", null, null)
        win.getFellow("image").content = img
        win.doModal();
    }
    
    def newRecordNewClient(){
        def winLocCli = Executions.createComponents("/zul/location/locationclient.zul", this, null)
        winLocCli.doModal();
    }
    
    /**
     * Activer ou d�sactiver les boutons d'ajout, suppression, modfication
     **/
    def activerBoutons(visible) {
        this.getFellow("btnUpdate").visible = visible
        //this.getFellow("btnDelete").visible = visible
        this.getFellow("btnCancel").visible = visible
        this.getFellow("btnSave").visible = !visible
        this.getFellow("btnNewOld").visible = !visible
        this.getFellow("btnNewNew").visible = !visible
        this.getFellow("btnDeclaration").visible = visible
        this.getFellow("btnPv1").visible = visible
        this.getFellow("btnPv2").visible = visible
        this.getFellow("btnFacture").visible = visible
        //this.getFellow("btnExport").visible = !visible
        this.getFellow("westPanel").open = visible  
        //this.getFellow("fieldDateDepart").disabled = visible
        //this.getFellow("fieldDateRestitution").disabled = visible
        this.getFellow("covoitures").disabled = visible
    }
    
    def changerDeVoiture() {
        objet.kilometrageDepart = voitureSelected.kilometrage
        objet.kilometrageRetour = null
        objet.prixParJour = voitureSelected.prixLocation
        def coPrixParJour = this.getFellow("fieldPrixParJour")
        def binder3 = new AnnotateDataBinder(coPrixParJour)
        binder3.loadAll()
        
        def prixAchatRow = this.getFellow("prixachatId")
        if(voitureSelected.sousTraite){
            prixAchatRow.visible = true}
        else prixAchatRow.visible = false
        new AnnotateDataBinder(prixAchatRow).loadAll()
    
        def coKilometrageDepart = this.getFellow("fieldKilometrageDepart")
        def coKilometrageRetour = this.getFellow("fieldKilometrageRetour")
        new AnnotateDataBinder(coKilometrageDepart).loadAll()
        new AnnotateDataBinder(coKilometrageRetour).loadAll()
    }
    
    def genererDeclaration() {
        def image = Utilitaire.getDeclarationImage(getClass())
        def nomsociete = ChoraClientInfo.get(1).nomsociete ;
        def reportDef = new JasperReportDef(name:'Declaration_location.jasper',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : [objet],
            parameters : [  'Image' : image , 'nomsociete' : nomsociete]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "declaration_prealable_"+objet.premierConducteur.nom+"_"+objet.premierConducteur.prenom+".pdf"
        Filedownload.save(bit, "application/file", nom_fichier);
    }
    
    // Générer la copie de facture si la facture est déjà imprimée 
    def genererCopieFacture() {
        genererFactureAvecNum(numFactureLocation, "Facture")
    }
    
    def genererFacture() {
        if(factureDejaGeneree){
            genererFactureAvecNum(numFactureLocation, "Facture : duplicata")
        }
        else {
            def dialogchoix = this.getFellow("modalDialogChoix")
            dialogchoix.getFellow("fieldNumFacture").value = paternCompteurService.getProchainNumFacture()
            this.getFellow("modalDialogChoix").visible = true
            this.getFellow("modalDialogChoix").doModal() 
        }
    }
    
    def verifierNumFacture(){
        def dialogchoix = this.getFellow("modalDialogChoix")
        def numFact = dialogchoix.getFellow("fieldNumFacture").value
        
        def result = Facture.findByNumeroFacture(numFact)
        if (result != null){
            Messagebox.show("le Numéro de facture est déjà attribué !!!", "N° Facture Non valide", Messagebox.OK, Messagebox.NONE)
        }else {
            Messagebox.show("le numéro de facture est valide", "N° Facture valide", Messagebox.OK, Messagebox.INFORMATION)
            dialogchoix.getFellow("btnImprimerFact").visible = true
        }
        dialogchoix.getFellow("btnVerifierNumFact").visible = false
    }
    
    def genererFactureAvecNum(numFacture, titrerapport) {
        
        def societe = ChoraClientInfo.get(1);
        def nomsociete = societe.nomsociete ;
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
        def patentesociete = societe.patente
        def reportDef = new JasperReportDef(name:'Facture_location.jasper',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : [objet],
            parameters : ['nomsociete' : nomsociete,'titrerapport' : titrerapport , 'adressesociete' : adressesociete,'codePostalesociete' : codePostalesociete,'villesociete' : villesociete, 'payssociete' : payssociete, 'telephonesociete':telephonesociete,'emailsociete': emailsociete,'faxsociete' : faxsociete,'rcsociete' :rcsociete ,'idfsociete':idfsociete,'sitesociete':sitesociete ,'cnsssociete':cnsssociete,'patentesociete':patentesociete, 'numeroFacture' : numFacture]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "facture_location_"+objet.premierConducteur.nom+"_"+objet.premierConducteur.prenom+".pdf"
        Filedownload.save(bit, "application/file", nom_fichier);
        
        def libelleClient = objet.premierConducteur.nom +  ' ' +  objet.premierConducteur.prenom
        
        if(!factureDejaGeneree){
            def facture = new Facture(numeroFacture : numFacture, date : new Date(), client : libelleClient, location : objet)
            factureService.save(facture)
        }

    }
    
    def genererPv(avecarriereplan) {
        def arriereplan
        if(avecarriereplan) {
            arriereplan = Utilitaire.getPvImage(getClass())
        } else {
            arriereplan = Utilitaire.getBlankImage(getClass())
        }
        def reportDef = new JasperReportDef(name:'pv.jasper',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : [objet],
            parameters : ['ParBackground':arriereplan]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "pv_"+objet.premierConducteur.nom+"_"+objet.premierConducteur.prenom+".pdf"
        Filedownload.save(bit, "application/file", nom_fichier);
    }
}

