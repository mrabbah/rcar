
package com.choranet.rentcar


import org.zkoss.zul.*
import org.zkoss.zkplus.databind.*
import org.apache.commons.logging.Log 
import org.apache.commons.logging.LogFactory
import org.zkoss.zksandbox.Category
import org.springframework.web.context.request.RequestContextHolder
import com.choranet.util.DateUtil
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import com.choranet.zk.*;


/**
 * Tdb Window Object
 **/
class TdbWindow extends Window {
   
    def voitureService
    def locationService
    def fraisCirculationService
    def fraisCirculationPeriodiqueService
    def entretienService
    def entretienPeriodiqueService
    def jasperService
    def reservationService
    
    def listeAlertsFcp
    
    def existAlertsFcp = false
    
    def listeAlertsEp
    
    def existAlertsEp  = false
    
    def listeDecisions
    
    def listeReservations
    
    def existAlertsReservations = false
    
    def existAlertsDecision  = false
    
    def listeVG
    
    def existVG = false
    
    def etatparkmdel
    
    def depensesgains
    
    def voitures
    
    def voiture
    
    def dateDebutDepenses
    
    def dateFinDepenses
    
    def dateDebutLocations
    
    def dateFinLocations
    /**
     * Logger de la class ClientWindow
     **/
    private Log logger = LogFactory.getLog(TdbWindow.class)
	
    /**
     * Constructeur
     **/
    public TdbWindow (voitureService, locationService, fraisCirculationService,
        fraisCirculationPeriodiqueService, entretienService, entretienPeriodiqueService, reservationService) {
        
        this.voitureService = voitureService
        this.locationService = locationService
        this.fraisCirculationService = fraisCirculationService
        this.fraisCirculationPeriodiqueService = fraisCirculationPeriodiqueService
        this.entretienService = entretienService
        this.entretienPeriodiqueService = entretienPeriodiqueService
        this.reservationService = reservationService
        
        listeAlertsFcp = fraisCirculationPeriodiqueService.getAlertsFraisCirculationPeriodiques()
        listeAlertsEp = entretienPeriodiqueService.getAlertsEntretienPeriodique()
        listeDecisions = voitureService.getAlertsDecisions()
        listeReservations = reservationService.getAlertsReservation()
        listeVG = voitureService.getListVoitureGarree()
        
        if(listeReservations != null && listeReservations.size() > 0) {
            existAlertsReservations = true
        }
        if(listeAlertsFcp != null && listeAlertsFcp.size() > 0) {
            existAlertsFcp = true
        }
        if(listeAlertsEp != null && listeAlertsEp.size() > 0) {
            existAlertsEp = true
        }
        if(listeDecisions != null && listeDecisions.size() > 0) {
            existAlertsDecision = true
        }
        if(listeVG != null && listeVG.size() > 0) {
            existVG = true
        }
        
        etatparkmdel = new SimplePieModel();
        etatparkmdel.setValue("Garée", voitureService.getNbVoitureGarree());
        etatparkmdel.setValue("Louée", voitureService.getNbVoitureLouee());
        
        
        Date d1 = new Date()
        String mois1 = DateUtil.recupererMoisDate(d1);
        Date[] dateDebutFin1 = DateUtil.recupereDateDebutDateFinMois(d1);
        def benefices1 = locationService.getSommeBenifices(dateDebutFin1[0], dateDebutFin1[1]);
        def charges1 = fraisCirculationService.getFraisCirculation(dateDebutFin1[0], dateDebutFin1[1])  + fraisCirculationPeriodiqueService.getFraisCirculationPeriodique(dateDebutFin1[0], dateDebutFin1[1]) + entretienService.getFraisEntretien(dateDebutFin1[0], dateDebutFin1[1]) + entretienPeriodiqueService.getFraisEntretienPeriodique(dateDebutFin1[0], dateDebutFin1[1]);
        
        Date d2 = DateUtil.recupererDateMoisPrecedent(d1);
        String mois2 = DateUtil.recupererMoisDate(d2);
        Date[] dateDebutFin2 = DateUtil.recupereDateDebutDateFinMois(d2);
        def benefices2 = locationService.getSommeBenifices(dateDebutFin2[0], dateDebutFin2[1]);
        def charges2 = fraisCirculationService.getFraisCirculation(dateDebutFin2[0], dateDebutFin2[1]) + fraisCirculationPeriodiqueService.getFraisCirculationPeriodique(dateDebutFin2[0], dateDebutFin2[1]) + entretienService.getFraisEntretien(dateDebutFin2[0], dateDebutFin2[1]) + entretienPeriodiqueService.getFraisEntretienPeriodique(dateDebutFin2[0], dateDebutFin2[1]);
            
        Date d3 = DateUtil.recupererDateMoisPrecedent(d2);
        String mois3 = DateUtil.recupererMoisDate(d3);
        Date[] dateDebutFin3 = DateUtil.recupereDateDebutDateFinMois(d3);
        def benefices3 = locationService.getSommeBenifices(dateDebutFin3[0], dateDebutFin3[1]);
        def charges3 = fraisCirculationService.getFraisCirculation(dateDebutFin3[0], dateDebutFin3[1]) + fraisCirculationPeriodiqueService.getFraisCirculationPeriodique(dateDebutFin3[0], dateDebutFin3[1]) + entretienService.getFraisEntretien(dateDebutFin3[0], dateDebutFin3[1]) + entretienPeriodiqueService.getFraisEntretienPeriodique(dateDebutFin3[0], dateDebutFin3[1]);  
            
        Date d4 = DateUtil.recupererDateMoisPrecedent(d3);
        String mois4 = DateUtil.recupererMoisDate(d4);
        Date[] dateDebutFin4 = DateUtil.recupereDateDebutDateFinMois(d4);
        def benefices4 = locationService.getSommeBenifices(dateDebutFin4[0], dateDebutFin4[1]);
        def charges4 = fraisCirculationService.getFraisCirculation(dateDebutFin4[0], dateDebutFin4[1]) + fraisCirculationPeriodiqueService.getFraisCirculationPeriodique(dateDebutFin4[0], dateDebutFin4[1]) + entretienService.getFraisEntretien(dateDebutFin4[0], dateDebutFin4[1]) + entretienPeriodiqueService.getFraisEntretienPeriodique(dateDebutFin4[0], dateDebutFin4[1]);
        
        depensesgains = new SimpleCategoryModel();
        depensesgains.setValue("Dépenses", mois4, charges4);
        depensesgains.setValue("Dépenses", mois3, charges3);
        depensesgains.setValue("Dépenses", mois2, charges2);
        depensesgains.setValue("Dépenses", mois1, charges1);
        depensesgains.setValue("Gains", mois4, benefices4);
        depensesgains.setValue("Gains", mois3, benefices3);
        depensesgains.setValue("Gains", mois2, benefices2);
        depensesgains.setValue("Gains", mois1, benefices1);
        
        voitures = Voiture.list()
        if(voitures != null && voitures.size() > 0) {
            voiture = voitures.get(0)
        }
    }  
    
