package com.kamileq_14.voidworld.block.custom.tank;


import com.kamileq_14.voidworld.util.ModBlockEntities;
import com.supermartijn642.core.block.BaseTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;


public class FluidTankTileEntity extends BaseTileEntity implements IFluidHandler{

 private FluidStack fluidStack = FluidStack.EMPTY;
 private boolean output = false;

 public FluidTankTileEntity(BlockPos pWorldPosition, BlockState pBlockState) {
     super(ModBlockEntities.FLUID_TANK_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
 }
    @Override
    protected CompoundTag writeData() {
        CompoundTag compound = new CompoundTag();
        compound.putBoolean("output", this.output);
        compound.put("fluid", this.fluidStack.writeToNBT(new CompoundTag()));
        return compound;
    }


    @Override
    protected void readData(CompoundTag compound) {
        this.output = compound.getBoolean("output");
        this.fluidStack = FluidStack.loadFluidStackFromNBT(compound.getCompound("fluid"));
    }



    public void tick() {
        if (this.output && !this.fluidStack.isEmpty()) {
            BlockEntity tileEntity = this.level.getBlockEntity(this.worldPosition.below());
            if (tileEntity != null)
                tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
                        .ifPresent(handler -> FluidUtil.tryFluidTransfer(handler, this, 1000, true));
        }
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this));
    }

    @Override
    public int getTanks() {
        return 1;
    }

        @Nonnull
        @Override
        public FluidStack getFluidInTank(int tank) {
            return this.fluidStack.copy();
        }
     @Override
    public int getTankCapacity(int tank) {
     return tankCapacity;
     }
     int tankCapacity = 8000;
    @Override
    public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
        return true;
    }
    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if (resource.isEmpty() || !(this.fluidStack.isEmpty() || this.fluidStack.isFluidEqual(resource)))
            return 0;
        int amount = Math.min(resource.getAmount(), this.getTankCapacity(0) - this.fluidStack.getAmount());
        if (action.execute()) {
            FluidStack newStack = resource.copy();
            newStack.setAmount(this.fluidStack.getAmount() + amount);
            this.fluidStack = newStack;
            this.dataChanged();
        }
        return amount;
    }
    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        if (resource.isEmpty() || this.fluidStack.isEmpty() || !this.fluidStack.getFluid().isSame(resource.getFluid()))
            return FluidStack.EMPTY;
        int amount = Math.min(resource.getAmount(), this.fluidStack.getAmount());
        FluidStack returnStack = this.fluidStack.copy();
        returnStack.setAmount(amount);
        if (action.execute()) {
            this.fluidStack.shrink(amount);
            this.dataChanged();
        }
        return returnStack;
    }
    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        if (maxDrain <= 0 || this.fluidStack.isEmpty())
            return FluidStack.EMPTY;
        int amount = Math.min(maxDrain, this.fluidStack.getAmount());
        FluidStack returnStack = this.fluidStack.copy();
        returnStack.setAmount(amount);
        if (action.execute()) {
            this.fluidStack.shrink(amount);
            this.dataChanged();
        }
        return returnStack;
    }

    public boolean interactWithItemFluidHandler(IFluidHandlerItem fluidHandler) {
        // just only consider tank 0 from items, as that's probably fine in most cases
        if (fluidHandler.getTanks() == 0)
            return false;
        FluidStack tankFluid = fluidHandler.getFluidInTank(0);
        if (tankFluid.isEmpty()) {
            if (!this.fluidStack.isEmpty() && fluidHandler.isFluidValid(0, this.fluidStack)) {
                int amount = fluidHandler.fill(this.fluidStack.copy(), FluidAction.EXECUTE);
                if (amount > 0) {
                    this.fluidStack.shrink(amount);
                    this.dataChanged();
                    return true;
                }
            }
        } else if (this.fluidStack.isEmpty() || this.fluidStack.isFluidEqual(tankFluid)) {
            tankFluid = tankFluid.copy();
            tankFluid.setAmount(this.getTankCapacity(0) - this.fluidStack.getAmount());
            FluidStack amount = fluidHandler.drain(tankFluid, FluidAction.SIMULATE);
            if (!amount.isEmpty() && (this.fluidStack.isEmpty() || this.fluidStack.isFluidEqual(amount))) {
                amount = fluidHandler.drain(tankFluid, FluidAction.EXECUTE);
                amount.grow(this.fluidStack.getAmount());
                this.fluidStack = amount;
                this.dataChanged();
                return true;
            }
        }
        return false;
    }




}



