<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAnonymous()">
    <h3 style="font-weight: bold;">Acces a E-SECURED</h3>
    <div id="accordion" class="panel-group">

        <div class="panel panel-default">

            <div class="panel-heading">
                <form class="form-signin" role="form"
                      action='<c:url value='j_spring_security_check' />' method="post">
                    <h4 style="font-weight: bold;" class="form-signin-heading">Authentification</h4>
                    <input name="j_username" type="text" class="form-control"
                           placeholder="login" required autofocus><br /> <input
                           name="j_password" type="password" class="form-control"
                           placeholder="Password" required> <label class="checkbox">
                        <input style="font-weight: bold;" type="checkbox" value="remember-me"> Garder la
                        session active
                    </label>
                    <div class="label">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Connecter
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <c:if test="${not empty error}">
        <div>
            <font color="red" style="font-weight: bold;"><br /> Login ou mot de passe invalide</font>
        </div>
    </c:if>
</sec:authorize>