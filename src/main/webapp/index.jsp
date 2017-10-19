<html>
<body>
	<h2>Hello Nikhil</h2>

	<form id="demo" action="TwilioServlet" method="post"></form>
	<h2>Message replied back</h2>
	<%
		System.out.println("In index.jsp");
	%>
</body>

<script>
	function nikHtmlFun() {
		var nikHtmlform = document.getElementById("demo");
		nikHtmlform.submit();
		alert('Message sent');
	}
</script>

</html>
