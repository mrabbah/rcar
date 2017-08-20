

package com.choranet.rentcar

import java.io.Serializable;
/**
 * EntretienPeriodique Domain Object
 */
class EntretienPeriodique implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    Date dateEntretien	
    	
    String details	
    	
    //Date prochainRendezVous

    Integer kilometrageProchaineEntretien
    	
    Double montant

    boolean paiementEffectue
       		   
    
    static belongsTo = [voiture : Voiture , typeEntretienPeriodique : TypeEntretienPeriodique]
    
    static mapping = { 
        dateEntretien index : "entretienper_date_entretien"
        details index : "entretienper_details"
        //prochainRendezVous index : "entretienper_prochain_rendez_vous"
        kilometrageProchaineEntretien index : "kilometrageProchaineEntretien"
        montant index : "entretienper_montant"
        paiementEffectue index : "paiement_effectue"
        voiture lazy : false
        typeEntretienPeriodique lazy : false
        batchSize: 14 
    }
    static constraints = {
    
        dateEntretien(nullable : true)
    
        details()
    
        //prochainRendezVous(nullable : false)

        kilometrageProchaineEntretien(nullable : false)
    
        montant(min : 0d, nullable : false, scale : 2)

        paiementEffectue()
    
    }
	
    String toString() {
        return "Entretien : " + dateEntretien + " " + montant
    }
}

