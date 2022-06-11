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
        <h3>chosire le niveua</h3>

        <form action="${pageContext.request.contextPath}/cadreadmin/showEtudentsByNiveau" method="post">
            <div class="form-group mb-2">
                <label for="annee">Select Annee</label>
                <input name="annee" type="annee" class="form-control" id="annee"  placeholder="Select Annee">
            </div>
            <div class="form-group mb-2">
                <label>select niveau</label>
                <select class="form-select" aria-label="Default select example" name="idNiveau">
                    <option selected>chose your niveau</option>
                    <c:forEach var="niveau" items="${niveauList}" varStatus="loopCounter" >
                        <option value ="${niveau.idNiveau}" >${niveau.titre}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>



    </div>





    <jsp:include page="../fragments/userfooter.jsp" />

