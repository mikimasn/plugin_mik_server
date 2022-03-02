package com.pythontest.blender;

import com.pythontest.blender.commands.OpenInv;
import com.pythontest.blender.events.InventoryEvents;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Blender extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("trade").setExecutor(new OpenInv());
        getServer().getPluginManager().registerEvents(new InventoryEvents(), this);
    }
    @Override
    public void onDisable() {
    }
}
