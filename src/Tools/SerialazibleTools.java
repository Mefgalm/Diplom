/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Mef
 */
public class SerialazibleTools {
    
    public static SerialazibleMessage getSerialazibleMessage(byte[] data) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(data));        
        return new SerialazibleMessage(inputStream.readInt(), inputStream.readObject());
    }
    
    public static byte[] createByteArray(int code, Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();        
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
        objectOutputStream.writeInt(code);
        objectOutputStream.writeObject(object);        
        objectOutputStream.flush();
        return baos.toByteArray();
    }
}
