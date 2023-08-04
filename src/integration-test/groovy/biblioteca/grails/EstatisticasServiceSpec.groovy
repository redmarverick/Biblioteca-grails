package biblioteca.grails

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EstatisticasServiceSpec extends Specification {

    EstatisticasService estatisticasService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Estatisticas(...).save(flush: true, failOnError: true)
        //new Estatisticas(...).save(flush: true, failOnError: true)
        //Estatisticas estatisticas = new Estatisticas(...).save(flush: true, failOnError: true)
        //new Estatisticas(...).save(flush: true, failOnError: true)
        //new Estatisticas(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //estatisticas.id
    }

    void "test get"() {
        setupData()

        expect:
        estatisticasService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Estatisticas> estatisticasList = estatisticasService.list(max: 2, offset: 2)

        then:
        estatisticasList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        estatisticasService.count() == 5
    }

    void "test delete"() {
        Long estatisticasId = setupData()

        expect:
        estatisticasService.count() == 5

        when:
        estatisticasService.delete(estatisticasId)
        sessionFactory.currentSession.flush()

        then:
        estatisticasService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Estatisticas estatisticas = new Estatisticas()
        estatisticasService.save(estatisticas)

        then:
        estatisticas.id != null
    }
}
