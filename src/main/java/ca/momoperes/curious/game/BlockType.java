package ca.momoperes.curious.game;

import ca.momoperes.curious.ui.Textures;

public enum BlockType {
    GRASS(0, "Grass.jpg");

    private int id;
    private String textureName;

    BlockType(int id, String textureName) {
        this.id = id;
        this.textureName = textureName;
        Textures.load(textureName);
    }

    public String getTextureName() {
        return textureName;
    }

    public int getId() {
        return id;
    }
}
