package systems.autototem.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static systems.autototem.AutoTotem.autoTotemGlobal;
import static systems.autototem.AutoTotem.mm;

public class AutoTotemGlobalToggle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (autoTotemGlobal) {
            autoTotemGlobal = false;
            sender.sendMessage(mm.deserialize("<#FF3362>AutoTotem is now globally disabled"));
        } else {
            autoTotemGlobal = true;
            sender.sendMessage(mm.deserialize("<#33FFA0>AutoTotem is now globally enabled"));
        }
        return true;
    }
}
