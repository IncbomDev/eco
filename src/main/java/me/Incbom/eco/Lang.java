package me.Incbom.eco;

import java.lang.module.Configuration;

public enum Lang {
    GENERAL_ERROR("general.error", "&cAn error has occurred!"),
    GENERAL_ERROR_NOT_FOUND("general.error.not_found", "&cPlayer not found!"),
    GENERAL_ERROR_NO_PERMISSION("general.error.no_permission", "&cYou don't have permission to do that!"),
    ECO_ADMIN_GIVE("eco.admin.give", "&aYou have given &e%amount% &ato &e%player%"),
    ECO_ADMIN_TAKE("eco.admin.take", "&aYou have taken &e%amount% &afrom &e%player%"),
    RCO_ADMIN_SET("eco.admin.set", "&aYou have set &e%player%'s &abalance to &e%amount%"),
    ECO_ADMIN_USAGE("eco.admin.usage", "&cUsage: /eco <give|take|set> <player> <amount>");

    private String path;

    

    Lang(String path) {
        this.path = path;
    }

    public String getMessage() {
        Configuration config = Main.getInstance().getConfig();
        return config.getString(path);
    }
}