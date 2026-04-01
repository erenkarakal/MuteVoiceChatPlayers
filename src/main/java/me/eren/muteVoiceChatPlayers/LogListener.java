package me.eren.muteVoiceChatPlayers;

import litebans.api.Database;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

/**
 * When the player joins check if they are muted.
 * If so, add them to the muted cache.
 * <p>
 * When they leave, remove them from the cache
 */
public class LogListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Bukkit.getScheduler().runTaskAsynchronously(MuteVoiceChatPlayers.instance(), () -> {
			UUID uuid = event.getPlayer().getUniqueId();
			String ip = event.getPlayer().getAddress().getAddress().getHostAddress();

			if (Database.get().isPlayerMuted(uuid, ip)) {
				LitebansVCPlugin.mutePlayer(event.getPlayer().getUniqueId());
			}
		});
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		LitebansVCPlugin.unmutePlayer(event.getPlayer().getUniqueId());
	}

}
