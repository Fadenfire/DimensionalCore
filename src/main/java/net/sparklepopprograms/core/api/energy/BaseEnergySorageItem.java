package net.sparklepopprograms.core.api.energy;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BaseEnergySorageItem extends Item {
	
	protected long capacity;

	public void setCapacity(long enteredCapacity) {
		capacity = enteredCapacity;
	}

	public long receiveAura(ItemStack container, long maxReceive, boolean simulate) {

		if (container.stackTagCompound == null) {
			container.stackTagCompound = new NBTTagCompound();
		}
		long energy = container.stackTagCompound.getLong("Aura");
		long energyReceived = Math.min(capacity - energy, maxReceive);

		if (!simulate) {
			energy += energyReceived;
			container.stackTagCompound.setLong("Aura", energy);
		}
		return energyReceived;
	}

	public long extractAura(ItemStack container, long maxExtract, boolean simulate) {

		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Aura")) {
			return 0;
		}
		long energy = container.stackTagCompound.getInteger("Aura");
		long energyExtracted = Math.min(energy, maxExtract);

		if (!simulate) {
			energy -= energyExtracted;
			container.stackTagCompound.setLong("Aura", energy);
		}
		return energyExtracted;
	}

	public long getStoredAura(ItemStack container) {

		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Aura")) {
			return 0;
		}
		return container.stackTagCompound.getLong("Aura");
	}

	public long getMaxStoredAura(ItemStack container) {

		return capacity;
	}

}
