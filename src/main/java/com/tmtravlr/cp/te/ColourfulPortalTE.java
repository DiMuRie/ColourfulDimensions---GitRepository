package com.tmtravlr.cp.te;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class ColourfulPortalTE extends TileEntity implements ITickable {

	private BlockPos position = new BlockPos(0, 1, 0);
	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}
	EntityPlayer player;

	private int timer;
	private int dimension;
	
	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("posX", this.position.getX());
        compound.setInteger("posY", this.position.getY());
        compound.setInteger("posZ", this.position.getZ());
        compound.setInteger("colourful_timer", getTimer());
        compound.setInteger("dimension", this.getDimension());
        return compound;
    }
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        int i = MathHelper.clamp_int(compound.getInteger("posX"), -32, 32);
        int j = MathHelper.clamp_int(compound.getInteger("posY"), -32, 32);
        int k = MathHelper.clamp_int(compound.getInteger("posZ"), -32, 32);
        this.position = new BlockPos(i, j, k);
        this.setTimer(compound.getInteger("colourful_timer")); //= compound.getInteger("colourful_timer");
        this.setDimension(compound.getInteger("dimension"));
    }
	
	@Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 7, this.getUpdateTag());
    }

    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }
    
    @Override
	public void update(){
		List<Entity> interestingItems = worldObj.getEntitiesWithinAABB(Entity.class, Block.FULL_BLOCK_AABB.expand(.5, .5, .5));
		for (Entity entity : interestingItems) {
			double dx = (pos.getX() + 0.5D - entity.posX);
			double dy = (pos.getY() + 0.5D - entity.posY);
			double dz = (pos.getZ() + 0.5D - entity.posZ);

			double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
			if (distance < 1.1) {
			} else {
				double var11 = 1.0 - distance / 15.0;

				if (var11 > 0.0D) {
					var11 *= var11;
					entity.motionX += dx / distance * var11 * 0.05;
					entity.motionY += dy / distance * var11 * 0.2;
					entity.motionZ += dz / distance * var11 * 0.05;
					entity.changeDimension(17);
				}
			}
		}
    }
	
}
