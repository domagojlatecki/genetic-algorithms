package at.doml.genalg.testutils;

import org.junit.Assert;
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
     * Default precision to use in tests when comparing <code>double</code> numbers.
     */
    public static final double PRECISION = 10E-5;
    /**
     * <code>Random</code> object used in tests.
     */
    public static final Random RAND = new Random();
    
    /**
     * Empty protected constructor - this class only has static methods and fields.
     */
    protected TestUtilities() {}
    
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
    
    /**
     * Tests if two arrays of <code>double</code>s have equal elements. Default precision is used.
     * 
     * @param expected array of expected values.
     * @param actual actual values.
     * @param precision precision to use when comparing <code>double</code>s.
     */
    public static void assertArrayElementsEqual(double[] expected, double[] actual, double precision) {
        
        Assert.assertEquals(expected.length, actual.length);
        
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], actual[i], precision);
        }
    }
    
    /**
     * Tests if two arrays of <code>double</code>s have equal elements. Default precision is used.
     * 
     * @param expected array of expected values.
     * @param actual actual values.
     */
    public static void assertArrayElementsEqual(double[] expected, double[] actual) {
        
        assertArrayElementsEqual(expected, actual, PRECISION);
    }
    
    /**
     * Tests if two arrays of <code>byte</code>s have equal elements.
     * 
     * @param expected array of expected values.
     * @param actual actual values.
     */
    public static void assertArrayElementsEqual(byte[] expected, byte[] actual) {
        
        Assert.assertEquals(expected.length, actual.length);
        
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }
    
    /**
     * Tests if two arrays of <code>int</code>s have equal elements.
     * 
     * @param expected array of expected values.
     * @param actual actual values.
     */
    public static void assertArrayElementsEqual(int[] expected, int[] actual) {
        
        Assert.assertEquals(expected.length, actual.length);
        
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }
}
