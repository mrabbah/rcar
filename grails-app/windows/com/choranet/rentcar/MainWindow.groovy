package com.choranet.rentcar

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory
import org.zkoss.zksandbox.Category
import org.springframework.web.context.request.RequestContextHolder


/**
 *
 * @author RABBAH
 */
class MainWindow extends Window {
    
    /**
     * Logger de la class ArticleWindow
     **/
    private Log logger = LogFactory.getLog(MainWindow.class)
    
    def authenticateService
    
    def session
    
    def nomsociete = ChoraClientInfo.get(1).nomsociete
    def site = ChoraClientInfo.get(1).site
    def trans_logo  = ChoraClientInfo.get(1).trans_logo
    
    //Page inclue par défaut pour l'utilisateur selon son role
    def currentinclude
    
    /**
     * liste categories pour le menu principal
     **/
    def categories =  []
    
    public MainWindow(authenticateService) {                
        if(!this.authenticateService) {
            this.authenticateService = authenticateService
            session = RequestContextHolder.currentRequestAttributes().getSession()
        
            if(!session.utilisateur) {
                def utilisateur = authenticateService.userDomain()
                session.utilisateur = utilisateur
                session.isroot = false
                for(GroupeUtilisateur gu : utilisateur.authorities) {
                    if(gu.authority == 'ROLE_ROOT') {
                        session.isroot = true;
                        break;
                    }
                }
            }
        }
    }
   
    def getCategories() {
        if(this.categories.size() == 0) {
            if(session) {
                def currentUser = session.utilisateur 
                def gp_tdb = false;
                def gp_gestionnaires_park = false;
                def gp_gestionnaires_location = false;
                def gp_admin = false;
                def gp_root = false;
                if(currentUser) {
                    for(GroupeUtilisateur gu : currentUser.authorities) {
                        if(gu.authority == 'ROLE_ROOT') {
                            gp_root = true;
                            
                        } else if(gu.authority == 'ROLE_TDB') {
                            gp_tdb = true;
                            
                        }  else if (gu.authority == 'ROLE_GESTION_PARK') {
                            gp_gestionnaires_park = true;
                            
                        }else if (gu.authority == 'ROLE_GESTION_LOCATION') {
                            gp_gestionnaires_location = true;
                            
                        } else if (gu.authority == 'ROLE_ADMIN') {
                            gp_admin = true;
                        }
                    } 
                    if(gp_root) {
                        this.categories = [new Category("cg_tdb", "/images/skin/ChartLogo600.png", "Tableau de bord", "/zul/tdb/tdb.zul"),
                            new Category("cg_location", "/images/skin/location-voiture.png", "Location", "/zul/location/location.zul"),
                            new Category("cg_reservation", "/images/skin/reservationbis.png", "Reservation", "/zul/location/reservation.zul"),
                            new Category("cg_client", "/images/skin/client.png", "Clients", "/zul/location/client.zul"),
                            new Category("cg_voiture", "/images/skin/katkat.png", "Voitures", "/zul/park/voiture.zul"),
                            new Category("cg_entretien", "/images/skin/voiture.png", "Entretien", "/zul/park/entretien.zul"),
                            new Category("cg_entretienperiodique", "/images/skin/vidange.png", "Entretien périodique", "/zul/park/entretienPeriodique.zul"),
                            new Category("cg_fraiscirculation", "/images/skin/carburant.png", "Frais circulation", "/zul/park/fraisCirculation.zul"),
                            new Category("cg_fraiscirculationperiodique", "/images/skin/NicOx.png", "Frais circulation périodiques", "/zul/park/fraisCirculationPeriodique.zul"),
                            new Category("cg_gressource", "/images/skin/icon_users.png", "Utilisateurs", "/zul/root/utilisateur.zul"),
                            new Category("cg_energie", "/images/skin/pg.png", "Energie", "/zul/admin/energie.zul"),
                            new Category("cg_marque", "/images/skin/plan_voiture_bebe.png", "Marques", "/zul/admin/marque.zul"),
                            new Category("cg_model", "/images/skin/marque_1.png", "Modèles", "/zul/admin/model.zul"),
                            new Category("cg_nationalite", "/images/skin/glob.png", "Nationalité", "/zul/admin/nationalite.zul"),
                            new Category("cg_typeentretien", "/images/skin/mecanique-carrosserie.png", "Type Entretien", "/zul/admin/typeEntretien.zul"),
                            new Category("cg_typeentretienper", "/images/skin/calendar.jpg", "Type Entretien Périodique", "/zul/admin/typeEntretienPeriodique.zul"),
                            new Category("cg_typefrais", "/images/skin/bidon-pour-carburant-vert-79106ec.png", "Type frais circulation", "/zul/admin/typeFraisCirculation.zul"),
                            new Category("cg_typefraisper", "/images/skin/TimeIsMoney.png", "Types circulation périodiques", "/zul/admin/typeFraisCirculationPeriodique.zul"),
                            new Category("cg_compteur_pattern", "/images/skin/compteur.jpg", "Compteurs", "/zul/admin/paternCompteur.zul"),
                            new Category("cg_clientinfo", "/images/skin/EnterpriseAndIntegration-48x48.png", "Information client", "/zul/admin/clientinfo.zul"),
                            new Category("cg_backup", "/images/skin/backup.png", "Sauvegurder Restaurer", "/zul/admin/backup.zul")]
                        //,
                        //    new Category("cg_droituser", "/images/skin/icon_users.png", "Rôles", "/zul/root/droitUtilisateur.zul")
                        
                    } else {
                        if(gp_tdb) {
                            this.categories += [new Category("cg_tdb", "/images/skin/ChartLogo600.png", "Tableau de bord", "/zul/tdb/tdb.zul")]
                        }
                        if (gp_gestionnaires_location) {
                            this.categories += [
                                new Category("cg_location", "/images/skin/location-voiture.png", "Location", "/zul/location/location.zul"),
                                new Category("cg_reservation", "/images/skin/reservationbis.png", "Reservation", "/zul/location/reservation.zul"),
                                new Category("cg_client", "/images/skin/client.png", "Clients", "/zul/location/client.zul")]
                        }
                        if (gp_gestionnaires_park) {
                            this.categories += [new Category("cg_voiture", "/images/skin/katkat.png", "Voitures", "/zul/park/voiture.zul"),
                            new Category("cg_entretien", "/images/skin/voiture.png", "Entretien", "/zul/park/entretien.zul"),
                            new Category("cg_entretienperiodique", "/images/skin/vidange.png", "Entretien périodique", "/zul/park/entretienPeriodique.zul"),
                            new Category("cg_fraiscirculation", "/images/skin/carburant.png", "Frais circulation", "/zul/park/fraisCirculation.zul"),
                            new Category("cg_fraiscirculationperiodique", "/images/skin/NicOx.png", "Frais circulation périodiques", "/zul/park/fraisCirculationPeriodique.zul")]
                        }
                        if(gp_admin) {
                            this.categories += [new Category("cg_gressource", "/images/skin/icon_users.png", "Utilisateurs", "/zul/root/utilisateur.zul"),
                            new Category("cg_energie", "/images/skin/pg.png", "Energie", "/zul/admin/energie.zul"),
                            new Category("cg_marque", "/images/skin/plan_voiture_bebe.png", "Marques", "/zul/admin/marque.zul"),
                            new Category("cg_model", "/images/skin/marque_1.png", "Modèles", "/zul/admin/model.zul"),
                            new Category("cg_nationalite", "/images/skin/glob.png", "Nationalité", "/zul/admin/nationalite.zul"),
                            new Category("cg_typeentretien", "/images/skin/mecanique-carrosserie.png", "Type Entretien", "/zul/admin/typeEntretien.zul"),
                            new Category("cg_typeentretienper", "/images/skin/calendar.jpg", "Type Entretien Périodique", "/zul/admin/typeEntretienPeriodique.zul"),
                            new Category("cg_typefrais", "/images/skin/bidon-pour-carburant-vert-79106ec.png", "Type frais circulation", "/zul/admin/typeFraisCirculation.zul"),
                            new Category("cg_typefraisper", "/images/skin/TimeIsMoney.png", "Types circulation périodiques", "/zul/admin/typeFraisCirculationPeriodique.zul"),
                            new Category("cg_clientinfo", "/images/skin/EnterpriseAndIntegration-48x48.png", "Information client", "/zul/admin/clientinfo.zul"),
                            new Category("cg_backup", "/images/skin/backup.png", "Sauvegurder Restaurer", "/zul/admin/backup.zul")]
                        }
                    }
                    
                }
            }
            //this.categories += [new Category("cg_logout", "/images/skin/48px-Vista-logout.png", "Se déconnecter", "/logout.zul")]
        }
        return this.categories
    }
    
