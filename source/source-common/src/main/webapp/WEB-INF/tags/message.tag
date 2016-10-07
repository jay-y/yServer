<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="content" type="java.lang.String" required="true" description="消息内容" %>
<%@ attribute name="type" type="java.lang.String" description="消息类型:0-success、1-error、2-warning、x-info" %>
<c:if test="${not empty content}">
    <c:choose>
        <c:when test="${type eq '0'}">
            <c:set var="buttonClass" value="alert-success"/>
            <c:set var="iconClass" value="fa-check"/>
        </c:when>
        <c:when test="${type eq '1'}">
            <c:set var="buttonClass" value="alert-danger"/>
            <c:set var="iconClass" value="fa-times"/>
        </c:when>
        <c:when test="${type eq '2'}">
            <c:set var="buttonClass" value="alert-warning"/>
            <c:set var="iconClass" value="fa-warning"/>
        </c:when>
        <c:otherwise>
            <c:set var="buttonClass" value="alert-info"/>
            <c:set var="iconClass" value="fa-info"/>
        </c:otherwise>
    </c:choose>
    <div class="alert ${buttonClass} no-margin fade in">
        <button class="close" data-dismiss="alert">
            ×
        </button>
        <i class="fa-fw fa ${iconClass}"></i>
        <span>${content}</span>
    </div>
</c:if>