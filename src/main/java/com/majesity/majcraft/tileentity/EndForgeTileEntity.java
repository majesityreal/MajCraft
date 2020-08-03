package com.majesity.majcraft.tileentity;

import com.majesity.majcraft.blocks.EndForge.EndForge;
import com.majesity.majcraft.init.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class EndForgeTileEntity extends TileEntity implements ITickableTileEntity {

    public int x,y,z,tick;
    boolean initialized = false;

    public EndForgeTileEntity(final TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public EndForgeTileEntity() {
        this(ModTileEntityTypes.END_FORGE.get());
    }

    @Override
    public void tick() {
        if (!initialized) {
            init();
        }
        tick++;
        if(tick==40) {
            tick = 0;
            if(y>2) {
                execute();
            }
        }
    }
// this does the functionality???
    private void execute() {
        int index = 0;
        Block[] blocksRemoved = new Block[3];
        for(int x = 0;x<3;x++) {
            for(int z=0;z<3;z++) {
                BlockPos posToBreak = new BlockPos(this.x+x,this.y,this.z+z);
                blocksRemoved[index] = this.world.getBlocksState(posToBreak).getBlock();
                destroyBlock(posToBreak,true,null);
                index++;
            }
        }
    }

}