    def genererRapportDepenses() {
        if(dateDebutDepenses == null || dateFinDepenses == null) {
            Messagebox.show("Veuillez indiquez l'interval du rapport","Erreurs",Messagebox.OK, Messagebox.ERROR)
        } else {
            String nomsociete = ChoraClientInfo.get(1).nomsociete
            String titrerapport = "Rapport des frais"
            def fraisCirculationsPeriodiques = fraisCirculationPeriodiqueService.getDepensesBetween(dateDebutDepenses, dateFinDepenses)
            def fraisCirculations = fraisCirculationService.getDepensesBetween(dateDebutDepenses, dateFinDepenses)
            def entretienPeriodique = entretienPeriodiqueService.getDepensesBetween(dateDebutDepenses, dateFinDepenses)
            def entretien = entretienService.getDepensesBetween(dateDebutDepenses, dateFinDepenses)
            def FcpSubReport = Utilitaire.getFcpSubReport(getClass())
            def FcSubReport = Utilitaire.getFcSubReport(getClass())
            def EpSubReport = Utilitaire.getEpSubReport(getClass())
            def ESubReport = Utilitaire.getESubReport(getClass())
            def reportDef = new JasperReportDef(name:'Rapport_des_frais.jasper',
                fileFormat:JasperExportFormat.PDF_FORMAT,
                reportData : voitures,
                parameters : ['nomsociete':nomsociete, 
                              'titrerapport':titrerapport,
                              'fraisCirculationsPeriodiques' : fraisCirculationsPeriodiques,
                              'fraisCirculations' : fraisCirculations,
                              'entretienPeriodique' : entretienPeriodique,
                              'entretien' : entretien,
                              'FcpSubReport' : FcpSubReport,
                              'FcSubReport' : FcSubReport,
                              'EpSubReport' : EpSubReport,
                              'ESubReport' : ESubReport
                ]
            )
            def bit = jasperService.generateReport(reportDef).toByteArray()
            def nom_fichier = "rapport_frais_entre_"+DateUtil.getDateTime("dd_MM_yyyy",dateDebutDepenses)+"_et_"+DateUtil.getDateTime("dd_MM_yyyy",dateFinDepenses)+".pdf"
            Filedownload.save(bit, "application/file", nom_fichier);
        }
    }
    
