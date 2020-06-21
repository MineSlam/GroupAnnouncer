package dev.rono.groupannouncer;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class GroupAnnouncer extends JavaPlugin {

    private static Permission permission = null;
    private static FileConfiguration configuration = null;

    @Override
    public void onEnable() {
        if (!setupPermissions() ) {
            Bukkit.getLogger().severe("Vault not found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        this.saveDefaultConfig();
        configuration = getConfig();
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }

    private boolean setupPermissions() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        if (rsp == null) {
            return false;
        }

        permission = rsp.getProvider();
        return permission != null;
    }

    public static Permission getPermission() {
        return permission;
    }

    public static FileConfiguration getConfiguration() {
        return configuration;
    }
}
