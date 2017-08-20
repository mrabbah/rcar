
package com.choranet.rentcar;

import java.util.Calendar;

/**
 * EntretienPeriodiqueService Service pour la gestion des opérations
 * transactionnelles pour l'objet EntretienPeriodique
 */
class EntretienPeriodiqueService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(EntretienPeriodique.class)
    }
        
    def getAlertsEntretienPeriodique() {
        def result = EntretienPeriodique.findAll("from EntretienPeriodique as e where e.paiementEffectue = false and e.voiture.vendu = false and (e.kilometrageProchaineEntretien - e.voiture.kilometrage) < e.typeEntretienPeriodique.periodicite and mod(e.voiture.kilometrage , e.typeEntretienPeriodique.periodicite) >= (e.typeEntretienPeriodique.periodicite * 90 / 100)")
        return result
    }
        
    def getFraisEntretienPeriodique(date1, date2) {
        def criteria = EntretienPeriodique.createCriteria()
        def result = criteria.get {
            projections {
                sum("montant")
            }
            between("dateEntretien", date1, date2)
            eq("paiementEffectue", true)
        }
        if(result == null) {
            result = 0;
        }
        return result
    }
        
    def getDepensesBetween(date1, date2) {
        def criteria = EntretienPeriodique.createCriteria()
        def result = criteria.list {
            between("dateEntretien", date1, date2)
            eq("paiementEffectue", true)
        }
        return result
    }
        
    def getDepensesVoiture(voiture) {
        def criteria = EntretienPeriodique.createCriteria()
        def result = criteria.list {
            eq("voiture", voiture)
            eq("paiementEffectue", true)
        }
        return result
    }

    /**
     * Fonction pour vérifier si le champ paiementEffectue est déjà seléctionné
     **/
    def fieldPaiementEffectueAlreadyCheked(kilometrageProchaineEntretien, voiture, typeEntretienPeriodique){
        def criteria = EntretienPeriodique.createCriteria()
        def result = criteria.list {
            eq("kilometrageProchaineEntretien", kilometrageProchaineEntretien)
            eq("voiture", voiture)
            eq("typeEntretienPeriodique", typeEntretienPeriodique)
            eq("paiementEffectue", true)
        }
        return result.size()> 0
    }

    /**
     * Fonction pour la génération du prochain entretien périodique pour une voiture,
     * suivant le paiement du précedent entretien périodique
     **/
    def generateProchainEntretienPeriodique(Object object) throws Exception {
        def tep = object.typeEntretienPeriodique
        def periode = object.kilometrageProchaineEntretien + tep.periodicite
        def  v = object.voiture
        new EntretienPeriodique(dateEntretien : null,
            kilometrageProchaineEntretien : periode,
            details : tep.libelle + " pour " + periode + " Km",
            montant : object.montant, paiementEffectue : false,
            voiture : v, typeEntretienPeriodique : tep).save()
    }

}