package com.tmtravlr.cp.block;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.tmtravlr.cp.init.ColourfulItems;
import com.tmtravlr.cp.te.PortalFrameTE;
import com.tmtravlr.cp.te.PortalFrameTESR;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PortalFrame extends Block implements ITileEntityProvider {

	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.5D, 0.625D);
	public IBlockState state = this.getDefaultState();
	
	public PortalFrame(){
	super(Material.ROCK);
	setCreativeTab(ColourfulItems.cpTab);
	setUnlocalizedName("portal_frame");
	setRegistryName("portal_frame");
	GameRegistry.register(this);
	GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	ClientRegistry.registerTileEntity(PortalFrameTE.class, "portal_frame_tile_entity", new PortalFrameTESR());
	}
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        ClientRegistry.bindTileEntitySpecialRenderer(PortalFrameTE.class, new PortalFrameTESR());
    }
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> list, boolean par4)
    {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            list.add(TextFormatting.WHITE + "right Click a block to put it in the frame");
            list.add(TextFormatting.WHITE + "used for portal construction");
        } else {
            list.add(TextFormatting.WHITE + "Hold Shift for info");
        }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
		entityIn.stepHeight=10;
    }
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
	}
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new PortalFrameTE();
	}
	
    private PortalFrameTE getTE(World world, BlockPos pos) {
        return (PortalFrameTE) world.getTileEntity(pos);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
                    EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
        	PortalFrameTE te = getTE(world, pos);
            if (te.getStack() == null) {
                if (player.getHeldItem(hand) != null) {
                    te.setStack(player.getHeldItem(hand));
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                    player.openContainer.detectAndSendChanges();
                }
            } else {
                ItemStack stack = te.getStack();
                te.setStack(null);
                if (!player.inventory.addItemStackToInventory(stack)) {
                    // Not possible. Throw item in the world
                    EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
                    world.spawnEntityInWorld(entityItem);
                } else {
                    player.openContainer.detectAndSendChanges();
                }
            }
        }
        return true;
        //thx McJty!
    }
    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
    	if(getTE(worldIn, pos).getStack() !=null){
    		EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), getTE(worldIn, pos).getStack());
    		worldIn.spawnEntityInWorld(item);
    	}
    }
	
}
