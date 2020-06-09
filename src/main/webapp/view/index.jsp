<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style>
.loader {
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #3498db;
  width: 120px;
  height: 120px;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}

/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>
</head>
<body>

<div class="container">


  <h2>Domain Check List</h2>
  <div class="panel panel-primary">
    <div class="panel-heading">Domain Form</div>
   
    
    <form action = "xlForm" method = "post"enctype="multipart/form-data" >
     <div class="panel-body">
   <div class="form-group">
    <label for="start">Starting Index :</label>
    <input type="number"name="start" class="form-control" style="width:50%;margin:auto"required = "true"  id="start">
  </div>
  <div class="form-group">
    <label for="end">End Index  :</label>
    <input type="number"name="end" class="form-control"style="width:50%;margin:auto;"required = "true" id="end">
  </div>
  <div class="form-group">
    <label for="domain">Domain column Index :</label>
    <input type="number"name="domain" class="form-control"style="width:50%;margin:auto;"required = "true" id="domain">
  </div>
  <div class="form-group">
    <label for="updatedDomain">Updated Domain Index  :</label>
    <input type="number"name="updatedDomain" class="form-control"style="width:50%;margin:auto;"required = "true" id="updatedDomain">
  </div>
 
  <div class="form-group">
   <label for="file">Xl File  :</label>
  <input type="file" name ="mfile" class="form-control" style="width:50%;margin:auto;" required = "true"  placeholder="Select a file"id="file">
   </div>
   <div id="alert" class="alert alert-danger" style="display:none">
  <strong id ="error"> Indicates a dangerous or potentially negative action.</strong>
</div>

</div>
<div class="panel-footer" style="text-align:center"> <button type="submit" class="btn btn-primary" onclick = "return check();" style="margin:auto;">Submit</button></div>
   
   
    </form>
    
    
    
  </div>
  </div>
  
  <script type="text/javascript">
  
  function check(){
	  
	  var start = document.getElementById("start").value;
	  var end = document.getElementById("end").value;
	 
	  if(end < start){
		  alert(comming);
		  document.getElementById("error").innerHTML = "End index must be greater than Start";
		  document.getElementById("alert").style.display = "block";
		  return false;
	  }else{
		  
		  return true;
		  
	  }
	  
	  
  }
  
  </script>
 

</body>
</html>
