<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="/resources/layout/header.jsp">
    <jsp:param value="Feed News" name="title"/>
    <jsp:param value="" name="js"/>
    <jsp:param value="/resources/css/feednews.css" name="css"/>
</jsp:include>

    <form:form commandName="news" cssClass="form-horizontal">
        <!-- Modal For Adding -->
        <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">Name: </label>
                            <div class="col-sm-10">
                                <form:input path="name" cssClass="form-control" />
                                <form:errors path="name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="category.name" class="col-sm-2 control-label">Category: </label>
                            <div class="col-sm-10">
                                <form:select path="category.name" cssClass="form-control">
                                        <form:options items="${categoryNames}"/>
                                </form:select>
                                <form:errors path="category.name" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="content" class="col-sm-2 control-label">Content: </label>
                            <div class="col-sm-10">
                                <form:textarea path="content" cssClass="form-control" />
                                <form:errors path="content" />
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <input type="submit" class="btn btn-primary" value="Save">
                    </div>
                </div>
            </div>
        </div>
    </form:form>
    <div class="container" style="width: 50%; background: white; border-radius: 6px;">
        <!-- Button trigger modal -->
        <button type="button" id="myModalBtn" style="text-align: center" class="btn btn-primary" data-toggle="modal" data-target="#addModal">
            Publish news
        </button>
        <div id="blog" class="row">
            <c:forEach items="${newsList}" var="news">
                <div class="col-md-10 blogShort">
                    <h1 class = "title"><c:out value="${news.name}" /></h1>
                    <em class = "publication"><c:out value="${news.publication}"/></em>
                    <article class ="content"><p>
                        <c:out value="${news.content}" />
                    </p></article>
                    <a class="btn btn-blog pull-right marginBottom10" href="<spring:url value="/remove/${news.newsId}"/>">REMOVE</a>
                    <button type="button" class="editModalBtn"  class="btn btn-blog" >
                        EDIT
                    </button>
                </div>
            </c:forEach>
            <div class="col-md-12 gap10"></div>
        </div>
    </div>
<script type="text/javascript" src="<c:url value="/resources/js/edit.js"/>"></script>
<jsp:include page="/resources/layout/footer.jsp"/>
