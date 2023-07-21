package AutorTagLib

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification

class GroovyTagLibSpec extends Specification implements TagLibUnitTest<GroovyTagLib> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
