
package com.choranet.rentcar


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory


/**
 * TypeEntretien Window Object
 **/
class TypeEntretienWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet TypeEntretien
     **/
    def typeEntretienService
    /**
     * Logger de la class TypeEntretienWindow
     **/
    private Log logger = LogFactory.getLog(TypeEntretienWindow.class)
	
    /**
     * Constructeur
     **/
    public TypeEntretienWindow () {
        super(TypeEntretien.class)
    }  

    protected SuperService getService() {
        return this.typeEntretienService
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

