package biblioteca.grails

class AutorTagLib {
    static namespace = 'autor'

    def novoAutorWidget = { attrs ->
        def autores = attrs.autores ?: []
        def selectAttrs = attrs.selectAttrs ?: [:]

        def novoAutorLink = g.createLink(controller: 'autor', action: 'create')

        out << g.select(from: autores, optionKey: 'id', optionValue: 'nomeCompleto', selectAttrs) {
            out << g.option(value: '', '') // Empty option
            out << g.option(value: novoAutorLink, 'Novo Autor')
        }
    }
}
