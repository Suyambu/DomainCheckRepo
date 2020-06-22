<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="header.jsp" />
		<div class="formContainer" style="padding: 20px; background: #C9C7AF;">
			<form:form action="registerUser" modelAttribute="user"
				class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">User Name:</label>
					<div class="col-sm-10">
						<form:input path="username" id="eventName" required ="true" class="form-control" />

					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<form:input path="password" required ="true" id="amountPerHead"
							class="form-control" />
					</div>
				</div>
				
				
				
				<br>
				<br>
				<div class="form-group">
				<span id="errorMessage"></span>
				</div>
			
				<div class="form-group" style="text-align: center;">
					<input type="submit" class="square_btn" value="Submit" />
				</div>
			</form:form>
		</div>
		<jsp:include page="footer.jsp" />
	