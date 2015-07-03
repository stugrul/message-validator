package com.smartechnology; /**
 * Created by Serkan.Tugrul on 03/07/2015.
 */

/**
 * Example implementation of {@link Validator}. Candidates may modify this class
 * or write their own implementation.
 */

public class MessageValidator implements Validator {
    int numberOfTensAtTheEndOfMessage;

    public boolean isValid(byte[] messageLine) throws MessageLineParseException, MalformedMessageException {
        MessageLineParser messageLineParser = new MessageLineParser();
        Message message = messageLineParser.parse(messageLine);

        isSTXCorrect(message);
        isETXCorrect(message);

        validateDLEBeforeEndOfText(messageLine);

        if (validateBodyOfMessage(messageLine)) return false;

        if (isLRCCorrect(messageLine) != message.getLrc())
            return false;

        return true;
    }

    private boolean validateBodyOfMessage(byte[] messageLine) {
        for (int i = 1; i < messageLine.length - 2; i++) {
            if (messageLine[i] == 0x10) {
                if (messageLine[i + 1] != 0x02 && messageLine[i + 1] != 0x03) {
                    if (messageLine[i + 1] != 0x10)
                        return true;
                    else
                        i++;
                }
            }
        }
        return false;
    }

    private void isSTXCorrect(Message message) throws MalformedMessageException {
        if (message.getStx() != 0x02) {
            throw new MalformedMessageException("STX is incorrect");
        }
    }

    private void validateDLEBeforeEndOfText(byte[] messageLine) throws MalformedMessageException {
        int lastValuesBeforeEndOfText = messageLine.length - 3;
        while (messageLine[lastValuesBeforeEndOfText] == 0x10) {
            numberOfTensAtTheEndOfMessage++;
            lastValuesBeforeEndOfText--;
        }
        if (numberOfTensAtTheEndOfMessage % 2 == 1)
            throw new MalformedMessageException("Message Format is incorrect");
    }

    public byte isLRCCorrect(byte[] messageLine) {
        byte lrc = 0;
        for (int i = 1; i < messageLine.length - 1; i++) {
            if (messageLine[i] == 0x10) {
                lrc ^= messageLine[i + 1];
                i++;
            } else
                lrc ^= messageLine[i];
        }
        return lrc;
    }

    private void isETXCorrect(Message message) throws MalformedMessageException {
        if (message.getEtx() != 0x03) {
            throw new MalformedMessageException("ETX is incorrect");
        }
    }
}
