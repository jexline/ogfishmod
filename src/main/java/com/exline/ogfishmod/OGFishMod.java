package com.exline.ogfishmod;

import com.exline.fishingloothelperapi.fishing.FishingEntry;
import com.exline.fishingloothelperapi.registry.FishRegistry;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(OGFishMod.MOD_ID)
public class OGFishMod {
    public static final String MOD_ID = "ogfishmod";

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

    public static final DeferredItem<Item> RAW_FISH = ITEMS.registerSimpleItem("raw_fish", p -> p.food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(2).saturationModifier(0.1F).build()));
    public static final DeferredItem<Item> COOKED_FISH = ITEMS.registerSimpleItem("cooked_fish", p -> p.food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(5).saturationModifier(0.6F).build()));

    public OGFishMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        ITEMS.register(modEventBus);
        FishRegistry.registerFishingLoot(RAW_FISH, 50, FishingEntry.FishingType.FISH);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(FMLCommonSetupEvent event) {}

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(RAW_FISH);
            event.accept(COOKED_FISH);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {    }
}
