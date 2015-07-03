package com.smartechnology; /**
 * Created by Serkan.Tugrul on 03/07/2015.
 */

/**
 * Implementations are capable of verifying whether a specific message is valid.
 */
public interface Validator {

    /**
     * Indicate if the given message is valid.
     *
     * @param message The candidate message to check
     * @return {@code true} if the message starts with STX, ends with ETX and the
     *         correct LRC, and has correctly-escaped data; {@code false}
     *         otherwise.
     */
    boolean isValid(byte[] message) throws MessageLineParseException, MalformedMessageException;

}
