package me.Incbom.eco.Commands;

import org.bukkit.command.Command;

import org.bukkit.command.CommandExecutor;

import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

import me.Incbom.eco.utils.Database;

public class balance implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("eco.balance")) {
            sender.sendMessage("§cYou don't have permission to do that!");
            return true;
        }
        if (args.length == 0) {
            Player player = (Player) sender;
            sender.sendMessage("§aYour balance is §e" + Database.getMoney(player));
            return true;
        }
        if (args.length == 1) {
            Player target = sender.getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§cPlayer not found!");
                return true;
            }
            sender.sendMessage("§a" + target.getName() + "'s balance is §e" + Database.getMoney(target));
            return true;
        }
        return false;
    }
}