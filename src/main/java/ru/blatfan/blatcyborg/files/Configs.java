package ru.blatfan.blatcyborg.files;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import ru.blatfan.blatcyborg.BlatCyborg;
import ru.blatfan.blatlibs.config.BaseConfig;

import java.util.ArrayList;
import java.util.Arrays;

public class Configs {
    @Getter
    private static BaseConfig config;
    @Getter
    private static BaseConfig implantsConfig;
    @Getter
    private static BaseConfig messages;
    
    public static void setup(){
        BlatCyborg bc = BlatCyborg.getInstance();
        config=new BaseConfig(bc, "config.yml");
        
        config.addDefault("debug", false);
        
        config.copyDefault(true);
        config.save();
        
        messages=new BaseConfig(bc, "messages.yml");
        
        messages.addDefault("prefix", ChatColor.GRAY+"["+ChatColor.DARK_PURPLE+"Blat"+ChatColor.DARK_GRAY+"Cyborg"+ChatColor.GRAY+"]"+ChatColor.RESET);
        messages.addDefault("menu.cheat.implants_menu", "Cheat Implants Menu");
        messages.addDefault("commands.error", "{PREFIX} §cThe command was entered incorrectly");
        messages.addDefault("commands.no_permission", "{PREFIX} §cYou don't have enough permissions to use this command");
        messages.addDefault("commands.reload", "{PREFIX} §aPlugin reloaded");
        messages.addDefault("commands.set", "{PREFIX} §aImplant added");
        
        messages.copyDefault(true);
        messages.save();
        
        BlatCyborg.getInstance().setPrefix(Configs.getMessages().getString("prefix"));
        
        implantsConfig=new BaseConfig(bc, "implants.yml");
        
        Implant.addImplant(new Implant("Bio Eyes", null, true, "eyes", "minecraft", Material.SPIDER_EYE.name(), 0, new ArrayList<>(), "blatcyborg.implants.default"));
        Implant.addImplant(new Implant("Bio Arms", null, true, "arms", "minecraft", Material.IRON_PICKAXE.name(), 0, new ArrayList<>(), "blatcyborg.implants.default"));
        Implant.addImplant(new Implant("Bio Body", null, true, "body", "minecraft", Material.BARREL.name(), 0, new ArrayList<>(), "blatcyborg.implants.default"));
        Implant.addImplant(new Implant("Bio Skin", null, true, "skin", "minecraft", Material.IRON_INGOT.name(), 0, new ArrayList<>(), "blatcyborg.implants.default"));
        Implant.addImplant(new Implant("Bio Legs", null, true, "legs", "minecraft", Material.STICK.name(), 0, new ArrayList<>(), "blatcyborg.implants.default"));
        Implant.addImplant(new Implant("Cyber Legs", Arrays.asList("Cybernetic Legs"), true, "legs", "minecraft", Material.STICK.name(), 0, new ArrayList<>(), "blatcyborg.implants.cyber_legs"));
        
        
        implantsConfig.copyDefault(true);
        implantsConfig.save();
        
        if(Configs.getConfig().getBoolean("debug")) bc.getConsole().info("[LOADER] Configs loaded");
    }
}
