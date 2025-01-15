package net.akaneo.fastfooddelight.common.registry;
import com.google.common.collect.ImmutableSet;
import net.akaneo.fastfooddelight.FastFoodDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

public class FFVillagers {
    public static ResourceLocation RL(String path) {
        return ResourceLocation.fromNamespaceAndPath(FastFoodDelight.MODID, path);
    }

    public static final DeferredRegister<PoiType> POI
            = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, FastFoodDelight.MODID);
    public static final DeferredRegister<VillagerProfession> PROFESSIONS
            = DeferredRegister.create(Registries.VILLAGER_PROFESSION, FastFoodDelight.MODID);

    public static final DeferredHolder<PoiType, PoiType> CHECKOUT_MACHINE_POI
            = POI.register("checkout_machine_poi", () -> new PoiType(getAllStates(FFBlocks.CHECKOUT_MACHINE.get()), 1, 1));
    public static final Supplier<VillagerProfession> FAST_FOOD_WAITER
            = registerProfession("fast_food_waiter", FFVillagers.CHECKOUT_MACHINE_POI, SoundEvents.VILLAGER_WORK_BUTCHER);

    @SuppressWarnings("SameParameterValue")
    private static Supplier<VillagerProfession> registerProfession(String name, DeferredHolder<PoiType, PoiType> poiType, SoundEvent sound) {
        return PROFESSIONS.register(name, () -> register(RL(name), poiType, sound));
    }

    private static VillagerProfession register(ResourceLocation name, DeferredHolder<PoiType, PoiType> poi, SoundEvent sound) {
        ResourceKey<PoiType> poiName = Objects.requireNonNull(poi.getKey());
        return new VillagerProfession(
                name.toString(), holder -> holder.is(poiName), holder -> holder.is(poiName),
                ImmutableSet.of(), ImmutableSet.of(), sound
        );
    }

    private static Set<BlockState> getAllStates(Block block) {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }



}