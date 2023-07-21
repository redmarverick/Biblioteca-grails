package biblioteca.grails

class Livro {
    String titulo
    String editora
    Integer anoPublicacao
    String isbn
    Integer exemplaresDisponiveis

    static belongsTo = [autor: Autor]
    
    static constraints = {
        titulo blank: false
        autor blank: false
        editora blank: false
        anoPublicacao min: 1000, max: Calendar.getInstance().get(Calendar.YEAR)
        isbn blank: false, unique: true
        exemplaresDisponiveis min: 0
    }
}
