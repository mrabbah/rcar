
package com.choranet.rentcar


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory


/**
 * TypeFraisCirculationPeriodique Window Object
 **/
class TypeFraisCirculationPeriodiqueWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet TypeFraisCirculationPeriodique
     **/
    def typeFraisCirculationPeriodiqueService
    /**
     * Logger de la class TypeFraisCirculationPeriodiqueWindow
     **/
    private Log logger = LogFactory.getLog(TypeFraisCirculationPeriodiqueWindow.class)
	
    /**
     * Constructeur
     **/
    public TypeFraisCirculationPeriodiqueWindow () {
        super(TypeFraisCirculationPeriodique.class)
    }  

    protected SuperService getService() {
        return this.typeFraisCirculationPeriodiqueService
    }
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
		
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
		
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
		
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
		
    }
}

