package pl.org.mensa.rp.mc.WhitelistUUIDFix;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import net.md_5.bungee.api.ChatColor;

public class PlayerLoginListener implements Listener {
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerLoginEvent e) {
		boolean replace = false;
		OfflinePlayer old_uuid_player = null;
		
		for (OfflinePlayer p : Bukkit.getServer().getWhitelistedPlayers()) {
			String old_name = p.getName();
			if (old_name.startsWith("<")) old_name = old_name.substring(1);
			if (old_name.endsWith(">")) old_name = old_name.substring(0, old_name.length()-1);
			
			if (old_name.equals(e.getPlayer().getName()) && !p.getUniqueId().equals(e.getPlayer().getUniqueId())) {
				replace = true;
				old_uuid_player = p;
			}
		}
		
		if (replace) {
			old_uuid_player.setWhitelisted(false);
			e.getPlayer().setWhitelisted(true);
			
			Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "Fixed " + e.getPlayer().getName() + "'s whitelist UUID");
			e.setResult(Result.ALLOWED);
		}
	}
}
