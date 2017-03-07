<%@ include file="include.jsp"%>
<body>
	<div id="wrapper">
		<%@ include file="header.jsp" %>
		<div id="body">
			<h1><spring:message code="label.user.import"/></h1>
			<div id="fileUpload">
				<spring:url value="/user/import" var="url"></spring:url>
				<form method="post" enctype="multipart/form-data" action="${url}">
					<input type="file" name="userXlsFile"/>
					<input type="submit" value='<spring:message code="label.button.import"/>'/>
				</form>
			</div>
		</div>
	</div>
	<div id="footer">
		<p>&copy; 2014 all rights reserved.</p>
	</div>
</body>
</html>
