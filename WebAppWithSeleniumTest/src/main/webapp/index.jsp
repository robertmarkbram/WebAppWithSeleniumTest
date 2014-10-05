<%@page import="com.rmb.sample.SampleData"%>
<html>
<body>
<h2>Hello World!</h2>
<p>You are viewing this page on a <%= new SampleData().getDay()%>.</p>
</body>
</html>
