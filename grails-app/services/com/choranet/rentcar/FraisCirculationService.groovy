
package com.choranet.rentcar;


/**
 * FraisCirculationService Service pour la gestion des opérations
 * transactionnelles pour l'objet FraisCirculation
 */
class FraisCirculationService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(FraisCirculation.class)
        }
        
        def getFraisCirculation(date1, date2) {
            def criteria = FraisCirculation.createCriteria()
            def result = criteria.get {
                projections {
                    sum("montant")
                }
                between("date", date1, date2)
            }
            if(result == null) {
                result = 0;
            }
            return result
        }   
        
        def getDepensesBetween(date1, date2) {
            def criteria = FraisCirculation.createCriteria()
            def result = criteria.list {
                between("date", date1, date2)
            }
            return result
        }
        
        def getDepensesVoiture(voiture) {
            def criteria = FraisCirculation.createCriteria()
            def result = criteria.list {
                eq("voiture", voiture)
            }
            return result
        }
}