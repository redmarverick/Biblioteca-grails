<table class="table table-bordered table-striped">
    <thead>
    <tr>
        <th><g:message code="livro.titulo.label" default="Título"/></th>
        <th><g:message code="livro.editora.label" default="Editora"/></th>
        <th><g:message code="livro.anoPublicacao.label" default="Ano de Publicação"/></th>
        <th><g:message code="livro.isbn.label" default="ISBN"/></th>
        <th><g:message code="livro.exemplaresDisponiveis.label" default="Exemplares Disponíveis"/></th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${livrosDoAutor}" var="livro">
        <tr>
            <td>${livro?.titulo}</td>
            <td>${livro?.editora}</td>
            <td>${livro?.anoPublicacao}</td>
            <td>${livro?.isbn}</td>
            <td>${livro?.exemplaresDisponiveis}</td>
        </tr>
    </g:each>
    </tbody>
</table>
