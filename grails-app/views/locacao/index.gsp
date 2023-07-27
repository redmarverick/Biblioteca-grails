<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'locacao.label', default: 'Locacao')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div id="content" role="main">
    <div class="container">
        <section class="row">
            <a href="#list-locacao" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                          default="Skip to content&hellip;"/></a>

            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                          args="[entityName]"/></g:link></li>
                </ul>
            </div>
        </section>
        <section class="row">
            <div id="list-locacao" class="col-12 content scaffold-list" role="main">
                <h1>Lista de locações</h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th><g:message code="locacao.cliente.label" default="Cliente"/></th>
                        <th><g:message code="locacao.livro.label" default="Livro"/></th>
                        <th><g:message code="locacao.dataEmprestimo.label" default="Data de Empréstimo"/></th>
                        <th><g:message code="locacao.dataDevolucaoPrevista.label"
                                       default="Data de Devolução Prevista"/></th>
                        <th><g:message
                                code="locacao.diaDevolucao.label"
                                default="Data de Devolução"/></th>
                        <th><g:message code="locacao.situacao.label" default="Situação"/></th>
                        <g:if test="${locacao?.situacao.toString() == 'Emprestado'}"><th>Status</th></g:if>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${locacaoList}" var="locacao">
                        <tr>
                            <td><g:link controller="cliente" action="show"
                                        id="${locacao?.cliente?.id}">${locacao?.cliente?.nomeCompleto}</g:link></td>
                            <td><g:link controller="livro" action="show"
                                        id="${locacao?.livro?.id}">${locacao?.livro?.titulo}</g:link></td>
                            <td><g:formatDate format="dd/MM/yyyy" date="${locacao?.dataEmprestimo}"/></td>
                            <td><g:formatDate format="dd/MM/yyyy" date="${locacao?.dataDevolucaoPrevista}"/></td>
                            <td><g:formatDate format="dd/MM/yyyy" date="${locacao?.diaDevolucao}"/></td>
                            <g:if test="${locacao?.situacao.toString() == 'Devolvido'}"><td>${locacao?.situacao}</td></g:if>
                            <g:if test="${locacao?.situacao.toString() == 'Emprestado'}">
                                <td>
                                    <a href="${createLink(controller: 'locacao', action: 'show', id: locacao?.id)}"
                                       class="btn btn-primary">
                                        Devolver
                                    </a>
                                </td>
                            </g:if>
                        </tr>
                    </g:each>
                    </tbody>
                </table>

                ${Locacao?.livro?.titulo}
                <g:if test="${locacaoCount > params.int('max')}">
                    <div class="pagination">
                        <g:paginate total="${locacaoCount ?: 0}"/>
                    </div>
                </g:if>
            </div>
        </section>

    </div>
</div>
</body>
</html>