
package com.choranet.rentcar


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory

import org.zkoss.image.Image
import org.zkoss.zk.ui.Executions

/**
 * Voiture Window Object
 **/
class VoitureWindow extends SuperWindow {
        
    /**
     * Service pour la gestion de l'objet Voiture
     **/
    def voitureService
    /**
     * Logger de la class VoitureWindow
     **/
    private Log logger = LogFactory.getLog(VoitureWindow.class)
	
    /**
     * liste de model
     **/	
    def models	
    /**
     * model  selectionn�
     **/
    def modelSelected
	
    /**
     * liste de energie
     **/	
    def energies	
    /**
     * energie  selectionn�
     **/
    def energieSelected
    
    Boolean voitureVendue = false
    
    Boolean voitureSousTraite = false
	
    /**
     * Constructeur
     **/
    public VoitureWindow (voitureService) {
        super(Voiture.class, 4)
        this.voitureService = voitureService
        filtrer(false)
        
    }  

    protected SuperService getService() {
        return this.voitureService
    }
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
		
        models = Model.list()		
        if(models.size() > 0)
        modelSelected = models.get(0)
        else
        modelSelected = null
		
        energies = Energie.list()		
        if(energies.size() > 0)
        energieSelected = energies.get(0)
        else
        energieSelected = null
		
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
			
        if(del) {
            models = Model.list()
        }	
        if(models.size() > 0)
        modelSelected = models.get(0)
        else
        modelSelected = null
			
        if(del) {
            energies = Energie.list()
        }	
        if(energies.size() > 0)
        energieSelected = energies.get(0)
        else
        energieSelected = null
		
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
				
        objet.model = modelSelected
        if(models.size() > 0) {
            def bindermodel = new AnnotateDataBinder(this.getFellow("comodels"))
            modelSelected = models.get(0)
            bindermodel.loadAll()
        }
        else
        modelSelected = null
				
        objet.energie = energieSelected
        if(energies.size() > 0) {
            def binderenergie = new AnnotateDataBinder(this.getFellow("coenergies"))
            energieSelected = energies.get(0)
            binderenergie.loadAll()
        }
        else
        energieSelected = null
		
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
				
        def bindermodel = new AnnotateDataBinder(this.getFellow("comodels"))
        modelSelected = models.find{ it.id == Voiture.findById(objet.id).model.id }
        bindermodel.loadAll()
				
