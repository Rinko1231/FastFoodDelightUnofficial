package net.akaneo.fastfooddelight.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.akaneo.fastfooddelight.FastFoodDelight.MODID;

public class TabInit {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> FF_TAB = TABS.register(MODID, () -> CreativeModeTab.builder()
            // Set name of tab to display
            .title(Component.translatable("item_group." + MODID))
            // Set icon of creative tab
            .icon(() -> new ItemStack(FFItems.FRENCH_FRIES.get()))
            // Add default items to tab
            .displayItems((params, output) -> {
                FFItems.ITEMS.getEntries().forEach(it -> output.accept(it.get()));
            })
            .build()
    );
}