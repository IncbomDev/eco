package me.Incbom.eco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.entity.Player;


public class Database {
    static Connection conn = null;

    public static void connect() {

        try {
            // db parameters
            String url = "jdbc:sqlite:" + dataFolder + "/database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
                        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void init() {
        String createTable = "CREATE TABLE IF NOT EXISTS users(user VARCHAR(36) PRIMARY KEY, balance DOUBLE)";
        try {
            PreparedStatement ps = conn.prepareStatement(createTable);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addMoney(Player player, double amount) {
        String addMoney = "UPDATE users SET balance = balance + ? WHERE user = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(addMoney);
            ps.setDouble(1, amount);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeMoney(Player player, double amount) {
        String removeMoney = "UPDATE users SET balance = balance - ? WHERE user = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(removeMoney);
            ps.setDouble(1, amount);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double getMoney(Player player) {
        String getMoney = "GET balance FROM users WHERE user = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(getMoney);
            ps.setString(1, player.getUniqueId().toString());
            return ps.executeQuery().getDouble("balance");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


