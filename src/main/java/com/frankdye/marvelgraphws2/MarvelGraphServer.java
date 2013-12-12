package com.frankdye.marvelgraphws2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javax.xml.ws.Endpoint;

/**
 * @author Frank Dye
 * 
 */

public class MarvelGraphServer {

	public static void main(String[] args) {

		Endpoint.publish("http://localhost:8080/MarvelGraphWS", new MarvelGraphWS());

		System.out.println("MarvelGraph service is ready");

	}

}
