<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
<%@ include file="/init.jsp" %>

<jsp:useBean id="from" type="java.lang.String" scope="request" />
<jsp:useBean id="to" type="java.lang.String" scope="request" />

<b>From: <%=from %></b>
<i>To: <%=to %></i>
<portlet:actionURL name="setStops" var="setStopsURL"></portlet:actionURL>
<aui:form action="<%= setStopsURL %>" name="<portlet:namespace />fm">
	<aui:fieldset>
		<aui:input name="from"></aui:input>
		<aui:input name="to"></aui:input>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit"></aui:button>
	</aui:button-row>
</aui:form>