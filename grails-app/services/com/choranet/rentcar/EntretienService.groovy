
package com.choranet.rentcar;


/**
 * EntretienService Service pour la gestion des opérations
 * transactionnelles pour l'objet Entretien
 */
class EntretienService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Entretien.class)
        }
        
        def getFraisEntretien(date1, date2) {
            def criteria = Entretien.createCriteria()
            def result = criteria.get {
                projections {
                    sum("montant")
                }
                between("dateEntretien", date1, date2)
            }
            if(result == null) {
                result = 0;
            }
            return result
        }
        
        def getDepensesBetween(date1, date2) {
            def criteria = Entretien.createCriteria()
            def result = criteria.list {
                between("dateEntretien", date1, date2)
            }
            return result
        }
        
        def getDepensesVoiture(voiture) {
            def criteria = Entretien.createCriteria()
            def result = criteria.list {
                eq("voiture", voiture)
            }
            return result
        }
}