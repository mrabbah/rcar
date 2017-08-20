
package com.choranet.rentcar


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory

import org.zkoss.image.Image
import org.zkoss.zk.ui.Executions
import org.springframework.web.context.request.RequestContextHolder

/**
 * Reservation Window Object
 **/
class ReservationClientWindow extends Window {
    def reservationService
    /**
     * Service pour la gestion de l'objet Client
     **/
    def clientService
    
    /**
     * Logger de la class ReservationWindow
     **/
    private Log logger = LogFactory.getLog(ReservationClientWindow.class)
	
    /**
     * premierConducteur
     **/	
    def premierConducteur	
	
    /**
     * deuxiemeConducteur
     **/	
    def deuxiemeConducteur	
	
    /**
     * liste de voiture
     **/	
    def voitures
    
    /**
     * Voiture selectionnee
     * */
    def voitureSelected
    
    /**
     * liste de nationalite
     **/	
    def nationalites
    
    /**
     * nationalite premier conducteur
     **/
    def nationaliteOneSelected
    
    /**
     * nationalite second conducteur
     **/
    def nationaliteTowSelected
    
    /**
     *reservation actuelle
     **/
    def reservation
    /**
     * liste de agentLoueurResponsable
     **/	
    def agentLoueurResponsables	
    
    /**
     * agent loueur
     * */
    def agentLoueurResponsableSelected
    
	
    /**
     * Constructeur
     **/
    public ReservationClientWindow () {
        
        premierConducteur = new Client();
        deuxiemeConducteur = new Client();
        reservation = new Reservation();
        reservation.montantPaye = 0.0
        
        voitures = Voiture.findAllByVendu(false)
        if(voitures.size() > 0) {
            voitureSelected = voitures.get(0)
            reservation.prixParJour = voitureSelected.prixLocation
        }
        else {
            voitureSelected = null
            reservation.prixParJour = null
	}	
        
        agentLoueurResponsables = Utilisateur.list()		
        if(agentLoueurResponsables.size() > 0){
            def session = RequestContextHolder.currentRequestAttributes().getSession()
            agentLoueurResponsableSelected = agentLoueurResponsables.find{ it.id == session.utilisateur.id }
        }
        else
        agentLoueurResponsableSelected = null
        
        nationalites = Nationalite.list()		
        if(nationalites.size() > 0) {
            nationaliteOneSelected = nationalites.get(0)
            //nationaliteTowSelected = nationalites.get(0)
        } else {
            nationaliteOneSelected = null
            //nationaliteTowSelected = null
        }
        
    }  
     
    /**
     * Fonction qui se charge de sauveguarder un nouveau �l�ment de article
     **/
    def ok() {
        try {
            
            def isReservationValid = reservationService.validatedReservation(reservation, voitureSelected)
            def isResForReservationValid = reservationService.validatedReservationForLocation(reservation, voitureSelected)
            if (isReservationValid && isResForReservationValid) {
                
                try {     
                    premierConducteur.nationalite = nationaliteOneSelected
                    clientService.save(premierConducteur)
                    reservation.premierConducteur = premierConducteur
                    def checked = this.getFellow("checkSecond").checked
                    if(checked) {
                        try {
                            deuxiemeConducteur.nationalite = nationaliteTowSelected
                            clientService.save(deuxiemeConducteur)
                            reservation.deuxiemeConducteur = deuxiemeConducteur
                        } catch(Exception ex) {
                            logger.error "Error: ${ex.message}", ex
                            Messagebox.show("Impossible d'ajouter le deuxième conducteur vérifier si ce client n'est pas déjà présent\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
                        }
                    }
                    try {
                        reservation.voiture = voitureSelected
                        reservation.agentLoueurResponsable = agentLoueurResponsableSelected
                        reservationService.save(reservation)
                        parent.rafraichirList()
                        parent.reinitialiserAssociation(true)
                    } catch(Exception ex) {
                        logger.error "Error: ${ex.message}", ex
                        Messagebox.show("Impossible de créer l'enregistrement\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
                    }
                
                    onClose()
                    
                } catch(Exception ex) {
                    logger.error "Error: ${ex.message}", ex
                    Messagebox.show("Impossible d'ajouter le premier conducteur vérifier si ce client n'est pas déjà présent\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
                }
            } else {
                Messagebox.show("Echec lors de l'ajout d'une réservation : voiture déjà réservée ou alouée pendant cette période \n[" +reservation.dateDepart +" , "+ reservation.dateRestitution+"] \n", "Erreur", Messagebox.OK, Messagebox.ERROR)
            }
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
        } 
        
    }
    
    def changerDeVoiture() {
        reservation.prixParJour = voitureSelected.prixLocation
        def coPrixParJour = this.getFellow("fieldPrixParJour")
        def binder3 = new AnnotateDataBinder(coPrixParJour)
        binder3.loadAll()
    }
    
}

