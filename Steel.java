// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Sami Amtout (samiamtout)
//-------------------------------------------------------------------------
/**
 *  Steel class creating a piece of steel 
 *  that cannot fall or flow.
 *
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.04
 */
public class Steel
    extends Particle
    
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created Steel object.
     */
    public Steel()
    {
        super(GRAY, true, 7.87);
        /*# Do any work to initialize your class here. */
    }


    //~ Methods ...............................................................
    /**
     * Steel cannot be falling
     * @return false Returns false
     */
    @Override
    public boolean isFalling()
    {
        return false;
    }
    /**
     * Steel doesn't have any flow
     * @return false Returns false
     */
    @Override
    public boolean flow()
    {
        return false;
    }

}
