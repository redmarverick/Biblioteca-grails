package biblioteca.grails

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import biblioteca.grails.LocacaoServiceImplementation
import grails.plugin.springsecurity.annotation.Secured

class EstatisticasController {

    EstatisticasService estatisticasService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    LocacaoService locacaoService


    @Secured(['ROLE_ADMIN'])
    def index() {
        def locacoesEmprestadas = locacaoService.countLocacoesBySituacao(LocacaoSituacaoEnum.Emprestado)
        def top10Clientes = locacaoService.getTop10ClientesByLocacoes()
        def top10Livros

        top10Clientes.each { cliente ->
            cliente.locacoesCount = Locacao.locacoesByClienteId(cliente.nomeCompleto).count()
        }

        top10Livros = Locacao.getAll()
        top10Livros = top10Livros.groupBy {it?.livro?.titulo}

        [top10Clientes: top10Clientes, locacoesEmprestadas: locacoesEmprestadas, top10Livros: top10Livros]
    }


    @Secured(['ROLE_ADMIN'])
    def show(Long id) {
        respond estatisticasService.get(id)
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        respond new Estatisticas(params)
    }

    @Secured(['ROLE_ADMIN'])
    def save(Estatisticas estatisticas) {
        if (estatisticas == null) {
            notFound()
            return
        }

        try {
            estatisticasService.save(estatisticas)
        } catch (ValidationException e) {
            respond estatisticas.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'estatisticas.label', default: 'Estatisticas'), estatisticas.id])
                redirect estatisticas
            }
            '*' { respond estatisticas, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit(Long id) {
        respond estatisticasService.get(id)
    }

    @Secured(['ROLE_ADMIN'])
    def update(Estatisticas estatisticas) {
        if (estatisticas == null) {
            notFound()
            return
        }

        try {
            estatisticasService.save(estatisticas)
        } catch (ValidationException e) {
            respond estatisticas.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'estatisticas.label', default: 'Estatisticas'), estatisticas.id])
                redirect estatisticas
            }
            '*'{ respond estatisticas, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        estatisticasService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'estatisticas.label', default: 'Estatisticas'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'estatisticas.label', default: 'Estatisticas'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
