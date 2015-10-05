$(document).ready(function() {
    /*Partie de home.JSP */
      var infoProfil;
    $('#mytable').dataTable({
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Transactions par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !!",
            sInfo: "Afficher page _PAGE_ of _PAGES_",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });
    $('#mytable_nv').dataTable({
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Transactions par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !!",
            sInfo: "Afficher page _PAGE_ of _PAGES_",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });
    /*La partie de l'edition d'un utilisateurs des transactions non valides */
    $('.tableauTransactions').on('click', '.detailTransaction', function() {
        var idTransaction = $(this).attr('data-id');
        oTable = $('.transactionsClient').dataTable();
        oTable.fnDestroy();
        $.ajax({
            url: 'transaction/get',
            type: 'get',
            data: 'idTransaction=' + idTransaction,
            success: function(response) {
                //alert('response received'+response.montant);
                //Informations du client provenant du serveur

                $('#tr_prenomClient').attr('value', response.prenomClient);
                $('#tr_nomClient').attr('value', response.nomClient);
                $('#tr_numCarte').attr('value', response.numCarte);
                $('#tr_numCompte').attr('value', response.numCompte);
                $('#tr_email').attr('value', response.email);
                $('#tr_adresseClient').attr('value', response.adresseClient);
                //Informations de la transaction provenant du serveur                
                $('#tr_nomCommercant').attr('value', response.nomCommercant);
                $('#tr_website').attr('value', response.siteWebCommercant);
                $('#tr_adresseCommercant').attr('value', response.adresseCommercant);
                $('#tr_montantTransaction').attr('value', response.montantTransaction);
                $('#tr_dateTransaction').attr('value', response.dateTransaction);
                var listeTransactions = response.transactions;
                $('#listeTransactionsClient').html("");
                for (var i = 0; i < listeTransactions.length; i++)
                {
                    var trContent = "<tr><td>" + listeTransactions[i].nomClient + "</td>";
                    trContent += "<td>" + listeTransactions[i].prenomClient + "</td>";
                    trContent += "<td>" + listeTransactions[i].dateTransaction + "</td>";
                    trContent += "<td>" + listeTransactions[i].siteWebCommercant + "</td>";
                    trContent += "<td>" + listeTransactions[i].montantTransaction + "</td>";
                    trContent += "<td>" + listeTransactions[i].etatTransaction + "</td>";
                    trContent += "</tr>";
                    $('#listeTransactionsClient').append(trContent);
                    trContent = null;
                }
                $('.transactionsClient').dataTable({
                    oLanguage: {
                        sLengthMenu: "Afficher: _MENU_ Transactions par page ",
                        sSearch: "Rechercher : ",
                        sZeroRecords: "Aucune valeur trouvee !!",
                        sInfo: "Afficher page _PAGE_ of _PAGES_",
                        oPaginate: {
                            sFirst: "Premier",
                            sPrevious: "Precedent",
                            sNext: "Suivant",
                            sLast: "Dernier"
                        }
                    },
                    "pagingType": "full_numbers"
                });
                listeTransactions = null;
                $('#edit').modal();
                // ajax success callback
            }, error: function(response) {
                //alert('Une exception est survenue au niveau du Serveur ' + response);
                $.notify("Une exception est survenue au niveau du Serveur : "+response, "error");
            }
        });
    });
    /*Fin de la partien home.jsp*/
/*==============================================AJOUT D'UN NOUVEAU SCRIPT====================================================*/
    /*Début partie pour setting.jsp*/
    $('#messagerieForm').submit(function(e) {
        var frm = $('#messagerieForm');
        e.preventDefault();
        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    //alert("Response: Name: "+callback.nom+"  Prenom: "+callback.prenom+"  Email: "+callback.email+"  Role: "+callback.role);
                    //$(this).html("Action effectuee avec succes");
                    $.notify("Action effectuee avec succes", "success");
                    //$('#settingsMessageSuccess').html(callback.msg);
                } else {
                    $('#settingsMessageSuccess').html(callback.msg);
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                $('#settingsMessageFailed').html("Une erreur est survenue pendant l'ajout des Parametres de messagerie");
                $.notify("Une erreur est survenue pendant l'ajout des Parametres de messagerie ", "error");
            }
        });
    });
    /*BANQUE*/

    $('#banqueForm').submit(function(e) {
        var frm = $('#banqueForm');
        e.preventDefault();
        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    //alert("Response: Name: "+callback.nom+"  Prenom: "+callback.prenom+"  Email: "+callback.email+"  Role: "+callback.role);
                    //$(this).html("Action effectuee avec succes");
                    $.notify("Action effectuee avec succes", "success");
                    //$('#banqueMessageSuccess').html(callback.msg);
                } else {
                    //$('#banqueMessageSuccess').html(callback.msg);
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                //$('#banqueMessageFailed').html("Une erreur est survenue pendant l'ajout des Parametres de la Banque");
                $.notify("Une erreur est survenue pendant l'ajout des Parametres de la Banque ", "error");
            }
        });
    });
    /* GENERATION DES RAPPORTS*/

    $('#rapportForm').submit(function(e) {
        var frm = $('#rapportForm');
        e.preventDefault();
        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    //alert("Response: Name: "+callback.nom+"  Prenom: "+callback.prenom+"  Email: "+callback.email+"  Role: "+callback.role);
                    //$(this).html("Action effectuee avec succes");
                    $.notify("Action effectuee avec succes", "success");
                    //$('#rapportMessageSuccess').html(callback.msg);
                } else {
                    //$('#rapportMessageSuccess').html(callback.msg);
                    $.notify(callback.msg, "warn");
                }
            },
            error: function() {
                //$('#rapportMessageSuccess').html("Une erreur est survenue pendant l'ajout des Parametres des Rapports");
                $.notify("Une erreur est survenue pendant l'ajout des Parametres des Rapports ", "error");
            }
        });
    });
    $("#genererRapports").click(function() {
        $("#toggleDemo").collapse('toggle');
    });
    /*Fin de la partien setting.jsp*/
