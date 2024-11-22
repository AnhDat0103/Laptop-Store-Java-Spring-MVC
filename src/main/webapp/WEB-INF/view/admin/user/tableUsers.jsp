<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Table users</title>

    <!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>

    <div class="container mt-5">
        <div class="d-flex justify-content-between">
            <h2>Table users</h2>
            
            <a class="btn btn-primary" href="/admin/user/create">Create user</a>

        </div>
        <hr>
        <table class="table table-hover table-bordered">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Email</th>
                    <th scope="col">Full Name</th>
                    <th scope="col">Action</th>   
                  </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td>${user.fullName}</td>
                        <td>
                            <a href="/admin/user/${user.id}" class="btn btn-success mx-2">View</a>
                            <a href="/admin/user/update/${user.id}" class="btn btn-warning mx-2">Update</a>
                            <button type="button" class="btn btn-danger mx-2">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            
              </tbody>
        </table>
    </div>

    
</body>
</html>