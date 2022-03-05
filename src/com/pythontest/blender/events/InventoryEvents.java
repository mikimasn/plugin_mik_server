package com.pythontest.blender.events;

import com.pythontest.blender.inventories.SelectionScreen;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InventoryEvents implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getClickedInventory()==null){return;}
        if(e.getClickedInventory().getHolder() instanceof SelectionScreen){
                if(e.isRightClick())
                    e.setCancelled(true);
                if(e.isShiftClick())
                    e.setCancelled(true);
                if(e.getSlot()>45&&e.getSlot()<53)
                    e.setCancelled(true);
                if(((SelectionScreen) e.getInventory().getHolder()).player1.getUniqueId().equals(e.getWhoClicked().getUniqueId())){
                    if(e.getSlot()%9>4)
                        e.setCancelled(true);
                }
                if(((SelectionScreen) e.getInventory().getHolder()).player2.getUniqueId().equals(e.getWhoClicked().getUniqueId())){
                    if(e.getSlot()%9<5)
                        e.setCancelled(true);
                }
                if(e.getSlot()%9==4)
                    e.setCancelled(true);
                if(!(e.isCancelled()))
                {
                    if(e.getCurrentItem()==null) {
                        ItemStack item;
                        Inventory inv = e.getInventory();
                        List<String> loree = Arrays.asList("Kliknij tu aby zaakceptować wymianę", "W przypadku zmiany jakiego kolwiek przedmiotu", "zgoda zostanie cofnięta");

                        item = createItem("Accept", Material.GREEN_STAINED_GLASS_PANE, loree);
                        inv.setItem(45, item);
                        inv.setItem(53, item);
                    }
                    else if(e.getCurrentItem().getType() == Material.GREEN_STAINED_GLASS_PANE&&(e.getSlot()==53||e.getSlot()==45)){
                        e.setCancelled(true);
                        ItemStack item;
                        Inventory inv = e.getInventory();

                        item = createItem("Accepted", Material.BLUE_STAINED_GLASS_PANE, Collections.singletonList("Ten użytkownik zaakceptował wymianę"));
                        e.getInventory().setItem(e.getSlot(),item);
                            if(e.getInventory().getItem(e.getSlot()==45?53:45).getType()==Material.BLUE_STAINED_GLASS_PANE){
                                for(int i=0;i<5;i+=1)
                                {
                                    for(int y=0;y<4;y+=1)
                                    {
                                        item = e.getInventory().getItem(y+9*i);

                                        if(item != null)
                                        {
                                            ((SelectionScreen) e.getInventory().getHolder()).player2.getInventory().addItem(item);
                                            e.getInventory().setItem(y+9*i,null);
                                        }
                                    }
                                    for(int y=5;y<9;y+=1)
                                    {
                                        item = e.getInventory().getItem(y+9*i);

                                        if(item != null)
                                        {
                                            ((SelectionScreen) e.getInventory().getHolder()).player1.getInventory().addItem(item);
                                            e.getInventory().setItem(y+9*i,null);
                                        }
                                    }
                                }
                                if(((SelectionScreen) e.getInventory().getHolder()).player1.getOpenInventory()!=null)
                                    ((SelectionScreen) e.getInventory().getHolder()).player1.closeInventory();
                                if(((SelectionScreen) e.getInventory().getHolder()).player2.getOpenInventory()!=null)
                                    ((SelectionScreen) e.getInventory().getHolder()).player2.closeInventory();
                            }

                    }

                }

        }
        if(e.getWhoClicked().getOpenInventory()!=null)
        {
            if(e.getWhoClicked().getOpenInventory().getTopInventory().getHolder() instanceof SelectionScreen)
            {
                if(e.isRightClick())
                    e.setCancelled(true);
                if(e.isShiftClick())
                    e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void leave(InventoryCloseEvent e) {
        if(e.getInventory().getHolder() instanceof  SelectionScreen)
        {
            ItemStack item;
            for(int i=0;i<5;i+=1)
            {
                for(int y=0;y<4;y+=1)
                {
                    item = e.getInventory().getItem(y+9*i);

                    if(item != null)
                    {
                        ((SelectionScreen) e.getInventory().getHolder()).player1.getInventory().addItem(item);
                        e.getInventory().setItem(y+9*i,null);
                    }
                }
                for(int y=5;y<9;y+=1)
                {
                     item = e.getInventory().getItem(y+9*i);

                    if(item != null)
                    {
                        ((SelectionScreen) e.getInventory().getHolder()).player2.getInventory().addItem(item);
                        e.getInventory().setItem(y+9*i,null);
                    }
                }
            }
            if(e.getPlayer().getUniqueId().equals(((SelectionScreen) e.getInventory().getHolder()).player1.getUniqueId()))
                ((SelectionScreen) e.getInventory().getHolder()).player2.closeInventory();
            else
                ((SelectionScreen) e.getInventory().getHolder()).player1.closeInventory();
        }
    }
    @EventHandler
    public void onDrag(InventoryDragEvent e)
    {
        if(e.getInventory().getHolder() instanceof SelectionScreen){
            e.setCancelled(true);
        }
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

}
