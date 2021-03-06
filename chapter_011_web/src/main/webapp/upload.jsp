<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!--    Bootstrap CSS   -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>

    <title>Dream Job</title>
</head>
<body>

<div class="container">
    <h1>Dream Job</h1>
    <jsp:include page="nav.jsp"/>

    <div class="row justify-content-md-center mb-2">
        <div class="card w-50">
            <div class="card-header">
                Upload Image
            </div>
            <div class="card-body">
                <form action="<c:url value="/upload"/>" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>Image file
                            <input type="file" name="file" class="form-control-file">
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary">Upload</button>
                </form>
            </div>
        </div>
    </div>
    <div class="row justify-content-md-center">
        <div class="card w-50">
            <div class="card-header">
                Photos
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>URL</th>
                        <th class="text-center">Photo</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--@elvariable id="images" type="java.util.List<java.lang.String>"--%>
                    <c:forEach var="image" items="${images}" varStatus="status">
                        <tr>
                            <td><c:out value="${image}"/></td>
                            <td><a href="<c:url value='/download?name=${image}'/>">Download</a></td>
                            <td>
                                <img src="<c:url value='/download?name=${image}'/>"
                                     width="100px" height="100px" alt="${image}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
