package com.example.rabbitmq.producer;

import java.io.*;

public class MessageParser {
    public static byte[] serializeObjectToByteArray(Object obj) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(obj);
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
            //   logger.error("serializeObjectToByteArray Failure", e);

        }
    }
    public static Object deSerializeObjectFromByteArray(byte[] data) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(in);
            return (Object) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            //  LOGGER.error("deSerializeObjectFromByteArray Failure", e);
            throw new RuntimeException("deSerializeObjectFromByteArray", e);
        }
    }
}
