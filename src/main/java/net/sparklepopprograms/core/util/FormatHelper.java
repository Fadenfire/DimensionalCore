package net.sparklepopprograms.core.util;


import java.sql.Array;
import java.text.DecimalFormat;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class FormatHelper {
	
	private static DecimalFormat twoDP = new DecimalFormat("#.##");
	
	public static String shortenNumber(int number) {
		if (number >= 1000000000) {
            return String.valueOf(twoDP.format(number / 1000000000.0)) + "B";
		} else if (number >= 1000000) {
            return String.valueOf(twoDP.format(number / 1000000.0)) + "M";
        } else if (number >= 1000) {
            return String.valueOf(twoDP.format(number / 1000)) + "K";
        } else {
            return String.valueOf(number);
        }
		
	}
	
	public static void addShiftText(EntityPlayer player, List list, List<String> text) {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			for (String v : text) {
				list.add(v);
			}
		} else {
			list.add(EnumChatFormatting.GRAY + "Hold " + EnumChatFormatting.YELLOW + EnumChatFormatting.ITALIC + "Shift" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for details" + EnumChatFormatting.RESET);
		}
	}

}
