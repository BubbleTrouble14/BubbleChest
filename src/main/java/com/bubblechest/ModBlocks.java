package com.bubblechest;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block special_chest;

    public static void createBlocks()
    {
        GameRegistry.registerBlock(special_chest = new ChestSpecial("special_chest"), "special_chest");
        GameRegistry.registerTileEntity(TileEntitySpecialChest.class, "te_special_chest");
    }
}
