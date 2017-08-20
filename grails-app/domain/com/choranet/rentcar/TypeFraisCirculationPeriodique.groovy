

package com.choranet.rentcar

import java.io.Serializable;

/**
 * TypeFraisCirculationPeriodique Domain Object
 */
class TypeFraisCirculationPeriodique implements Serializable {
    	
    private static final long serialVersionUID = 1L;
    
    String libelle

    Integer periodicite

    Double chargeDeBase
       		   
    static mapping = { 
        libelle index : "type_circul_per_libelle"
        periodicite index : "periodicite"
        chargeDeBase index : "chargeDeBase"
        batchSize: 14 
    }
    static constraints = {
    
        libelle(blank : false, nullable : false, unique : true)
        periodicite(nullable : false)
        chargeDeBase()
    }
	
    String toString() {
        return libelle
    }
}

