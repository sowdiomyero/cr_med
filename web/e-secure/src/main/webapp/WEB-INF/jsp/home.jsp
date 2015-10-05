<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>

    <sec:authorize access="isAuthenticated()">
        <div class="container">
            <div class="row">
                <h3 class="text-left" style="font-weight: bold;">
                    GESTION DES CLIENTS E-SECURED</h3>
                <div class="container">
                    <div class="row">
                        <div class="tabbable">
                            <ul class="nav nav-pills">
                                <li class="active"><a href="#panel_transactions" data-toggle="tab" style="font-weight: bold;">Historique Transactions <span class="badge">${nb_transactions}</span></a></li>
                                <li ><a href="#panel_echoue" data-toggle="tab" style="font-weight: bold;">Transactions Echouées <span class="badge">${nb_nv_transactions}</span></a></li>
                            </ul>
                            <div class="tab-content">
                                <div id="panel_transactions" class="tab-pane active">
                                    <div>
                                        <h4 style="font-weight: bold;">Liste des Transactions</h4>
                                        <div style="padding-bottom: 5px; margin-bottom: 5px">
                                            <span class="label label-default" style="margin: 5px; padding: 5px; color:#ac2925"><a href="${pageContext.request.contextPath}/download/rapport.csv">Download CSV</a> </span>
                                            <span class="label label-default" style="margin: 5px; padding: 5px; color:#ac2925"><a href="${pageContext.request.contextPath}/download/rapport.pdf">Download PDF</a> </span>
                                        </div>
                                        <div class="table-responsive" >
                                            <table id="mytable" class=" tableauTransactions display"  border="0">
                                                <thead>
                                                   <tr>
                                                   <th colspan="3" align="center" style=" font-weight: bold; text-align: center; background-color: #5EB6DD;">Client</th>

                                                    <th colspan="2" style="font-weight: bold; text-align: center;background-color: #C4E6F5;" >Commercant</th>

                                                    <th colspan="2" style="font-weight: bold; text-align: center;background-color: #5EB6DD;">Transaction</th>

                                                    <th  style="font-weight: bold;text-align: center;background-color: #C4E6F5;">Action</th>
                                                    </tr>

                                                    <tr  style="background-color: green;">
                                                        <td style="font-weight: bold; background-color: skyblue">Nom </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Prénom </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Numero Carte </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Nom </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Site Web</td>

                                                        <td style="font-weight: bold; background-color: skyblue">Montant</td>
                                                        <td style="font-weight: bold; background-color: skyblue">Date Transaction</td>

                                                        <td style="font-weight: bold; background-color: skyblue">Visualiser</td>
                                                    </tr> 
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${transactions}" var="transaction" >
                                                        <tr>
                                                            <td><c:out value="${transaction.carteBancaire.client.nomClient}"></c:out></td>
                                                            <td><c:out value="${transaction.carteBancaire.client.prenomClient}"></c:out></td>
                                                            <td><c:out value="${transaction.carteBancaire.numCarte}"></c:out></td>
                                                            <td><c:out value="${transaction.nomCommercant}"></c:out></td>
                                                            <td><c:out value="${transaction.siteWebCommercant}"></c:out></td>
                                                            <td><c:out value="${transaction.montantBrute}"></c:out></td>
                                                            <td><c:out value="${transaction.dateTransaction}"></c:out></td>
                                                            <td>                                                          
                                                                <button title="Visualiser transaction" style="background:#5EB6DD;" class="detailTransaction btn btn-primary btn-xs" data-id="${transaction.idTransaction}"  data-title="Edit"
                                                                        data-toggle="modal">
                                                                    <span class="glyphicon glyphicon-eye-open"></span>
                                                                </button>
                                                            </td>
                                                            </tr>
                                                    </c:forEach>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <!-- Tab Transactions ayant Echouees -->
                                <div id="panel_echoue" class="tab-pane">
                                    <div >
                                        <h4 style="font-weight: bold;">Transactions ayant Echouées </h4>
                                        <div style="padding-bottom: 5px; margin-bottom: 5px">
                                            <span class="label label-success" style="margin: 5px; padding: 5px; color:#ac2925"><a href="${pageContext.request.contextPath}/download/rapport_failed.csv">Download CSV</a> </span>
                                            <span class="label label-default" style=" font-weight: bold; margin: 5px; padding: 5px; color:#ac2925 "><a href="${pageContext.request.contextPath}/download/rapport_failed.pdf">Download PDF</a> </span>
                                        </div>
                                        <div class="table-responsive" >
                                            <table id="mytable_nv" class=" tableauTransactions table table-bordred table-striped"  border="0">
                                                <thead>
                                                    <tr>
                                                        <th colspan="3" align="center" style=" font-weight: bold; text-align: center; background-color: #5EB6DD;">Client</th>

                                                        <th colspan="2" style="font-weight: bold; text-align: center;background-color: #C4E6F5;" >Commercant</th>

                                                        <th colspan="2" style="font-weight: bold; text-align: center;background-color: #5EB6DD;">Transaction</th>

                                                        <th  style="font-weight: bold; text-align: center;background-color: #C4E6F5;">Action</th>
                                                    </tr>
                                                    <tr  style="background-color: green;">
                                                        <td style="font-weight: bold; background-color: skyblue">Nom </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Prénom </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Numero Carte </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Nom </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Site Web</td>

                                                        <td style="font-weight: bold; background-color: skyblue">Montant</td>
                                                        <td style="font-weight: bold; background-color: skyblue">Date Transaction</td>

                                                        <td style="font-weight: bold; background-color: skyblue">Visualiser</td>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${nv_transactions}" var="transaction_nv">
                                                        <tr>
                                                            <td><c:out value="${transaction_nv.carteBancaire.client.nomClient}"></c:out></td>
                                                            <td><c:out value="${transaction_nv.carteBancaire.client.prenomClient}"></c:out></td>
                                                            <td><c:out value="${transaction_nv.carteBancaire.numCarte}"></c:out></td>
                                                            <td><c:out value="${transaction_nv.nomCommercant}"></c:out></td>
                                                            <td><c:out value="${transaction_nv.siteWebCommercant}"></c:out></td>
                                                            <td><c:out value="${transaction_nv.montantBrute}"></c:out></td>
                                                            <td><c:out value="${transaction_nv.dateTransaction}"></c:out></td>
                                                                <td>
                                                                    <div data-placement="top" data-toggle="tooltip">
                                                                        <button title="Visualiser transaction" style="background:#5EB6DD;"
                                                                    class="detailTransaction btn btn-primary btn-xs" data-id="${transaction_nv.idTransaction}"  data-title="Edit"
                                                                                data-toggle="modal">
                                                                            <span class="glyphicon glyphicon-eye-open"></span>
                                                                        </button>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- /.tab-content -->
                        </div><!-- /.tabbable -->
                    </div>
                </div>
                <!-- #######################################################  -->

                <div class="modal fade modal-admin" id="edit" tabindex="-1" role="dialog"
                     aria-labelledby="edit" aria-hidden="true"  >
                    <div class="modal-dialog">
                        <div class="modal-content" style="width:900px; margin-left: -150px">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </button>
                                <div style="font-weight: bold; margin-bottom: 5px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold;">DETAILS TRANSACTION</div>
                            </div>
                            <div class="modal-body" id="edit_user">
                                <div class="row"> 

                                    <div class="col-md-6" id="leftCol">
                                        <div class="panel panel-info">
                                            <div class="panel-heading">
                                                <h3 style="font-weight: bold;" class="panel-title">Info Client</h3>
                                            </div>
                                            <div class="panel-body" id ="edit_client">

                                                Prénom : <input class="form-control " id="tr_prenomClient" type="text" placeholder="" readonly="true">
                                                Nom : <input class="form-control "id="tr_nomClient" type="text" placeholder="" readonly="true">
                                                Numéro Carte : <input class="form-control " id="tr_numCarte" type="text" placeholder="" readonly="true">
                                                Numéro Compte : <input class="form-control " id="tr_numCompte" type="text" placeholder="" readonly="true">
                                                E-mail : <input class="form-control " id="tr_email" type="text" placeholder="" readonly="true">
                                                Adresse : <input class="form-control " id="tr_adresseClient" type="text" placeholder="" readonly="true">                                          
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6" id="leftCol">
                                        <div class="row">
                                            <div class="col-md-12" id="leftCol">

                                                <div class="panel panel-info">
                                                    <div class="panel-heading">
                                                        <h3 style="font-weight: bold;" class="panel-title">Info Commercant</h3>
                                                    </div>
                                                    <div class="panel-body" id="edit_commercant">
                                                        Nom Commercant : <input class="form-control " id="tr_nomCommercant" type="text" placeholder="" readonly="true">
                                                        Site web : <input class="form-control " id="tr_website" type="text" placeholder="" readonly="true">
                                                        Adresse : <input class="form-control " id="tr_adresseCommercant" type="text" placeholder="" readonly="true">                                                       
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12" id="leftCol">
                                                <div class="panel panel-info">
                                                    <div class="panel-heading">
                                                        <h3 style="font-weight: bold;" class="panel-title">Info Transaction</h3>
                                                    </div>
                                                    <div class="panel-body" id=""edit_transaction>

                                                        Montant Transaction : <input class="form-control " id="tr_montantTransaction" type="text" placeholder="" readonly="true">
                                                        Date Transaction : <input class="form-control " id="tr_dateTransaction" type="text" placeholder="" readonly="true">
                                                    </div>
                                                </div></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">                                 
                                    <div class="col-md-12" id="leftCol">
                                        <div class="panel panel-info">
                                            <div class="panel-heading">
                                                <h3 style="font-weight: bold;" class="panel-title">Historique transactions</h3>
                                            </div>
                                            <div class="panel-body" id="histo_transactions">

                                                <table  class=" transactionsClient table table-bordred table-striped display">
                                                    <thead>
                                                        <tr>
                                                            <th>Nom</th>
                                                            <th>Prénom</th>
                                                            <th class="text-center">Date</th>
                                                            <th class="text-center">Site web</th>
                                                            <th class="text-center">Montant</th>
                                                            <th class="text-center">Etat</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="listeTransactionsClient">
                                                
                                                    </tbody>
                                                </table>
                                            </div> 
                                        </div> 
                                    </div>                                                             
                                </div>

                            </div>
                            <div class="modal-footer ">

                            </div>
                            <!-- /.modal-content -->
                        </div>
                        <!-- /.modal-dialog -->
                    </div>
                </div>
            </div>
            <!-- container-->
        </sec:authorize>


    </div>

