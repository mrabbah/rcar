
package com.choranet.rentcar


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory


/**
 * Entretien Window Object
 **/
class EntretienWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Entretien
     **/
    def entretienService
    /**
     * Logger de la class EntretienWindow
     **/
    private Log logger = LogFactory.getLog(EntretienWindow.class)
	
    /**
     * liste de typeEntretien
     **/	
    def typeEntretiens	
    /**
     * typeEntretien  selectionn�
     **/
    def typeEntretienSelected
	
    /**
     * liste de voiture
     **/	
    def voitures	
    /**
     * voiture  selectionn�
     **/
    def voitureSelected
	
    /**
     * Constructeur
     **/
    public EntretienWindow () {
        super(Entretien.class, 10)
    }  

    protected SuperService getService() {
        return this.entretienService
    }
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
		
        typeEntretiens = TypeEntretien.list()		
        if(typeEntretiens.size() > 0)
        typeEntretienSelected = typeEntretiens.get(0)
        else
        typeEntretienSelected = null
		
        voitures = Voiture.list()		
        if(voitures.size() > 0)
        voitureSelected = voitures.get(0)
        else
        voitureSelected = null
		
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
			
        if(del) {
            typeEntretiens = TypeEntretien.list()
        }	
        if(typeEntretiens.size() > 0)
        typeEntretienSelected = typeEntretiens.get(0)
        else
        typeEntretienSelected = null
			
        if(del) {
            voitures = Voiture.list()
        }	
        if(voitures.size() > 0)
        voitureSelected = voitures.get(0)
        else
        voitureSelected = null
		
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
				
        objet.typeEntretien = typeEntretienSelected
        if(typeEntretiens.size() > 0) {
            def bindertypeEntretien = new AnnotateDataBinder(this.getFellow("cotypeEntretiens"))
            typeEntretienSelected = typeEntretiens.get(0)
            bindertypeEntretien.loadAll()
        }
        else
        typeEntretienSelected = null
				
        objet.voiture = voitureSelected
        if(voitures.size() > 0) {
            def bindervoiture = new AnnotateDataBinder(this.getFellow("covoitures"))
            voitureSelected = voitures.get(0)
            bindervoiture.loadAll()
        }
        else
        voitureSelected = null
		
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
				
        def bindertypeEntretien = new AnnotateDataBinder(this.getFellow("cotypeEntretiens"))
        typeEntretienSelected = typeEntretiens.find{ it.id == Entretien.findById(objet.id).typeEntretien.id }
        bindertypeEntretien.loadAll()
				
        def bindervoiture = new AnnotateDataBinder(this.getFellow("covoitures"))
        voitureSelected = voitures.find{ it.id == Entretien.findById(objet.id).voiture.id }
        bindervoiture.loadAll()
		
    }
}

