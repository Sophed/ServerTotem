package systems.autototem.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static systems.autototem.AutoTotem.infiniteTotemGlobal;
import static systems.autototem.AutoTotem.mm;

public class InfiniteTotemGlobalToggle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (infiniteTotemGlobal) {
            infiniteTotemGlobal = false;
            sender.sendMessage(mm.deserialize("<#FF3362>InfiniteTotems is now globally disabled"));
        } else {
            infiniteTotemGlobal = true;
            sender.sendMessage(mm.deserialize("<#33FFA0>InfiniteTotems is now globally enabled"));
        }
        return true;
    }
}
