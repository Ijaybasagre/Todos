<%@ include file="common/header.jspf"%>
        <%@ include file="common/navigation.jspf"%>
<h3 xmlns:form="http://www.w3.org/1999/xhtml" xmlns:form="http://www.w3.org/1999/xhtml">Enter Todo Details</h3>
    <div class="container fluid">
        <form:form class="row row-cols-lg-auto g-3 align-items-center" method="post" modelAttribute="todo">
            <div style="display:block">
            <div>
                <form:label path="description" class="form-label">Description</form:label>
                <form:input type="text" class="form-control form-control-sm" id="description"  path="description" required="required"/>
                <form:errors path="description" cssClass="text-warning" />
            </div>
            <div>
                <form:label path="targetDate" class="form-label">Target Date</form:label>
                <form:input type="text" class="form-control form-control-sm" id="targetDate"  path="targetDate" required="required"/>
                <form:errors path="targetDate" cssClass="text-warning" />
            </div>
            <form:input type="hidden" placeholder="id" path="id"/>
            <form:input type="hidden" placeholder="done" path="done"/>
            <button type="submit" class="btn btn-primary sm mt-2">Add Todo</button>
            </div>
        </form:form>
    </div>
        <%@ include file="common/footer.jspf" %>
        <script type="text/javascript">
            $('#targetDate').datepicker({
            format: 'yyyy-mm-dd'
             });
        </script>
