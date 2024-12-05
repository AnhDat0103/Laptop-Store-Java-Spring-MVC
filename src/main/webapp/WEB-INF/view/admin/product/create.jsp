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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
      $(document).ready(() => {
          const avatarFile = $("#avatarFile");
          avatarFile.change(function (e) {
              const imgURL = URL.createObjectURL(e.target.files[0]);
              $("#avatarPreview").attr("src", imgURL);
              $("#avatarPreview").css({ "display": "block" });
          });
      });
  </script>

</head>

<body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp" />
    <div id="layoutSidenav">
        <jsp:include page="../layout/sidebar.jsp" />
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Laptopshop</h1>
                    
                    <div class="container mt-5">
                      <div class="row">
                          <div class="col-md-6 col-12 mx-auto ">
                              <h2>CREATE A NEW PRODUCT</h2>  
                      <hr>
                      <form:form action="/admin/product/create" method="POST" modelAttribute="newProduct" class="row"
                      enctype="multipart/form-data">
                          <div class="mb-3 col-12 col-md-6">
                            <c:set var="errorMsg">
                              <form:errors path="name"/>
                            </c:set>
                            <form:label for="nameProduct" class="form-label" path="name">Name:</form:label>
                            <form:input type="text" class="form-control ${not empty errorMsg?'is-invalid' : ''}" path="name"/>
                            <form:errors path="name" cssClass="invalid-feedback" />
                          </div>
                          <div class="mb-3 col-12 col-md-6">
                            <c:set var="errorMsg">
                              <form:errors path="price"/>
                            </c:set>
                            <form:label for="inputPrice" class="form-label" path="price">Price:</form:label>
                            <form:input type="number" class="form-control ${not empty errorMsg?'is-invalid' : ''}" path="price"/>
                            <form:errors path="price" cssClass="invalid-feedback" />

                          </div>
                          <div class="mb-3 col-12">
                              <form:label for="inputDescription" class="form-label" path="detailDesc">Description:</form:label>
                              <form:textarea type="text" class="form-control" path="detailDesc"/>
                            </div>
                            <div class="mb-3 col-12 col-md-6">
                              <form:label for="inputShortDesc" class="form-label" path="shortDesc">Short Description:</form:label>
                              <form:input type="text" class="form-control" path="shortDesc"/>
                            </div>
                            <div class="mb-3 col-12 col-md-6">
                              <c:set var="errorMsg">
                              <form:errors path="quantity"/>
                            </c:set>
                              <form:label for="inputQuantity" class="form-label" path="quantity">Quantity:</form:label>
                              <form:input type="number" class="form-control ${not empty errorMsg?'is-invalid' : ''}" path="quantity"/>
                            <form:errors path="quantity" cssClass="invalid-feedback" />

                            </div>
                            <div class="mb-3 col-12 col-md-6">
                              <label class="form-label">Factory:</label>
                              <form:select class="form-select" aria-label="Default select example" path="factory">
                                <form:option value="APPLE">Apple (MacBook)</form:option>
                                <form:option value="ASUS">Asus</form:option>
                                <form:option value="LENOVO">Lenovo</form:option>
                                <form:option value="DELL">Dell</form:option>
                                <form:option value="LG">LG</form:option>
                              </form:select>
                            </div>
                            <div class="mb-3 col-12 col-md-6">
                                <label class="form-label">Target:</label>
                                <form:select class="form-select" path="target">
                                    <form:option value="GAMING">Gaming</form:option>
                                    <form:option value="SINHVIEN-VANPHONG">Sinh viên - Văn phòng
                                    </form:option>
                                    <form:option value="THIET-KE-DO-HOA">Thiết kế đồ họa
                                    </form:option>
                                    <form:option value="MONG-NHE">Mỏng nhẹ</form:option>
                                    <form:option value="DOANH-NHAN">Doanh nhân</form:option>
                                </form:select>
                            </div>
                            <div class="mb-3 col-12 col-md-6">
                              <label for="avatarFile" class="form-label">Image:</label>
                              <input class="form-control" type="file" id="avatarFile"
                                  accept=".png, .jpg, .jpeg"
                                  name="datntFile"
                                  />
                          </div>
                          <div class="col-12 mb-3">
                            <img style="max-height: 250px; display: none;" alt="avatar preview"
                                id="avatarPreview" />
                          </div>
                          <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-primary">Create</button>
                            <a href="/admin/product" class="btn btn-secondary mx-3">Cancel</a> 
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

