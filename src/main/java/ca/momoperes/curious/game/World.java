package ca.momoperes.curious.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class World {
    private Block[] blocks;
    private Random random;

    public World() {
        blocks = new Block[0];
        random = new Random();

        for (double x = -50; x <= 50; x++) {
            for (double y = -50; y <= 50; y++) {
                Block block = new Block(BlockType.GRASS, new Location(x, y));
                if (random.nextInt(10) == 1 && (!((x < -2 && x > 2)&&(y < -2 && y > 2)))) {
                    block.setProp(new BlockProp(PropType.TREE));
                    System.out.println("Spawned prop at " + x + ", " + y);
                }
                createBlock(block);
            }
        }
    }

    public void update(Block block) {
        for (int i = 0; i < blocks.length; i++) {
            Block b = blocks[i];

            if (b.getLocation().getX() != block.getLocation().getX() && b.getLocation().getY() != block.getLocation().getY()) {
                continue;
            }

            blocks[i] = block;
        }
    }

    public Block getBlockAt(Location location) {
        for (Block block : blocks) {
            Location b = block.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();

            if (location.getX() == x && location.getY() <= y) {
                return block;
            }
        }

        return null;
    }

    public void createBlock(Block block) {
        List<Block> blockList = new ArrayList<Block>();
        Collections.addAll(blockList, blocks);
        blockList.add(block);

        blocks = blockList.toArray(new Block[blockList.size()]);
    }

    public void createBlock(BlockType type, Location location) {
        createBlock(new Block(type, location));
    }

    public Block[] getBlocks() {
        return blocks;
    }
}
