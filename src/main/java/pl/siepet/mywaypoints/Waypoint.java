package pl.siepet.mywaypoints;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Waypoint {
    private String waypointName;
    private String waypointDescription;
    private double x;
    private double y;
    private double z;

    public Waypoint(String name, String desc, double x, double y, double z){
        this.waypointName = name;
        this.waypointDescription = desc;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getWaypointName() {
        return waypointName;
    }

    public void setWaypointName(String waypointName) {
        this.waypointName = waypointName;
    }

    public String getWaypointDescription() {
        return waypointDescription;
    }

    public void setWaypointDescription(String waypointDescription) {
        this.waypointDescription = waypointDescription;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public JSONObject toJSON(){
        JSONObject self = new JSONObject();
        self.put("name", getWaypointName());
        self.put("description", getWaypointDescription());
        JSONArray location = new JSONArray();
        location.add("x: " + getX());
        location.add("y: " + getY());
        location.add("z: " + getZ());
        self.put("location", location);
        return self;
    }
}
