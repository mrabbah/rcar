
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
 * Reservation Window Object
 **/
class ReservationWindow extends SuperWindow {
    
    /**
     * Service pour la gestion de l'objet Reservation
     **/
    def reservationService
    /**
     * Logger de la class ReservationWindow
     **/
    private Log logger = LogFactory.getLog(ReservationWindow.class)
	
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
    public ReservationWindow () {
        super(Reservation.class, 14, ["dateDepart", "dateRestitution", "prixParJour"
                , "montantPaye", "premierConducteur", "voiture", "agentLoueurResponsable"])
    }  

    protected SuperService getService() {
        return this.reservationService
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
            //objet.kilometrageDepart = voitureSelected.kilometrage
            objet.prixParJour = voitureSelected.prixLocation
        }
        else {
            voitureSelected = null
            //objet.kilometrageDepart = null
            objet.prixParJour = null
	}	
        agentLoueurResponsables = Utilisateur.list()		
        if(agentLoueurResponsables.size() > 0){
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
            //objet.kilometrageDepart = voitureSelected.kilometrage
            objet.prixParJour = voitureSelected.prixLocation
        }
        else {
            voitureSelected = null
            //objet.kilometrageDepart = null
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
            objet.prixParJour = voitureSelected.prixLocation
            bindervoiture.loadAll()
        }
        else {
            voitureSelected = null
            //objet.kilometrageDepart = null
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
        premierConducteurSelected = premierConducteurs.find{ it.id == Reservation.findById(objet.id).premierConducteur.id }
        binderpremierConducteur.loadAll()
				
        def binderdeuxiemeConducteur = new AnnotateDataBinder(this.getFellow("codeuxiemeConducteurs"))
        //deuxiemeConducteurSelected = deuxiemeConducteurs.find{ it.id == Reservation.findById(objet.id).deuxiemeConducteur.id }
        deuxiemeConducteurSelected = objet.deuxiemeConducteur
        binderdeuxiemeConducteur.loadAll()
				
        def bindervoiture = new AnnotateDataBinder(this.getFellow("covoitures"))
        voitureSelected = voitures.find{ it.id == Reservation.findById(objet.id).voiture.id }
        //objet.kilometrageDepart = voitureSelected.kilometrage
        objet.prixParJour = voitureSelected.prixLocation
        bindervoiture.loadAll()
				
        def binderagentLoueurResponsable = new AnnotateDataBinder(this.getFellow("coagentLoueurResponsables"))
        agentLoueurResponsableSelected = agentLoueurResponsables.find{ it.id == Reservation.findById(objet.id).agentLoueurResponsable.id }
        binderagentLoueurResponsable.loadAll()
		
    }
    
    /**
     *  Cette fonction est appel�e lorsque un �l�ment de la liste est selectionn�
     **/
    def select() {                    
        super.select()
    }
    /**
     * Fonction qui se charge de sauveguarder un nouveau �l�ment de article
     **/
    def add() {
        try {
            def isReservationValid = reservationService.validatedReservation(objet, voitureSelected)
            def isResForLocationValid = reservationService.validatedReservationForLocation(objet, voitureSelected)
            if (isReservationValid && isResForLocationValid)
            super.add()
            else {
                Messagebox.show("Echec lors de l'ajout d'une réservation : voiture déjà réservée ou alouée pendant cette période \n[" +objet.dateDepart +" , "+ objet.dateRestitution+"] \n", "Erreur", Messagebox.OK, Messagebox.ERROR)
            }
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
 
    }

    def confirm() {
        try{
            reservationService.generateLocation(objet)
            super.delete()
        } catch(Exception ex){
            logger.error "Error: Erreur pendant la confirmation de réservation ${ex.message}", ex
            Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
    }
    /**
     * Pour annuler la modification ou la supression et pour basculer en mode ajout d'un nouveau �l�ment
     **/
    def cancel() {        
        super.cancel()
    }
    /**
     * Fonction qui se charge de mettre � jour un �l�ment selectionn� de article
     **/
    def update() {
        try {
            super.update()
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("Probleme lors de la mise à jour de la réservation courante", "Erreur", Messagebox.OK, Messagebox.ERROR)
        } 
        
    }
    /**
     * Fonction qui se charge de supprimer un �l�ment selectinn� de article
     **/
    def delete() {
        super.delete()
    }
   
    /**
     * Activer ou d�sactiver les boutons d'ajout, suppression, modfication
     **/
    def activerBoutons(visible) {
        this.getFellow("btnUpdate").visible = visible
        this.getFellow("btnDelete").visible = visible
        this.getFellow("btnCancel").visible = visible
        this.getFellow("btnSave").visible = !visible
        this.getFellow("btnConfirm").visible = visible
        this.getFellow("btnNewOld").visible = !visible
        this.getFellow("btnNewNew").visible = !visible
        //this.getFellow("btnExport").visible = !visible
        this.getFellow("westPanel").open = visible   
        this.getFellow("fieldDateDepart").disabled = visible
        this.getFellow("fieldDateRestitution").disabled = visible
        this.getFellow("covoitures").disabled = visible
    }
    
    def changerDeVoiture() {
        if(!this.getFellow("btnUpdate").visible) {
            objet.prixParJour = voitureSelected.prixLocation
            def coPrixParJour = this.getFellow("fieldPrixParJour")
            def binder3 = new AnnotateDataBinder(coPrixParJour)
            binder3.loadAll()
        } 
    }
    
    def newRecordNewClient(){
        def winLocCli = Executions.createComponents("/zul/location/reservationclient.zul", this, null)
        winLocCli.doModal();
    }
    
}

