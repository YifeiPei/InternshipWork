@{
var dataFile = Server.MapPath("~/Persons.txt");
Array userData = File.ReadAllLines(dataFile);
}


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="jquery.js"></script>
<script>
function myFunction(msg) {
    var x, text;
    x = document.getElementById(msg).value;
    //document.getElementById(msg).disabled = 'disabled';
    document.write(x);
}
</script>
</head>
<body>
<div id="wrapper">

   <div id="seats">
       <form>
          ABC and partner 
          <input id="11" type="text"> <button type="button" onclick="myFunction(11)">Submit</button>
          <input id="12" type="text"> <button type="button" onclick="myFunction(12)">Submit</button>
       </form> 
       <form>
          ABC and partner 
          <input id="21" type="text"> <button type="button" onclick="myFunction(21)">Submit</button>
          <input id="22" type="text"> <button type="button" onclick="myFunction(22)">Submit</button>
       </form>
       <form>
          ABC and partner 
          <input id="31" type="text"> <button type="button" onclick="myFunction(31)">Submit</button>
          <input id="32" type="text"> <button type="button" onclick="myFunction(32)">Submit</button>
       </form>
   </div>
   <p id="demo"></p>
</div>

    <h1>Reading Data from a File</h1>
@foreach (string dataLine in userData) 
{
  foreach (string dataItem in dataLine.Split(',')) 
  {@dataItem <text>&nbsp;</text>}
  <br />
}

</body>
</html>