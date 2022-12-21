package ru.itmo.coursework2.commands;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.exit(0);
    }
}
