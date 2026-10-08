package com.exline.ogfishmod.datagen;

import com.exline.ogfishmod.OGFishMod;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, OGFishMod.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        flat(itemModels,
                OGFishMod.RAW_FISH.get(),
                OGFishMod.COOKED_FISH.get()
        );
    }

    private static void flat(ItemModelGenerators itemModels, Item... items) {
        for (Item item : items) {
            itemModels.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
        }
    }
}