

package com.choranet.rentcar


import java.io.IOException
import org.zkoss.image.AImage
import org.zkoss.image.Image
import com.choranet.zk.Utilitaire
import java.io.Serializable;

/**
 * Location Domain Object
 */
class Location implements Serializable {
    
    private static final long serialVersionUID = 1L;
    	
    Double prixAchatParJour	
    
    Double prixParJour	
    	
    Date dateDepart	
    
    Date dateRestitution	
    
    Integer kilometrageDepart	
    	
    Integer kilometrageRetour	
    	
    String lieuLivraison	
    	
    String lieuReprise	
    	
    Double montantPaye	
    	
    String modePaiment	
    	
    String observations	
    	
    byte[] contratNumeriser	
    
    Image trans_contrat
    	
    byte[] declarationPrealableNumeriser
    
    Image trans_declaration
    
    Boolean estArchive
    
    static transients = ['trans_contrat', 'trans_declaration']
    
    static belongsTo = [premierConducteur : Client , deuxiemeConducteur : Client , voiture : Voiture , agentLoueurResponsable : Utilisateur]
    
    static mapping = { 
        prixParJour index : "prix_par_jour"
        dateDepart index : "date_depart"
        dateRestitution index : "date_restitution"
        kilometrageDepart index : "kilometrage_depart"
        kilometrageRetour index : "kilometrage_retour"
        lieuLivraison index : "lieu_livraison"
        lieuReprise index : "lieu_reprise"
        montantPaye index : "montant_paye"
        modePaiment index : "mode_paiment"
        observations index : "observations"
        premierConducteur lazy : false
        deuxiemeConducteur lazy : false
        voiture lazy : false
        agentLoueurResponsable lazy : false
        batchSize: 14 
    }
    static constraints = {
    
        prixAchatParJour(min : 0d, nullable : true, scale : 2)

        prixParJour(min : 0d, nullable : false, scale : 2)
    
        dateDepart(nullable : false)
        
        dateRestitution(nullable : false)
    
        kilometrageDepart(min : 0, nullable : false)
    
        kilometrageRetour(nullable : true, min : 0)
    
        lieuLivraison()
    
        lieuReprise()
    
        montantPaye(min : 0d, nullable : false, scale : 2)
    
        modePaiment()
    
        observations()
    
        contratNumeriser()
    
        declarationPrealableNumeriser()
        
        deuxiemeConducteur(nullable : true)
        
        estArchive(nullable : false)
    
    }
	
    String toString() {
        return "location numero : " + id
    }
    
    public void setContratNumeriser(byte[] bd) {
        contratNumeriser = bd
        if(contratNumeriser != null) {
                trans_contrat = new AImage("dummpdocc", bd);
        }
    }

    public void setTrans_contrat(Image img) {        
        if(img == null) {            
            img = Utilitaire.getDumpDocImage(getClass())
        }
        trans_contrat = img
        contratNumeriser = trans_contrat.getByteData()     
    }
    
    public void setDeclarationPrealableNumeriser(byte[] bd) {
        declarationPrealableNumeriser = bd
        if(declarationPrealableNumeriser != null) {
                trans_declaration = new AImage("dummpdocd", bd);
        }
    }

    public void setTrans_declaration(Image img) {        
        if(img == null) {            
            img = Utilitaire.getDumpDocImage(getClass())
        }
        trans_declaration = img
        declarationPrealableNumeriser = trans_declaration.getByteData()     
    }
}

