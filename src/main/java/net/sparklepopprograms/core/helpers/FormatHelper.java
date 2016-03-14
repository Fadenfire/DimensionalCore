package net.sparklepopprograms.core.helpers;

import java.math.BigInteger;
import java.sql.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;

public class FormatHelper {
	
	public static DecimalFormat twoDecimals = new DecimalFormat("#.##");
	public static NumberFormat numberWithCommas = new DecimalFormat("#,###");
	
	/** Shortens the number given. For example, if you gave it the number 57,900 to would return 57.9K. */
	public static String shortenNumber(long number) {
		if (number >= Math.pow(1000,6)) {
			return String.valueOf(twoDecimals.format(number / Math.pow(1000,6))) + "E";
		} else if (number >= Math.pow(1000,5)) {
			return String.valueOf(twoDecimals.format(number / Math.pow(1000,5))) + "P";
		} else if (number >= Math.pow(1000,4)) {
			return String.valueOf(twoDecimals.format(number / Math.pow(1000,4))) + "T";
		} else if (number >= Math.pow(1000,3)) {
			return String.valueOf(twoDecimals.format(number / Math.pow(1000,3))) + "G";
		} else if (number >= Math.pow(1000,2)) {
			return String.valueOf(twoDecimals.format(number / Math.pow(1000,2))) + "M";
		} else if (number >= 1000) {
        	return String.valueOf(twoDecimals.format(number / 1000.0)) + "K";
        } else {
        	return String.valueOf(number);
        }
		
	}
	
	/** Adds a shift tooltip.
	 * 
	 * @param list The list to add the tooltip to. 
	 * @param text The text to be shown when holding shift. */
	public static void addShiftTooltip(List list, List<String> text) {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			for (String v : text) {
				list.add(v);
			}
		} else {
			list.add(EnumChatFormatting.GRAY + "Hold " + EnumChatFormatting.YELLOW + EnumChatFormatting.ITALIC + "Shift" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for details" + EnumChatFormatting.RESET);
		}
	}

}
