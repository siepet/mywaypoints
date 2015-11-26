package pl.siepet.mywaypoints;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MyWaypointsCommandExecutor implements CommandExecutor {
    private final Logger logger;
    private final String myWaypointsPath;

    MyWaypointsCommandExecutor(Logger logger, String myWaypointsPath){
        this.logger = logger;
        this.myWaypointsPath = myWaypointsPath;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] arguments) {
        if(arguments.length < 1) {
            return false;
        }

        Player player = (Player)commandSender;
        MyWaypointsManager waypointsManager = new MyWaypointsManager(logger, myWaypointsPath);

        if(arguments[0].equals("list")){
            listAllWaypoints(waypointsManager, player);
            return true;
        }

        ArrayList<Waypoint> myWaypoints =  waypointsManager.getMyWaypoints();
        for(int i = 0; i < myWaypoints.size(); i++){
            if(myWaypoints.get(i).getWaypointName().equals(arguments[0])){
                moveToWaypoint(player, myWaypoints.get(i).getX(), myWaypoints.get(i).getY(), myWaypoints.get(i).getZ());
                return true;
            }
        }

        if(arguments.length < 2) {
            return false;
        }

        if(arguments[0].equals("add")){
            String waypointName = arguments[1];
            addNewWaypoint(waypointsManager, player, waypointName);
            return true;
        } else if(arguments[0].equals("edit")){
            String waypointName = arguments[1];
            editWaypoint(waypointsManager, player, waypointName);
            return true;
        } else if(arguments[0].equals("delete")){
            String waypointName = arguments[1];
            deleteWaypoint(waypointsManager, waypointName);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Lists all waypoints available and defined on the server.
     * @param waypointsManager waypoints manager
     * @param player player who listed all waypoints
     */
    private void listAllWaypoints(MyWaypointsManager waypointsManager, Player player){
        int waypointsCount = waypointsManager.getMyWaypoints().size();
        String[] message = new String[waypointsCount + 1];
        message[0] = "Available waypoints: ";
        for(int i = 0; i < waypointsCount; i++){
            message[i + 1] = "Name: " + waypointsManager.getMyWaypoints().get(i).getWaypointName();
        }
        player.sendMessage(message);
    }

    /**
     *  Adds new waypoint to the server.
     * @param player player who wants to save new waypoint
     * @param waypointsManager waypoints manager
     * @param waypointName name of the waypoint to add
     */
    private void addNewWaypoint(MyWaypointsManager waypointsManager, Player player, String waypointName){
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        waypointsManager.addNewWaypoint(waypointName, x, y, z);
        player.sendMessage("Added waypoint " + waypointName + " to server, nice!");
    }

    /**
     * Edits existing waypoint with player's current location.
     * @param waypointsManager waypoints manager
     * @param player who wants to edit given waypoint
     * @param waypointName name of the waypoint to edit
     */
    private void editWaypoint(MyWaypointsManager waypointsManager, Player player, String waypointName){

    }



    /**
     * Deletes waypoint from the server.
     * @param waypointsManager waypoints manager
     * @param waypointName name of the waypoint to delete
     */
    private void deleteWaypoint(MyWaypointsManager waypointsManager, String waypointName){

    }

    /**
     * Teleports player to waypoint location
     * @param player who wants to be teleported
     * @param x the X coordinate of waypoint's location
     * @param y the Y coordinate of waypoint's location
     * @param z the Z coordinate of waypoint's location
     */
    private void moveToWaypoint(Player player, double x, double y, double z){
        World world = player.getLocation().getWorld();
        player.teleport(new Location(world, x, y, z));
        player.sendMessage("You have been teleported to waypoint!");
    }
}
