// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Sami Amtout (samiamtout)
import java.awt.Color;
//-------------------------------------------------------------------------
/**
 *  Class creating fluid behavior. Establishing color
 *  dissolvability and the density. Establishes the flowing
 *  behavior that every particle has.
 *
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.04
 */
public class Fluid
    extends Particle
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created Fluid object.
     * @param color Color of particle
     * @param willDissolve True or False if particle dissolves or not
     * @param density Density of the particle
     */
    public Fluid(Color color, boolean willDissolve, double density)
    {
        super(color, willDissolve, density);
        /*# Do any work to initialize your class here. */
    }


    //~ Methods ...............................................................
    /**
     * Fluid flows downward. If blocked, it flows eithr left or right
     * @return moved Returns if particle flows or not
     */
    @Override
    public boolean flow()
    {
        boolean moved = super.flow();
        
        if (!moved)
        {
            int x = getX();
            int y = getY();
            
            if (swapPlacesIfPossible(x - 1, y))
            {
                return true;
            }
            if (swapPlacesIfPossible(x + 1, y))
            {
                return true;
            }
        }
        return moved;
    }

}
