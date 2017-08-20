
package com.choranet.rentcar;


/**
 * TypeEntretienPeriodiqueService Service pour la gestion des opérations
 * transactionnelles pour l'objet TypeEntretienPeriodique
 */
class TypeEntretienPeriodiqueService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(TypeEntretienPeriodique.class)
        }
        
}