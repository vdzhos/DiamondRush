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
}