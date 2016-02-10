package net.sparklepopprograms.core.util;

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

public class WorldHelper {
	
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

            	if (!(block == Blocks.dragon_egg)) {
            	
            		if (!(item == null)) {
            			Block block2 = item instanceof ItemBlock && !block.isFlowerPot() ? Block.getBlockFromItem(item) : block;
                		WorldHelper.spawnItem(world, new ItemStack(item, 1, l), x, y, z);
            		}
            	} else if (block == Blocks.dragon_egg) {
            		WorldHelper.spawnItem(world, new ItemStack(Blocks.dragon_egg, 1), x, y, z);
            	}
            }

            world.setBlock(x, y, z, Blocks.air, 0, 3);
        }}
    }
	
	public static void spawnItem(World world, ItemStack item, int  x, int y, int z) {
		float f = 0.7F;
        double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
        EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, item);
        entityitem.delayBeforeCanPickup = 10;
        world.spawnEntityInWorld(entityitem);
	}

}