    def genererRapportLocations() {
        if(dateDebutLocations == null || dateFinLocations == null) {
            Messagebox.show("Veuillez indiquez l'interval du rapport","Erreurs",Messagebox.OK, Messagebox.ERROR)
        } else {
            def locations = locationService.getLocationsBetween(dateDebutLocations, dateFinLocations)
            String nomsociete = ChoraClientInfo.get(1).nomsociete
            String titrerapport = "Rapport des locations"
            def reportDef = new JasperReportDef(name:'Rapport_des_locations.jasper',
                fileFormat:JasperExportFormat.PDF_FORMAT,
                reportData : locations,
                parameters : ['nomsociete':nomsociete, 'titrerapport':titrerapport]
            )
            def bit = jasperService.generateReport(reportDef).toByteArray()
            def nom_fichier = "rapport_locations_entre_"+DateUtil.getDateTime("dd_MM_yyyy",dateDebutLocations)+"_et_"+DateUtil.getDateTime("dd_MM_yyyy",dateFinLocations)+".pdf"
            Filedownload.save(bit, "application/file",
                nom_fichier);
        }
    }
    
    def genererRapportBenifices() {
        
        def fraisCirculationsPeriodiques = fraisCirculationPeriodiqueService.getDepensesVoiture(voiture)
        def fraisCirculations = fraisCirculationService.getDepensesVoiture(voiture)
        def entretienPeriodique = entretienPeriodiqueService.getDepensesVoiture(voiture)
        def entretien = entretienService.getDepensesVoiture(voiture)
        def locations = locationService.getLocationsVoiture(voiture)
            
        def VoitureFcpSubReport = Utilitaire.getVoitureFcpSubReport(getClass())
        def VoitureFcSubReport = Utilitaire.getVoitureFcSubReport(getClass())
        def VoitureEpSubReport = Utilitaire.getVoitureEpSubReport(getClass())
        def VoitureESubReport = Utilitaire.getVoitureESubReport(getClass())
        def VoitureRevenusReport = Utilitaire.getVoitureRevenusReport(getClass())
        String nomsociete = ChoraClientInfo.get(1).nomsociete
        String titrerapport = "Rapport des frais et revenus"
        
        def reportDef = new JasperReportDef(name:'Rapport_des_frais_et_revenus.jasper',
            fileFormat:JasperExportFormat.PDF_FORMAT,
            reportData : [voiture],
            parameters : ['nomsociete':nomsociete, 'titrerapport':titrerapport,
                          'VoitureFcpSubReport' : VoitureFcpSubReport,
                          'VoitureFcSubReport' : VoitureFcSubReport,
                          'VoitureEpSubReport' : VoitureEpSubReport,
                          'VoitureESubReport' : VoitureESubReport,
                          'VoitureRevenusReport' : VoitureRevenusReport,
                          'fraisCirculationsPeriodiques' : fraisCirculationsPeriodiques,
                          'fraisCirculations' : fraisCirculations,
                          'entretienPeriodique' : entretienPeriodique,
                          'entretien' : entretien,
                          'locations' : locations
            ]
        )
        def bit = jasperService.generateReport(reportDef).toByteArray()
        def nom_fichier = "Frais_et_revenus_voiture.pdf"
        Filedownload.save(bit, "application/file", nom_fichier);
    }
    
}

