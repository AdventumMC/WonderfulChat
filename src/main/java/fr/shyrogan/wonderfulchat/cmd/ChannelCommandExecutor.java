package fr.shyrogan.wonderfulchat.cmd;

import fr.shyrogan.wonderfulchat.WonderfulChat;
import fr.shyrogan.wonderfulchat.chatter.IChatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.List;

/**
 * This is our command executor. It basically open an inventory to manage channels
 * we're listening to.
 * @see fr.shyrogan.wonderfulchat.listeners.InventoryListener
 *
 * @author Sébastien (Shyrogan)
 */
public final class ChannelCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cConsole cannot manage which channels it listens to.");
            return true;
        }
        final Player p = (Player)sender;
        final Inventory inv = Bukkit.createInventory(p, InventoryType.CHEST, "§cChannels");
        final IChatter chatter = WonderfulChat.getInstance().getChatterProvider().getChatter(p.getUniqueId());

        WonderfulChat.getInstance().getChannels().forEach(channel -> {
            ItemStack itemStack = new ItemStack(channel.getLogo());
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName((chatter.isListeningTo(channel) ? ChatColor.GREEN : ChatColor.RED) + channel.getName());
            List<String> lore = new LinkedList<>();
            lore.add(" ");
            if(!channel.getDescription().isEmpty()) {
                lore.add("§7" + channel.getDescription());
                lore.add(" ");
            }
            meta.setLore(lore);
            itemStack.setItemMeta(meta);

            inv.addItem(itemStack);
        });
        p.openInventory(inv);
        return true;
    }

}
