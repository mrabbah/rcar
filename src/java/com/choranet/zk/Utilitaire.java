/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.choranet.zk;

import java.io.File;
import java.io.InputStream;
import org.zkoss.image.AImage;
import org.zkoss.image.Image;

/**
 *
 * @author RABBAH
 */
public class Utilitaire {

    private static String pathToDeclarationImage = "com/choranet/rentcar/images/declaration.png";
            
    private static String pathToLogoImage = "com/choranet/rentcar/images/logo.png";
    
    private static String pathToDumpImage = "com/choranet/rentcar/images/voiture_hayon.png";
    
    private static String pathToDumpDocImage = "com/choranet/rentcar/images/neant.png";
    
    private static String pathToPvImage = "com/choranet/rentcar/images/pv.jpg";
    
    private static String pathToBlankImage = "com/choranet/rentcar/images/blank.png";
    
    private static String pathToAdresseImage = "com/choranet/rentcar/images/adresse.png";
    private static String pathToAppartenantImage = "com/choranet/rentcar/images/appartenant.png";
    private static String pathToAuImage = "com/choranet/rentcar/images/au.png";
    private static String pathToCodepostalImage = "com/choranet/rentcar/images/codepostal.png";
    private static String pathToDateImage = "com/choranet/rentcar/images/date.png";
    private static String pathToDatelivraisonImage = "com/choranet/rentcar/images/datelivraison.png";
    private static String pathToDaterestitutionImage = "com/choranet/rentcar/images/daterestitution.png";
    private static String pathToDeclareImage = "com/choranet/rentcar/images/declare.png";
    private static String pathToDuImage = "com/choranet/rentcar/images/du.png";
    private static String pathToGsmImage = "com/choranet/rentcar/images/gsm.png";
    private static String pathToHeurelivraisonImage = "com/choranet/rentcar/images/heurelivraison.png";
    private static String pathToHeurerestitutionImage = "com/choranet/rentcar/images/heurerestitution.png";
    private static String pathToLieuImage = "com/choranet/rentcar/images/lieu.png";
    private static String pathToMailImage = "com/choranet/rentcar/images/mail.png";
    private static String pathToNomImage = "com/choranet/rentcar/images/nom.png";
    private static String pathToNum1Image = "com/choranet/rentcar/images/Num1.png";
    private static String pathToNum2Image = "com/choranet/rentcar/images/Num2.png";
    private static String pathToPermisImage = "com/choranet/rentcar/images/permis.png";
    private static String pathToPrenomImage = "com/choranet/rentcar/images/prenom.png";
    private static String pathToSignatureImage = "com/choranet/rentcar/images/signature.png";
    private static String pathToSignature2Image = "com/choranet/rentcar/images/signature2.png";
    private static String pathToSoussigneImage = "com/choranet/rentcar/images/soussigne.png";
    private static String pathToVilleImage = "com/choranet/rentcar/images/ville.png";
    private static String pathToHeaderDeclarationImage = "com/choranet/rentcar/images/Header_Declaration_Location.png";
    
    private static String pathToFcpSubReport = "com/choranet/rentcar/subreports/Rapport_des_frais_fcp.jasper";
    private static String pathToFcSubReport = "com/choranet/rentcar/subreports/Rapport_des_frais_fc.jasper";
    private static String pathToEpSubReport = "com/choranet/rentcar/subreports/Rapport_des_frais_ep.jasper";
    private static String pathToESubReport = "com/choranet/rentcar/subreports/Rapport_des_frais_e.jasper";
    
    private static String pathToVoitureFcpSubReport = "com/choranet/rentcar/subreports/Rapport_voiture_des_frais_fcp.jasper";
    private static String pathToVoitureFcSubReport = "com/choranet/rentcar/subreports/Rapport_voiture_des_frais_fc.jasper";
    private static String pathToVoitureEpSubReport = "com/choranet/rentcar/subreports/Rapport_voiture_des_frais_ep.jasper";
    private static String pathToVoitureESubReport = "com/choranet/rentcar/subreports/Rapport_voiture_des_frais_e.jasper";
    private static String pathToVoitureRevenusReport = "com/choranet/rentcar/subreports/Rapport_des_revenus.jasper";
    

