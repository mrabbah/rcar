
package com.choranet.rentcar


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory

import org.zkoss.image.Image
import org.zkoss.zk.ui.Executions
import org.springframework.web.context.request.RequestContextHolder

/**
 * Location Window Object
 **/
class LocationClientWindow extends Window {
    def reservationService
    def voitureService
    /**
     * Service pour la gestion de l'objet Client
     **/
    def clientService
    /**
     * Service pour la gestion de l'objet Location
     **/
    def locationService
    /**
     * Logger de la class LocationWindow
     **/
    private Log logger = LogFactory.getLog(LocationClientWindow.class)
	
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
     *location actuelle
     **/
    def location
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
    public LocationClientWindow () {
        
        premierConducteur = new Client();
        deuxiemeConducteur = new Client();
        location = new Location();
        
        voitures = Voiture.findAllByVendu(false)
        if(voitures.size() > 0) {
            voitureSelected = voitures.get(0)
            location.kilometrageDepart = voitureSelected.kilometrage
            location.prixParJour = voitureSelected.prixLocation
        }
        else {
            voitureSelected = null
            location.kilometrageDepart = null
            location.prixParJour = null
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
    
    def updateImageContrat(Object media) {        
        if (media != null) {                   
            
            if (media instanceof Image) {
                this.getFellow("appercuContrat").setContent(media);   
                location.setTrans_contrat((Image) media);
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
                location.setTrans_declaration((Image) media);
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
     * Fonction qui se charge de sauveguarder un nouveau �l�ment de article
     **/
    def ok() {
        try {
            
            def isReservationValid = reservationService.validatedReservation(location, voitureSelected)
            def isResForLocationValid = reservationService.validatedReservationForLocation(location, voitureSelected)
            if (isReservationValid && isResForLocationValid) {
                if(location.trans_contrat == null) {
                    location.setTrans_contrat(null);
                }
                if(location.trans_declaration == null) {
                    location.setTrans_declaration(null);
                }
                try {     
                    premierConducteur.nationalite = nationaliteOneSelected
                    clientService.save(premierConducteur)
                    location.premierConducteur = premierConducteur
                    def checked = this.getFellow("checkSecond").checked
                    if(checked) {
                        try {
                            deuxiemeConducteur.nationalite = nationaliteTowSelected
                            clientService.save(deuxiemeConducteur)
                            location.deuxiemeConducteur = deuxiemeConducteur
                        } catch(Exception ex) {
                            logger.error "Error: ${ex.message}", ex
                            Messagebox.show("Impossible d'ajouter le deuxième conducteur vérifier si ce client n'est pas déjà présent\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
                        }
                    }
                    try {
                        location.voiture = voitureSelected
                        location.agentLoueurResponsable = agentLoueurResponsableSelected
                        locationService.save(location)
                        if(location.kilometrageRetour != null) {
                            location.voiture.kilometrage = location.kilometrageRetour
                            voitureService.update(location.voiture)
                        }
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
                Messagebox.show("Echec lors de l'ajout d'une location : voiture déjà réservée ou alouée pendant cette période \n[" +location.dateDepart +" , "+ location.dateRestitution+"] \n", "Erreur", Messagebox.OK, Messagebox.ERROR)
            }
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
        } 
        
    }
    
    def changerDeVoiture() {
        location.kilometrageDepart = voitureSelected.kilometrage
        location.kilometrageRetour = null
        location.prixParJour = voitureSelected.prixLocation
        def coPrixParJour = this.getFellow("fieldPrixParJour")
        def binder3 = new AnnotateDataBinder(coPrixParJour)
        binder3.loadAll()
        def coKilometrageDepart = this.getFellow("fieldKilometrageDepart")
        def coKilometrageRetour = this.getFellow("fieldKilometrageRetour")
        def binder1 = new AnnotateDataBinder(coKilometrageDepart)
        binder1.loadAll()
        def binder2 = new AnnotateDataBinder(coKilometrageRetour)
        binder2.loadAll()
    }
    
}

