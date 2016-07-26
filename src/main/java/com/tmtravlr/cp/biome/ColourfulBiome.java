package com.tmtravlr.cp.biome;

import javafx.scene.paint.Color;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ColourfulBiome extends Biome {

	private WorldGenerator UnDeadworldGeneratorBigTree;
	public final Material blockMaterial;
	static BiomeProperties bprop;
	public ColourfulBiome(/*BiomeProperties par1*/)
	{
		super(bprop.setWaterColor(0x66C1FF));
		this.blockMaterial = Material.WATER;
		//this.minHeight = 0.1F;
		//this.maxHeight = 0.6F;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityWolf.class, 8, 4, 4));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
		this.topBlock = Blocks.WOOL.getDefaultState();
		this.fillerBlock = Blocks.WOOL.getDefaultState();
		//this.setBiomeName("Tutorial 1");
	}
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0xA3FFAA;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0xA3FFAA;
    }
	
}
