package ru.itmo.coursework2.commands;

import ru.itmo.coursework2.core.Game;
import ru.itmo.coursework2.core.GameSaver;

public class SaveGameCommand implements Command {
    private final Game game;
    private final GameSaver gameSaver;

    public SaveGameCommand(Game game, GameSaver gameSaver) {
        this.game = game;
        this.gameSaver = gameSaver;
    }

    @Override
    public void execute() {
        gameSaver.saveGame(game.getCurrentGameState());
    }
}
