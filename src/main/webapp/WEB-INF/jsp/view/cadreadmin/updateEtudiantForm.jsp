<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>


<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page="../fragments/userheader.jsp" />
<div class="container">

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">



            <jsp:include page="../fragments/usermenu.jsp" />


        </div>
    </nav>

    <jsp:include page="../fragments/saidBar.jsp" />





    <h3>Modification des données de l'etudiant <span class="text-info">${etudiant.getNom()} ${etudiant.getPrenom()}</span></h3>
    <div>

        <form method="POST" action="${pageContext.request.contextPath}/cadreadmin/updateEtudiantById" name="registration_form" id='registration_form' class="form-inline">
            <div class="row">
                <div class="col-xl-6">
                    <label for="idEtudiant">Id Etudiant</label>
                     <input class="form-control input-group-lg reg_name" id="idEtudiant" name="idEtudiant" value="${etudiant.getIdUtilisateur()}" />
                </div>
            </div>
            <div class="row">
                <div class="form-group col-xl-6">

                    <label for="nom" class="sr-only">Nom</label>
                    <input id="nom" class="form-control input-group-lg reg_name" type="text" name="nom" value="${etudiant.getNom()}"
                           title="Enter Nom"
                           placeholder="Nom"/>
                </div>

                <div class="form-group col-xl-6">
                    <label for="prenom" class="sr-only">Prenom</label>
                    <input id="prenom" class="form-control input-group-lg reg_name" type="text" name="prenom" value="${etudiant.getPrenom()}"
                           title="Enter Prnom"
                           placeholder="Prenom"/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-xl-6">
                    <label for="nomArabe" class="sr-only">Nom Arabe</label>
                    <input id="nomArabe" class="form-control input-group-lg reg_name" type="text" name="nomArabe" value="${etudiant.getNomArabe()}"
                           title="Enter nomArabe"
                           placeholder="nomArabe"/>
                </div>

                <div class="form-group col-xl-6">
                    <label for="prenomArabe" class="sr-only">Prenom Arabe</label>
                    <input id="prenomArabe" class="form-control input-group-lg reg_name" type="text" name="prenomArabe" value="${etudiant.getPrenomArabe()}"
                           title="Enter prenom Arabe"
                           placeholder="prenom Arabe"/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-xl-6">
                    <label for="email" class="sr-only">Email</label>
                    <input id="email" class="form-control input-group-lg reg_name" type="email" name="email" value="${etudiant.getEmail()}"
                           title="Enter Email"
                           placeholder="Email"/>
                </div>

                <div class="form-group col-xl-6">
                    <label for="telephone" class="sr-only">Telephone</label>
                    <input id="telephone" class="form-control input-group-lg reg_name" type="text" name="telephone" value="${etudiant.getTelephone()}"
                           title="Enter Telephone"
                           placeholder="Telephone"/>
                </div>
            </div>
            <div class="row">


                <div class="form-group col-xl-6">
                    <label for="cne" class="sr-only">CNE</label>
                    <input id="cne" class="form-control input-group-lg reg_name" type="text" name="cne" value="${etudiant.getCne()}"
                           title="Enter CNE"
                           placeholder="CNE"/>
                </div>
            </div>
            <div class="row align-content-center">
                <input class="col-xl-1 m-1 btn btn-primary" value="Update" type="submit">
                <input class="col-xl-1 m-1 btn btn-secondary" value="Cancel" type="reset">
            </div>
        </form>



    </div>





    <jsp:include page="../fragments/userfooter.jsp" />

