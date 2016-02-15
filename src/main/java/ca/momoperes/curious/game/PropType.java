package ca.momoperes.curious.game;

import ca.momoperes.curious.ui.Textures;

public enum PropType {
    TREE(0, "Tree.png");

    private final String textureName;
    private final int id;

    PropType(int id, String textureName) {
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
