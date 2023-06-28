package com.aeroshide.efficient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

import java.text.DecimalFormat;

public class Efficient implements ModInitializer {
	public static final Logger LOG = LogManager.getLogger("Efficient");
	public static DecimalFormat df = new DecimalFormat("##.#");
	@Override
	public void onInitialize() {


		LOG.warn("Efficient is doing dangerous stuff");
	}
}
