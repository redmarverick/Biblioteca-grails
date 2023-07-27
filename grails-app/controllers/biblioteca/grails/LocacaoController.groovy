package biblioteca.grails

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

import java.text.ParseException
import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*

class LocacaoController {

    LocacaoService locacaoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond locacaoService.list(params), model: [locacaoCount: locacaoService.count()]
    }

    def show(Long id) {
        respond locacaoService.get(id)
    }

    def create() {
        respond new Locacao(params)
    }

    def save(Locacao locacao) {
        if (locacao == null) {
            notFound()
            return
        }

        try {
            locacaoService.save(locacao)
        } catch (ValidationException e) {
            respond locacao.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'locacao.label', default: 'Locacao'), locacao.id])
                redirect locacao
            }
            '*' { respond locacao, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond locacaoService.get(id)
    }

    @Transactional
    def devolver(Long id) {
        def locacao = Locacao.get(id)
        if (locacao) {
            if (!params.diaDevolucao) {
                flash.message = "Informe a data de devolução."
                render(view: "show", model: [locacao: locacao])
                return
            }
            log.debug("Received diaDevolucao value: ${params.diaDevolucao}")
            def dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm")
            try {
                locacao.diaDevolucao = dateFormat.parse("${params?.diaDevolucao_day}-${params?.diaDevolucao_month}-${params?.diaDevolucao_year} ${params?.diaDevolucao_hour}:${params?.diaDevolucao_minute}")
            } catch (ParseException e) {
                // Log the error for debugging
                log.error("Error parsing diaDevolucao: ${e.message}", e)
                // Set an error message for the user
                flash.message = "Data de devolução inválida. Utilize o formato yyyy-MM-dd HH:mm."
                render(view: "show", model: [locacao: locacao])
                return
            }
            locacao.situacao = LocacaoSituacaoEnum.Devolvido

            locacao.save(flush: true)

            redirect(action: "show", id: locacao.id)
        } else {
            flash.message = "Locação não encontrada."
            redirect(action: "index")
        }
    }


    @Transactional
    def update() {
        def locacao = Locacao.get(params.id)
        if (locacao) {
            locacao.properties = params
            locacao.save(flush: true)
            redirect(action: "show", id: locacao.id)
        } else {
            flash.message = "Locação não encontrada."
            redirect(action: "index")
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        locacaoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'locacao.label', default: 'Locacao'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'locacao.label', default: 'Locacao'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
