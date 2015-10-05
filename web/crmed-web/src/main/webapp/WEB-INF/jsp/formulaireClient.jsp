<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        <div class="container" id="infoFormulaireId">
            
            <form commandName="infoFormulaireForm" action="${pageContext.request.contextPath}/clientform/verif" method="post" id="verifInfoFormulaireId">
            <div class="row">
                <h1 class="text-center"><strong>SECURISATION PAIEMENT ELECTRONIQUE </strong></h1>

                    <hr>

                    <div class="col-sm-5 pull-right">
                        <h4 style="font-weight: bold;" >INFORMATIONS SUR LA TRANSACTION</h4>
                        <div class="panel panel-default">

                            <div class="panel-heading">
                                <address>
                                    <strong>Nom Commerçant :</strong><br> <span
                                        id="commercantid">
                                         <label for="commercantid"  style="text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: normal;">${infoFormulaireForm.nomCommercant}</label>
                                    </span> 
                                </address>
                                     <address>
                                    <strong>Site web Commerçant :</strong><br> <span
                                        id="sitewebid">
                                         <label for="sitewebid"  style="text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: normal;">${infoFormulaireForm.siteWebCommercant}</label>
                                    </span> 
                                </address>
                                <address>
                                   <strong>Montant de la transaction :</strong><br> <span
                                        id="montantid">
                                         <label for="montantid"  style="text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: normal;">${infoFormulaireForm.montantTransaction}</label>
                                    </span>
                                </address>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-sm-7" >
                        <h4 style="font-weight: bold;">FORMULAIRE SIGNATAIRE</h4>
                        <div class="panel panel-default">
                            <div style="margin-bottom: 2px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold;" id="verifAdresseContent"></div>
                            <div class="panel-heading" >
                                <div style="margin-bottom: 2px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold;" id="confirmationContent">
                                    
                                </div>
                                <div><label  style="margin-bottom: 2px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold; color: red"> <p id="messageNotification"></p></label></div>
                                    <div class="panel-heading" >
                                        
                                        <div class="col-xs-10 form-group has-feedback" id="divNomVerif">
                                            <div><label  style="margin-bottom: 2px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold; color: red"> <p id="nomverifContent"></p></label></div>
                                            <label for="nomid"  style="margin-bottom: 2px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold;">Nom Signataire</label>
                                            <input class="form-control" id="nomid" name="nomClient" placeholder="****** " required="required" type="password" size="100" value="${infoFormulaireForm.nomClient}" />                                          
                                            <input class="form-control" id="nomverif" name="nomClientSaisie" placeholder="Nom Client" required="" type="text" size="100" style="margin-top: 4px" value="${infoFormulaireForm.nomClientSaisie}" aria-describedby="inputError2Status">
                                        </div>
                                    </div>
                                    <div class="panel-heading" >
                                        <div class="col-xs-10 form-group has-feedback" id="divPrenomverif">
                                            <div><label  style="margin-bottom: 2px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold; color: red"> <p id="prenomverifContent"></p></label></div>
                                             <label for="prenomid" class="form-signin-heading" style="margin-bottom: 2px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold;">Prénom Signataire</label>
                                            <input class="form-control" id="prenomid" name="prenomClient" placeholder="****** " required="required" type="password" size="100" value="${infoFormulaireForm.prenomClient}" />
                                            <input class="form-control" id="prenomverif" name="prenomClientSaisie" placeholder="Prénom Client" required="" type="text" size="100" style="margin-top: 4px" value="${infoFormulaireForm.prenomClientSaisie}" />
                                        </div>
                                    </div>
                                    <div class="panel-heading" >
                                        <div class="col-xs-10 form-group has-feedback" id="divCodepostalverif">
                                            <div><label  style="margin-bottom: 2px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold; color: red"> <p id="codepostalverifContent"></p></label></div>
                                             <label for="adressepostaleid" class="form-signin-heading" style="margin-bottom: 2px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold;">Code Postale</label>
                                            <input class="form-control" id="adressepostaleid" name="codePostal" placeholder="****** " required="" type="password" size="100" value="${infoFormulaireForm.codePostal}" />
                                            <input class="form-control" id="codepostalverif" name="codePostalSaisie" placeholder="Adresse Postale" required="" type="text" size="100" style="margin-top: 4px" value="${infoFormulaireForm.codePostalSaisie}" />
                                        </div>
                                    </div>

                                    
                                   

                                    <div class="panel-heading" >    
                                        <div class="col-xs-10 form-group has-feedback" id="divEmailverif" >
                                            <div><label  style="margin-bottom: 2px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold; color: red"> <p id="emailverifContent"></p></label></div>
                                             <label for="emailid" class="form-signin-heading" style="margin-bottom: 2px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold;">Adresse Email</label>
                                             <input class="form-control" id="emailid" name="email"  required="" type="hidden" size="100" value="${infoFormulaireForm.email}" style="vertical-align: middle" >
                                            <input class="form-control" id="mailid" name="emailLeftCrypt" placeholder="xxxx@xxxxx.xxx" required="" type="text" size="100" value="${infoFormulaireForm.emailLeftCrypt}" >
                                            <input class="form-control" id="emailverif" name="emailSaisie" placeholder="Adresse Email" required="" type="text" size="100" value="${infoFormulaireForm.emailSaisie}"  />
                                          
                                        </div>
                                    </div>
                                    
                                  
                                 
                                    <div class="panel-heading" >
                                        <div class="col-xs-10 form-group has-feedback" id="divVilleverif">
                                            <div><label  style="margin-bottom: 2px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold; color: red"> <p id="villeverifContent"></p></label></div>
                                             <label for="villeid" class="" style="margin-bottom: 2px; text-shadow: 0.1em 0.1em 0.1em  gray; font-weight: bold;">Ville</label>
                                            <input class="form-control" id="villeid" name="ville" placeholder="****** " required="" type="password" size="100" value="${infoFormulaireForm.ville}" />
                                            <input class="form-control" id="villeverif" name="villeSaisie" placeholder="Ville" required="" type="text" size="100" value="${infoFormulaireForm.villeSaisie}" style="margin-top: 4px; margin-bottom: 10px" />
                                        </div>
                                    </div>
                                    
                                    
                                    <div class="row form-group" >
                                        <div class="col-xs-10">
                                                <input class="form-control" id="idMessage" name="msg" type="hidden" size="100" value="${infoFormulaireForm.msg}" />
                                                <input class="form-control" id="idResultat" name="resultat" type="hidden" size="100" value="${infoFormulaireForm.resultat}" />
                                                <input class="form-control" id="nbVerif" name="nbVerif" type="hidden" size="100" value="${infoFormulaireForm.nbVerif}" />
                                                <input class="form-control" id="nbTentative" name="nbTentativeAutorise" type="hidden" size="100" value="${infoFormulaireForm.nbTentativeAutorise}" />
                                              <button type="submit" id="validerTransaction" onClick="" class="btn btn-info" value="submit">Valider</button>
                                              <button type="button" id="annulerTransaction" onClick=""  data-dismiss="modal" class="btn btn-warning">Annuler</button>
                                        </div>
                                 
                            </div>

                        </div>
                        <!--/row-->
                    </div>
            </div>
          </form>

        </div>                           
        <!-- container-->
 
