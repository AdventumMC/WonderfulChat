package fr.shyrogan.wonderfulchat.listeners;

import fr.shyrogan.wonderfulchat.WonderfulChat;
import fr.shyrogan.wonderfulchat.channel.IChannel;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Mainly for our command.
 * @author Sébastien (Shyrogan)
 */
public final class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e.getClickedInventory() != null && e.getClickedInventory().getName() != null &&
                e.getClickedInventory().getName().equalsIgnoreCase("§cChannels") && e.getWhoClicked() == e.getClickedInventory().getHolder()) {
            final Inventory inv = e.getClickedInventory();
            final Player p = (Player)e.getWhoClicked();
            final ItemStack item = e.getCurrentItem();

            if(item == null) {
                return;
            }

            final IChannel channel = item.getItemMeta() != null ?
                    WonderfulChat.getInstance().getChannel(ChatColor.stripColor(item.getItemMeta().getDisplayName())).orElse(null)
                    : null;

            e.setCancelled(true);
            if(channel != null) {
                if(channel.getListeners().contains(p)) {
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + channel.getName());
                    item.setItemMeta(meta);
                    channel.getListeners().remove(p);
                } else {
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(ChatColor.GREEN + channel.getName());
                    item.setItemMeta(meta);
                    channel.addListener(p);
                }
            }
        }
    }

}
