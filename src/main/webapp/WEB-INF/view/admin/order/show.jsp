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
    <link href="../css/styles.css" rel="stylesheet" />
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
                            <h2>Order list</h2>
                            
                            <a class="btn btn-primary" href="/admin/order/create">Create order</a>
        
                        </div>
                        <hr>
                        <c:forEach var="order" items="${orders}">
                            <form action="/admin/order/delete/${order.id}" method="POST" >
                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                <div class="d-flex justify-content-between">
                                    <h2>Đơn hàng số ${order.id}</h2>
                                    <button type="submit" class="btn btn-danger my-1 mx-1">Delete</button>
                                </div>

                            </form>

                                <table class="table table-hover table-bordered">
                                    <thead>
                                        <tr>
                                            <th scope="col">ID</th>
                                            <th scope="col">Product</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="orderDetail" items="${order.orderDetails}">
                                            <tr>
                                                <td>${orderDetail.id}</td>
                                                <td>${orderDetail.product.name}</td>
                                                <td>${orderDetail.quantity}</td>
                                                <td><fmt:formatNumber pattern="#,##0.00" value="${orderDetail.price * orderDetail.quantity}"/></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            <hr>
                        </c:forEach>

                    </div>
                    
                </div>
                <hr>
            </main>
            <jsp:include page="../layout/footer.jsp" />
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
</body>

</html>