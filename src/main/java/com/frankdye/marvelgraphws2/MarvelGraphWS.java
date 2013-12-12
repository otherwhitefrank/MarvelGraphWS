package com.frankdye.marvelgraphws2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Frank Dye
 */
/**
 * MarvelGraph *MarvelGraphWS.java Nov 14, 2013:12:32:14 PM
 */
/**
 * @author Frank Dye
 *
 */


import java.util.HashSet;
import java.util.Set;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Holder;

@WebService(serviceName = "MarvelGraphWSService", portName = "MarvelGraphWSPort")
public class MarvelGraphWS {

    @WebMethod()
    @WebResult(name = "EdgeSet",
            targetNamespace = "http://frankdye.com:8080/MarvelGraphWS")
    public Set<String> requestShortestPath(
            @WebParam(name = "param1", mode = WebParam.Mode.IN) String param1)
    {
        Set<String> mySet = new HashSet<String>();
        mySet.add(param1);
        mySet.add("Stuff2");
        mySet.add("Some other Stuff");
        

        return mySet;

    }
}
