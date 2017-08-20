

package com.choranet.rentcar

import java.io.Serializable;
/**
 * FraisCirculation Domain Object
 */
class FraisCirculation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    Date date	
    	
    String details	
    	
    Double montant	
       		   
    
    static belongsTo = [voiture : Voiture , typeFraisCirculation : TypeFraisCirculation]
    
    static mapping = { 
        date index : "frais_circulation_date"
        details index : "frais_circulation_details"
        montant index : "frais_circulation_montant"
        voiture lazy : false
        typeFraisCirculation lazy : false
        batchSize: 14 
    }
    static constraints = {
    
        date(nullable : false)
    
        details()
    
        montant(min : 0d, nullable : false, scale : 2)
    
    }
	
    String toString() {
        return "FC : " + date + " " + montant
    }
}

