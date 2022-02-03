package de.mariocst.editsigns.formutils;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.window.FormWindowCustom;

public class FormListener implements Listener {
    @EventHandler
    public void onResponse(PlayerFormRespondedEvent event) {
        if (event.getWindow() instanceof FormWindowCustom) FormHandler.handleCustom(event.getPlayer(), (FormWindowCustom) event.getWindow());
    }
}
