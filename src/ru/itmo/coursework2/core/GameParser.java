package ru.itmo.coursework2.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class GameParser {
    private static final String WIN_TEXT = "<b>Игра завершилась успехом</b>";
    private static final String LOSE_TEXT = "<b>Игра завершилась неудачей</b>";
    private static final String SEPARATOR = "###";
    private static final String ARROW = " -> ";
    private static final String OPEN_TAG = "<b>";

    public GameState parse(String sourceFile) {
        List<String> strings = new ArrayList<>();
        try {
            strings = Files.readAllLines(Path.of(sourceFile));
        } catch (IOException e) {
            System.out.println("Не удалось инициализировать игру из файла");
        }
        return parseIntoGameStates(strings);
    }

    private GameState parseIntoGameStates(List<String> strings) {
        Map<String, GameState> states = new LinkedHashMap<>();
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            if (s.startsWith(SEPARATOR)) {
                int j = i + 1;
                while (j <= strings.size() - 1 && !strings.get(j).startsWith(SEPARATOR)) {
                    j++;
                }
                GameState gameState = parseIntoGameState(strings.subList(i, j));
                states.put(gameState.getTitle(), gameState);
            }
        }
        for (GameState gameState : states.values()) {
            String firstChoiceTitle = gameState.getFirstChoiceTitle();
            if (firstChoiceTitle != null) {
                gameState.setFirstChoice(states.get(extractTitle(firstChoiceTitle)));
            }
            String secondChoiceTitle = gameState.getSecondChoiceTitle();
            if (secondChoiceTitle != null) {
                gameState.setSecondChoice(states.get(extractTitle(secondChoiceTitle)));
            }
        }
        return states.values().iterator().next();
    }

    private GameState parseIntoGameState(List<String> strings) {
        GameState gameState = new GameState();
        gameState.setTitle(strings.get(0).substring(4));
        StringBuilder stringBuilder = new StringBuilder();
        int i = 2;
        for (; i < strings.size(); i++) {
            if (strings.get(i).equals("")) {
                break;
            }
            stringBuilder.append(strings.get(i));
            stringBuilder.append("\n");
        }
        String text = stringBuilder.toString();
        gameState.setText(trimTags(text));
        gameState.setState(calculateState(text));
        if (i < strings.size() - 1) {
            gameState.setFirstChoiceTitle(strings.get(i + 1).substring(3));
            gameState.setSecondChoiceTitle(strings.get(i + 2).substring(3));
        }
        return gameState;
    }

    private String trimTags(String text) {
        if (text.contains(OPEN_TAG)) {
            return text.substring(0, text.indexOf(OPEN_TAG)).trim();
        } else {
            return text;
        }
    }

    private GameState.State calculateState(String text) {
        String oneStringText = text.replaceAll("\n", " ");
        if (oneStringText.contains(WIN_TEXT)) {
            return GameState.State.WIN;
        } else if (oneStringText.contains(LOSE_TEXT)) {
            return GameState.State.LOSE;
        } else {
            return GameState.State.RUNNING;
        }
    }

    private String extractTitle(String choiceTitle) {
        if (choiceTitle.contains(ARROW)) {
            return choiceTitle.substring(choiceTitle.indexOf(ARROW) + ARROW.length());
        } else {
            return choiceTitle;
        }
    }
}
