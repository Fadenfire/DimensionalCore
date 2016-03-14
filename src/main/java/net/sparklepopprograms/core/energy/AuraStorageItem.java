package net.sparklepopprograms.core.energy;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class AuraStorageItem extends Item implements IEnergyContainerItem {
	
	private long capacity;

	public void setCapacity(long enteredCapacity) {
		capacity = enteredCapacity;
	}

	public long receiveAura(ItemStack container, long maxReceive, boolean simulate) {

		if (container.stackTagCompound == null) {
			container.stackTagCompound = new NBTTagCompound();
		}
		long energy = container.stackTagCompound.getLong("AuraStorage");
		long energyReceived = Math.min(capacity - energy, maxReceive);

		if (!simulate) {
			energy += energyReceived;
			container.stackTagCompound.setLong("AuraStorage", energy);
		}
		return energyReceived;
	}

	public long extractAura(ItemStack container, long maxExtract, boolean simulate) {

		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("AuraStorage")) {
			return 0;
		}
		long energy = container.stackTagCompound.getInteger("AuraStorage");
		long energyExtracted = Math.min(energy, maxExtract);

		if (!simulate) {
			energy -= energyExtracted;
			container.stackTagCompound.setLong("AuraStorage", energy);
		}
		return energyExtracted;
	}

	public long getStoredAura(ItemStack container) {

		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("AuraStorage")) {
			return 0;
		}
		return container.stackTagCompound.getLong("AuraStorage");
	}

	public long getMaxStoredAura(ItemStack container) {
		return capacity;
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		return 0;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		return (int) (this.extractAura(container, maxExtract / 16, simulate) * 16);
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return (int) (this.getStoredAura(container) * 16);
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return (int) (this.getMaxStoredAura(container) * 16);
	}

}
