<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Find flights</title>
	</head>
	<body>
		<h2>Find flights</h2>
		<form action="findFlights" method="get">
			From: <input type="text" name="from" /><br/>
			To: <input type="text" name="to" /><br/>
			Departure Date: <input type="text" name="departureDate" /><br/>
			<input type="submit" value="search" /><br/><br/>
			<a href="/flightreservation/admin/showAddFlight">Add Flight - Only Admin users</a>
		</form>
	</body>
</html>