/*==============================================AJOUT D'UN NOUVEAU SCRIPT====================================================*/
    /*Début partie users.jsp*/
    var loginUser = null;
    $('#table_utilisateurs').dataTable({
        oLanguage: {
            sLengthMenu: "Afficher: _MENU_ Transactions par page ",
            sSearch: "Rechercher : ",
            sZeroRecords: "Aucune valeur trouvee !!",
            sInfo: "Afficher page _PAGE_ of _PAGES_",
            oPaginate: {
                sFirst: "Premier",
                sPrevious: "Precedent",
                sNext: "Suivant",
                sLast: "Dernier"
            }
        },
        "pagingType": "full_numbers"
    });
    function deleteUserById(idUser) {

        $.ajax({
            url: 'user/deleteUser?idUser=' + idUser,
            type: 'delete',
            success: function(response) {
                if (response.resultat === 200) {

                    //$('#confirmationContent').html(response.msg);
                    $('#' + idUser).remove();
                    //$('#confirmationModal').modal({backdrop: 'static', keyboard: false});
                    $.notify(response.msg,"success");
                } else {

                    //$('#confirmationContent').html(response.msg);
                    //$('#confirmationModal').modal({backdrop: 'static', keyboard: false});
                    $.notify(response.msg,"error");
                }
            }, error: function(response) {

                //$('#confirmationContent').html(response.msg);
                //$('#confirmationModal').modal({backdrop: 'static', keyboard: false});
                $.notify(response.msg,"error");
            }
        });
    }
    $('#table_utilisateurs').on('click', '.deleteUser', function(e) {
        e.preventDefault();
        var id = $(this).data('id');
        //confirmDelete(id);
        $('#deleteConfirmationModal').modal({backdrop: 'static', keyboard: false})
                .one('click', '#okDeletionModalButton', function() {
                    $('#deleteConfirmationModal').hide();
                    deleteUserById(id);
                    // recharger la page avec un time 1700 ms
                    setTimeout(function() {
                        location.reload();
                    }, 1700);
                });
    });
    //Code de chargement d'un utilisateur selectionné sur la liste des utilisateurs
    $('#table_utilisateurs').on('click', '.showUser', function(e) {
        e.preventDefault();
        $('#userEditSuccess').css('display', 'none');
        $('#userEditFailed').css('display', 'none');
        var idUser = $(this).attr('data-id');
        $('#viewUserModal').modal();
        $.ajax({
            url: 'user/getUser',
            type: 'get',
            data: 'idUser=' + idUser,
            success: function(response) {
                //Informations du client provenant du serveur  
                $('#editNomInput').val(response.nom);
                $('#editPrenomInput').val(response.prenom);
                $('#editEmailInput').val(response.email);
                $('#editTelInput').val(response.telephone);
                $('#editLoginInput').val(response.login);
            }
            , error: function(response) {
                alert('ajax failed' + response);
            }
        });
    });
    //Partie pour la modification d'un utilisateur
    $('#editUserFormulaire').submit(function(e) {
        var frm = $('#editUserFormulaire');
        e.preventDefault();
        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                if (callback.resultat === 200) {
                    $('#userEditSuccess').html(callback.msg);
                    hideModal('viewUserModal');
                    //$('#viewUserModal').modal('hide');  // masquer le modal après modification du user
                    // masquer le modal
                    $.notify(callback.msg ,"success");
                      setTimeout(function() {
                        location.reload();
                    }, 1500);
                }
            },
            error: function() {
                $('#userMessageFailed').html("Une erreur est survenue pendant l'ajout de l'utilisateur ");
                $('#userMessageFailed').css('display', 'block');
                $.notify("Une erreur est survenue pendant l'ajout de l'utilisateur" ,"error");
            }
        });
    });
    function regenererPassword(loginUser)
    {
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: 'get',
            url: 'user/regenerePassword',
            dataType: 'json',
            data: 'loginUser=' + loginUser,
            success: function(callback) {
                if (callback.resultat === 200)
                    $('#userEditSuccess').html(callback.msg);
                    $.notify(callback.msg ,"success");
            },
            error: function() {
                $('#userEditFailed').html("Une erreur est survenue pendant l'ajout de l'utilisateur");
                $.notify("Une erreur est survenue pendant l'ajout de l'utilisateur" ,"error");
            }
        });
    }
    $('#regenerePassword').click(function(e) {
        e.preventDefault();
        loginUser = $('#editLoginInput').val();
        $('#regenererConfirmationModal').show();
    });
    $('#okRegenererModalButton').click(function() {
        regenererPassword(loginUser);
       //$('#regenererConfirmationModal').hide();
    });
    /*Fin partie users.jsp*/
