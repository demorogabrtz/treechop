package ht.treechop.common.capabilities;

import ht.treechop.common.config.SneakBehavior;

public class ChopSettings {

    private boolean choppingEnabled = true;
    private boolean fellingEnabled = true;
    private SneakBehavior sneakBehavior = SneakBehavior.INVERT_CHOPPING;
    private boolean treesMustHaveLeaves = true;
    private boolean chopInCreativeMode = true;

    public ChopSettings() {}

    public boolean getChoppingEnabled() { return choppingEnabled; }
    public boolean getFellingEnabled() { return fellingEnabled; }
    public SneakBehavior getSneakBehavior() { return sneakBehavior; }
    public boolean getTreesMustHaveLeaves() { return treesMustHaveLeaves; }
    public boolean getChopInCreativeMode() { return chopInCreativeMode; }

    public void setChoppingEnabled(boolean enabled) { choppingEnabled = enabled; }
    public void setFellingEnabled(boolean enabled) { fellingEnabled = enabled; }
    public void setSneakBehavior(SneakBehavior behavior) { sneakBehavior = behavior; }
    public void setTreesMustHaveLeaves(boolean enabled) { treesMustHaveLeaves = enabled; }
    public void setChopInCreativeMode(boolean enabled) { chopInCreativeMode = enabled; }

    public void toggleChopping() {
        setChoppingEnabled(!choppingEnabled);
    }

    public void toggleFelling() {
        setFellingEnabled(!fellingEnabled);
    }

    public void cycleSneakBehavior() {
        SneakBehavior nextSneakBehavior = SneakBehavior.values()[Math.floorMod(sneakBehavior.ordinal() + 1, SneakBehavior.values().length)];
        setSneakBehavior(nextSneakBehavior);
    }

    public void copyFrom(ChopSettings oldSettings) {
        this.choppingEnabled = oldSettings.choppingEnabled;
        this.fellingEnabled = oldSettings.fellingEnabled;
        this.sneakBehavior = oldSettings.sneakBehavior;
        this.treesMustHaveLeaves = oldSettings.treesMustHaveLeaves;
        this.chopInCreativeMode = oldSettings.chopInCreativeMode;
    }

}