<?php
//**** START Database connection script
// Connect to database script
include ("connect.php");
//**** END Database connection script

//**** START Clean out expired reservations
//**** Amount of time that reservations are held is set here 3 minutes
// Get list of expired seats from 1 table and original number of seats from another table
$clean = "SELECT r.*, a.avail
		  FROM reserves AS r
		  LEFT JOIN available AS a ON a.tablenum = r.tablenumber
		  WHERE r.restime < (NOW() - INTERVAL 3 MINUTE)";
$freequery = mysqli_query($connect, $clean) or die (mysqli_error($connect));
$num_check = mysqli_num_rows($freequery);
if ($num_check != 0){
	while ($row = mysqli_fetch_array($freequery, MYSQLI_ASSOC)){
		$dI = $row['tablenumber'];
		$dS = $row['numseats'];
		$dIdRow = $row['id'];
		$originalavail = $row['avail'];
		// Add back the expired reserves
		$updateAvailable = $originalavail + $dS;
			
		// Delete the reserves
		$sql3 = "DELETE FROM reserves WHERE tablenumber='$dI' LIMIT 1";
		$query3 = mysqli_query($connect, $sql3);
		// Update the database with newly available seats
		$sql3 = "UPDATE available SET avail='$updateAvailable' WHERE tablenum='$dI' LIMIT 1";
		$query3 = mysqli_query($connect, $sql3);
	}
}
//**** END Clean out expired reservations

//**** START Get initial state of tables with seats after clean up
// Initialize our output to NULL
$chart = "";
// Query for tables with seats available
$sql = "SELECT * FROM available";
$query = mysqli_query($connect, $sql) or die (mysqli_error($connect));
// Loop and get all the data
while ($row = mysqli_fetch_array($query, MYSQLI_ASSOC)){
	// Assign table data to variables
	$id = $row['id'];
	$tablenum = $row['tablenum'];
	$avail = $row['avail'];
	$price = $row['price'];
	// Build display output
	// Display for tables with no available seats
	if ($avail == 0){
		$chart .= '<div class="full"><div class="numSeats">0 Seats Available</div></div>';		
	} else {
		// Display for tables with available seats - clickable inner div
		$chart .= '<div class="available"><div id="tbl_'.$id.'" class="numSeats" onClick="showSeats(this.id)">'.$avail.' Seats Available</div></div>';		
	}
}
$chart .= '<div class="clear">';
//**** END Get initial state of tables with seats after clean up
?>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="auction.css">
<script src="reservations.js"></script>
</head>
<body>
<div id="wrapper">
   <div id="stage"></div>
   <div id="seats">
      <?php echo $chart; ?>   
   </div>
   <div id="returnData">Click Available Tables To Get Seats</div>
</div>
</body>
</html>