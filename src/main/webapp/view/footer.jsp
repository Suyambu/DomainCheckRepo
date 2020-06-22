</div>

<p>Footer</p>
<script>
		$(document).ready(
				function() {
					var date_input = $('#fdate'); //our date input has the name "date"
					var date_input2 = $('#tdate'); //our date input has the name "date"
					var container = $('.bootstrap-iso form').length > 0 ? $(
							'.bootstrap-iso form').parent() : "body";
				   var date = new Date();
				  date.setDate(date.getDate());

					var options = {
						format : 'yyyy/mm/dd',
						container : container,
						todayHighlight : true,
						autoclose : true,
						 startDate: date,
					};
					date_input.datepicker(options);
					date_input2.datepicker(options);
				})
	</script>
	<script>
	

	function validate() {

				var eventName = document.getElementById("eventName").value;
				var event = document.getElementById("eventName");
				var amountPerHead = document.getElementById("amountPerHead").value;
				var amount = document.getElementById("amountPerHead");
				var groupName = document.getElementById("groupName").value;
				var group = document.getElementById("groupName");
				var fdate = document.getElementById("fdate").value;
				var fdateelement = document.getElementById("fdate");
				var tdate = document.getElementById("tdate").value;
				var tdateelement = document.getElementById("tdate");
				var valid = 1;
				var error = "Please fill ";
				if (eventName == "") {
					valid = 0;
					event.style.border = "1px dotted red";
					error = error + " Event Name";

				}else{
					
					event.style.border = "1px dotted green";
				}

				if (amountPerHead == "") {

					valid = 0;
					amount.style.border = "1px dotted red";
					error = error + " Amount per head";

				}else{
					
					amount.style.border = "1px dotted green";
				}
				

				if (isNaN(amountPerHead)) {
					valid = 0;
					amount.value = '';
					amount.style.border = "1px dotted red";
					document.getElementsByName('amountPerHead')[0].placeholder = 'Please provide a valid input';
				}else{
					
					amount.style.border = "1px dotted green";
				}
				
				
				if (groupName == "") {

					valid = 0;
					group.style.border = "1px dotted red";
					error = error + " Group Name";

				}else{
					
					group.style.border = "1px dotted green";
				}
				
				if (fdate == "") {

					valid = 0;
					fdateelement.style.border = "1px dotted red";
					error = error + " From Date";

				}else{
					
					fdateelement.style.border = "1px dotted green";
				}
				
				if (tdate == "") {

					valid = 0;
					tdateelement.style.border = "1px dotted red";
					error = error + " To Date";

				}else{
					
					tdateelement.style.border = "1px dotted green";
				}
				
			

				if (valid == 1) {
					return true;
				} else {
				
					return false;
				}

			}
	</script>

</body>
</html>