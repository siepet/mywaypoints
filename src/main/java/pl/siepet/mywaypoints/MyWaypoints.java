package pl.siepet.mywaypoints;

import org.bukkit.plugin.PluginManager;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *  Really simple and easily configurable waypoint manager for your Bukkit server!
 *
 *  @author siepet
 */
public class MyWaypoints extends JavaPlugin {
    private String myWaypointsPath;

    @Override
    public void onDisable() {
        getLogger().info("MyWaypoints is sad :(");
    }

    @Override
    public void onEnable() {
        Path currentRelativePath = Paths.get("");
        myWaypointsPath = currentRelativePath.toAbsolutePath().toString() + "/plugins/MyWaypoints/";
        createBasicWaypoint();
        getCommand("waypoint").setExecutor(new MyWaypointsCommandExecutor(getLogger(), myWaypointsPath));
    }

    private void createBasicWaypoint() {

        File waypointsFolder = new File(myWaypointsPath);
        if(!waypointsFolder.exists()){
            if(waypointsFolder.mkdir()){
                getLogger().info("Created MyWaypoints folder!");
                double spawnLocationX = getServer().getWorld("world").getSpawnLocation().getX();
                double spawnLocationY = getServer().getWorld("world").getSpawnLocation().getY();
                double spawnLocationZ = getServer().getWorld("world").getSpawnLocation().getZ();
                MyWaypointsManager.saveInitialSpawnWaypoint(myWaypointsPath + "spawn.json", spawnLocationX, spawnLocationY, spawnLocationZ);
                getLogger().info("Saved spawn waypoint!");
            }
        }
    }
}
