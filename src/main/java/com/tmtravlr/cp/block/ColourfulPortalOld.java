package com.tmtravlr.cp.block;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Nullable;

import com.tmtravlr.cp.CPLib;
import com.tmtravlr.cp.init.ColourfulItems;
import com.tmtravlr.cp.te.ColourfulPortalTE;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ColourfulPortalOld extends Block implements ITileEntityProvider {
	
	public static AxisAlignedBB APRILAABB = new AxisAlignedBB(-5D,-5D,-5D,5D,5D,5D);
	//public BlockPos destPos = new BlockPos(0,10,0);
	public int timer = 10;
	public synchronized int increaseTimer(int timer){
		return timer++;
	}
	public synchronized int decreaseTimer(int timer){
		return timer--;
	}
	boolean aprilfools;
	public List<BlockPos> posList = new ArrayList();

	public ColourfulPortalOld(){
		super(Material.CLAY);
		setCreativeTab(ColourfulItems.cpTab);
		setUnlocalizedName("colourful_portal");
		setRegistryName("colourful_portal");
		setBlockUnbreakable();
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		Calendar calendar = Calendar.getInstance();

        if (calendar.get(2)==4 && calendar.get(5)==1)
        {
            this.aprilfools = true;
        }
        else{
        	aprilfools=false;
        }
	}
	
	@Override
	public String toString(){
		return "I`ve got a golden ticket!-Best selling sng on itunin by famous singer erikcartmansX_x99";
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
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new ColourfulPortalTE();
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if ((entityIn instanceof EntityLivingBase)) {
			EntityLivingBase livingEntity = (EntityLivingBase) entityIn;

			livingEntity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 80, 0, true, false));
			livingEntity.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 80, 99, true, false));
		}
		teleportColourfully(worldIn, pos, entityIn);
	}

	public Entity teleportColourfully(World world, BlockPos startPos, Entity entity) {
	BlockPos destination = CPLib.getColourfulDestination(world, startPos);

		double x = destination.getX() + 0.5D;
		double y = destination.getY() + 0.1D ;
		double z = destination.getZ() + 0.5D;
		
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

	public void tpPlayerColourfully(World world, double x, double y, double z, EntityPlayerMP player, BlockPos destination){
		player.connection.setPlayerLocation(x, y, z, player.rotationYaw, player.rotationPitch);
		world.updateEntityWithOptionalForce(player, false);
	}
	public Entity tpEntityColourfully(World world, double x, double y, double z, Entity entity, BlockPos destination){
		entity.setLocationAndAngles(x, y, z, entity.rotationYaw, entity.rotationPitch);
		return entity;
	}
}
	

