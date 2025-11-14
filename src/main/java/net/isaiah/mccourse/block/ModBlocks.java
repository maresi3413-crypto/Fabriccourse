package net.isaiah.mccourse.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.isaiah.mccourse.MCCourseMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {

    private static Block register(String name,
                                  Function<AbstractBlock.Settings, Block> blockFactory,
                                  AbstractBlock.Settings settings,
                                  boolean shouldRegisterItem) {

        RegistryKey<Block> blockKey = RegistryKey.of(
                RegistryKeys.BLOCK,
                Identifier.of(MCCourseMod.MOD_ID, name)
        );

        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = RegistryKey.of(
                    RegistryKeys.ITEM,
                    Identifier.of(MCCourseMod.MOD_ID, name)
            );

            BlockItem blockItem = new BlockItem(
                    block,
                    new Item.Settings()
                            .registryKey(itemKey)
                            .useBlockPrefixedTranslationKey()
            );

            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    public static final Block FLUORITE_BLOCK = register(
            "fluorite_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .strength(4.0F)
                    .requiresTool(),
            true
    );

    public static void registerModBlocks() {
        MCCourseMod.LOGGER.info("Registering Mod Blocks for " + MCCourseMod.MOD_ID);

        ItemGroupEvents
                .modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                .register(entries -> entries.add(FLUORITE_BLOCK.asItem()));
    }
}
