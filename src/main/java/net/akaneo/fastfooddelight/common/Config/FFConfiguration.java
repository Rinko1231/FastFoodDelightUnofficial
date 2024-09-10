package net.akaneo.fastfooddelight.common.Config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;


public class FFConfiguration
{
    private static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final FFConfiguration INSTANCE = new FFConfiguration();
    private static final ForgeConfigSpec.BooleanValue GenerateStructures;

    private static final ForgeConfigSpec.IntValue StructureWeight;

    static
    {
        BUILDER.push("Fast Food Delight Config");

        GenerateStructures = BUILDER
                .comment("Generate Fast Food Shop Structures in Villages")
                .define("Generate Structures", true);

        StructureWeight = BUILDER
                .comment("Fast Food Shop Structure Weight, max is 25")
                .defineInRange("Structure Weight", 10, 0, 25);

        SPEC = BUILDER.build();
    }

    public boolean doGenerateStructures()
    {
        return GenerateStructures.get();
    }

    public int SWeight ()
    {
        return StructureWeight.get();
    }

    public static void setup()
    {

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC, "fastfooddelight.toml");
    }
    public static FFConfiguration getInstance()
    {
        return INSTANCE;
    }

}