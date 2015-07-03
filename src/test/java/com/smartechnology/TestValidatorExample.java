package com.smartechnology; /**
 * Created by Serkan.Tugrul on 03/07/2015.
 */

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestValidatorExample {
    private MessageLineParser messageLineParser = new MessageLineParser();
    private byte[] messageLine = {0x02, 0x10, 0x02, 0x0A, 0x10, 0x10, 0x07, 0x08, 0x10, 0x10, 0x10, 0x10, 0x03, 0x14};

    @Test
    public void isMessageStartsCorrectly() throws MessageLineParseException {
        Message message = messageLineParser.parse(messageLine);
        Assert.assertEquals(message.getStx(), 0x02);
    }

    @Test
    public void isMessageEndsCorrectly() throws MessageLineParseException {
        Message message = messageLineParser.parse(messageLine);
        Assert.assertEquals(message.getEtx(), 0x03);
    }

    @Test
    public void isLRCCorrect() throws MessageLineParseException, MalformedMessageException {
        MessageValidator messageValidator = new MessageValidator();
        Assert.assertEquals(messageValidator.isLRCCorrect(messageLine), messageLine[messageLine.length - 1]);
    }

    @Test
    public void testValidMessage() throws MessageLineParseException, MalformedMessageException {
        byte[] messageLine = {0x02, 0x10, 0x02, 0x0A, 0x10, 0x10, 0x07, 0x08, 0x03, 0x14};
        MessageValidator messageValidator = new MessageValidator();
        Assert.assertEquals(messageValidator.isValid(messageLine), true);
    }

    @Test
    public void testIncorrectLRC() throws MessageLineParseException, MalformedMessageException {
        byte[] messageLine = {0x02, 0x10, 0x02, 0x0A, 0x10, 0x10, 0x07, 0x08, 0x10, 0x10, 0x03, 0x15};
        MessageValidator messageValidator = new MessageValidator();
        Assert.assertEquals(messageValidator.isValid(messageLine), false);
    }

    @Test(expectedExceptions = MessageLineParseException.class,
            expectedExceptionsMessageRegExp = "message too short")
    public void shortMessagesCannotBeParsed() throws MessageLineParseException {
        byte[] shortMessage = {0x02, 0x10};
        messageLineParser.parse(shortMessage);
    }

    @Test(expectedExceptions = MalformedMessageException.class, expectedExceptionsMessageRegExp = "Message Format is incorrect")
    public void testIncorrectDLEFormat() throws MalformedMessageException, MessageLineParseException {
        byte[] messageLine = {0x02, 0x10, 0x10, 0x10, 0x03, 0x14};
        MessageValidator messageValidator = new MessageValidator();
        Assert.assertEquals(messageValidator.isValid(messageLine), false);
    }
}
