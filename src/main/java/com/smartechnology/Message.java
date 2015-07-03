package com.smartechnology;

/**
 * Created by Serkan.Tugrul on 03/07/2015.
 */
public class Message {
    private byte stx;
    private byte etx;
    private byte lrc;

    public Message(byte stx, byte etx, byte lrc) {
        this.stx = stx;
        this.etx = etx;
        this.lrc = lrc;
    }

    public byte getDle() {
        return (byte) 0x10;
    }

    public byte getStx() {
        return stx;
    }

    public byte getEtx() {
        return etx;
    }

    public byte getLrc() {
        return lrc;
    }

    @Override
    public String toString() {
        return "com.smartechnology.Message{" +
                "stx=" + stx +
                ", etx=" + etx +
                ", lrc=" + lrc +
                '}';
    }
}
