package me.planetguy.ore.content.lavawell;

import cofh.api.energy.IEnergyHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

//@Optional.Interface(iface = "cofh.api.energy.IEnergyHandler", modid = "CoFHCore")
public class TileEntityCryoPump extends TEThermal implements IEnergyHandler{

	public int energy=0;
	
	public static final int maxEnergy=100000;
	public static final int mass=2000;

	@Override
	public int receiveEnergy(ForgeDirection from, int e,
			boolean simulate) {
		int excess=Math.max(0,energy+e-maxEnergy);
		if(!simulate){
			energy+=e-excess;
		}
		return e-excess;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,
			boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return energy;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return maxEnergy;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setInteger("energy", energy);
		
	}
	
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		energy=tag.getInteger("energy");
	}

	@Override
	public float getRadiationToEnvironment() {
		int energyToUse=energy/100;
		energy-=energyToUse;
		return energyToUse * BLMBalance.RADIATION_CRYO_PUMP;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}
	
	@Override
	public int getMass() {
		return BLMBalance.MASS_CRYO_PUMP;
	}

	@Override
	public int meltingTime() {
		return BLMBalance.MTIME_CRYO_PUMP;
	}

	@Override
	public int meltingTemp() {
		return BLMBalance.MTEMP_CRYO_PUMP;
	}

	@Override
	public float getHeatAvailable() {
		return BLMBalance.AVAILABLE_CRYO_PUMP;
	}

}
