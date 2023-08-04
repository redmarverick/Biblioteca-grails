package biblioteca.grails

import grails.gorm.services.Service

@Service(Estatisticas)
interface EstatisticasService {

    Estatisticas get(Serializable id)

    List<Estatisticas> list(Map args)

    Long count()

    void delete(Serializable id)

    Estatisticas save(Estatisticas estatisticas)

}