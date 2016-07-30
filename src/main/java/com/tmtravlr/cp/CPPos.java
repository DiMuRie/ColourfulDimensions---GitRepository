package com.tmtravlr.cp;

import net.minecraft.util.math.BlockPos;

public class CPPos {
	
	public BlockPos pos;
	public int dimension;
	public int portalMetadata;

	public CPPos(int xPos, int yPos, int zPos, int dimension, int portalMetadata) {
		this(new BlockPos(xPos, yPos, zPos), dimension, portalMetadata);
	}

	public CPPos(BlockPos pos, int dimension, int portalMetadata) {
		this.pos = pos;
		this.dimension = dimension;
		this.portalMetadata = portalMetadata;
	}

	@Override
	public boolean equals(Object o) {
		if ((o == null) || (!(o instanceof CPPos))) {
			return false;
		}

		final CPPos other = (CPPos) o;
		return (this.pos.equals(other.pos)) && (this.dimension == other.dimension) && (this.portalMetadata == other.portalMetadata);
	}

	@Override
	public String toString() {
		return "CPL[meta=" + this.portalMetadata + ", pos=" + this.pos.toString() + ", dim=" + this.dimension + "]";
	}
	
}
