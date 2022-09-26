<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="col">
    <h2 class="d-inline p-2">Welcome To homepage</h2>
    <h4 style="float:right" class="d-inline p-2 bg-dark text-white"> ${username}</h4>
</div>
        <hr>
        <div>
            <h5><a href="list-todos">View</a> your Todos</h5>
        </div>
<%@ include file="common/footer.jspf" %>