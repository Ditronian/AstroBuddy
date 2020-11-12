package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IO
{
    private static String filePath = "data.json";
    
    
    //Tries to write the json string provided to the file
    public static boolean WriteData(String json)
    {
        try
        {
            File file = new File(filePath);
            
            //Create New file if does not exist, else overwrite
            if(!file.exists()) file.createNewFile();
            
            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(json);
            buffer.close();
            writer.close();
        }
        
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    //Tries to Read JSON Data from the file
    public static String ReadData() 
    {
        
        String data = "";
        
        try
        {
            File file = new File(filePath);
            if(file.exists()) 
            {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) 
                {
                    data = scanner.nextLine();
                }
                scanner.close();
            }
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }
}
