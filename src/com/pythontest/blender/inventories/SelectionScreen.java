package com.pythontest.blender.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SelectionScreen implements InventoryHolder {

    private  Inventory inv;
    public Player player1;
    public Player player2;
    public SelectionScreen(){
        inv = Bukkit.createInventory(this,54,"Trade");
        init();
    }
    private void init() {
        ItemStack item;
        item = createItem("",Material.BLACK_STAINED_GLASS_PANE,Collections.singletonList(""));
        for(int i=0;i<6;i++){
            inv.setItem(4+i*9,item);
        }
        for(int i=1;i<8;i++){
            inv.setItem(5*9+i,item);
        }
        List<String> loree = Arrays.asList("Kliknij tu aby zaakceptować wymianę","W przypadku zmiany jakiego kolwiek przedmiotu ","zgoda zostanie cofnięta");

        item=createItem("Accept",Material.GREEN_STAINED_GLASS_PANE,loree);
        inv.setItem(45,item);
        inv.setItem(53,item);
    }
    private ItemStack createItem(String name, Material mat, List<String> lore)
    {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    @Override
    public Inventory getInventory() {
        return inv;
    }
}
