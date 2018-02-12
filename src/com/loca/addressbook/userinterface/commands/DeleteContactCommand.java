package com.loca.addressbook.userinterface.commands;

import com.loca.addressbook.registry.Registry;
import com.loca.addressbook.userinterface.ConsolePrinter;
import com.loca.addressbook.exceptions.InvalidCommandParameterException;

import java.util.List;

public class DeleteContactCommand implements Command {

    private CommandType commandType = CommandType.DELETE;
    private ConsolePrinter consolePrinter;
    private Registry registry;
    private List<String> parameters;

    public DeleteContactCommand(ConsolePrinter consolePrinter, Registry registry, List<String> parameters) {
        this.consolePrinter = consolePrinter;
        this.registry = registry;
        this.parameters = parameters;
    }

    @Override
    public String getName() {
        return commandType.getCommandName();
    }

    @Override
    public String getDescription() {
        return commandType.getDescription();
    }

    @Override
    public void execute() throws InvalidCommandParameterException {
        validate();
        deleteContactFromRegistry();
    }

    private void deleteContactFromRegistry() {
        String uuid = parameters.get(0);
        String message;

        boolean deleted = registry.deleteContact(uuid);
        if (deleted) {
            message = commandType.getSuccessMessage();
        } else {
            message = commandType.getFailureMessage();
        }
        consolePrinter.print(message);
    }

    private void validate() throws InvalidCommandParameterException {
        if (parameters.size() != commandType.getParametersCount()) {
            throw new InvalidCommandParameterException(commandType, parameters);
        }
    }
}
