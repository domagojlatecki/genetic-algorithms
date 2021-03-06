package at.doml.genalg.codecs;

import at.doml.genalg.codecs.abstracts.AbstractDoubleArrayToBinaryCodec;
import at.doml.genalg.testutils.TestUtilities;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.function.Function;

/**
 * Class which contains tests for <code>DoubleArrayToNaturalBinaryCodec</code> and DoubleArrayToGrayBinaryCodec.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see DoubleArrayToNaturalBinaryCodec
 * @see DoubleArrayToGrayBinaryCodec
 */
public class DoubleArrayToNaturalBinaryAndGrayBinaryCodecTest {
    
    /**
     * Specifies the number of bits per <code>double</code> value to pass to the constructor in serialization test.
     */
    private static final int BITS_PER_VALUE = 8;
    /**
     * Size of test arrays.
     */
    private static final int TEST_ARRAY_SIZE = 1_000;
    /**
     * Percentage of generated random values which will go outside of defined bounds.
     */
    private static final double OUT_OF_BOUNDS_PERC = 0.25;
    /**
     * Lower bound to use in the test. Must be less than {@link #UPPER_BOUND}.
     */
    private static final double LOWER_BOUND = -100.0;
    /**
     * Lower bound to use in the test. Must be greater than {@link #LOWER_BOUND}.
     */
    private static final double UPPER_BOUND = 100.0;
    /**
     * Input array used in the test.
     */
    private static final double[] INPUT_ARRAY = new double[TEST_ARRAY_SIZE];
    /**
     * Output array used in the test.
     */
    private static final double[] OUTPUT_ARRAY = new double[TEST_ARRAY_SIZE];
    
    static {
        double range = (UPPER_BOUND - LOWER_BOUND) * (1.0 + OUT_OF_BOUNDS_PERC);
        double modifiedBound = UPPER_BOUND * (1.0 + OUT_OF_BOUNDS_PERC);
        
        for (int i = 0; i < TEST_ARRAY_SIZE; i++) {
            INPUT_ARRAY[i] = TestUtilities.RAND.nextDouble() * range - modifiedBound;
            
            if (INPUT_ARRAY[i] <= LOWER_BOUND) {
                OUTPUT_ARRAY[i] = LOWER_BOUND;
            } else if (INPUT_ARRAY[i] >= UPPER_BOUND) {
                OUTPUT_ARRAY[i] = UPPER_BOUND;
            } else {
                OUTPUT_ARRAY[i] = INPUT_ARRAY[i];
            }
        }
    }
    
    /**
     * Tests the functionality of the natural binary codec.
     */
    @Test
    public void testNaturalBinaryCodec() {
        
        testCodec(bitsPerValue -> new DoubleArrayToNaturalBinaryCodec(bitsPerValue, LOWER_BOUND, UPPER_BOUND));
    }
    
    /**
     * Tests the functionality of the gray binary codec.
     */
    @Test
    public void testGrayBinaryCodec() {
        
        testCodec(bitsPerValue -> new DoubleArrayToGrayBinaryCodec(bitsPerValue, LOWER_BOUND, UPPER_BOUND));
    }
    
    /**
     * Tests the codec which is generated by provided function.
     * 
     * @param codecGenerator function which generates the codec. Input parameter is the number of bits per value the
     *            codec should use.
     */
    private void testCodec(Function<Integer, AbstractDoubleArrayToBinaryCodec> codecGenerator) {
        
        for (int i = AbstractDoubleArrayToBinaryCodec.getMinNumOfBitsPerValue(), bound =
                AbstractDoubleArrayToBinaryCodec.getMaxNumOfBitsPerValue(); i <= bound; i++) {
            double expectedPrecision = (UPPER_BOUND - LOWER_BOUND) / Math.pow(2.0, i);
            
            AbstractDoubleArrayToBinaryCodec a = codecGenerator.apply(i);
            TestUtilities.assertArrayElementsEqual(OUTPUT_ARRAY, a.decode(a.encode(INPUT_ARRAY)), expectedPrecision);
        }
    }
    
    /**
     * Tests the serialization for natural binary codec.
     * 
     * @throws IOException thrown if any stream is unable to read or write.
     * @throws ClassNotFoundException thrown if object in the stream cannot be deserialized.
     */
    @Test
    public void testSerializationForNaturalBinaryCodec() throws IOException, ClassNotFoundException {
        
        DoubleArrayToNaturalBinaryCodec toSend =
                new DoubleArrayToNaturalBinaryCodec(BITS_PER_VALUE, LOWER_BOUND, UPPER_BOUND);
        Object recieved = TestUtilities.serializeDeserialize(toSend);
        Assert.assertTrue(recieved instanceof DoubleArrayToNaturalBinaryCodec);
    }
    
    /**
     * Tests the serialization for gray binary codec.
     * 
     * @throws IOException thrown if any stream is unable to read or write.
     * @throws ClassNotFoundException thrown if object in the stream cannot be deserialized.
     */
    @Test
    public void testSerializationForGrayBinaryCodec() throws IOException, ClassNotFoundException {
        
        DoubleArrayToGrayBinaryCodec toSend =
                new DoubleArrayToGrayBinaryCodec(BITS_PER_VALUE, LOWER_BOUND, UPPER_BOUND);
        Object recieved = TestUtilities.serializeDeserialize(toSend);
        Assert.assertTrue(recieved instanceof DoubleArrayToGrayBinaryCodec);
    }
}
