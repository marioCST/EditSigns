package de.mariocst.editsigns;

import cn.nukkit.plugin.PluginBase;
import de.mariocst.editsigns.formutils.FormListener;
import lombok.Getter;

public class EditSigns extends PluginBase {
    @Getter
    private final String prefix = "§8[§3Edit§9Signs§8] §f";

    @Getter
    private EditSignsForm editSignsForm;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new FormListener(), this);
        this.getServer().getPluginManager().registerEvents(new EditSignsListener(this), this);

        this.editSignsForm = new EditSignsForm();

        this.log("Enabled EditSigns");
    }

    @Override
    public void onDisable() {
        this.log("Disabled EditSigns");
    }

    public void log(String msg) {
        this.getLogger().info(this.prefix + msg);
    }
}
