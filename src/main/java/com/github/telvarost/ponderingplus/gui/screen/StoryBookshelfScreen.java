package com.github.telvarost.ponderingplus.gui.screen;

import com.github.telvarost.ponderingplus.blockentity.StoryBookshelfBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.network.packet.play.UpdateSignPacket;
import net.minecraft.util.CharacterUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

@Environment(EnvType.CLIENT)
public class StoryBookshelfScreen extends Screen {
	protected String title = "Story Bookshelf:";
	private StoryBookshelfBlockEntity storyBookshelf;
	private int ticksSinceOpened;
	private int currentRow = 0;
	private static final String VALID_CHARACTERS;

	public StoryBookshelfScreen(StoryBookshelfBlockEntity storyBookshelf) {
        this.storyBookshelf = storyBookshelf;
	}

	public void init() {
		this.buttons.clear();
		Keyboard.enableRepeatEvents(true);
		this.buttons.add(new ButtonWidget(0, this.width / 2 - 100, this.height / 4 + 120, "Done"));
	}

	public void removed() {
		Keyboard.enableRepeatEvents(false);
		if (this.minecraft.world.isRemote) {
			this.minecraft.getNetworkHandler().sendPacket(new UpdateSignPacket(this.storyBookshelf.x, this.storyBookshelf.y, this.storyBookshelf.z, this.storyBookshelf.texts));
		}

	}

	public void tick() {
		++this.ticksSinceOpened;
	}

	protected void buttonClicked(ButtonWidget button) {
		if (button.active) {
			if (button.id == 0) {
				this.storyBookshelf.markDirty();
				this.minecraft.setScreen((Screen)null);
			}

		}
	}

	protected void keyPressed(char character, int keyCode) {
		if (keyCode == 200) {
			this.currentRow = this.currentRow - 1 & 3;
		}

		if (keyCode == 208 || keyCode == 28) {
			this.currentRow = this.currentRow + 1 & 3;
		}

		if (keyCode == 14 && this.storyBookshelf.texts[this.currentRow].length() > 0) {
			this.storyBookshelf.texts[this.currentRow] = this.storyBookshelf.texts[this.currentRow].substring(0, this.storyBookshelf.texts[this.currentRow].length() - 1);
		}

		if (VALID_CHARACTERS.indexOf(character) >= 0 && this.storyBookshelf.texts[this.currentRow].length() < 15) {
			StringBuilder var10000 = new StringBuilder();
			String[] var10002 = this.storyBookshelf.texts;
			int var10004 = this.currentRow;
			var10002[var10004] = var10000.append(var10002[var10004]).append(character).toString();
		}

	}

	public void render(int mouseX, int mouseY, float delta) {
		this.renderBackground();
		this.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 40, 16777215);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)(this.width / 2), 0.0F, 50.0F);
		float var4 = 93.75F;
		GL11.glScalef(-var4, -var4, -var4);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
		Block var5 = this.storyBookshelf.getBlock();
		if (var5 == Block.SIGN) {
			float var6 = (float)(this.storyBookshelf.getPushedBlockData() * 360) / 16.0F;
			GL11.glRotatef(var6, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0.0F, -1.0625F, 0.0F);
		} else {
			int var8 = this.storyBookshelf.getPushedBlockData();
			float var7 = 0.0F;
			if (var8 == 2) {
				var7 = 180.0F;
			}

			if (var8 == 4) {
				var7 = 90.0F;
			}

			if (var8 == 5) {
				var7 = -90.0F;
			}

			GL11.glRotatef(var7, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0.0F, -1.0625F, 0.0F);
		}

		if (this.ticksSinceOpened / 6 % 2 == 0) {
			this.storyBookshelf.currentRow = this.currentRow;
		}

		BlockEntityRenderDispatcher.INSTANCE.render(this.storyBookshelf, -0.5, -0.75, -0.5, 0.0F);
		this.storyBookshelf.currentRow = -1;
		GL11.glPopMatrix();
		super.render(mouseX, mouseY, delta);
	}

	static {
		VALID_CHARACTERS = CharacterUtils.VALID_CHARACTERS;
	}
}
