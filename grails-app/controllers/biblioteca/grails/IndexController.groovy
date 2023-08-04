package biblioteca.grails
import grails.plugin.springsecurity.annotation.Secured

class IndexController {

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index() { }
}
