package io.github.donghune.sample

import io.github.monun.kommand.kommand

object TemplateCommand {
    fun initialize(plugin: TemplatePlugin) {
        plugin.kommand {
            register("") {
            }
        }
    }
}