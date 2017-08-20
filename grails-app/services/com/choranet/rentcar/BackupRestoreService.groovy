package com.choranet.rentcar

import grails.converters.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.choranet.util.BackupDirectoryFilter;
import org.hibernate.ReplicationMode;
import com.choranet.commun.*

class BackupRestoreService {

    def sessionFactory
    
    static transactional = true

    def cleanDataBase() throws Exception {
        try {
            def session = sessionFactory.getCurrentSession()
            session.clear()  
            Location.executeUpdate("delete Location l")
            Reservation.executeUpdate("delete Reservation r")
            Entretien.executeUpdate("delete Entretien en")
            EntretienPeriodique.executeUpdate("delete EntretienPeriodique enp")
            FraisCirculation.executeUpdate("delete FraisCirculation fc")
            FraisCirculationPeriodique.executeUpdate("delete FraisCirculationPeriodique fcp")
            Client.executeUpdate("delete Client c")
            Nationalite.executeUpdate("delete Nationalite n")
            TypeEntretien.executeUpdate("delete TypeEntretien te")
            TypeEntretienPeriodique.executeUpdate("delete TypeEntretienPeriodique tep")
            TypeFraisCirculation.executeUpdate("delete TypeFraisCirculation tfc")
            TypeFraisCirculationPeriodique.executeUpdate("delete TypeFraisCirculationPeriodique tfcp")
            Voiture.executeUpdate("delete Voiture v")
            Energie.executeUpdate("delete Energie e")
            Model.executeUpdate("delete Model mo")
            Marque.executeUpdate("delete Marque mq")
            DroitUtilisateur.executeUpdate("delete DroitUtilisateur d") 
            session.createSQLQuery("delete from utilisateur_authorities").executeUpdate();
            Utilisateur.executeUpdate("delete Utilisateur uts")
            GroupeUtilisateur.executeUpdate("delete GroupeUtilisateur gu")
            ChoraClientInfo.executeUpdate("delete ChoraClientInfo ci")
            ChoraBarrage.executeUpdate("delete ChoraBarrage chbar")
            session.flush()
            session.clear()
        } catch(Exception ex) {
            throw new Exception("Impossible de supprimer la base de donnée courante", ex)
        }
    }
    
    def bulkInsert(objets) throws Exception {

        def session = sessionFactory.getCurrentSession()

        try {
            def batch = []
            objets.each { objet ->
                batch.add(objet)
                if(batch.size() > 1000) {
                    objet.getClass().withTransaction {
                        for(o in batch) {
                            session.replicate(o, ReplicationMode.OVERWRITE);
                        }
                    }
                    batch.clear()
                    session.flush()
                    session.clear()        
                }
            }
            if(batch.size() > 0) {
                batch[0].getClass().withTransaction {
                    for(o in batch) {
                        session.replicate(o, ReplicationMode.OVERWRITE);
                    }
                }
                batch.clear()
                session.flush()
                session.clear()  
            }
        } catch(Exception ex) {
            throw new Exception("Impossible d'insérer dans la base de données les objets : " + objets, ex)
        }
    }
    
    def xmlStringToObjets(xmls) throws Exception {
        def objets = null
        try {
            objets = XML.parse(xmls)
        } catch (Exception ex) {
            throw new Exception("Problème lors de la convesion de la chaine " + xmls + " en objets", ex)
        }
        return objets
    }
    
    def xmlFileToObjets(File file) throws Exception {
        def objets = null
        try {
            FileInputStream fis = new FileInputStream(file)
            objets = XML.parse(fis, "UTF-8")
        } catch (Exception ex) {
            throw new Exception("Le fichier " + file.getAbsolutePath() + " est corrompu", ex)
        }
        return objets
    }
    
