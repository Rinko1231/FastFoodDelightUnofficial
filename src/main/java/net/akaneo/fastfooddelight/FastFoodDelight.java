package net.akaneo.fastfooddelight;

import com.mojang.logging.LogUtils;
import net.akaneo.fastfooddelight.common.Config.FFConfiguration;
import net.akaneo.fastfooddelight.common.registry.*;
import net.akaneo.fastfooddelight.common.world.FFVillageStructures;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("fastfooddelight")
public class FastFoodDelight
{
    public static final String MODID = "fastfooddelight";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "fastfooddelight" namespace


    public FastFoodDelight(ModContainer modContainer,IEventBus modBus)
    {
        modContainer.registerConfig(ModConfig.Type.COMMON, FFConfiguration.SPEC,"fastfooddelight.toml");


        FFBlocks.BLOCKS.register(modBus);
        FFItems.ITEMS.register(modBus);
        FFVillagers.POI.register(modBus);
        FFVillagers.PROFESSIONS.register(modBus);
        TabInit.TABS.register(modBus);
        NeoForge.EVENT_BUS.addListener(FFVillageStructures::addNewVillageBuilding);

    }


}
