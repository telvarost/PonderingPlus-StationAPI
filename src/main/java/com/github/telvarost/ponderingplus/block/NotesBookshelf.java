package com.github.telvarost.ponderingplus.block;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateSignBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class NotesBookshelf extends TemplateSignBlock {

    public NotesBookshelf(Identifier identifier, Material material) {
        super(identifier, SignBlockEntity.class, true);
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        return Box.createCached((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return this.BOOKSHELF.asItem().id;
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity entity) {
        /** - Open sign screen */
        SignBlockEntity signBlockEntity = (SignBlockEntity)world.getBlockEntity(x, y, z);
        if (signBlockEntity != null) {
            entity.openEditSignScreen(signBlockEntity);
        }
        return true;
    }
}