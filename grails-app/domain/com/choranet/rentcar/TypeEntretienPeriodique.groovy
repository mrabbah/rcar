

package com.choranet.rentcar

import java.io.Serializable;
/**
 * TypeEntretienPeriodique Domain Object
 */
class TypeEntretienPeriodique implements Serializable {
    	
    private static final long serialVersionUID = 1L;
    
    String libelle	
       		   
    Integer periodicite

    Double prixEntretien
    
    static mapping = { 
        libelle index : "type_entretien_per_libelle"
        periodicite index : "type_entretien_per_periodicite"
        prixEntretien index : "prixEntretien"
        batchSize: 14 
    }
    static constraints = {
    
        libelle(blank : false, nullable : false, unique : true)
        periodicite(nullable : false, min : 0)
        prixEntretien(nullable : false)
    
    }
	
    String toString() {
        return libelle
    }
}

