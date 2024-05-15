package ru.blatfan.blatcyborg.files;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.blatfan.blatcyborg.BlatCyborg;
import ru.blatfan.blatlibs.config.BaseConfig;
import ru.blatfan.blatlibs.util.compatibility.ItemsAdderManager;

import java.util.*;

@Getter
public class Implant {
    private String id;
    private String name;
    private List<String> description;
    private boolean enabled;
    private String type;
    private String icon_type;
    private String icon_item;
    private int icon_model_data;
    private List<String> effects;
    private String permission;
    
    public Implant(String name, List<String> description, boolean enabled, String type, String icon_type, String icon_item, int icon_model_data, List<String> effects, String permission){
        this.id = name.replace(" ", "_").toLowerCase();
        this.name=name;
        this.description=description;
        this.enabled=enabled;
        this.type=type;
        this.icon_type=icon_type;
        this.icon_item=icon_item;
        this.icon_model_data=icon_model_data;
        this.effects=effects;
        this.permission=permission;
    }
    
    @Override
    public String toString() {
        return "Implant{name="+name+"; description="+description.toString()+"; enabled="+enabled+"; type:"+type+"; icon_type:"+icon_type+"; icon_item="+icon_item+"; icon_model_data"+icon_model_data+"; effects="+effects.toString()+"; permission="+permission+"}";
    }
    
    public static void addImplant(Implant implant){
        BaseConfig imConfig = Configs.getImplantsConfig();
        List<String> implants = imConfig.getStringList("implants");
        String id = implant.getId();
        
        if (!Configs.getImplantsConfig().getStringList("implants").contains(id)) {
            implants.add(id);
            imConfig.set("implants", implants);
        } else
            if(Configs.getConfig().getBoolean("debug"))
                BlatCyborg.getInstance().getConsole().warn("[LOADER] The implant with the ID: '"+id+"' has already been registered");
        
        
        
        setImplant(implant);
    }
    public static void setImplant(Implant implant){
        BaseConfig imConfig = Configs.getImplantsConfig();
        String id = implant.getId();
        
        imConfig.set(id+".name", implant.name);
        imConfig.set(id+".description", implant.description);
        imConfig.set(id+".enable", implant.enabled);
        imConfig.set(id+".type", implant.type);
        imConfig.set(id+".icon.type", implant.icon_type);
        imConfig.set(id+".icon.item", implant.icon_item);
        imConfig.set(id+".icon.model_data", implant.icon_model_data);
        imConfig.set(id+".effects", implant.effects);
        imConfig.set(id+".permission", implant.permission);
        
        imConfig.save();
        if(Configs.getConfig().getBoolean("debug")) BlatCyborg.getInstance().getConsole().info("[LOADER] The implant with the ID: '"+id+"' registered");
    }
    
    public static Implant getImplant(String id){
        BaseConfig config = Configs.getImplantsConfig();
        if(config.contains(id))
            return new Implant(
                config.getRawString(id+".name"),
                config.getStringList(id+".description"),
                config.getBoolean(id+".enable"),
                config.getRawString(id+".type"),
                config.getRawString(id+".icon.type"),
                config.getRawString(id+".icon.item"),
                config.getInt(id+".icon.model_data"),
                config.getRawStringList(id+".effects"),
                config.getRawString(id+".permission")
            );
        ArrayList<String> lore = new ArrayList<>();
        lore.add("UNKNOWN");
        lore.add("UNKNOWN");
        lore.add("UNKNOWN");
        lore.add("UNKNOWN");
        lore.add("UNKNOWN");
        lore.add("UNKNOWN");
        return new Implant("UNKNOWN", lore, true, "eyes", "minecraft", Material.BARRIER.name(), 0, new ArrayList<>(), "unknown");
    }
    private ItemStack getRawItem(){
        if(icon_type == "itemsadder") {
            ItemStack item1 = ItemsAdderManager.getItem(icon_item);
            ItemMeta meta = item1.getItemMeta();
            if(meta == null)
                return item1;
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_DYE,ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
            meta.setDisplayName(name);
            if(description!=null)meta.setLore(description);
            item1.setItemMeta(meta);
            return item1;
        }
        else if(icon_type == "minecraft")
        {
            ItemStack item2 = new ItemStack(Material.valueOf(icon_item));
            ItemMeta meta = item2.getItemMeta();
            if(meta == null)
                return item2;
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_DYE,ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_PLACED_ON,ItemFlag.HIDE_POTION_EFFECTS,ItemFlag.HIDE_UNBREAKABLE);
            meta.setCustomModelData(icon_model_data);
            meta.setDisplayName(name);
            if(description!=null)meta.setLore(description);
            item2.setItemMeta(meta);
            return item2;
        }
        return new ItemStack(Material.AIR);
    }
    public ItemStack getItem(){
        ItemStack item = getRawItem();
        
        if(Configs.getConfig().getBoolean("debug") && item.getItemMeta() != null) {
            BlatCyborg.getInstance().getConsole().info("[LOADER] Got implant (" + item.getItemMeta().getDisplayName() + ")");
        }
        
        return item;
    }
}
