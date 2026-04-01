package me.eren.muteVoiceChatPlayers;

import de.maxhenkel.voicechat.api.BukkitVoicechatService;
import org.bukkit.plugin.java.JavaPlugin;

public final class MuteVoiceChatPlayers extends JavaPlugin {

	private static MuteVoiceChatPlayers instance;

	public static MuteVoiceChatPlayers instance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		this.getServer().getPluginManager().registerEvents(new LogListener(), this);
		LitebansEntryListener.registerListeners();

		BukkitVoicechatService service = getServer().getServicesManager().load(BukkitVoicechatService.class);
		if (service != null) {
			service.registerPlugin(new LitebansVCPlugin());
		}
	}

}
