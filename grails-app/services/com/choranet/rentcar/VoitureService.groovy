
package com.choranet.rentcar;

import java.util.Date;
/**
 * VoitureService Service pour la gestion des opérations
 * transactionnelles pour l'objet Voiture
 */
class VoitureService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(Voiture.class)
    }
        
    def getAlertsDecisions() throws Exception {
        Date now = new Date()
        def criteria = Voiture.createCriteria()
        def result = criteria.list {
            le("dateDecision", now - 356)
            eq("vendu", false)
        }
        return result
    }
        
    def getListVoitureGarree() throws Exception {
        def locations = getListVoitureLouee()
        def result
        if(locations.size() == 0) {
            def criteria = Voiture.createCriteria()
            result = criteria.list {
                eq("vendu", false)
            }
        } else {
            def criteria = Voiture.createCriteria()
            result = criteria.list {
                not{"in"("immatriculation", locations.voiture.immatriculation)}
                eq("vendu", false)
            }
        }
            
        return result
    }
        
    def getListVoitureLouee() throws Exception {
        Date now = new Date()
        def criteria = Location.createCriteria()
        def result = criteria.list {
            ge("dateRestitution", now)
            le("dateDepart", now)
            voiture {
                eq("vendu",false)
            }
            projections {
                    "voiture"
            }
        }
            
        return result
    }
    
    def getNbVoitureLouee() throws Exception {
        Date now = new Date()
        def criteria = Location.createCriteria()
        def result = criteria.count {
            ge("dateRestitution", now)
            le("dateDepart", now)
            voiture {
                eq("vendu",false)
            }
            projections {
                    "voiture"
            }
        }

        return result
    }
    
    def getNbVoitureGarree() throws Exception {
        def nbVoituresLouees = getNbVoitureLouee()
        def nbVoitures = Voiture.count()
        def criteria = Voiture.createCriteria()
        def nbVoituresVendu = criteria.count { eq("vendu", true) }
        def nbVoitureGarree = nbVoitures - (nbVoituresLouees + nbVoituresVendu)
        return nbVoitureGarree
    }
    
    /**
    Fonction pour la génération des entretiens périodiques pour une voiture nouvellement ajoutée
     **/
    def generateEntretiensPeriodiques(Object object) throws Exception {
        int km, prd, coef
        def typesEP = TypeEntretienPeriodique.list()
        typesEP.each(){
            km  = object.kilometrage
            prd = it.periodicite
            coef = km / prd
            if (km%prd != 0) {
                coef += 1
            }
            new com.choranet.rentcar.EntretienPeriodique(dateEntretien : null,
                kilometrageProchaineEntretien : coef * prd, details : it.libelle + ' pour ' + prd + ' (km)',
                montant : it.prixEntretien, paiementEffectue : false,
                voiture : object, typeEntretienPeriodique : it).save()
        }
    }

    /**
    Fonction pour la génération des Frais de circulation périodiques pour une voiture nouvellement ajoutée
     **/
    def generateFraisCirculationPeriodiques(Object object) throws Exception {
        //int km, prd, coef
        def currentDateMec = object.dateMiseEnCirculation
        def now = new Date()
        def currentYear = now.getYear()
        currentDateMec.setYear(currentYear)
        if(currentDateMec >= now) {
            currentDateMec.setYear(currentYear - 1)
        }
        def typesFCP = TypeFraisCirculationPeriodique.list()
        typesFCP.each(){
            int nbmois = (int) (it.periodicite / 30)
            new com.choranet.rentcar.FraisCirculationPeriodique(date : currentDateMec + it.periodicite,
                details : it.libelle + ' pour ' + nbmois + ' mois ', montant : it.chargeDeBase,
                prochainRendezVous : object.dateMiseEnCirculation + it.periodicite * 2, paiementEffectue : false,
                voiture : object, typeFraisCirculationPeriodique : it).save()
        }
    }

    /**
     * Fonction pour supprimer les entretiens périodiques pour les voitures vendus
     **/
    def deleteEntretiensPeriodiques(Object voiture) throws Exception {
        def criteria = EntretienPeriodique.createCriteria()
        def entretiensPeriodiques = criteria.list {
            eq("voiture", voiture)
        }
        entretiensPeriodiques.each{
            if (!it.paiementEffectue){
                this.delete(it)
            }
        }
    }

    /**
     * Fonction pour supprimer les entretiens périodiques pour les voitures vendus
     **/
    def deleteFraisCirculationPeriodiques(Object voiture) throws Exception {
        def criteria = FraisCirculationPeriodique.createCriteria()
        def fraisCirculationPeriodiques = criteria.list {
            eq("voiture", voiture)
        }
        fraisCirculationPeriodiques.each{
            if (!it.payementEffectue){
                this.delete(it)
            }
        }
    }
}