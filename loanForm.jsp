//JSP file for main 

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Loan Calculator Result</title>
</head>
<body>
    <h2>Loan Calculation Result</h2>
    <p>Monthly Payment: $<%= request.getAttribute("monthlyPayment") %></p>
    <p>Total Payment: $<%= request.getAttribute("totalPayment") %></p>
    <p>Monthly Payment: $<%= request.getAttribute("monthlyPayment") %></p>
    <p>Total Payment: $<%= request.getAttribute("totalPayment") %></p>
</body>
</html>
