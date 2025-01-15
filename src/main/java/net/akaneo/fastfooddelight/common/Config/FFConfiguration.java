package net.akaneo.fastfooddelight.common.Config;


import net.neoforged.neoforge.common.ModConfigSpec;

public class FFConfiguration
{
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    private static final FFConfiguration INSTANCE = new FFConfiguration();
    private static final ModConfigSpec.BooleanValue GenerateStructures;

    private static final ModConfigSpec.IntValue StructureWeight;

    static
    {
        BUILDER.push("Fast Food Delight Unofficial Config");

        GenerateStructures = BUILDER
                .comment("Generate Fast Food Shop Structures in Villages")
                .define("Generate Structures", true);

        StructureWeight = BUILDER
                .comment("Fast Food Shop Structure Weight, max is 50")
                .defineInRange("Structure Weight", 25, 0, 50);

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

    public static FFConfiguration getInstance()
    {
        return INSTANCE;
    }

}