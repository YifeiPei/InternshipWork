<?php 

include ("connect.php");

$chart = "";

$sql = "SELECT * FROM available"; 
$query = sqlsrv_query($connect, $sql);
if ($query === false) {exit("<pre>".print_r(sqlsrv_errors(),true));}



?>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="index.css">
<script src="jquery.js"></script>
<script>
  $(document).ready(function () {
    $('#toggle-view li').click(function () {
      var text = $(this).children('div.panel');
      if (text.is(':hidden')) {
        text.slideDown('200');
        $(this).children('span').html('-');        
      } else {
        text.slideUp('200');
        $(this).children('span').html('+');        
      }
    });
  });
</script>
</head>
<body>

<div id="wrapper">

   <div id="floorplan"> 
        <img src="LSR.png" alt="floorplan" style="width:958px;height:650px">
   </div>
   <div id="seats">
       <div >
        <ul id="toggle-view" style="list-style-type:none">
            <li>
              <h3>Title 1</h3>
              <span>+</span>
              <div class="panel">
                <p>chair1</p>
                <p>chair2</p>
                <p>chair3</p>
              </div>
            </li>
            <li>
              <h3>Title 2</h3>
              <span>+</span>
              <div class="panel">
                <p>chair1</p>
                <p>chair2</p>
                <p>chair3</p>
              </div>
            </li>
             <li>
               <h3>Title 3</h3>
               <span>+</span>
               <div class="panel">
                <p>chair1</p>
                <p>chair2</p>
                <p>chair3</p>
               </div>
             </li>
        </ul>
       </div>  
       
   </div>
   <div id="returnData">

   </div>

</div>

</body>

</html>