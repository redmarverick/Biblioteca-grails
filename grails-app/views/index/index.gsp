<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>
<body>
<content tag="nav">
</content>

<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="book.png" class="grails-logo"/>
    </div>
</div>

<div id="content" role="main">
    <div class="container">
        <section class="colset-2-its">
            <h1 class="centered">Bem-vindo a biblioteca</h1>

            <h3 class="centered">
                Neste aplicativo você pode alugar livros!
            </h3>
            <br>
            <div id="controllers" role="navigation">
                <h2>Menu de opções:</h2>
                <ul style="display: block">
                    <g:link class="btn btn-info" style="display: block; margin-bottom: 4px;" controller="autor">Autores</g:link>
                    <g:link class="btn btn-info" style="display: block; margin-bottom: 4px;" controller="livro">Livros</g:link>
                    <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <g:link class="btn btn-info" style="display: block; margin-bottom: 4px;" controller="cliente">Clientes</g:link>
                    <g:link class="btn btn-info" style="display: block; margin-bottom: 4px;" controller="estatisticas">Estatísticas</g:link>
                    <g:link class="btn btn-info" style="display: block; margin-bottom: 4px;" controller="locacao">Locações</g:link>
                    </sec:ifAnyGranted>
                </ul>
            </div>
        </section>
    </div>
</div>

</body>
</html>
