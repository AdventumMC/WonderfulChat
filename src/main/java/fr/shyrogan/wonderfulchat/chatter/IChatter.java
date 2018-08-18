package fr.shyrogan.wonderfulchat.chatter;

import fr.shyrogan.wonderfulchat.WonderfulChat;
import fr.shyrogan.wonderfulchat.channel.IChannel;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IChatter is the representation of a Player for our plugin.
 * It allows us to know which Channel the player is listening to.
 *
 * There are normally 3 IChatter implementations inside of WonderfulChat.
 * <ul>
 *     <li>OnlineChatter</li>
 *     <li>OfflineChatter</li>
 * </ul>
 *
 * Both have really specific usages but if you go through the plugin,
 * it is also useful for your own implementations but I recommend doing your owns.
 *
 * However if you go through the AccountProvider, everything should be ok.
 *
 * @author Sébastien (Shyrogan)
 */
public interface IChatter {

    /**
     * Returns our IChatter's UUID. I mean, we need to recognize him lul.
     *
     * @return Chatter's Unique ID.
     */
    UUID getUniqueId();

    /**
     * Returns our Chatter listened channels.
     *
     * @return IChannel Collection
     */
    Collection<IChannel> getListenedChannels();

    /**
     * Checks if our Chatter listens to a Channel.
     *
     * @param channel Channel.
     * @return True if listening to.
     */
    default boolean isListeningTo(IChannel channel) {
        return getListenedChannels().contains(channel);
    }

    /**
     * Save Chatter informations. This method is implented inside
     * of the interface because it's generally the same method for each one
     * inside of WonderfulChat.
     *
     * If you're creating your own implementation, code this method yourself.
     */
    default void disconnect() {
        final WonderfulChat wc = WonderfulChat.getInstance();

        wc.getServer().getScheduler().runTaskAsynchronously(wc, () -> {
            // Path to our chatter file.
            final Path chatterPath = Paths.get(WonderfulChat.getInstance().getDataFolder().getPath(), "chatter_cache", getUniqueId().toString() + ".json");

            try {
                // We write informations inside of the file
                String json = wc.getGson().toJson(getListenedChannels().stream().map(IChannel::getName).collect(Collectors.toList()));
                wc.info(json);
                Files.write(
                        chatterPath,
                        json.getBytes(Charset.defaultCharset())
                );
            } catch (IOException e) {
                // There might be an error but, no problemo we just remove the file.
                try {
                    Files.delete(chatterPath);
                } catch (IOException sub) {
                    // Well problemo.
                    sub.printStackTrace();
                }
            }
        });
    }

}
