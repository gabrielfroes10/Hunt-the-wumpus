public class Player {
    private Cave currentCave;
    private int arrows;
    private boolean isAlive;

    public Player(Cave currentCave) {
        this.currentCave = currentCave;
        this.arrows = 5;
        this.isAlive = true;
    }

    public Cave getCurrentCave() {
        return currentCave;
    }

    public void setCurrentCave(Cave currentCave) {
        this.currentCave = currentCave;
    }

    public int getArrows() {
        return arrows;
    }

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}