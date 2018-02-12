package com.loca.addressbook.userinterface;

import java.util.Scanner;

public class Console implements ConsolePrinter {

	private InputHandler inputHandler;
	private boolean isQuit = false;
 
    public void registerInputHandler(InputHandler inputHandler) {
    	this.inputHandler = inputHandler;
    }
 
    public void readUserInput() {
        Scanner scanner = new Scanner(System.in);
		while (!isQuit) {
			String userInput = scanner.nextLine();
			CommandLine commandLine = CommandLine.parse(userInput);
            inputHandler.handle(commandLine);
            System.out.println("enter your command or type 'help' for help");
		}
		scanner.close();
	}

    @Override
    public void print(String output) {
        System.out.println(output);
    }

    public void close() {
        isQuit = true;
    }

}
