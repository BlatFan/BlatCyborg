package ru.blatfan.blatcyborg;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import ru.blatfan.blatcyborg.events.PlayerJoin;
import ru.blatfan.blatcyborg.files.Configs;
import ru.blatfan.blatcyborg.files.DataFile;
import ru.blatfan.blatcyborg.files.Implant;
import ru.blatfan.blatcyborg.gui.CheatImplantsMenu;
import ru.blatfan.blatlibs.BlatPlugin;

import java.util.ArrayList;
import java.util.List;

public final class BlatCyborg extends BlatPlugin {
    @Getter
    private static BlatCyborg instance;
    @Getter
    private static DataFile data;
    @Getter
    private static List<Implant> implants=new ArrayList<>();
    
    @Override
    public void onStartEnabling() {
        instance=this;
        data=new DataFile();
        
        reload();
        
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        
        getCommandFramework().registerCommands(new Commands());
    }
    
    public static void reload(){
        Configs.setup();
        implants = new ArrayList<>();
        for (String id : Configs.getImplantsConfig().getStringList("implants"))
            implants.add(Implant.getImplant(id));
        BlatCyborg.getInstance().menusSetup();
    }
    
    private void menusSetup(){
        CheatImplantsMenu.setup();
    }
}
