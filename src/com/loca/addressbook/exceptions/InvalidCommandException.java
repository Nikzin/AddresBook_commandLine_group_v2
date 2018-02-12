package com.loca.addressbook.exceptions;

public class InvalidCommandException extends Exception {


    public InvalidCommandException (String command) {
        super("Command Line failed! Input command " + "\"" + command + "\"" + " is not a valid command type.");
    }

}
