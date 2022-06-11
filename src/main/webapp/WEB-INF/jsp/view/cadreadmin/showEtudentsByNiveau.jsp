<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>


<jsp:include page="../fragments/userheader.jsp" />
<div class="container">

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">



            <jsp:include page="../fragments/usermenu.jsp" />


        </div>
    </nav>

    <jsp:include page="../fragments/saidBar.jsp" />






    <div>
        <div class="row">
            <h3 class="col-9">La list des etudiants </h3>
            <div class="col-3">
                <a href="${pageContext.request.contextPath}/cadreadmin/exportEtudiant" class="btn btn-primary"> Export Excel </a>
            </div>
        </div>



        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Nom</th>
                <th scope="col">Prenom</th>
                <th scope="col">CNE</th>
                <th scope="col">Email</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
<%--            THIS FOR LOOP SHOW THE ETUDENTS WITH THE SAME NIVEAU       -----%>

            <c:forEach var="etudaint" items="${etudiantsList}" >
                <tr>
                    <th scope="row"><c:out value="${etudaint.getIdUtilisateur()}" /></th>
                    <td><c:out value="${etudaint.getNom()}" /></td>
                    <td><c:out value="${etudaint.getPrenom()}" /></td>
                    <td>$<c:out value="${etudaint.getCne()}" /></td>
                    <td><c:out value="${etudaint.getEmail()}" /></td>
                    <td><a class="btn btn-primary" href="${pageContext.request.contextPath}/cadreadmin/updateEtudiant/${etudaint.getIdUtilisateur()}">update</a>
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/cadreadmin/deletEtudiant/${etudaint.getIdUtilisateur()}">Delete</a> </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>

    </div>





    <jsp:include page="../fragments/userfooter.jsp" />

