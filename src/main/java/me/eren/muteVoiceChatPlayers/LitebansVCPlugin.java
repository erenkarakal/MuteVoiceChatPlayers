package me.eren.muteVoiceChatPlayers;

import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LitebansVCPlugin implements VoicechatPlugin {

	private static final Set<UUID> mutedPlayers = new HashSet<>();

	public static void mutePlayer(UUID uuid) {
		mutedPlayers.add(uuid);
	}

	public static void unmutePlayer(UUID uuid) {
		mutedPlayers.remove(uuid);
	}

	@Override
	public String getPluginId() {
		return "litebans-integration";
	}

	@Override
	public void registerEvents(EventRegistration registration) {
		registration.registerEvent(MicrophonePacketEvent.class, (event) -> {
			VoicechatConnection conn = event.getSenderConnection();
			if (conn == null || conn.getPlayer() == null) {
				// player is offline, likely
				return;
			}

			UUID senderUuid = conn.getPlayer().getUuid();
			if (event.isCancellable() && mutedPlayers.contains(senderUuid)) {
				event.cancel();
			}
		});
	}

}
