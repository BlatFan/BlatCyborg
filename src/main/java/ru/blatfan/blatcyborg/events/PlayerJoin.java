package ru.blatfan.blatcyborg.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.blatfan.blatcyborg.BlatCyborg;
import ru.blatfan.blatcyborg.files.DataFile;

import java.util.Arrays;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        DataFile data = BlatCyborg.getData();
        String uuid = event.getPlayer().getUniqueId().toString();
        
        if(!data.contains(uuid)){
            data.set(uuid+".level", 1);
            data.set(uuid+".implants.eyes", "bio_eyes");
            data.set(uuid+".implants.arms", "bio_arms");
            data.set(uuid+".implants.body", "bio_body");
            data.set(uuid+".implants.skin", "bio_skin");
            data.set(uuid+".implants.legs", "bio_legs");
            data.save();
        }
    }
}
