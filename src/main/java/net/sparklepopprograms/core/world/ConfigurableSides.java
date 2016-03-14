package net.sparklepopprograms.core.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class ConfigurableSides {
	
	private String down;
	private String up;
	private String north;
	private String south;
	private String west;
	private String east;
	
	public ConfigurableSides(String defaultConfig) {
		down = defaultConfig;
		up = defaultConfig;
		north = defaultConfig;
		south = defaultConfig;
		west = defaultConfig;
		east = defaultConfig;
	}
	
	public void setSideConfiguration(ForgeDirection side, String value) {
		if (side == ForgeDirection.DOWN) {
			down = value;
		} else if (side == ForgeDirection.UP) {
			up = value;
		} else if (side == ForgeDirection.NORTH) {
			north = value;
		} else if (side == ForgeDirection.SOUTH) {
			south = value;
		} else if (side == ForgeDirection.WEST) {
			west = value;
		} else if (side == ForgeDirection.EAST) {
			east = value;
		}
	}
	
	public String getSideConfiguration(ForgeDirection side) {
		if (side == ForgeDirection.DOWN) {
			return down;
		} else if (side == ForgeDirection.UP) {
			return up;
		} else if (side == ForgeDirection.NORTH) {
			return north;
		} else if (side == ForgeDirection.SOUTH) {
			return south;
		} else if (side == ForgeDirection.WEST) {
			return west;
		} else if (side == ForgeDirection.EAST) {
			return east;
		} else {
			return null;
		}
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		down = nbt.getString("ConfigurableSideDown");
		up = nbt.getString("ConfigurableSideUp");
		north = nbt.getString("ConfigurableSideNorth");
		south = nbt.getString("ConfigurableSideSouth");
		west = nbt.getString("ConfigurableSideWest");
		east = nbt.getString("ConfigurableSideEast");
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setString("ConfigurableSideDown", down);
		nbt.setString("ConfigurableSideUp", up);
		nbt.setString("ConfigurableSideNorth", north);
		nbt.setString("ConfigurableSideSouth", south);
		nbt.setString("ConfigurableSideWest", west);
		nbt.setString("ConfigurableSideEast", east);
	}

}
