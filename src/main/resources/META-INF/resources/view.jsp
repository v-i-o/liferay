<%@ include file="/init.jsp" %>

<jsp:useBean id="userName" type="java.lang.String" scope="request" />

<p>

	<b>Hello, <%=userName %>!</b>

</p>