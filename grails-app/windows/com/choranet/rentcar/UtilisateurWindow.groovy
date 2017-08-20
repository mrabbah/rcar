package com.choranet.rentcar

import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory


/**
 * Utilisateur Window Object
 **/
class UtilisateurWindow extends SuperWindow{
    
    def utilisateurService
    /**
     * Logger de la class UtilisateurWindow
     **/
    private Log logger = LogFactory.getLog(UtilisateurWindow.class)
	
    /**
     * liste de groupeUtilisateur
     **/
    def groupes
    /**
     * groupeUtilisateur  selectionn�
     **/
    def groupesSelected
	
    /**
     * Constructeur
     **/
    public UtilisateurWindow () {
        super(Utilisateur.class, 14, ["authorities", "username", "userRealName", "enabled", "email"])
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
        this.getFellow("westPanel").open = visible        
    }
    /**
     * Fonction qui g�re l'initialisation des listes d'associations au niveau du constructeur
     **/
    def initialiserAssociation() {
		
        groupes = GroupeUtilisateur.list()
        groupesSelected = null
		
    }
    /**
     * Fonction qui permet de r�-initaliser l'association au niveau de l'interface
     * @param del si c'est une r�initionalisation apr�s une suppression ou non
     **/
    def reinitialiserAssociation(del) {
			
        if(del) {
            groupes = GroupeUtilisateur.list()
        }
        this.getFellow("lstgroupes").clearSelection()
        groupesSelected = null
		
    }
    /**
     * Fonction qui copie la valeur de l'association � l'�l�ment courant
     **/
    def actualiserValeurAssociation() {
				
        objet.authorities = groupesSelected
        this.getFellow("lstgroupes").clearSelection()
        groupesSelected = null
		
    }
    /**
     * Fonction qui fait la liaison entre l'association l'�l�ment selectionn� et la liste dans le crud
     **/
    def afficherValeurAssociation() {
				
        def bindergroupes = new AnnotateDataBinder(this.getFellow("lstgroupes"))
        groupesSelected = objetSelected.authorities
        bindergroupes.loadAll()
		
    }
    
    protected SuperService getService() {
        return utilisateurService
    }
}

