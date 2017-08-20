

package com.choranet.rentcar

import java.io.Serializable;
/**
 * TypeFraisCirculation Domain Object
 */
class TypeFraisCirculation implements Serializable {
    	
    private static final long serialVersionUID = 1L;
    
    String libelle	
       		   
    
    static mapping = { 
        libelle index : "type_frais_circu_libelle"
        batchSize: 14 
    }
    static constraints = {
    
        libelle(blank : false, nullable : false, unique : true)
    
    }
	
    String toString() {
        return libelle
    }
}

