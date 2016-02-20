package net.sparklepopprograms.core.api.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class BaseEnergyStorageBlock extends TileEntity {

	protected long energy;
	protected long capacity;

	public void readFromNBT(NBTTagCompound nbt) {

		this.energy = nbt.getLong("Aura");

		if (energy > capacity) {
			energy = capacity;
		}
	}

	public void writeToNBT(NBTTagCompound nbt) {

		if (energy < 0) {
			energy = 0;
		}
		nbt.setLong("Aura", energy);
	}

	public void setCapacity(long capacity) {

		this.capacity = capacity;

		if (energy > capacity) {
			energy = capacity;
		}
	}

	public long receiveEnergy(long maxReceive, boolean simulate) {

		long energyReceived = Math.min(capacity - energy, maxReceive);

		if (!simulate) {
			energy += energyReceived;
		}
		return energyReceived;
	}

	public long extractEnergy(long maxExtract, boolean simulate) {

		long energyExtracted = Math.min(energy, maxExtract);

		if (!simulate) {
			energy -= energyExtracted;
		}
		return energyExtracted;
	}

	public long getStoredAura() {

		return energy;
	}

	public long getMaxStoredAura() {

		return capacity;
	}
	
	@Override
	public Packet getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, nbttagcompound);
    }
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}

}