    public static File getVoitureRevenusReport(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        File is;
        try {
            is = new File(cl.getResource(pathToVoitureRevenusReport).getPath());
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir le rapport", e);
        }
        return is;
    }
    
    public static File getVoitureESubReport(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        File is;
        try {
            is = new File(cl.getResource(pathToVoitureESubReport).getPath());
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir le rapport", e);
        }
        return is;
    }
    
    public static File getVoitureEpSubReport(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        File is;
        try {
            is = new File(cl.getResource(pathToVoitureEpSubReport).getPath());
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir le rapport", e);
        }
        return is;
    }
    
    public static File getVoitureFcSubReport(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        File is;
        try {
            is = new File(cl.getResource(pathToVoitureFcSubReport).getPath());
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir le rapport", e);
        }
        return is;
    }
    
    public static File getVoitureFcpSubReport(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        File is;
        try {
            is = new File(cl.getResource(pathToVoitureFcpSubReport).getPath());
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir le rapport", e);
        }
        return is;
    }
    
    public static File getESubReport(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        File is;
        try {
            is = new File(cl.getResource(pathToESubReport).getPath());
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir le rapport", e);
        }
        return is;
    }
    
    public static File getEpSubReport(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        File is;
        try {
            is = new File(cl.getResource(pathToEpSubReport).getPath());
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir le rapport", e);
        }
        return is;
    }
    
    public static File getFcSubReport(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        File is;
        try {
            is = new File(cl.getResource(pathToFcSubReport).getPath());
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir le rapport", e);
        }
        return is;
    }
    
    public static File getFcpSubReport(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        File is;
        try {
            is = new File(cl.getResource(pathToFcpSubReport).getPath());
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir le rapport", e);
        }
        return is;
    }
    
    public static InputStream getDeclarationImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToDeclarationImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getHeaderDeclarationImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToHeaderDeclarationImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getAdresseImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToAdresseImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getAppartenantImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToAppartenantImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getAuImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToAuImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getCodepostalImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToCodepostalImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getDateImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToDateImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getDatelivraisonImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToDatelivraisonImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getDaterestitutionImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToDaterestitutionImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getDeclareImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToDeclareImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getDuImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToDuImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getGsmImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToGsmImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getHeurelivraisonImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToHeurelivraisonImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getHeurerestitutionImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToHeurerestitutionImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getLieuImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToLieuImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getMailImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToMailImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getNomImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToNomImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getNum1Image(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToNum1Image);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getNum2Image(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToNum2Image);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getPermisImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToPermisImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getPrenomImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToPrenomImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getSignatureImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToSignatureImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getSignature2Image(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToSignature2Image);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getSoussigneImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToSoussigneImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    public static InputStream getVilleImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToVilleImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image", e);
        }
        return is;
    }
    
    
    public static InputStream getBlankImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToBlankImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image blank", e);
        }
        return is;
    }
    
    public static InputStream getPvImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToPvImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image pv", e);
        }
        return is;
    }
    
    public static Image getDumpImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToDumpImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image dump", e);
        }
        Image img = new AImage("dump", is);
        return img;
    }
    
    public static Image getDumpDocImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToDumpDocImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image dump", e);
        }
        Image img = new AImage("dump", is);
        return img;
    }
    
    public static Image getLogoImage(Class clazz) throws Exception {
        ClassLoader cl = clazz.getClassLoader();
        InputStream is;
        try {
            is = cl.getResourceAsStream(pathToLogoImage);
        } catch (Exception e) {
            throw new Exception("Impossible d obtenir l image logo", e);
        }
        Image img = new AImage("logo", is);
        return img;
    }

}
