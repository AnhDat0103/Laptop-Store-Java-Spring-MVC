<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
                        <h2>Product information</h2>
                        <hr>
                        <div class="card" style="width:50%">
                            <div class="card-header">
                                Product with id = ${product.id}
                            </div>
                            <ul class="list-group list-group-flush">
                              <li class="list-group-item">Name: ${product.name}</li>
                              <li class="list-group-item">price: <fmt:formatNumber pattern="#,##0.00" value="${product.price}"/></li>
                              <li class="list-group-item">Description: ${product.detailDesc}</li>
                              <li class="list-group-item">Short Description: ${product.shortDesc}</li>
                              <li class="list-group-item">Factory: ${product.factory}</li>
                              <li class="list-group-item">Quantity: ${product.quantity}</li>
                              <li class="list-group-item">Target: ${product.target}</li>
                              <li class="list-group-item"><img src="/images/product/${product.image}" ></li>
                            </ul>
                        </div>
                        <a  href="/admin/product" class="btn btn-secondary mt-2">Back</a>  
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


