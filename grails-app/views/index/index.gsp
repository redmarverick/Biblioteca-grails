<sec:ifAnyGranted roles="ROLE_USER,ROLE_ADMIN">
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">Grails App</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="statusDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Application Status
                    </a>
                    <div class="dropdown-menu" aria-labelledby="statusDropdown">
                        <!-- Status Dropdown Content -->
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="artefactsDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Artefacts
                    </a>
                    <div class="dropdown-menu" aria-labelledby="artefactsDropdown">
                        <!-- Artefacts Dropdown Content -->
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="pluginsDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Installed Plugins
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="pluginsDropdown">
                        <!-- Plugins Dropdown Content -->
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-6 text-center">
            <img src="grails-cupsonly-logo-white.svg" alt="Grails Logo" class="img-fluid">
            <h1 class="mt-4">Welcome to Grails</h1>
            <p class="lead">
                Congratulations, you have successfully started your first Grails application! At the moment
                this is the default page, feel free to modify it to either redirect to a controller or display
                whatever content you may choose. Below is a list of controllers that are currently deployed in
                this application, click on each to execute its default action:
            </p>
        </div>
    </div>
    <div class="row justify-content-center mt-5">
        <div class="col-md-8">
            <h2>Available Controllers:</h2>
            <ul class="list-unstyled">
                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                    <li class="mb-2">
                        <a href="${createLink(controller: c.logicalPropertyName)}">${c.fullName}</a>
                    </li>
                </g:each>
            </ul>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
</sec:ifAnyGranted>