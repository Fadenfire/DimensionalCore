package net.sparklepopprograms.core.util;

import java.math.BigInteger;
import java.sql.Array;
import java.text.DecimalFormat;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.util.EnumChatFormatting;

public class FormatHelper {
	
	private static DecimalFormat twoDP = new DecimalFormat("#.##");
	
	public static String shortenNumber(long number) {
		if (number >= Math.pow(1000,6)) {
			return String.valueOf(twoDP.format(number / Math.pow(1000,6))) + "E";
		} else if (number >= Math.pow(1000,5)) {
			return String.valueOf(twoDP.format(number / Math.pow(1000,5))) + "P";
		} else if (number >= Math.pow(1000,4)) {
			return String.valueOf(twoDP.format(number / Math.pow(1000,4))) + "T";
		} else if (number >= Math.pow(1000,3)) {
			return String.valueOf(twoDP.format(number / Math.pow(1000,3))) + "G";
		} else if (number >= Math.pow(1000,2)) {
			return String.valueOf(twoDP.format(number / Math.pow(1000,2))) + "M";
		} else if (number >= 1000) {
        	return String.valueOf(twoDP.format(number / 1000.0)) + "K";
        } else {
        	return String.valueOf(number);
        }
		
	}
	
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