        def binderenergie = new AnnotateDataBinder(this.getFellow("coenergies"))
        energieSelected = energies.find{ it.id == Voiture.findById(objet.id).energie.id }
        binderenergie.loadAll()
		
    }

    def updatePhotoVoiture(Object media){
        updateImage(media, true)
    }
    
    def updateImageDecision(Object media){
        updateImage(media, false)
    }

    def updateImage(Object media, boolean typeImage) {
        if (media != null) {                   
            
            if (media instanceof Image) {
                if(typeImage){
                    this.getFellow("appercuPhoto").setContent(media);
                    objet.setTrans_photo((Image) media);
                } else {
                    this.getFellow("appercuDecision").setContent(media);
                    objet.setTrans_decision((Image) media);
                }                
                
            } else {
                Messagebox.show("Le fichier choisi n'est pas une image: " + media, "Erreur",
                    Messagebox.OK, Messagebox.ERROR);
                //break; //not to show too many errors
            }

        } else {
            Messagebox.show("Impossible d'envoyer le fichier choisi: " + media, "Erreur",
                Messagebox.OK, Messagebox.ERROR);
        }
    }
    
    /**
     *  Cette fonction est appel�e lorsque un �l�ment de la liste est selectionn�
     **/
    def select() {                    
        super.select()
        rafraichirApercuImage()
    }


    /**
    Fonction pour la génération des entretiens périodiques pour une voiture nouvellement ajoutée
     **/
    def generateEntretiensPeriodiques() {
        try{
            voitureService.generateEntretiensPeriodiques(objet)
        }
        catch(Exception ex) {
            logger.error "Erreur pendant la generation des entretiens périodiques pour la voiture en cours: ${ex.message}", ex
            Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
        //        finally {
        //            objet = clazz.newInstance()
        //            rafraichirField()
        //            rafraichirList()
        //            activerBoutons(false)
        //        }
    }

    /**
    Fonction pour la génération des frais de circulation périodiques pour une voiture nouvellement ajoutée
     **/
    def generateFraisCirculationPeriodiques() {
        try{
            voitureService.generateFraisCirculationPeriodiques(objet)
        }
        catch(Exception ex) {
            logger.error "Erreur pendant la generation des frais de circulation périodiques pour la voiture en cours: ${ex.message}", ex
            Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            objet = clazz.newInstance()
            rafraichirField()
            rafraichirList()
            activerBoutons(false)
        }
    }

    /**
     * Fonction pour supprimer les entretiens périodiques pour les voitures vendus
     **/
    def deleteEntretiensPeriodiques() {
        try{
            voitureService.deleteEntretiensPeriodiques(objet)  
        }
        catch(Exception ex) {
            logger.error "Erreur pendant la suppression des entretiens périodiques pour la voiture en cours: ${ex.message}", ex
            Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
        //        finally {
        //            activerBoutons(false)
        //            objet = clazz.newInstance()
        //            rafraichirField()
        //            rafraichirList()
        //        }
    }

    /**
     * Fonction pour supprimer les frais circulation périodiques pour les voitures vendus
     **/
    def deleteFraisCirculationPeriodiques() {
        try{
            voitureService.deleteFraisCirculationPeriodiques(objet)
        }
        catch(Exception ex) {
            logger.error "Erreur pendant la suppression des frais de circulation périodiques pour la voiture en cours: ${ex.message}", ex
            Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            activerBoutons(false)
            objet = clazz.newInstance()
            rafraichirField()
            rafraichirList()
        }
    }

    /**
     * Fonction qui se charge de sauveguarder un nouveau �l�ment de article
     **/
    def add() {
        try {
            if(objet.trans_photo == null) {
                objet.setTrans_photo(null);
            }
            if(objet.trans_decision == null) {
                objet.setTrans_decision(null);
            }
            
            if (!objet.vendu && !objet.sousTraite){
                super.addWithNoRefresh()
                generateEntretiensPeriodiques()
                generateFraisCirculationPeriodiques()
            } else {
                super.add()
            }
           
        } catch(Exception ex) {
            logger.error "Error: ${ex.message}", ex
            Messagebox.show("Echec lors de la transaction\n" + ex.message, "Erreur", Messagebox.OK, Messagebox.ERROR)
        } finally {
            rafraichirApercuImage()
        }
        //reinitialiserAssociation()
    }
    /**
     * Pour annuler la modification ou la supression et pour basculer en mode ajout d'un nouveau �l�ment
     **/
    def cancel() {        
        super.cancel()
        rafraichirApercuImage()
    }
    /**
     * Fonction qui se charge de mettre � jour un �l�ment selectionn� de article
     **/
    def update() {
        try {
            if (!objet.sousTraite && objet.vendu){
                super.updateWithoutRefresh()
                deleteEntretiensPeriodiques()
                deleteFraisCirculationPeriodiques()
            } else {
                super.update()
            }
        }
        catch(Exception e) {
            logger.error "Error: ${e.message}", e
            Messagebox.show("Probleme lors de la mise à jour", "Erreur", Messagebox.OK, Messagebox.ERROR)
        }
        finally {
            rafraichirApercuImage()
        }
        //reinitialiserAssociation()
    }
    /**
     * Fonction qui se charge de supprimer un �l�ment selectinn� de article
     **/
    def delete() {
        super.delete()
        rafraichirApercuImage()
    }
    
    /**
     *Reinitialiser affichage image
     * */
    def rafraichirApercuImage() {
        this.getFellow("appercuPhoto").content = objet.trans_photo
        this.getFellow("appercuDecision").content = objet.trans_decision
    }
    
    def zoomerImage(img) {
        def win = Executions.createComponents("/zul/park/zoomer.zul", null, null)
        win.getFellow("image").content = img
        win.doModal();
    }
    
    /**
     * Activer ou d�sactiver les boutons d'ajout, suppression, modfication
     **/
    def activerBoutons(visible) {
        this.getFellow("btnUpdate").visible = visible
        this.getFellow("btnDelete").visible = visible
        this.getFellow("btnCancel").visible = visible
        this.getFellow("btnSave").visible = !visible
        this.getFellow("btnNew").visible = !visible
        this.getFellow("rwVendu").visible = visible
        //this.getFellow("btnExport").visible = !visible
        this.getFellow("westPanel").open = visible        
    }
    
    def filtrer(Boolean loadList = true) {
        try {

            this.filtre.vendu = voitureVendue            
            
            this.filtre.sousTraite = voitureSousTraite
           
            
            def map
            if(attributsAFiltrer == null) {                
                map = getService().filtrer(clazz, filtre, sortedHeader, sortedDirection, ofs, maxNb)
            } else {                
                map = getService().filtrer(clazz, filtre, attributsAFiltrer, sortedHeader, sortedDirection, ofs, maxNb)
            }
            
            tailleListe = map["tailleListe"]
            listeObjets = map["listeObjets"]
            
            if (loadList){
                def binder = new AnnotateDataBinder(this.getFellow("lstObjet"))
                binder.loadAll()
            }
            
            def binder2 = new AnnotateDataBinder(this.getFellow("paging"))
            binder2.loadAll()    
        } catch (Exception ex) {
            logger.error(ex)
        }    

    }
    
    private void initialisation(clazz, maxNb, attributsAFiltrer) throws Exception {
        try {
            this.clazz = clazz
            this.maxNb = maxNb
            this.attributsAFiltrer = attributsAFiltrer
            this.nomClassFille = clazz.getSimpleName()
            ofs = 0
            
       
            //                listeObjets = clazz.list(offset:ofs, max:maxNb)
            //                def criteria = clazz.createCriteria()
            //                tailleListe = criteria.count{} 

            
            objetSelected = null
            objet = clazz.newInstance()
            filtre = clazz.newInstance()
            initialiserAssociation()
        } catch(Exception e) {
            logger.error(e)
            throw e
        }
    } 
    
}

