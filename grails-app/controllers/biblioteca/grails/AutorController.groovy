package biblioteca.grails

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
class AutorController {

    AutorService autorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond autorService.list(params), model: [autorCount: autorService.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        def autor = Autor.get(id)
        if (!autor) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'autor.label', default: 'Autor'), id])
            redirect(action: 'index')
            return
        }

        // Get all books written by the current author
        def livrosEscritos = Livro.findAllByAutor(autor)

        [autor: autor, livrosEscritos: livrosEscritos]
    }


    @Secured(['ROLE_ADMIN'])
    def create() {
        respond new Autor(params)
    }

    @Secured(['ROLE_ADMIN'])
    def save(Autor autor) {
        if (autor == null) {
            notFound()
            return
        }

        try {
            autorService.save(autor)
        } catch (ValidationException e) {
            respond autor.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'autor.label', default: 'Autor'), autor.id])
                redirect autor
            }
            '*' { respond autor, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit(Long id) {
        respond autorService.get(id)
    }

    @Secured(['ROLE_ADMIN'])
    def update(Autor autor) {
        if (autor == null) {
            notFound()
            return
        }

        try {
            autorService.save(autor)
        } catch (ValidationException e) {
            respond autor.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'autor.label', default: 'Autor'), autor.id])
                redirect autor
            }
            '*' { respond autor, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        autorService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'autor.label', default: 'Autor'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'autor.label', default: 'Autor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
