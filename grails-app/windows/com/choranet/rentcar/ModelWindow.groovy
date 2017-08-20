
package com.choranet.rentcar


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory


/**
 * Model Window Object
 **/
class ModelWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Model
     **/
    def modelService
    /**
     * Logger de la class ModelWindow
     **/
    private Log logger = LogFactory.getLog(ModelWindow.class)
	
    /**
     * liste de marque
     **/	
    def marques	
    /**
     * marque  selectionn�
     **/
    def marqueSelected
	
    /**
     * Constructeur
     **/
    public ModelWindow () {
        super(Model.class)
    }  

    protected SuperService getService() {
        return this.modelService
    }
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
		
        marques = Marque.list()		
        if(marques.size() > 0)
        marqueSelected = marques.get(0)
        else
        marqueSelected = null
		
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
			
        if(del) {
            marques = Marque.list()
        }	
        if(marques.size() > 0)
        marqueSelected = marques.get(0)
        else
        marqueSelected = null
		
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
				
        objet.marque = marqueSelected
        if(marques.size() > 0) {
            def bindermarque = new AnnotateDataBinder(this.getFellow("comarques"))
            marqueSelected = marques.get(0)
            bindermarque.loadAll()
        }
        else
        marqueSelected = null
		
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
				
        def bindermarque = new AnnotateDataBinder(this.getFellow("comarques"))
        marqueSelected = marques.find{ it.id == Model.findById(objet.id).marque.id }
        bindermarque.loadAll()
		
    }
}

