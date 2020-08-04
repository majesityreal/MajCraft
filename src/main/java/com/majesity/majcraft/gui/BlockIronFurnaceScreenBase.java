package com.majesity.majcraft.gui;

import com.majesity.majcraft.MajCraft;
import com.majesity.majcraft.blocks.obsidianForge.container.BlockIronFurnaceContainerBase;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class BlockIronFurnaceScreenBase<T extends BlockIronFurnaceContainerBase> extends ContainerScreen<T> {

    public ResourceLocation GUI = new ResourceLocation(MajCraft.MOD_ID + ":" +"textures/gui/furnace.png");
    PlayerInventory playerInv;
    ITextComponent name;

    public BlockIronFurnaceScreenBase(T t, PlayerInventory inv, ITextComponent name) {
        super(t, inv, name);
        playerInv = inv;
        this.name = name;
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrix);
        super.render(matrix, mouseX, mouseY, partialTicks);
        this.func_230459_a_(matrix, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrix, int mouseX, int mouseY) {
        //drawString(Minecraft.getInstance().fontRenderer, "Energy: " + container.getEnergy(), 10, 10, 0xffffff);
        this.minecraft.fontRenderer.func_238422_b_(matrix, this.playerInv.getDisplayName(), 7, this.ySize - 93, 4210752);
        this.minecraft.fontRenderer.func_238422_b_(matrix, name, 7 + this.xSize / 2 - this.minecraft.fontRenderer.func_238414_a_(name) / 2, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrix, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(matrix, relX, relY, 0, 0, this.xSize, this.ySize);
        int i;
        if (((BlockIronFurnaceContainerBase)this.container).isBurning()) {
            i = ((BlockIronFurnaceContainerBase)this.container).getBurnScaled(13);
            this.blit(matrix, guiLeft + 56, guiTop + 36 + 12 - i, 176, 12 - i, 14, i + 1);
        }

        i = ((BlockIronFurnaceContainerBase)this.container).getCookScaled(24);
        this.blit(matrix, guiLeft + 79, guiTop + 34, 176, 14, i + 1, 16);
    }



}