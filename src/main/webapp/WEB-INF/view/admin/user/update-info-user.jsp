<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="DatNT- Dự án laptopshop" />
    <meta name="author" content="Hỏi Dân IT" />
    <title>Dashboard - DatNT</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>

<body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp" />
    <div id="layoutSidenav">
        <jsp:include page="../layout/sidebar.jsp" />
        <div id="layoutSidenav_content">            
            <main>
                <div class="container-fluid px-4">
                        <!-- <h1 class="mt-4">Dashboard</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Dashboard</li>
                        </ol> -->
                        <div class="container mt-5">
                          <div class="row">
                              <div class="col-md-6 col-12 mx-auto">
                                  <h2>Update user id = ${userUpdated.id}</h2>  
                          <hr>
                          <form:form action="/admin/user/update" method="POST" modelAttribute="userUpdated">
                              <div class="mb-3">
                                  <form:label for="inputEmail" class="form-label" path="email">Email:</form:label>
                                  <form:input type="email" class="form-control" path="email" disabled="true"/>
                                </div>
                                <div class="mb-3" style="display: none;">
                                  <form:label for="inputId" class="form-label" path="id">ID:</form:label>
                                  <form:input type="text" class="form-control" path="id" />
                                </div>
                              <div class="mb-3">
                                  <c:set var="errorMsg">
                                    <form:error path="fullName" />
                                  </c:set>
                                  <form:label for="inputFullName" class="form-label" path="fullName" >Full Name:</form:label>
                                  <form:input type="text" class="form-control ${not empty errorMsg?'is-invalid':''}" path="fullName" />
                                  <form:error path="fullName" cssClass="invalid-feedback" />
                                </div>
                                <div class="mb-3">
                                  <form:label for="inputPhoneNumber" class="form-label" path="telephone" >Phone Number:</form:label>
                                  <form:input type="text" class="form-control" path="telephone" />
                                </div>
                                <div class="mb-3">
                                  <form:label for="inputAddress" class="form-label" path="address">Address:</form:label>
                                  <form:input type="text" class="form-control" path="address"/>
                                </div>
                                
                                <div class="d-flex justify-content-between">
                                  <button type="submit" class="btn btn-primary">Submit</button>
                                  <a  href="/admin/user" class="btn btn-secondary" >Back</a>  
                                </div>
                            </form:form>
                              </div>
                          </div> 
                        </div>
                </div>

            </main>
            <jsp:include page="../layout/footer.jsp" />
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
</body>

</html>



