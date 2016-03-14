package net.sparklepopprograms.core.updatechecker;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sparklepopprograms.core.DimensionalCore;
import net.sparklepopprograms.core.helpers.LogHelper;

import org.apache.commons.io.IOUtils;

import cpw.mods.fml.common.Loader;

public class VersionRetriever implements Runnable {
	
	private String currentVersion;
	private String newestVersion;
	private String catalog;
	private int line;
	private boolean isUpToDate = false;
	
	public boolean ableToCheckForUpdates = true;
	

	public VersionRetriever(int line, String currentVersion) {
		this.line = line;
		this.currentVersion = currentVersion;
	}
	
	@Override
	public void run() {
		List<String> pageData = null;
		
		InputStream in = null;
		
		try {
			in = new URL("http://pastebin.com/raw/wBaagZTP").openStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			ableToCheckForUpdates = false;
		} catch (IOException e) {
			e.printStackTrace();
			LogHelper.warn("Unable to check for updates!", "DimensionalUpdateChecker");
			ableToCheckForUpdates = false;
		}
		
		try {
			pageData = IOUtils.readLines(in);
		} catch (IOException e) {
			e.printStackTrace();
			LogHelper.warn("Unable to check for updates!", "DimensionalUpdateChecker");
			ableToCheckForUpdates = false;
		} finally {
			IOUtils.closeQuietly(in);
		}
		
		if (ableToCheckForUpdates) {
			newestVersion = pageData.get(line);
			catalog = pageData.get(line + 1);
		}
		
		if (newestVersion.equals(currentVersion)) {
			isUpToDate = true;
		}
	}
	
	public String getNewestVersion() {
		return newestVersion;
	}
	
	public boolean isUpToDate() {
		return isUpToDate;
	}
	
	public String getCatalog() {
		return catalog;
	}
	
}
