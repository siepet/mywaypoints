package pl.siepet.mywaypoints;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

public class MyWaypointsManager {
    private ArrayList<Waypoint> myWaypoints;
    private final Logger logger;
    private final String waypointsPath;

    MyWaypointsManager(Logger logger, String waypointsPath){
        this.logger = logger;
        this.waypointsPath = waypointsPath;
        myWaypoints = loadWaypointsFromFiles();

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
        Path currentRelativePath = Paths.get("");
        String pathToWaypointFiles = currentRelativePath.toAbsolutePath().toString() + "/plugins/MyWaypoints/";
        File dir = new File(pathToWaypointFiles);
        File [] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".json");
            }
        });

        JSONParser jsonParser = new JSONParser();
        for(File jsonFile : files) {
            waypoints.add(loadWaypointFromFile(jsonFile));
        }
        return waypoints;
    }
    private Waypoint loadWaypointFromFile(File jsonFile){
        Waypoint waypoint;
        JSONParser jsonParser = new JSONParser();
        try {
            Object temp = jsonParser.parse(new FileReader(jsonFile));
            JSONObject jsonWaypoint = (JSONObject)temp;
            String waypointName = (String)jsonWaypoint.get("name");
            String waypointDescription = (String)jsonWaypoint.get("description");
            JSONArray location = (JSONArray)jsonWaypoint.get("location");
            double x = Double.parseDouble(location.get(0).toString());
            double y = Double.parseDouble(location.get(1).toString());
            double z = Double.parseDouble(location.get(2).toString());
            waypoint = new Waypoint(waypointName, waypointDescription, x, y, z);
            logger.info("Added waypoint: " + waypointName);
        } catch (IOException e) {
            e.getStackTrace();
            return null;
        } catch (ParseException e) {
            e.getStackTrace();
            return null;
        }
        return waypoint;
    }

    public void saveWaypointToFile(Waypoint waypoint){


    }

    public static void saveInitialSpawnWaypoint(String path, double x, double y, double z) {
        File spawnFile = new File(path);
        if(spawnFile.exists()){
            return;
        }
        try (FileWriter fileWriter = new FileWriter(path)){
            Waypoint spawnWaypoint = new Waypoint("Spawn", "Initial spawn for your server!", x, y, z);
            fileWriter.write(spawnWaypoint.toJSON().toJSONString());
            fileWriter.close();
        } catch(IOException e){
            e.getStackTrace();
        }
    }
}
