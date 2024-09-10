package net.akaneo.fastfooddelight;

import com.mojang.logging.LogUtils;
import net.akaneo.fastfooddelight.common.Config.FFConfiguration;
import net.akaneo.fastfooddelight.common.registry.FFBlocks;
import net.akaneo.fastfooddelight.common.registry.FFItems;
import net.akaneo.fastfooddelight.common.registry.FFVillagers;
import net.akaneo.fastfooddelight.common.registry.TabInit;
import net.akaneo.fastfooddelight.common.world.FFVillageStructures;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import vectorwing.farmersdelight.common.world.VillageStructures;
import javax.annotation.Nonnull;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(FastFoodDelight.MODID)
public class FastFoodDelight
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "fastfooddelight";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "fastfooddelight" namespace


    public FastFoodDelight()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        FFItems.ITEMS.register(modEventBus);
        FFBlocks.BLOCKS.register(modEventBus);
        FFVillagers.register(modEventBus);
        TabInit.TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.addListener(FFVillageStructures::addNewVillageBuilding);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        FFConfiguration.setup();


    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(FFBlocks.CHECKOUT_MACHINE.get(), RenderType.translucent());
    }
    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
