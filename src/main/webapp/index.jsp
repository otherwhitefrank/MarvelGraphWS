<%-- 
    Document   : index
    Created on : Nov 14, 2013, 1:30:48 PM
    Author     : Frank Dye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
        <script>
            
            
            $(document).ready(function() {

                /* Send the data using post and put the results in a div */
                var formData = {heroOne:"ravi",heroTwo:"31"};  
                $.ajax({
                    type: "GET",
                    url: "marvelGraph_Client.php",
                    data: formData,
                    
                    success: function(data, textStatus, jqXHR)
                    {
                        //data - response from server
                        alert("Success");
                        console.log(data);
                    },
                    error: function (jqXHR, textStatus, errorThrown)
                    {
                        alert("Error");
                        alert(jqXHR);
                    }
                });
                
                
            });
            

        </script>
    </body>
</html>
