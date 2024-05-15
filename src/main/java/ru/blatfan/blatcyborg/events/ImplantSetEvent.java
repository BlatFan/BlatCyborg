package ru.blatfan.blatcyborg.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ru.blatfan.blatcyborg.files.Implant;

public class ImplantSetEvent extends Event {
    @Getter
    private Implant implant;
    @Getter
    private Player player;
    private HandlerList handlers;
    
    public ImplantSetEvent(Implant implant, Player player){
        this.implant = implant;
        this.player = player;
    }
    
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
