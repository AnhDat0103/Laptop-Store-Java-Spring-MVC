<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register user</title>

    <!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 col-12 mx-auto">
                <h2>Create a user</h2>  
        <hr>
        <form:form action="/create" method="post" modelAttribute="user">
            <div class="mb-3">
              <form:label for="inputEmail" class="form-label" path="email">Email:</form:label>
              <form:input type="email" class="form-control" path="email"/>
            </div>
            <div class="mb-3">
              <form:label for="inputPassword" class="form-label" path="password">Password:</form:label>
              <form:input type="password" class="form-control" path="password"/>
            </div>
            <div class="mb-3">
                <form:label for="inputFullName" class="form-label" path="fullName">Full Name:</form:label>
                <form:input type="text" class="form-control" path="fullName"/>
              </div>
              <div class="mb-3">
                <form:label for="inputPhoneNumber" class="form-label" path="telephone">Phone Number:</form:label>
                <form:input type="text" class="form-control" path="telephone"/>
              </div>
              <div class="mb-3">
                <form:label for="inputAddress" class="form-label" path="address">Address:</form:label>
                <form:input type="text" class="form-control" path="address"/>
              </div>
            <button type="submit" class="btn btn-primary">Submit</button>
          </form:form>
            </div>
        </div> 
    </div>

    
</body>
</html>