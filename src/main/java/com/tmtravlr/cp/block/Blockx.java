package com.tmtravlr.cp.block;

import java.util.Random;

import javax.annotation.Nullable;

import com.tmtravlr.cp.init.ColourfulItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Blockx extends Block {
	
	public static final PropertyInteger NUMBER = PropertyInteger.create("numberx", 0, 9);
	
	public Blockx(){
		super(Material.ROCK);
		setCreativeTab(ColourfulItems.cpTab);
		setUnlocalizedName("000x");
		setRegistryName("000x");
		setBlockUnbreakable();
		this.setDefaultState(this.blockState.getBaseState().withProperty(NUMBER, Integer.valueOf(0)));
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
	
    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(NUMBER)).intValue();
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(NUMBER, Integer.valueOf(MathHelper.clamp_int(meta, 0, 9)));
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {NUMBER});
    }
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	if(state==this.getStateFromMeta(0)){
    		setState1(worldIn, pos, state);
    	}
    	if(state==this.getStateFromMeta(1)){
    		setState2(worldIn, pos, state);
    	}
    	if(state==this.getStateFromMeta(2)){
    		setState3(worldIn, pos, state);
    	}
    	if(state==this.getStateFromMeta(3)){
    		setState4(worldIn, pos, state);
    	}
    	if(state==this.getStateFromMeta(4)){
    		setState5(worldIn, pos, state);
    	}
    	if(state==this.getStateFromMeta(5)){
    		setState6(worldIn, pos, state);
    	}
    	if(state==this.getStateFromMeta(6)){
    		setState7(worldIn, pos, state);
    	}
    	if(state==this.getStateFromMeta(7)){
    		setState8(worldIn, pos, state);
    	}
    	if(state==this.getStateFromMeta(8)){
    		setState9(worldIn, pos, state);
    	}
    	if(state==this.getStateFromMeta(9)){
    		setState0(worldIn, pos, state);
    	}
    	if(playerIn.isSneaking()){
    		setState0(worldIn, pos, state);
    	}
    	return true;
	}

    private void setState0(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, this.getStateFromMeta(0), 9);
	}
	private void setState1(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, this.getStateFromMeta(1), 9);
	}
	private void setState2(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, this.getStateFromMeta(2), 9);
	}
	private void setState3(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, this.getStateFromMeta(3), 9);
	}
	private void setState4(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, this.getStateFromMeta(4), 9);
	}
	private void setState5(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, this.getStateFromMeta(5), 9);
	}
	private void setState6(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, this.getStateFromMeta(6), 9);
	}
	private void setState7(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, this.getStateFromMeta(7), 9);
	}
	private void setState8(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, this.getStateFromMeta(8), 9);
	}
	private void setState9(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, this.getStateFromMeta(9), 9);
	}
	
	
}
