<%@ page import="biblioteca.grails.Role" %>
<html>
<head>
    <meta name="layout" content="${gspLayout ?: 'main'}"/>
    <title>Register</title>
</head>

<body>
<div class="row">
    <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
            <div class="card-body">
                <h5 class="card-title text-center">Registre-se aqui</h5>
                <g:if test='${flash.message}'>
                    <div class="alert alert-danger" role="alert">${flash.message}</div>
                </g:if>
                <form class="form-signin" action="register" method="POST" id="loginForm" autocomplete="off">
                    <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <div class="form-group">
                        <label for="role">Role</label>
                        <g:select class="form-control" name="role.id"
                                  from="${Role.list()}"
                                  optionKey="id" />
                    </div>
                    </sec:ifAnyGranted>
                    <div class="form-group">
                        <label for="username">Usuário</label>
                        <input type="text" placeholder="Seu usuário" class="form-control" name="username" id="username" autocapitalize="none"/>
                    </div>

                    <div class="form-group">
                        <label for="password">Senha</label>
                        <input type="password" placeholder="Sua senha" class="form-control" name="password" id="password"/>
                    </div>

                    <div class="form-group">
                        <label for="password">Re-insira a Senha</label>
                        <input type="password" placeholder="Re-insira sua senha" class="form-control" name="repassword" id="repassword"/>
                    </div>

                    <div class="form-group">
                        <label for="username">Nome Completo</label>
                        <input type="text" placeholder="Seu nome completo" class="form-control" name="fullname" id="fullname" autocapitalize="none"/>
                    </div>

                    <button id="submit" class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Register</button>
                    <hr class="my-4">
                    <p>Já tem uma conta? <g:link controller="login" action="auth">Login</g:link></p>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function(event) {
        document.forms['loginForm'].elements['username'].focus();
    });
</script>
</body>
</html>