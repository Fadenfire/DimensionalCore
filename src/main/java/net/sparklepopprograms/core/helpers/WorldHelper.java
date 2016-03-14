package net.sparklepopprograms.core.helpers;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.sparklepopprograms.core.world.PositionInWorld;

public class WorldHelper {
	
	/** Breaks a block at the given x, y, z coordinates. 
	 * 
	 * @param world The world.
	 * @param dropBlock Whether or not the block should be dropped.
	 * @param silkTouch Whether or not the block should drop it's drops, or itself. */
	public static void breakBlock(World world, int x, int y, int z, boolean dropBlock, boolean silkTouch) {
        if (!world.isRemote) {
		Block block = world.getBlock(x, y, z);

        if (!(block.getMaterial() == Material.air)) {
            int l = world.getBlockMetadata(x, y, z);
            world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (l << 12));

            if (dropBlock && !silkTouch) {
            	ArrayList<ItemStack> items = block.getDrops(world, x, y, z, l, 0);
            	
            	for (ItemStack item : items) {
            		WorldHelper.spawnItem(world, item, x, y, z);
            	}
            } else if (dropBlock && silkTouch) {
            	Item item = block.getItem(world, x, y, z);
            	
            		if (!(item == null)) {
            			Block block2 = item instanceof ItemBlock && !block.isFlowerPot() ? Block.getBlockFromItem(item) : block;
                		WorldHelper.spawnItem(world, new ItemStack(item, 1, l), x, y, z);
            		}
            }

            world.setBlock(x, y, z, Blocks.air, 0, 3);
        }
        }
    }
	
	/** Spawns an item in the world.
	 * 
	 * @param world The world to spawn the item in. 
	 * @param item The ItemStack to spawn in. */
	public static void spawnItem(World world, ItemStack item, int x, int y, int z) {
		float f = 0.7F;
        double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, item);
        entityitem.delayBeforeCanPickup = 10;
        world.spawnEntityInWorld(entityitem);
	}
	
	/** Gets a list of positions around a block. */
	public static PositionInWorld[] getListOfPositionsAroundBlock(int xCoord, int yCoord, int zCoord) {
		return new PositionInWorld[] {
				new PositionInWorld(xCoord, yCoord - 1, zCoord),
				new PositionInWorld(xCoord, yCoord + 1, zCoord),
				new PositionInWorld(xCoord, yCoord, zCoord - 1),
				new PositionInWorld(xCoord, yCoord, zCoord + 1),
				new PositionInWorld(xCoord - 1, yCoord, zCoord),
				new PositionInWorld(xCoord + 1, yCoord, zCoord)
			};
	}
	
	/** Gets a list of positions around a block. */
	public static PositionInWorld[] getListOfPositionsAroundBlock(PositionInWorld Coord) {
		int xCoord = Coord.getX();
		int yCoord = Coord.getY();
		int zCoord = Coord.getZ();
		
		return new PositionInWorld[] {
				new PositionInWorld(xCoord, yCoord - 1, zCoord),
				new PositionInWorld(xCoord, yCoord + 1, zCoord),
				new PositionInWorld(xCoord, yCoord, zCoord - 1),
				new PositionInWorld(xCoord, yCoord, zCoord + 1),
				new PositionInWorld(xCoord - 1, yCoord, zCoord),
				new PositionInWorld(xCoord + 1, yCoord, zCoord)
			};
	}

}
