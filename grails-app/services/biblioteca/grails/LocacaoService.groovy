package biblioteca.grails

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Locacao)
interface LocacaoService {

    Locacao get(Serializable id)

    List<Locacao> list(Map args)

    Long count()

    void delete(Serializable id)

    Locacao save(Locacao locacao)

    @Transactional(readOnly = true)
    Long countLocacoesByCliente(Cliente cliente)

    @Transactional(readOnly = true)
    Long countLocacoesBySituacao(LocacaoSituacaoEnum situacao)

    @Transactional(readOnly = true)
    List<Cliente> getTop10ClientesByLocacoes()

    @Transactional(readOnly = true)
    List<Livro> getTop10LivrosByLocacoes()
}
