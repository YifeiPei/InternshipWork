

 function confirm ( ) {

    var name = document.getElementById("name").value;
    var table = document.getElementById("table").value;
	var chair = document.getElementById("chair").value;

    //document.getElementById("demo").innerHTML = name;
    //document.getElementById("demo").innerHTML = table;
    //document.getElementById("demo").innerHTML = chair;

    if(name == "" || chair == "" || table == "" ){
		return false;
	}

    if (Number (table) <= 0 || Number (table) >10) {
        alert("wrong table number, Please type in again");
        window.location = 'index.php';
        return false;
    }

    if ((Number(table) == 1 || Number(table) == 2 || Number(table) == 3 || Number(table) == 4 || Number(table) == 5 || Number(table) == 6 || Number(table) == 7 || Number(table) == 8 || Number(table) == 9 || Number(table) == 10) == false) {
	    alert("invalid value, Please type in again");
        window.location = 'index.php';
        return false;
    }

    if (Number (table) == 1 || Number (table) == 7 || Number (table) == 9) {
        
        if (Number (chair) <= 0 || Number (chair) >11) {
            alert("wrong chair number, Please type in again");
            window.location = 'index.php';
            return false;
        }

        if ((Number(chair) == 1 || Number(chair) == 2 || Number(chair) == 3 || Number(chair) == 4 || Number(chair) == 5 || Number(chair) == 6 || Number(chair) == 7 || Number(chair) == 8 || Number(chair) == 9 || Number(chair) == 10|| Number(chair) == 11) == false) {
	        alert("invalid value, Please type in again");
            window.location = 'index.php';
            return false;
        }

    } else {
        
        if (Number (chair) <= 0 || Number (chair) >10) {
            alert("wrong chair number, Please type in again");
            window.location = 'index.php';
            return false;
        }

        if ((Number(chair) == 1 || Number(chair) == 2 || Number(chair) == 3 || Number(chair) == 4 || Number(chair) == 5 || Number(chair) == 6 || Number(chair) == 7 || Number(chair) == 8 || Number(chair) == 9 || Number(chair) == 10) == false) {
	        alert("invalid value, Please type in again");
            window.location = 'index.php';
            return false;
        }

    }

    

    

    var confData = "confirm="+name+"&tn="+table+"&cn="+chair;

    //document.getElementById("demo").innerHTML = confData;

    var hr = new XMLHttpRequest();
	var url = "processing.php";
    hr.open("POST", url, true);
    hr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    hr.onreadystatechange = function () {
        if (hr.readyState == 4 && hr.status == 200) {
            var return_data = hr.responseText
            //document.getElementById("demo").innerHTML = return_data;
            if (Number(return_data) != 0) {
                alert("This seat has been reserved, please choose another one");
                window.location = 'index.php';
                return false;
            }
            alert("You have reserved the seat");
            window.location = 'index.php';
        }
    }
    hr.send(confData);
  } 

