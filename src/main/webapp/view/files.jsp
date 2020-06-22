
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page import="com.example.demo.model.FileInfo"%>
    <style>
    
    table { 
  width: 100%; 
  border-collapse: collapse; 
}
/* Zebra striping */
tr:nth-of-type(odd) { 
  background: #eee; 
}
th { 
  background: #F8C764; 
  color: white; 
  font-weight: bold; 
  padding: 8px; 
  border: 1px solid #ccc; 
  text-align: left; 
  padding: 10px; 
}
td { 
  padding: 8px; 
  border: 1px solid #ccc; 
  text-align: left; 
}
@media 
only screen and (max-width: 760px),
(min-device-width: 768px) and (max-device-width: 1024px)  {

	/* Force table to not be like tables anymore */
	table, thead, tbody, th, td, tr { 
		display: block; 
	}
	
	/* Hide table headers (but not display: none;, for accessibility) */
	thead tr { 
		position: absolute;
		top: -9999px;
		left: -9999px;
	}
	
	tr { border: 1px solid #ccc; }
	
	td { 
		/* Behave  like a "row" */
		border: none;
		border-bottom: 1px solid #eee; 
		position: relative;
		padding-left: 50%; 
	}
	
	td:before { 
		/* Now like a table header */
		position: absolute;
		/* Top/left values mimic padding */
		top: 6px;
		left: 6px;
		width: 45%; 
		padding-right: 10px; 
		white-space: nowrap;
	}
	
	/*
	Label the data
	*/
	td:nth-of-type(1):before { content: "ID"; }
	td:nth-of-type(2):before { content: "Event name"; }
	td:nth-of-type(3):before { content: "Amount"; }
	td:nth-of-type(4):before { content: "Group"; }
	td:nth-of-type(5):before { content: "From Date"; }
	td:nth-of-type(6):before { content: "To Date"; }
	td:nth-of-type(7):before { content: "Edit"; }
	td:nth-of-type(8):before { content: "Delete"; }
}
    
    </style>
<jsp:include page="header.jsp" />
	<table>
				<thead>
					<tr>
						
						<th>File name</th>
						<th>Date</th>
						<th>Percentage</th>
						<th>Status</th>
						<th>Download</th>
						<th>Delete</th>
					</tr>
				</thead>

				<tbody>
				<c:forEach  items = "${fileInfoList}" var="file">
					<tr>
					
						
						<td>${file.fileName}</td>
						<td>${file.date}</td>
						<td>${file.percentage}</td>
						<td>${file.status}</td>
						<td><a href="<c:url value='/download/${file.id}' />" ><img src="https://img.icons8.com/office/16/000000/edit.png"/></a></td>
						<td><a href="<c:url value='/delete/${file.id}' />" ><img src="https://img.icons8.com/officexs/16/000000/delete-sign.png"/></a></td>
					
					</tr>
				   	</c:forEach>
				</tbody>
			</table>
			

<jsp:include page="footer.jsp" />



