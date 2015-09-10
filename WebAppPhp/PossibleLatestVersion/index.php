<?php 

include ("connect.php");

$chart ="";
$chart1 = "";
$chart2 = "";
$chart3 = "";
$chart4 = "";
$chart5 = "";
$chart6 = "";
$chart7 = "";
$chart8 = "";
$chart9 = "";
$chart10 = "";

$sql = "SELECT * FROM reservation_test"; 
$query = sqlsrv_query($connect, $sql, array(), array( "Scrollable" => SQLSRV_CURSOR_KEYSET ));
if ($query === false) {exit("<pre>".print_r(sqlsrv_errors(),true));}

$num_check = sqlsrv_num_rows($query);

if ($num_check != 0){

  while ($row = sqlsrv_fetch_array($query, SQLSRV_FETCH_ASSOC)){

      $id = $row[id];
      $name = $row[name];
      $table = $row[tablenum];
      $chair = $row[chairnum];

      $type = gettype($table);

      //echo "<p> $type </p>";
      // echo "<p> $table </p>";

      if (strcmp($table, "1") == 0) {
          $chart1 .= '<div id="'.$id.'"> <p> Name: '.$name.' Reserved chair: '.$chair.' </P> </div>';
      }

      if (strcmp($table, "2") == 0) {
          $chart2 .= '<div id="'.$id.'"> <p> Name: '.$name.' Reserved chair: '.$chair.' </P> </div>';
      }

      if (strcmp($table, "3") == 0) {
          $chart3 .= '<div id="'.$id.'"> <p> Name: '.$name.' Reserved chair: '.$chair.' </P> </div>';
      }

      if (strcmp($table, "4") == 0) {
          $chart4 .= '<div id="'.$id.'"> <p> Name: '.$name.' Reserved chair: '.$chair.' </P> </div>';
      }

      if (strcmp($table, "5") == 0) {
          $chart5 .= '<div id="'.$id.'"> <p> Name: '.$name.' Reserved chair: '.$chair.' </P> </div>';
      }

      if (strcmp($table, "6") == 0) {
          $chart6 .= '<div id="'.$id.'"> <p> Name: '.$name.' Reserved chair: '.$chair.' </P> </div>';
      }

      if (strcmp($table, "7") == 0 ) {
          $chart7 .= '<div id="'.$id.'"> <p> Name: '.$name.' Reserved chair: '.$chair.' </P> </div>';
      }

      if (strcmp($table, "8") == 0) {
          $chart8 .= '<div id="'.$id.'"> <p> Name: '.$name.' Reserved chair: '.$chair.' </P> </div>';
      }

      if (strcmp($table, "9") == 0) {
          $chart9 .= '<div id="'.$id.'"> <p> Name: '.$name.' Reserved chair: '.$chair.' </P> </div>';
      }

      if (strcmp($table, "10") == 0) {
          $chart10 .= '<div id="'.$id.'"> <p> Name: '.$name.' Reserved chair: '.$chair.' </P> </div>';
      }
  }
}

?>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="index.css">
<script src="jquery.js"></script>
<script src="confirm.js"></script>
<script>
    function showChair1() {
        $('#chair1').toggle(500);
    };
    function showChair2() {
        $('#chair2').toggle(500);
    };
    function showChair3() {
        $('#chair3').toggle(500);
    };
    function showChair4() {
        $('#chair4').toggle(500);
    };
    function showChair5() {
        $('#chair5').toggle(500);
    };
    function showChair6() {
        $('#chair6').toggle(500);
    };
    function showChair7() {
        $('#chair7').toggle(500);
    };
    function showChair8() {
        $('#chair8').toggle(500);
    };
    function showChair9() {
        $('#chair9').toggle(500);
    };
    function showChair10() {
        $('#chair10').toggle(500);
    };
</script>
</head>
<body>

<div id="wrapper">

   <div id="floorplan"> 
        <img src="LSR.png" alt="floorplan" style="width:958px;height:650px">
   </div>
   <div id="register">
      <p>This is an Example: <br>
         Name: Tony Bezuidenhout <br>
         Table: 7 <br>
         Chair: 10 <br>
      </p>
      <div class="container">
      <form>
          Name
          <input id="name" type="text" > <br>
          Table
          <input id="table" type="text" > <br>
          Chair
          <input id="chair" type="text" > <br>
          <button type="button" onclick="confirm()">Submit</button>
       </form> 
       </div>
   </div>

   <div id="showboard">
      <p>Reservation so far: <br>
         you can click on the table to hide and show the chair reservations under it</p>
       <div id="table1"><h3 style="cursor: pointer; display: inline-block" onclick="showChair1 ();">Table 1</h3> <div id="chair1"> <?php echo $chart1; ?> </div> </div>
       <div id="table2"><h3 style="cursor: pointer; display: inline-block" onclick="showChair2 ();">Table 2</h3> <div id="chair2"> <?php echo $chart2; ?> </div> </div>
       <div id="table3"><h3 style="cursor: pointer; display: inline-block" onclick="showChair3 ();">Table 3</h3> <div id="chair3"> <?php echo $chart3; ?> </div> </div>
       <div id="table4"><h3 style="cursor: pointer; display: inline-block" onclick="showChair4 ();">Table 4</h3> <div id="chair4"> <?php echo $chart4; ?> </div> </div>
       <div id="table5"><h3 style="cursor: pointer; display: inline-block" onclick="showChair5 ();">Table 5</h3> <div id="chair5"> <?php echo $chart5; ?> </div> </div>
       <div id="table6"><h3 style="cursor: pointer; display: inline-block" onclick="showChair6 ();">Table 6</h3> <div id="chair6"> <?php echo $chart6; ?> </div> </div>
       <div id="table7"><h3 style="cursor: pointer; display: inline-block" onclick="showChair7 ();">Table 7</h3> <div id="chair7"> <?php echo $chart7; ?> </div> </div>
       <div id="table8"><h3 style="cursor: pointer; display: inline-block" onclick="showChair8 ();">Table 8</h3> <div id="chair8"> <?php echo $chart8; ?> </div> </div>
       <div id="table9"><h3 style="cursor: pointer; display: inline-block" onclick="showChair9 ();">Table 9</h3> <div id="chair9"> <?php echo $chart9; ?> </div> </div>
       <div id="table10"><h3 style="cursor: pointer; display: inline-block" onclick="showChair10 ();">Table 10</h3> <div id="chair10"> <?php echo $chart10; ?></div></div> 
   </div>

   <div id="returnData">
       <p id="demo"></p>
   </div>

</div>

</body>

</html>