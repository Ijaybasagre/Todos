<!--//use for displaying putting logic like forEach-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link href="webjars\bootstrap\5.2.0\css\bootstrap.min.css" rel="stylesheet">
        <title>My TODO Page</title>
    </head>
    <body>
        <div class="container">
            <%@ include file="common/navigation.jspf"%>
            <h1>Your Todos</h1>
            <table class="table table-hover table-sm ">
            <thead>
            <tr>
                <th>Description</th>
                <th>Target Date</th>
                <th>isDone?</th>
                <th>Action</th>
            </tr>
            </thead>
               <tbody>
                   <c:forEach items="${todos}" var="todo">
                       <tr>
                           <td>${todo.description}</td>
                           <td>${todo.targetDate}</td>
                           <td>${todo.done}</td>
                           <td>
                               <div class="btn-group" role="group">
                                   <a class="btn btn-danger btn-sm" href="delete-todo?id=${todo.id}">Delete</a>
                                   <a class="btn btn-primary btn-sm" href="update-todo?id=${todo.id}">Update</a>
                               </div>
                           </td>
                       </tr>
                   </c:forEach>
               </tbody>
            </table>
            <a href="add-Todo" class="btn btn-outline-primary sm">Add Todo</a>
        </div>
        <script src="webjars\bootstrap\5.2.0\js\bootstrap.min.js"></script>
        <script src="webjars\jquery\3.6.1\jquery.min.js"></script>
    </body>
    <footer>
    </footer>
</html>