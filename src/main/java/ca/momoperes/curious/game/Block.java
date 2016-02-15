package ca.momoperes.curious.game;

public class Block {

    private BlockType type;
    private Location location;
    private BlockProp prop;
    private int light = 0;

    public Block(BlockType type, Location location) {
        this.type = type;
        this.location = location;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public BlockType getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }

    public void setProp(BlockProp prop) {
        this.prop = prop;
    }

    public BlockProp getProp() {
        return prop;
    }
}
