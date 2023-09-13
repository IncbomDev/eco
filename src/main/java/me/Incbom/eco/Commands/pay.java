package me.Incbom.eco.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Incbom.eco.utils.Database;

public class pay implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("eco.pay")) {
            player.sendMessage("§cYou don't have permission to do that!");
            return true;
        }
        if (args.length == 0) {
            player.sendMessage("§cUsage: /pay <player> <amount>");
            return true;
        }
        if (args.length == 1) {
            player.sendMessage("§cUsage: /pay <player> <amount>");
            return true;
        }
        double money = Double.parseDouble(args[1]);
        if (money > Database.getMoney(player)) {
            player.sendMessage("§cYou don't have enough money!");
            return true;
        }
        Player target = player.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("§cPlayer not found!");
            return true;
        }
        Database.addMoney(target, money);
        Database.removeMoney(player, money);
        player.sendMessage("§aYou have sent §e" + money + " §ato §e" + target.getName());
        return true;
    }
}