public class Output {
    public void print(String message) {
        System.out.print(message);
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void wumpusSignal() {
        this.println("Você consegue sentir um odor.");
    }

    public void batSignal() {
        this.println("Você consegue ouvir o bater de asas.");
    }

    public void bottomlessPitSignal() {
        this.println("Você consegue sentir uma brisa.");
    }

    public void wumpusInteraction() {
        this.println("Você foi devorado pelo Wumpus.");
        this.println("");
    }

    public void batInteraction() {
        this.println("Você foi levado para outra caverna por morcegos.");
        this.println("");
    }

    public void bottomlessPitInteraction() {
        this.println("Você caiu em um poço sem fundo.");
        this.println("");
    }

    public void invalidDirection() {
        this.println("Não tem cavernas nessa direção, tente novamente!");
        this.println("");
    }

    public void arrowCollected() {
        this.println("Você encontrou uma flecha.");
        this.println("");
    }

    public void arrowMissed() {
        this.println("Você errou a flechada.");
        this.println("");
    }

    public void missingArrows() {
        this.println("Você morreu, acabaram suas flechas!");
        this.println("");
    }

    public void showWelcomeMessage() {
        this.println("Bem-vindo ao Hunt the Wumpus!");
        this.println("");
    }

    public void showMenuOptions() {
        this.println("1. Começar um novo jogo!");
        this.println("2. Regras!");
        this.println("3. Sair!");
        this.showChooseOption();
    }

    public void showChooseOption() {
        this.print("Escolha uma opção: ");
    }

    public void showStartingGame() {
        this.println("Começando um novo jogo...");
        this.println("");
    }

    public void showInvalidOption() {
        this.println("Opção inválida. Por favor, tente novamente.");
        this.println("");
    }

    public void showBye() {
        this.println("Até mais! :)");
    }

    public void showRules() {
        this.println("");
        this.println("Regras - Hunt the Wumpus.");
        this.println("");
        this.println("""
                1. Existem três perigos! Sendo estes:
                 - Um poço sem fundo (Você sentirá uma brisa por perto).
                 - Uma colônia de morcegos que irá pegá-lo e deixá-lo em uma caverna aleatória (Você consegue ouvir o bater de asas).
                 - Um wumpus temível, faminto e sem banho (Você consegue sentir um odor).""");
        this.println("2. Você pode encontrar uma flecha lançada por um caçador anterior.");
        this.println("3. O jogo acaba quando você não tiver mais flechas ou se cair em um poço sem fundo ou se você for devorado pelo Wumpus.");
        this.println("4. O 'X' no mapa representa a sua localização atual.");
        this.println("5. Sobreviva! Você tem 5 flechas e 3 flechas espalhadas entre as 25 cavernas disponíveis para serem exploradas.");
        this.println("");
    }

    public void showOptionsToPlay() {
        this.println("1. Mover para outra caverna.");
        this.println("2. Atirar uma flecha.");
        this.showChooseOption();
    }

    public void showDirections() {
        this.println("1. Norte");
        this.println("2. Sul");
        this.println("3. Leste");
        this.println("4. Oeste");
        this.showChooseOption();
    }

    public void wumpusDefeated() {
        this.println("Você derrotou o Wumpus!");
        this.println("");
    }

    public void showTotalPlayerArrows(int arrows) {
        if(arrows != 0) {
            this.println("No momento você possui: " + arrows + " flechas!");
            this.println("");
        } else {
            this.println("Você não tem mais flechas.");
            this.println("");
        }
    }
}
