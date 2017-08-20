// Place your Spring DSL code here
beans = { 
    utilisateurWindow(com.choranet.rentcar.UtilisateurWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    droitUtilisateurWindow(com.choranet.rentcar.DroitUtilisateurWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    loginWindow(com.choranet.rentcar.LoginWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    mainWindow(com.choranet.rentcar.MainWindow, ref("authenticateService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
        
    clientWindow(com.choranet.rentcar.ClientWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    modelWindow(com.choranet.rentcar.ModelWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    entretienPeriodiqueWindow(com.choranet.rentcar.EntretienPeriodiqueWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    entretienWindow(com.choranet.rentcar.EntretienWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    fraisCirculationPeriodiqueWindow(com.choranet.rentcar.FraisCirculationPeriodiqueWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    typeEntretienWindow(com.choranet.rentcar.TypeEntretienWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    voitureWindow(com.choranet.rentcar.VoitureWindow, ref("voitureService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    locationWindow(com.choranet.rentcar.LocationWindow, ref("paternCompteurService"), ref("factureService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    energieWindow(com.choranet.rentcar.EnergieWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    typeEntretienPeriodiqueWindow(com.choranet.rentcar.TypeEntretienPeriodiqueWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    typeFraisCirculationWindow(com.choranet.rentcar.TypeFraisCirculationWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    nationaliteWindow(com.choranet.rentcar.NationaliteWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    marqueWindow(com.choranet.rentcar.MarqueWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    typeFraisCirculationPeriodiqueWindow(com.choranet.rentcar.TypeFraisCirculationPeriodiqueWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    fraisCirculationWindow(com.choranet.rentcar.FraisCirculationWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    paternCompteurWindow(com.choranet.rentcar.PaternCompteurWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    tdbWindow(com.choranet.rentcar.TdbWindow, ref("voitureService"), ref("locationService"), ref("fraisCirculationService"), ref("fraisCirculationPeriodiqueService"), ref("entretienService"), ref("entretienPeriodiqueService"),ref("reservationService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    locationClientWindow(com.choranet.rentcar.LocationClientWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }

    reservationWindow(com.choranet.rentcar.ReservationWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    reservationClientWindow(com.choranet.rentcar.ReservationClientWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    choraClientInfoWindow(com.choranet.rentcar.ChoraClientInfoWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    backupRestoreWindow(com.choranet.rentcar.BackupRestoreWindow, ref("backupRestoreService")) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    cleWindow(com.choranet.commun.CleWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
    
    clePopUpWindow(com.choranet.commun.ClePopUpWindow) { bean ->
        bean.scope = "prototype"
        bean.autowire = "byName"
    }
}