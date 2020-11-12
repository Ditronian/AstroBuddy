package Model;

import java.util.ArrayList;

public abstract class CelestialBody
{
    private String name;
    private double mass;            //In kg
    private double radius;          //In km
    private double surfaceGravity;  //In gs
    private double orbitalRadius;
    
    private double xLocation;
    private double yLocation;
    private double zLocation;
    
    private ArrayList<CelestialBody> satellites = new ArrayList<CelestialBody>();

    
    
    //-------Getters and Setters-------//
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getMass()
    {
        return mass;
    }

    public void setMass(double mass)
    {
        this.mass = mass;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public double getSurfaceGravity()
    {
        return surfaceGravity;
    }

    public void setSurfaceGravity(double surfaceGravity)
    {
        this.surfaceGravity = surfaceGravity;
    }

    public double getxLocation()
    {
        return xLocation;
    }

    public void setxLocation(double xLocation)
    {
        this.xLocation = xLocation;
    }

    public double getyLocation()
    {
        return yLocation;
    }

    public void setyLocation(double yLocation)
    {
        this.yLocation = yLocation;
    }

    public double getzLocation()
    {
        return zLocation;
    }

    public void setzLocation(double zLocation)
    {
        this.zLocation = zLocation;
    }

    public double getOrbitalRadius()
    {
        return orbitalRadius;
    }

    public void setOrbitalRadius(double orbitalRadius)
    {
        this.orbitalRadius = orbitalRadius;
    }

    public ArrayList<CelestialBody> getSatellites()
    {
        return satellites;
    }

    public void setSatellites(ArrayList<CelestialBody> satellites)
    {
        this.satellites = satellites;
    }
}
