import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Game {
    private Cave[][] caves;
    private Player player;
    private boolean isWumpusAlive;
    private final Output output;

    public Game() {
        this.output = new Output();
    }

    public Output getOutput() {
        return output;
    }

    public Cave[][] getCaves() {
        return caves;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isWumpusAlive() {
        return isWumpusAlive;
    }

    public ArrayList<Cave> getAllCaves() {
        ArrayList<Cave> allCaves = new ArrayList<>();
        for (Cave[] caveRow : this.getCaves()) {
            allCaves.addAll(Arrays.asList(caveRow));
        }

        if(getPlayer() != null && getPlayer().getCurrentCave() != null) {
            allCaves.remove(player.getCurrentCave());
        }

        return allCaves;
    }

    private void generateCaves() {
        Cave[][] caves = new Cave[5][5];

        for (int i = 0; i < caves.length; i++) {
            for (int j = 0; j < caves[0].length; j++) {
                caves[i][j] = new Cave();
            }
        }

        for (int i = 0; i < caves.length; i++) {
            for (int j = 0; j < caves[0].length; j++) {

                if (j < caves[0].length - 1) {
                    caves[i][j].setEast(caves[i][j + 1]);
                    caves[i][j + 1].setWest(caves[i][j]);
                }

                if (i < caves[0].length -1) {
                    caves[i][j].setSouth(caves[i + 1][j]);
                    caves[i + 1][j].setNorth(caves[i][j]);
                }
            }
        }

        this.caves = caves;
    }

    private void generateEnemiesAndArrows() {
        Wumpus wumpus = new Wumpus();

        int enemiesLength = 3;
        int arrows = 3;

        Bat[] bats = new Bat[enemiesLength];
        BottomlessPit[] bottomlessPits = new BottomlessPit[enemiesLength];

        for(int i = 0; i < enemiesLength; i++) {
            bats[i] = new Bat();
            bottomlessPits[i] = new BottomlessPit();
        }

        spreadEnemiesAndArrows(wumpus, bats, bottomlessPits, arrows);
    }

    private void spreadEnemiesAndArrows(Wumpus wumpus, Bat[] bats, BottomlessPit[] bottomlessPits, Integer arrows) {
        ArrayList<Cave> allCaves = this.getAllCaves();

        Random random = new Random();

        int wumpusIndex = random.nextInt(allCaves.size());

        while (allCaves.get(wumpusIndex).getEnemy() != null) {
            wumpusIndex = random.nextInt(allCaves.size());
        }

        allCaves.get(wumpusIndex).setEnemy(wumpus);
        this.isWumpusAlive = true;
        allCaves.remove(wumpusIndex);

        for (Bat bat : bats) {
            int batIndex = random.nextInt(allCaves.size());
            while(allCaves.get(batIndex).getEnemy() != null) {
                batIndex = random.nextInt(allCaves.size());
            }
            allCaves.get(batIndex).setEnemy(bat);
            allCaves.remove(batIndex);
        }

        for (BottomlessPit bottomlessPit : bottomlessPits) {
            int pitIndex = random.nextInt(allCaves.size());
            while(allCaves.get(pitIndex).getEnemy() != null) {
                pitIndex = random.nextInt(allCaves.size());
            }
            allCaves.get(pitIndex).setEnemy(bottomlessPit);
            allCaves.remove(pitIndex);
        }

        for(int i = 0; i < arrows; i++) {
            int arrowIndex = random.nextInt(allCaves.size());
            while(allCaves.get(arrowIndex).getEnemy() != null) {
                arrowIndex = random.nextInt(allCaves.size());
            }
            allCaves.get(arrowIndex).setHasAnArrow(true);
            allCaves.remove(arrowIndex);
        }

    }

    private void createAndPositionPlayer() {
        ArrayList<Cave> safeCaves = this.findUnvisitedSafeCaves();

        Random random = new Random();
        int randomIndex = random.nextInt(safeCaves.size());
        Cave safeCave = safeCaves.get(randomIndex);
        this.player = new Player(safeCave);
    }

    public void startGame() {
        this.generateCaves();
        this.generateEnemiesAndArrows();
        this.createAndPositionPlayer();
    }

    private int getTotalArrows() {
        ArrayList<Cave> allCaves = this.getAllCaves();

        int arrowsInTheCaves = 0;

        for(Cave cave : allCaves) {
            if(cave.isHasAnArrow()) {
                arrowsInTheCaves++;
            }
        }

        return this.getPlayer().getArrows() + arrowsInTheCaves;
    }

    public boolean isGameOver() {
        return !this.getPlayer().isAlive() || !this.isWumpusAlive() || this.getTotalArrows() == 0;
    }

    public void getGameOverMessage() {
        if(this.getTotalArrows() == 0) {
            this.getOutput().missingArrows();
        }
    }

    private Cave getDestinationCave(Cave currentCave, Directions direction) {
        return switch (direction) {
            case NORTH -> currentCave.getNorth();
            case SOUTH -> currentCave.getSouth();
            case EAST -> currentCave.getEast();
            case WEST -> currentCave.getWest();
        };
    }

    private void handleEnemyInteraction(Player player, Cave destinationCave) {
        if(destinationCave.getEnemy() instanceof Bat) {
            ArrayList<Cave> safeCaves = this.findUnvisitedSafeCaves();

            if (!safeCaves.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(safeCaves.size());
                Cave safeCave = safeCaves.get(randomIndex);

                this.getPlayer().setCurrentCave(safeCave);
            }
        }

        destinationCave.getEnemy().interactWithPlayer(player);
    }

    private ArrayList<Cave> findUnvisitedSafeCaves() {
        ArrayList<Cave> allCaves = this.getAllCaves();

        ArrayList<Cave> safeCaves = new ArrayList<>();

        for(Cave cave : allCaves) {
            if(cave.getEnemy() == null && !cave.isWasVisited()) {
                safeCaves.add(cave);
            }
        }

        return safeCaves;
    }

    private void handleArrowCollection(Player player, Cave destinationCave) {
        if(destinationCave.isHasAnArrow()) {
            player.setArrows(player.getArrows() + 1);
            destinationCave.setHasAnArrow(false);
            this.getOutput().arrowCollected();
        }
    }

    public void movePlayer(Directions direction) {
        Player player = this.getPlayer();
        Cave currentCave = player.getCurrentCave();

        Cave destinationCave = getDestinationCave(currentCave, direction);

        if(destinationCave != null) {
            if(destinationCave.getEnemy() != null) {
                handleEnemyInteraction(player, destinationCave);
            } else {
                handleArrowCollection(player, destinationCave);
                destinationCave.setWasVisited(true);
                player.setCurrentCave(destinationCave);
            }
        } else {
            this.getOutput().invalidDirection();
        }
    }

    public void shootArrow(Directions direction) {
        Player player = this.getPlayer();
        Cave currentCave = player.getCurrentCave();

        Cave destinationCave = getDestinationCave(currentCave, direction);

        if(destinationCave != null) {
            if(player.getArrows() != 0) {
                if(destinationCave.getEnemy() == null) {
                    player.setArrows(player.getArrows() - 1);
                    this.getOutput().arrowMissed();
                } else if(destinationCave.getEnemy() instanceof Wumpus) {
                    this.isWumpusAlive = false;
                    this.getOutput().wumpusDefeated();
                } else {
                    player.setArrows(player.getArrows() - 1);
                    this.getOutput().arrowMissed();
                }
            }
        } else {
            this.getOutput().invalidDirection();
        }
    }

    private void checkAndEmitSignal(Cave cave) {
        if (cave != null) {
            if (cave.getEnemy() != null) {
                cave.getEnemy().emitSignal();
            }

            Cave westCave = cave.getWest();
            if (westCave != null && westCave.getEnemy() != null) {
                westCave.getEnemy().emitSignal();
            }
        }
    }

    public void getEnemySignals() {
        Cave currentPlayerCave = this.getPlayer().getCurrentCave();

        this.checkAndEmitSignal(currentPlayerCave.getNorth());
        this.checkAndEmitSignal(currentPlayerCave.getSouth());
        this.checkAndEmitSignal(currentPlayerCave.getEast());
        this.checkAndEmitSignal(currentPlayerCave.getWest());
        this.getOutput().println("");
    }

    public void moveBats() {
        ArrayList<Cave> safeCaves = this.findUnvisitedSafeCaves();

        Random random = new Random();
        for (Cave cave : this.getAllCaves()) {
            if (cave.getEnemy() instanceof Bat && !safeCaves.isEmpty()) {
                int randomIndex = random.nextInt(safeCaves.size());
                Cave safeCave = safeCaves.get(randomIndex);
                safeCaves.remove(randomIndex);
                safeCave.setEnemy(cave.getEnemy());
                cave.setEnemy(null);
            }
        }
    }

    public void printCaves() {
        Cave[][] caves = this.getCaves();

        for (Cave[] cave : caves) {
            for (int j = 0; j < caves[0].length; j++) {
                if (cave[j] == this.getPlayer().getCurrentCave()) {
                    getOutput().print("[X] ");
                } else {
                    getOutput().print("[ ] ");
                }
            }
            getOutput().println("");
        }

        getOutput().println("");
    }

    public void printTotalPlayerArrows() {
        this.getOutput().showTotalPlayerArrows(this.getPlayer().getArrows());
    }
}
