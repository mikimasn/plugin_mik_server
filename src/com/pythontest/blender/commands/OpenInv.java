package com.pythontest.blender.commands;

import com.pythontest.blender.inventories.SelectionScreen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class OpenInv implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))
                commandSender.sendMessage("only players can use this command");
        else
        {
            if(strings.length<1)
            {
                commandSender.sendMessage("Za mało argumentów");
                return true;
            }
            Player player = (Player) commandSender;
            Player player_2 = (Player) commandSender.getServer().getPlayer(strings[0]);
            if(player_2==null)
            {
                commandSender.sendMessage("Błędny drugi gracz :(");
                return true;
            }
            if(command.getName().equalsIgnoreCase("trade"))
            {
                SelectionScreen gui =  new SelectionScreen();
                gui.player1=player;
                gui.player2=player_2;
                Inventory inv = gui.getInventory();
                player.openInventory(inv);
                player_2.openInventory(inv);

            }
        }
        return true;
    }
}
