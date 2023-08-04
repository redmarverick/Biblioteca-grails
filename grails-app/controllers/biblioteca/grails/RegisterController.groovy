package biblioteca.grails

import grails.validation.ValidationException
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import biblioteca.grails.User
import biblioteca.grails.Role
import biblioteca.grails.UserRole

@Transactional
@Secured('permitAll')
class RegisterController {

    static allowedMethods = [register: "POST"]

    def index() { }

    def register() {
        if(!params.password.equals(params.repassword)) {
            flash.message = "A senha e a confirmação são diferentes"
            redirect action: "index"
            return
        } else {
            try {
                def user = User.findByUsername(params.username)?: new User(username: params.username, password: params.password, fullname: params.fullname).save()
                def role = Role.get(params.role.id)
                if(user && role) {
                    UserRole.create user, role

                    UserRole.withSession {
                        it.flush()
                        it.clear()
                    }

                    flash.message = "Usuário registrado com sucesso. Você pode fazer login agora!"
                    redirect controller: "login", action: "auth"
                } else {
                    flash.message = "O registro falhou"
                    render view: "index"
                    return
                }
            } catch (ValidationException e) {
                flash.message = "O registro falhou"
                redirect action: "index"
                return
            }
        }
    }
}