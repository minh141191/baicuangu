<%--
  Created by IntelliJ IDEA.
  User: Minh Tran
  Date: 5/29/2023
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h1 style="text-align: center">List Category</h1>
    <div class="row" style="width: 700px; margin: auto">
        <div class="col-lg-4">
            <a class="btn btn-primary" style="text-decoration: none; color: white" href="/category?action=create">Create
                New Category</a>
        </div>
        <div class="col-lg-8">
            <a class="btn btn-primary" style="text-decoration: none; color: white" href="/products">Back to Product</a>
        </div>
    </div>
    <table style="width: 700px; margin: auto" class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>ProductSum</th>
            <th colspan="2" style="text-align: center; width: 33%">Action</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${category}" var="c">
            <tr>
                <td>${c.id}</td>
                <td>${c.name}</td>
                <td>${c.productSum}</td>
                <td>
                    <a class="btn btn-warning" href="/category?action=update&&id=${c.id}">Update</a>
                </td>
                <td>
                    <button class="btn btn-danger" onclick="deleteS(${c.id})">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script>
    function deleteS(id) {
        if (confirm("Are you sure?")) {
            window.location.href = `http://localhost:8080/category?action=delete&&id=` + id;
        }
    }
</script>
</html>
