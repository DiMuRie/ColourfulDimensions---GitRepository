package com.tmtravlr.cp.block;

import java.util.Iterator;

import javax.annotation.Nullable;

import com.tmtravlr.cp.ColourfulTeleporter;
import com.tmtravlr.cp.init.ColourfulItems;
import com.tmtravlr.cp.te.ColourfulPortalTE;
import com.tmtravlr.cp.te.ColourfulReceiverTE;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.network.play.server.SPacketSetExperience;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ColourfulPortal extends Block implements ITileEntityProvider {
	
	public static AxisAlignedBB AABB = new AxisAlignedBB(0,0,0,0,0,0);

	public ColourfulPortal(){
		super(Material.CLAY);
		setCreativeTab(ColourfulItems.cpTab);
		setUnlocalizedName("colourful_portal");
		setRegistryName("colourful_portal");
		setBlockUnbreakable();
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
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
	
	//ColourfulReceiverTE rec;
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
		ColourfulReceiverTE rec = new ColourfulReceiverTE();
		ColourfulPortalTE por = new ColourfulPortalTE();
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
        //entityIn.changeDimension(17);
        //entityIn.setPositionAndRotation(0, 20, 0, 0, 0);
        //entityIn.setPosition(rec.getPosition().getX(), rec.getPosition().getY(), rec.getPosition().getZ());
        //entityIn.setPositionAndRotation(rec.getPosition().getX(), rec.getPosition().getY(), rec.getPosition().getZ(), 0, 0);
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
		ColourfulPortalTE col = new ColourfulPortalTE();;
		//Make sure the dimension we are trying to teleport to exists first!
		if (world.getMinecraftServer().worldServerForDimension(col.getDimension()) == null) {
			return entity;
		}
		double x = col.getPos().getX() + 0.5D;
		double y = col.getPos().getY() + 0.1D;
		double z = col.getPos().getZ() + 0.5D;
		WorldServer newWorldServer = world.getMinecraftServer().worldServerForDimension(col.getDimension());

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

			teleportPlayerColourfully(newWorldServer, x, y, z, player);
		} else {
			entity = teleportEntityColourfully(newWorldServer, x, y, z, entity);
		}
		
		return entity;
	}
	private static Entity teleportEntityColourfully(World world, double x, double y, double z, Entity entity) {
		ColourfulPortalTE col = new ColourfulPortalTE();
		int dimension = col.getDimension();
		int currentDimension = entity.worldObj.provider.getDimension();
		if (dimension != currentDimension) {
			return transferEntityToDimension(entity, dimension, x, y, z);
		}
		entity.setLocationAndAngles(x, y, z, entity.rotationYaw, 0.0F);

		return entity;
	}

	private static void teleportPlayerColourfully(World world, double x, double y, double z, EntityPlayerMP player) {
		ColourfulPortalTE col = new ColourfulPortalTE();
		int dimension = col.getDimension();
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
	}
	
}
