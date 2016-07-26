package com.tmtravlr.cp.dimension;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ColourfulChunkProvider implements IChunkProvider {

	private Random rand;
	private World worldObj;
	
	@Override
	public Chunk getLoadedChunk(int x, int z) {
		Chunk chunk = new Chunk(this.worldObj, x, z);
		return chunk;
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		Chunk chunk = new Chunk(this.worldObj, x, z);
		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public String makeString() {
		return "CCPString";
	}

	

}
