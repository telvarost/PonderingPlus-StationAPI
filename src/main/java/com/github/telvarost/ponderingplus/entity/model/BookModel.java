package com.github.telvarost.ponderingplus.entity.model;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;

public class BookModel extends EntityModel {
    public ModelBox body;

    public BookModel(EntityRenderer entityRenderer) {
        this.body = new ModelBox(entityRenderer, 0, 0, -4.0F, -2.9F, -5.0F, 8, 3, 10, 0.0F, false);
    }

    @Override
    public void render(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.render(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
        this.body.render(scale);
    }
}
