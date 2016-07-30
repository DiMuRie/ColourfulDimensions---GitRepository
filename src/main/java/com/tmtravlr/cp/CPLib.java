package com.tmtravlr.cp;

import java.util.ArrayList;
import java.util.Comparator;
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
	
	public static List<ColourfulPortal> cpBlocks = new ArrayList<ColourfulPortal>();
	
	static World world;
	
	public static boolean useDestinationBlackList = false;
	public static boolean useDestinationWhiteList = false;
	public static boolean useDimensionBlackList = false;
	public static boolean useDimensionWhiteList = false;
	
	public static int[] destinationBlackList = { 1 };
	public static int[] destinationWhiteList = { 0, -1 };
	public static int[] dimensionBlackList = new int[0];
	public static int[] dimensionWhiteList = { 0, 1, -1 };
	
	
	public String portalSufix = "_cp";//Probably not gunna use < but still gunna put it here
	
	//now.let`s start with our lib methods.for science and colourfulness.yeah.......cue the methods!
	
	public static boolean isCPBlock(Block block){
		return cpBlocks.contains(block);
	}
	
	public static int getShiftedCPMetadata(IBlockAccess iba, BlockPos pos)
	{
		IBlockState state = iba.getBlockState(pos);
		return getShiftedCPMetadata(state);
	}
	
	public static int getIndexFromShiftedMetadata(int meta)
	{
		return (int)Math.floor(meta / 16);
	}

	public static int getShiftedCPMetadata(IBlockState state)
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
		ArrayList<CPPos> toDelete = new ArrayList<CPPos>();
		final ColourfulWorldData colourfulWorldData = ColourfulWorldData.get(world);

		if (colourfulWorldData != null) {
			for (CPPos portal : colourfulWorldData.getLocationList())
			{
				WorldServer currentWS = world.getMinecraftServer().worldServerForDimension(portal.dimension);
				BlockPos currentPos = portal.pos;
				if ((getCPBlockByShiftedMetadata(portal.portalMetadata) != currentWS.getBlockState(currentPos).getBlock())) {
					toDelete.add(portal);
				}
			}

			for (CPPos deleted : toDelete) {
				colourfulWorldData.removeLocation(deleted);
			}

			colourfulWorldData.save(world);
		}
	}

	public static CPPos getColourfulDestination(World world, BlockPos pos)
	{
		final ColourfulWorldData colourfulWorldData = ColourfulWorldData.get(world);
		if (colourfulWorldData == null || colourfulWorldData.getLocationList().size() <= 0) {
			return new CPPos(pos, world.provider.getDimension(), getShiftedCPMetadata(world, pos));
		}

		final List<CPPos> locationList = colourfulWorldData.getLocationList();
		CPPos start = findCPPos(world, pos);

		int originalPos = locationList.indexOf(start);
		if (originalPos == -1) {
			return new CPPos(pos, world.provider.getDimension(), getShiftedCPMetadata(world, pos));
		}
		int size = locationList.size();
		for (int i = 0; i < size; i++) {
			int index = i + originalPos + 1;
			if (index >= size) {
				index -= size;
			}
			CPPos current = locationList.get(index);
			if (current.portalMetadata == start.portalMetadata) {
				if (world.getMinecraftServer().worldServerForDimension(current.dimension) != null) {
					return current;
				}
			}
		}

		return start;
	}

	public static CPPos findCPPos(World world, BlockPos pos)
	{
		if (!isCPBlock(world.getBlockState(pos).getBlock())) {
			return null;
		}
		if (isCPBlock(world.getBlockState(pos).getBlock())) {
			return new CPPos(pos, world.provider.getDimension(), getShiftedCPMetadata(world, pos));
		}
		boolean xDir = true;
		boolean yDir = true;
		boolean zDir = true;
		int i = 0;
		
		CPLSet visited = new CPLSet();
		Stack<CPPos> toVisit = new Stack<CPPos>();

		toVisit.push(new CPPos(pos, world.provider.getDimension(), getShiftedCPMetadata(world, pos)));

		visited.add(toVisit.peek());
		final ColourfulWorldData colourfulWorldData = ColourfulWorldData.get(world);
		if (colourfulWorldData == null) {
			return null;
		}

		final List<CPPos> locationList = colourfulWorldData.getLocationList();

		while (!toVisit.empty()) {
			CPPos current = toVisit.pop();
			if (locationList.contains(current)) {
				return current;
			}
			BlockPos currentPos = current.pos;
			if ((zDir) || (xDir)) {
				if (isCPBlock(world.getBlockState(currentPos.add(0, 1, 0)).getBlock())) {
					CPPos temp = new CPPos(currentPos.add(0, 1, 0), world.provider.getDimension(), getShiftedCPMetadata(world, currentPos.add(0, 1, 0)));
					if (!visited.contains(temp)) {
						toVisit.push(temp);
						visited.add(temp);
					}
				}
				if (isCPBlock(world.getBlockState(currentPos.add(0, -1, 0)).getBlock())) {
					CPPos temp = new CPPos(currentPos.add(0, -1, 0), world.provider.getDimension(), getShiftedCPMetadata(world, currentPos.add(0, -1, 0)));
					if (!visited.contains(temp)) {
						toVisit.push(temp);
						visited.add(temp);
					}
				}
			}
			if ((zDir) || (yDir)) {
				if (isCPBlock(world.getBlockState(currentPos.add(1, 0, 0)).getBlock())) {
					CPPos temp = new CPPos(currentPos.add(1, 0, 0), world.provider.getDimension(), getShiftedCPMetadata(world, currentPos.add(1, 0, 0)));
					if (!visited.contains(temp)) {
						toVisit.push(temp);
						visited.add(temp);
					}
				}
				if (isCPBlock(world.getBlockState(currentPos.add(-1, 0, 0)).getBlock())) {
					CPPos temp = new CPPos(currentPos.add(-1, 0, 0), world.provider.getDimension(), getShiftedCPMetadata(world, currentPos.add(-1, 0, 0)));
					if (!visited.contains(temp)) {
						toVisit.push(temp);
						visited.add(temp);
					}
				}
			}
			if ((yDir) || (xDir)) {
				if (isCPBlock(world.getBlockState(currentPos.add(0, 0, 1)).getBlock())) {
					CPPos temp = new CPPos(currentPos.add(0, 0, 1), world.provider.getDimension(), getShiftedCPMetadata(world, currentPos.add(0, 0, 1)));
					if (!visited.contains(temp)) {
						toVisit.push(temp);
						visited.add(temp);
					}
				}
				if (isCPBlock(world.getBlockState(currentPos.add(0, 0, -1)).getBlock())) {
					CPPos temp = new CPPos(currentPos.add(0, 0, -1), world.provider.getDimension(), getShiftedCPMetadata(world, currentPos.add(0, 0, -1)));
					if (!visited.contains(temp)) {
						toVisit.push(temp);
						visited.add(temp);
					}
				}
			}
		}

		return null;
	}
	
	public static void deletePortal(World world, CPPos locToDelete)
	{
		final ColourfulWorldData colourfulData = ColourfulWorldData.get(world);
		if (colourfulData != null) {
			colourfulData.removeLocation(locToDelete);
			colourfulData.save(world);
		}
	}

	public static boolean addPortalToList(World world, CPPos newLocation)
	{
		final ColourfulWorldData colourfulData = ColourfulWorldData.get(world);
		if (colourfulData != null && !colourfulData.getLocationList().contains(newLocation)) {
			colourfulData.addLocation(newLocation);
			colourfulData.save(world);

			return true;
		}
		return false;
	}

	public static class CPLSet extends TreeSet<CPPos>
	{
		public CPLSet()
		{
			super(CPLcomparator);
		}
	}

	public static Comparator<CPPos> CPLcomparator = new Comparator<CPPos>()
			{

		@Override
		public int compare(CPPos first, CPPos second)
		{
			if (first.portalMetadata != second.portalMetadata) {
				return second.portalMetadata - first.portalMetadata;
			}
			if (first.dimension != second.dimension) {
				return second.dimension - first.dimension;
			}
			return first.pos.compareTo(second.pos);
		}
			};

}
