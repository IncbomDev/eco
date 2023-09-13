package me.Incbom.eco.Commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Incbom.eco.Lang;

public class economy implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("eco.admin")) {
            sender.sendMessage(Lang.GENERAL_ERROR_NO_PERMISSION.getMessage());
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(Lang.ECO_ADMIN_USAGE.getMessage());
            return true;
        }
        if (args.length == 1) {
            sender.sendMessage(Lang.ECO_ADMIN_USAGE.getMessage());
            return true;
        }
        if (args.length == 2) {
            sender.sendMessage(Lang.ECO_ADMIN_USAGE.getMessage());
            return true;
        }
        if (args[0].equalsIgnoreCase("give")) {
            double money = Double.parseDouble(args[2]);
            Player target = sender.getServer().getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(Lang.GENERAL_ERROR_NOT_FOUND.getMessage());
                return true;
            }
            Database.addMoney(target, money);
            sender.sendMessage(Lang.ECO_ADMIN_GIVE.getMessage().replace("%amount%", String.valueOf(money)).replace("%player%", target.getName()));
            return true;
        }
        if (args[0].equalsIgnoreCase("take")) {
            double money = Double.parseDouble(args[2]);
            Player target = sender.getServer().getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(Lang.GENERAL_ERROR_NOT_FOUND.getMessage());
                return true;
            }
            Database.removeMoney(target, money);
            sender.sendMessage(Lang.ECO_ADMIN_TAKE.getMessage().replace("%amount%", String.valueOf(money)).replace("%player%", target.getName()));
            return true;
        }
        if (args[0].equalsIgnoreCase("set")) {
            double money = Double.parseDouble(args[2]);
            Player target = sender.getServer().getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(Lang.GENERAL_ERROR_NOT_FOUND.getMessage());
                return true;
            }
            Database.setMoney(target, money);
            sender.sendMessage(Lang.ECO_ADMIN_SET.getMessage().replace("%amount%", String.valueOf(money)).replace("%player%", target.getName()));
            return true;
        }
        return false;
    }
}
