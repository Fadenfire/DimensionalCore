package net.sparklepopprograms.core;

import scala.Int;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.sparklepopprograms.core.compatibility.ModCompatibility;
import net.sparklepopprograms.core.energy.AuraStorageTile;
import net.sparklepopprograms.core.helpers.LogHelper;
import net.sparklepopprograms.core.updatechecker.UpdateManager;
import net.sparklepopprograms.core.updatechecker.VersionRetriever;
import cpw.mods.fml.common.Mod.EventHandler;

@Mod(modid = DimensionalCore.modid, name = "DimensionalCore", version = DimensionalCore.version)
public class DimensionalCore {
	
	public static final String modid = "DimensionalCore";
	public static final String version = "1.0.7";
	
	public static final String updateURL = "http://minecraft.curseforge.com/projects/";

	@EventHandler
	public void load(FMLPreInitializationEvent event) {
		
		
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		ModCompatibility.register(event);
		
		UpdateManager.register("DimensionalCore", DimensionalCore.updateURL + "dimensionalcore/files", 0, this.version);
	}
	
	@EventHandler
	public void load(FMLPostInitializationEvent event) {
		
	}
	
	@EventHandler
	public void imcCallback(FMLInterModComms.IMCEvent event) {
		
	}
}