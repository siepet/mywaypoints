package pl.siepet.mywaypoints;

import java.util.ArrayList;

public class MyWaypointsManager {
    private ArrayList<Waypoint> myWaypoints;

    MyWaypointsManager(){
        Waypoint asd = new Waypoint("ASD", "ASD", 50.1, 50.2, 60.2);
        myWaypoints = new ArrayList<Waypoint>();
        myWaypoints.add(asd);
        //myWaypoints = loadWaypointsFromFiles();

    }

    public ArrayList<Waypoint> getMyWaypoints(){
        return myWaypoints;
    }

    public Waypoint getWaypointByName(String name){
        for(int i = 0; i < myWaypoints.size(); i++){
            if(myWaypoints.get(i).getWaypointName().equals(name)){
                return myWaypoints.get(i);
            }
        }
        return null;
    }

    public void addNewWaypoint(String name, double x, double y, double z){
        Waypoint waypoint = new Waypoint(name, "", x, y, z);
        myWaypoints.add(waypoint);
    }

    public void editWaypoint(String name, double x, double y, double z){
        for(int i = 0; i < myWaypoints.size(); i++){
            if(myWaypoints.get(i).getWaypointName().equals(name)){
                myWaypoints.get(i).setX(x);
                myWaypoints.get(i).setY(y);
                myWaypoints.get(i).setZ(y);
                return;
            }
        }
    }

    public void deleteWaypoint(String name){
        for(int i = 0; i < myWaypoints.size(); i++){
            if(myWaypoints.get(i).getWaypointName().equals(name)){
                myWaypoints.remove(i);
                return;
            }
        }
    }

    public ArrayList<Waypoint> loadWaypointsFromFiles(){
        ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();

        return waypoints;
    }

    public void saveWaypointToFile(Waypoint waypoint){

    }
}
