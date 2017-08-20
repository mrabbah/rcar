class BootStrap {

    def authenticateService
    def voitureService
    
    def init = { servletContext ->
        environments {
            development {
                def clientInfo = new com.choranet.rentcar.ChoraClientInfo(nomsociete:'CHORA INFORMATIQUE',raisonSocial : null, telephone : null, fax : null,email:null,patente:null,rc:null,idF:null,cnss:null,site:null,repertoirBackup:".")
                clientInfo.setTrans_logo(null)
                clientInfo.save()
                def roleRoot = new com.choranet.rentcar.GroupeUtilisateur(authority:'ROLE_ROOT', description:'Super Utilisateur').save()
                def roleAdmin = new com.choranet.rentcar.GroupeUtilisateur(authority:'ROLE_ADMIN', description:'Administrateur').save() 
                def roleGestionPark = new com.choranet.rentcar.GroupeUtilisateur(authority:'ROLE_GESTION_PARK', description:'Gestion Park Automobile').save() 
                def roleGestionLocation = new com.choranet.rentcar.GroupeUtilisateur(authority:'ROLE_GESTION_LOCATION', description:'Gestion location des voitures').save() 
                def roleTdb = new com.choranet.rentcar.GroupeUtilisateur(authority:'ROLE_TDB', description:'Visualisation du tableau de bord').save()
        
                def userRoot = new com.choranet.rentcar.Utilisateur(username:'root',
                    userRealName:'root',
                    enabled: true,
                    emailShow: true,
                    email: 'admin@choranet.com',
                    passwd: authenticateService.encodePassword('root')).save()
                
                def userAdmin = new com.choranet.rentcar.Utilisateur(username:'admin',
                    userRealName:'admin',
                    enabled: true,
                    emailShow: true,
                    email: 'admin@choranet.com',
                    passwd: authenticateService.encodePassword('admin')).save()
        
                def userGestionPark = new com.choranet.rentcar.Utilisateur(username:'park',
                    userRealName:'park',
                    enabled: true,
                    emailShow: true,
                    email: 'park@choranet.com',
                    passwd: authenticateService.encodePassword('park')).save()
                
                def userGestionLocation = new com.choranet.rentcar.Utilisateur(username:'location',
                    userRealName:'location',
                    enabled: true,
                    emailShow: true,
                    email: 'location@choranet.com',
                    passwd: authenticateService.encodePassword('location')).save()
               
                def userTdb = new com.choranet.rentcar.Utilisateur(username:'tdb',
                    userRealName:'tdb',
                    enabled: true,
                    emailShow: true,
                    email: 'tdb@choranet.com',
                    passwd: authenticateService.encodePassword('tdb')).save()
         
                def secureRoot = new com.choranet.rentcar.DroitUtilisateur(url: '/zul/root/**',
                    configAttribute:'ROLE_ROOT').save()
                def secureAdmin = new com.choranet.rentcar.DroitUtilisateur(url: '/zul/admin/**',
                    configAttribute:'ROLE_ADMIN').save()
                def secureGestionPark = new com.choranet.rentcar.DroitUtilisateur(url: '/zul/park/**',
                    configAttribute:'ROLE_GESTION_PARK').save()
                def secureGestionLocation = new com.choranet.rentcar.DroitUtilisateur(url: '/zul/location/**',
                    configAttribute:'ROLE_GESTION_LOCATION').save()                
                def secureTdb = new com.choranet.rentcar.DroitUtilisateur(url: '/zul/tdb/**',
                    configAttribute:'ROLE_TDB').save()
                
                userRoot.addToAuthorities(roleRoot) 
                userRoot.addToAuthorities(roleAdmin)
                userRoot.addToAuthorities(roleGestionPark)
                userRoot.addToAuthorities(roleGestionLocation)
                userRoot.addToAuthorities(roleTdb)
                userAdmin.addToAuthorities(roleAdmin)  
                userGestionPark.addToAuthorities(roleGestionPark)
                userGestionLocation.addToAuthorities(roleGestionLocation)
                userTdb.addToAuthorities(roleTdb)
                
                
                new com.choranet.rentcar.PaternCompteur(libelle : 'Pattern facture', prefixe : 'FACT{annee}{mois}{jour}', 
                    suffixe : '', pas : 1, remplissage : 3, numeroSuivant : 1, type : 'FACTURE').save(); 
                
                //Energie
                def ene = new com.choranet.rentcar.Energie(libelle : 'Essence').save()
                def end = new com.choranet.rentcar.Energie(libelle : 'Diesel').save()

                //Marques et Models de voiture
                def marqueAlfaromeo = new com.choranet.rentcar.Marque(libelle : 'Alfa romeo').save()
                new com.choranet.rentcar.Model(libelle : '145', marque : marqueAlfaromeo).save()
                new com.choranet.rentcar.Model(libelle : '146', marque : marqueAlfaromeo).save()
                new com.choranet.rentcar.Model(libelle : '147', marque : marqueAlfaromeo).save()
                new com.choranet.rentcar.Model(libelle : '156', marque : marqueAlfaromeo).save()
                new com.choranet.rentcar.Model(libelle : '159', marque : marqueAlfaromeo).save()
                new com.choranet.rentcar.Model(libelle : '164', marque : marqueAlfaromeo).save()
                new com.choranet.rentcar.Model(libelle : '166', marque : marqueAlfaromeo).save()

                def marqueAudi = new com.choranet.rentcar.Marque(libelle : 'Audi').save()
                new com.choranet.rentcar.Model(libelle : 'A1', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'A2', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'A3', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'A4', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'A5', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'A6', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'A8', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'Q5', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'Q7', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : '164', marque : marqueAudi).save()

                def marqueBmw = new com.choranet.rentcar.Marque(libelle : 'BMW').save()
                new com.choranet.rentcar.Model(libelle : 'M', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'Serie 1', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'Serie 3', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'Serie 5', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'Serie 6', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'Serie 7', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'X3', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'X5', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'X6', marque : marqueBmw).save()

                def marqueChoverolet = new com.choranet.rentcar.Marque(libelle : 'Choverolet').save()
                new com.choranet.rentcar.Model(libelle : 'Optra', marque : marqueChoverolet).save()
                new com.choranet.rentcar.Model(libelle : 'Spark', marque : marqueChoverolet).save()
                    
                def marqueChery = new com.choranet.rentcar.Marque(libelle : 'Chery').save()
                new com.choranet.rentcar.Model(libelle : 'A113', marque : marqueChery).save()
                new com.choranet.rentcar.Model(libelle : 'A516', marque : marqueChery).save()
                new com.choranet.rentcar.Model(libelle : 'Eastar', marque : marqueChery).save()
                new com.choranet.rentcar.Model(libelle : 'QQ', marque : marqueChery).save()
                new com.choranet.rentcar.Model(libelle : 'Tiggo', marque : marqueChery).save()

                def marqueCitroen = new com.choranet.rentcar.Marque(libelle : 'Citroen').save()
                new com.choranet.rentcar.Model(libelle : 'Berlingo', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'C1', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'C2', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'C3', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'C4', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'C4 Picasso', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'C5', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'C6', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'Jumper', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'Jumpy', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'Saxo', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'Xsara', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'Xsara Picasso', marque : marqueCitroen).save()

                def marqueDacia = new com.choranet.rentcar.Marque(libelle : 'Dacia').save()
                new com.choranet.rentcar.Model(libelle : 'Duster', marque : marqueDacia).save()
                def modelLogan = new com.choranet.rentcar.Model(libelle : 'Logan', marque : marqueDacia).save()
                new com.choranet.rentcar.Model(libelle : 'Sandero', marque : marqueDacia).save()
                new com.choranet.rentcar.Model(libelle : 'Solenzo', marque : marqueDacia).save()

                def marqueDaihatsu = new com.choranet.rentcar.Marque(libelle : 'Daihatsu').save()
                new com.choranet.rentcar.Model(libelle : 'Hijet', marque : marqueDaihatsu).save()
                new com.choranet.rentcar.Model(libelle : 'Rocky', marque : marqueDaihatsu).save()
                new com.choranet.rentcar.Model(libelle : 'Terios', marque : marqueDaihatsu).save()
                new com.choranet.rentcar.Model(libelle : 'Sirion', marque : marqueDaihatsu).save()

                def marqueFiat = new com.choranet.rentcar.Marque(libelle : 'Fiat').save()
                new com.choranet.rentcar.Model(libelle : 'Brava', marque : marqueFiat).save()
                new com.choranet.rentcar.Model(libelle : 'Bravo', marque : marqueFiat).save()
                new com.choranet.rentcar.Model(libelle : 'Linea', marque : marqueFiat).save()
                new com.choranet.rentcar.Model(libelle : 'Palio', marque : marqueFiat).save()
                new com.choranet.rentcar.Model(libelle : 'Panda', marque : marqueFiat).save()
                def modelPunto = new com.choranet.rentcar.Model(libelle : 'Punto', marque : marqueFiat).save()
                new com.choranet.rentcar.Model(libelle : 'Siena', marque : marqueFiat).save()
                new com.choranet.rentcar.Model(libelle : 'Stilo', marque : marqueFiat).save()
                new com.choranet.rentcar.Model(libelle : 'Uno', marque : marqueFiat).save()

                def marqueFord = new com.choranet.rentcar.Marque(libelle : 'Ford').save()
                new com.choranet.rentcar.Model(libelle : 'Fiesta', marque : marqueFord).save()
                new com.choranet.rentcar.Model(libelle : 'Focus', marque : marqueFord).save()
                new com.choranet.rentcar.Model(libelle : 'Fusion', marque : marqueFord).save()
                new com.choranet.rentcar.Model(libelle : 'Tourneo', marque : marqueFord).save()

                def marqueHonda = new com.choranet.rentcar.Marque(libelle : 'Honda').save()
                new com.choranet.rentcar.Model(libelle : 'Accord', marque : marqueHonda).save()
                new com.choranet.rentcar.Model(libelle : 'City', marque : marqueHonda).save()
                new com.choranet.rentcar.Model(libelle : 'Civic', marque : marqueHonda).save()
                new com.choranet.rentcar.Model(libelle : 'Jazz', marque : marqueHonda).save()
                new com.choranet.rentcar.Model(libelle : 'Legend', marque : marqueHonda).save()
                new com.choranet.rentcar.Model(libelle : 'Odyssey', marque : marqueHonda).save()
                new com.choranet.rentcar.Model(libelle : 'Stream', marque : marqueHonda).save()
                new com.choranet.rentcar.Model(libelle : 'Vigor', marque : marqueHonda).save()

                def marqueHyundai = new com.choranet.rentcar.Marque(libelle : 'Hyundai').save()
                new com.choranet.rentcar.Model(libelle : 'Accent', marque : marqueHyundai).save()
                def modelAtos = new com.choranet.rentcar.Model(libelle : 'Atos', marque : marqueHyundai).save()
                new com.choranet.rentcar.Model(libelle : 'Atos Prime', marque : marqueHyundai).save()
                new com.choranet.rentcar.Model(libelle : 'Azera', marque : marqueHyundai).save()
                new com.choranet.rentcar.Model(libelle : 'Elantra', marque : marqueHyundai).save()
                new com.choranet.rentcar.Model(libelle : 'Galloper', marque : marqueHyundai).save()
                new com.choranet.rentcar.Model(libelle : 'i10', marque : marqueHyundai).save()
                new com.choranet.rentcar.Model(libelle : 'i30', marque : marqueHyundai).save()
                new com.choranet.rentcar.Model(libelle : 'Tucson', marque : marqueHyundai).save()

                def marqueKia = new com.choranet.rentcar.Marque(libelle : 'Kia').save()
                new com.choranet.rentcar.Model(libelle : 'Carens', marque : marqueKia).save()
                new com.choranet.rentcar.Model(libelle : "Cee'd", marque : marqueKia).save()
                new com.choranet.rentcar.Model(libelle : 'Cerato', marque : marqueKia).save()
                def modelPicanto = new com.choranet.rentcar.Model(libelle : 'Picanto', marque : marqueKia).save()
                new com.choranet.rentcar.Model(libelle : 'Rio', marque : marqueKia).save()
                new com.choranet.rentcar.Model(libelle : 'Sorento', marque : marqueKia).save()
                new com.choranet.rentcar.Model(libelle : 'Soul', marque : marqueKia).save()
                new com.choranet.rentcar.Model(libelle : 'Sportage', marque : marqueKia).save()

                def marqueMazda = new com.choranet.rentcar.Marque(libelle : 'Mazda').save()
                new com.choranet.rentcar.Model(libelle : '2', marque : marqueMazda).save()
                new com.choranet.rentcar.Model(libelle : '3', marque : marqueMazda).save()
                new com.choranet.rentcar.Model(libelle : '323', marque : marqueMazda).save()
                new com.choranet.rentcar.Model(libelle : '5', marque : marqueMazda).save()
                new com.choranet.rentcar.Model(libelle : '6', marque : marqueMazda).save()
                new com.choranet.rentcar.Model(libelle : '626', marque : marqueMazda).save()

                def marqueMercedesBenz = new com.choranet.rentcar.Marque(libelle : 'Mercedes-Benz').save()
                new com.choranet.rentcar.Model(libelle : '190', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '200', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '207', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '250', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '260', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '270', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '300', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '310', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '350', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe A', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe B', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe C', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe CL', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe CLK', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe CLS', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe D', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe E', marque : marqueMercedesBenz).save()

                def marqueMini = new com.choranet.rentcar.Marque(libelle : 'Mini').save()
                new com.choranet.rentcar.Model(libelle : 'Cabrio', marque : marqueMini).save()
                new com.choranet.rentcar.Model(libelle : 'Cooper', marque : marqueMini).save()

                def marqueMitsubishi = new com.choranet.rentcar.Marque(libelle : 'Mini').save()
                new com.choranet.rentcar.Model(libelle : 'L200', marque : marqueMitsubishi).save()
                new com.choranet.rentcar.Model(libelle : 'L300', marque : marqueMitsubishi).save()
                new com.choranet.rentcar.Model(libelle : 'Lancer', marque : marqueMitsubishi).save()

                def marqueNissan = new com.choranet.rentcar.Marque(libelle : 'Nissan').save()
                new com.choranet.rentcar.Model(libelle : 'Cedric', marque : marqueNissan).save()
                new com.choranet.rentcar.Model(libelle : 'Micra', marque : marqueNissan).save()
                new com.choranet.rentcar.Model(libelle : 'Prado', marque : marqueNissan).save()
                new com.choranet.rentcar.Model(libelle : 'Premira', marque : marqueNissan).save()
                new com.choranet.rentcar.Model(libelle : 'Qashqai', marque : marqueNissan).save()
                new com.choranet.rentcar.Model(libelle : 'Tirrano', marque : marqueNissan).save()
                new com.choranet.rentcar.Model(libelle : 'X-Trail', marque : marqueNissan).save()

                def marqueOpel = new com.choranet.rentcar.Marque(libelle : 'Opel').save()
                new com.choranet.rentcar.Model(libelle : 'Astra', marque : marqueOpel).save()
                new com.choranet.rentcar.Model(libelle : 'Combo', marque : marqueOpel).save()
                new com.choranet.rentcar.Model(libelle : 'Prado', marque : marqueOpel).save()
                new com.choranet.rentcar.Model(libelle : 'Corsa', marque : marqueOpel).save()
                new com.choranet.rentcar.Model(libelle : 'Insignia', marque : marqueOpel).save()
                new com.choranet.rentcar.Model(libelle : 'Tigra', marque : marqueOpel).save()

                def marquePeugeot = new com.choranet.rentcar.Marque(libelle : 'Peugeot').save()
                new com.choranet.rentcar.Model(libelle : '206', marque : marquePeugeot).save()
                def modelPg207 = new com.choranet.rentcar.Model(libelle : '207', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : '306', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : '307', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : '308', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : '406', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : '407', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : '607', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : '807', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : 'Patner', marque : marquePeugeot).save()
                    
                def marqueRenault = new com.choranet.rentcar.Marque(libelle : 'Renault').save()
                new com.choranet.rentcar.Model(libelle : 'Clio', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Espace', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Fluence', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Kangoo', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Koleos', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Logan', marque : marqueRenault).save()
                def modelMegane = new com.choranet.rentcar.Model(libelle : 'Megane', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Sandero', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Scenic', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Symbol', marque : marqueRenault).save()

                def marqueSeat = new com.choranet.rentcar.Marque(libelle : 'Seat').save()
                new com.choranet.rentcar.Model(libelle : 'Cordoba', marque : marqueSeat).save()
                new com.choranet.rentcar.Model(libelle : 'Ibiza', marque : marqueSeat).save()
                new com.choranet.rentcar.Model(libelle : 'Leon', marque : marqueSeat).save()

                def marqueSuziki = new com.choranet.rentcar.Marque(libelle : 'Suziki').save()
                new com.choranet.rentcar.Model(libelle : 'Alto', marque : marqueSuziki).save()
                new com.choranet.rentcar.Model(libelle : 'Baleno', marque : marqueSuziki).save()
                new com.choranet.rentcar.Model(libelle : 'Carry', marque : marqueSuziki).save()
                def modelSwift = new com.choranet.rentcar.Model(libelle : 'Swift', marque : marqueSuziki).save()

                def marqueToyota = new com.choranet.rentcar.Marque(libelle : 'Toyota').save()
                new com.choranet.rentcar.Model(libelle : 'Auris', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'Avensis', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'Corolla', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'Corolla verso', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'Prado', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'RAV4', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'Verso', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'Yaris', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'Yaris verso', marque : marqueToyota).save()

                def marqueVolkswagon = new com.choranet.rentcar.Marque(libelle : 'Volkswagon').save()
                new com.choranet.rentcar.Model(libelle : 'Bora', marque : marqueVolkswagon).save()
                new com.choranet.rentcar.Model(libelle : 'Caddy', marque : marqueVolkswagon).save()
                new com.choranet.rentcar.Model(libelle : 'Fox', marque : marqueVolkswagon).save()
                new com.choranet.rentcar.Model(libelle : 'Gol', marque : marqueVolkswagon).save()
                new com.choranet.rentcar.Model(libelle : 'Passat', marque : marqueVolkswagon).save()
                def modelPolo = new com.choranet.rentcar.Model(libelle : 'Polo', marque : marqueVolkswagon).save()
                new com.choranet.rentcar.Model(libelle : 'Tiguan', marque : marqueVolkswagon).save()
                new com.choranet.rentcar.Model(libelle : 'Touareg', marque : marqueVolkswagon).save()
                new com.choranet.rentcar.Model(libelle : 'Touran', marque : marqueVolkswagon).save()
                  
                def marqueVolvo = new com.choranet.rentcar.Marque(libelle : 'Volvo').save()

                // voitures
                def Swift = new com.choranet.rentcar.Voiture(
                    immatriculation:'4254-a-3256', dateMiseEnCirculation:new Date('01/09/2011'),
                    kilometrage:9000, prixLocation:250.0, vendu : false, sousTraite : false, photoData:null, dateDecision : new Date('01/09/2011'),
                    decisionData : null, model : modelSwift, energie : ene )
                Swift.setTrans_photo(null)
                Swift.setTrans_decision(null)
                Swift.save()
                
                def Swiftbis = new com.choranet.rentcar.Voiture(
                    immatriculation:'7777-a-3256', dateMiseEnCirculation:new Date('01/09/2011'),
                    kilometrage:15000, prixLocation:250.0, vendu : false, sousTraite : false, photoData:null, dateDecision : new Date('01/09/2011'),
                    decisionData : null, model : modelSwift, energie : ene )
                Swiftbis.setTrans_photo(null)
                Swiftbis.setTrans_decision(null)
                Swiftbis.save()

                def Logan = new com.choranet.rentcar.Voiture(
                    immatriculation:'1254-b-3256', dateMiseEnCirculation:new Date('01/09/2010'),
                    kilometrage:59000, prixLocation:250.0, vendu : false, sousTraite : false, photoData:null, dateDecision : new Date('05/09/2011'),
                    decisionData : null, model : modelLogan, energie : ene )
                Logan.setTrans_photo(null)
                Logan.setTrans_decision(null)
                Logan.save()
                
                def Loganbis = new com.choranet.rentcar.Voiture(
                    immatriculation:'5555-b-3256', dateMiseEnCirculation:new Date('01/09/2010'),
                    kilometrage:159000, prixLocation:400.0, vendu : false, sousTraite : false, photoData:null, dateDecision : new Date('05/09/2011'),
                    decisionData : null, model : modelLogan, energie : end )
                Loganbis.setTrans_photo(null)
                Loganbis.setTrans_decision(null)
                Loganbis.save()

                def Punto = new com.choranet.rentcar.Voiture(
                    immatriculation:'4054-C-3256', dateMiseEnCirculation:new Date('01/06/2010'),
                    kilometrage:15000, prixLocation:250.0, vendu : false, sousTraite : false, photoData:null, dateDecision : new Date('01/06/2011'),
                    decisionData : null, model : modelPunto, energie : ene )
                Punto.setTrans_photo(null)
                Punto.setTrans_decision(null)
                Punto.save()
                
                def Atos = new com.choranet.rentcar.Voiture(
                    immatriculation:'4250-b-3206', dateMiseEnCirculation:new Date('01/09/2009'),
                    kilometrage:25000, prixLocation:250.0, vendu : false, sousTraite : false, photoData:null, dateDecision : new Date() - 360,
                    decisionData : null, model : modelAtos, energie : ene )
                Atos.setTrans_photo(null)
                Atos.setTrans_decision(null)
                Atos.save()

                def Picanto = new com.choranet.rentcar.Voiture(
                    immatriculation:'1214-b-3956', dateMiseEnCirculation:new Date('01/10/2008'),
                    kilometrage:150000, prixLocation:250.0, vendu : false, sousTraite : false, photoData:null, dateDecision : new Date('01/10/2011'),
                    decisionData : null, model : modelPicanto, energie : ene )
                Picanto.setTrans_photo(null)
                Picanto.setTrans_decision(null)
                Picanto.save()
                
                def Picantobis = new com.choranet.rentcar.Voiture(
                    immatriculation:'8888-b-3956', dateMiseEnCirculation:new Date('01/10/2008'),
                    kilometrage:120000, prixLocation:250.0, vendu : false, sousTraite : false, photoData:null, dateDecision : new Date('01/10/2011'),
                    decisionData : null, model : modelPicanto, energie : ene )
                Picantobis.setTrans_photo(null)
                Picantobis.setTrans_decision(null)
                Picantobis.save()

                def Pg207 = new com.choranet.rentcar.Voiture(
                    immatriculation:'4234-b-3356', dateMiseEnCirculation:new Date() - 50 ,
                    kilometrage:160000, prixLocation:250.0, vendu : false, sousTraite : false, photoData:null, dateDecision : new Date() - 45,
                    decisionData : null, model : modelPg207, energie : ene )
                Pg207.setTrans_photo(null)
                Pg207.setTrans_decision(null)
                Pg207.save()

                def Megane = new com.choranet.rentcar.Voiture(
                    immatriculation:'1154-b-6556', dateMiseEnCirculation:new Date() - 380,
                    kilometrage:210000, prixLocation:250.0, vendu : false, sousTraite : false, photoData : null, dateDecision : new Date() - 375,
                    decisionData : null, model : modelMegane, energie : ene )
                Megane.setTrans_photo(null)
                Megane.setTrans_decision(null)
                Megane.save()

                // Entretiens Normaux non périodiques
                def maintenance = new com.choranet.rentcar.TypeEntretien(libelle : 'Maintenance').save()
                def reparation = new com.choranet.rentcar.TypeEntretien(libelle : 'Réparation').save()
                def lavage = new com.choranet.rentcar.TypeEntretien(libelle : 'Lavage').save()

                def entretien_1 = new com.choranet.rentcar.Entretien(
                    dateEntretien : new Date() - 5, details : 'changement des avant bras',
                    montant : 1200.0, typeEntretien : reparation, voiture : Megane ).save()

                def entretien_2 = new com.choranet.rentcar.Entretien(
                    dateEntretien : new Date(), details : 'changement de pneus',
                    montant : 2400.0, typeEntretien : maintenance, voiture : Swift).save()

                new com.choranet.rentcar.Entretien(
                    dateEntretien : new Date() - 110, details : 'changement de pneus',
                    montant : 200.0, typeEntretien : maintenance, voiture : Swift).save()
                new com.choranet.rentcar.Entretien(
                    dateEntretien : new Date() - 100, details : 'changement de pneus',
                    montant : 200.0, typeEntretien : maintenance, voiture : Swift).save()
                new com.choranet.rentcar.Entretien(
                    dateEntretien : new Date() - 10, details : 'changement de pneus',
                    montant : 200.0, typeEntretien : maintenance, voiture : Swift).save()
                new com.choranet.rentcar.Entretien(
                    dateEntretien : new Date() - 20, details : 'changement de pneus',
                    montant : 200.0, typeEntretien : maintenance, voiture : Swift).save()
                new com.choranet.rentcar.Entretien(
                    dateEntretien : new Date() - 30, details : 'changement de pneus',
                    montant : 200.0, typeEntretien : maintenance, voiture : Swift).save()
                new com.choranet.rentcar.Entretien(
                    dateEntretien : new Date() - 40, details : 'changement de pneus',
                    montant : 200.0, typeEntretien : maintenance, voiture : Swift).save()
                new com.choranet.rentcar.Entretien(
                    dateEntretien : new Date() - 50, details : 'changement de pneus',
                    montant : 200.0, typeEntretien : maintenance, voiture : Swift).save()
                new com.choranet.rentcar.Entretien(
                    dateEntretien : new Date() - 60, details : 'changement de pneus',
                    montant : 200.0, typeEntretien : maintenance, voiture : Swift).save()
                new com.choranet.rentcar.Entretien(
                    dateEntretien : new Date() - 70, details : 'changement de pneus',
                    montant : 200.0, typeEntretien : maintenance, voiture : Swift).save()
                new com.choranet.rentcar.Entretien(
                    dateEntretien : new Date() - 80, details : 'changement de pneus',
                    montant : 200.0, typeEntretien : maintenance, voiture : Swift).save()
                new com.choranet.rentcar.Entretien(
                    dateEntretien : new Date() - 90, details : 'changement de pneus',
                    montant : 200.0, typeEntretien : maintenance, voiture : Swift).save()
                new com.choranet.rentcar.Entretien(
                    dateEntretien : new Date() - 100, details : 'changement de pneus',
                    montant : 200.0, typeEntretien : maintenance, voiture : Swift).save()
                
                // Entretiens Périodiques
                def vidange = new com.choranet.rentcar.TypeEntretienPeriodique(libelle:'Vidange', periodicite:10000, prixEntretien:300).save()
                def filtration = new com.choranet.rentcar.TypeEntretienPeriodique(libelle:'Filtration', periodicite:20000, prixEntretien:200).save()
                new com.choranet.rentcar.TypeEntretienPeriodique(libelle : 'Climatisation', periodicite : 20000, prixEntretien:350).save()

//                voitureService.generateEntretiensPeriodiques(Swift)
//                voitureService.generateEntretiensPeriodiques(Swiftbis)
//                voitureService.generateEntretiensPeriodiques(Logan)
//                voitureService.generateEntretiensPeriodiques(Loganbis)
//                voitureService.generateEntretiensPeriodiques(Atos)
//                voitureService.generateEntretiensPeriodiques(Punto)
//                voitureService.generateEntretiensPeriodiques(Picanto)
//                voitureService.generateEntretiensPeriodiques(Picantobis)
//                voitureService.generateEntretiensPeriodiques(Pg207)
//                voitureService.generateEntretiensPeriodiques(Megane)
//                

                // Frais de circulation
                def carburant = new com.choranet.rentcar.TypeFraisCirculation(libelle : 'Carburant').save()
                def payage = new com.choranet.rentcar.TypeFraisCirculation(libelle : 'Payage').save()
                def recette = new com.choranet.rentcar.TypeFraisCirculation(libelle : 'Recette').save()
                def incident = new com.choranet.rentcar.TypeFraisCirculation(libelle : 'Incident').save()
                def depannage = new com.choranet.rentcar.TypeFraisCirculation(libelle : 'Divers').save()

                new com.choranet.rentcar.FraisCirculation(date : new Date() - 10, 
                    details : 'carburant 1er semaine du mois', montant : 200.0, voiture : Atos,
                    typeFraisCirculation : carburant).save()

                new com.choranet.rentcar.FraisCirculation(date : new Date('08/02/2011'), 
                    details : 'dépannage aîn sebâa/sidi-moumn', montant : 200.0, voiture : Atos,
                    typeFraisCirculation : depannage).save()
                new com.choranet.rentcar.FraisCirculation(date : new Date('08/03/2011'), 
                    details : 'dépannage aîn sebâa/sidi-moumn', montant : 200.0, voiture : Atos,
                    typeFraisCirculation : depannage).save()
                new com.choranet.rentcar.FraisCirculation(date : new Date('08/04/2011'), 
                    details : 'dépannage aîn sebâa/sidi-moumn', montant : 200.0, voiture : Atos,
                    typeFraisCirculation : depannage).save()
                new com.choranet.rentcar.FraisCirculation(date : new Date('08/05/2011'), 
                    details : 'dépannage aîn sebâa/sidi-moumn', montant : 200.0, voiture : Atos,
                    typeFraisCirculation : depannage).save()
                new com.choranet.rentcar.FraisCirculation(date : new Date('08/06/2011'), 
                    details : 'dépannage aîn sebâa/sidi-moumn', montant : 200.0, voiture : Atos,
                    typeFraisCirculation : depannage).save()
                new com.choranet.rentcar.FraisCirculation(date : new Date('08/07/2011'), 
                    details : 'dépannage aîn sebâa/sidi-moumn', montant : 200.0, voiture : Atos,
                    typeFraisCirculation : depannage).save()
                new com.choranet.rentcar.FraisCirculation(date : new Date('08/09/2011'), 
                    details : 'dépannage aîn sebâa/sidi-moumn', montant : 200.0, voiture : Atos,
                    typeFraisCirculation : depannage).save()
                new com.choranet.rentcar.FraisCirculation(date : new Date('08/08/2011'), 
                    details : 'dépannage aîn sebâa/sidi-moumn', montant : 200.0, voiture : Atos,
                    typeFraisCirculation : depannage).save()
                new com.choranet.rentcar.FraisCirculation(date : new Date('08/07/2006'), 
                    details : 'dépannage aîn sebâa/sidi-moumn', montant : 200.0, voiture : Atos,
                    typeFraisCirculation : depannage).save()
                new com.choranet.rentcar.FraisCirculation(date : new Date('08/07/2007'), 
                    details : 'dépannage aîn sebâa/sidi-moumn', montant : 200.0, voiture : Atos,
                    typeFraisCirculation : depannage).save()
                new com.choranet.rentcar.FraisCirculation(date : new Date('08/07/2008'), 
                    details : 'dépannage aîn sebâa/sidi-moumn', montant : 200.0, voiture : Atos,
                    typeFraisCirculation : depannage).save()
                new com.choranet.rentcar.FraisCirculation(date : new Date('08/07/2009'), 
                    details : 'dépannage aîn sebâa/sidi-moumn', montant : 200.0, voiture : Atos,
                    typeFraisCirculation : depannage).save()
                new com.choranet.rentcar.FraisCirculation(date : new Date('08/07/2010'), 
                    details : 'dépannage aîn sebâa/sidi-moumn', montant : 200.0, voiture : Atos,
                    typeFraisCirculation : depannage).save()

                // Frais de circulation périodique
                def assurance = new com.choranet.rentcar.TypeFraisCirculationPeriodique(libelle:'Assurance', periodicite:365, chargeDeBase:4000).save()
                def vignette = new com.choranet.rentcar.TypeFraisCirculationPeriodique(libelle : 'Vignette', periodicite:365, chargeDeBase:1500).save()
                def visite_technique = new com.choranet.rentcar.TypeFraisCirculationPeriodique(libelle : 'Visite technique', periodicite:182, chargeDeBase:400).save()

//                voitureService.generateFraisCirculationPeriodiques(Swift)
//                voitureService.generateFraisCirculationPeriodiques(Swiftbis)
//                voitureService.generateFraisCirculationPeriodiques(Logan)
//                voitureService.generateFraisCirculationPeriodiques(Loganbis)
//                voitureService.generateFraisCirculationPeriodiques(Atos)
//                voitureService.generateFraisCirculationPeriodiques(Punto)
//                voitureService.generateFraisCirculationPeriodiques(Picanto)
//                voitureService.generateFraisCirculationPeriodiques(Picantobis)
//                voitureService.generateFraisCirculationPeriodiques(Pg207)
//                voitureService.generateFraisCirculationPeriodiques(Megane)

                // Locations
                def marocain = new com.choranet.rentcar.Nationalite(libelle : 'Maroc').save()
                def francais = new com.choranet.rentcar.Nationalite(libelle : 'France').save()
                def anglend = new com.choranet.rentcar.Nationalite(libelle : 'Angletaire').save()
                def italien = new com.choranet.rentcar.Nationalite(libelle : 'Italie').save()

                def conducteur1 = new com.choranet.rentcar.Client(nom : 'nabil', prenom : 'bouziane',
                    cin : null, cne : null, carteSejour : 'FG12354', numeroPassport : null, adresseMaroc : null,
                    adresseEtranger : 'Bd. roudani A:12 paris', gsm : '0660346546', fixe : '05345478',
                    numeroPermis : '442134', mail : 'toto@gmail.com', nationalite : italien).save()
                def conducteur2 = new com.choranet.rentcar.Client(nom : 'sayf', prenom : 'abotaja',
                    cin : 'HG2354', cne : null, carteSejour : null, numeroPassport : null, adresseMaroc : 'Bd. chefchaouni N°12',
                    adresseEtranger : null, gsm : '0660346326', fixe : '05311478',
                    numeroPermis : '902134', mail : 'toti@gmail.com', nationalite : marocain).save()
                def conducteur3 = new com.choranet.rentcar.Client(nom : 'rabbah', prenom : 'mahmoud',
                    cin : null, cne : null, carteSejour : null, numeroPassport : 'DZZF425425', adresseMaroc : null,
                    adresseEtranger : 'Bd. roudani A:12 paris', gsm : '0660346546', fixe : '05345478',
                    numeroPermis : '55555', mail : 'toto@gmail.com', nationalite : francais).save()
                def conducteur4 = new com.choranet.rentcar.Client(nom : 'chahdi', prenom : 'mohamed',
                    cin : null, cne : 'AN457896', carteSejour : null, numeroPassport : null, adresseMaroc : 'Bd. chefchaouni N°12',
                    adresseEtranger : null, gsm : '0660346326', fixe : '05311478',
                    numeroPermis : '666666', mail : 'toti@gmail.com', nationalite : marocain).save()
                def conducteur5 = new com.choranet.rentcar.Client(nom : 'ahmed', prenom : 'ezzahri',
                    cin : null, cne : null, carteSejour : 'aaaaa', numeroPassport : 'DZZF425425', adresseMaroc : null,
                    adresseEtranger : 'Bd. roudani A:12 paris', gsm : '0660346546', fixe : '05345478',
                    numeroPermis : '777777', mail : 'toto@gmail.com', nationalite : marocain).save()
                def conducteur6 = new com.choranet.rentcar.Client(nom : 'oussama', prenom : 'akram',
                    cin : 'bbbbbb', cne : null, carteSejour : null, numeroPassport : null, adresseMaroc : 'Bd. chefchaouni N°12',
                    adresseEtranger : null, gsm : '0660346326', fixe : '05311478',
                    numeroPermis : '88888', mail : 'toti@gmail.com', nationalite : marocain).save()
                def conducteur7 = new com.choranet.rentcar.Client(nom : 'farid', prenom : 'laatichi',
                    cin : null, cne : null, carteSejour : 'cccc', numeroPassport : null, adresseMaroc : null,
                    adresseEtranger : 'Bd. roudani A:12 paris', gsm : '0660346546', fixe : '05345478',
                    numeroPermis : '1111111', mail : 'toto@gmail.com', nationalite : marocain).save()
                def conducteur8 = new com.choranet.rentcar.Client(nom : 'jalal', prenom : 'lwalidi',
                    cin : 'dddddd', cne : null, carteSejour : null, numeroPassport : null, adresseMaroc : 'Bd. chefchaouni N°12',
                    adresseEtranger : null, gsm : '0660346326', fixe : '05311478',
                    numeroPermis : '9999999', mail : 'toti@gmail.com', nationalite : marocain).save()
                def conducteur9 = new com.choranet.rentcar.Client(nom : 'yacine', prenom : 'larfaoui',
                    cin : null, cne : null, carteSejour : 'eeeeee', numeroPassport : null, adresseMaroc : null,
                    adresseEtranger : 'Bd. roudani A:12 paris', gsm : '0660346546', fixe : '05345478',
                    numeroPermis : '222222', mail : 'toto@gmail.com', nationalite : marocain).save()
                def conducteur10 = new com.choranet.rentcar.Client(nom : 'rajaa', prenom : 'lkhalidi',
                    cin : 'ffffff', cne : null, carteSejour : null, numeroPassport : null, adresseMaroc : 'Bd. chefchaouni N°12',
                    adresseEtranger : null, gsm : '0660346326', fixe : '05311478',
                    numeroPermis : '333333', mail : 'toti@gmail.com', nationalite : marocain).save()
                def conducteur11 = new com.choranet.rentcar.Client(nom : 'imane', prenom : 'lbasiti',
                    cin : null, cne : null, carteSejour : null, numeroPassport : 'ggggggg', adresseMaroc : null,
                    adresseEtranger : 'Bd. roudani A:12 paris', gsm : '0660346546', fixe : '05345478',
                    numeroPermis : '4444444', mail : 'toto@gmail.com', nationalite : marocain).save()
                def conducteur12 = new com.choranet.rentcar.Client(nom : 'dhmoum', prenom : 'jahmoud',
                    cin : 'hhhhhhh', cne : null, carteSejour : null, numeroPassport : null, adresseMaroc : 'Bd. chefchaouni N°12',
                    adresseEtranger : null, gsm : '0660346326', fixe : '05311478',
                    numeroPermis : '10101010', mail : 'toti@gmail.com', nationalite : marocain).save()
                def conducteur13 = new com.choranet.rentcar.Client(nom : 'abdellah', prenom : 'arfaoui',
                    cin : null, cne : null, carteSejour : 'iiiiiii', numeroPassport : null, adresseMaroc : null,
                    adresseEtranger : 'Bd. roudani A:12 paris', gsm : '0660346546', fixe : '05345478',
                    numeroPermis : '20202020', mail : 'toto@gmail.com', nationalite : marocain).save()
                def conducteur14 = new com.choranet.rentcar.Client(nom : 'kamal', prenom : 'abertoun',
                    cin : 'jjjjjj', cne : null, carteSejour : null, numeroPassport : null, adresseMaroc : 'Bd. chefchaouni N°12',
                    adresseEtranger : null, gsm : '0660346326', fixe : '05311478',
                    numeroPermis : '30303030', mail : 'toti@gmail.com', nationalite : marocain).save()
                def conducteur15 = new com.choranet.rentcar.Client(nom : 'amal', prenom : 'raji',
                    cin : null, cne : null, carteSejour : null, numeroPassport : 'kkkkkk', adresseMaroc : null,
                    adresseEtranger : 'Bd. roudani A:12 paris', gsm : '0660346546', fixe : '05345478',
                    numeroPermis : '40404040', mail : 'toto@gmail.com', nationalite : marocain).save()
                def conducteur16 = new com.choranet.rentcar.Client(nom : 'narjis', prenom : 'sahi',
                    cin : 'lllllll', cne : null, carteSejour : null, numeroPassport : null, adresseMaroc : 'Bd. chefchaouni N°12',
                    adresseEtranger : null, gsm : '0660346326', fixe : '05311478',
                    numeroPermis : '50505050', mail : 'toti@gmail.com', nationalite : marocain).save()

                def location1 = new com.choranet.rentcar.Location( prixParJour : 250.0,
                    dateDepart : new Date() - 10, dateRestitution : new Date() - 8, kilometrageDepart : 100000, 
                    kilometrageRetour : 102000, lieuLivraison : 'Casa', lieuReprise : 'Aîn sebâa',
                    montantPaye : 500.0, modePaiment : 'Espece', observations : 'bon client',
                    contratNumeriser : null, declarationPrealableNumeriser : null, premierConducteur : conducteur1,
                    deuxiemeConducteur : null, voiture : Atos, agentLoueurResponsable : userGestionLocation)
                location1.setTrans_contrat(null)
                location1.setTrans_declaration(null)
                location1.save()

                def location2 = new com.choranet.rentcar.Location( prixParJour : 300.0,
                    dateDepart : new Date() - 2, dateRestitution : new Date() + 2, kilometrageDepart : 6500, 
                    kilometrageRetour : null, lieuLivraison : 'Casa', lieuReprise : 'Aîn sebâa', montantPaye : 600.0,
                    modePaiment : 'Espece', observations : 'Etat carroserie dégradée', contratNumeriser : null,
                    declarationPrealableNumeriser : null, premierConducteur : conducteur2,
                    deuxiemeConducteur : null, voiture : Punto, agentLoueurResponsable : userGestionLocation)
                location2.setTrans_contrat(null)
                location2.setTrans_declaration(null)
                location2.save()
                
                def location3 = new com.choranet.rentcar.Location( prixParJour : 400.0,
                    dateDepart : new Date() - 30 , dateRestitution : new Date() - 28, kilometrageDepart : 6500,
                    kilometrageRetour : null, lieuLivraison : 'Casa', lieuReprise : 'Aîn sebâa',
                    montantPaye : 600.0, modePaiment : 'Espece', observations : 'Etat carroserie ok',
                    contratNumeriser : null, declarationPrealableNumeriser : null, premierConducteur : conducteur3,
                    deuxiemeConducteur : null, voiture : Loganbis, agentLoueurResponsable : userGestionLocation)
                location3.setTrans_contrat(null)
                location3.setTrans_declaration(null)
                location3.save()
                
                def location4 = new com.choranet.rentcar.Location( prixParJour : 250.0,
                    dateDepart : new Date() , dateRestitution : new Date() + 3, kilometrageDepart : 6500,
                    kilometrageRetour : null, lieuLivraison : 'Casa', lieuReprise : 'Aîn sebâa',
                    montantPaye : 750.0, modePaiment : 'Espece', observations : 'Etat carroserie ok',
                    contratNumeriser : null, declarationPrealableNumeriser : null, premierConducteur : conducteur4,
                    deuxiemeConducteur : null, voiture : Logan, agentLoueurResponsable : userGestionLocation)
                location4.setTrans_contrat(null)
                location4.setTrans_declaration(null)
                location4.save()
                
                def location5 = new com.choranet.rentcar.Location( prixParJour : 250.0,
                    dateDepart : new Date() - 30 , dateRestitution : new Date() - 28, kilometrageDepart : 6500,
                    kilometrageRetour : 7500, lieuLivraison : 'Casa', lieuReprise : 'Aîn sebâa',
                    montantPaye : 500.0, modePaiment : 'Espece', observations : 'Etat carroserie ok',
                    contratNumeriser : null, declarationPrealableNumeriser : null, premierConducteur : conducteur4,
                    deuxiemeConducteur : null, voiture : Swift, agentLoueurResponsable : userGestionLocation)
                location5.setTrans_contrat(null)
                location5.setTrans_declaration(null)
                location5.save()
                
                def location6 = new com.choranet.rentcar.Location( prixParJour : 300.0,
                    dateDepart : new Date() - 62 , dateRestitution : new Date() - 60, kilometrageDepart : 6500,
                    kilometrageRetour : 8500, lieuLivraison : 'Casa', lieuReprise : 'Aîn sebâa',
                    montantPaye : 500.0, modePaiment : 'Espece', observations : 'Etat carroserie ok',
                    contratNumeriser : null, declarationPrealableNumeriser : null, premierConducteur : conducteur5,
                    deuxiemeConducteur : null, voiture : Swiftbis, agentLoueurResponsable : userGestionLocation)
                location6.setTrans_contrat(null)
                location6.setTrans_declaration(null)
                location6.save()
                
                def location7 = new com.choranet.rentcar.Location( prixParJour : 250.0,
                    dateDepart : new Date() - 11 , dateRestitution : new Date() - 7, kilometrageDepart : 6500,
                    kilometrageRetour : 8500, lieuLivraison : 'Casa', lieuReprise : 'Aîn sebâa',
                    montantPaye : 1000.0, modePaiment : 'Espece', observations : 'Etat carroserie ok',
                    contratNumeriser : null, declarationPrealableNumeriser : null, premierConducteur : conducteur6,
                    deuxiemeConducteur : null, voiture : Picanto, agentLoueurResponsable : userGestionLocation)
                location7.setTrans_contrat(null)
                location7.setTrans_declaration(null)
                location7.save()
                
                def reservation1 = new com.choranet.rentcar.Reservation( prixParJour : 250.0, dateDepart : new Date() + 100 , dateRestitution : new Date() + 115, lieuLivraison : 'Casa', lieuReprise : 'Aîn sebâa',
                    montantPaye : 0.0, modePaiment : null, observations : null, premierConducteur : conducteur7,
                    deuxiemeConducteur : null, voiture : Picantobis, agentLoueurResponsable : userGestionLocation)
                reservation1.save()
                
                def reservation2 = new com.choranet.rentcar.Reservation( prixParJour : 250.0,
                    dateDepart : new Date() + 75 , dateRestitution : new Date() + 80, lieuLivraison : 'Casa', lieuReprise : 'Aîn sebâa',
                    montantPaye : 0.0, modePaiment : null, observations : null, premierConducteur : conducteur8,
                    deuxiemeConducteur : null, voiture : Pg207, agentLoueurResponsable : userGestionLocation)
                reservation2.save()
            }
            test {
                def roleRoot = new com.choranet.rentcar.GroupeUtilisateur(authority:'ROLE_ROOT', description:'Super Utilisateur').save()
                def roleAdmin = new com.choranet.rentcar.GroupeUtilisateur(authority:'ROLE_ADMIN', description:'Administrateur').save() 
                def roleGestionPark = new com.choranet.rentcar.GroupeUtilisateur(authority:'ROLE_GESTION_PARK', description:'Gestion Park Automobile').save() 
                def roleGestionLocation = new com.choranet.rentcar.GroupeUtilisateur(authority:'ROLE_GESTION_LOCATION', description:'Gestion location des voitures').save() 
                def roleTdb = new com.choranet.rentcar.GroupeUtilisateur(authority:'ROLE_TDB', description:'Visualisation du tableau de bord').save()
        
                def userRoot = new com.choranet.rentcar.Utilisateur(username:'root',
                    userRealName:'root',
                    enabled: true,
                    emailShow: true,
                    email: 'admin@choranet.com',
                    passwd: authenticateService.encodePassword('root')).save()
                
               
                def secureRoot = new com.choranet.rentcar.DroitUtilisateur(url: '/zul/root/**',
                    configAttribute:'ROLE_ROOT').save()
                def secureAdmin = new com.choranet.rentcar.DroitUtilisateur(url: '/zul/admin/**',
                    configAttribute:'ROLE_ADMIN').save()
                def secureGestionPark = new com.choranet.rentcar.DroitUtilisateur(url: '/zul/park/**',
                    configAttribute:'ROLE_GESTION_PARK').save()
                def secureGestionLocation = new com.choranet.rentcar.DroitUtilisateur(url: '/zul/location/**',
                    configAttribute:'ROLE_GESTION_LOCATION').save()                
                def secureTdb = new com.choranet.rentcar.DroitUtilisateur(url: '/zul/tdb/**',
                    configAttribute:'ROLE_TDB').save()
                
                userRoot.addToAuthorities(roleRoot) 
                userRoot.addToAuthorities(roleAdmin)
                userRoot.addToAuthorities(roleGestionPark)
                userRoot.addToAuthorities(roleGestionLocation)
                userRoot.addToAuthorities(roleTdb)
                
                
                //Energie
                def ene = new com.choranet.rentcar.Energie(libelle : 'Essence').save()
                def end = new com.choranet.rentcar.Energie(libelle : 'Diesel').save()

                //Marques et Models de voiture
                def marqueAlfaromeo = new com.choranet.rentcar.Marque(libelle : 'Alfa romeo').save()
                new com.choranet.rentcar.Model(libelle : '145', marque : marqueAlfaromeo).save()
                new com.choranet.rentcar.Model(libelle : '146', marque : marqueAlfaromeo).save()
                new com.choranet.rentcar.Model(libelle : '147', marque : marqueAlfaromeo).save()
                new com.choranet.rentcar.Model(libelle : '156', marque : marqueAlfaromeo).save()
                new com.choranet.rentcar.Model(libelle : '159', marque : marqueAlfaromeo).save()
                new com.choranet.rentcar.Model(libelle : '164', marque : marqueAlfaromeo).save()
                new com.choranet.rentcar.Model(libelle : '166', marque : marqueAlfaromeo).save()

                def marqueAudi = new com.choranet.rentcar.Marque(libelle : 'Audi').save()
                new com.choranet.rentcar.Model(libelle : 'A1', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'A2', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'A3', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'A4', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'A5', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'A6', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'A8', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'Q5', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : 'Q7', marque : marqueAudi).save()
                new com.choranet.rentcar.Model(libelle : '164', marque : marqueAudi).save()

                def marqueBmw = new com.choranet.rentcar.Marque(libelle : 'BMW').save()
                new com.choranet.rentcar.Model(libelle : 'M', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'Serie 1', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'Serie 3', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'Serie 5', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'Serie 6', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'Serie 7', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'X3', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'X5', marque : marqueBmw).save()
                new com.choranet.rentcar.Model(libelle : 'X6', marque : marqueBmw).save()

                def marqueChoverolet = new com.choranet.rentcar.Marque(libelle : 'Choverolet').save()
                new com.choranet.rentcar.Model(libelle : 'Optra', marque : marqueChoverolet).save()
                new com.choranet.rentcar.Model(libelle : 'Spark', marque : marqueChoverolet).save()
                    
                def marqueChery = new com.choranet.rentcar.Marque(libelle : 'Chery').save()
                new com.choranet.rentcar.Model(libelle : 'A113', marque : marqueChery).save()
                new com.choranet.rentcar.Model(libelle : 'A516', marque : marqueChery).save()
                new com.choranet.rentcar.Model(libelle : 'Eastar', marque : marqueChery).save()
                new com.choranet.rentcar.Model(libelle : 'QQ', marque : marqueChery).save()
                new com.choranet.rentcar.Model(libelle : 'Tiggo', marque : marqueChery).save()

                def marqueCitroen = new com.choranet.rentcar.Marque(libelle : 'Citroen').save()
                new com.choranet.rentcar.Model(libelle : 'Berlingo', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'C1', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'C2', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'C3', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'C4', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'C4 Picasso', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'C5', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'C6', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'Jumper', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'Jumpy', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'Saxo', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'Xsara', marque : marqueCitroen).save()
                new com.choranet.rentcar.Model(libelle : 'Xsara Picasso', marque : marqueCitroen).save()

                def marqueDacia = new com.choranet.rentcar.Marque(libelle : 'Dacia').save()
                new com.choranet.rentcar.Model(libelle : 'Duster', marque : marqueDacia).save()
                def modelLogan = new com.choranet.rentcar.Model(libelle : 'Logan', marque : marqueDacia).save()
                new com.choranet.rentcar.Model(libelle : 'Sandero', marque : marqueDacia).save()
                new com.choranet.rentcar.Model(libelle : 'Solenzo', marque : marqueDacia).save()

                def marqueDaihatsu = new com.choranet.rentcar.Marque(libelle : 'Daihatsu').save()
                new com.choranet.rentcar.Model(libelle : 'Hijet', marque : marqueDaihatsu).save()
                new com.choranet.rentcar.Model(libelle : 'Rocky', marque : marqueDaihatsu).save()
                new com.choranet.rentcar.Model(libelle : 'Terios', marque : marqueDaihatsu).save()
                new com.choranet.rentcar.Model(libelle : 'Sirion', marque : marqueDaihatsu).save()

                def marqueFiat = new com.choranet.rentcar.Marque(libelle : 'Fiat').save()
                new com.choranet.rentcar.Model(libelle : 'Brava', marque : marqueFiat).save()
                new com.choranet.rentcar.Model(libelle : 'Bravo', marque : marqueFiat).save()
                new com.choranet.rentcar.Model(libelle : 'Linea', marque : marqueFiat).save()
                new com.choranet.rentcar.Model(libelle : 'Palio', marque : marqueFiat).save()
                new com.choranet.rentcar.Model(libelle : 'Panda', marque : marqueFiat).save()
                def modelPunto = new com.choranet.rentcar.Model(libelle : 'Punto', marque : marqueFiat).save()
                new com.choranet.rentcar.Model(libelle : 'Siena', marque : marqueFiat).save()
                new com.choranet.rentcar.Model(libelle : 'Stilo', marque : marqueFiat).save()
                new com.choranet.rentcar.Model(libelle : 'Uno', marque : marqueFiat).save()

                def marqueFord = new com.choranet.rentcar.Marque(libelle : 'Ford').save()
                new com.choranet.rentcar.Model(libelle : 'Fiesta', marque : marqueFord).save()
                new com.choranet.rentcar.Model(libelle : 'Focus', marque : marqueFord).save()
                new com.choranet.rentcar.Model(libelle : 'Fusion', marque : marqueFord).save()
                new com.choranet.rentcar.Model(libelle : 'Tourneo', marque : marqueFord).save()

                def marqueHonda = new com.choranet.rentcar.Marque(libelle : 'Honda').save()
                new com.choranet.rentcar.Model(libelle : 'Accord', marque : marqueHonda).save()
                new com.choranet.rentcar.Model(libelle : 'City', marque : marqueHonda).save()
                new com.choranet.rentcar.Model(libelle : 'Civic', marque : marqueHonda).save()
                new com.choranet.rentcar.Model(libelle : 'Jazz', marque : marqueHonda).save()
                new com.choranet.rentcar.Model(libelle : 'Legend', marque : marqueHonda).save()
                new com.choranet.rentcar.Model(libelle : 'Odyssey', marque : marqueHonda).save()
                new com.choranet.rentcar.Model(libelle : 'Stream', marque : marqueHonda).save()
                new com.choranet.rentcar.Model(libelle : 'Vigor', marque : marqueHonda).save()

                def marqueHyundai = new com.choranet.rentcar.Marque(libelle : 'Hyundai').save()
                new com.choranet.rentcar.Model(libelle : 'Accent', marque : marqueHyundai).save()
                def modelAtos = new com.choranet.rentcar.Model(libelle : 'Atos', marque : marqueHyundai).save()
                new com.choranet.rentcar.Model(libelle : 'Atos Prime', marque : marqueHyundai).save()
                new com.choranet.rentcar.Model(libelle : 'Azera', marque : marqueHyundai).save()
                new com.choranet.rentcar.Model(libelle : 'Elantra', marque : marqueHyundai).save()
                new com.choranet.rentcar.Model(libelle : 'Galloper', marque : marqueHyundai).save()
                new com.choranet.rentcar.Model(libelle : 'i10', marque : marqueHyundai).save()
                new com.choranet.rentcar.Model(libelle : 'i30', marque : marqueHyundai).save()
                new com.choranet.rentcar.Model(libelle : 'Tucson', marque : marqueHyundai).save()

                def marqueKia = new com.choranet.rentcar.Marque(libelle : 'Kia').save()
                new com.choranet.rentcar.Model(libelle : 'Carens', marque : marqueKia).save()
                new com.choranet.rentcar.Model(libelle : "Cee'd", marque : marqueKia).save()
                new com.choranet.rentcar.Model(libelle : 'Cerato', marque : marqueKia).save()
                def modelPicanto = new com.choranet.rentcar.Model(libelle : 'Picanto', marque : marqueKia).save()
                new com.choranet.rentcar.Model(libelle : 'Rio', marque : marqueKia).save()
                new com.choranet.rentcar.Model(libelle : 'Sorento', marque : marqueKia).save()
                new com.choranet.rentcar.Model(libelle : 'Soul', marque : marqueKia).save()
                new com.choranet.rentcar.Model(libelle : 'Sportage', marque : marqueKia).save()

                def marqueMazda = new com.choranet.rentcar.Marque(libelle : 'Mazda').save()
                new com.choranet.rentcar.Model(libelle : '2', marque : marqueMazda).save()
                new com.choranet.rentcar.Model(libelle : '3', marque : marqueMazda).save()
                new com.choranet.rentcar.Model(libelle : '323', marque : marqueMazda).save()
                new com.choranet.rentcar.Model(libelle : '5', marque : marqueMazda).save()
                new com.choranet.rentcar.Model(libelle : '6', marque : marqueMazda).save()
                new com.choranet.rentcar.Model(libelle : '626', marque : marqueMazda).save()

                def marqueMercedesBenz = new com.choranet.rentcar.Marque(libelle : 'Mercedes-Benz').save()
                new com.choranet.rentcar.Model(libelle : '190', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '200', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '207', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '250', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '260', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '270', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '300', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '310', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : '350', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe A', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe B', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe C', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe CL', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe CLK', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe CLS', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe D', marque : marqueMercedesBenz).save()
                new com.choranet.rentcar.Model(libelle : 'Classe E', marque : marqueMercedesBenz).save()

                def marqueMini = new com.choranet.rentcar.Marque(libelle : 'Mini').save()
                new com.choranet.rentcar.Model(libelle : 'Cabrio', marque : marqueMini).save()
                new com.choranet.rentcar.Model(libelle : 'Cooper', marque : marqueMini).save()

                def marqueMitsubishi = new com.choranet.rentcar.Marque(libelle : 'Mini').save()
                new com.choranet.rentcar.Model(libelle : 'L200', marque : marqueMitsubishi).save()
                new com.choranet.rentcar.Model(libelle : 'L300', marque : marqueMitsubishi).save()
                new com.choranet.rentcar.Model(libelle : 'Lancer', marque : marqueMitsubishi).save()

                def marqueNissan = new com.choranet.rentcar.Marque(libelle : 'Nissan').save()
                new com.choranet.rentcar.Model(libelle : 'Cedric', marque : marqueNissan).save()
                new com.choranet.rentcar.Model(libelle : 'Micra', marque : marqueNissan).save()
                new com.choranet.rentcar.Model(libelle : 'Prado', marque : marqueNissan).save()
                new com.choranet.rentcar.Model(libelle : 'Premira', marque : marqueNissan).save()
                new com.choranet.rentcar.Model(libelle : 'Qashqai', marque : marqueNissan).save()
                new com.choranet.rentcar.Model(libelle : 'Tirrano', marque : marqueNissan).save()
                new com.choranet.rentcar.Model(libelle : 'X-Trail', marque : marqueNissan).save()

                def marqueOpel = new com.choranet.rentcar.Marque(libelle : 'Opel').save()
                new com.choranet.rentcar.Model(libelle : 'Astra', marque : marqueOpel).save()
                new com.choranet.rentcar.Model(libelle : 'Combo', marque : marqueOpel).save()
                new com.choranet.rentcar.Model(libelle : 'Prado', marque : marqueOpel).save()
                new com.choranet.rentcar.Model(libelle : 'Corsa', marque : marqueOpel).save()
                new com.choranet.rentcar.Model(libelle : 'Insignia', marque : marqueOpel).save()
                new com.choranet.rentcar.Model(libelle : 'Tigra', marque : marqueOpel).save()

                def marquePeugeot = new com.choranet.rentcar.Marque(libelle : 'Peugeot').save()
                new com.choranet.rentcar.Model(libelle : '206', marque : marquePeugeot).save()
                def modelPg207 = new com.choranet.rentcar.Model(libelle : '207', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : '306', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : '307', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : '308', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : '406', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : '407', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : '607', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : '807', marque : marquePeugeot).save()
                new com.choranet.rentcar.Model(libelle : 'Patner', marque : marquePeugeot).save()
                    
                def marqueRenault = new com.choranet.rentcar.Marque(libelle : 'Renault').save()
                new com.choranet.rentcar.Model(libelle : 'Clio', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Espace', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Fluence', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Kangoo', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Koleos', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Logan', marque : marqueRenault).save()
                def modelMegane = new com.choranet.rentcar.Model(libelle : 'Megane', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Sandero', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Scenic', marque : marqueRenault).save()
                new com.choranet.rentcar.Model(libelle : 'Symbol', marque : marqueRenault).save()

                def marqueSeat = new com.choranet.rentcar.Marque(libelle : 'Seat').save()
                new com.choranet.rentcar.Model(libelle : 'Cordoba', marque : marqueSeat).save()
                new com.choranet.rentcar.Model(libelle : 'Ibiza', marque : marqueSeat).save()
                new com.choranet.rentcar.Model(libelle : 'Leon', marque : marqueSeat).save()

                def marqueSuziki = new com.choranet.rentcar.Marque(libelle : 'Suziki').save()
                new com.choranet.rentcar.Model(libelle : 'Alto', marque : marqueSuziki).save()
                new com.choranet.rentcar.Model(libelle : 'Baleno', marque : marqueSuziki).save()
                new com.choranet.rentcar.Model(libelle : 'Carry', marque : marqueSuziki).save()
                def modelSwift = new com.choranet.rentcar.Model(libelle : 'Swift', marque : marqueSuziki).save()

                def marqueToyota = new com.choranet.rentcar.Marque(libelle : 'Toyota').save()
                new com.choranet.rentcar.Model(libelle : 'Auris', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'Avensis', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'Corolla', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'Corolla verso', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'Prado', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'RAV4', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'Verso', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'Yaris', marque : marqueToyota).save()
                new com.choranet.rentcar.Model(libelle : 'Yaris verso', marque : marqueToyota).save()

                def marqueVolkswagon = new com.choranet.rentcar.Marque(libelle : 'Volkswagon').save()
                new com.choranet.rentcar.Model(libelle : 'Bora', marque : marqueVolkswagon).save()
                new com.choranet.rentcar.Model(libelle : 'Caddy', marque : marqueVolkswagon).save()
                new com.choranet.rentcar.Model(libelle : 'Fox', marque : marqueVolkswagon).save()
                new com.choranet.rentcar.Model(libelle : 'Gol', marque : marqueVolkswagon).save()
                new com.choranet.rentcar.Model(libelle : 'Passat', marque : marqueVolkswagon).save()
                def modelPolo = new com.choranet.rentcar.Model(libelle : 'Polo', marque : marqueVolkswagon).save()
                new com.choranet.rentcar.Model(libelle : 'Tiguan', marque : marqueVolkswagon).save()
                new com.choranet.rentcar.Model(libelle : 'Touareg', marque : marqueVolkswagon).save()
                new com.choranet.rentcar.Model(libelle : 'Touran', marque : marqueVolkswagon).save()
                  
                def marqueVolvo = new com.choranet.rentcar.Marque(libelle : 'Volvo').save()

                // Entretiens Normaux non périodiques
                def maintenance = new com.choranet.rentcar.TypeEntretien(libelle : 'Maintenance').save()
                def reparation = new com.choranet.rentcar.TypeEntretien(libelle : 'Réparation').save()
                def lavage = new com.choranet.rentcar.TypeEntretien(libelle : 'Lavage').save()

                // Entretiens Périodiques
                def vidange = new com.choranet.rentcar.TypeEntretienPeriodique(libelle:'Vidange', periodicite:10000, prixEntretien:300).save()
                def filtration = new com.choranet.rentcar.TypeEntretienPeriodique(libelle:'Filtration', periodicite:20000, prixEntretien:200).save()
                new com.choranet.rentcar.TypeEntretienPeriodique(libelle : 'Climatisation', periodicite : 20000, prixEntretien:350).save()

                // Frais de circulation
                def carburant = new com.choranet.rentcar.TypeFraisCirculation(libelle : 'Carburant').save()
                def payage = new com.choranet.rentcar.TypeFraisCirculation(libelle : 'Payage').save()
                def recette = new com.choranet.rentcar.TypeFraisCirculation(libelle : 'Recette').save()
                def incident = new com.choranet.rentcar.TypeFraisCirculation(libelle : 'Incident').save()
                def depannage = new com.choranet.rentcar.TypeFraisCirculation(libelle : 'Divers').save()

                // Frais de circulation périodique
                def assurance = new com.choranet.rentcar.TypeFraisCirculationPeriodique(libelle:'Assurance', periodicite:365, chargeDeBase:4000).save()
                def vignette = new com.choranet.rentcar.TypeFraisCirculationPeriodique(libelle : 'Vignette', periodicite:365, chargeDeBase:1500).save()
                def visite_technique = new com.choranet.rentcar.TypeFraisCirculationPeriodique(libelle : 'Visite technique', periodicite:182, chargeDeBase:400).save()

                // Locations
                def marocain = new com.choranet.rentcar.Nationalite(libelle : 'Maroc').save()
                new com.choranet.rentcar.Nationalite(libelle : 'France').save()
                new com.choranet.rentcar.Nationalite(libelle : 'Angletaire').save()
                new com.choranet.rentcar.Nationalite(libelle : 'Italie').save()
                
            }
            production {
//                new com.choranet.rentcar.PaternCompteur(libelle : 'Pattern facture', prefixe : 'FACT{annee}{mois}{jour}', 
//                    suffixe : '', pas : 1, remplissage : 3, numeroSuivant : 1, type : 'FACTURE').save(); 
            }
        }
                
    }
    def destroy = {
    }
}
