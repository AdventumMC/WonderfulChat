package fr.shyrogan.wonderfulchat.listeners;

import fr.shyrogan.wonderfulchat.WonderfulChat;
import fr.shyrogan.wonderfulchat.chatter.IChatter;
import fr.shyrogan.wonderfulchat.chatter.implementations.OfflineChatter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Sébastien (Shyrogan)
 */
public final class PlayerListener implements Listener {

    @EventHandler
    public void onConnect(PlayerJoinEvent e) {
        final WonderfulChat wc = WonderfulChat.getInstance();

        wc.getServer().getScheduler().runTaskAsynchronously(wc, () -> {
            OfflineChatter chatter = new OfflineChatter(e.getPlayer().getUniqueId());
            wc.getChatterProvider().putChatter(chatter);
            chatter.load();
        });
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent e) {
        final IChatter chatter = WonderfulChat.getInstance().getChatterProvider().getChatter(e.getPlayer().getUniqueId());

        if(chatter != null) {
            chatter.disconnect();
        }
    }

}
