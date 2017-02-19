package com.bubblechest;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class BlockRenderRegister {
	public static void registerBlockRenderer() {
	    reg(ModBlocks.special_chest);
	}

	public static void reg(Block block) {
	    Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(BubbleChest.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
}
