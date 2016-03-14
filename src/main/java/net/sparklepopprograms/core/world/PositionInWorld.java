package net.sparklepopprograms.core.world;

public class PositionInWorld {
	
	protected int X;
	protected int Y;
	protected int Z;
	
	public PositionInWorld(int x, int y, int z) {
		X = x;
		Y = y;
		Z = z;
	}
	
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public int getZ() {
		return Z;
	}
	
	public void setPos(int x, int y, int z) {
		X = x;
		Y = y;
		Z = z;
	}

}
