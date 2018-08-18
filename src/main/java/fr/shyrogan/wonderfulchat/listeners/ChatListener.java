package fr.shyrogan.wonderfulchat.listeners;

import fr.shyrogan.wonderfulchat.WonderfulChat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.stream.Collectors;

/**
 * @author Sébastien (Shyrogan)
 */
public final class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onSendMessage(AsyncPlayerChatEvent e) {
        final String message = e.getMessage();
        final Player p = e.getPlayer();

        WonderfulChat.getInstance().getChannels().stream()
            .filter(channel -> message.startsWith(channel.getMarker() + " "))
            .findFirst().ifPresent(channel -> {
                if(!channel.getListeners().contains(p)) {
                    p.sendMessage("§cYou can't send a message inside of a channel you don't listen to.");
                    e.setCancelled(true);
                    return;
                }

                if(!channel.respectConditions(p)) {
                    p.sendMessage("§cYou don't respect conditions to send a message inside of this channel. §4§o" + channel.getConditions().stream().map(c -> c.getConditionTag() + "=" + c.getConditionParameter()).collect(Collectors.joining()));
                    e.setCancelled(true);
                    return;
                }

                e.getRecipients().clear();
                e.getRecipients().addAll(channel.getListeners());
                e.setMessage(message.substring(channel.getMarker().length() + 1));
                e.setFormat(channel.getPrefix() + e.getFormat());
        });
    }

}
