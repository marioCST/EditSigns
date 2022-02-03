package de.mariocst.editsigns;

import cn.nukkit.Player;
import cn.nukkit.blockentity.BlockEntitySign;
import cn.nukkit.form.element.ElementInput;
import de.mariocst.editsigns.formutils.custom.CustomForm;

public class EditSignsForm {
    public void openSignEditMenu(EditSigns plugin, Player player, BlockEntitySign sign) throws NullPointerException {
        assert sign != null;
        String[] text = sign.getText();

        CustomForm form = new CustomForm.Builder(plugin, "§3Edit§9Signs")
                .addElement(new ElementInput("Line 1", "Empty", text[0]))
                .addElement(new ElementInput("Line 2", "Empty", text[1]))
                .addElement(new ElementInput("Line 3", "Empty", text[2]))
                .addElement(new ElementInput("Line 4", "Empty", text[3]))
                .onSubmit((p, r) -> sign.setText(
                        r.getInputResponse(0).replaceAll("&", "§"),
                        r.getInputResponse(1).replaceAll("&", "§"),
                        r.getInputResponse(2).replaceAll("&", "§"),
                        r.getInputResponse(3).replaceAll("&", "§")))
                .build();

        form.send(player);
    }
}
