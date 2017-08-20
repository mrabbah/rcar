

package com.choranet.rentcar

import java.io.Serializable;
/**
 * Entretien Domain Object
 */
class Entretien implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    Date dateEntretien	
    	
    String details	
    	
    Double montant	
       		   
    
    static belongsTo = [typeEntretien : TypeEntretien , voiture : Voiture]
    
    static mapping = { 
        dateEntretien index : "entretien_date_entretien"
        details index : "entretien_details"
        montant index : "entretien_montant"
        typeEntretien lazy : false
        voiture lazy : false
        batchSize: 14 
    }
    static constraints = {
    
        dateEntretien(nullable : false)
    
        details()
    
        montant(min : 0d, nullable : false, scale : 2)
    
    }
	
    String toString() {
        return "Entretien : " + dateEntretien + " " + montant
    }
}

