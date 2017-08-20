
package com.choranet.rentcar


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory


/**
 * Energie Window Object
 **/
class EnergieWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Energie
     **/
    def energieService
    /**
     * Logger de la class EnergieWindow
     **/
    private Log logger = LogFactory.getLog(EnergieWindow.class)
	
    /**
     * Constructeur
     **/
    public EnergieWindow () {
        super(Energie.class)
    }  

    protected SuperService getService() {
        return this.energieService
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

