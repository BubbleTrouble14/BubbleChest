package com.bubblechest;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy 
{
	
    public void preInit(FMLPreInitializationEvent e) 
    {
    	ModBlocks.createBlocks();
    }

    public void init(FMLInitializationEvent e) {

        NetworkRegistry.INSTANCE.registerGuiHandler(BubbleChest.instance, new GUIHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
