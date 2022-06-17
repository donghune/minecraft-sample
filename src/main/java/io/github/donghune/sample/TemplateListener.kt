package io.github.donghune.sample

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class TemplateListener : Listener {
    @EventHandler
    suspend fun onPlayerJoinEvent(event: PlayerJoinEvent) {

    }
}