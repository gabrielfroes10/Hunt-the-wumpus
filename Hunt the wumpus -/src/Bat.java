public class Bat extends Enemy {

    private final Output output;

    public Bat() {
        this.output = new Output();
    }

    public Output getOutput() {
        return output;
    }

    @Override
    public void emitSignal() {
        this.getOutput().batSignal();
    }

    @Override
    public void interactWithPlayer(Player player) {
        this.getOutput().batInteraction();
        player.setAlive(true);
    }
}