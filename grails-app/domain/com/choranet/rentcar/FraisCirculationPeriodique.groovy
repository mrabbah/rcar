

package com.choranet.rentcar

import java.io.Serializable;
/**
 * FraisCirculationPeriodique Domain Object
 */
class FraisCirculationPeriodique implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    Date date	
    	
    String details	
    	
    Double montant	
    	
    Date prochainRendezVous
    
    boolean payementEffectue = false
       		   
    
    static belongsTo = [voiture : Voiture , typeFraisCirculationPeriodique : TypeFraisCirculationPeriodique]
    
    static mapping = { 
        date index : "frais_circulation_per_date"
        details index : "frais_circulation_per_details"
        montant index : "frais_circulation_per_montant"
        prochainRendezVous index : "frais_circulation_per_prochain_rendez_vous"
        payementEffectue index : "payement_effectue"
        voiture lazy : false
        typeFraisCirculationPeriodique lazy : false
        batchSize: 14 
    }
    static constraints = {
    
        date(nullable : true)
    
        details(nullable : true)
    
        montant(min : 0d, nullable : true, scale : 2)
    
        prochainRendezVous(nullable : false)
        
        payementEffectue()
    
    }
	
    String toString() {
        return "FCP : " + date + " " + montant
    }
}

