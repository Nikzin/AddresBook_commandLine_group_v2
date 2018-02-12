package com.loca.addressbook.userinterface;

import com.loca.addressbook.Application;
import com.loca.addressbook.registry.Registry;
import com.loca.addressbook.remoteregistry.RemoteRegistry;
import com.loca.addressbook.userinterface.commands.Command;
import com.loca.addressbook.exceptions.InvalidCommandException;
import com.loca.addressbook.exceptions.InvalidCommandParameterException;
import com.loca.addressbook.userinterface.commands.HelpCommand;

public class CommandLineInterface implements InputHandler {


    //TODO WHY STATIC?
	private Console console;
	private CommandInterpreter commandInterpreter;

	public CommandLineInterface(Registry registry, RemoteRegistry remoteRegistry, Console console, Application application) {
	    this.console = console;
	    this.commandInterpreter = new CommandInterpreter(console, registry, remoteRegistry, application);
	    console.registerInputHandler(this);

    }

    public void start() {
        console.print("Welcome to AddressBook 2.0!");
        System.out.println("enter your command or type 'help' for help");
        console.readUserInput();
    }

    @Override
    public void handle(CommandLine commandLine) {
        try {
            Command command = commandInterpreter.interpret(commandLine);
            command.execute();
        } catch (InvalidCommandException | InvalidCommandParameterException e) {
            console.print(e.getMessage());
        }

    }
    
}
