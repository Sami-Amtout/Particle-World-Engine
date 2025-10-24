// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Sami Amtout (samiamtout)
import greenfoot.World;
import student.micro.*;
//-------------------------------------------------------------------------
/**
 *  Acid class creating a acid particle that can dissolve
 *  nearly everything within the world. It can weaken itself
 *  and weaken everything it comes in contact with. Has standard fluid
 *  behavior.
 *
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.04
 */
public class Acid
    extends Fluid
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created Acid object.
     */
    public Acid()
    {
        super(GREEN, false,  1.0);
        /*# Do any work to initialize your class here. */
    }


    //~ Methods ...............................................................
    /**
     * Weakens a dissolvable particle at any location
     * @param x X coordinate of particle
     * @param y Y coordinate of particle
     */
    public void dissolveIfPossible(int x, int y)
    {
        World world = getWorld();
        if (world == null)
        {
            return;
        }
        if (x < 0 || x >= world.getWidth() || y < 0 || y >= world.getHeight())
        {
            return;
        }
        Particle target = null;
        for (Object obj : world.getObjects(Particle.class))
        {
            Particle p = (Particle) obj;
            if (p.getX() == x && p.getY() == y)
            {
                target = p;
                break;
            }
        }
        if (target != null && target.willDissolve())
        {
            target.weaken();
            this.weaken();
        }
    }
    /**
     * Dissolves particle in all four directions.
     */
    @Override
    public void act()
    {
        if (getWorld() == null)
        {
            return;
        }
        
        int x = getX();
        int y = getY();
        
        dissolveIfPossible(x - 1, y);
        if (getWorld() == null)
        {
            return;
        }
        dissolveIfPossible(x + 1, y);
        if (getWorld() == null) 
        {
            return;
        }        
        dissolveIfPossible(x, y - 1);
        if (getWorld() == null)
        {
            return;
        }
        dissolveIfPossible(x, y + 1);
        if (getWorld() == null)
        {
            return;
        }
        super.act();
    }
    

}
