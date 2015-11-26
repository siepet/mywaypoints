package pl.siepet.mywaypoints;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MyWaypointsCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] arguments) {
        if(arguments.length < 1) {
            return false;
        }

        Player player = (Player)commandSender;
        MyWaypointsManager waypointsManager = new MyWaypointsManager();

        if(arguments[0].equals("list")){
            listAllWaypoints(waypointsManager, player);
            return true;
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
        String[] message = new String[waypointsCount];
        message[0] = "Available waypoints: ";
        for(int i = 1; i <= waypointsCount; i++){
            message[i] = "Name: " + waypointsManager.getMyWaypoints().get(i - 1).getWaypointName();
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
}
