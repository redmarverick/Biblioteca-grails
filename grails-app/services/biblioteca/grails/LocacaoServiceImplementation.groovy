package biblioteca.grails

import org.hibernate.criterion.CriteriaSpecification
import org.hibernate.criterion.Order
import grails.gorm.transactions.Transactional
import org.hibernate.criterion.Restrictions

@Transactional
class LocacaoServiceImplementation implements LocacaoService {

    Locacao get(Serializable id) {
        return Locacao.get(id)
    }

    Long count() {
        return Locacao.count()
    }

    @Override
    void delete(Serializable id) {

    }

    Locacao save(Locacao locacao) {
        locacao.save(flush: true, failOnError: true)
    }

    @Override
    List<Locacao> list(Map args) {
        Locacao.withCriteria {
            if (args?.max) {
                maxResults(args.max as int)
            }
        }.list()
    }

    @Transactional(readOnly = true)
    List<Map<String, Object>> getTop10ClientesByLocacoes() {
        Cliente.withCriteria {
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            projections {
                groupProperty("clienteId", "id") // Use clienteId instead of id for the groupProperty
                groupProperty("nomeCompleto", "nomeCompleto")
                rowCount("locacoesCount")
            }
            createAlias("locacoes", "locacao")
            addOrder(Order.desc("locacoesCount"))
            maxResults(10)
            list()
        } as List<Map<String, Object>>
    }

    @Transactional(readOnly = true)
    Long countLocacoesByCliente(Cliente cliente) {
        return Locacao.createCriteria().count {
            eq("cliente", cliente)
        }
    }

    @Transactional(readOnly = true)
    List<Livro> getTop10LivrosByLocacoes() {
        Livro.withCriteria {
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            projections {
                groupProperty("id", "id")
                groupProperty("titulo", "titulo")
                rowCount("totalLocacoes")
            }
            createAlias("locacoes", "locacao")
            addOrder(Order.desc("totalLocacoes"))
            maxResults(10)
            list()
        } as List<Livro>
    }

    Long countLocacoesBySituacao(LocacaoSituacaoEnum situacao) {
        return Locacao.createCriteria().count {
            eq("situacao", situacao)
        }
    }
}
