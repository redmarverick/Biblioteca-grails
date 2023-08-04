package biblioteca.grails

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

class ClienteController {

    ClienteService clienteService

    @Secured(['ROLE_ADMIN'])
    def estatisticas() {
        // Counter for "Emprestado" LocacaoSituacaoEnum
        def emprestadosCount = Locacao.countBySituacao(LocacaoSituacaoEnum.Emprestado)

        // Top 10 Clientes with most Locacoes
        def topClientes = Cliente.createCriteria().list {
            projections {
                groupProperty('nome')
                rowCount('totalLocacoes')
            }
            order("totalLocacoes", "desc")
            maxResults(10)
        }

        // Top 10 Livros with most Locacoes
        def topLivros = Livro.createCriteria().list {
            projections {
                groupProperty('titulo')
                rowCount('totalLocacoes')
            }
            order("totalLocacoes", "desc")
            maxResults(10)
        }

        render view: "estatisticas", model: [
                emprestadosCount: emprestadosCount,
                topClientes: topClientes,
                topLivros: topLivros
        ]
    }

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond clienteService.list(params), model:[clienteCount: clienteService.count()]
    }

    @Secured(['ROLE_ADMIN'])
    def show(Long id) {
        Cliente cliente = Cliente.get(id)
        if (!cliente) {
            notFound()
            return
        }
        List<Locacao> locacoes = clienteService.getLocacoesByCliente(cliente)
        respond cliente, model: [locacoes: locacoes]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        respond new Cliente(params)
    }

    @Secured(['ROLE_ADMIN'])
    def save(Cliente cliente) {
        if (cliente == null) {
            notFound()
            return
        }

        try {
            clienteService.save(cliente)
        } catch (ValidationException e) {
            respond cliente.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cliente.label', default: 'Cliente'), cliente.id])
                redirect cliente
            }
            '*' { respond cliente, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit(Long id) {
        respond clienteService.get(id)
    }

    @Secured(['ROLE_ADMIN'])
    def update(Cliente cliente) {
        if (cliente == null) {
            notFound()
            return
        }

        try {
            clienteService.save(cliente)
        } catch (ValidationException e) {
            respond cliente.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cliente.label', default: 'Cliente'), cliente.id])
                redirect cliente
            }
            '*'{ respond cliente, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        clienteService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cliente.label', default: 'Cliente'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
