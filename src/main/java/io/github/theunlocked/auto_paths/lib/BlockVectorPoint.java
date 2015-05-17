package io.github.theunlocked.auto_paths.lib;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BlockVectorPoint {
	public World world;
	public int x;
	public int y;
	public int z;
	public BlockVectorPoint (World world, int x, int y, int z){
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void setBlock (BlockVectorPoint bvp, Block block){
		bvp.world.setBlock(bvp.x, bvp.y, bvp.z, block);
	}
	
	public Block getBlock (BlockVectorPoint bvp){
		return bvp.world.getBlock(bvp.x, bvp.y, bvp.z);
	}
	
	public Block getSouthBlock (BlockVectorPoint bvp){
		return bvp.world.getBlock(bvp.x, bvp.y, bvp.z + 1);
	}
	
	public Block getNorthBlock (BlockVectorPoint bvp){
		return bvp.world.getBlock(bvp.x, bvp.y, bvp.z - 1);
	}
	
	public Block getEastBlock (BlockVectorPoint bvp){
		return bvp.world.getBlock(bvp.x + 1, bvp.y, bvp.z);
	}
	
	public Block getWestBlock (BlockVectorPoint bvp){
		return bvp.world.getBlock(bvp.x - 1, bvp.y, bvp.z);
	}
	
	public Block getUpBlock (BlockVectorPoint bvp){
		return bvp.world.getBlock(bvp.x, bvp.y + 1, bvp.z);
	}
	
	public Block getDownBlock (BlockVectorPoint bvp){
		return bvp.world.getBlock(bvp.x, bvp.y - 1, bvp.z);
	}
	
	public BlockVectorPoint getNorth (BlockVectorPoint bvp){
		return new BlockVectorPoint(bvp.world, bvp.x, bvp.y, bvp.z - 1);
	}
	
	public BlockVectorPoint getSouth (BlockVectorPoint bvp){
		return new BlockVectorPoint(bvp.world, bvp.x, bvp.y, bvp.z + 1);
	}
	
	public BlockVectorPoint getEast (BlockVectorPoint bvp){
		return new BlockVectorPoint(bvp.world, bvp.x + 1, bvp.y, bvp.z);
	}
	
	public BlockVectorPoint getWest (BlockVectorPoint bvp){
		return new BlockVectorPoint(bvp.world, bvp.x - 1, bvp.y, bvp.z);
	}
	
	public BlockVectorPoint getUp (BlockVectorPoint bvp){
		return new BlockVectorPoint(bvp.world, bvp.x, bvp.y + 1, bvp.z);
	}
	
	public BlockVectorPoint getDown (BlockVectorPoint bvp){
		return new BlockVectorPoint(bvp.world, bvp.x, bvp.y - 1, bvp.z);
	}
	
	public boolean equals(BlockVectorPoint bvp){
		return (x == bvp.x)&&(y == bvp.y)&&(z == bvp.z)&&(world == bvp.world);
	}
	
	public BlockVectorPoint Copy(BlockVectorPoint bvp){
		return new BlockVectorPoint(bvp.world, bvp.x, bvp.y, bvp.z);
	}
	
}