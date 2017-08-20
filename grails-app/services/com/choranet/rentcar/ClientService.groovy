
package com.choranet.rentcar;


/**
 * ClientService Service pour la gestion des opérations
 * transactionnelles pour l'objet Client
 */
class ClientService extends SuperService {

        static transactional = true

        def list() throws Exception {
            return super.list(Client.class)
        }
}