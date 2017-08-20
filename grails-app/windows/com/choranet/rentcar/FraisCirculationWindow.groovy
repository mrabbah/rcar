
package com.choranet.rentcar


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory


/**
 * FraisCirculation Window Object
 **/
class FraisCirculationWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet FraisCirculation
     **/
    def fraisCirculationService
    /**
     * Logger de la class FraisCirculationWindow
     **/
    private Log logger = LogFactory.getLog(FraisCirculationWindow.class)
	
    /**
     * liste de voiture
     **/	
    def voitures	
    /**
     * voiture  selectionn�
     **/
    def voitureSelected
	
    /**
     * liste de typeFraisCirculation
     **/	
    def typeFraisCirculations	
    /**
     * typeFraisCirculation  selectionn�
     **/
    def typeFraisCirculationSelected
	
    /**
     * Constructeur
     **/
    public FraisCirculationWindow () {
        super(FraisCirculation.class, 10)
    }  

    protected SuperService getService() {
        return this.fraisCirculationService
    }
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
		
        voitures = Voiture.list()		
        if(voitures.size() > 0)
        voitureSelected = voitures.get(0)
        else
        voitureSelected = null
		
        typeFraisCirculations = TypeFraisCirculation.list()		
        if(typeFraisCirculations.size() > 0)
        typeFraisCirculationSelected = typeFraisCirculations.get(0)
        else
        typeFraisCirculationSelected = null
		
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
			
        if(del) {
            voitures = Voiture.list()
        }	
        if(voitures.size() > 0)
        voitureSelected = voitures.get(0)
        else
        voitureSelected = null
			
        if(del) {
            typeFraisCirculations = TypeFraisCirculation.list()
        }	
        if(typeFraisCirculations.size() > 0)
        typeFraisCirculationSelected = typeFraisCirculations.get(0)
        else
        typeFraisCirculationSelected = null
		
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
				
        objet.voiture = voitureSelected
        if(voitures.size() > 0) {
            def bindervoiture = new AnnotateDataBinder(this.getFellow("covoitures"))
            voitureSelected = voitures.get(0)
            bindervoiture.loadAll()
        }
        else
        voitureSelected = null
				
        objet.typeFraisCirculation = typeFraisCirculationSelected
        if(typeFraisCirculations.size() > 0) {
            def bindertypeFraisCirculation = new AnnotateDataBinder(this.getFellow("cotypeFraisCirculations"))
            typeFraisCirculationSelected = typeFraisCirculations.get(0)
            bindertypeFraisCirculation.loadAll()
        }
        else
        typeFraisCirculationSelected = null
		
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
				
        def bindervoiture = new AnnotateDataBinder(this.getFellow("covoitures"))
        voitureSelected = voitures.find{ it.id == FraisCirculation.findById(objet.id).voiture.id }
        bindervoiture.loadAll()
				
        def bindertypeFraisCirculation = new AnnotateDataBinder(this.getFellow("cotypeFraisCirculations"))
        typeFraisCirculationSelected = typeFraisCirculations.find{ it.id == FraisCirculation.findById(objet.id).typeFraisCirculation.id }
        bindertypeFraisCirculation.loadAll()
		
    }
}

