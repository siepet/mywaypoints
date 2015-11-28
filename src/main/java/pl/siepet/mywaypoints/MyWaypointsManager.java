package pl.siepet.mywaypoints;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public void addWaypoint(String waypointName, String waypointDescription, double x, double y, double z){
        Waypoint waypoint = new Waypoint(waypointName, waypointDescription, x, y, z);
        saveWaypointToFile(waypoint);
    }

    public void deleteWaypoint(String waypointName){
        for(int i = 0; i < myWaypoints.size(); i++){
            if(myWaypoints.get(i).getWaypointName().equals(waypointName)){
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
            double x = Double.parseDouble(location.get(0).toString().split(":")[1]);
            double y = Double.parseDouble(location.get(1).toString().split(":")[1]);
            double z = Double.parseDouble(location.get(2).toString().split(":")[1]);
            waypoint = new Waypoint(waypointName, waypointDescription, x, y, z);
            logger.info("Loaded waypoint: " + waypointName);
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
        try {
            FileWriter fileWriter = new FileWriter(waypointsPath + waypoint.getWaypointName() + ".json");
            fileWriter.write(waypoint.toJSON().toJSONString());
            fileWriter.close();
        } catch (IOException e){
            e.getStackTrace();
        }
        logger.info("Saved " + waypoint.getWaypointName() + " to file successfully!");
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
