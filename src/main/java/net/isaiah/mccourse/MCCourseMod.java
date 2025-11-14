package net.isaiah.mccourse;

import net.fabricmc.api.ModInitializer;

import net.isaiah.mccourse.block.ModBlocks;
import net.isaiah.mccourse.item.ModItemGroups;
import net.isaiah.mccourse.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCCourseMod implements ModInitializer {
	public static final String MOD_ID = "mccourse";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItemGroups.registerItemGroups();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
	}
}