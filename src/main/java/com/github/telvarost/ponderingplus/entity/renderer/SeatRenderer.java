package com.github.telvarost.ponderingplus.entity.renderer;

import com.github.telvarost.ponderingplus.entity.model.SeatModel;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class SeatRenderer extends EntityRenderer {
    public EntityModel model;

    public SeatRenderer() {
        this.shadowRadius = 0.45F;
        this.model = new SeatModel(this);
    }

    @Override
    public void render(Entity entity, double x, double y, double z, float g, float h) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)z);
        GL11.glRotatef(180.0F - g, 0.0f, 1.0f, 0.0f);
        this.bindTexture("/assets/ponderingplus/stationapi/textures/entity/seat_skin.png");
        GL11.glScalef(-1.0f, -1.0f, 1.0f);
        GL11.glRotatef(180.0F, 0.0f, 1.0f, 0.0f);
        this.model.render(0.0F,0.5F,0.0F,0.0F,0.0F,0.0625F);
        GL11.glPopMatrix();
    }
}