public class Wumpus extends Enemy {
    private final Output output;

    public Wumpus() {
        this.output = new Output();
    }

    public Output getOutput() {
        return output;
    }

    @Override
    public void emitSignal() {
        this.getOutput().wumpusSignal();
    }

    @Override
    public void interactWithPlayer(Player player) {
        this.getOutput().wumpusInteraction();
        player.setAlive(false);
    }

}