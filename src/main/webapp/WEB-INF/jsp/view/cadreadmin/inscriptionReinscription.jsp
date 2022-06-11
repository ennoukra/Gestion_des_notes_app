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

    <form action="${pageContext.request.contextPath}/cadreadmin/upLoadFile" method="post" enctype = "multipart/form-data">
        <div class="row">
            <div class="col-6">
                <label>Annee</label>
                <input class="form-control" type="number" name="annee">
            </div>
            <div class="col-6">
                <label>Fechier Excel</label>
                <input class=" form-control" type="file" name="file" multiple accept=".xlsx, .xls, .csv">
            </div>
        </div>
        <div class="row">
            <input class="col-2 mt-3 btn btn-primary" value="Submit" type="submit">
        </div>

    </form>





    <jsp:include page="../fragments/userfooter.jsp" />

