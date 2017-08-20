package com.choranet.rentcar

class GroupeUtilisateurService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(GroupeUtilisateur.class)
    }
    
    def getGroupeByAuthority(authority) throws Exception {
        return GroupeUtilisateur.findByAuthority(authority)
    }
}
