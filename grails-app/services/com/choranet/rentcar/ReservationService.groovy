
package com.choranet.rentcar;


/**
 * ReservationService Service pour la gestion des opérations
 * transactionnelles pour l'objet Reservation
 */
class ReservationService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(Reservation.class)
    }   

    /**
    Fonction pour la génération d'une location de voiture à partir de la réservation en cours
     **/
    def generateLocation(Object object) throws Exception {

        def newLocation = new com.choranet.rentcar.Location( prixParJour : object.prixParJour,
            dateDepart : object.dateDepart, dateRestitution : object.dateRestitution,
            kilometrageDepart : object.voiture.kilometrage, kilometrageRetour : null, lieuLivraison : object.lieuLivraison,
            lieuReprise : object.lieuReprise, montantPaye : object.montantPaye, modePaiment : object.modePaiment,
            observations : object.observations, contratNumeriser : null, declarationPrealableNumeriser : null,
            premierConducteur : object.premierConducteur, deuxiemeConducteur : object.deuxiemeConducteur, voiture : object.voiture,
            agentLoueurResponsable : object.agentLoueurResponsable)
        newLocation.setTrans_contrat(null)
        newLocation.setTrans_declaration(null)
        newLocation.save()
    }

    /**
     * Fonction pour la validation d'une réservation donnée à la base des réservations déjà passées
     **/
    def validatedReservation(reservation, voitureSelected) throws Exception {
        def criteria = Reservation.createCriteria()
        def reservationAlreadyPassed = criteria.list {
            eq("voiture", voitureSelected)
            or {
                between("dateDepart", reservation.dateDepart, reservation.dateRestitution)
                between("dateRestitution", reservation.dateDepart, reservation.dateRestitution)
                and {
                    le("dateDepart", reservation.dateDepart)
                    ge("dateRestitution", reservation.dateRestitution)
                }
            }       
        }
        if (reservationAlreadyPassed != null) {
            return reservationAlreadyPassed.size()<= 0
        }
        else return true
    }

    /**
     * Fonction pour la validation d'une réservation donnée à base des locations déjà passées
     **/
    def validatedReservationForLocation(reservation, voitureSelected)throws Exception {
        def criteria = Location.createCriteria()
        def locationAlreadyPassed = criteria.list {
            eq("voiture", voitureSelected)
            or {
                between("dateDepart", reservation.dateDepart, reservation.dateRestitution)
                between("dateRestitution", reservation.dateDepart, reservation.dateRestitution)
                and {
                    le("dateDepart", reservation.dateDepart)
                    ge("dateRestitution", reservation.dateRestitution)
                }
            }
        }
        if (locationAlreadyPassed != null) {
            return locationAlreadyPassed.size()<= 0
        }
        else return true
    }
    
    def getAlertsReservation() {
        Date now = new Date()
        def criteria = Reservation.createCriteria()
        def result = criteria.list {
            le("dateDepart", now + 7)
        }
        return result
    }
}