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
			<pre>
				From:           <input type="text" name="from" /><br/>
				To:             <input type="text" name="to" /><br/>
				Departure Date: <input type="text" name="departureDate" /><br/>
				<input type="submit" value="search" /><br/><br/>
				<a href="/flightreservation/admin/showAddFlight">Add Flight (Admin users)</a><br/><br/>
				<a href="/flightreservation/generateReport">Generate Report</a><br/><br/>
				<a href="/flightreservation/displayUpload">Upload documents/images</a>
			</pre>
		</form>
	</body>
</html>