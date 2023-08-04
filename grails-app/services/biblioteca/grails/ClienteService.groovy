package biblioteca.grails

import grails.gorm.services.Service
import org.springframework.transaction.annotation.Transactional


@Service(Cliente)
interface ClienteService {

    Cliente get(Serializable id)

    List<Cliente> list(Map args)

    Long count()

    void delete(Serializable id)

    Cliente save(Cliente cliente)

    @Transactional(readOnly = true)
    List<Locacao> getLocacoesByCliente(Cliente cliente)

}