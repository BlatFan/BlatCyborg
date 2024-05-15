package ru.blatfan.blatcyborg;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginDescriptionFile;
import ru.blatfan.blatcyborg.files.Configs;
import ru.blatfan.blatcyborg.files.Permissions;
import ru.blatfan.blatcyborg.gui.CheatImplantsMenu;
import ru.blatfan.blatlibs.commandframework.Command;
import ru.blatfan.blatlibs.commandframework.CommandArguments;
import ru.blatfan.blatlibs.commandframework.Completer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Commands {
    public Commands(){
        if(Configs.getConfig().getBoolean("debug"))BlatCyborg.getInstance().getConsole().info("[LOADER] Commands loaded");
    }
    
    @Command(
        name = "blatcyborg"
    )
    public void blatcyborgCommand(CommandArguments arg){
        BlatCyborg(arg);
    }
    @Command(
        name = "blatcyborg.cheat"
    )
    public void blatcyborgCheatCommand(CommandArguments arg){
        if (arg.hasPermission(Permissions.cheatMenu)) CheatMenu(arg);
        else arg.sendMessage(Configs.getMessages().getString("commands.no_permission").replace("{PREFIX}", Configs.getMessages().getString("prefix")));
    }
    @Command(
        name = "blatcyborg.reload"
    )
    public void blatcyborgReloadCommand(CommandArguments arg){
        if(arg.hasPermission(Permissions.reload)) {
            BlatCyborg.reload();
            arg.sendMessage(Configs.getMessages().getString("commands.reload").replace("{PREFIX}", Configs.getMessages().getString("prefix")));
        }
        else arg.sendMessage(Configs.getMessages().getString("commands.no_permission").replace("{PREFIX}", Configs.getMessages().getString("prefix")));
    }
    @Completer(
        name = "blatcyborg"
    )
    public List<String> blatcyborgTabs(CommandArguments arg){
        List<String> tabs = new ArrayList<>();
        if(arg.getLength()==1){
            if(arg.hasPermission(Permissions.cheatMenu)) tabs.add("cheat");
            if(arg.hasPermission(Permissions.reload)) tabs.add("reload");
        }
        
        
        return tabs;
    }
    
    public static void BlatCyborg(CommandArguments arg){
        PluginDescriptionFile dec = BlatCyborg.getInstance().getDescription();
        arg.sendMessage("");
        arg.sendMessage("");
        arg.sendMessage(ChatColor.GRAY+"---------------------"+BlatCyborg.getInstance().getPrefix().replace("[", "").replace("]", "")+ChatColor.GRAY+"--------------------");
        arg.sendMessage(ChatColor.GRAY+"Description: "+ ChatColor.LIGHT_PURPLE +dec.getDescription());
        arg.sendMessage(ChatColor.GRAY+"Authors: "+ ChatColor.LIGHT_PURPLE +dec.getAuthors().get(0));
        arg.sendMessage(ChatColor.GRAY+"Version: "+ ChatColor.LIGHT_PURPLE +dec.getVersion());
        arg.sendMessage(ChatColor.GRAY+"MC version: "+ ChatColor.LIGHT_PURPLE +dec.getAPIVersion());
        arg.sendMessage(ChatColor.GRAY+"--------------------------------------------------");
        arg.sendMessage("");
        arg.sendMessage("");
    }
    public static void CheatMenu(CommandArguments arg){
        CheatImplantsMenu.addPlayer(arg.getSender());
    }
}
