<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Timetable</title>
        <style type="text/css">
            body { font-family:Arial, Helvetica, Sans-Serif; font-size:0.8em;}
            #report { border-collapse:collapse;}
            #report h4 { margin:0px; padding:0px;}
            #report img { float:right;}
            #report ul { margin:10px 0 10px 40px; padding:0px;}
            #report th { background:#7CB8E2 url(header_bkg.png) repeat-x scroll center left; color:#fff; padding:7px 15px; text-align:left;}
            #report td { background:#C7DDEE none repeat-x scroll center left; color:#000; padding:7px 15px; }
            #report tr.odd td { background:#fff url(row_bkg.png) repeat-x scroll center left; cursor:pointer; }
            #report div.arrow { background:transparent url(arrows.png) no-repeat scroll 0px -16px; width:16px; height:16px; display:block;}
            #report div.up { background-position:0px 0px;}
        </style>
        <link rel='stylesheet' href='theme.css' />
        <link href='fullcalendar/fullcalendar.css' rel='stylesheet' />
        <link href='fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
        <script src='jquery/jquery-1.9.1.min.js'></script>
        <script src='jquery/jquery-ui-1.10.2.custom.min.js'></script>
        <script src='fullcalendar/fullcalendar.min.js'></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#report tr:odd").addClass("odd");
                $("#report tr:not(.odd)").hide();
                $("#report tr:first-child").show();

                $("#report tr.odd").click(function() {
                    $(this).next("tr").toggle();
                    $(this).find(".arrow").toggleClass("up");
                });
            });
        </script>
        <script>

            var myEvent = {
                        id:111,
			title: "Event1",
                        allDay: false,
			start: "2013-06-04 13:00:00",
                        end: "2013-06-04 17:00:00",
                        editable: false 
            };
            
            $(document).ready(function() {

                $('#calendar').fullCalendar({
                    theme: true,
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay'
                    },
                    editable: false,
                    weekends: false,
                    allDaySlot: false,
                    minTime: 8,
                    maxTime: 19
                });

            });
            
            function addClass(arreglo){
                
                if(isOverlapping(arreglo)){
                    alert("Existo");
                }else{
                    $('#calendar').fullCalendar('renderEvent', arreglo);
                }
            }
            
            
            function isOverlapping(event){
            var array = $('#calendar').fullCalendar('clientEvents');
            for(i in array){
                if(array[i].id !== event.id){
                    if(!(array[i].start >= event.end || array[i].end <= event.start)){
                    return true;
                    }
                }
            }
            return false;
            }
            
            
            function deleteClass (classId){
                $('#calendar').fullCalendar('removeEventSource', classId);
                $('#calendar').fullCalendar('refetchEvents');
            }
            
            
            

        </script>    
        <style>

            body {
                margin-top: 40px;
                text-align: center;
                font-size: 12px;
                font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
            }

            #calendar {
                width: 600px;
                margin: 0 auto;
            }

        </style>    

    </head>
    <body>
        
        <table id="report">
        <tr>
            <th>Class</th>
            <th>Subject</th>
            <th>Component</th>
          <th>Availability</th>
            <th>Campus</th>
            <th></th>
        </tr>
        <tr>
        
            <?php
           $classes = array(array(29339,"COMP SCI 7000 Software Architecture", "Lecture", 18, 
               "NTRCE","2013-08-01 - 2013-09-19: Thursday: 11:00 - 13:00: Ingkarni Wardli, B21, Teaching Suite", 
                   "2013-10-10 - 2013-10-31: Thursday: 11:00 - 13:00: Ingkarni Wardli, B21, Teaching Suite"),
                            array(22963,"COMP SCI 7007 Specialised Programming", "Lecture", 23,
                "NTRCE", "2013-07-29 - 2013-09-16: Monday: 15:00 - 17:00: Ingkarni Wardli, B18, Teaching Suite",
                "2013-10-07 - 2013-10-28: Monday: 15:00 - 17:00: Ingkarni Wardli, B18, Teaching Suite"));
           
           $timeClasses = array(array('id' => 29339, 'title' => "COMP SCI 7000 Software Architecture"
                       , 'allDay' => false, 'start' => "2013-08-01T11:00:00Z", 'end' => "2013-08-01T13:00:00Z", 'editable' => false), array('id' => 29339, 'title' => "COMP SCI 7000 Software Architecture"
                       , 'allDay' => false, 'start' => "2013-09-19T11:00:00Z", 'end' => "2013-09-19T13:00:00Z", 'editable' => false));
           
       for ($index = 0; $index < count($classes); $index++) {
           for ($index1 = 0; $index1 < count($classes[$index]); $index1++) {
               
               if ($index1 < 5){
                   echo "<td>".$classes[$index][$index1]."</td>";
               }
               if ($index1 == 5) {
                   echo "<td><div class=\"arrow\"></div></td>";
                   echo "</tr>";
                   echo "<tr>";
                   echo "<td colspan=\"6\">";
                   echo "<h4>Additional information</h4>";
                   echo "<ul>";
               }
               
               if ($index1 >= 5){
                   echo "<li>".$classes[$index][$index1]."</li>";
               }
               
               if ($index1 == count($classes[$index]) - 1){
                   echo "</ul>";
                   
                   //next cycle
                   echo "<img src=\"accept-button.png\" width=\"60\" height=\"40\" onclick=\"addClass(myEvent);\">";
                   echo "<img src=\"delete-button.png\" width=\"60\" height=\"40\" onclick=\"deleteClass(myEvent[0]);\">";
                   echo "</td>";
                   echo "</tr>";
               }

           }
    
        }

            ?>
              
        </table>
            
    <div id='calendar'></div>

        
    </body>
</html>
