package com.github.telvarost.ponderingplus.entity;

import com.github.telvarost.ponderingplus.ModHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.RailBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.EntitySpawnDataProvider;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.TriState;

import java.util.List;

@HasTrackingParameters(updatePeriod = 4, sendVelocity = TriState.TRUE, trackingDistance = 30)
public class SeatEntity extends Entity implements EntitySpawnDataProvider {

    public SeatEntity(World world) {
        super(world);
        this.setBoundingBoxSpacing(0.6F, 0.6F);
        this.standingEyeHeight = 0.01F;
    }

    public SeatEntity(World world, Double x, Double y, Double z) {
        this(world);
        this.setPosition(x, y, z);
        this.velocityX = 0.0;
        this.velocityY = 0.0;
        this.velocityZ = 0.0;
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public float getShadowRadius() {
        return 0.45F;
    }

    @Override
    public Box getBoundingBox() {
        return this.boundingBox;
    }

    @Override
    protected boolean bypassesSteppingEffects() {
        return false;
    }

    @Override
    public double getPassengerRidingHeight() {
        return (double)this.height * 0.0 - 0.05;
    }

    @Override
    public void updatePassengerPosition() {
        if (this.passenger != null) {
            this.passenger.setPosition(this.x, this.y + this.getPassengerRidingHeight() + this.passenger.getStandingEyeHeight(), this.z);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.world.isRemote) {
            this.setPosition(this.x, this.y, this.z);
            this.setRotation(this.yaw, this.pitch);
        } else {
            this.prevX = this.x;
            this.prevY = this.y;
            this.prevZ = this.z;
            this.velocityY -= 0.03999999910593033;
            int var1 = MathHelper.floor(this.x);
            int var2 = MathHelper.floor(this.y);
            int var3 = MathHelper.floor(this.z);
            if (RailBlock.isRail(this.world, var1, var2 - 1, var3)) {
                --var2;
            }

            double var4 = 0.4;

            if (this.velocityX < -var4) {
                this.velocityX = -var4;
            }

            if (this.velocityX > var4) {
                this.velocityX = var4;
            }

            if (this.velocityZ < -var4) {
                this.velocityZ = -var4;
            }

            if (this.velocityZ > var4) {
                this.velocityZ = var4;
            }

            if (this.onGround) {
                this.velocityX *= 0.5;
                this.velocityY *= 0.5;
                this.velocityZ *= 0.5;
            }

            this.move(this.velocityX, this.velocityY, this.velocityZ);
            if (!this.onGround) {
                this.velocityX *= 0.949999988079071;
                this.velocityY *= 0.949999988079071;
                this.velocityZ *= 0.949999988079071;
            }

            List var16 = this.world.getEntities(this, this.boundingBox.expand(0.20000000298023224, 0.0, 0.20000000298023224));
            if (var16 != null && var16.size() > 0) {
                for(int var51 = 0; var51 < var16.size(); ++var51) {
                    Entity var18 = (Entity)var16.get(var51);
                    if (var18 != this.passenger && var18.isPushable() && var18 instanceof SeatEntity) {
                        var18.onCollision(this);
                    }
                }
            }

            if (this.passenger != null && this.passenger.dead) {
                this.passenger = null;
            }

            if (null == this.passenger) {
                world.remove(this);
            } else if (this.passenger.isSneaking()) {
                if (this.onGround) {
                    this.setPosition(this.x, this.y + 1.0, this.z);
                }
                this.passenger.setVehicle(null);
                world.remove(this);
            }
        }
    }

    @Override
    public boolean interact(PlayerEntity player) {
        if (this.passenger != null && this.passenger instanceof PlayerEntity && this.passenger != player) {
            return true;
        } else {
            if (!this.world.isRemote) {
                player.setVehicle(this);
            }

            return true;
        }
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeNbt(NbtCompound nbt) {

    }

    @Override
    public Identifier getHandlerIdentifier() {
        return ModHelper.NAMESPACE.id("seat");
    }
}