    def getCurrentinclude() {
        if(session) {
            def currentUser = session.utilisateur 
            def gp_tdb = false;
            def gp_gestionnaires_park = false;
            def gp_gestionnaires_location = false;
            def gp_admin = false;
            def gp_root = false;
            if(currentUser) {
                for(GroupeUtilisateur gu : currentUser.authorities) {
                    if(gu.authority == 'ROLE_ROOT') {
                        gp_root = true;
                            
                    } else if(gu.authority == 'ROLE_TDB') {
                        gp_tdb = true;
                            
                    } else if (gu.authority == 'ROLE_GESTION_LOCATION') {
                        gp_gestionnaires_location = true;
                            
                    } else if (gu.authority == 'ROLE_GESTION_PARK') {
                        gp_gestionnaires_park = true;
                            
                    } else if (gu.authority == 'ROLE_ADMIN') {
                        gp_admin = true;
                    }
                } 
                if(gp_root) {
                    this.currentinclude = "/zul/tdb/tdb.zul"
                } else if (gp_tdb) {
                    this.currentinclude = "/zul/tdb/tdb.zul"
                } else if (gp_gestionnaires_location) {
                    this.currentinclude = "/zul/location/location.zul"
                } else if (gp_gestionnaires_park) {
                    this.currentinclude = "/zul/park/voiture.zul"
                }  else if (gp_admin) {
                    this.currentinclude = "/zul/root/utilisateur.zul"
                }
            }
        }
        return this.currentinclude
    }
    
//    def afficherApropos() {
//        def winApropos = Executions.createComponents("/zul/aide/apropos.zul", null, null)
//        winApropos.doModal();
//    }
}

