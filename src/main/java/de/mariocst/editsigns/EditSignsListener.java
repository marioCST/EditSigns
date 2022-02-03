package de.mariocst.editsigns;

import cn.nukkit.Player;
import cn.nukkit.block.BlockSignPost;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class EditSignsListener implements Listener {
    private final EditSigns plugin;

    public EditSignsListener(EditSigns plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) return;

        if (!(event.getBlock() instanceof BlockSignPost)) return;

        Player player = event.getPlayer();

        if (!player.hasPermission("editsigns") && !player.hasPermission("*") && !player.isOp()) return;

        if (this.plugin.getServer().getPluginManager().getPlugin("PlotSquared") != null) {
            com.intellectualcrafters.plot.object.PlotPlayer plotPlayer = com.plotsquared.nukkit.util.NukkitUtil.getPlayer(player);

            if (plotPlayer.getCurrentPlot() != null) {
                if (!plotPlayer.getCurrentPlot().isAdded(player.getUniqueId()) && !plotPlayer.getCurrentPlot().isOwner(player.getUniqueId())) {
                    if (!player.hasPermission("editsigns.bypass") && !player.hasPermission("*") && !player.isOp()) return;
                }
            }
        }
        else if (this.plugin.getServer().getPluginManager().getPlugin("FuturePlots") != null) {
            tim03we.futureplots.utils.Plot plot = tim03we.futureplots.FuturePlots.getInstance().getPlotByPosition(player.getLocation());

            if (plot != null) {
                if (!plot.canInteract(player) && !plot.canByPass(player)) {
                    if (!player.hasPermission("editsigns.bypass") && !player.hasPermission("*") && !player.isOp()) return;
                }
            }
        }

        BlockSignPost sign = (BlockSignPost) event.getBlock();

        try {
            this.plugin.getEditSignsForm().openSignEditMenu(this.plugin, player, Objects.requireNonNull(sign.getBlockEntity()));
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