    def sauveguarderBdD() throws Exception {
        def info = ChoraClientInfo.get(1)
        if(info == null) {
            throw new Exception("Aucune informations sur le client trouvé au niveau de la Base")
        } else if(info.repertoirBackup == null || info.repertoirBackup.equals("")) {
            throw new Exception("Vous devez indiquer un répértoir pour la sauveguarde de vos données")
        } else {
            File f;
            try {
                f = new File(info.repertoirBackup)
            } catch(Exception e) {
                throw new Exception("Le chemin indiqué n'est pas une répértoir valide : " + info.repertoirBackup, e)
            }
            if(!f.exists()) {
                throw new Exception("Le chemin indiqué n'est pas une répértoir valide : " + info.repertoirBackup)
            }
            if(!f.isDirectory()) {
                throw new Exception("Le chemin indiqué n'est pas un répértoire : " + info.repertoirBackup)
            }
            if(!f.canWrite()) {
                throw new Exception("Vous n'avez pas le droit d'écriture sur le répértoire : " + info.repertoirBackup)
            }
         
        }
        String FS = System.getProperty("file.separator");
        Date d = new Date()
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmm");
        String fullPathToBackupDirectory = info.repertoirBackup + FS + "BACKUP" + dateFormat.format(d);
        File directoryBackup = new File(fullPathToBackupDirectory);
        directoryBackup.mkdirs();
        
        def choraBarrage = ChoraBarrage.list()
        def cci = ChoraClientInfo.list()
        def du = DroitUtilisateur.list()
        def gu = GroupeUtilisateur.list()
        def utilisateurs = Utilisateur.list()
        def marques = Marque.list()
        def models = Model.list()
        def energies = Energie.list()
        def voitures = Voiture.list()
        def tfcp = TypeFraisCirculationPeriodique.list()
        def tfc = TypeFraisCirculation.list()
        def tep = TypeEntretienPeriodique.list()
        def te = TypeEntretien.list()
        def nationalites = Nationalite.list()
        def clients = Client.list()
        def fcp = FraisCirculationPeriodique.list()
        def fc = FraisCirculation.list()
        def ep = EntretienPeriodique.list()
        def e = Entretien.list()
        def reservations = Reservation.list()
        def locations = Location.list()
        File backupFile = new File(fullPathToBackupDirectory + FS + "chorainformatiquebackup.backup" );
        backupFile.createNewFile();
        println backupFile.getAbsolutePath()
        backupFile.withObjectOutputStream { out ->
            out << choraBarrage
            out << cci
            out << gu
            out << utilisateurs
            out << du
            out << marques
            out << models
            out << energies
            out << voitures
            out << tfcp
            out << tfc
            out << tep
            out << te
            out << nationalites
            out << clients
            out << fcp
            out << fc
            out << ep
            out << e
            out << reservations
            out << locations
        }
    }
    
    def getListeDesSauveguardes() {
        def info = ChoraClientInfo.get(1)
        def result = []
        if(info != null && info.repertoirBackup != null && !info.repertoirBackup.equals("")) {
            File f = new File(info.repertoirBackup);
            if(f.exists() && f.isDirectory() && f.canRead()) {
                result = f.list(new BackupDirectoryFilter());
            } 
        } 
        return result;
    }
    
    def restaurerDb(backupname) throws Exception {
        def info = ChoraClientInfo.get(1)
        if(info != null && info.repertoirBackup != null && !info.repertoirBackup.equals("")) {
            File f = new File(info.repertoirBackup);
            if(f.exists() && f.isDirectory() && f.canRead()) {
                String FS = System.getProperty("file.separator");
                String fullPathToBackupDirectory = info.repertoirBackup + FS + backupname;
                File directoryBackup = new File(fullPathToBackupDirectory);
                if(directoryBackup.exists() && directoryBackup.isDirectory() && directoryBackup.canRead()) {
                    File backupFile = new File(fullPathToBackupDirectory + FS + "chorainformatiquebackup.backup" );
                    if(!(backupFile.exists() && backupFile.canRead())) {
                        throw new Exception("Le fichier chorainformatiquebackup.backup n'existe pas ou n'est pas en lecture" )
                    } 
                    //Vérification du fichier de sauveguarde
                    //TODO
                    //                    int compteur = 0;
                    //                    try {
                    //                        backupFile.withObjectInputStream(getClass().classLoader) { instream ->
                    //                            instream.eachObject { 
                    //                                compteur++;
                    //                            }
                    //                        }
                    //                        if(compteur != 20) {
                    //                            throw new Exception("Le fichier chorainformatiquebackup.backup est invalide");
                    //                        }
                    //                        println "nombre delement trouve : " + compteur
                    //                    } catch(Exception ex) {
                    //                        throw ex;
                    //                    }
                    
                    //Apres verification supression de la base actuelle
                    cleanDataBase()
                    //lecture des fichiers
                    try {
                        backupFile.withObjectInputStream(getClass().classLoader) { instream ->
                            instream.eachObject { 
                                bulkInsert(it)
                            }
                        }
                    } catch(Exception ex) {
                        ex.printStackTrace()
                        throw ex
                    }
                    
                } else {
                    throw new Exception("Le répertoire de backup : " + fullPathToBackupDirectory + " n'existe pas ou n'est pas en lecture") 
                }
            }else {
                throw new Exception("Le répertoire de backup : " + info.repertoirBackup + " n'existe pas ou n'est pas en lecture") 
            }
        } else {
            throw new Exception("Vous devez indiquer un répértoir pour la sauveguarde de vos données")
        }
    }
}
