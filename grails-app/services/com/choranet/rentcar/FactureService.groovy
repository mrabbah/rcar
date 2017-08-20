
package com.choranet.rentcar;

/**
 * FactureService Service pour la gestion des opérations
 * transactionnelles pour l'objet Facture
 */
class FactureService extends SuperService {

    def paternCompteurService
    
    static transactional = true

    def list() throws Exception {
        return super.list(Facture.class)
    }
    
    def update(Object object) throws Exception {
        //def paiements = object.paiements
        object = super.update(object)
        //updateEtatFacturationBonCommande(paiements)
        return object
    }
    
    def save(Object object) throws Exception {
        //def paiements = object.paiements
        object.numeroFacture = paternCompteurService.getProchainNumFactureEtIncrementer()   
        object = super.save(object)
        return object
    }
    
    def delete(Object object) throws Exception {
        //def paiements = object.paiements
        super.delete(object)
    }
}