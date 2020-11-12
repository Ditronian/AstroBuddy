package Controller;

import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import Model.CelestialBody;
import Model.Moon;
import Model.Planet;
import Model.Star;

public class CelestialFactory
{
    public ArrayList<CelestialBody> Build(StringBuilder jsonString)
    {
        //Prepare the JSON
        JsonObject jsonObject = new JsonParser().parse(jsonString.toString()).getAsJsonObject();
        JsonArray planets = jsonObject.getAsJsonArray("planets");
        
        ArrayList<CelestialBody> bodies = new ArrayList<CelestialBody>();
        
        CelestialBody sun = new Star();
        sun.setName("Sun");
        sun.setMass(1.9885*Math.pow(10,30));
        sun.setRadius(695700);
        sun.setSurfaceGravity(28);
        sun.setxLocation(0);
        sun.setyLocation(0);
        sun.setzLocation(0);
        bodies.add(sun);
        
        //Foreach body in the JSON, make an appropriate celestial body for it.
        for(int i = 0; i<planets.size();i++)
        {
           JsonArray body = (JsonArray) planets.get(i);
           
           String rawID = body.get(0).getAsString();
           
           //int id = body.get(0).getAsInt();
           String name = body.get(8).getAsString();
           String type = body.get(10).getAsString();
           double xCoord = body.get(1).getAsDouble();
           double yCoord = body.get(2).getAsDouble();
           double zCoord = body.get(3).getAsDouble();
           
           CelestialBody newBody;
           
           if(type.equals("Planet")) 
           {
               newBody = new Planet();
               bodies.add(newBody);
           }
           else 
           {
               newBody = new Moon();
               
               //Attach satellite to its parent
               int parent = Integer.parseInt(rawID.substring(0,1));
               bodies.get(parent).getSatellites().add(newBody);
           }
           
           newBody.setName(name);
           newBody.setxLocation(xCoord);
           newBody.setyLocation(yCoord);
           newBody.setzLocation(zCoord);
           BodyBuilder(newBody, name);
        }
        
        return bodies;
    }
    
    //Hack to give scientific data to planets
    private void BodyBuilder(CelestialBody body, String name) 
    {
        if(name.equals("Mercury")) 
        {
            body.setMass(3.3011*Math.pow(10,23));
            body.setRadius(2439.7);
            body.setSurfaceGravity(0.38);
        }
        else if(name.equals("Venus")) 
        {
            body.setMass(4.8675*Math.pow(10,24));
            body.setRadius(6051.8);
            body.setSurfaceGravity(0.904);
        }
        else if(name.equals("Earth")) 
        {
            body.setMass(5.97237*Math.pow(10,24));
            body.setRadius(6371.0);
            body.setSurfaceGravity(1);
            body.setOrbitalRadius(149600000);
        }
        else if(name.equals("Mars")) 
        {
            body.setMass(6.4171*Math.pow(10,23));
            body.setRadius(3389.5);
            body.setSurfaceGravity(0.3794);
        }
        else if(name.equals("Jupiter")) 
        {
            body.setMass(1.8982*Math.pow(10,27));
            body.setRadius(69911);
            body.setSurfaceGravity(2.528);
        }
        else if(name.equals("Saturn")) 
        {
            body.setMass(5.6834*Math.pow(10,26));
            body.setRadius(58232);
            body.setSurfaceGravity(1.065);
        }
        else if(name.equals("Uranus")) 
        {
            body.setMass(8.6810*Math.pow(10,25));
            body.setRadius(25362);
            body.setSurfaceGravity(0.886);
        }
        else if(name.equals("Neptune")) 
        {
            body.setMass(1.024*Math.pow(10,26));
            body.setRadius(24622);
            body.setSurfaceGravity(1.14);
        }
    }
}
