

package com.choranet.rentcar

import java.io.Serializable;
/**
 * Energie Domain Object
 */
class Energie implements Serializable {
  
private static final long serialVersionUID = 1L;

    String libelle	
       		   
    
    static mapping = { 
        libelle index : "energie_libelle"
        batchSize: 14 
    }
    static constraints = {
    
        libelle(blank : false, nullable : false, unique : true)
    
    }
	
    String toString() {
        return libelle
    }
}

