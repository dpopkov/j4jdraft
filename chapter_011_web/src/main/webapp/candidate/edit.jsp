<%@ page import="ru.j4jdraft.djob.model.Candidate" %>
<%@ page import="ru.j4jdraft.djob.store.PsqlStore" %>
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
<%
    String idParam = request.getParameter("id");
    int id = 0;
    Candidate candidate;
    if (idParam != null) {
        id = Integer.parseInt(idParam);
        candidate = PsqlStore.getInstance().findCandidateById(id);
    } else {
        candidate = new Candidate(0, "", "");
    }
%>
<div class="container pt-3">
    <h1>Dream Job</h1>
    <jsp:include page="../nav.jsp"/>

    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <%= id == 0 ? "New" : "Edit" %> Candidate
            </div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/candidates.do" method="post">
                    <input type="hidden" name="id" value="<%=candidate.getId()%>">
                    <div class="form-group">
                        <label>
                            Name
                            <input type="text" class="form-control" name="name"
                                   value="<%=candidate.getName()%>" required>
                        </label>
                    </div>
                    <div class="form-group">
                        <label>
                            Photo ID
                            <input type="text" class="form-control" name="photoId"
                                   value="<%=candidate.getPhotoId()%>">
                        </label>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
