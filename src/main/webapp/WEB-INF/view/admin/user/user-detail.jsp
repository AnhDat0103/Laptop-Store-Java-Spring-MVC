<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Table users ${user.id}</title>

    <!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>

    <div class="container mt-5">
        <h2>User information</h2>
        <hr>
        <div class="card" style="width:50%">
            <div class="card-header">
                Users with id = ${user.id}
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">Email: ${user.email}</li>
              <li class="list-group-item">Full name: ${user.fullName}</li>
              <li class="list-group-item">Telephone number: ${user.telephone}</li>
              <li class="list-group-item">Address: ${user.address}</li>
            </ul>
        </div>
        <a  href="/admin/user" class="btn btn-secondary mt-2">Back</a>  
    </div>

    
</body>
</html>