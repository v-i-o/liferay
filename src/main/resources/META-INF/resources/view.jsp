<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
<%@ include file="/init.jsp" %>

<jsp:useBean id="from" type="java.lang.String" scope="request" />
<jsp:useBean id="to" type="java.lang.String" scope="request" />


<style type="text/css">
	.wrapper {
		width: 45%;
		font-family: sans-serif;
	}

	.departures thead td {
		padding: 1em;
		background-color: #e3eff8;
		font-weight: bold;
	}
	.departures tbody td {
		border-bottom: 1px solid #98c6e3;
		padding: 0.8em 1em;
	}

	.departures {
		width: 100%;
		border: 5px solid #98c6e3;
	}

	.from, .to {
		font-size: 1.2em;
		font-weight: bold;
		margin: 0 0.25em 0 0.35em;
	}

	.to {
		margin-right: 0.8em;
	}
</style>


<div class="wrapper">
	<p>Detail vašeho spojení ze stanice <span class="from"><%= from %></span> do stanice <span class="to"><%= to %></span>:</p>

	<table class="departures" cellspacing="0">
		<thead>
		<tr>
			<td>Odjezd</td>
			<td>Typ spoje</td>
			<td>Doba jízdy</td>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>
				<div>2. 10. 2020</div>
				<div>13:44</div>
			</td>
			<td>
				<div>vlak, autobus</div>
			</td>
			<td>
				<div>20 minut</div>
			</td>
		</tr>
		<tr>
			<td>
				<div>2. 10. 2020</div>
				<div>13:44</div>
			</td>
			<td>
				<div>vlak, autobus</div>
			</td>
			<td>
				<div>20 minut</div>
			</td>
		</tr>
		</tbody>
	</table>
</div>


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