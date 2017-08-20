
package com.choranet.rentcar


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory


/**
 * Marque Window Object
 **/
class MarqueWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Marque
     **/
    def marqueService
    /**
     * Logger de la class MarqueWindow
     **/
    private Log logger = LogFactory.getLog(MarqueWindow.class)
	
    /**
     * Constructeur
     **/
    public MarqueWindow () {
        super(Marque.class)
    }  

    protected SuperService getService() {
        return this.marqueService
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

