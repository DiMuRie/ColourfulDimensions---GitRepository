package com.tmtravlr.cp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

import com.tmtravlr.cp.block.ColourfulPortal;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class CPLib {
	
	public static final String MODID = "colourfulportals";
	public static final String VERSION = "2.0-alpha";
	public static final String NAME = "ColourfulPortalsMod";

	public static final String CLIENT_PROXY_CLASS = "com.tmtravlr.cp.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.tmtravlr.cp.proxy.ServerProxy";
	
	static World world;
	
	public static boolean useDestinationBlackList = false;
	public static boolean useDestinationWhiteList = false;
	public static boolean useDimensionBlackList = false;
	public static boolean useDimensionWhiteList = false;
	
	public static int[] destinationBlackList = { 1 };
	public static int[] destinationWhiteList = { 0, -1 };
	public static int[] dimensionBlackList = new int[0];
	public static int[] dimensionWhiteList = { 0, 1, -1 };
	
	public static HashMap<Integer, ColourfulPortal> cpBlocks = new HashMap();
	
	
	public String portalSufix = "_cp";//Probably not gunna use < but still gunna put it here
	
	//now.let`s start with our lib methods.for science and colourfulness.yeah.......cue the methods!
	
	public static boolean isCPBlock(Block block){
		return cpBlocks.containsValue(block);
	}
	
	/*public static int getShiftedCPMetadata(IBlockAccess iba, BlockPos pos)
	{
		IBlockState state = iba.getBlockState(pos);
		return getShiftedCPMetadata(state);
	}*/
	
	public static int getIndexFromShiftedMetadata(int meta)
	{
		return (int)Math.floor(meta / 16);
	}

	public static int getShiftedClaymaPMetadata(IBlockState state)
	{
		for (int i = 0; i < cpBlocks.size(); i++) {
			if ((cpBlocks.get(i) == state.getBlock())) {
				return getMeta(state) + 16 * i;
			}
		}
		return -1;
	}
	
	public static Block getCPBlockByShiftedMetadata(int meta)
	{
		return (Block)cpBlocks.get(getIndexFromShiftedMetadata(meta));
	}
	
	public static int unshiftCPMetadata(int meta)
	{
		return meta % 16;
	}
	
	public static int getMeta(IBlockAccess iba, BlockPos pos) {
		return getMeta(iba.getBlockState(pos));
	}
	
	public static int getMeta(IBlockState state) {
		return state.getBlock().getMetaFromState(state);
	}
	
	public static boolean isDimensionValidForDestination(int dimension)
	{
		if (!isDimensionValidAtAll(dimension)) {
			return false;
		}
		if (useDestinationWhiteList)
		{
			if (destinationWhiteList.length == 0) {
				return false;
			}
			boolean inWhiteList = false;
			for (int i = 0; i < destinationWhiteList.length; i++) {
				if (dimension == destinationWhiteList[i]) {
					inWhiteList = true;
				}
			}
			if (!inWhiteList) {
				return false;
			}
		}
		if (useDestinationBlackList) {
			for (int i = 0; i < destinationBlackList.length; i++) {
				if (dimension == destinationBlackList[i]) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isDimensionValidAtAll(int dimension)
	{
		if (useDimensionWhiteList)
		{
			if (dimensionWhiteList.length == 0) {
				return false;
			}
			boolean inWhiteList = false;
			for (int i = 0; i < dimensionWhiteList.length; i++) {
				if (dimension == dimensionWhiteList[i]) {
					inWhiteList = true;
				}
			}
			if (!inWhiteList) {
				return false;
			}
		}
		if (useDimensionBlackList) {
			for (int i = 0; i < dimensionBlackList.length; i++) {
				if (dimension == dimensionBlackList[i]) {
					return false;
				}
			}
		}
		return true;
	}
	
	//
	
	
	public static void checkForPortalChanges(World world)
	{
		ArrayList<BlockPos> toDelete = new ArrayList<BlockPos>();
		final ColourfulWorldData colourfulWorldData = ColourfulWorldData.get(world);

		if (colourfulWorldData != null) {
			for (BlockPos portal : colourfulWorldData.getLocationList())
			{
				toDelete.add(portal);
			}

			for (BlockPos deleted : toDelete) {
				colourfulWorldData.removeLocation(deleted);
			}

			colourfulWorldData.save(world);
		}
	}

	public static BlockPos getColourfulDestination(World world, BlockPos pos)
	{
		final ColourfulWorldData colourfulWorldData = ColourfulWorldData.get(world);
		if (colourfulWorldData == null || colourfulWorldData.getLocationList().size() <= 0) {
			return new BlockPos(pos);
		}

		final List<BlockPos> locationList = colourfulWorldData.getLocationList();
		BlockPos start = findBlockPos(world, pos);

		int originalPos = locationList.indexOf(start);
		if (originalPos == -1) {
			return new BlockPos(pos);
		}
		int size = locationList.size();
		for (int i = 0; i < size; i++) {
			int index = i + originalPos + 1;
			if (index >= size) {
				index -= size;
			}
			BlockPos current = locationList.get(index);
			if (world.getMinecraftServer() != null) {
				return current;
			}
		}

		return start;
	}

	public static BlockPos findBlockPos(World world, BlockPos pos)
	{
		if (!isCPBlock(world.getBlockState(pos).getBlock())) {
			return null;
		}
		if (isCPBlock(world.getBlockState(pos).getBlock())) {
			return new BlockPos(pos);
		}
		boolean xDir = true;
		boolean yDir = true;
		boolean zDir = true;
		int i = 0;
		
		CPLSet visited = new CPLSet();
		Stack<BlockPos> toVisit = new Stack<BlockPos>();

		toVisit.push(new BlockPos(pos));

		visited.add(toVisit.peek());
		final ColourfulWorldData colourfulWorldData = ColourfulWorldData.get(world);
		if (colourfulWorldData == null) {
			return null;
		}

		final List<BlockPos> locationList = colourfulWorldData.getLocationList();

		while (!toVisit.empty()) {
			BlockPos current = toVisit.pop();
			if (locationList.contains(current)) {
				return current;
			}
			BlockPos currentPos = current;
			if ((zDir) || (xDir)) {
				if (isCPBlock(world.getBlockState(currentPos.add(0, 1, 0)).getBlock())) {
					BlockPos temp = new BlockPos(currentPos.add(0, 1, 0));
					if (!visited.contains(temp)) {
						toVisit.push(temp);
						visited.add(temp);
					}
				}
				if (isCPBlock(world.getBlockState(currentPos.add(0, -1, 0)).getBlock())) {
					BlockPos temp = new BlockPos(currentPos.add(0, -1, 0));
					if (!visited.contains(temp)) {
						toVisit.push(temp);
						visited.add(temp);
					}
				}
			}
			if ((zDir) || (yDir)) {
				if (isCPBlock(world.getBlockState(currentPos.add(1, 0, 0)).getBlock())) {
					BlockPos temp = new BlockPos(currentPos.add(1, 0, 0));
					if (!visited.contains(temp)) {
						toVisit.push(temp);
						visited.add(temp);
					}
				}
				if (isCPBlock(world.getBlockState(currentPos.add(-1, 0, 0)).getBlock())) {
					BlockPos temp = new BlockPos(currentPos.add(-1, 0, 0));
					if (!visited.contains(temp)) {
						toVisit.push(temp);
						visited.add(temp);
					}
				}
			}
			if ((yDir) || (xDir)) {
				if (isCPBlock(world.getBlockState(currentPos.add(0, 0, 1)).getBlock())) {
					BlockPos temp = new BlockPos(currentPos.add(0, 0, 1));
					if (!visited.contains(temp)) {
						toVisit.push(temp);
						visited.add(temp);
					}
				}
				if (isCPBlock(world.getBlockState(currentPos.add(0, 0, -1)).getBlock())) {
					BlockPos temp = new BlockPos(currentPos.add(0, 0, -1));
					if (!visited.contains(temp)) {
						toVisit.push(temp);
						visited.add(temp);
					}
				}
			}
		}

		return null;
	}
	
	public static void deletePortal(World world, BlockPos locToDelete)
	{
		final ColourfulWorldData colourfulData = ColourfulWorldData.get(world);
		if (colourfulData != null) {
			colourfulData.removeLocation(locToDelete);
			colourfulData.save(world);
		}
	}

	public static boolean addPortalToList(World world, BlockPos newLocation)
	{
		final ColourfulWorldData colourfulData = ColourfulWorldData.get(world);
		if (colourfulData != null && !colourfulData.getLocationList().contains(newLocation)) {
			colourfulData.addLocation(newLocation);
			colourfulData.save(world);

			return true;
		}
		return false;
	}

	public static class CPLSet extends TreeSet<BlockPos>
	{
		public CPLSet()
		{
			super(CPLcomparator);
		}
	}

	public static Comparator<BlockPos> CPLcomparator = new Comparator<BlockPos>()
			{

		@Override
		public int compare(BlockPos first, BlockPos second)
		{
			return first.compareTo(second);
		}
			};

}
