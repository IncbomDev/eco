package me.yourname.pluginname;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import me.Incbom.eco.utils.Database;
import me.yourname.pluginname.utils.Logger;

public final class Main extends JavaPlugin {

  public File dataFolder = this.getDataFolder();

    @Override
    public void onEnable() {
      this.saveDefaultConfig();
      File database = new File(dataFolder, "database.db");
      try {
        if (!database.exists()) {
          database.createNewFile();
        }
      } catch (Exception e) {
        Logger.log(Logger.LogLevel.ERROR, "Failed to create database file!");
        e.printStackTrace();
      }
      try {
        Database.connect();
        Database.init();
      } catch (Exception e) {
        Logger.log(Logger.LogLevel.ERROR, "Failed to connect to database!");
        e.printStackTrace();
      }


      Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");
      Logger.log(Logger.LogLevel.SUCCESS, "Loading Eco test...");
      Logger.log(Logger.LogLevel.SUCCESS, "Loaded!");
      Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");
    }   

    @Override
    public void onDisable() {
      Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");
      Logger.log(Logger.LogLevel.SUCCESS, "Unloading Eco test...");
      Logger.log(Logger.LogLevel.SUCCESS, "Unloaded!");
      Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");
      try {
        Database.disconnect();
      } catch (Exception e) {
        Logger.log(Logger.LogLevel.ERROR, "Failed to disconnect from database!");
        e.printStackTrace();
      }
      this.saveConfig();
      }
    }