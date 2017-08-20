
package com.choranet.rentcar;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat


/**
 * FraisCirculationPeriodiqueService Service pour la gestion des opérations
 * transactionnelles pour l'objet FraisCirculationPeriodique
 */
class FraisCirculationPeriodiqueService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(FraisCirculationPeriodique.class)
    }
        
    def getAlertsFraisCirculationPeriodiques() {
        Date nextWeek = new Date() + 8;
        def criteria = FraisCirculationPeriodique.createCriteria()
        def result = criteria.list {
            eq("payementEffectue", false)
            le("prochainRendezVous", nextWeek)
        }
        return result
    }
        
    def getFraisCirculationPeriodique(date1, date2) {
        def criteria = FraisCirculationPeriodique.createCriteria()
        def result = criteria.get {
            projections {
                sum("montant")
            }
            between("date", date1, date2)
            eq("payementEffectue", true)
        }
        if(result == null) {
            result = 0;
        }
        return result
    }
        
    def getDepensesBetween(date1, date2) {
        def criteria = FraisCirculationPeriodique.createCriteria()
        def result = criteria.list {
            between("date", date1, date2)
            eq("payementEffectue", true)
        }
        return result
    }
        
    def getDepensesVoiture(voiture) {
        def criteria = FraisCirculationPeriodique.createCriteria()
        def result = criteria.list {
            eq("voiture", voiture)
            eq("payementEffectue", true)
        }
        return result
    }

    /**
     * Fonction pour vérifier si le champ payementEffectue est déjà seléctionné
     **/
    def fieldPayementEffectueAlreadyCheked(prochainRendezVous, voiture, typeFraisCirculationPeriodique){
        def criteria = FraisCirculationPeriodique.createCriteria()
        def result = criteria.list {
            eq("prochainRendezVous", prochainRendezVous)
            eq("voiture", voiture)
            eq("typeFraisCirculationPeriodique", typeFraisCirculationPeriodique)
            eq("payementEffectue", true)
        }
        return result.size()> 0
    }

    /**
     * Fonction pour la génération du prochain frais de circulation périodique pour une voiture,
     * suivant le paiement du précedent entretien périodique
     **/
    def generateProchainFraisCirculationPeriodique(Object object) throws Exception {
        def sdf = new SimpleDateFormat("yyyy/MM/dd")
        ef tfcp = object.typeFraisCirculationPeriodique
        def periode = object.prochainRendezVous + tfcp.periodicite
        def v = object.voiture
        new com.choranet.rentcar.FraisCirculationPeriodique(date : object.prochainRendezVous,
                details : tfcp.libelle + " pour " + sdf.format(periode), 
                montant : tfcp.chargeDeBase,
                prochainRendezVous : periode, payementEffectue : false,
                voiture : v, typeFraisCirculationPeriodique : tfcp).save()
    }
}