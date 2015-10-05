<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div>
    <nav class="navbar navbar-default navbar-fixed-top" style="margin-bottom:50px" role="navigation">
        <div class="container-fluid" style="border-color:#FF00FF; color:white">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a style="font-weight: bold;" class="navbar-brand" href="#">E-SECURED</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="color:#FFFFFF">
                <ul class="nav navbar-nav">
                    <sec:authorize  ifAllGranted="ROLE_ADMIN,ROLE_SUPER_ADMIN">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-weight: bold;"><span
                                    class="glyphicon glyphicon-cog"></span> Administration <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li>

                                    <a href="userslist" style="font-weight: bold;"><span class="glyphicon glyphicon-user"></span> Liste des utilisateurs</a>
                                    <a href="${pageContext.request.contextPath}" style="font-weight: bold;"><span class="glyphicon glyphicon-transfer"></span> Liste des Transactions</a>
                                </li>

                            </ul>
                        </li>

                    </sec:authorize>

<!--                    <li><a data-target="#" style="font-weight: bold;" data-toggle="pill" href="#accounts">Services</a></li>

                    <li><a data-target="#" style="font-weight: bold;"data-toggle="pill" href="#faq">FAQ</a></li>-->
                </ul>
                <sec:authorize  ifAllGranted="ROLE_ADMIN,ROLE_SUPER_ADMIN">
                    <div class="btn-group">
                        <button type="button"
                                style="height: 32px;  margin-top:7px; background:#5EB6DD;color: #ffffff;border-radius: 6px;font-size: 13px"
                                class="btn btn-default" data-title="CreateUser"
                                data-toggle="modal" data-target="#CreateUser">Nouveau Utilisateur
                        </button>
                        <!-- onClick="newUserDialog.show();" -->
                    </div>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" style="font-weight: bold;" class="dropdown-toggle" data-toggle="dropdown"><span
                                    class="glyphicon glyphicon-user"style="font-weight: bold;" ></span> Profile <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">

                                <li>
                                    <a href="#" style="font-weight: bold;" data-toggle="modal" class="showProfil " data-id="<sec:authentication property="principal.username" />" ><span class="glyphicon glyphicon-cog"></span> Profile</a>
                                </li>
                                <sec:authorize  ifAllGranted="ROLE_ADMIN,ROLE_SUPER_ADMIN">
                                    <li><a href="${pageContext.request.contextPath}/settings" style="font-weight: bold;"><span class="glyphicon glyphicon-wrench"></span> Parametres</a></li>
                                </sec:authorize>
                                <li class="divider"></li>
                                <li><a href="<c:url value="/j_spring_security_logout" />" style="font-weight: bold;"> <span
                                            class="glyphicon glyphicon-off"></span> Se deconnecter</a></li>

                            </ul>
                        </li>
                    </ul>
                </sec:authorize>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    <!-- contenu des modals deplacés vers htmlLayout.jsp-->
    </nav>

</div>
