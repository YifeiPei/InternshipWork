<?php
//**** START Database connection script
// Connect to database script
include ("connect.php");
//**** END Database connection script

//**** START Clean out expired reservations
//**** Amount of time that reservations are held is set here 3 minutes
// Get list of expired seats from 1 table and original number of seats from another table
$clean = "SELECT r.*, a.avail
		  FROM reserves_test AS r
		  LEFT JOIN available_test AS a ON a.tablenum = r.tablenumber
		  WHERE r.restime < DATEADD (MINUTE, -3, GETDATE())";

//echo "<p> $clean </p>";

$freequery = sqlsrv_query($connect, $clean, array(), array( "Scrollable" => SQLSRV_CURSOR_KEYSET )); 
if ($freequery === false) {exit("<pre>".print_r(sqlsrv_errors(),true));}
$num_check = sqlsrv_num_rows($freequery);

//echo "<p> $num_check </p>";

if ($num_check != 0){
	while ($row = sqlsrv_fetch_array($freequery/*, SQLSRV_FETCH_ASSOC*/)){
		$dI = $row[tablenumber];
		$dS = $row[numseats];
		$dIdRow = $row[id];
		$originalavail = $row[avail];

        //echo "<p> $dI </p>";
        //echo "<p> $dS </p>";
        //echo "<p> $dIdRow </p>";
        //echo "<p> $originalavail </p>";

		// Add back the expired reserves
		$updateAvailable = $originalavail + $dS;
			
		// Delete the reserves
		$sql3 = "DELETE TOP (1) FROM reserves_test WHERE tablenumber='$dI' ";
        //$sql3 = "DELETE FROM reserves_test WHERE tablenumber='$dI' TOP 1";
		$query3 = sqlsrv_query($connect, $sql3);
		// Update the database with newly available seats
        //$sql3 = "UPDATE available_test SET avail='$updateAvailable' WHERE avail IN (SELECT TOP 1 avail FROM available_test WHERE tablenum='$dI') ";
        //$sql3 = "UPDATE available_test SET avail='$updateAvailable' WHERE tablenum='$dI' TOP 1";
		//$sql3 = "UPDATE TOP (1) available_test SET avail='$updateAvailable' WHERE tablenum='$dI' ";
        $sql3 = "
                 WITH C AS
                 (SELECT TOP (1) avail
                  FROM available_test
                  WHERE tablenum='$dI'
                 )
                 UPDATE C
                 SET avail='$updateAvailable'
        ";
		$query3 = sqlsrv_query($connect, $sql3);
	}
}
//**** END Clean out expired reservations

//**** START Get initial state of tables with seats after clean up
// Initialize our output to NULL
$chart = "";
// Query for tables with seats available
$sql = "SELECT * FROM available_test";
$query = sqlsrv_query($connect, $sql, array(), array( "Scrollable" => SQLSRV_CURSOR_KEYSET ));
if ($query === false) {exit("<pre>".print_r(sqlsrv_errors(),true));}
// Loop and get all the data
while ($row = sqlsrv_fetch_array($query, SQLSRV_FETCH_ASSOC)){
	// Assign table data to variables
	$id = $row[id];
	$tablenum = $row[tablenum];
	$avail = $row[avail];
	$price = $row[price];

    //echo "<p> $id </p>";
    //echo "<p> $tablenum </p>";
    //echo "<p> $avail </p>";
    //echo "<p> $price </p>";

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