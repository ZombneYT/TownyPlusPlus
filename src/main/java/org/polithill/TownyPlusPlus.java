package org.polithill;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.cringe.ActivedCommandTsunami;
import org.polithill.town.TownCreate;
import org.polithill.town.century.BlockerCraftListener;
import org.polithill.town.century.CenturySetting;
import org.polithill.town.century.CreateConfigCentury;

import java.util.Set;

public final class TownyPlusPlus extends JavaPlugin {

    private static TownyPlusPlus instance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        //For century
        CreateConfigCentury configCentury = new CreateConfigCentury(this);
        CenturySetting loader = new CenturySetting(this);
        Set<Material> allowedCrafts = loader.getAllowedCrafts();
        configCentury.createCenturyConfig();
        getLogger().info("""
                
                ╔═════════════════════════════════════════════════════════════════╗
                ║░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░║
                ║░████████╗░█████╗░░██╗░░░░░░░██╗███╗░░██╗██╗░░░██╗░░░░░░░░░░░░░░░║
                ║░╚══██╔══╝██╔══██╗░██║░░██╗░░██║████╗░██║╚██╗░██╔╝░░██╗░░░░██╗░░░║
                ║░░░░██║░░░██║░░██║░╚██╗████╗██╔╝██╔██╗██║░╚████╔╝░██████╗██████╗░║
                ║░░░░██║░░░██║░░██║░░████╔═████║░██║╚████║░░╚██╔╝░░╚═██╔═╝╚═██╔═╝░║
                ║░░░░██║░░░╚█████╔╝░░╚██╔╝░╚██╔╝░██║░╚███║░░░██║░░░░░╚═╝░░░░╚═╝░░░║
                ║░░░░╚═╝░░░░╚════╝░░░░╚═╝░░░╚═╝░░╚═╝░░╚══╝░░░╚═╝░░░░░░░░░░░░░░░░░░║
                ║░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░║
                ╚═════════════════════════════════════════════════════════════════╝
                
                Thank you for purchasing the plugin!
                """);
        getCommand("tsunami").setExecutor(new ActivedCommandTsunami());
        getCommand("crumblecookie").setExecutor(new Example());
        getCommand("towny++").setExecutor(this);
        getServer().getPluginManager().registerEvents(new TownCreate(this), this);
        getServer().getPluginManager().registerEvents(new BlockerCraftListener(allowedCrafts), this);
        //getServer().getPluginManager().registerEvents(new ListenerPlayer(), this);
        //TownyCommandAddonAPI.addSubCommand(TownyCommandAddonAPI.CommandType.TOWN, "inv", new TownInvCommand());
    }
    public static TownyPlusPlus getInstance() {
        return instance;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("towny++")) {
            if (args.length == 0) {
                sender.sendMessage("§cсписок команд");
                sender.sendMessage("§c/towny++ reload");
                sender.sendMessage("§c/towny++ info");
                return true;
            }
            String townyplusplus = args[0].toLowerCase();
            switch (townyplusplus) {
                case "info":
                    Player player = (Player) sender;
                    player.sendMessage("""
                        polithill was created with the support of Vodomer and Vetux!
                        The product is sold on github, and a demo version of DemoTowny is also available.
                        """);
                    if(sender.hasPermission("townyplus.admin")) {
                        return true;
                    }

                case "reload":
                    reloadConfig();
                    sender.sendMessage("§aКонфиг перезагружен!");
                    break;

            }
        }
        return true;
    }

    @Override
    public void onDisable() {
        getLogger().info("TownyPlusPlus has been disabled.");
    }
}
