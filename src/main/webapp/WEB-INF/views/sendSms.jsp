<%@ include file="include.jsp"%>
<body>
	<div id="wrapper">
		<%@ include file="header.jsp" %>
		<div id="body">
			<h1><spring:message code="label.user.sendsms"/></h1>
			<div id="sendSms">
				<h2><spring:message code="label.user.smsContent"/></h2>
				<spring:url value="/user/sendSms" var="url"></spring:url>
				<form action="${url}" method="post">
					<textarea rows="20" cols="50" name="smsContent"></textarea>
					<input type="submit" value='<spring:message code="label.user.sms.send"/>'/>
				</form>
			</div>
		</div>
	</div>
	<div id="footer">
		<p>&copy; 2014 all rights reserved.</p>
	</div>
</body>
</html>