/*==============================================AJOUT D'UN NOUVEAU SCRIPT====================================================*/
    /*Début partie FormulaireClient.jsp*/
    function closeWindow() {
        netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserWrite");
        window.open('', '_self');
        window.close();
    }
    function hideModal(idModal) {
        $('#'+idModal).modal('hide');
    }
   // var message = $('#idMessage').val();
    var reponse = $('#idResultat').val();
    //$('#idMessage').html(message);
    if(reponse==="300"){
        $.notify("Cette demande a été déjà validée." , "error") ;
        $('#validerTransaction').addClass('disabled');
        $('#annulerTransaction').addClass('disabled');
    }
    if(reponse==="700"){
        $.notify("Cette demande a été déjà annulée." , "error");
        $('#validerTransaction').addClass('disabled');
        $('#annulerTransaction').addClass('disabled');
    }
    if(reponse==="800"){
        $.notify("Cette demande a été déjà acceptée." , "error") ;
        $('#validerTransaction').addClass('disabled');
        $('#annulerTransaction').addClass('disabled');
    }
    $('#verifInfoFormulaireId').submit(function(e) {
         $('#verifAdresseContent').html("");
        var frm = $('#verifInfoFormulaireId');
        e.preventDefault();
        var data = {};
        //Gather Data also remove undefined keys(buttons)
        $.each(this, function(i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: frm.attr('method'),
            url: frm.attr('action'),
            dataType: 'json',
            data: JSON.stringify(data),
            success: function(callback) {
                $('#confirmationContent').html(callback.msg);
                $.notify(callback.msg , "success") ;
                if (callback.resultat === 200) {
                    $.notify("La validation des vos informations a réussie. Vous allez être redirigés vers la page de confirmation de votre banque.", "success") ;
                    //$('#confirmationContent').html("La validation des vos informations a réussie. Vous allez être redirigés vers la page de confirmation de votre banque.");
                    setTimeout(function() {
                        window.close();
                    },
                            6000
                            );
                    // closeWindow();
                } else {
                    $('#nbVerif').val(callback.nbVerif);
                    $('#nbTentativeAutorise').val(callback.nbTentativeAutorise);
                    
                    if (callback.nbVerif <= callback.nbTentativeAutorise) {
                        $('#confirmationContent').addClass('error-validation');
                        $('#confirmationContent').html(callback.msg);
                        $('#verifAdresseContent').html(callback.msgAdress);
                        if (callback.msgNomClientSaisie !== null) {
                            $('#nomverif').val("");
                            $('#nomverif').after('<span class="form-control-feedback" aria-hidden="true"></span>');
                            $('#divNomVerif').addClass('has-error');
                        }

                        if (callback.msgPrenomClientSaisie !== null) {
                            $('#prenomverif').val("");
                            $('#prenomverif').after('<span class="form-control-feedback" aria-hidden="true"></span>');
                            $('#divPrenomverif').addClass('has-error');
                        }

                        if (callback.msgemailSaisie !== null) {
                            $('#emailverif').val("");
                            $('#emailverif').after('<span class="form-control-feedback" aria-hidden="true"></span>');
                            $('#divEmailverif').addClass('has-error');
                        }

                        if (callback.msgVilleSaisie !== null) {
                            $('#villeverif').val("");
                            $('#villeverif').after('<span class="form-control-feedback" aria-hidden="true"></span>');
                            $('#divVilleverif').addClass('has-error');
                        }
                        if (callback.msgCodePostalSaisie !== null) {
                            $('#codepostalverif').val("");
                            $('#codepostalverif').after('<span class="form-control-feedback" aria-hidden="true"></span>');
                            $('#divCodepostalverif').addClass('has-error');
                        }
                    }
                    else {
                        $('#confirmationContent').html("La validation des vos informations a échouée, Vous avez eppuisé tous vos tentatives. Pour des raisons de sécurité, nous ne pouvons poursuivre la validation. Vueillez contacter votre conseiller.");
                        $('#confirmationContent').addClass('error-validation');
                        setTimeout(function() {
                            window.close();
                        },
                                6000
                                );
                    }
                }
            },
            error: function() {
                //$('#confirmationContent').html("Une erreur est survenue pendant l'ajout de l'utilisateur");
                //alert("Une erreur est survenue pendant la vérification");
                $.notify("Une erreur est survenue pendant la vérification","error");
            }
        });
      
    });
      $('#annulerTransaction').click(function(e) {
            e.preventDefault();
               $.ajax({
                url: 'transaction/annulation',
                type: 'get',
                
                success: function(response) {
                 
                }, error: function(response) {
                    alert('ajax failed' + response);
                }
            });
        });
         /*Fin partie FormulaireClient.jsp*/
