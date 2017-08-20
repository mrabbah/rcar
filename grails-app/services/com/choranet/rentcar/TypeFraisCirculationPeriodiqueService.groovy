
package com.choranet.rentcar;


/**
 * TypeFraisCirculationPeriodiqueService Service pour la gestion des opérations
 * transactionnelles pour l'objet TypeFraisCirculationPeriodique
 */
class TypeFraisCirculationPeriodiqueService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(TypeFraisCirculationPeriodique.class)
        }
}