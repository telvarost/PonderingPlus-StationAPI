package com.github.telvarost.ponderingplus.block;

import com.github.telvarost.ponderingplus.blockentity.StoryBookshelfBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class StoryBookshelf extends TemplateBlockWithEntity {

    public StoryBookshelf(Identifier identifier, Material material) {
        super(identifier, material);
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new StoryBookshelfBlockEntity();
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
        return this.BOOKSHELF.id;
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity entity) {
        /** - Open sign screen */
        StoryBookshelfBlockEntity storyBookshelfBlockEntity = (StoryBookshelfBlockEntity)world.getBlockEntity(x, y, z);
        if (storyBookshelfBlockEntity != null) {
            entity.openEditSignScreen(storyBookshelfBlockEntity.convertToSignBlockEntity());
        }
        return true;
    }
}