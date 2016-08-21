package com.tmtravlr.cp;

import net.minecraft.util.math.BlockPos;

public class CPPos extends Object {
	
	public BlockPos pos;
	public int dimension;

	public CPPos(int xPos, int yPos, int zPos, int dimension) {
		this(new BlockPos(xPos, yPos, zPos), dimension);
	}

	public CPPos(BlockPos pos, int dimension) {
		this.pos = pos;
		this.dimension = dimension;
	}

	@Override
	public boolean equals(Object o) {
		if ((o == null) || (!(o instanceof CPPos))) {
			return false;
		}

		final CPPos other = (CPPos) o;
		return (this.pos.equals(other.pos)) && (this.dimension == other.dimension);
	}

	@Override
	public String toString() {
		return "CPL[meta=" + ", pos=" + this.pos.toString() + ", dim=" + this.dimension + "]";
	}
	
}
