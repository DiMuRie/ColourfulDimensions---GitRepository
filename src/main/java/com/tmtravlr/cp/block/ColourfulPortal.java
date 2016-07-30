package com.tmtravlr.cp.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.tmtravlr.cp.CPLib;
import com.tmtravlr.cp.CPPos;
import com.tmtravlr.cp.ColourfulTeleporter;
import com.tmtravlr.cp.init.ColorfulSounds;
import com.tmtravlr.cp.init.ColourfulBlocks;
import com.tmtravlr.cp.init.ColourfulItems;
import com.tmtravlr.cp.te.ColourfulPortalTE;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ColourfulPortal extends Block implements ITileEntityProvider {
	
	public static AxisAlignedBB AABB = new AxisAlignedBB(0,0,0,0,0,0);
	private static LinkedList<Entity> entitiesTeleported = new LinkedList<Entity>();
	private static HashMap<Entity, Entity> toStack = new HashMap<Entity, Entity>();
	private static int stackDelay = 0;
	IBlockAccess iba;
	BlockPos specificblahpoz;
	private BlockPos destPos;
	private static int destDim;
	
	
	public int getDestDim() {
		return destDim;
	}

	public void setDestDim(int destDim) {
		this.destDim = destDim;
	}

	public BlockPos getDestPos() {
		return destPos;
	}

	public void setDestPos(BlockPos destPos) {
		this.destPos = destPos;
	}

	public ColourfulPortal(){
		super(Material.CLAY);
		setCreativeTab(ColourfulItems.cpTab);
		setUnlocalizedName("colourful_portal");
		setRegistryName("colourful_portal");
		setBlockUnbreakable();
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new ColourfulPortalTE();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(this);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if ((entityIn instanceof EntityLivingBase)) {
			EntityLivingBase livingEntity = (EntityLivingBase) entityIn;

			livingEntity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 80, 0, true, false));
		}

		if (!worldIn.isRemote) {

			//Find the bottom entity of the stack

			while (entityIn.getRidingEntity() != null) {
				entityIn = entityIn.getRidingEntity();
				System.out.println("setten entity to the riding one.thiz iz gud");
			}

			//Go through to the top entity of the stack

			boolean doTeleport = true;
			Entity nextEntity = entityIn;

			//Go through the stack and make sure all entities can teleport.

			do {
				entityIn = nextEntity;

				if (!entitySatisfiesTeleportConditions(worldIn, pos, entityIn)) {
					System.out.println("entity does not satisfy the portal");

					entityIn.getEntityData().setInteger("ColourfulPortalDelay", 10);
					if (!(entityIn instanceof EntityPlayer)) {
						entityIn.getEntityData().setBoolean("InColourfulPortal", true);
					}

					doTeleport = false;
					System.out.println("did not teleport");
				}

				if (!entitiesTeleported.contains(entityIn)) {
					entitiesTeleported.add(entityIn);
					System.out.println("added entity to  a list of teleported entities");
				}

				nextEntity = entityIn.getRidingEntity();
			}
			while (nextEntity != null);
			System.out.println("entity aint null");

			if (doTeleport && entitySatisfiesTeleportConditions(worldIn, pos, entityIn)) {

				teleportColourfully(worldIn, pos, entityIn);
				System.out.println("teleported the entity");

			} else {
				entityIn.getEntityData().setInteger("ColourfulPortalDelay", 10);
				if (!(entityIn instanceof EntityPlayer)) {
					entityIn.getEntityData().setBoolean("InColourfulPortal", true);
				}
			}
			if (!entitiesTeleported.contains(entityIn)) {
				entitiesTeleported.add(entityIn);
				System.out.println("list.dot.....");
			}


		}
	}

	private boolean entitySatisfiesTeleportConditions(World world, BlockPos pos, Entity entity) {
		if (world.isRemote) {
			System.out.println("portal not satisfied");
			return false;
		}
		if (((entity instanceof EntityPlayer)) && (entity.getEntityData().getInteger("ColourfulPortalPlayerDelay") >= 10) && (entity.isSneaking())) {
			System.out.println("portal satisfied");
			return true;
		}
		return true;//!entity.getEntityData().getBoolean("InColourfulPortal");
	}

	private Entity teleportColourfully(World world, BlockPos startPos, Entity entity) {
		int dimension = destDim;
		//Make sure the dimension we are trying to teleport to exists first!
		if (world.getMinecraftServer().worldServerForDimension(dimension) == null) {
			System.out.println("dimension exists!");
			return entity;
		}
		WorldServer newWorldServer = world.getMinecraftServer().worldServerForDimension(dimension);

		Entity ridingEntity = entity.getRidingEntity();
		if (ridingEntity != null) {
			entity.dismountRidingEntity();
			/*ridingEntity = */teleportColourfully(world, startPos, ridingEntity);
		}

		entity.getEntityData().setInteger("ColourfulPortalDelay", 10);
		entity.getEntityData().setBoolean("InColourfulPortal", true);

		EntityPlayerMP player = null;
		if ((entity instanceof EntityPlayer)) {
			System.out.println("entity is a player.yayy");
			Iterator<EntityPlayerMP> iterator = (world.getMinecraftServer().getPlayerList()).getPlayerList().iterator();//.playerEntityList.iterator();
			EntityPlayerMP entityplayermp = null;
			do {
				if (!iterator.hasNext()) {
					break;
				}
				entityplayermp = iterator.next();
				System.out.println("iterated through players");
			} while (!entityplayermp.getName().equalsIgnoreCase(entity.getName()));
			player = entityplayermp;
		}
		if (player != null) {
			player.getEntityData().setInteger("ColourfulPortalPlayerDelay", 0);

			teleportPlayerColourfully(newWorldServer, destPos.getX(), destPos.getY(), destPos.getZ(), player);
			System.out.println("teleported");
		} else {
			/*entity = */teleportEntityColourfully(newWorldServer, destPos.getX(), destPos.getY(), destPos.getZ(), entity);
		}


		return entity;
	}

	private static Entity teleportEntityColourfully(World world, double x, double y, double z, Entity entity) {
		int dimension = destDim;
		int currentDimension = entity.worldObj.provider.getDimension();
		if (dimension != currentDimension) {
			entitiesTeleported.remove(entity);
			return transferEntityToDimension(entity, dimension, x, y, z);
		}

		entity.setLocationAndAngles(x, y, z, entity.rotationYaw, 0.0F);

		return entity;
	}

	private static void teleportPlayerColourfully(World world, double x, double y, double z, EntityPlayerMP player) {
		int dimension = destDim;
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

	private static void forceTeleportPlayerFromEnd(EntityPlayerMP player, int newDimension, ColourfulTeleporter colourfulTeleporter) {
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

	public static Entity transferEntityToDimension(Entity toTeleport, int newDimension, double x, double y, double z) {
		if (!toTeleport.isDead) {
			toTeleport.worldObj.theProfiler.startSection("changeDimension");
			MinecraftServer minecraftserver = toTeleport.getEntityWorld().getMinecraftServer();
			int oldDimension = toTeleport.dimension;
			WorldServer worldServerOld = minecraftserver.worldServerForDimension(oldDimension);
			WorldServer worldServerNew = minecraftserver.worldServerForDimension(newDimension);
			toTeleport.dimension = newDimension;

			toTeleport.worldObj.removeEntity(toTeleport);
			toTeleport.isDead = false;
			toTeleport.worldObj.theProfiler.startSection("reposition");
			if (oldDimension == 1) {
				forceTeleportEntityFromEnd(toTeleport, newDimension, new ColourfulTeleporter(worldServerOld, x, y, z), worldServerNew);
			} else {
				minecraftserver.getPlayerList().transferEntityToWorld(toTeleport, oldDimension, worldServerOld, worldServerNew, new ColourfulTeleporter(worldServerOld, x, y, z));
			}
			toTeleport.worldObj.theProfiler.endStartSection("reloading");
			Entity entity = EntityList.createEntityByName(EntityList.getEntityString(toTeleport), worldServerNew);
			if (entity != null) {
				//entity.copyDataFromOld(toTeleport);
				entity.getEntityData().copy();
				worldServerNew.spawnEntityInWorld(entity);
			}
			toTeleport.isDead = true;
			toTeleport.worldObj.theProfiler.endSection();
			worldServerOld.resetUpdateEntityTick();
			worldServerNew.resetUpdateEntityTick();
			toTeleport.worldObj.theProfiler.endSection();

			return entity;
		}
		return toTeleport;
	}

	private static void forceTeleportEntityFromEnd(Entity entity, int newDimension, Teleporter colourfulTeleporter, WorldServer worldServerNew) {
		worldServerNew.spawnEntityInWorld(entity);
		entity.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
		worldServerNew.updateEntityWithOptionalForce(entity, false);
		colourfulTeleporter.placeInPortal(entity, 0.0F);

		entity.setWorld(worldServerNew);
	}
	
	/*@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
		CPPos tpos;
		ColourfulReceiverTE rec = new ColourfulReceiverTE();
		ColourfulPortalTE por = new ColourfulPortalTE();
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
		if ((entityIn instanceof EntityLivingBase)) {
			EntityLivingBase livingEntity = (EntityLivingBase) entityIn;

			livingEntity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 80, 0, true, false));
		}
		else if ((entityIn.getRidingEntity() == null) && ((entityIn instanceof EntityPlayerMP)))
		{
			if (entitySatisfiesTeleportConditions(worldIn, pos, entityIn)) {

				teleportColourfully(worldIn, pos, entityIn);
			} 
			else {
				por.setTimer(10);
				por.setDimension(entityIn.dimension);
			}
		}
    }
	private boolean entitySatisfiesTeleportConditions(World world, BlockPos pos, Entity entity) {
		ColourfulPortalTE por = new ColourfulPortalTE();
		if (world.isRemote) {
			return false;
		}
		if (((entity instanceof EntityPlayer)) && por.getTimer() >= 10 && (entity.isSneaking())) {
			return true;
		}
		return true;
	}
	private Entity teleportColourfully(World world, BlockPos startPos, Entity entity) {
		CPPos destination = new CPPos(startPos, entity.dimension);
		ColourfulPortalTE col = new ColourfulPortalTE();;
		ColourfulReceiverTE rec = new ColourfulReceiverTE();
		//Make sure the dimension we are trying to teleport to exists first!
		if (world.getMinecraftServer().worldServerForDimension(rec.getDimension()) == null) {
			System.out.println("world exists sir!");
			return entity;
		}
		double x = destination.pos.getX() + 0.5D;
		double y = destination.pos.getY() + 0.1D;
		double z = destination.pos.getZ() + 0.5D;
		WorldServer newWorldServer = world.getMinecraftServer().worldServerForDimension(rec.getDimension());

		Entity ridingEntity = entity.getRidingEntity();
		if (ridingEntity != null) {
			entity.dismountRidingEntity();
			ridingEntity = teleportColourfully(world, startPos, ridingEntity);
		}

		col.setTimer(10);
		
		EntityPlayerMP player = null;
		if ((entity instanceof EntityPlayer)) {
			Iterator<EntityPlayerMP> iterator = (world.getMinecraftServer().getPlayerList()).getPlayerList().iterator();//.playerEntityList.iterator();
			EntityPlayerMP entityplayermp = null;
			do {
				if (!iterator.hasNext()) {
					break;
				}
				entityplayermp = iterator.next();
			} while (!entityplayermp.getName().equalsIgnoreCase(entity.getName()));
			player = entityplayermp;
		}
		if (player != null) {
			col.setTimer(0);

			teleportPlayerColourfully(newWorldServer, x, y, z, player, destination);
		} else {
			entity = teleportEntityColourfully(newWorldServer, x, y, z, entity, destination);
		}
		
		return entity;
	}
	private static Entity teleportEntityColourfully(World world, double x, double y, double z, Entity entity, CPPos destination) {
		ColourfulPortalTE col = new ColourfulPortalTE();
		ColourfulReceiverTE rec = new ColourfulReceiverTE();
		int dimension = destination.dimension;
		int currentDimension = entity.worldObj.provider.getDimension();
		if (dimension != currentDimension) {
			return transferEntityToDimension(entity, dimension, x, y, z);
		}
		entity.setLocationAndAngles(x, y, z, entity.rotationYaw, 0.0F);

		return entity;
	}

	private static void teleportPlayerColourfully(World world, double x, double y, double z, EntityPlayerMP player, CPPos destination) {
		ColourfulPortalTE col = new ColourfulPortalTE();
		ColourfulReceiverTE rec = new ColourfulReceiverTE();
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
	public static Entity transferEntityToDimension(Entity toTeleport, int newDimension, double x, double y, double z) {
		if (!toTeleport.isDead) {
			toTeleport.worldObj.theProfiler.startSection("changeDimension");
			MinecraftServer minecraftserver = toTeleport.getEntityWorld().getMinecraftServer();
			int oldDimension = toTeleport.dimension;
			WorldServer worldServerOld = minecraftserver.worldServerForDimension(oldDimension);
			WorldServer worldServerNew = minecraftserver.worldServerForDimension(newDimension);
			toTeleport.dimension = newDimension;

			toTeleport.worldObj.removeEntity(toTeleport);
			toTeleport.isDead = false;
			toTeleport.worldObj.theProfiler.startSection("reposition");
			if (oldDimension == 1) {
				forceTeleportEntityFromEnd(toTeleport, newDimension, new ColourfulTeleporter(worldServerOld, x, y, z), worldServerNew);
			} else {
				minecraftserver.getPlayerList().transferEntityToWorld(toTeleport, oldDimension, worldServerOld, worldServerNew, new ColourfulTeleporter(worldServerOld, x, y, z));
			}
			toTeleport.worldObj.theProfiler.endStartSection("reloading");
			Entity entity = EntityList.createEntityByName(EntityList.getEntityString(toTeleport), worldServerNew);
			if (entity != null) {
				entity.getEntityData().copy();
				worldServerNew.spawnEntityInWorld(entity);
			}
			toTeleport.isDead = true;
			toTeleport.worldObj.theProfiler.endSection();
			worldServerOld.resetUpdateEntityTick();
			worldServerNew.resetUpdateEntityTick();
			toTeleport.worldObj.theProfiler.endSection();

			return entity;
		}
		return toTeleport;
	}
	private static void forceTeleportEntityFromEnd(Entity entity, int newDimension, Teleporter colourfulTeleporter, WorldServer worldServerNew) {
		worldServerNew.spawnEntityInWorld(entity);
		entity.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
		worldServerNew.updateEntityWithOptionalForce(entity, false);
		colourfulTeleporter.placeInPortal(entity, 0.0F);

		entity.setWorld(worldServerNew);
	}*/
	
}
