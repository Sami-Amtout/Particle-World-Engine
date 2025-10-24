// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Sami Amtout (samiamtout)
import student.micro.*;

//-------------------------------------------------------------------------
/**
 *  Represents a world where particles interact.
 *
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.04
 */
public class ParticleWorld
    extends ParticleWorldBase
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new world with the default layout.
     */
    public ParticleWorld()
    {
        /*# Change to a different number to change the layout */
        this(0);
    }


    // ----------------------------------------------------------
    /**
     * Creates a new world with a specific layout.
     * @param layout A number 0-2 specifying the layout to use.
     */
    public ParticleWorld(int layout)
    {
        super(layout);
    }


    // ----------------------------------------------------------
    /**
     * Creates a new, empty world with the specified dimensions.
     * @param width  The width of the new world.
     * @param height The height of the new world.
     */
    public ParticleWorld(int width, int height)
    {
        super(width, height);
    }
}
