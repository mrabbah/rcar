

package com.choranet.rentcar


import java.io.IOException
import org.zkoss.image.AImage
import org.zkoss.image.Image
import com.choranet.zk.Utilitaire
import java.io.Serializable;
/**
 * Voiture Domain Object
 */
class Voiture implements Serializable {
    
    private static final long serialVersionUID = 1L;
    	
    String immatriculation

//    String immatriculation1
//    String immatriculation2
//    String immatriculation3
    	
    Date dateMiseEnCirculation	
    	
    Integer kilometrage	
    	
    Double prixLocation

    boolean vendu
    
    boolean sousTraite
    	
    byte[] photoData	
    
    Image trans_photo

    Date dateDecision
    
    byte[] decisionData

    Image trans_decision

    static transients = ['trans_photo', 'trans_decision']
    
    static belongsTo = [model : Model , energie : Energie]
    
    static mapping = { 
        immatriculation index : "immatriculation"
//        immatriculation1 index : "immatriculation1"
//        immatriculation2 index : "immatriculation2"
//        immatriculation3 index : "immatriculation3"
        dateMiseEnCirculation index : "date_mise_en_circulation"
        kilometrage index : "kilometrage"
        prixLocation index : "prix_location"
        dateDecision index : "date_decision"
        vendu index : "vendu"
        model lazy : false
        energie lazy : false
        batchSize: 14 
    }
    static constraints = {
    
        immatriculation(blank : false, nullable : false, unique : true)

//        immatriculation1(blank : false, nullable : false, unique : false)
//        immatriculation2(blank : false, nullable : false, unique : false)
//        immatriculation3(blank : false, nullable : false, unique : false)
    
        dateMiseEnCirculation(nullable : false)
    
        kilometrage(min : 0, nullable : false)
    
        prixLocation(min : 0d, nullable : false, scale : 2)

        vendu()
        
        sousTraite()
    
        photoData()

        dateDecision(nullable : false)

        decisionData()
    
    }
	
    String toString() {
        return model.marque.toString() + " " + model.toString() + " " + immatriculation
        //immatriculation1+immatriculation2+immatriculation3
    }
    
    public void setPhotoData(byte[] bd) {
        photoData = bd
        if(photoData != null) {
            //immatriculation = immatriculation1+immatriculation2+immatriculation3
            trans_photo = new AImage(immatriculation, bd);
        }
    }

    public void setTrans_photo(Image img) {        
        if(img == null) {            
            img = Utilitaire.getDumpImage(getClass())
        }
        trans_photo = img
        photoData = trans_photo.getByteData()     
    }

    public void setDecisionData(byte[] bd) {
        decisionData = bd
        if(decisionData != null) {
            //immatriculation = immatriculation1+immatriculation2+immatriculation3
            trans_decision = new AImage(immatriculation, bd);
        }
    }

    public void setTrans_decision(Image img) {
        if(img == null) {
            img = Utilitaire.getDumpImage(getClass())
        }
        trans_decision = img
        decisionData = trans_decision.getByteData()
    }
}

