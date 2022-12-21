package ru.itmo.coursework2.core;

import java.io.*;

public class GameSaver {
    private static final String SAVE_FILE = "save.bin";

    public void saveGame(GameState gameState) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(SAVE_FILE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(gameState);
            System.out.println("Игра сохранена\n");
        } catch (IOException e) {
            System.out.println("Не удалось сохранить игру");
        }
    }

    public GameState loadGame() {
        Object result = null;
        try (FileInputStream fileInputStream = new FileInputStream(SAVE_FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            result = objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Не удалось загрузить игру");
        }
        return (GameState) result;
    }
}

