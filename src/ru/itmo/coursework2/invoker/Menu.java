package ru.itmo.coursework2.invoker;

import ru.itmo.coursework2.commands.Command;

public class Menu {
    private final Command startNewGameCommand;
    private final Command saveGameCommand;
    private final Command loadGameCommand;
    private final Command exitCommand;

    public Menu(Command startNewGameCommand, Command saveGameCommand, Command loadGameCommand, Command exitCommand) {
        this.startNewGameCommand = startNewGameCommand;
        this.saveGameCommand = saveGameCommand;
        this.loadGameCommand = loadGameCommand;
        this.exitCommand = exitCommand;
    }

    public void startGame() {
        startNewGameCommand.execute();
    }

    public void saveGame() {
        saveGameCommand.execute();
    }

    public void loadGame() {
        loadGameCommand.execute();
    }

    public void exit() {
        exitCommand.execute();
    }
}
