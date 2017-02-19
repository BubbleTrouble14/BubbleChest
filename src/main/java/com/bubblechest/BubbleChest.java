package com.bubblechest;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BubbleChest.MODID, name = BubbleChest.MODNAME, version = BubbleChest.VERSION)
public class BubbleChest 
{
    public static final String MODID = "bubblechest";
    public static final String MODNAME = "Bubble's Special Chest";
    public static final String VERSION = "1.0.0";    
    
    @Instance
    public static BubbleChest instance = new BubbleChest();
    
    @SidedProxy(clientSide="com.bubblechest.ClientProxy", serverSide="com.bubblechest.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        this.proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        this.proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        this.proxy.postInit(e);
    }
}
