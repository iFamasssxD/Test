package me.ifamasssxd.test;

import java.lang.reflect.Field;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Test extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (label.equalsIgnoreCase("gimme")) {
            ItemStack is = new ItemStack(Material.GOLD_INGOT);
            is.addUnsafeEnchantment(CustomEnchant(), 1);
            p.getInventory().addItem(is);
        }
        return false;
    }

    public Enchantment CustomEnchant() {
        EnchantGlow glow = new EnchantGlow(120);
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            EnchantmentWrapper.registerEnchantment(glow);
            return glow;
        } catch (IllegalArgumentException e) {

        }
        return glow;
    }
}
