package Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import Model.CelestialBody;
import Model.Moon;
import Model.Planet;
import Model.Star;
import View.View;
import javafx.scene.paint.Color;

public class SimulationController
{
    ArrayList<CelestialBody> bodies;
    CelestialFactory factory = new CelestialFactory();
    APIConnection connection = new APIConnection(this);
    StringBuilder json = new StringBuilder();
    
    public SimulationController() 
    {
        //Attempts to Load any previously saved data
        String readAttempt = IO.ReadData();
        
        if(!readAttempt.equals("")) 
        {
            json.append(readAttempt);
            bodies = factory.Build(json);
            DrawBodies();
        }
    }
    
    //Grabs the Bodies and Locations from API
    public void RetrieveAPIBodies(LocalDate date)
    {
        bodies = connection.CallAPI(date, factory);
        DrawBodies();
    }
    
    //Simply Saves the Project's data
    public String Save() 
    {
        String response = "";
        
        if(json != null) 
        {
            boolean result = IO.WriteData(json.toString());
            if(result) response = "Save Successful!";
            else response = "Save Failed!";
        }
        else response = "There is no data to save!";
        
        return response;
    }
    
    //Commands the View to draw all the bodies in the arraylist, as well as their orbital paths
    private void DrawBodies() 
    {
        //View.DrawBody(10, 375, 375, 10, Color.YELLOW);
        double scale = 80;
        for(int i = 0; i<bodies.size();i++)
        {
            CelestialBody body = bodies.get(i);
            double x = 2500+body.getxLocation()*scale;
            double y = 2500+body.getyLocation()*scale;
            
            if(body instanceof Star) View.DrawBody(i, x, y, 20, Color.YELLOW);
            else if(body instanceof Planet) View.DrawBody(i, x, y, 15, Color.BROWN);
            else if(body instanceof Moon) View.DrawBody(i, x, y, 4, Color.GREY);
            
            View.DrawOrbit(2500, CartesianToPolar(body.getxLocation()*scale, body.getyLocation()*scale));
        }
    }
    
    //Simply converts from cartesian to a radius
    private double CartesianToPolar(double x, double y) 
    {
        return Math.sqrt(x*x + y*y);
    }

    public ArrayList<CelestialBody> getBodies()
    {
        return bodies;
    }

    public void setBodies(ArrayList<CelestialBody> bodies)
    {
        this.bodies = bodies;
    }

    public CelestialFactory getFactory()
    {
        return factory;
    }

    public void setFactory(CelestialFactory factory)
    {
        this.factory = factory;
    }

    public APIConnection getConnection()
    {
        return connection;
    }

    public void setConnection(APIConnection connection)
    {
        this.connection = connection;
    }


    public StringBuilder getJson()
    {
        return json;
    }

    public void setJson(StringBuilder response)
    {
        this.json = response;
    }
    
     
}
