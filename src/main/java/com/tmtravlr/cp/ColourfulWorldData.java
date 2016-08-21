package com.tmtravlr.cp;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraftforge.common.util.Constants;

/*
 * THE AUTHOR OF this class is Choonster.go give him a hug.go now,my child.-DiMuRie
 */

public class ColourfulWorldData extends WorldSavedData {

	private final static String DATA_NAME = "CPL[]";
	private static ColourfulWorldData instance = null;

	/**
	 * The location list.
	 */
	private final List<BlockPos> locationList = new LinkedList<BlockPos>();

	/**
	 * A read-only view of the location list
	 */
	private final List<BlockPos> locationListUnmodifiable = Collections.unmodifiableList(locationList);

	private ColourfulWorldData() {
		super(DATA_NAME);
	}

	/**
	 * Get the {@link ColourfulData} instance.
	 *
	 * @param world The World
	 * @return The ColourfulData instance
	 */
	@Nullable
	public static ColourfulWorldData get(World world) {
		if (world.isRemote) {
			return null;
		}

		if (instance != null) {
			return instance;
		}

		instance = (ColourfulWorldData) world.loadItemData(ColourfulWorldData.class, DATA_NAME);

		if (instance == null) {
			instance = new ColourfulWorldData();
		}

		return instance;
	}

	/**
	 * Clear the {@link ColourfulData} instance.
	 */
	public static void clearInstance() {
		instance = null;
	}

	/**
	 * Save the location list.
	 *
	 * @param world A server world
	 */
	public void save(World world) {
		world.setItemData(DATA_NAME, this);
		markDirty();
	}

	/**
	 * Get the location list.
	 *
	 * @return A read-only view of the location list
	 */
	public List<BlockPos> getLocationList() {
		return locationListUnmodifiable;
	}

	/**
	 * Add a location to the list.
	 *
	 * @param location The location
	 */
	public void addLocation(BlockPos location) {
		locationList.add(location);
	}

	/**
	 * Remove a location from the list.
	 *
	 * @param location The location
	 */
	public void removeLocation(BlockPos location) {
		locationList.remove(location);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		// Clear the current location list
		locationList.clear();

		// Get the location list tag
		final NBTTagList portalLocations = compound.getTagList("PortalLocations", Constants.NBT.TAG_COMPOUND);

		// For each saved location,
		for (int i = 0; i < portalLocations.tagCount(); i++) {
			// Get the location compound tag from the list tag
			final NBTTagCompound locationTag = portalLocations.getCompoundTagAt(i);

			// Create a new BlockPos from the saved data
			final BlockPos location = new BlockPos(locationTag.getInteger("posX"), locationTag.getInteger("posY"), locationTag.getInteger("posZ"));

			// Add it to the location list
			locationList.add(location);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		// Create the location list tag
		final NBTTagList portalLocations = new NBTTagList();

		// For each location in the current location list
		for (BlockPos location : locationList) {
			// Save the location to a compound tag
			final NBTTagCompound locationTag = new NBTTagCompound();
			locationTag.setInteger("posX", location.getX());
			locationTag.setInteger("posY", location.getY());
			locationTag.setInteger("posZ", location.getZ());

			// Add it to the list tag
			portalLocations.appendTag(locationTag);
		}

		// Store the list tag in the compound tag
		compound.setTag("PortalLocations", portalLocations);

		// Return the compound tag
		return compound;
	}

}
