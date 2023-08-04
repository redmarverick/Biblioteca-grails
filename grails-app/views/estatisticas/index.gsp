<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Estatísticas</title>
</head>
<body>
<div id="content" role="main">
    <div class="container">
        <section class="row">
            <a href="#list-livro" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                        default="Skip to content&hellip;"/></a>

            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                </ul>
            </div>
        </section>
        <section class="row">
            <div class="col-12 content scaffold-list" role="main">
                <h1>Estatísticas</h1>
                <h2>Locações com Status "Emprestado"</h2>
                <p>Total de Locações: <strong>${locacoesEmprestadas}</strong></p>
                <h2>Top 10 Clientes com Mais Locações</h2>
                <table>
                    <thead>
                    <tr>
                        <th>Nome do Cliente</th>
                        <th>Total de Locações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${top10Clientes}" var="cliente">
                        <tr>
                            <td>${cliente.nomeCompleto}</td>
                            <td>${cliente.locacoesCount}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <h2>Top 10 Livros com Mais Locações</h2>
                <table>
                    <thead>
                    <tr>
                        <th>Título do Livro</th>
                        <th>Total de Locações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${top10Livros}" var="locacao">
                        <tr>
                            <td>${locacao?.key}</td>
                            <td>${locacao?.value?.size()}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </section>
    </div>
</div>
</body>
</html>
