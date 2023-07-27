<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'locacao.label', default: 'Locacao')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div id="content" role="main">
    <div class="container">
        <section class="row">
            <a href="#show-locacao" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                          default="Skip to content&hellip;"/></a>

            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="list" action="index"><g:message code="default.list.label"
                                                                       args="[entityName]"/></g:link></li>
                    <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                          args="[entityName]"/></g:link></li>
                </ul>
            </div>
        </section>
        <section class="row">
            <div id="show-locacao" class="col-6 content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]"/></h1>
                <fieldset class="form">
                    <table class="table table-bordered table-striped">
                        <tbody>
                        <tr>
                            <th><g:message code="locacao.cliente.label" default="Cliente"/></th>
                            <td><g:link controller="cliente" action="show" id="${locacao?.cliente?.id}">
                                ${locacao?.cliente?.nomeCompleto}
                            </g:link></td>
                        </tr>
                        <tr>
                            <th><g:message code="locacao.livro.label" default="Livro"/></th>
                            <td><g:link controller="livro" action="show" id="${locacao?.livro?.id}">
                                ${locacao?.livro?.titulo}
                            </g:link></td>
                        </tr>
                        <tr>
                            <th><g:message code="locacao.dataEmprestimo.label" default="Data de Empréstimo"/></th>
                            <td>${locacao?.dataEmprestimo}</td>
                        </tr>
                        <g:if test="${locacao?.situacao.toString() == 'Devolvido'}">
                            <tr>
                                <th><g:message code="locacao.diaDevolucao.label" default="Data de Devolução"/></th>
                                <td>${locacao?.diaDevolucao}</td>
                            </tr>
                        </g:if>
                        <tr>
                            <th>Situação</th>
                            <td>${locacao?.situacao}</td>
                        </tr
                        </tbody>
                    </table>
                </fieldset>
                <g:if test="${locacao?.situacao.toString() == 'Emprestado'}">
                    <fieldset class="form">
                        <g:form controller="locacao" action="devolver" params="[id: locacao?.id]">
                            <p>
                                <label for="diaDevolucao"><g:message code="locacao.diaDevolucao.label"
                                                                     default="Dia da Devolução"/>:</label>
                                <g:datePicker name="diaDevolucao" value="${locacao?.diaDevolucao}"
                                              noSelection="['': '']"/>
                            </p>

                            <p>
                                <g:submitButton name="devolver" value="Devolver" class="btn btn-primary"/>
                            </p>
                        </g:form>
                    </fieldset>
                </g:if>
            </div>
        </section>
    </div>
</div>
</body>
</html>
