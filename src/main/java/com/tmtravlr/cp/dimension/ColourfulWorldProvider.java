package com.tmtravlr.cp.dimension;

import akka.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ColourfulWorldProvider extends WorldProvider {

	public DimensionType getDimensionType()
    {
        return DimensionType.OVERWORLD;
    }

    /**
     * Called to determine if the chunk at the given chunk coordinates within the provider's world can be dropped. Used
     * in WorldProviderSurface to prevent spawn chunks from being unloaded.
     */
    public boolean canDropChunk(int x, int z)
    {
        return !this.worldObj.isSpawnChunk(x, z) || !this.worldObj.provider.getDimensionType().shouldLoadSpawn();
    }
	
	private float[] colorsSunriseSunset = new float[4];
	
	public void registerWorldChunkManager()
	{
		this.setDimension(17);
		this.hasNoSky = false;
	}
	/*public IChunkProvider createChunkGenerator()
	{
		return new ColourfulChunkProvider();
	}*/
	
	public IChunkProvider createChunkProvider(){
		return new ColourfulChunkProvider();
	}
	
	public int getAverageGroundLevel()
	{
		return 0;
	}
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int par1, int par2)
	{
		return false;
	}
	public String getDimensionName()
	{
		return "ColourfulPortalHub";
	}
	public boolean renderStars()
	{
		return true;
	}
	public float getStarBrightness(World world, float f)
	{
		return 10.0F;
	}
	public boolean renderClouds()
	{
		return false;
	}
	public boolean renderVoidFog()
	{
		return false;
	}
	public boolean renderEndSky()
	{
		return false;
	}
	public float setSunSize()
	{
		return 10.0F;
	}
	public float setMoonSize()
	{
		return 8.0F;
	}
	@SideOnly(Side.CLIENT)
	public boolean isSkyColored()
	{
		return true;
	}
	public boolean canRespawnHere()
	{
		return false;
	}
	public boolean isSurfaceWorld()
	{
		return true;
	}
	@SideOnly(Side.CLIENT)
	public float getCloudHeight()
	{
		return 128.0F;
	}
	@SideOnly(Side.CLIENT)
	public ResourceLocation getSunTexture()
	{
		return textureSun;
	}
	private static final ResourceLocation textureSun = new ResourceLocation("textures/misc/colourfulsun");
	//TODO create the sun texture.baka!
	public boolean canCoordinateBeSpawn(int par1, int par2)
	{
		return false;
	}
	public ChunkPos getEntrancePortalLocation()
	{
		BlockPos startpos = new BlockPos(0,25,0);
		//return new ChunkPos(0, 5);
		return new ChunkPos(startpos);
	}
	protected void generateLightBrightnessTable()
	{
		float f = 12.0F;
		for (int i = 0; i <= 15; i++)
		{
			float f1 = 12.0F - i / 15.0F;
			this.lightBrightnessTable[i] = ((1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f);
		}
	}
	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage()
	{
		if ((this instanceof ColourfulWorldProvider))
		{
			return "Entering Colourful Portal Hub";
		}
		return null;
	}
	@SideOnly(Side.CLIENT)
	public float[] calcSunriseSunsetColors(float par1, float par2)
	{
		float f2 = 0.4F;
		float f3 = MathHelper.cos(par1 * 3.141593F * 2.0F) - 0.0F;
		float f4 = -0.0F;
		if ((f3 >= f4 - f2) && (f3 <= f4 + f2))
		{
			float f5 = (f3 - f4) / f2 * 0.5F + 0.5F;
			float f6 = 1.0F - (1.0F - MathHelper.sin(f5 * 3.141593F)) * 0.99F;
			f6 *= f6;
			this.colorsSunriseSunset[0] = (f5 * 0.3F + 0.7F);
			this.colorsSunriseSunset[1] = (f5 * f5 * 0.7F + 0.2F);
			this.colorsSunriseSunset[2] = (f5 * f5 * 0.0F + 0.2F);
			this.colorsSunriseSunset[3] = f6;
			return this.colorsSunriseSunset;
		}
		return null;
	}
	public float calculateCelestialAngle(long par1, float par3)
	{
		int j = (int)(par1 % 24000L);
		float f1 = (j + par3) / 24000.0F - 0.25F;
		if (f1 < 0.0F)
		{
			f1 += 1.0F;
		}
		if (f1 > 1.0F)
		{
			f1 -= 1.0F;
		}
		float f2 = f1;
		f1 = 1.0F - (float)((Math.cos(f1 * 3.141592653589793D) + 1.0D) / 2.0D);
		f1 = f2 + (f1 - f2) / 3.0F;
		return f1;
	}

}
