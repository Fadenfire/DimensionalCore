package net.sparklepopprograms.core.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT) 
public class CustomItemRenderer implements IItemRenderer {
	
	TileEntitySpecialRenderer model;
	TileEntity tile;
	
	public CustomItemRenderer(TileEntitySpecialRenderer inputModel, TileEntity inputTile) {
		model = inputModel;
		tile = inputTile;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if(type == IItemRenderer.ItemRenderType.ENTITY) {
			GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
		}
		
		model.renderTileEntityAt(tile, 0.0D, 0.0D, 0.0D, 0.0F);
		
	}

}