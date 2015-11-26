package pl.siepet.mywaypoints;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *  Really simple and easily configurable waypoint manager for your Bukkit server!
 *
 *  @author siepet
 */
public class MyWaypoints extends JavaPlugin {

    @Override
    public void onDisable() {
        getLogger().info("MyWaypoints is sad :(");
    }

    @Override
    public void onEnable() {
        getLogger().info("Configuration error");
        getCommand("waypoint").setExecutor(new MyWaypointsCommandExecutor());
    }

}
