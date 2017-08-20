

package com.choranet.rentcar


import java.io.Serializable;
import org.apache.commons.lang.builder.* 

/**
 * PaternCompteur Domain Object 
 */
class PaternCompteur implements Serializable {	
    private static final long serialVersionUID = 1L;
    	
    String libelle	
    	
    String prefixe	
    	
    String suffixe	
    	
    Integer pas	
    	
    Integer remplissage	
    	
    Integer numeroSuivant	
    	
    String type	
    
    static mapping = { 
        libelle index : "paterncompteur_libelle"
        prefixe index : "paterncompteur_prefixe"
        suffixe index : "paterncompteur_suffixe"
        pas index : "paterncompteur_pas"
        remplissage index : "paterncompteur_remplissage"
        numeroSuivant index : "paterncompteur_numero_suivant"
        type index : "paterncompteur_type"
        batchSize: 14 
        cache usage : 'nonstrict-read-write'
    }
    static constraints = {
    
        libelle(blank : false, nullable : false, unique : true)
    
        prefixe(blank : true, nullable : false)
    
        suffixe(blank : true, nullable : false)
    
        pas(min:1, nullable : false)
    
        remplissage(min:1, nullable : false)
    
        numeroSuivant(min : 0, nullable : false)
    
        type(blank : false, nullable : false, inList:["FACTURE"])
    
    }
	
    String toString() {
        return libelle
    }
    
    @Override 
    boolean equals(final Object that) { 
            EqualsBuilder.reflectionEquals(this, that, ["libelle"]) 
    } 

    @Override 
    int hashCode() { 
            HashCodeBuilder.reflectionHashCode(this, ["libelle"]) 
    } 
}

