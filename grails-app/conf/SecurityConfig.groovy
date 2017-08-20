security {

    // see DefaultSecurityConfig.groovy for all settable/overridable properties

    active = true

    loginUserDomainClass = "com.choranet.rentcar.Utilisateur"
    authorityDomainClass = "com.choranet.rentcar.GroupeUtilisateur"
    requestMapClass = "com.choranet.rentcar.DroitUtilisateur"
        
    authenticationFailureUrl = '/login.zul?login_error=true'
    loginFormUrl = '/choraBarrage'
    cookieName = 'goodcar_remember_me'
    errorPage = '/zul/errors/denied.zul'
    defaultTargetUrl = '/zul/main.zul'
}
