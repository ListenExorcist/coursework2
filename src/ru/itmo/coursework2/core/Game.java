package ru.itmo.coursework2.core;

import ru.itmo.coursework2.commands.*;
import ru.itmo.coursework2.invoker.Menu;

import java.util.Scanner;

import static ru.itmo.coursework2.core.GameState.State.*;

public class Game {
    private static final String SOURCE_FILE = "resources/текст-параграфов.md";
    private static final String START_OPTIONS = """
            Введите номер пункта для выбора
                        
                        
            1. Начать игру
            2. Загрузить игру
            3. Выйти
                        
                        
            Введите 0 для вызова меню во время игры
                        
            """;
    private static final String MID_GAME_OPTIONS = """
            Введите номер пункта для выбора
            
            
            1. Начать игру
            2. Сохранить игру
            3. Загрузить игру
            4. Выйти
            
            
            Введите 0 для вызова меню во время игры
            
                 
            """;
    private static final String WIN_STATEMENT = "\nИгра завершилась успехом";
    private static final String LOSE_STATEMENT = "\nИгра завершилась неудачей";
    private static final String INVALID_INPUT = "Неверный ввод";
    private GameState currentGameState;
    private Menu menu;

    public static void main(String[] args) {
        Game game = new Game();
        GameParser gameParser = new GameParser();
        GameSaver gameSaver = new GameSaver();
        StartNewGameCommand startNewGameCommand = new StartNewGameCommand(game, gameParser);
        SaveGameCommand saveGameCommand = new SaveGameCommand(game, gameSaver);
        LoadGameCommand loadGameCommand = new LoadGameCommand(game, gameSaver);
        ExitCommand exitCommand = new ExitCommand();
        game.menu = new Menu(startNewGameCommand, saveGameCommand, loadGameCommand, exitCommand);
        System.out.println(START_OPTIONS);
        game.selectStartOption();
        while (true) {
            if (game.currentGameState == null) {
                game.selectStartOption();
                continue;
            }
            game.displayCurrentGameState();
            switch (game.currentGameState.getState()) {
                case WIN -> {
                    System.out.println(WIN_STATEMENT);
                    return;
                }
                case LOSE -> {
                    System.out.println(LOSE_STATEMENT);
                    return;
                }
                case RUNNING -> game.makePlayerChoice();
            }
        }
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }

    public String getSourceFile() {
        return SOURCE_FILE;
    }

    private void displayCurrentGameState() {
        System.out.println(currentGameState.getText());
        if (currentGameState.getFirstChoice() == null) {
            return;
        }
        System.out.println("1. " + currentGameState.getFirstChoiceTitle());
        System.out.println("2. " + currentGameState.getSecondChoiceTitle());
    }

    private void makePlayerChoice() {
        while (true) {
            String input = receiveInput();
            switch (input) {
                case "1" -> {
                    currentGameState = currentGameState.getFirstChoice();
                    return;
                }
                case "2" -> {
                    currentGameState = currentGameState.getSecondChoice();
                    return;
                }
                case "0" -> {
                    System.out.println(MID_GAME_OPTIONS);
                    selectMidGameOption();
                    return;
                }
                default -> System.out.println(INVALID_INPUT);
            }
        }
    }

    private void selectMidGameOption() {
        while (true) {
            String input = receiveInput();
            switch (input) {
                case "1" -> {
                    menu.startGame();
                    return;
                }
                case "2" -> {
                    menu.saveGame();
                    return;
                }
                case "3" -> {
                    menu.loadGame();
                    return;
                }
                case "4" -> menu.exit();
                default -> System.out.println(INVALID_INPUT);
            }
        }
    }

    private void selectStartOption() {
        while (true) {
            String input = receiveInput();
            switch (input) {
                case "1" -> {
                    menu.startGame();
                    return;
                }
                case "2" -> {
                    menu.loadGame();
                    return;
                }
                case "3" -> menu.exit();
                default -> System.out.println(INVALID_INPUT);
            }
        }
    }

    private String receiveInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
