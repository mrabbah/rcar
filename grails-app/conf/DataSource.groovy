dataSource {
    pooled = true
    driverClassName = "org.hsqldb.jdbcDriver"
    username = "sa"
    password = ""
}
hibernate {
    //hibernate.show_sql = true
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        logSql = true
        dataSource {
            dbCreate = "create" // one of 'create', 'create-drop','update'
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQLDialect
            username = "root"
            password = "root"
            dbCreate = "update"
            url = "jdbc:postgresql://localhost/goodcartest"
        }
    }
    test {
        dataSource {
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQLDialect
            username = "root"
            password = "root"
            dbCreate = "update"
            url = "jdbc:postgresql://localhost/goodcartest"
        }
    }
    production {
        dataSource {
            
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQLDialect
            username = "root"
            password = "root"
            dbCreate = "validate"
            url = "jdbc:postgresql://localhost/goodcartest"
        }
    }
}
