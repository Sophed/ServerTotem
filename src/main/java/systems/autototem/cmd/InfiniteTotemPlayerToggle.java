package systems.autototem.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static systems.autototem.AutoTotem.*;

public class InfiniteTotemPlayerToggle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player target;
        boolean otherPlayer;
        if(!(args.length < 1)){
            target = Bukkit.getPlayer(args[0]);
            otherPlayer = true;
        } else {
            target = (Player) sender;
            otherPlayer = false;
        }

        if (target == null) {
            sender.sendMessage(mm.deserialize("<#FF3362>Player not found"));
            return true;
        }

        if (infiniteTotemMap.contains(target)) {
            infiniteTotemMap.remove(target);
            target.sendMessage(mm.deserialize("<#FF3362>InfiniteTotem is now disabled"));
            if (otherPlayer) sender.sendMessage(mm.deserialize("<#FF3362>InfiniteTotem is now disabled for " + target.getName()));
        } else {
            infiniteTotemMap.add(target);
            target.sendMessage(mm.deserialize("<#33FFA0>InfiniteTotem is now enabled"));
            if (otherPlayer) sender.sendMessage(mm.deserialize("<#33FFA0>InfiniteTotem is now enabled for " + target.getName()));
        }
        return true;
    }
}
