package ru.itmo.coursework2.commands;

import ru.itmo.coursework2.core.Game;
import ru.itmo.coursework2.core.GameParser;

public class StartNewGameCommand implements Command {
    private final Game game;
    private final GameParser gameParser;

    public StartNewGameCommand(Game game, GameParser gameParser) {
        this.game = game;
        this.gameParser = gameParser;
    }

    @Override
    public void execute() {
        game.setCurrentGameState(gameParser.parse(game.getSourceFile()));
    }
}
