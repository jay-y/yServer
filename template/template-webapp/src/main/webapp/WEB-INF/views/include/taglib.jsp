<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/views/include/tlds/fns.tld" %>
<%@ taglib prefix="shiro" uri="/WEB-INF/views/include/tlds/shiros.tld" %>
<%--<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxSource" value="${pageContext.request.contextPath}/source"/>
<c:set var="commonSource" value="${pageContext.request.contextPath}/source/common"/>
<c:set var="modulesSource" value="${pageContext.request.contextPath}/source/modules"/>