package systems.autototem;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import systems.autototem.cmd.AutoTotemGlobalToggle;
import systems.autototem.cmd.AutoTotemPlayerToggle;
import systems.autototem.cmd.InfiniteTotemGlobalToggle;
import systems.autototem.cmd.InfiniteTotemPlayerToggle;
import systems.autototem.event.PlayerDeath;

import java.util.ArrayList;

public final class AutoTotem extends JavaPlugin {

    public static MiniMessage mm = MiniMessage.miniMessage();
    public static boolean autoTotemGlobal = false;
    public static boolean infiniteTotemGlobal = false;

    public static ArrayList<Player> autoTotemMap = new ArrayList<>();
    public static ArrayList<Player> infiniteTotemMap = new ArrayList<>();

    @Override
    public void onEnable() {

        getCommand("autototem").setExecutor(new AutoTotemPlayerToggle());
        getCommand("infinitetotem").setExecutor(new InfiniteTotemPlayerToggle());
        getCommand("autototemglobal").setExecutor(new AutoTotemGlobalToggle());
        getCommand("infinitetotemglobal").setExecutor(new InfiniteTotemGlobalToggle());
        new PlayerDeath(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}