package com.tmtravlr.cp;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

public class CPPos {
	
	public BlockPos pos;
	public int dimension;

	public CPPos(int xPos, int yPos, int zPos, int dimension) {
		this(new BlockPos(xPos, yPos, zPos), dimension);
	}

	public CPPos(BlockPos pos, int dimension) {
		this.pos = pos;
		this.dimension = dimension;
	}
	
}
