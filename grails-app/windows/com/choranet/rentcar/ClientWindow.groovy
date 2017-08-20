
package com.choranet.rentcar


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory


/**
 * Client Window Object
 **/
class ClientWindow extends SuperWindow {
    /**
     * Service pour la gestion de l'objet Client
     **/
    def clientService
    /**
     * Logger de la class ClientWindow
     **/
    private Log logger = LogFactory.getLog(ClientWindow.class)
	
    /**
     * liste de nationalite
     **/	
    def nationalites	
    /**
     * nationalite  selectionn�
     **/
    def nationaliteSelected
	
    /**
     * liste de locations
     **/
    //def locations	
    /**
     * locations  selectionn�
     **/
    //def locationsSelected
	
    /**
     * Constructeur
     **/
    public ClientWindow () {
        super(Client.class, 14, ["cin","nom","prenom","gsm","nationalite"])
    }  

    protected SuperService getService() {
        return this.clientService
    }
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
		
        nationalites = Nationalite.list()		
        if(nationalites.size() > 0)
        nationaliteSelected = nationalites.get(0)
        else
        nationaliteSelected = null
		
        //locations = Location.list()
        //locationsSelected = null// = new ArrayList()
		
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
			
        if(del) {
            nationalites = Nationalite.list()
        }	
        if(nationalites.size() > 0)
        nationaliteSelected = nationalites.get(0)
        else
        nationaliteSelected = null
		
        //        if(del) {
        //            locations = Location.list()
        //        }
        //        this.getFellow("lstlocations").clearSelection()
        //        locationsSelected = null// = new ArrayList()
		
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
				
        objet.nationalite = nationaliteSelected
        if(nationalites.size() > 0) {
            def bindernationalite = new AnnotateDataBinder(this.getFellow("conationalites"))
            nationaliteSelected = nationalites.get(0)
            bindernationalite.loadAll()
        }
        else
        nationaliteSelected = null
		
        //        client.locations = locationsSelected
        //        this.getFellow("lstlocations").clearSelection()
        //        locationsSelected = null// = new ArrayList()
		
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
				
        def bindernationalite = new AnnotateDataBinder(this.getFellow("conationalites"))
        nationaliteSelected = nationalites.find{ it.id == Client.findById(objet.id).nationalite.id }
        bindernationalite.loadAll()
		
        //        def binderlocations = new AnnotateDataBinder(this.getFellow("lstlocations"))
        //        locationsSelected = clientSelected.locations
        //        binderlocations.loadAll()
		
    }
    
    def select() {
        super.select()
        if (objet.cne != null  && objet.cne != "") {
            this.getFellow("rcne").checked = true
            this.getFellow("fieldCin").setConstraint("");
            this.getFellow("fieldCne").setConstraint("no empty: Veillez indiquez une valeur");
            this.getFellow("fieldCarteSejour").setConstraint("");
            this.getFellow("fieldNumeroPassport").setConstraint("");
                                                
            this.getFellow("fieldCin").value = "";
            this.getFellow("fieldCarteSejour").value = "";
            this.getFellow("fieldNumeroPassport").value = "";
                                                
            this.getFellow("rw1").visible = false;
            this.getFellow("rw2").visible = true;
            this.getFellow("rw3").visible = false;
            this.getFellow("rw4").visible = false;
        } else if((objet.carteSejour != null && objet.carteSejour != "") || (objet.numeroPassport != null && objet.numeroPassport != "")) {
            this.getFellow("rcs").checked = true
            this.getFellow("fieldCin").setConstraint("");
            this.getFellow("fieldCne").setConstraint("");
            this.getFellow("fieldCarteSejour").setConstraint("");
            this.getFellow("fieldNumeroPassport").setConstraint("");
                                                
            this.getFellow("fieldCne").value = "";
            this.getFellow("fieldCin").value = "";
                                                
            this.getFellow("rw1").visible = false;
            this.getFellow("rw2").visible = false;
            this.getFellow("rw3").visible = true;
            this.getFellow("rw4").visible = true;
        } else {
            this.getFellow("rcin").checked = true
            
            this.getFellow("fieldCin").setConstraint("no empty: Veillez indiquez une valeur");
            this.getFellow("fieldCne").setConstraint("");
            this.getFellow("fieldCarteSejour").setConstraint("");
            this.getFellow("fieldNumeroPassport").setConstraint("");
                                                
            this.getFellow("fieldCne").value = "";
            this.getFellow("fieldCarteSejour").value = "";
            this.getFellow("fieldNumeroPassport").value = "";

            this.getFellow("rw1").visible = true;
            this.getFellow("rw2").visible = false;
            this.getFellow("rw3").visible = false;
            this.getFellow("rw4").visible = false;
        }
    }
}

