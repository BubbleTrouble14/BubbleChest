package com.bubblechest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSpecialChest extends ContainerScrollable 
{
    private TileEntitySpecialChest chestInv;
	
	public ContainerSpecialChest(IInventory playerInv, TileEntitySpecialChest te)
	{
		this.chestInv = te;	
		initScrollableSlots();
		
	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
	        }
	    }

	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
	    }
	}
	
	@Override
	public void initScrollableSlots()
	{
		for (int i = 0; i < getVisibleSlotsAmount(); i++)
		{
			System.out.println(getVisibleSlotsAmount());
			this.addSlotToContainer(new EngramSlot((TileEntitySpecialChest) getScrollableInventory(), i, getScrollableSlotsX()
					+ i % getScrollableSlotsWidth() * getSlotSize(), getScrollableSlotsY() + i
							/ getScrollableSlotsWidth() * getSlotSize(), this));
		}
	}

	public class EngramSlot extends SlotScrolling
	{
		public EngramSlot(TileEntitySpecialChest inventoryIn, int index, int xPosition, int yPosition, ContainerSpecialChest container)
		{
			super(inventoryIn, index, xPosition, yPosition, container);
		}

	//	@Override
	//	public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack)
	//	{
			//ContainerEngram.this.setSelected(getEngram());
	//	}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return chestInv.isUseableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
	    ItemStack previous = null;
	    Slot slot = (Slot) this.inventorySlots.get(fromSlot);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        // [...] Custom behaviour

	        if (current.stackSize == 0)
	            slot.putStack((ItemStack) null);
	        else
	            slot.onSlotChanged();

	        if (current.stackSize == previous.stackSize)
	            return null;
	        slot.onPickupFromSlot(playerIn, current);
	    }
	    return previous;
	}

	@Override
	public int getScrollableSlotsWidth()
	{
		return 8;
	}

	@Override
	public int getScrollableSlotsHeight()
	{
		return 4;
	}

	@Override
	public int getScrollableSlotsX()
	{
		return 1;
	}

	@Override
	public int getScrollableSlotsY()
	{
		return 20;
	}

	@Override
	public int getSlotSize()
	{
		return 20;
	}

	@Override
	public IInventory getScrollableInventory()
	{
		return chestInv;
	}
}
