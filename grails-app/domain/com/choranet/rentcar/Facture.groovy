
package com.choranet.rentcar


import java.io.Serializable;
import java.util.List

/**
 * Facture Domain Object 
 */
class Facture implements Serializable {	
    private static final long serialVersionUID = 1L;
    	
    String numeroFacture		
    Date date
    String client
    
    Double trans_totalht
    Double trans_totaltva
    Double trans_totalttc
    
    static transients = ['trans_totalht', 'trans_totaltva', 'trans_totalttc']
    
    static belongsTo = [location : Location]
    //static hasMany = [paiements : Paiement]
    
    static mapping = { 
        numeroFacture index : "facture_numero_facture"
        date index : "facture_date"
        location lazy : false
        //paiements lazy : false
        client fetch : 'join'
        batchSize: 10 
    }
    static constraints = {
    
        numeroFacture(blank : false, nullable : false, unique : true)
        date(nullable : false)
        //paiements(nullable : true)
        client(nullable : false)
    
    }
	
    String toString() {
        return numeroFacture
    }
    
//    public Double getTrans_totalht() {
//        trans_totalht = 0d;
//        if(faitgenerateur.equals("Encaissement")) {
//            def codes = []
//            def ttcbc = 0d
//            def htbc = 0d
//            for(p in paiements) {
//                if(!p.isAttached()) {
//                    p.attach()
//                }
//                if(!codes.contains(p.bonCommande.numBC)) {
//                    codes.add(p.bonCommande.numBC)
//                    ttcbc += p.bonCommande.trans_totalttc
//                    htbc += p.bonCommande.trans_totalht
//                }
//            }
//            if(ttcbc != 0) {
//                trans_totalht = (getTrans_totalttc()/ttcbc) * htbc 
//            }
//            
//        } else {
//            if(bonCommandes) {
//                for(bc in bonCommandes) {
//                    if(!bc.isAttached()) {
//                        bc.attach()
//                    }
//                    trans_totaltva += bc.trans_totalht
//                }
//            }
//        }
//            
//        return trans_totalht;
//    }
//    public Double getTrans_totaltva() {
//        trans_totaltva = getTrans_totalttc() - getTrans_totalht();
//        return trans_totaltva;
//    }
//    public Double getTrans_totalttc() {
//        trans_totalttc = 0d;
//        if(faitgenerateur.equals("Encaissement")) {
//            if(paiements) {
//                for(paiement in paiements) {
//                    if(!paiement.isAttached()) {
//                        paiement.attach()
//                    }
//                    trans_totalttc += paiement.montantPaye
//                }
//            }
//        } else { // fait generateur facturation
//            if(bonCommandes) {
//                for(bc in bonCommandes) {
//                    if(!bc.isAttached()) {
//                        bc.attach()
//                    }
//                    trans_totalttc += bc.trans_totalttc
//                }
//            }
//        }
//        return trans_totalttc;
//    }
    
}

