package com.tmtravlr.cp.block;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import com.tmtravlr.cp.CPLib;
import com.tmtravlr.cp.CPPos;
import com.tmtravlr.cp.ColourfulTeleporter;
import com.tmtravlr.cp.te.ClayPortalTE;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BasePortal extends Block {
	
	public static AxisAlignedBB APRILAABB = new AxisAlignedBB(-5D,-5D,-5D,5D,5D,5D);
	public int timer = 10;
	public CPPos destpos;
	
	public synchronized int increaseTimer(int timer){
		return timer++;
	}
	public synchronized int decreaseTimer(int timer){
		return timer--;
	}
	boolean aprilfools;
	public List<CPPos> posList = new ArrayList();

	public BasePortal(){
		super(Material.CLAY);
		/*setCreativeTab(ColourfulItems.cpTab);
		setUnlocalizedName("colourful_portal");
		setRegistryName("colourful_portal");
		setBlockUnbreakable();
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));*/
		Calendar calendar = Calendar.getInstance();

        if (calendar.get(2)==4 && calendar.get(5)==1)
        {
            this.aprilfools = true;
        }
        else{
        	aprilfools=false;
        }
	}
	//PortalsMaxLib PortalsMaxLib;
	@Override 
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		/*if(PortalsMaxLib.claymap.size()<=512){
			if(PortalsMaxLib.claymap.size()==0){
				PortalsMaxLib.claymap.put(0, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
				mapnr = 10;
			}
			else{
				PortalsMaxLib.claymap.put(PortalsMaxLib.claymap.size()+1, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
				mapnr=PortalsMaxLib.claymap.size()+1;
			}
		}*/
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
		if(aprilfools){
			return APRILAABB;
		}
		else{
        return NULL_AABB;
		}
    }
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if ((entityIn instanceof EntityLivingBase)) {
			EntityLivingBase livingEntity = (EntityLivingBase) entityIn;

			livingEntity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 80, 0, true, false));
			livingEntity.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 80, 99, true, false));
		}
		teleportColourfully(worldIn, pos, entityIn);
		TileEntity te = worldIn.getTileEntity(pos);
		if(state.getBlock() instanceof ClayPortal){
			if(te instanceof ClayPortalTE){
				ClayPortalTE cte = new ClayPortalTE();
				cte.visited=true;
				cte.portalNumberToShift=cte.portalNumberToShift+1;
			}
		}
	}

	/*public Entity teleportColourfully(World world, BlockPos pos, Entity entity) {
		if(PortalsMaxLib.claymap.containsValue(new CPPos(pos.getX(), pos.getY(), pos.getZ(), world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension))){
			CPPos destination = CPLib.getColourfulDestination(world, pos);

			double x = destination.pos.getX() + 0.5D;
			double y = destination.pos.getY() + 0.1D ;
			double z = destination.pos.getZ() + 0.5D;
			
			Entity ridingEntity = entity.getRidingEntity();
			if (ridingEntity != null) {
			entity.dismountRidingEntity();
			ridingEntity = teleportColourfully(world, startPos, ridingEntity);
			}
			if (entity instanceof EntityPlayerMP) {
			tpPlayerColourfully(world, x, y, z, (EntityPlayerMP)entity, destination);
			} else {
			entity = tpEntityColourfully(world, x, y, z, entity, destination);
			}
			return entity;
			}
		}*/
	
	public void teleportColourfully(World world, BlockPos pos, Entity entity) {
		/*if(PortalsMaxLib.claymap.containsValue(new CPPos(pos.getX(), pos.getY(), pos.getZ(), world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension))==false){
			//int diskey = (int) getKeyFromValue(PortalsMaxLib.claymap, new CPPos(pos.getX(), pos.getY(), pos.getZ(), entity.dimension));
			int key = mapnr;
			CPPos thiscppos =PortalsMaxLib.claymap.get(key+1);
			int dim = thiscppos.dimension;
			double x = thiscppos.pos.getX();
			double y = thiscppos.pos.getY();
			double z = thiscppos.pos.getZ();
			
			Entity ridingEntity = entity.getRidingEntity();
			if (ridingEntity != null) {
			entity.dismountRidingEntity();
			ridingEntity = teleportColourfully(world, thiscppos.pos, ridingEntity);
			}
			if (entity instanceof EntityPlayerMP) {
			tpPlayerColourfully(world, x, y, z, (EntityPlayerMP)entity, thiscppos);
			} else {
			entity = tpEntityColourfully(world, x, y, z, entity, thiscppos);
			}
			return entity;
		}*/
	}

	public void tpPlayerColourfully(World world, double x, double y, double z, EntityPlayerMP player, CPPos destination){
		int dimension = destination.dimension;
		int currentDimension = player.worldObj.provider.getDimension();
		if (currentDimension != dimension) {
			if (!world.isRemote) {
				if (currentDimension != 1) {
					player.mcServer.getPlayerList().transferPlayerToDimension(player, dimension, new ColourfulTeleporter(player.mcServer.worldServerForDimension(dimension), x, y, z));
				} else {
					forceTeleportPlayerFromEnd(player, dimension, new ColourfulTeleporter(player.mcServer.worldServerForDimension(dimension), x, y, z));
				}
				player.connection.sendPacket(new SPacketSetExperience(player.experience, player.experienceTotal, player.experienceLevel));
			}
		} else {
			player.connection.setPlayerLocation(x, y, z, player.rotationYaw, player.rotationPitch);
			world.updateEntityWithOptionalForce(player, false);
		}
	}
	public Entity tpEntityColourfully(World world, double x, double y, double z, Entity entity, CPPos destination){
		int dimension = destination.dimension;
		int currentDimension = entity.worldObj.provider.getDimension();
		if (dimension != currentDimension) {
			entity.changeDimension(dimension);
		}
		entity.setLocationAndAngles(x, y, z, entity.rotationYaw, entity.rotationPitch);
		return entity;
	}
	private static void forceTeleportPlayerFromEnd(EntityPlayerMP player, int newDimension, Teleporter colourfulTeleporter) {
		int j = player.dimension;
		WorldServer worldServerOld = player.mcServer.worldServerForDimension(player.dimension);
		player.dimension = newDimension;
		WorldServer worldServerNew = player.mcServer.worldServerForDimension(player.dimension);
		player.connection.sendPacket(new SPacketRespawn(player.dimension, player.worldObj.getDifficulty(), player.worldObj.getWorldInfo().getTerrainType(), Minecraft.getMinecraft().playerController.getCurrentGameType()));
		worldServerOld.removeEntityDangerously(player);
		player.isDead = false;

		WorldProvider pOld = worldServerOld.provider;
		WorldProvider pNew = worldServerNew.provider;
		double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
		double d0 = player.posX * moveFactor;
		double d1 = player.posZ * moveFactor;
		double d3 = player.posX;
		double d4 = player.posY;
		double d5 = player.posZ;
		float f = player.rotationYaw;

		worldServerOld.theProfiler.startSection("placing");
		d0 = MathHelper.clamp_int((int) d0, -29999872, 29999872);
		d1 = MathHelper.clamp_int((int) d1, -29999872, 29999872);
		if (player.isEntityAlive()) {
			player.setLocationAndAngles(d0, player.posY, d1, player.rotationYaw, player.rotationPitch);
			colourfulTeleporter.placeInPortal(player, f);
			worldServerNew.spawnEntityInWorld(player);
			worldServerNew.updateEntityWithOptionalForce(player, false);
		}
		worldServerOld.theProfiler.endSection();

		player.setWorld(worldServerNew);

		player.mcServer.getPlayerList().preparePlayer(player, worldServerOld);
		player.connection.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
		player.setWorld(worldServerNew);
		player.mcServer.getPlayerList().updateTimeAndWeatherForPlayer(player, worldServerNew);
		player.mcServer.getPlayerList().syncPlayerInventory(player);
		Iterator iterator = player.getActivePotionEffects().iterator();
		while (iterator.hasNext()) {
			PotionEffect potioneffect = (PotionEffect) iterator.next();
			player.connection.sendPacket(new SPacketEntityEffect(player.getEntityId(), potioneffect));
		}
		FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, j, newDimension);
	}
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		int x= pos.getX();
		int y= pos.getY();
		int z= pos.getZ();
		CPLib.deletePortal(worldIn, new BlockPos(x, y, z));
		super.onBlockHarvested(worldIn, pos, state, player);
	}
}
