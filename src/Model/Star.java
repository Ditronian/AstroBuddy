package Model;

public class Star extends CelestialBody
{
    private char starType;
    private double luminosity;
    
    public Star(String name, double mass, double radius, double surfaceGravity, char starType, double luminosity)
    {
        super();
        this.starType = starType;
        this.luminosity = luminosity;
        super.setName(name);
        super.setMass(mass);
        super.setRadius(radius);
        super.setSurfaceGravity(surfaceGravity);
    }
    
    public Star() 
    {
        
    }
    //-------Getters and Setters-------//
    public char getStarType()
    {
        return starType;
    }

    public void setStarType(char starType)
    {
        this.starType = starType;
    }

    public double getLuminosity()
    {
        return luminosity;
    }

    public void setLuminosity(double luminosity)
    {
        this.luminosity = luminosity;
    }
    

    
    
}
