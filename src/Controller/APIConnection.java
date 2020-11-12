package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import Model.CelestialBody;



public class APIConnection
{
    
    SimulationController controller;
    
    public APIConnection(SimulationController controller) 
    {
        this.controller = controller;
    }
    
    //Calls the online Skybot3d API to return all of the planets' current rectangular coordinates, using the sun as the origin.
    public ArrayList<CelestialBody> CallAPI(LocalDate date, CelestialFactory factory) 
    {
        StringBuilder response = new StringBuilder();
        
        try
        {
            URL url = new URL ("http://vo.imcce.fr/webservices/skybot3d/getPlanet_query.php?-ep="+date.toString()+"&-mime=json&-coord=rectangular&-from=Skybot3dDoc");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = "";

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);           
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8")); 
            
            String responseLine = null;
              
            while ((responseLine = br.readLine()) != null) 
            {
                response.append(responseLine.trim());
            }
            
            controller.setJson(response);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return factory.Build(response);
    }
}
