
package com.choranet.rentcar;

import com.choranet.util.DateUtil;
import java.util.Date;

/**
 * PaternCompteurService Service pour la gestion des opérations
 * transactionnelles pour l'objet PaternCompteur
 */
class PaternCompteurService extends SuperService {

    static transactional = true

    def list() throws Exception {
        return super.list(PaternCompteur.class)
    }
        
    def getProchainNumFactureEtIncrementer() {
        def pc = PaternCompteur.findByType("FACTURE");
        def result = constituerPattern(pc);
        incrementerPattern(pc);
        return result;
    }
    
    def getProchainNumFacture() {
        def pc = PaternCompteur.findByType("FACTURE");
        return constituerPattern(pc);
    }
    
        
    private String constituerPattern(PaternCompteur pc) throws Exception {
        Date now = new Date()
        def result = pc.prefixe
        String ns = pc.numeroSuivant
        def diff = pc.remplissage - ns.length()
        while(diff > 0) {
            ns = "0" + ns;
            diff--;
        }
        result += ns; 
        result += pc.suffixe
        //Remplacer les variables
        result = result.replaceAll("\\{annee\\}", DateUtil.getDateTime("yyyy", now));
        result = result.replaceAll("\\{minannee\\}", DateUtil.getDateTime("yy", now));
        result = result.replaceAll("\\{mois\\}", DateUtil.getDateTime("MM", now));
        result = result.replaceAll("\\{jour\\}", DateUtil.getDateTime("dd", now));
        return result
    }
    
    private void incrementerPattern(PaternCompteur pc) throws Exception {
        pc.numeroSuivant += pc.pas;
        update(pc);
    }
}