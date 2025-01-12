package com.github.telvarost.ponderingplus.entity.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.EntityRenderer;

public class ModelBox extends ModelPart {
    public ModelBox(EntityRenderer entityRenderer, int u, int v, float x, float y, float z, int sizeX, int sizeY, int sizeZ, float delta, boolean mirror) {
        super(u, v);
        this.pitch = 0.0F;
        this.yaw = 0.0F;
        this.roll = 0.0F;
        this.mirror = mirror;
        this.addCuboid(x, y, z, sizeX, sizeY, sizeZ);
    }
}