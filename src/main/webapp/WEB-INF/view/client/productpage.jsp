<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Danh sách sản phẩm - Laptopshop </title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->
        <link href="/client/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="/client/css/style.css" rel="stylesheet">
    </head>

    <body>

        <!-- Spinner Start -->
        <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
            <div class="spinner-grow text-primary" role="status"></div>
        </div>
        <!-- Spinner End -->


        <!-- Navbar start -->
        <jsp:include page="../client/layout/header.jsp"/>
        <!-- Navbar End -->

        <!-- Fruits Shop Start-->
        <div class="container-fluid fruite py-5">
            <div class="container py-5">    
                <div class="row g-4 py-4">
                    <div class="col-lg-12">
                        <div class="row g-4">
                            <div class="col-lg-4">
                                <div class="row g-4">
                                    <div class="col-lg-12" id="factoryFilter">
                                        <div class="mb-2"><b>Hãng sản xuất</b></div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="factory-1"
                                                        value="APPLE">
                                                    <label class="form-check-label" for="factory-1">Apple</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="factory-2"
                                                        value="ASUS">
                                                    <label class="form-check-label" for="factory-2">Asus</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="factory-3"
                                                        value="LENOVO">
                                                    <label class="form-check-label" for="factory-3">Lenovo</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="factory-4"
                                                        value="DELL">
                                                    <label class="form-check-label" for="factory-4">Dell</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="factory-5"
                                                        value="LG">
                                                    <label class="form-check-label" for="factory-5">LG</label>
                                                </div>
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="factory-6"
                                                        value="ACER">
                                                    <label class="form-check-label" for="factory-6">Acer</label>
                                                </div>
                                    </div>
                                    <div class="col-lg-12" id="targetFilter">
                                        <div class="mb-2"><b>Mục đích sử dụng</b></div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="checkbox" id="target-1"
                                                value="GAMING">
                                            <label class="form-check-label" for="target-1">Gaming</label>
                                        </div>

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="checkbox" id="target-2"
                                                value="SINHVIEN-VANPHONG">
                                            <label class="form-check-label" for="target-2">Sinh viên - văn
                                                phòng</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="checkbox" id="target-3"
                                                value="THIET-KE-DO-HOA">
                                            <label class="form-check-label" for="target-3">Thiết kế đồ
                                                họa</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="checkbox" id="target-4"
                                                value="MONG-NHE">
                                            <label class="form-check-label" for="target-4">Mỏng nhẹ</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="checkbox" id="target-5"
                                                value="DOANH-NHAN">
                                            <label class="form-check-label" for="target-5">Doanh nhân</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-12" id="priceFilter">
                                        <div class="mb-2"><b>Mức giá</b></div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="price-2"
                                                        value="duoi-10-trieu">
                                                    <label class="form-check-label" for="price-2">Dưới 10 triệu</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="price-3"
                                                        value="10-15-trieu">
                                                    <label class="form-check-label" for="price-3">Từ 10 - 15
                                                        triệu</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="price-4"
                                                        value="15-20-trieu">
                                                    <label class="form-check-label" for="price-4">Từ 15 - 20
                                                        triệu</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="price-5"
                                                        value="tren-20-triệu">
                                                    <label class="form-check-label" for="price-5">Trên 20 triệu</label>
                                                </div>
                                    </div>
                                    <div class="col-lg-12">
                                        <div class="mb-2"><b>Sắp xếp</b></div>

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" id="sort-1"
                                                value="gia-tang-dan" name="radio-sort">
                                            <label class="form-check-label" for="sort-1">Giá tăng dần</label>
                                        </div>

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" id="sort-2"
                                                value="gia-giam-dan" name="radio-sort">
                                            <label class="form-check-label" for="sort-2">Giá giảm dần</label>
                                        </div>

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" id="sort-3" checked
                                                value="gia-nothing" name="radio-sort">
                                            <label class="form-check-label" for="sort-3" >Không sắp xếp</label>
                                        </div>

                                    </div>
                                    <div class="col-lg-12">
                                        <button class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4" id="btnFilter">
                                                    Lọc Sản Phẩm
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-8">
                                <div class="row g-4 justify-content-between">
                                    <c:if test="${totalPages ==  0}">
                                                <div>Không tìm thấy sản phẩm</div>
                                     </c:if>
                                        <c:forEach items="${products}" var="product">
                                            <div class="col-md-4 col-lg-4 col-xl-4">
                                                <div class="rounded position-relative fruite-item">
                                                    <div class="fruite-img">
                                                        <img src="/images/product/${product.image}" class="img-fluid w-100 rounded-top" alt="">
                                                    </div>
                                                    <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">Laptop</div>
                                                    <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                        <h4 style="font-size: 15px;"><a href="/client/product/${product.id}">${product.name}</a></h4>
                                                        <p style="font-size: 13px;">${product.shortDesc}</p>
                                                        <div class="d-flex justify-content-center flex-lg-wrap"> 
                                                            <p style="font-size: 15px; text-align: center; width: 100%;" class="text-dark fs-5 fw-bold mb-0"><fmt:formatNumber value="${product.price}"/> đ</p>
                                                            <form action="/add-to-cart/${product.id}" method="POST">
                                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                                <button style="text-align: center; width: 100%;" type="submit" class="btn border border-secondary rounded-pill px-3 text-primary">
                                                                    <i class="fa fa-shopping-bag me-2 text-primary"></i>
                                                                     Add to cart
                                                                </button>    
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <c:if test="${totalPages > 0}">
                                        <div class="col-12">
                                            <nav aria-label="Page navigation example" class="mt-5">
                                                <ul class="pagination d-flex justify-content-center">
                                                    
                                                        <li class="page-item ">
                                                            <a class=" ${currentPage eq 0 ? 'page-link disabled': 'page-link'}" href="/client/page/product?page=${currentPage - 1}${queryString}">Previous</a>
                                                          </li>
                                                          <c:forEach begin="0" end="${totalPages - 1}" varStatus="loop">
                                                                <li class="page-item"> 
                                                                    <a class="${currentPage eq loop.index ? 'page-link active' : 'page-link'}" href="/client/page/product?page=${loop.index}${queryString}">${loop.index + 1}</a>
                                                                </li>
                                                        </c:forEach>
                                                          <li class="page-item">
                                                            <a class=" ${currentPage eq (totalPages - 1) ? 'page-link disabled': 'page-link'}" href="/client/page/product?page=${currentPage + 1}${queryString}">Next</a>
                                                          </li>
                                                  
                                                </ul>
                                              </nav>
                                        </div>
                                        </c:if>
                                                 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Fruits Shop End-->


        <!-- Footer Start -->
  
        <jsp:include page="../client/layout/footer.jsp"/>
        <!-- Footer End -->

        <!-- Copyright Start -->
        <div class="container-fluid copyright bg-dark py-4">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                        <span class="text-light"><a href="#"><i class="fas fa-copyright text-light me-2"></i>Your Site Name</a>, All right reserved.</span>
                    </div>
                    <div class="col-md-6 my-auto text-center text-md-end text-white">
                        <!--/*** This template is free as long as you keep the below author’s credit link/attribution link/backlink. ***/-->
                        <!--/*** If you'd like to use the template without the below author’s credit link/attribution link/backlink, ***/-->
                        <!--/*** you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". ***/-->
                        Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a> Distributed By <a class="border-bottom" href="https://themewagon.com">ThemeWagon</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Copyright End -->



        <!-- Back to Top -->
        <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   

        
    <!-- JavaScript Libraries -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/client/lib/easing/easing.min.js"></script>
    <script src="/client/lib/waypoints/waypoints.min.js"></script>
    <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
    <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="/client/js/main.js"></script>
    </body> 

</html>