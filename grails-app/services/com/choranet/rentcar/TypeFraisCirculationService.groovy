
package com.choranet.rentcar;


/**
 * TypeFraisCirculationService Service pour la gestion des opérations
 * transactionnelles pour l'objet TypeFraisCirculation
 */
class TypeFraisCirculationService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(TypeFraisCirculation.class)
        }
}