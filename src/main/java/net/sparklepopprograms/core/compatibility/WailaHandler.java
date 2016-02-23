package net.sparklepopprograms.core.compatibility;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.sparklepopprograms.core.api.energy.BaseEnergyStorageBlock;
import net.sparklepopprograms.core.api.energy.IAuraUser;
import net.sparklepopprograms.core.util.FormatHelper;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import cpw.mods.fml.common.Optional;

@Optional.Interface(iface = "mcp.mobius.waila.api.IWailaDataProvider", modid = "Waila")
public class WailaHandler implements IWailaDataProvider {

	@Optional.Method(modid = "Waila")
    public static void callbackRegister(IWailaRegistrar register) {

        register.registerBodyProvider(new WailaHandler(), IAuraUser.class);

        register.addConfig("Aura", "AuraEnergySystem.ShowAuraStorage");
    }
	
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		
		if (config.getConfig("AuraEnergySystem.ShowAuraStorage")) {
			long storedAura = accessor.getNBTData().getLong("AuraStorage");
			long maxStoredAura = ((BaseEnergyStorageBlock)accessor.getTileEntity()).getMaxStoredAura();
			
			currenttip.add("Aura: " + FormatHelper.shortenNumber(storedAura) + " / " + FormatHelper.shortenNumber(maxStoredAura));
			
        }
		
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
		
		
		return tag;
	}
	
	
}