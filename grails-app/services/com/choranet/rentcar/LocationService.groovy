
package com.choranet.rentcar;


/**
 * LocationService Service pour la gestion des opérations
 * transactionnelles pour l'objet Location
 */
class LocationService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(Location.class)
    }
        
    def getSommeBenifices(date1, date2) {
        def criteria = Location.createCriteria()
        def result = criteria.get {
            between("dateRestitution", date1, date2)
            projections {
                sum("montantPaye")
            }
        }
        if(result == null) {
            result = 0;
        }
        return result
    }
        
    def getLocationsBetween(date1, date2) {
        def criteria = Location.createCriteria()
        def result = criteria.list {
            between("dateDepart", date1, date2)
        }
        return result
    }
        
    def getLocationsVoiture(voiture) {
        def criteria = Location.createCriteria()
        def result = criteria.list {
            eq('voiture', voiture)
        }
        return result
    }
     
    def getKilometrageVoiture(voiture, currentKilometrageRetour){
        def criteria = Location.createCriteria()
        def result = criteria.list {
            and {
                eq('voiture', voiture)
                isNotNull('kilometrageRetour')
            }
            order('kilometrageRetour', 'desc')
        }
        if (result.size() == 0){
            return currentKilometrageRetour
        }
        return currentKilometrageRetour
    }
    
    def getKilometrageVoitureForUpdate(idLocation, voiture, currentKilometrageRetour){
        def criteria = Location.createCriteria()
        def result = criteria.list {
            and {
                not{
                    idEq(idLocation)
                }
                eq('voiture', voiture)
                isNotNull('kilometrageRetour')
            }
            order('kilometrageRetour', 'desc')
        }
        if (result.size() == 0){
            return currentKilometrageRetour
        }
        def newKilometrage = result.get(0).kilometrageRetour
        if (newKilometrage < currentKilometrageRetour){
            newKilometrage = currentKilometrageRetour
        }
        return newKilometrage
    }
}