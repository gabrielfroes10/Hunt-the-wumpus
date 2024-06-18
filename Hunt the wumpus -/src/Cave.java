public class Cave {
    private Cave east;
    private Cave west;
    private Cave north;
    private Cave south;
    private Enemy enemy;
    private boolean wasVisited;
    private boolean hasAnArrow;

    public Cave() {
    }

    public Cave getEast() {
        return east;
    }

    public void setEast(Cave east) {
        this.east = east;
    }

    public Cave getWest() {
        return west;
    }

    public void setWest(Cave west) {
        this.west = west;
    }

    public Cave getNorth() {
        return north;
    }

    public void setNorth(Cave north) {
        this.north = north;
    }

    public Cave getSouth() {
        return south;
    }

    public void setSouth(Cave south) {
        this.south = south;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public boolean isWasVisited() {
        return wasVisited;
    }

    public void setWasVisited(boolean wasVisited) {
        this.wasVisited = wasVisited;
    }

    public boolean isHasAnArrow() {
        return hasAnArrow;
    }

    public void setHasAnArrow(boolean hasAnArrow) {
        this.hasAnArrow = hasAnArrow;
    }
}