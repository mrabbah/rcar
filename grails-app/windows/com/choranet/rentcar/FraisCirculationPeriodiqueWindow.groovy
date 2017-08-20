
package com.choranet.rentcar


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory


/**
 * FraisCirculationPeriodique Window Object
 **/
class FraisCirculationPeriodiqueWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet FraisCirculationPeriodique
     **/
    def fraisCirculationPeriodiqueService
    /**
     * Logger de la class FraisCirculationPeriodiqueWindow
     **/
    private Log logger = LogFactory.getLog(FraisCirculationPeriodiqueWindow.class)
	
    /**
     * liste de voiture
     **/	
    def voitures	
    /**
     * voiture  selectionn�
     **/
    def voitureSelected
	
    /**
     * liste de typeFraisCirculationPeriodique
     **/	
    def typeFraisCirculationPeriodiques	
    /**
     * typeFraisCirculationPeriodique  selectionn�
     **/
    def typeFraisCirculationPeriodiqueSelected
    
    /**
     * Constructeur
     **/
    public FraisCirculationPeriodiqueWindow () {
        super(FraisCirculationPeriodique.class, 10)
    }  

    protected SuperService getService() {
        return this.fraisCirculationPeriodiqueService
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
		
        typeFraisCirculationPeriodiques = TypeFraisCirculationPeriodique.list()		
        if(typeFraisCirculationPeriodiques.size() > 0)
        typeFraisCirculationPeriodiqueSelected = typeFraisCirculationPeriodiques.get(0)
        else
        typeFraisCirculationPeriodiqueSelected = null
		
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
            typeFraisCirculationPeriodiques = TypeFraisCirculationPeriodique.list()
        }	
        if(typeFraisCirculationPeriodiques.size() > 0)
        typeFraisCirculationPeriodiqueSelected = typeFraisCirculationPeriodiques.get(0)
        else
        typeFraisCirculationPeriodiqueSelected = null
		
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
				
        objet.typeFraisCirculationPeriodique = typeFraisCirculationPeriodiqueSelected
        if(typeFraisCirculationPeriodiques.size() > 0) {
            def bindertypeFraisCirculationPeriodique = new AnnotateDataBinder(this.getFellow("cotypeFraisCirculationPeriodiques"))
            typeFraisCirculationPeriodiqueSelected = typeFraisCirculationPeriodiques.get(0)
            bindertypeFraisCirculationPeriodique.loadAll()
        }
        else
        typeFraisCirculationPeriodiqueSelected = null
		
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
				
        def bindervoiture = new AnnotateDataBinder(this.getFellow("covoitures"))
        voitureSelected = voitures.find{ it.id == FraisCirculationPeriodique.findById(objet.id).voiture.id }
        bindervoiture.loadAll()
				
        def bindertypeFraisCirculationPeriodique = new AnnotateDataBinder(this.getFellow("cotypeFraisCirculationPeriodiques"))
        typeFraisCirculationPeriodiqueSelected = typeFraisCirculationPeriodiques.find{ it.id == FraisCirculationPeriodique.findById(objet.id).typeFraisCirculationPeriodique.id }
        bindertypeFraisCirculationPeriodique.loadAll()
		
    }

    /**
     Fonction pour la génération du prochain frais de circulation périodique pour une voiture
     **/
    def generateProchainFraisCirculationPeriodique() {
        try{
            fraisCirculationPeriodiqueService.generateProchainFraisCirculationPeriodique(objet)
        }
        catch(Exception ex) {
            logger.error "Erreur pendant la generation des entretiens périodiques pour la voiture en cours: ${ex.message}", ex
            Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
    }

    /**
     * Fonction qui se charge de mettre à jour d'un frais de circulation périodique
     **/
    def update() {
        try {
            if (objet.payementEffectue &&
                !fraisCirculationPeriodiqueService.fieldPayementEffectueAlreadyCheked(
                        objet.prochainRendezVous, objet.voiture, objet.typeFraisCirculationPeriodique)){
                generateProchainFraisCirculationPeriodique()
            }
            super.update()
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("Probleme lors de la mise à jour", "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
    }
}

