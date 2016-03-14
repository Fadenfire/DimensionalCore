package net.sparklepopprograms.core.updatechecker;

import com.google.common.base.Strings;

import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.sparklepopprograms.core.DimensionalCore;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class UpdateManager {
	
	private String modname;
	private String downloadlink;
	
	public VersionRetriever versionChecker;
	
	private static final ChatStyle catalog = new ChatStyle();
	private static final ChatStyle download = new ChatStyle();
	private static final ChatStyle white = new ChatStyle();
	private static final ChatStyle cyc = new ChatStyle();
	
	private final IChatComponent text = new ChatComponentText("");
	private final IChatComponent tooltip = new ChatComponentTranslation("updater.tooltip");
	
	static {

		catalog.setColor(EnumChatFormatting.GRAY);
		download.setColor(EnumChatFormatting.BLUE);
		white.setColor(EnumChatFormatting.WHITE);
		cyc.setColor(EnumChatFormatting.AQUA);

	}
	
	private UpdateManager(String modname, String downloadURL, int line, String currentVersion) {
		this.modname = modname;
		this.downloadlink = downloadURL;
		
		{
			text.appendSibling(new ChatComponentTranslation("updater.text_1"));
			text.appendText(" " + modname + " ");
			text.appendSibling(new ChatComponentTranslation("updater.text_2"));
			
			tooltip.appendText(" " + modname + ".");
		}
		
		this.versionChecker = new VersionRetriever(line, currentVersion);
		Thread versionCheckThread = new Thread(versionChecker, "UpdateChecker");
		versionCheckThread.start();
	}
	
	public static void register(String modname, String updateURL, int line, String currentVersion) {
		FMLCommonHandler.instance().bus().register(new UpdateManager(modname, updateURL, line, currentVersion));
	}

	@SubscribeEvent
	public void onEvent(PlayerTickEvent event) {
		IChatComponent chat = new ChatComponentText("");
		
		if (!Strings.isNullOrEmpty(downloadlink)) {
			chat.appendText(EnumChatFormatting.BLUE + "[");
			ChatStyle data = download.createShallowCopy();
			data.setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, downloadlink));
			data.setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tooltip.setChatStyle(cyc)));
			chat.appendSibling(new ChatComponentTranslation("updater.download").setChatStyle(data));
			chat.appendText(EnumChatFormatting.BLUE + "] ");
		}
		
		chat.appendSibling(text.setChatStyle(white));
		
		if (event.player.ticksExisted == 25 * 20) {
			FMLCommonHandler.instance().bus().unregister(this);
			if (this.versionChecker.ableToCheckForUpdates) {
				if (!this.versionChecker.isUpToDate()) {
					event.player.addChatMessage(chat);
					event.player.addChatMessage(new ChatComponentText(this.versionChecker.getCatalog()).setChatStyle(catalog));
				}
			}
		}
	}

}
