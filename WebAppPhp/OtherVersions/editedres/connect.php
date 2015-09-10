<?php
// Put your specific sql Server database connection data below
// host, user, password, database name in between the double quotes ---> " here "
$server = "testsql";
$options = array(  "UID" => "yifpei",  "PWD" => "1234",  "Database" => "reservation_sc");
$connect = sqlsrv_connect($server, $options);
// Evaluate the connection
if ($conn === false) die("<pre>".print_r(sqlsrv_errors(), true));
//echo "Successfully connected!";
?>