package biblioteca.grails

import grails.gorm.services.Service

@Service(Locacao)
interface LocacaoService {

    Locacao get(Serializable id)

    List<Locacao> list(Map args)

    Long count()

    void delete(Serializable id)

    Locacao save(Locacao locacao)

}