package com.loca.addressbook.userinterface;

import com.loca.addressbook.Application;
import com.loca.addressbook.registry.Registry;
import com.loca.addressbook.remoteregistry.RemoteRegistry;
import com.loca.addressbook.userinterface.commands.*;
import com.loca.addressbook.exceptions.InvalidCommandException;

import java.util.List;

public class CommandInterpreter {

    private Registry registry;
    private RemoteRegistry remoteRegistry;
    private ConsolePrinter consolePrinter;
    private Application application;

    public CommandInterpreter(ConsolePrinter consolePrinter, Registry registry, RemoteRegistry remoteRegistry, Application application) {
        this.registry = registry;
        this.remoteRegistry = remoteRegistry;
        this.consolePrinter = consolePrinter;
        this.application = application;
    }

    public Command interpret(CommandLine commandLine) throws InvalidCommandException {
        CommandType commandType = findCommandType(commandLine.getCommand());
        return createCommand(commandType, commandLine.getParameters());
	}

    private CommandType findCommandType(String command) throws InvalidCommandException {
	    CommandType inputCommandType = null;
	    for (CommandType commandType: CommandType.values()) {
	        if (commandType.getCommandName().equals(command)) {
	            inputCommandType = commandType;
	            break;
            }
        }
        if (inputCommandType == null) {
            throw new InvalidCommandException(command);
        }
        return inputCommandType;
    }

    private Command createCommand(CommandType commandType, List<String> parameters) {
	    Command command = null;
	    switch (commandType) {
            case ADD:
                command = new AddContactCommand(consolePrinter, registry, parameters);
                break;
            case LIST:
                command = new ListCommand(consolePrinter, registry, remoteRegistry, parameters);
                break;
            case DELETE:
                command = new DeleteContactCommand(consolePrinter, registry, parameters);
                break;
            case HELP:
                command = new HelpCommand(consolePrinter, parameters);
                break;
            case SEARCH:
                command = new SearchCommand(consolePrinter, registry, remoteRegistry, parameters);
                break;
            case QUIT:
                command = new QuitCommand(consolePrinter, parameters, application);
                break;
        }

        return command;
    }


}
