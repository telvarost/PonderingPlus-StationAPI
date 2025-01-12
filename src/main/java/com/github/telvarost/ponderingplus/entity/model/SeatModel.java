package com.github.telvarost.ponderingplus.entity.model;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;

public class SeatModel extends EntityModel {
    public ModelBox body;

    public SeatModel(EntityRenderer entityRenderer) {
        this.body = new ModelBox(entityRenderer, 0, 0, -4.0F, -1.9F, -4.0F, 8, 2, 8, 0.0F, false);
    }

    @Override
    public void render(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.render(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
        this.body.render(scale);
    }
}
