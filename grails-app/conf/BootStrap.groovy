import grails.plugin.springsecurity.SpringSecurityUtils
import biblioteca.grails.Role

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        def userRole = new Role(authority: 'ROLE_USER').save(failOnError: true)
    }

    def destroy = {
    }
}
