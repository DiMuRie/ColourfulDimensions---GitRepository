package com.tmtravlr.cp.te;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class ColourfulReceiverTE extends TileEntity {

	public BlockPos getPosition() {
		return position;
	}

	protected BlockPos position = new BlockPos(0, 1, 0);
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("receiverposX", this.getPosition().getX());
        compound.setInteger("receiverposY", this.getPosition().getY());
        compound.setInteger("receiverposZ", this.getPosition().getZ());
        return compound;
    }
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        int i = MathHelper.clamp_int(compound.getInteger("receiverposX"), -32, 32);
        int j = MathHelper.clamp_int(compound.getInteger("receiverposY"), -32, 32);
        int k = MathHelper.clamp_int(compound.getInteger("receiverposZ"), -32, 32);
        //this.position = new BlockPos(i, j, k);
        this.setPosition(new BlockPos(i, j, k));
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
    
    public void setPosition(BlockPos posIn)
    {
        this.position = posIn;
    }
	
}
