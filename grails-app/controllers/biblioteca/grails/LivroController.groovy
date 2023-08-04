package biblioteca.grails

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

class LivroController {

    LivroService livroService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond livroService.list(params), model: [livroCount: livroService.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        respond livroService.get(id)
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        respond new Livro(params)
    }

    @Secured(['ROLE_ADMIN'])
    def save(Livro livro) {
        if (livro == null) {
            notFound()
            return
        }

        try {
            livroService.save(livro)
        } catch (ValidationException e) {
            respond livro.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'livro.label', default: 'Livro'), livro.id])
                redirect livro
            }
            '*' { respond livro, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit(Long id) {
        respond livroService.get(id)
    }

    @Secured(['ROLE_ADMIN'])
    def update(Livro livro) {
        if (livro == null) {
            notFound()
            return
        }

        try {
            livroService.save(livro)
        } catch (ValidationException e) {
            respond livro.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'livro.label', default: 'Livro'), livro.id])
                redirect livro
            }
            '*' { respond livro, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        livroService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'livro.label', default: 'Livro'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'livro.label', default: 'Livro'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
