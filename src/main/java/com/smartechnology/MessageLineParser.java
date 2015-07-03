package com.smartechnology;

/**
 * Created by Serkan.Tugrul on 03/07/2015.
 */
public class MessageLineParser {
    public Message parse(byte[] messageLine) throws MessageLineParseException {
        if (messageLine.length < 3) {
            throw new MessageLineParseException("message too short");
        }

        byte stx = messageLine[0];
        byte etx = messageLine[messageLine.length - 2];
        byte lrc = messageLine[messageLine.length - 1];
        return new Message(stx, etx, lrc);
    }
}
