package net.akaneo.fastfooddelight.common.world;

import com.mojang.datafixers.util.Pair;
import net.akaneo.fastfooddelight.FastFoodDelight;
import net.akaneo.fastfooddelight.common.Config.FFConfiguration;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;


import java.util.ArrayList;
import java.util.List;

public class FFVillageStructures
{
    public static void addNewVillageBuilding(final ServerAboutToStartEvent event) {
        if (!FFConfiguration.getInstance().doGenerateStructures()) {
            return;
        }

        int WeightOfStructure = FFConfiguration.getInstance().SWeight();

        Registry<StructureTemplatePool> templatePools = event.getServer().registryAccess().registry(Registries.TEMPLATE_POOL).get();
        Registry<StructureProcessorList> processorLists = event.getServer().registryAccess().registry(Registries.PROCESSOR_LIST).get();

        FFVillageStructures.addBuildingToPool(templatePools, processorLists, ResourceLocation.parse("minecraft:village/plains/houses"), FastFoodDelight.MODID + ":village/houses/plains_fast_food", WeightOfStructure);
        FFVillageStructures.addBuildingToPool(templatePools, processorLists, ResourceLocation.parse("minecraft:village/snowy/houses"), FastFoodDelight.MODID + ":village/houses/snowy_fast_food", WeightOfStructure);
        FFVillageStructures.addBuildingToPool(templatePools, processorLists, ResourceLocation.parse("minecraft:village/savanna/houses"), FastFoodDelight.MODID + ":village/houses/savanna_fast_food", WeightOfStructure);
        FFVillageStructures.addBuildingToPool(templatePools, processorLists, ResourceLocation.parse("minecraft:village/desert/houses"), FastFoodDelight.MODID + ":village/houses/desert_fast_food", WeightOfStructure);
        FFVillageStructures.addBuildingToPool(templatePools, processorLists, ResourceLocation.parse("minecraft:village/taiga/houses"), FastFoodDelight.MODID + ":village/houses/taiga_fast_food", WeightOfStructure);
    }

    public static void addBuildingToPool(Registry<StructureTemplatePool> templatePoolRegistry, Registry<StructureProcessorList> processorListRegistry, ResourceLocation poolRL, String nbtPieceRL, int weight) {
        StructureTemplatePool pool = templatePoolRegistry.get(poolRL);
        if (pool == null) return;

        ResourceLocation emptyProcessor =  ResourceLocation.withDefaultNamespace("empty");
        Holder<StructureProcessorList> processorHolder = processorListRegistry.getHolderOrThrow(ResourceKey.create(Registries.PROCESSOR_LIST, emptyProcessor));

        SinglePoolElement piece = SinglePoolElement.single(nbtPieceRL, processorHolder).apply(StructureTemplatePool.Projection.RIGID);

        for (int i = 0; i < weight; i++) {
            pool.templates.add(piece);
        }

        List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(pool.rawTemplates);
        listOfPieceEntries.add(new Pair<>(piece, weight));
        pool.rawTemplates = listOfPieceEntries;
    }

}