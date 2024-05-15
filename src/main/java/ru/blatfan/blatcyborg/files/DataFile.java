package ru.blatfan.blatcyborg.files;

import org.bukkit.entity.Player;
import ru.blatfan.blatcyborg.BlatCyborg;
import ru.blatfan.blatlibs.config.BaseConfig;

public class DataFile extends BaseConfig {
    public DataFile() {
        super(BlatCyborg.getInstance(), "data.yml");
    }
    
    public void setPlayerImplant(Player player, Implant implant, boolean b){
        set(player.getUniqueId().toString()+"."+implant.getType(), implant.getId());
        save();
    }
    
    public boolean hasPlayerImplant(Player player, Implant implant){
        return this.getString(player.getUniqueId().toString()+"."+implant.getType()) == implant.getId();
    }
}
