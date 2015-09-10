<?php 

include ("connect.php");



if (isset($_POST['confirm'])){

	
	$name = preg_replace('#[^a-z0-9 ]#i', '', $_POST['confirm']);
	$table = preg_replace('#[^0-9 ]#i', '', $_POST['tn']);
	$chair = preg_replace('#[^0-9 ]#i', '', $_POST['cn']);

	// If POSTS are empty after cleaning, exit
	if($name == "" || $table == "" || $chair == ""){
		exit();
	}

    $sql = "SELECT * FROM reservation_test WHERE tablenum='$table' AND chairnum='$chair'";
    $query = sqlsrv_query($connect, $sql, array(), array( "Scrollable" => SQLSRV_CURSOR_KEYSET ));
    if ($query === false) {exit("<pre>".print_r(sqlsrv_errors(),true));}
    $check = sqlsrv_num_rows($query);

    echo "$check";

    if ($check != 0){
    
        //alert("This seat has been reserved, please choose another one");
        //window.location = 'index.php';

        exit();
    
    }
	
    $sql = "INSERT INTO reservation_test(name,tablenum,chairnum) VALUES ('$name','$table','$chair')";
	$query = sqlsrv_query($connect, $sql);
	if ($query === false) {exit("<pre>".print_r(sqlsrv_errors(),true));}

	exit();
	
}


?>