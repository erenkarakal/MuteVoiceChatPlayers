package me.eren.muteVoiceChatPlayers;

import litebans.api.Entry;
import litebans.api.Events;
import litebans.api.Events.Listener;

import java.util.UUID;

public class LitebansEntryListener extends Listener {

	public static void registerListeners() {
		Events.get().register(new Events.Listener() {
			@Override
			public void entryAdded(Entry entry) {
				if (entry.getType().equals("mute")) {
					String stringUUID = entry.getUuid();
					if (stringUUID == null) {
						return;
					}
					LitebansVCPlugin.mutePlayer(UUID.fromString(entry.getUuid()));
				}
			}

			@Override
			public void entryRemoved(Entry entry) {
				if (entry.getType().equals("mute")) {
					String stringUUID = entry.getUuid();
					if (stringUUID == null) {
						return;
					}
					LitebansVCPlugin.unmutePlayer(UUID.fromString(entry.getUuid()));
				}
			}
		});

	}

}
