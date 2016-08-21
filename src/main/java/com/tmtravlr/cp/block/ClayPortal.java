package com.tmtravlr.cp.block;

import java.util.List;

import com.tmtravlr.cp.CPPos;
import com.tmtravlr.cp.PortalsMaxLib;
import com.tmtravlr.cp.init.ColourfulItems;
import com.tmtravlr.cp.te.ClayPortalTE;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClayPortal extends BasePortal implements ITileEntityProvider{
	
	public ClayPortal(){
		setCreativeTab(ColourfulItems.cpTab);
		setUnlocalizedName("clay_colourful_portal");
		setRegistryName("clay_colourful_portal");
		setBlockUnbreakable();
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	public int maxportals = 255;
	
	int i =0;
	@Override 
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		ClayPortalTE cte = new ClayPortalTE();
		if(state==this.getStateFromMeta(0)){
		switch(maxportals){ 
		case 0:  PortalsMaxLib.claymap.add(0, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=0;
		case 1:  PortalsMaxLib.claymap.add(1, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=1;
        case 2:  PortalsMaxLib.claymap.add(2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=2;
        case 3:  PortalsMaxLib.claymap.add(3, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=3;
        case 4:  PortalsMaxLib.claymap.add(4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=4;
        case 5:  PortalsMaxLib.claymap.add(5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=5;
        case 6:  PortalsMaxLib.claymap.add(6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=6;
        case 7:  PortalsMaxLib.claymap.add(7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=7;
        case 8:  PortalsMaxLib.claymap.add(8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=8;
        case 9:  PortalsMaxLib.claymap.add(9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=9;
        case 10: PortalsMaxLib.claymap.add(10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=10;
        case 11: PortalsMaxLib.claymap.add(11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=11;
        case 12: PortalsMaxLib.claymap.add(12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=12;
        case 13:  PortalsMaxLib.claymap.add(13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=13;
		case 14:  PortalsMaxLib.claymap.add(14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=14;
        case 15:  PortalsMaxLib.claymap.add(15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=15;
        break;
        default: break;
		}
		}
		if(state==this.getStateFromMeta(1)){
		switch(maxportals){ 
        case 16:  PortalsMaxLib.claymap.add(16, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=16;
        case 17:  PortalsMaxLib.claymap.add(17, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=17;
        case 18:  PortalsMaxLib.claymap.add(18, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=18;
        case 19:  PortalsMaxLib.claymap.add(19, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=19;
        case 20:  PortalsMaxLib.claymap.add(20, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=20;
        case 21:  PortalsMaxLib.claymap.add(21, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=21;
        case 22:  PortalsMaxLib.claymap.add(22, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=22;
        case 23: PortalsMaxLib.claymap.add(23, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=23;
        case 24: PortalsMaxLib.claymap.add(24, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=24;
        case 25: PortalsMaxLib.claymap.add(25, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=25;
        case 26:  PortalsMaxLib.claymap.add(26, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=26;
        case 27:  PortalsMaxLib.claymap.add(27, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=27;
        case 28:  PortalsMaxLib.claymap.add(28, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=28;
        case 29:  PortalsMaxLib.claymap.add(29, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=29;
        case 30:  PortalsMaxLib.claymap.add(30, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=30;
        case 31:  PortalsMaxLib.claymap.add(31, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=31;
        break;
        default: break;
		}
		}
		if(state==this.getStateFromMeta(2)){
			switch(maxportals){ 
			case 0+16*2:  PortalsMaxLib.claymap.add(0+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=32;
			case 1+16*2:  PortalsMaxLib.claymap.add(1+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=33;
	        case 2+16*2:  PortalsMaxLib.claymap.add(2+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=34;
	        case 3+16*2:  PortalsMaxLib.claymap.add(3+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=35;
	        case 4+16*2:  PortalsMaxLib.claymap.add(4+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=36;
	        case 5+16*2:  PortalsMaxLib.claymap.add(5+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=37;
	        case 6+16*2:  PortalsMaxLib.claymap.add(6+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=38;
	        case 7+16*2:  PortalsMaxLib.claymap.add(7+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=39;
	        case 8+16*2:  PortalsMaxLib.claymap.add(8+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=40;
	        case 9+16*2:  PortalsMaxLib.claymap.add(9+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=41;
	        case 10+16*2: PortalsMaxLib.claymap.add(10+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=42;
	        case 11+16*2: PortalsMaxLib.claymap.add(11+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=43;
	        case 12+16*2: PortalsMaxLib.claymap.add(12+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=44;
	        case 13+16*2:  PortalsMaxLib.claymap.add(13+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=45;
			case 14+16*2:  PortalsMaxLib.claymap.add(14+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=46;
	        case 15+16*2:  PortalsMaxLib.claymap.add(15+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=47;
	        break;
	        default: break;
			}
			}
		if(state==this.getStateFromMeta(3)){
			switch(maxportals){ 
			case 0+16*3:  PortalsMaxLib.claymap.add(0+16*3, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=48;
			case 1+16*3:  PortalsMaxLib.claymap.add(1+16*3, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=49;
	        case 2+16*3:  PortalsMaxLib.claymap.add(2+16*3, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=50;
	        case 3+16*3:  PortalsMaxLib.claymap.add(3+16*3, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=51;
	        case 4+16*3:  PortalsMaxLib.claymap.add(4+16*3, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=52;
	        case 5+16*3:  PortalsMaxLib.claymap.add(5+16*3, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=53;
	        case 6+16*3:  PortalsMaxLib.claymap.add(6+16*3, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=54;
	        case 7+16*3:  PortalsMaxLib.claymap.add(7+16*3, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=55;
	        case 8+16*3:  PortalsMaxLib.claymap.add(8+16*3, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=56;
	        case 9+16*3:  PortalsMaxLib.claymap.add(9+16*3, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=57;
	        case 10+16*3: PortalsMaxLib.claymap.add(10+16*3, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=58;
	        case 11+16*3: PortalsMaxLib.claymap.add(11+16*3, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=59;
	        case 12+16*3: PortalsMaxLib.claymap.add(12+16*3, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=60;
	        case 13+16*3:  PortalsMaxLib.claymap.add(13+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=61;
			case 14+16*3:  PortalsMaxLib.claymap.add(14+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=62;
	        case 15+16*3:  PortalsMaxLib.claymap.add(15+16*2, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=63;
	        break;
	        default: break;
			}
			}
		if(state==this.getStateFromMeta(4)){
			switch(maxportals){ 
			case 0+16*4:  PortalsMaxLib.claymap.add(0+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=64;								
			case 1+16*4:  PortalsMaxLib.claymap.add(1+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=65;
	        case 2+16*4:  PortalsMaxLib.claymap.add(2+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=66;
	        case 3+16*4:  PortalsMaxLib.claymap.add(3+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=67;
	        case 4+16*4:  PortalsMaxLib.claymap.add(4+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=68;
	        case 5+16*4:  PortalsMaxLib.claymap.add(5+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=69;
	        case 6+16*4:  PortalsMaxLib.claymap.add(6+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=70;
	        case 7+16*4:  PortalsMaxLib.claymap.add(7+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=71;
	        case 8+16*4:  PortalsMaxLib.claymap.add(8+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=72;
	        case 9+16*4:  PortalsMaxLib.claymap.add(9+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=73;
	        case 10+16*4:  PortalsMaxLib.claymap.add(10+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=74;
	        case 11+16*4:  PortalsMaxLib.claymap.add(11+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=75;
	        case 12+16*4:  PortalsMaxLib.claymap.add(12+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=76;
	        case 13+16*4:  PortalsMaxLib.claymap.add(13+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=77;
			case 14+16*4:  PortalsMaxLib.claymap.add(14+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=78;
	        case 15+16*4:  PortalsMaxLib.claymap.add(15+16*4, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=79;
	        break;
	        default: break;
			}
			}
		if(state==this.getStateFromMeta(5)){
			switch(maxportals){ 
			case 0+16*5:  PortalsMaxLib.claymap.add(0+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=80;
			case 1+16*5:  PortalsMaxLib.claymap.add(1+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=81;
	        case 2+16*5:  PortalsMaxLib.claymap.add(2+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=82;
	        case 3+16*5:  PortalsMaxLib.claymap.add(3+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=83;
	        case 4+16*5:  PortalsMaxLib.claymap.add(4+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=84;
	        case 5+16*5:  PortalsMaxLib.claymap.add(5+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=85;
	        case 6+16*5:  PortalsMaxLib.claymap.add(6+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=86;
	        case 7+16*5:  PortalsMaxLib.claymap.add(7+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=87;
	        case 8+16*5:  PortalsMaxLib.claymap.add(8+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=88;
	        case 9+16*5:  PortalsMaxLib.claymap.add(9+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=89;
	        case 10+16*5:  PortalsMaxLib.claymap.add(10+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=90;
	        case 11+16*5:  PortalsMaxLib.claymap.add(11+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=91;
	        case 12+16*5:  PortalsMaxLib.claymap.add(12+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=92;
	        case 13+16*5:  PortalsMaxLib.claymap.add(13+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=93;
			case 14+16*5:  PortalsMaxLib.claymap.add(14+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=94;
	        case 15+16*5:  PortalsMaxLib.claymap.add(15+16*5, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=95;
	        break;
	        default: break;
			}
			}
		if(state==this.getStateFromMeta(6)){
			switch(maxportals){ 
			case 0+16*6:  PortalsMaxLib.claymap.add(0+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));cte.portalNumberToShift=96;
			case 1+16*6:  PortalsMaxLib.claymap.add(1+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 2+16*6:  PortalsMaxLib.claymap.add(2+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 3+16*6:  PortalsMaxLib.claymap.add(3+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 4+16*6:  PortalsMaxLib.claymap.add(4+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 5+16*6:  PortalsMaxLib.claymap.add(5+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 6+16*6:  PortalsMaxLib.claymap.add(6+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 7+16*6:  PortalsMaxLib.claymap.add(7+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 8+16*6:  PortalsMaxLib.claymap.add(8+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 9+16*6:  PortalsMaxLib.claymap.add(9+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 10+16*6:  PortalsMaxLib.claymap.add(10+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 11+16*6:  PortalsMaxLib.claymap.add(11+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 12+16*6:  PortalsMaxLib.claymap.add(12+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 13+16*6:  PortalsMaxLib.claymap.add(13+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 14+16*6:  PortalsMaxLib.claymap.add(14+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 15+16*6:  PortalsMaxLib.claymap.add(15+16*6, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        break;
	        default: break;
			}
			}
		if(state==this.getStateFromMeta(7)){
			switch(maxportals){ 
			case 0+16*7:  PortalsMaxLib.claymap.add(0+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 1+16*7:  PortalsMaxLib.claymap.add(1+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 2+16*7:  PortalsMaxLib.claymap.add(2+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 3+16*7:  PortalsMaxLib.claymap.add(3+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 4+16*7:  PortalsMaxLib.claymap.add(4+16+7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 5+16*7:  PortalsMaxLib.claymap.add(5+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 6+16*7:  PortalsMaxLib.claymap.add(6+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 7+16*7:  PortalsMaxLib.claymap.add(7+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 8+16*7:  PortalsMaxLib.claymap.add(8+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 9+16*7:  PortalsMaxLib.claymap.add(9+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 10+16*7:  PortalsMaxLib.claymap.add(10+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 11+16*7:  PortalsMaxLib.claymap.add(11+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 12+16*7:  PortalsMaxLib.claymap.add(12+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 13+16*7:  PortalsMaxLib.claymap.add(13+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 14+16*7:  PortalsMaxLib.claymap.add(14+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 15+16*7:  PortalsMaxLib.claymap.add(15+16*7, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        break;
	        default: break;
			}
			}
		if(state==this.getStateFromMeta(8)){
			switch(maxportals){ 
			case 0+16*8:  PortalsMaxLib.claymap.add(0+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 1+16*8:  PortalsMaxLib.claymap.add(1+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 2+16*8:  PortalsMaxLib.claymap.add(2+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 3+16*8:  PortalsMaxLib.claymap.add(3+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 4+16*8:  PortalsMaxLib.claymap.add(4+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 5+16*8:  PortalsMaxLib.claymap.add(5+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 6+16*8:  PortalsMaxLib.claymap.add(6+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 7+16*8:  PortalsMaxLib.claymap.add(7+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 8+16*8:  PortalsMaxLib.claymap.add(8+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 9+16*8:  PortalsMaxLib.claymap.add(9+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 10+16*8:  PortalsMaxLib.claymap.add(10+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 11+16*8:  PortalsMaxLib.claymap.add(11+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 12+16*8:  PortalsMaxLib.claymap.add(12+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 13+16*8:  PortalsMaxLib.claymap.add(13+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 14+16*8:  PortalsMaxLib.claymap.add(14+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 15+16*8:  PortalsMaxLib.claymap.add(15+16*8, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        break;
	        default: break;
			}
			}
		if(state==this.getStateFromMeta(9)){
			switch(maxportals){ 
			case 0+16*9:  PortalsMaxLib.claymap.add(0+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 1+16*9:  PortalsMaxLib.claymap.add(1+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 2+16*9:  PortalsMaxLib.claymap.add(2+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 3+16*9:  PortalsMaxLib.claymap.add(3+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 4+16*9:  PortalsMaxLib.claymap.add(4+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 5+16*9:  PortalsMaxLib.claymap.add(5+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 6+16*9:  PortalsMaxLib.claymap.add(6+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 7+16*9:  PortalsMaxLib.claymap.add(7+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 8+16*9:  PortalsMaxLib.claymap.add(8+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 9+16*9:  PortalsMaxLib.claymap.add(9+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 10+16*9:  PortalsMaxLib.claymap.add(10+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 11+16*9:  PortalsMaxLib.claymap.add(11+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 12+16*9:  PortalsMaxLib.claymap.add(12+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 13+16*9:  PortalsMaxLib.claymap.add(13+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 14+16*9:  PortalsMaxLib.claymap.add(14+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 15+16*9:  PortalsMaxLib.claymap.add(15+16*9, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        break;
	        default: break;
			}
			}
		if(state==this.getStateFromMeta(10)){
			switch(maxportals){ 
			case 0+16*10:  PortalsMaxLib.claymap.add(0+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 1+16*10:  PortalsMaxLib.claymap.add(1+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 2+16*10:  PortalsMaxLib.claymap.add(2+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 3+16*10:  PortalsMaxLib.claymap.add(3+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 4+16*10:  PortalsMaxLib.claymap.add(4+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 5+16*10:  PortalsMaxLib.claymap.add(5+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 6+16*10:  PortalsMaxLib.claymap.add(6+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 7+16*10:  PortalsMaxLib.claymap.add(7+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 8+16*10:  PortalsMaxLib.claymap.add(8+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 9+16*10:  PortalsMaxLib.claymap.add(9+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 10+16*10:  PortalsMaxLib.claymap.add(10+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 11+16*10:  PortalsMaxLib.claymap.add(11+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 12+16*10:  PortalsMaxLib.claymap.add(12+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 13+16*10:  PortalsMaxLib.claymap.add(13+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 14+16*10:  PortalsMaxLib.claymap.add(14+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 15+16*10:  PortalsMaxLib.claymap.add(15+16*10, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        break;
	        default: break;
			}
			}
		if(state==this.getStateFromMeta(11)){
			switch(maxportals){ 
			case 0+16*11:  PortalsMaxLib.claymap.add(0+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 1+16*11:  PortalsMaxLib.claymap.add(1+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 2+16*11:  PortalsMaxLib.claymap.add(2+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 3+16*11:  PortalsMaxLib.claymap.add(3+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 4+16*11:  PortalsMaxLib.claymap.add(4+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 5+16*11:  PortalsMaxLib.claymap.add(5+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 6+16*11:  PortalsMaxLib.claymap.add(6+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 7+16*11:  PortalsMaxLib.claymap.add(7+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 8+16*11:  PortalsMaxLib.claymap.add(8+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 9+16*11:  PortalsMaxLib.claymap.add(9+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 10+16*11:  PortalsMaxLib.claymap.add(10+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 11+16*11:  PortalsMaxLib.claymap.add(11+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 12+16*11:  PortalsMaxLib.claymap.add(12+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 13+16*11:  PortalsMaxLib.claymap.add(13+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 14+16*11:  PortalsMaxLib.claymap.add(14+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 15+16*11:  PortalsMaxLib.claymap.add(15+16*11, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        break;
	        default: break;
			}
			}
		if(state==this.getStateFromMeta(12)){
			switch(maxportals){ 
			case 0+16*12:  PortalsMaxLib.claymap.add(0+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 1+16*12:  PortalsMaxLib.claymap.add(1+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 2+16*12:  PortalsMaxLib.claymap.add(2+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 3+16*12:  PortalsMaxLib.claymap.add(3+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 4+16*12:  PortalsMaxLib.claymap.add(4+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 5+16*12:  PortalsMaxLib.claymap.add(5+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 6+16*12:  PortalsMaxLib.claymap.add(6+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 7+16*12:  PortalsMaxLib.claymap.add(7+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 8+16*12:  PortalsMaxLib.claymap.add(8+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 9+16*12:  PortalsMaxLib.claymap.add(9+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 10+16*12:  PortalsMaxLib.claymap.add(10+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 11+16*12:  PortalsMaxLib.claymap.add(11+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 12+16*12:  PortalsMaxLib.claymap.add(12+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 13+16*12:  PortalsMaxLib.claymap.add(13+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 14+16*12:  PortalsMaxLib.claymap.add(14+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 15+16*12:  PortalsMaxLib.claymap.add(15+16*12, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        break;
	        default: break;
			}
			}
		if(state==this.getStateFromMeta(13)){
			switch(maxportals){ 
			case 0+16*13:  PortalsMaxLib.claymap.add(0+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 1+16*13:  PortalsMaxLib.claymap.add(1+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 2+16*13:  PortalsMaxLib.claymap.add(2+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 3+16*13:  PortalsMaxLib.claymap.add(3+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 4+16*13:  PortalsMaxLib.claymap.add(4+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 5+16*13:  PortalsMaxLib.claymap.add(5+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 6+16*13:  PortalsMaxLib.claymap.add(6+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 7+16*13:  PortalsMaxLib.claymap.add(7+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 8+16*13:  PortalsMaxLib.claymap.add(8+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 9+16*13:  PortalsMaxLib.claymap.add(9+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 10+16*13:  PortalsMaxLib.claymap.add(10+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 11+16*13:  PortalsMaxLib.claymap.add(11+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 12+16*13:  PortalsMaxLib.claymap.add(12+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 13+16*13:  PortalsMaxLib.claymap.add(13+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 14+16*13:  PortalsMaxLib.claymap.add(14+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 15+16*13:  PortalsMaxLib.claymap.add(15+16*13, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        break;
	        default: break;
			}
			}
		if(state==this.getStateFromMeta(14)){
			switch(maxportals){ 
			case 0+16*14:  PortalsMaxLib.claymap.add(0+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 1+16*14:  PortalsMaxLib.claymap.add(1+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 2+16*14:  PortalsMaxLib.claymap.add(2+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 3+16*14:  PortalsMaxLib.claymap.add(3+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 4+16*14:  PortalsMaxLib.claymap.add(4+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 5+16*14:  PortalsMaxLib.claymap.add(5+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 6+16*14:  PortalsMaxLib.claymap.add(6+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 7+16*14:  PortalsMaxLib.claymap.add(7+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 8+16*14:  PortalsMaxLib.claymap.add(8+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 9+16*14:  PortalsMaxLib.claymap.add(9+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 10+16*14:  PortalsMaxLib.claymap.add(10+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 11+16*14:  PortalsMaxLib.claymap.add(11+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 12+16*14:  PortalsMaxLib.claymap.add(12+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 13+16*14:  PortalsMaxLib.claymap.add(13+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 14+16*14:  PortalsMaxLib.claymap.add(14+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 15+16*14:  PortalsMaxLib.claymap.add(15+16*14, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        break;
	        default: break;
			}
			}
		if(state==this.getStateFromMeta(15)){
			switch(maxportals){ 
			case 0+16*15:  PortalsMaxLib.claymap.add(0+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 1+16*15:  PortalsMaxLib.claymap.add(1+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 2+16*15:  PortalsMaxLib.claymap.add(2+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 3+16*15:  PortalsMaxLib.claymap.add(3+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 4+16*15:  PortalsMaxLib.claymap.add(4+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 5+16*15:  PortalsMaxLib.claymap.add(5+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 6+16*15:  PortalsMaxLib.claymap.add(6+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 7+16*15:  PortalsMaxLib.claymap.add(7+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 8+16*15:  PortalsMaxLib.claymap.add(8+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 9+16*15:  PortalsMaxLib.claymap.add(9+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 10+16*15:  PortalsMaxLib.claymap.add(10+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 11+16*15:  PortalsMaxLib.claymap.add(11+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 12+16*15:  PortalsMaxLib.claymap.add(12+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 13+16*15:  PortalsMaxLib.claymap.add(13+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
			case 14+16*15:  PortalsMaxLib.claymap.add(14+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        case 15+16*15:  PortalsMaxLib.claymap.add(15+16*15, new CPPos(pos.getX(), pos.getY(), pos.getZ(), worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension));
	        break;
	        default: break;
			}
			}
	}
	@Override()
	public void teleportColourfully(World world, BlockPos pos, Entity entity) {
		CPPos thiscppos = destpos;
		System.out.println("cppos set");
		int dim = 0;//thiscppos.dimension;
		System.out.println("dimension set");
		int x = thiscppos.pos.getX();
		System.out.println("x = "+ thiscppos.pos.getX());
		int y = thiscppos.pos.getY();
		System.out.println("x = "+ thiscppos.pos.getY());
		int z = thiscppos.pos.getZ();
		System.out.println("x = "+ thiscppos.pos.getZ());
		if (entity instanceof EntityPlayerMP) {
			tpPlayerColourfully(world, x, y, z, (EntityPlayerMP)entity, thiscppos);
			System.out.println("teleported player to"+ world + x + y + z + thiscppos);
		} else {
			tpEntityColourfully(world, x, y, z, entity, thiscppos);
			System.out.println("teleported enteteh to"+ world + x + y + z + thiscppos);
		}
		/*ClayPortalTE cte = new ClayPortalTE();
		
		CPPos thiscppos =PortalsMaxLib.claymap.get(cte.portalNumberToShift);
		System.out.println("cppos set");
		int dim = 0;//thiscppos.dimension;
		System.out.println("dimension set");
		int x = thiscppos.pos.getX();
		System.out.println("x = "+ thiscppos.pos.getX());
		int y = thiscppos.pos.getY();
		System.out.println("x = "+ thiscppos.pos.getY());
		int z = thiscppos.pos.getZ();
		System.out.println("x = "+ thiscppos.pos.getZ());
		
		if (entity instanceof EntityPlayerMP) {
			tpPlayerColourfully(world, x, y, z, (EntityPlayerMP)entity, thiscppos);
			System.out.println("teleported player to"+ world + x + y + z + thiscppos);
		} else {
			tpEntityColourfully(world, x, y, z, entity, thiscppos);
			System.out.println("teleported enteteh to"+ world + x + y + z + thiscppos);
		}*/
		/*if(PortalsMaxLib.claymap.containsValue(new CPPos(pos.getX(), pos.getY(), pos.getZ(), world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, false).dimension))==false){
			System.out.println("destination isn`t this block.yaaay");
			ClayPortalTE cte = new ClayPortalTE();
			CPPos thiscppos =PortalsMaxLib.claymap.get(cte.portalNumberToShift);
			System.out.println("cppos set");
			int dim = 0;//thiscppos.dimension;
			System.out.println("dimension set");
			int x = thiscppos.pos.getX();
			System.out.println("x = "+ thiscppos.pos.getX());
			int y = thiscppos.pos.getY();
			System.out.println("x = "+ thiscppos.pos.getY());
			int z = thiscppos.pos.getZ();
			System.out.println("x = "+ thiscppos.pos.getZ());
			
			Entity ridingEntity = entity.getRidingEntity();
			if (ridingEntity != null) {
			entity.dismountRidingEntity();
			ridingEntity = teleportColourfully(world, thiscppos.pos, ridingEntity);
			System.out.println("teleported riding enteteh to"+ world + x + y + z + thiscppos);
			}
			if (entity instanceof EntityPlayerMP) {
			tpPlayerColourfully(world, x, y, z, (EntityPlayerMP)entity, thiscppos);
			System.out.println("teleported player to"+ world + x + y + z + thiscppos);
			} else {
			entity = tpEntityColourfully(world, x, y, z, entity, thiscppos);
			System.out.println("teleported enteteh to"+ world + x + y + z + thiscppos);
			}
			return entity;
		}*/
	}
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new ClayPortalTE();
	}
	
}
