package systems.autototem.event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import systems.autototem.AutoTotem;

import static systems.autototem.AutoTotem.*;

public class PlayerDeath implements Listener {
    public PlayerDeath(AutoTotem plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    private final Plugin plugin = AutoTotem.getPlugin(AutoTotem.class);

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {

        Entity entity = event.getEntity();
        if (infiniteTotemGlobal || infiniteTotemMap.contains((Player) entity)) {

            // Entity is a player
            if (!(entity instanceof Player player)) { return; }
            // Player is going to die
            if (player.getHealth() - event.getFinalDamage() > 0) { return; }
            // Get item in offhand
            ItemStack oldItem = player.getInventory().getItemInOffHand();

            // Player already has a totem in their offhand
            if (oldItem.getType() == Material.TOTEM_OF_UNDYING || player.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING) {
                return;
            }

            // Set the player's offhand to a totem and schedule a task to set it back to the original item
            setTotem(player, player.getInventory(), oldItem);

            return;
        }

        if (!autoTotemGlobal && !autoTotemMap.contains((Player) entity)) return; // AutoTotem is enabled
        Player player = (Player) entity;
        if (player.getHealth() - event.getFinalDamage() > 0) { return; } // Player is going to die

        ItemStack oldItem = player.getInventory().getItemInOffHand();
        if (oldItem.getType() == Material.TOTEM_OF_UNDYING || player.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING) {
            return; // Player already has a totem in their offhand
        }

        Inventory inv = player.getInventory();
        if (getFirstTotem(player) == null) {
            return;
        }

        setTotem(player, inv, oldItem);

    }

    private ItemStack getFirstTotem(Player player) {
        Inventory inv = player.getInventory();
        for(int i = 0; i < inv.getSize(); i++){
            if (inv.getItem(i) == null) continue; // Item is not null (prevents exceptions)
            if(inv.getItem(i).getType() == Material.TOTEM_OF_UNDYING){
                return inv.getItem(i);
            }
        }
        return null;
    }

    private void setTotem(Player player, Inventory inventory, ItemStack oldItem) {
        // Put a totem in the player's offhand
        if (!infiniteTotemGlobal && !infiniteTotemMap.contains(player)) {
            player.getInventory().setItemInOffHand(getFirstTotem(player));
        } else {
            player.getInventory().setItemInOffHand(new ItemStack(Material.TOTEM_OF_UNDYING));
        }
        // I love the λ
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            // Remove a totem from the player's inventory
            if (!infiniteTotemGlobal && !infiniteTotemMap.contains(player)) {
                inventory.removeItem(getFirstTotem(player));
            }
            // Put the player's old item back in their offhand
            player.getInventory().setItemInOffHand(oldItem);
        }, 2L);
    }

}
