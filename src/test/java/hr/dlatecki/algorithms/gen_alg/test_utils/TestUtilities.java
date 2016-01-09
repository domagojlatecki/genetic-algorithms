package hr.dlatecki.algorithms.gen_alg.test_utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * Class which contains various methods and constants for testing.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 */
public class TestUtilities {
    
    /**
     * Precision to use in tests when comparing double numbers.
     */
    public static final double PRECISION = 10E-5;
    /**
     * <code>Random</code> object used in tests.
     */
    public static final Random RAND = new Random();
    
    /**
     * Serializes and deserializes an object. The returned object should have same values as the sent object.
     * 
     * @param object object to send through serialization/deserialization process.
     * @return Object deserialized from serialized version of input object.
     * @throws IOException thrown if any stream is unable to read or write.
     * @throws ClassNotFoundException thrown if object in the stream cannot be deserialized.
     */
    public static Object serializeDeserialize(Object object) throws IOException, ClassNotFoundException {
        
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(byteOutput));
        
        out.writeObject(object);
        out.flush();
        
        byte[] outputBytes = byteOutput.toByteArray();
        
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(outputBytes)));
        
        return in.readObject();
    }
}