package dev.rono.groupannouncer;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class JoinListener implements Listener {

    private final ConfigurationSection config;

    JoinListener() {
        config = GroupAnnouncer.getConfiguration().getConfigurationSection("groups");
    }

    @EventHandler()
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        String group = GroupAnnouncer.getPermission().getPrimaryGroup(player);

        List<String> messages = config.getStringList(group + ".message");
        for (String message : messages) {
            message = message.replace("{player}", player.getName());
            message = ChatColor.translateAlternateColorCodes('&', message);
            TextComponent textMessage = new TextComponent(TextComponent.fromLegacyText(message));
            event.getPlayer().getServer().broadcast(textMessage);
        }
    }
}
