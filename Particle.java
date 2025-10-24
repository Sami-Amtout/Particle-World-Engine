// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Sami Amtout (samiamtout)
import java.awt.Color;
//-------------------------------------------------------------------------
/**
 *  Particle class where all attributes or a particle are 
 *  established. 
 *
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.03
 */
public class Particle
    extends ParticleBase
{
    //~ Fields ................................................................
    private double velocity;
    private double acceleration;
    private int strength;
    private double density;
    private boolean willDissolve;
    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created Particle object.
     * @param color Color of particle
     * @param willDissolve Boolean if particle dissolves or not
     * @param density Density of the particle
     */
    public Particle(Color color, boolean willDissolve, double density)
    {
        super(color);
        this.velocity = 0.0;
        this.acceleration = 1.0;
        this.strength = 100;
        this.density = density;
        this.willDissolve = willDissolve;
        /*# Do any work to initialize your class here. */
    }


    //~ Methods ...............................................................
    /**
     * Gets the density of the particle
     * @return density Returns density of particle
     */
    public double getDensity()
    {
        return density;
    }
    /**
     * Gets the velocity of the particle
     * @return velocity Returns velocity of particle
     */
    public double getVelocity()
    {
        return velocity;
    }
    /**
     * Gets the acceleration of the particle
     * @return acceleration Return acceleration of particle
     */
    public double getAcceleration()
    {
        return acceleration;
    }
    /**
     * Gets the strength of the particle
     * @return strength Returns strength of particle
     */
    public int getStrength()
    {
        return strength;
    }
    /**
     * Returns true or false if particle dissolves others
     * @return willDissolve Returns true or false if particle is dissolvable
     */
    public boolean willDissolve()
    {
        return willDissolve;
    }
    /**
     * Reduces the strength of a particle by 1
     * If strength reaches 0, particle is removed from world
     */
    public void weaken()
    {
        strength = strength - 1;
        if (strength <= 0)
        {
            getWorld().removeObject(this);
        }
    }
    /**
     * Space below for a given particle to fall
     * @return Returns new cell particle falls to
     */
    public boolean isFalling()
    {
        int x = getX();
        int y = getY();
        if (y >= getWorld().getHeight() - 1)
        {
            return false;
        }
        
        for (Object obj : getWorld().getObjects(Particle.class))
        {
            Particle p = (Particle) obj;
            if (p.getX() == x && p.getY() == y + 1)
            {
                return false;
            }
        }
        return true;
    }
    /**
     * Falling of a particle
     * Increases the velocity of the particle
     */
    public void fall()
    {
        velocity += acceleration;
        int distance = (int) velocity;
        boolean moved = false;
        for (int i = 0; i < distance; i++)
        {
            if (isFalling())
            {
                setLocation(getX(), getY() + 1);
                moved = true;
            }
            else
            {
                velocity = 0.0;
                break;
            }
        }
        if (moved && !isFalling())
        {
            velocity = 0.0;
        }

    }
    /**
     * Movement of particles
     * If cell is empty, particle moves there
     * If lighter particle is present, it swaps places with it
     * @param x X location of particle
     * @param y Y location of particle
     * @return false Returns false if particle can't swap places
     */
    public boolean swapPlacesIfPossible(int x, int y)
    {
        if (x < 0 || x >= getWorld().getWidth() || 
             y < 0 || y >= getWorld().getHeight())
        {
            return false;
        }
        Particle target = null;
        for (Object object : getWorld().getObjects(Particle.class))
        {
            Particle particle = (Particle) object;
            if (particle.getX() == x && particle.getY() == y)
            {
                target = particle;
            }
        }
         
        if (target == null)
        {
            setLocation(x, y);
            return true;
        }
        else if (this.density > target.getDensity())
        {
            int oldX = getX();
            int oldY = getY();
            target.setLocation(oldX, oldY);
            setLocation(x, y);
            return true;
        }
        return false;
    }
    /**
     * Flowing of a particle
     * @return Returns all possible flowing direction of particle
     */
    public boolean flow()
    {
        int x = getX();
        int y = getY();
        return swapPlacesIfPossible(x, y + 1) ||
                swapPlacesIfPossible(x - 1, y + 1) ||
                swapPlacesIfPossible(x + 1, y + 1);
    }
    /**
     * Particle falls if possible, if not, it flows
     */
    public void act()
    {
        if (isFalling())
        {
            fall();
        }
        else
        {
            flow();
        }
    }

}
