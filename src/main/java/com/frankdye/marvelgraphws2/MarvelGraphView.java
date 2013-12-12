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
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.UnsupportedEncodingException;

//JSON Support
import org.json.*;

import java.util.*;
import java.util.Map.Entry;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import edu.uci.ics.jung.algorithms.shortestpath.ShortestPath;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarvelGraphView {

    Graph<String, String> g;
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    /**
     * Creates a new instance of SimpleGraphView
     */
    public MarvelGraphView() {
        try {
            Path fFilePath;

            /**
             * Assumes UTF-8 encoding. JDK 7+.
             */
            String aFileName = "C:\\Users\\Frank Dye\\Desktop\\Programming Projects\\MarvelGraph\\data\\marvel_labeled_edges.tsv";
            fFilePath = Paths.get(aFileName);

                // New code
            // Read words from file and put into a simulated multimap
            Map<String, List<String>> map1 = new HashMap<String, List<String>>();
            Set<String> set1 = new HashSet<String>();

            Scanner scan1 = null;
            try {
                scan1 = new Scanner(fFilePath, ENCODING.name());
                while (scan1.hasNext()) {
                    processLine(scan1.nextLine(), map1, set1);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

                // Creating an URL object.
            JSONObject newObj = new JSONObject();

            newObj.put("Heroes", set1);

                //Write out our set to a file
            PrintWriter fileWriter = null;
            try {
                fileWriter = new PrintWriter("heroes-json.txt", "UTF-8");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            fileWriter.println(newObj);
            fileWriter.close();

            System.out.println(newObj);

            // Close our scanner
            scan1.close();

            log("Done.");

                // Graph<V, E> where V is the type of the vertices
            // and E is the type of the edges
            g = new SparseMultigraph<String, String>();
                // Add some vertices. From above we defined these to be type Integer.

            for (String s : set1) {
                g.addVertex(s);
            }

            for (Entry<String, List<String>> entry : map1.entrySet()) {
                String issue = entry.getKey();
                ListIterator<String> heroes = entry.getValue().listIterator();
                String firstHero = heroes.next();
                while (heroes.hasNext()) {
                    String secondHero = heroes.next();

                    if (!g.containsEdge(issue)) {
                        g.addEdge(issue, firstHero, secondHero);
                    }
                }
            }

                // Let's see what we have. Note the nice output from the
            // SparseMultigraph<V,E> toString() method
            // System.out.println("The graph g = " + g.toString());
            // Note that we can use the same nodes and edges in two different
            // graphs.
            DijkstraShortestPath alg = new DijkstraShortestPath(g, true);
            Map<String, Number> distMap = new HashMap<String, Number>();
            String n1 = "VOLSTAGG";
            String n4 = "ROM, SPACEKNIGHT";
            
            
            List<String> l = alg.getPath(n1, n4);
            distMap = alg.getDistanceMap(n1);
            System.out.println("The shortest unweighted path from " + n1 + " to "
                    + n4 + " is:");
            System.out.println(l.toString());

            System.out.println("\nDistance Map: " + distMap.get(n4));
        } catch (JSONException ex) {
            Logger.getLogger(MarvelGraphView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processLine(String nextLine, Map<String, List<String>> map1,
            Set<String> set1) {
		// TODO Auto-generated method stub

        // use a second Scanner to parse the content of each line
        Scanner scanner = new Scanner(nextLine);
        scanner.useDelimiter("\t");
        if (scanner.hasNext()) {
            // assumes the line has a certain structure
            String first = scanner.next();
            first = first.replaceAll("^\"|\"$", "");
            String last = scanner.next();
            last = last.replaceAll("^\"|\"$", "");

            // Query map to see if we have an entry for this comic issue
            List<String> l = map1.get(last);
            if (l == null) // No entry for this issue so create it.
            {
                map1.put(last, l = new ArrayList<String>());
            }
			// Issue exists or is created so append the characters name to the
            // map entry for that issue
            l.add(first);
			// Add the characters name to our set to maintain a list of each
            // unique character
            set1.add(first);

        } else {
            log("Empty or invalid line. Unable to process.");
        }
        scanner.close();

    }

    private static void log(Object aObject) {
        // System.out.println(String.valueOf(aObject));
    }
}
