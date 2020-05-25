package maps;

import objects.blocks.Block;
import objects.harmless.Harmless;
import objects.traps.Trap;


public class Cell {

    private Block block;
    private Harmless harmlessObject;
    private Trap trapObject;

    public Cell(Block block, Harmless harmlessObject, Trap trapObject) {
        this.block = block;
        this.harmlessObject = harmlessObject;
        this.trapObject = trapObject;
    }

    public Block getBlock() {
        return block;
    }

    public Harmless getHarmlessObject() {
        return harmlessObject;
    }

    public void setHarmlessObject(Harmless harmlessObject){
        this.harmlessObject = harmlessObject;
    }

    public Trap getTrapObject() {
        return trapObject;
    }

    public void setTrapObject(Trap trapObject){
        this.trapObject = trapObject;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
