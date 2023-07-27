<%@ page import="biblioteca.grails.Livro" %>
<g:if test="${value}">
    <g:link controller="livro" action="show" id="${value.id}">${value.titulo}</g:link>
</g:if>