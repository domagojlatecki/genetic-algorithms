package at.doml.genalg.population.chromosomes;

import at.doml.genalg.population.abstracts.AbstractByteArrayChromosome;
import at.doml.genalg.population.abstracts.AbstractChromosome;

/**
 * An implementation of the <code>AbstractByteArrayChromosome</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractByteArrayChromosome
 */
public final class ByteArrayChromosome extends AbstractByteArrayChromosome {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 7477856036254972691L;
    
    /**
     * An empty constructor used in {@link #newLikeThis()} method.
     */
    protected ByteArrayChromosome() {}
    
    /**
     * Constructs a <code>ByteArrayChromosome</code> using the provided array of bytes. Bytes are copied in order to
     * insure that they cannot be altered outside of this chromosome.
     * 
     * @param bytes bytes which will be assigned to the chromosome.
     */
    public ByteArrayChromosome(byte[] bytes) {
        super(bytes, true);
    }
    
    @Override
    protected void deepCopyTo(AbstractChromosome target) throws CloneNotSupportedException {}
    
    @Override
    public ByteArrayChromosome newLikeThis() {
        
        return new ByteArrayChromosome();
    }
}
