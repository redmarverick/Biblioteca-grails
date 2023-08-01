package biblioteca.grails

import grails.gorm.services.Service
import org.springframework.transaction.annotation.Transactional
import org.grails.datastore.gorm.GormEntity

@Service(Cliente)
class ClienteService {

    Cliente get(Serializable id) {
        // Your implementation here to retrieve a Cliente by id
    }

    List<Cliente> list(Map args) {
        // Your implementation here to retrieve a list of Cliente objects
    }

    Long count() {
        // Your implementation here to get the count of Cliente objects
    }

    void delete(Serializable id) {
        // Your implementation here to delete a Cliente by id
    }

    Cliente save(Cliente cliente) {
        // Your implementation here to save a Cliente object
    }

    @Transactional(readOnly = true)
    List<Locacao> getLocacoesByCliente(Cliente cliente) {
        return Locacao.findAllByCliente(cliente)
    }
}