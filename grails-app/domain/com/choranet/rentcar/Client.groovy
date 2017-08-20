

package com.choranet.rentcar

import java.io.Serializable;

/**
 * Client Domain Object
 */
class Client implements Serializable {
   
private static final long serialVersionUID = 1L;

    String nom	
    	
    String prenom

    Date dateNaissance

    String lieuNaissance
    	
    String cin	
    	
    String cne
    	
    String carteSejour

    String numeroPassport

    String adresseMaroc	
    
    String adresseEtranger

    String ville

    String codePostale
    	
    String gsm	
    	
    String fixe	
    	
    String numeroPermis	
    	
    String mail	
       		   
    
    static belongsTo = [nationalite : Nationalite]
    
    //static hasMany = [locations : Location]
    
    //static mappedbBy =  [locations : 'premierConducteur']
    
    static mapping = { 
        nom index : "nom"
        prenom index : "prenom"
        dateNaissance index : "date_naissance"
        lieuNaissance index : "lieu_naissance"
        cin index : "cin"
        cne index : "cne"
        carteSejour index : "carte_sejour"
        numeroPassport index : "numero_Passport"
        adresseMaroc index : "adresse_maroc"
        adresseEtranger index : "adresse_etranger"
        ville index : "ville"
        codePostale index : "codePostale"
        gsm index : "gsm"
        fixe index : "fixe"
        numeroPermis index : "numero_permis"
        mail index : "mail"
        nationalite lazy : false
        //locations lazy : false
        batchSize: 14 
    }
    static constraints = {
    
        nom(blank : false, nullable : false)
    
        prenom(blank : false, nullable : false)

        dateNaissance(nullable : true)

        lieuNaissance(nullable : true)
    
        cin(nullable : true)
    
        cne(nullable : true)
    
        carteSejour(nullable : true)

        numeroPassport(nullable : true)
    
        adresseMaroc(nullable : true)
    
        adresseEtranger(nullable : true)

        ville(nullable : true)

        codePostale(nullable : true)
    
        gsm()
    
        fixe()
    
        numeroPermis(blank : true, nullable : true, unique : true)
    
        mail(email : true)
    
    }
	
    String toString() {
        return nom + ' : ' + prenom;
    }
}

