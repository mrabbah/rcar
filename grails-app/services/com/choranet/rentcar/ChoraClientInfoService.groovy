package com.choranet.rentcar

class ChoraClientInfoService extends SuperService {

    static transactional = true
    
    def list() throws Exception {
        return super.list(ChoraClientInfo.class)
    }
    
    def getConfig() throws Exception {
        def config = ChoraClientInfo.list()
        def objet
        if (config == null || config == []){
            objet = new ChoraClientInfo()            
            objet = this.save(objet)
        }else {
            objet = config[0]
        }
        return objet
    }

}
