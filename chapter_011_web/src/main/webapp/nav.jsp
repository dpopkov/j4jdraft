<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link" href="posts.do">Vacancies</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="candidates.do">Candidates</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="posts.do?action=add">Add Vacancy</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="candidates.do?action=add">Add Candidate</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="upload">Upload Photo</a>
        </li>
        <li class="nav-item">
            <c:choose>
                <%--@elvariable id="user" type="ru.j4jdraft.djob.model.User"--%>
                <c:when test="${empty user}">
                    <a class="nav-link" href="auth.do">Login</a>
                </c:when>
                <c:otherwise>
                    <a class="nav-link" href="auth.do?logout"><c:out value="${user.name}"/> | Log out</a>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>
</div>
