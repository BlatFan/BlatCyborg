package ru.blatfan.blatcyborg.gui;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.blatfan.blatcyborg.BlatCyborg;
import ru.blatfan.blatcyborg.files.Configs;
import ru.blatfan.blatcyborg.files.Implant;
import ru.blatfan.blatlibs.util.InventoryUtils;
import ru.blatfan.blatlibs.util.gui.GUI;

import java.util.ArrayList;
import java.util.List;

public class CheatImplantsMenu {
    private static GUI gui;
    
    public static void setup(){
        List<Implant> implants = BlatCyborg.getImplants();
        List<Implant> implants2 = new ArrayList<>();
        
        gui=new GUI(BlatCyborg.getInstance(), 54, "Cheat");
        gui.setTitle(Configs.getMessages().getString("menu.cheat.implants_menu"));
        
        
        for (int i = 0; i < implants.size(); i++)
            if(implants.get(i).isEnabled()) implants2.add(implants.get(i));
        
        for (int i = 0; i < implants2.size(); i++)
            gui.setItem(i, implants2.get(i).getItem(), action -> action.setCancelled(true));
        
        if(Configs.getConfig().getBoolean("debug"))BlatCyborg.getInstance().getConsole().info("[LOADER] Cheat GUI loaded");
    }
    
    public static void addPlayer(Player player){
        gui.addPlayer(player);
    }
}
