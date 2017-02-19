package com.bubblechest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class TileEntitySpecialChest extends TileEntity implements IUpdatePlayerListBox, IInventory{

	
	private ItemStack[] itemStack = new ItemStack[getSizeInventory()];
//	private ArrayList size = new ArrayList();
    private String customName;    
    private int InvSize;
    
    public TileEntitySpecialChest(int slotCount)
    {
    	InvSize = slotCount;
    }

    public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
	
	@Override
	public String getName() {
	    return this.hasCustomName() ? this.customName : "container.te_special_chest";
	}

	@Override
	public boolean hasCustomName() {
	    return this.customName != null && !this.customName.equals("");
	}

	@Override
	public IChatComponent getDisplayName() {
	    return this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName());
	}

	@Override
	public int getSizeInventory() {
		return 100;
	}
	
	public void setSizeInventory(int i)
	{
		InvSize = i;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return itemStack[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
		if(itemStack[index] != null)
		{
			ItemStack stack;
			if(itemStack[index].stackSize <= count)
			{
				stack = itemStack[index];
				itemStack[index] = null;
				markDirty();
				return stack;
			}
			else
			{
				stack = itemStack[index].splitStack(count);
				if(itemStack[index].stackSize == 0)
				{
					itemStack[index] = null;
				}
				markDirty();
				return stack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) 
	{
		if(itemStack[index] != null)
		{
			ItemStack stack = itemStack[index];
			itemStack[index] = null;
			return stack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		itemStack[index] = stack;
		if(stack != null && stack.stackSize > getInventoryStackLimit())
		{
			stack.stackSize = getInventoryStackLimit();
		}
		markDirty();
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
	    return this.worldObj.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {	
	}

	@Override
	public void closeInventory(EntityPlayer player) {		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {		
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {

		for(int i = 0; i < itemStack.length; i++)
		{
			itemStack[i] = null;
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
	    super.writeToNBT(nbt);

	    NBTTagList list = new NBTTagList();
	    for (int i = 0; i < this.getSizeInventory(); ++i) {
	        if (this.getStackInSlot(i) != null) {
	            NBTTagCompound stackTag = new NBTTagCompound();
	            stackTag.setByte("Slot", (byte) i);
	            this.getStackInSlot(i).writeToNBT(stackTag);
	            list.appendTag(stackTag);
	        }
	    }
	    nbt.setTag("Items", list);

	    if (this.hasCustomName()) {
	        nbt.setString("CustomName", this.getCustomName());
	    }
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
	    super.readFromNBT(nbt);

	    NBTTagList list = nbt.getTagList("Items", 10);
	    for (int i = 0; i < list.tagCount(); ++i) {
	        NBTTagCompound stackTag = list.getCompoundTagAt(i);
	        int slot = stackTag.getByte("Slot") & 255;
	        this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
	    }

	    if (nbt.hasKey("CustomName", 8)) {
	        this.setCustomName(nbt.getString("CustomName"));
	    }
	}

	@Override
	public void update() 
	{
		for(int i = 0; i < itemStack.length; i++)
		{
			if(itemStack[i] == new ItemStack(Items.diamond))
			{
				System.out.println("Found a Diamond");
				setSizeInventory(getSizeInventory() + 1);
			}
		}
	}
}
