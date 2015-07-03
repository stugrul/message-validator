package com.smartechnology;

/**
 * Created by Serkan.Tugrul on 03/07/2015.
 */
public class ApplicationMain {
    public static void main(String[] args) throws MessageLineParseException, MalformedMessageException {
            Validator validator = new MessageValidator();

            byte[] message = {0x02, 0x10, 0x02, 0x0A, 0x10, 0x10, 0x07, 0x08, 0x03, 0x14};
            System.out.println("Message is valid: " + validator.isValid(message));
    }
}
