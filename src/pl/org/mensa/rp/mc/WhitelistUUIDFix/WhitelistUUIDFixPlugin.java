package pl.org.mensa.rp.mc.WhitelistUUIDFix;

import org.bukkit.plugin.java.JavaPlugin;

public class WhitelistUUIDFixPlugin extends JavaPlugin {
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);
	}
}
