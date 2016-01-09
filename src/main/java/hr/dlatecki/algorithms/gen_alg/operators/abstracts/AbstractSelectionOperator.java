package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import java.util.Random;
import java.util.SortedSet;
import hr.dlatecki.algorithms.gen_alg.operators.interfaces.ISelectionOperator;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;

/**
 * An abstract class for selection operator. It is recommended that all implementations of selection operator extend
 * this class instead of directly implementing <code>ISelectionOperator</code> interface.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @param <C> the type of chromosome which will be used in the selection operator.
 * @see IChromosome
 * @see ISelectionOperator
 */
public abstract class AbstractSelectionOperator<C extends IChromosome> extends AbstractOperator
        implements ISelectionOperator<C> {
        
    /**
     * Defines a number of chromosomes to be selected in this selection operator.
     */
    private int selectionSize;
    
    /**
     * Constructs a selection operator with provided <code>Random</code> object and selection size. Selection size must
     * be a number greater than or equal to 1.
     * 
     * @param rand object used to generate random numbers.
     * @param selectionSize number of chromosomes to select. Must be a number greater than or equal to 1.
     * @throws IllegalArgumentException thrown if provided selection size is less than 1.
     */
    public AbstractSelectionOperator(Random rand, int selectionSize) {
        super(rand);
        
        setSelectionSize(selectionSize);
    }
    
    @Override
    public final void setSelectionSize(int size) {
        
        checkSelectionSize(size);
        
        selectionSize = size;
    }
    
    @Override
    public final SortedSet<C> select(SortedSet<C> pool) {
        
        return select(pool, selectionSize);
    }
    
    @Override
    public final SortedSet<C> select(SortedSet<C> pool, int size) {
        
        checkSelectionSize(size);
        
        return performSelection(pool, size);
    }
    
    /**
     * Checks if provided selection size is greater or equal to 1.
     * 
     * @param size selection size.
     * @throws IllegalArgumentException thrown if provided selection size is less than 1.
     */
    private void checkSelectionSize(int size) {
        
        if (size < 1) {
            throw new IllegalArgumentException("Selection size cannot be less than 1.");
        }
    }
    
    /**
     * Performs the selection. Chromosomes are selected based on their fitness values, with chromosomes with higher
     * fitness having higher chance of being selected. The provided set of chromosomes is expected to be sorted by
     * fitness in descending order. Number of selected chromosomes is defined by the second argument, which will always
     * be greater than or equal to 1 (this is insured in <code>AbstractSelectionOperator</code>) before calling this
     * method. This method is not supposed to be invoked externally. Instead, the {@link #select(SortedSet)} or
     * {@link #select(SortedSet, int)} is invoked externally, and then this method is invoked by either of them after
     * the size is confirmed to be correct.
     * 
     * @param pool set from which chromosomes will be selected based on their fitness.
     * @param size number of chromosomes to select. Guaranteed to be greater than or equal to 1.
     * @return Set which contains selected chromosomes. The set is sorted by fitness of the chromosomes, in descending
     *         order.
     */
    protected abstract SortedSet<C> performSelection(SortedSet<C> pool, int size);
}
