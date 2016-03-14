package net.sparklepopprograms.core.compatibility;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;

public class ModCompatibility {
	
	public static void register(FMLInitializationEvent event) {
		FMLInterModComms.sendMessage("Waila", "register", "net.sparklepopprograms.core.compatibility.WailaHandler.callbackRegister");
		
		if (Loader.isModLoaded("PracticalLogistics")) {
			
		}
	}

}
