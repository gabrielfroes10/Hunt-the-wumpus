public class BottomlessPit extends Enemy {
    private final Output output;

    public BottomlessPit() {
        this.output = new Output();
    }

    public Output getOutput() {
        return output;
    }

    @Override
    public void emitSignal() {
        this.getOutput().bottomlessPitSignal();
    }

    @Override
    public void interactWithPlayer(Player player) {
        this.getOutput().bottomlessPitInteraction();
        player.setAlive(false);
    }

}