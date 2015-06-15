/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.forupload;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author shaown
 */
@Path("weather")
public class Weather {

    /**
     * Creates a new instance of weatherKitty
     */
    private double temp;
    private int humidity;
    private String condition;

    public Weather() {
    }

    /**
     * Retrieves representation of an instance of
     * shaown.buet.restapi.weatherKitty
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{name}")
    @Produces("application/json")
    public String getJson(@PathParam("name") String name) throws MalformedURLException, IOException, UnsupportedEncodingException, JSONException {
        //TODO return proper representation object
        String answer, city = "";
        StringTokenizer index = new StringTokenizer(name);

        while (index.hasMoreElements()) {
            city = index.nextToken();
        }
        city = city.replace("?", "");
        calculation(city);
        name = name.toLowerCase();

        if (name.contains("temp")) {
            answer = Double.toString(temp) + " K";
        } else if (name.contains("humid")) {
            answer = Integer.toString(humidity) + "%";
        } else if (name.contains("rain") || name.contains("clouds") || name.contains("clear")) {
            answer = name.contains("rain") ? (condition.toLowerCase().startsWith("r") ? "Yes" : "No") : (name.contains("clouds") ? (condition.toLowerCase().startsWith("clo") ? "Yes" : "No") : (condition.toLowerCase().startsWith("cle") ? "Yes" : "No"));
        } else {
            answer = "Undefined Question !!!! Please ask relevant question";
        }

        return new Gson().toJson(answer);
    }

    /**
     * PUT method for updating or creating an instance of weatherKitty
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    private void calculation(String city) throws UnsupportedEncodingException, MalformedURLException, IOException, JSONException {
        String s = "http://api.openweathermap.org/data/2.5/weather?q=";
        s += URLEncoder.encode(city, "UTF-8");
        URL url = new URL(s);

        // read from the URL
        Scanner scan = new Scanner(url.openStream());
        String str = new String();
        while (scan.hasNext()) {
            str += scan.nextLine();
        }
        scan.close();

        // build a JSON object
        JSONObject obj = new JSONObject(str);

        // get the results
        temp = obj.getJSONObject("main").getDouble("temp");
        humidity = obj.getJSONObject("main").getInt("humidity");

        JSONArray arr = obj.getJSONArray("weather");
        condition = arr.getJSONObject(0).getString("main");

    }
}
