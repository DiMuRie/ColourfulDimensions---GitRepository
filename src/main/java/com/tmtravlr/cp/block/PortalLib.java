package com.tmtravlr.cp.block;

import java.util.Random;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.command.CommandResultStats;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PortalLib {

	//temporarly just for the looks but keep this class plz.
	
	public int timeUntilPortal;
    protected boolean inPortal;
    protected int portalCounter;
    public World worldObj;
    public BlockPos lastPortalPos;
    protected Vec3d lastPortalVec;
    protected EnumFacing teleportDirection;
    /** Entity position X */
    public double posX;
    /** Entity position Y */
    public double posY;
    /** Entity position Z */
    public double posZ;
    
    public PortalLib(World worldIn)
    {
        this.worldObj = worldIn;

    }
	
    public int getPortalCooldown()
    {
        return 300;
    }
    
	public void setPortal(BlockPos pos)
    {
        if (this.timeUntilPortal > 0)
        {
            this.timeUntilPortal = this.getPortalCooldown();
        }
        else
        {
            if (!this.worldObj.isRemote && !pos.equals(this.lastPortalPos))
            {
                this.lastPortalPos = new BlockPos(pos);
                BlockPattern.PatternHelper blockpattern$patternhelper = Blocks.PORTAL.createPatternHelper(this.worldObj, this.lastPortalPos);
                double d0 = blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X ? (double)blockpattern$patternhelper.getFrontTopLeft().getZ() : (double)blockpattern$patternhelper.getFrontTopLeft().getX();
                double d1 = blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X ? this.posZ : this.posX;
                d1 = Math.abs(MathHelper.pct(d1 - (double)(blockpattern$patternhelper.getForwards().rotateY().getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE ? 1 : 0), d0, d0 - (double)blockpattern$patternhelper.getWidth()));
                double d2 = MathHelper.pct(this.posY - 1.0D, (double)blockpattern$patternhelper.getFrontTopLeft().getY(), (double)(blockpattern$patternhelper.getFrontTopLeft().getY() - blockpattern$patternhelper.getHeight()));
                this.lastPortalVec = new Vec3d(d1, d2, 0.0D);
                this.teleportDirection = blockpattern$patternhelper.getForwards();
            }

            this.inPortal = true;
        }
    }
	
}
