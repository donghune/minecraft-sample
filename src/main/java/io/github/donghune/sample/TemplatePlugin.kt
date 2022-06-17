package io.github.donghune.sample

import io.github.donghune.api.BasePlugin
import io.github.donghune.api.mccoroutine.registerSuspendingEvents
import org.bukkit.Bukkit

class TemplatePlugin : BasePlugin() {
    override fun onEnable() {
        super.onEnable()
        TemplateCommand.initialize(this)
        Bukkit.getPluginManager().registerSuspendingEvents(TemplateListener(), this)
    }
}