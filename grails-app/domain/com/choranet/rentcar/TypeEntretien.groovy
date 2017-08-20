

package com.choranet.rentcar

import java.io.Serializable;
/**
 * TypeEntretien Domain Object
 */
class TypeEntretien implements Serializable {
    	
    private static final long serialVersionUID = 1L;
    
    String libelle	
       		   
    
    static mapping = { 
        libelle index : "type_entretien_libelle"
        batchSize: 14 
    }
    static constraints = {
    
        libelle(blank : false, nullable : false, unique : true)
    
    }
	
    String toString() {
        return libelle
    }
}