/*==============================================AJOUT D'UN NOUVEAU SCRIPT====================================================*/
    /*Début partie header.jsp*/   
    $('#submitForm').submit(function(e) {
            var frm = $('#submitForm');
            e.preventDefault();

            var data = {};
            //Gather Data also remove undefined keys(buttons)
            $.each(this, function(i, v) {
                var input = $(v);
                data[input.attr("name")] = input.val();
                delete data["undefined"];
            });
            $.ajax({
                contentType: 'application/json; charset=utf-8',
                type: frm.attr('method'),
                url: frm.attr('action'),
                dataType: 'json',
                data: JSON.stringify(data),
                success: function(callback) {
                    if (callback.resultat === 200) {
                        //alert("Response: Name: "+callback.nom+"  Prenom: "+callback.prenom+"  Email: "+callback.email+"  Role: "+callback.role);
                        $(this).html("Action effectuee avec succes");
                        $('#userMessageSuccess').html(callback.msg);
                        $.notify( callback.msg,"success");
                        hideModal('CreateUser');
                        $('#userMessageSuccess').addClass('alert');
                        $('#userMessageSuccess').css('display','block');
                    } else {
                        $('#userMessageSuccess').html(callback.msg);
                        $.notify( callback.msg,"warn");
                    }
                },
                error: function() {
                    $('#userMessageFailed').html("Une erreur est survenue pendant l'ajout de l'utilisateur");
                    $.notify( "Une erreur est survenue pendant l'ajout de l'utilisateur","error");
                }
            });
        });

        function hideOrShowElement(divId){
          if($('#'+divId).css('display')==='none'){
              $('#'+divId).css('display', 'block');
          }else{
              $('#'+divId).css('display', 'none');
          }
        }

        function hideElement(divId){
            if($('#'+divId).css('display')==='block'){
                $('#'+divId).css('display', 'none');
            }
        }

        //l'evenement au click du lien change password
        $('.changePassword').click(function(e) {
            e.preventDefault();
//            var login = $(this).attr('data-id');
            $('#editProfilPasswordInput').val('');
            $('#panelPassword').toggle('slow');
            return false;
        });

        //Partie pour la modification d'un utilisateur
        $('#editProfilFormulaire').submit(function(e) {
          
            var frm = $('#editProfilFormulaire');
            var contextPath = $('#contextPath').val();
            e.preventDefault();
            var data = {};

            //Gather Data also remove undefined keys(buttons)
            $.each(this, function(i, v) {
                var input = $(v);
                data[input.attr("name")] = input.val();
                delete data["undefined"];
            });

            $.ajax({
                contentType: 'application/json; charset=utf-8',
                type: frm.attr('method'),
                url: frm.attr('action'),
                dataType: 'json',
                data: JSON.stringify(data),
                success: function(callback) {
                    if (callback.resultat === 200) {
                        // alert("Response: Name: "+callback.nom+"  Prenom: "+callback.prenom+"  Email: "+callback.email+");
                        $(this).html("Success!");
                        $('#profilMessageSuccess').html(callback.msg);
                        hideModal('viewProfilModal');
                        $.notify( callback.msg,"success");
                        $('#profilMessageSuccess').css('display','block');
                    }
                    if (callback.resultat === 300)
                    {
                      //  $('#profilMessageFailed').show();
                        $('#profilMessageFailed').html(callback.msg);
                        $.notify( callback.msg,"error");
                        $('#profilMessageFailed').css('display','block');
                    }
                    if (callback.resultat === 600)
                    {
                        $('#editProfilFormulaire').hide();
                        window.location = contextPath;
                    }
                },
                error: function() {
                    $('#profilMessageFailed').html("Une erreur est survenue pendant l'ajout de l'utilisateur");
                    $.notify( "Une erreur est survenue pendant l'ajout de l'utilisateur","error");
                    $('#profilMessageFailed').css('display','block');
                }
            });
        });

        $('.showProfil').click(function(e) {
            e.preventDefault();
            $('#profilMessageSuccess').css('display','none');
            $('#profilMessageFailed').css('display','none');
            var login = $(this).attr('data-id');

            $.ajax({
                url: 'user/getProfil',
                type: 'get',
                data: 'login=' + login,
                success: function(response) {
                    infoProfil=response;
                    $('#viewProfilModal').modal();
                    $('#editProfilNomInput').val(response.nom);
                    $('#editProfilPrenomInput').val(response.prenom);
                    $('#editProfilEmailInput').val( response.email);
                    $('#editProfilTelInput').val( response.telephone);
                    $('#editProfilLoginInput').val( response.login);
                    $('#editProfilPasswordInput').val( response.password);
                }, error: function(response) {
                    alert('ajax failed' + response);
                }
            });
        });
         $('.annulerModifProfil').click(function(e) {
            e.preventDefault();
            $('#editProfilNomInput').val(infoProfil.nom);
            $('#editProfilPrenomInput').val(infoProfil.prenom);
            $('#editProfilEmailInput').val( infoProfil.email);
            $('#editProfilTelInput').val( infoProfil.telephone);
            $('#editProfilLoginInput').val( infoProfil.login);
            $('#editProfilPasswordInput').val( infoProfil.password);
        });
});