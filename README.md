# Hunt the Wumpus Game

Bem-vindo ao "Hunt the Wumpus", um jogo de aventura clássico onde os jogadores navegam por um sistema de cavernas para caçar o elusivo Wumpus enquanto evitam vários perigos. Este README irá guiá-lo na compreensão da mecânica do jogo e como configurar e jogar o jogo usando as classes Java fornecidas.
## Visão Geral
O jogo consiste em uma grade 5x5 de cavernas interconectadas. Cada caverna pode conter o seguinte:
* O Wumpus (o principal inimigo)
* Morcegos
* Poços sem fundo
* Flechas
O objetivo do jogador é caçar e derrotar o Wumpus sem cair em um poço ou ser pego pelos morcegos. Os jogadores podem coletar flechas e usá-las para tentar matar o Wumpus.
## Classes
### Game
A classe Game é o núcleo da aplicação. Ela lida com a inicialização do sistema de cavernas, do jogador e dos inimigos. Também gerencia o fluxo do jogo, incluindo o movimento do jogador, as interações com os inimigos e a verificação do estado do jogo.
#### Principais Métodos
* startGame(): Inicializa o jogo gerando as cavernas, colocando inimigos e flechas, e posicionando o jogador.
* movePlayer(Directions direction): Move o jogador na direção especificada e lida com quaisquer interações com inimigos ou flechas.
* shootArrow(Directions direction): Permite ao jogador disparar uma flecha na direção especificada para tentar matar o Wumpus.
* isGameOver(): Verifica se o jogo acabou com base no status do jogador, no status do Wumpus e no número de flechas restantes.
* getEnemySignals(): Emite sinais se houver inimigos próximos para fornecer dicas ao jogador.
* printCaves(): Imprime o estado atual do sistema de cavernas, indicando a posição do jogador.
* printTotalPlayerArrows(): Mostra o número total de flechas que o jogador possui. 
### Player 
A classe Player representa o jogador no jogo. Ela mantém o controle da posição atual do jogador, do número de flechas e do status de vida.
#### Principais Métodos
* Player(Cave initialCave): Inicializa o jogador na caverna especificada.
* setCurrentCave(Cave cave): Atualiza a caverna atual do jogador.
* getArrows(): Retorna o número de flechas que o jogador possui.
* setArrows(int arrows): Define o número de flechas que o jogador possui.
### Cave 
A classe Cave representa uma caverna individual na grade. Ela mantém o controle das cavernas vizinhas, de quaisquer inimigos presentes e se a caverna possui uma flecha ou foi visitada.
#### Principais Métodos
* setNorth(Cave cave), setSouth(Cave cave), setEast(Cave cave), setWest(Cave cave): Define as cavernas vizinhas ao norte, sul, leste e oeste, respectivamente.
* getNorth(), getSouth(), getEast(), getWest(): Obtêm as cavernas vizinhas nas direções correspondentes.
* setEnemy(Enemy enemy): Define o inimigo presente na caverna.
* getEnemy(): Retorna o inimigo presente na caverna.
* setHasAnArrow(boolean hasAnArrow): Define se a caverna possui uma flecha.
* isHasAnArrow(): Verifica se a caverna possui uma flecha.
* setWasVisited(boolean wasVisited): Define se a caverna foi visitada.
* isWasVisited(): Verifica se a caverna foi visitada.
## Configuracao e execucao do jogo
1.Inicialização: Através do método startGame() da classe Game, o sistema de cavernas é gerado, os inimigos e flechas são posicionados aleatoriamente, e o jogador é colocado em uma caverna segura.

2.Movimentação: Utilize o método movePlayer(Directions direction) para mover o jogador na direção desejada. O jogo lida automaticamente com as interações com inimigos e coleta de flechas.

3.Disparo de Flechas: Use o método shootArrow(Directions direction) para disparar uma flecha na direção especificada.

4.Verificação de Status: O método isGameOver() verifica se o jogo terminou. Ele considera se o jogador ainda está vivo, se o Wumpus foi derrotado e se restam flechas disponíveis.

5.Dicas de Enemies: O método getEnemySignals() emite sinais indicando a proximidade de inimigos.

6.Impressão do Estado do Jogo: Use printCaves() para visualizar o estado atual da grade de cavernas e printTotalPlayerArrows() para mostrar o total de flechas do jogador.

Com essas informações, você está pronto para iniciar sua jornada em "Hunt the Wumpus"! Boa sorte na caça!
