package Model;

public class Planet extends CelestialBody
{
    
    private String composition;  //Gas/Rocky/Ice
    private String planetType;  //Dwarf/Minor/Major
    
    public Planet(String name, double mass, double radius, double surfaceGravity, String composition, String planetType)
    {
        super();
        this.composition = composition;
        this.planetType = planetType;
        super.setName(name);
        super.setMass(mass);
        super.setRadius(radius);
        super.setSurfaceGravity(surfaceGravity);
    }
    
    public Planet() 
    {
        
    }

    
    
    //-------Getters and Setters-------//
    public String getPlanetType()
    {
        return planetType;
    }

    public void setPlanetType(String planetType)
    {
        this.planetType = planetType;
    }

    
    public String getComposition()
    {
        return composition;
    }

    
    public void setComposition(String composition)
    {
        this.composition = composition;
    }
    
    
}
