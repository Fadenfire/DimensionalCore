package net.sparklepopprograms.core.api.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class BaseEnergyStorageBlock extends TileEntity implements IAuraUser {

	long energy;
	long capacity;

	public void readFromNBT(NBTTagCompound var1) {
	    super.readFromNBT(var1);
	    energy = var1.getLong("AuraStorage");
	  }
	
	public void writeToNBT(NBTTagCompound var1) {
	    super.writeToNBT(var1);
	    var1.setLong("AuraStorage", energy);
	  }

	public void setCapacity(long capacity) {

		this.capacity = capacity;

		if (energy > capacity) {
			energy = capacity;
		}
	}

	public long receiveAura(long maxReceive, boolean simulate) {

		long energyReceived = Math.min(capacity - energy, maxReceive);

		if (!simulate) {
			energy += energyReceived;
		}
		
		return energyReceived;
	}

	public long extractAura(long maxExtract, boolean simulate) {

		long energyExtracted = Math.min(energy, maxExtract);

		if (!simulate) {
			energy -= energyExtracted;
		}
		
		return energyExtracted;
	}
	
	public void modifyEnergyStored(int energy) {

		this.energy += energy;

		if (this.energy > capacity) {
			this.energy = capacity;
		} else if (this.energy < 0) {
			this.energy = 0;
		}
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
