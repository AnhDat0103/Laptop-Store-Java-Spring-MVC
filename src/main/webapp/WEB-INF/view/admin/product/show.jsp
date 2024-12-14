<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

                    <div class="container mt-5">
                        <div class="d-flex justify-content-between">
                            <h2>Product list</h2>
                            
                            <a class="btn btn-primary" href="/admin/product/create">Create product</a>
        
                        </div>
                        <hr>
                        <table class="table table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Factory</th>
                                    <th scope="col">Action</th>  
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="product" items="${products}">
                                    <tr>
                                        <td>${product.id}</td>
                                        <td>${product.name}</td>
                                        <td><fmt:formatNumber pattern="#,##0.00" value="${product.price}"/></td>
                                        <td>${product.factory}</td>
                                        <td>
                                            <a href="/admin/product/show/${product.id}" class="btn btn-success mx-2">View</a>
                                            <a href="/admin/product/update/${product.id}" class="btn btn-warning mx-2">Update</a>
                                            <a href="/admin/product/delete/${product.id}" class="btn btn-danger mx-2">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center">
                              <li class="page-item ">
                                <a class=" ${currentPage eq 0 ? 'page-link disabled': 'page-link'}" href="/admin/product?page=${currentPage - 1}">Previous</a>
                              </li>
                              <c:forEach begin="0" end="${totalPages - 1}" varStatus="loop">
                                    <li class="page-item"> 
                                        <a class="${currentPage eq loop.index ? 'page-link active' : 'page-link'}" href="/admin/product?page=${loop.index}">${loop.index + 1}</a>
                                    </li>
                            </c:forEach>
                              <li class="page-item">
                                <a class=" ${currentPage eq (totalPages - 1) ? 'page-link disabled': 'page-link'}" href="/admin/product?page=${currentPage + 1}">Next</a>
                              </li>
                            </ul>
                          </nav>
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