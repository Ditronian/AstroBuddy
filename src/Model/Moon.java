package Model;

public class Moon extends CelestialBody
{
    
    
    public Moon(String name, double mass, double radius, double surfaceGravity)
    {
        super();
        super.setName(name);
        super.setMass(mass);
        super.setRadius(radius);
        super.setSurfaceGravity(surfaceGravity);
    }
    
    public Moon()
    {
        
    }
}
