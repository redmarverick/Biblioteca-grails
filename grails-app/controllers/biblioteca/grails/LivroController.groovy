package biblioteca.grails

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class LivroController {
    Autor autor
    LivroService livroService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond livroService.list(params), model: [livroCount: livroService.count()]
    }

    def show(Long id) {
        respond livroService.get(id)
    }

    def create() {
        [livro: new Livro(), autores: Autor.list()]
    }

    def save(Livro livro) {
        if (livro == null) {
            notFound()
            return
        }

        def autorId = params['autor']
        def autor = Autor.get(autorId)

        livro.autor = autor

        try {
            livroService.save(livro)
        } catch (ValidationException e) {
            respond livro.errors, view: 'create', model: [autores: Autor.list()]
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

    def edit(Long id) {
        respond livroService.get(id)
    }

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
