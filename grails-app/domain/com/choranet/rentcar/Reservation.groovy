

package com.choranet.rentcar


import java.io.IOException
import org.zkoss.image.AImage
import org.zkoss.image.Image
import com.choranet.zk.Utilitaire
import java.io.Serializable;

/**
 * Reservation Domain Object
 */
class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    Double prixParJour
    	
    Date dateDepart	
    
    Date dateRestitution
    	
    String lieuLivraison	
    	
    String lieuReprise	
    	
    Double montantPaye	
    	
    String modePaiment	
    	
    String observations	
    	
    
    static belongsTo = [premierConducteur : Client , deuxiemeConducteur : Client , voiture : Voiture , agentLoueurResponsable : Utilisateur]
    
    static mapping = { 
        prixParJour index : "reser_prix_par_jour"
        dateDepart index : "reser_date_depart"
        dateRestitution index : "reser_date_restitution"
        lieuLivraison index : "reser_lieu_livraison"
        lieuReprise index : "reser_lieu_reprise"
        montantPaye index : "reser_montant_paye"
        modePaiment index : "reser_mode_paiment"
        observations index : "reser_observations"
        premierConducteur lazy : false
        deuxiemeConducteur lazy : false
        voiture lazy : false
        agentLoueurResponsable lazy : false
        batchSize: 14 
    }
    static constraints = {        

        prixParJour(min : 0d, nullable : false, scale : 2)
    
        dateDepart(nullable : false)
        
        dateRestitution(nullable : false)
    
        lieuLivraison()
    
        lieuReprise()
    
        montantPaye(min : 0d, nullable : false, scale : 2)
    
        modePaiment()
    
        observations()
        
        deuxiemeConducteur(nullable : true)
    
    }
	
    String toString() {
        return "réservation numero : " + id
    }
}

