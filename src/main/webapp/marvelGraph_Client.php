<?php

/*
 * Copyright (C) 2013 Frank Dye
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
$wsdl = "http://localhost:8080/MarvelGraphWS/MarvelGraphWS?wsdl";

$hero1 = $_GET[heroOne];
$hero2 = $_GET[heroTwo];


$wsdlUrl = "http://frankdye.com:8080/MarvelGraphWS/MarvelGraphWS?wsdl";
$serviceUrl = "http://frankdye.com:8080/MarvelGraphWS/MarvelGraphWS";

/* Set your parameters for the request */
$params = array(
  "param1" => "Stuff",
  
    
);

var_dump($params);

try {
    $client = new SoapClient($wsdlUrl, array("trace" => 1, "location" => $serviceUrl));
    var_dump($client);
    echo "\n";
    $response = $client->requestShortestPath($params);
    echo "Response: ";
    var_dump($response);
    
    echo "\nLastRequest: ";
    var_dump($client->__getLastRequest());

    echo "\n";
} catch (Exception $exp) {
    echo "EXCEPTION";
}
?>