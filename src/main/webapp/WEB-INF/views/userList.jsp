<%@ include file="include.jsp"%>
<body>
	<div id="wrapper">
		<%@ include file="header.jsp" %>
		<div id="body">
			<h1><spring:message code="label.user.list"/></h1>
			<spring:url value="/user/preImport" var="url"/>
			<h2><a href="${url}"><spring:message code="label.user.import"/></a></h2>
			<spring:url value="/user/preSendSms" var="url"/>
			<h2><a href="${url}"><spring:message code="label.user.sendsms"/></a></h2>
			<table>
				<thead>
					<tr>
						<th><spring:message code="label.sequenceNum"/></th>
						<th><spring:message code="label.user.userName"/></th>
						<th><spring:message code="label.user.gender"/></th>
						<th><spring:message code="label.user.phoneNumber"/></th>
						<th><spring:message code="label.user.idNumber"/></th>
						<th><spring:message code="label.user.weChatId"/></th>
						<th><spring:message code="label.user.qqId"/></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" varStatus="status" items="${userList}">
					    <tr>
					    	<td><c:out value="${status.count}"/></td>
					        <td><spring:escapeBody>${user.userName}</spring:escapeBody></td>
					        <td>
					        	&nbsp;
					        </td>
					        <td><c:out value="${user.phoneNumber}"/></td>
					        <td><c:out value="${user.idNumber}"/></td>
					        <td><spring:escapeBody><c:out value="${user.weChatId}"/></spring:escapeBody></td>
					        <td><c:out value="${user.qqId}"/></td>
					    </tr>
             		</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div id="footer">
		<p>&copy; 2014 all rights reserved.</p>
	</div>
</body>
</html>
