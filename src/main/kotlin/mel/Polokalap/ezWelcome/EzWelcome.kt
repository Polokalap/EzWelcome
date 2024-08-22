package mel.Polokalap.ezWelcome

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin

class EzWelcome : JavaPlugin(), Listener  {

    override fun onEnable() {
        logger.info("EzWelcome started.")
        server.pluginManager.registerEvents(this, this)
        config.options().copyDefaults(true)
        saveConfig()
    }

    override fun onDisable() {
        logger.info("EzWelcome shut down.")
    }

    val welcomemessage = config.getString("welcome")

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        event.joinMessage = config.getString("welcome").toString().replace("%player%", player.name, ignoreCase = true)
        if (welcomemessage == "§e%player% joined the server") {
            player.sendMessage("${ChatColor.ITALIC}You are using the default config, change it in the config.yml.")
        }
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        val player = event.player
        event.quitMessage = config.getString("quit").toString().replace("%player%", player.name, ignoreCase = true)
    }
}
