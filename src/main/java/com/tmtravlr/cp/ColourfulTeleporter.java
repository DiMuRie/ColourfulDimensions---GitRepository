package com.tmtravlr.cp;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class ColourfulTeleporter extends Teleporter {

    private final WorldServer worldServer;
    private double x;
    private double y;
    private double z;


    public ColourfulTeleporter(WorldServer world, double x, double y, double z) {
        super(world);
        this.worldServer = world;
        this.x = x;
        this.y = y;
        this.z = z;

    }

    @Override
    public void placeInPortal(Entity pEntity, float rotationYaw) {
        this.worldServer.getBlockState(new BlockPos((int) this.x, (int) this.y, (int) this.z));   //dummy load to maybe gen chunk

        pEntity.setPosition(this.x, this.y, this.z);
        pEntity.motionX = 0.0f;
        pEntity.motionY = 0.0f;
        pEntity.motionZ = 0.0f;
    }
	
	  /*private WorldServer worldServer;
	  private double x;
	  private double y;
	  private double z;
	  
	  public ColourfulTeleporter(WorldServer worldServerOld, double xToSet, double yToSet, double zToSet)
	  {
	    super(worldServerOld);
	    this.worldServer = worldServerOld;
	    this.x = xToSet;
	    this.y = yToSet;
	    this.z = zToSet;
	  }
	  
	  public void placeInPortal(Entity entity, float par8)
	  {
	    entity.setLocationAndAngles(this.x, this.y, this.z, entity.rotationYaw, 0.0F);
	    entity.motionX = (entity.motionY = entity.motionZ = 0.0D);
	    entity.setSneaking(false);
	  }*/
}
