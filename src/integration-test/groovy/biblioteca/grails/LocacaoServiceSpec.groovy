package biblioteca.grails

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class LocacaoServiceSpec extends Specification {

    LocacaoService locacaoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Locacao(...).save(flush: true, failOnError: true)
        //new Locacao(...).save(flush: true, failOnError: true)
        //Locacao locacao = new Locacao(...).save(flush: true, failOnError: true)
        //new Locacao(...).save(flush: true, failOnError: true)
        //new Locacao(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //locacao.id
    }

    void "test get"() {
        setupData()

        expect:
        locacaoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Locacao> locacaoList = locacaoService.list(max: 2, offset: 2)

        then:
        locacaoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        locacaoService.count() == 5
    }

    void "test delete"() {
        Long locacaoId = setupData()

        expect:
        locacaoService.count() == 5

        when:
        locacaoService.delete(locacaoId)
        sessionFactory.currentSession.flush()

        then:
        locacaoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Locacao locacao = new Locacao()
        locacaoService.save(locacao)

        then:
        locacao.id != null
    }
}
