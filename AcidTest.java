// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Sami Amtout (samiamtout)
import student.micro.*;
import static org.assertj.core.api.Assertions.*;
// -------------------------------------------------------------------------
/**
 *  Test class for my Acid class.
 *
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.04
 */
public class AcidTest
    extends TestCase
{
    //~ Fields ................................................................
    private ParticleWorld world;
    private Acid a;
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new AcidTest test object.
     */
    public AcidTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        /*# Insert your own setup code here */
        world = new ParticleWorld(5, 5);
        a = new Acid();
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * Tests the constructor
     */
    public void testConstructor()
    {
        Acid acid = new Acid();
        assertThat(acid.getDensity()).isEqualTo(1.0);
        assertThat(acid.willDissolve()).isFalse();
        assertThat(acid.getStrength()).isEqualTo(100);
    }
    /**
     * Tests acid dissolving in an empty world
     */
    public void testDissolveEmptyWorld()
    {
        Acid acid = new Acid();
        acid.dissolveIfPossible(1, 1);
        assertThat(acid.getStrength()).isEqualTo(100);
    }
    /**
     * Tests out of bound behavior for acid
     */
    public void testDissolveOutBounds()
    {
        world.addObject(a, 2, 2);
        a.dissolveIfPossible(-1, 0);
        a.dissolveIfPossible(5, 0);
        a.dissolveIfPossible(0, -1);
        a.dissolveIfPossible(0, 5);
        assertThat(a.getStrength()).isEqualTo(100);
    }
    /**
     * Tests acid dissolving with no other particles
     */
    public void testDissolveWithNoParticle()
    {
        Acid acid = new Acid();
        world.addObject(acid, 2, 2);
        acid.dissolveIfPossible(1, 1);
        assertThat(acid.getStrength()).isEqualTo(100);
    }
    /**
     * Tets when acid and another particle don't weaken
     */
    public void testDissolveOtherNotDissolve()
    {
        Acid acid = new Acid();
        Particle inert = new Particle(ParticleBase.GREEN, false, 2.0);
        world.addObject(acid, 2, 2);
        world.addObject(inert, 2, 1);
        
        acid.dissolveIfPossible(2, 1);
        assertThat(acid.getStrength()).isEqualTo(100);
        assertThat(inert.getStrength()).isEqualTo(100);
    }
    /**
     * Tests when acid and another particle weaken
     */    
    public void testDissolveOtherDissolve()
    {
        Acid acid = new Acid();
        Particle weak = new Particle(ParticleBase.GREEN, true, 2.0);
        world.addObject(acid, 2, 2);
        world.addObject(weak, 2, 1);
        
        acid.dissolveIfPossible(2, 1);
        assertThat(acid.getStrength()).isEqualTo(99);
        assertThat(weak.getStrength()).isEqualTo(99);
    }
    /**
     * Tests when there's no more acid left
     */
    public void testAcidRemoved()
    {
        Acid acid = new Acid();
        world.addObject(acid, 2, 2);
        for (int i = 0; i < 99; i++)
        {
            acid.weaken();
        }
        Particle weak = new Particle(ParticleBase.GREEN, true, 2.0);
        world.addObject(weak, 2, 1);
        
        acid.act();
        assertThat(acid.getWorld()).isNull();
        assertThat(weak.getStrength()).isEqualTo(99);
    }
    /**
     * Tests acid dissolving below
     */
    public void testDissolveBelow()
    {
        Acid acid = new Acid();
        Particle p = new Particle(ParticleBase.GREEN, true, 2.0);
        
        world.addObject(acid, 2, 2);
        world.addObject(p, 2, 3);
        
        acid.act();
        assertThat(acid.getStrength()).isEqualTo(99);
        assertThat(p.getStrength()).isEqualTo(99);
    }
    /**
     * Tests super.act() method
     */
    public void testSuperAct()
    {
        Acid acid = new Acid();
        Particle weak = new Particle(ParticleBase.GREEN, true, 2.0);
        world.addObject(acid, 2, 1);
        world.addObject(weak, 2, 0);
        acid.act();
        assertThat(acid.getStrength()).isEqualTo(99);
        assertThat(weak.getStrength()).isEqualTo(99);
        assertThat(acid.getY() >= 1).isTrue();
    }
    /**
     * Tests acid in an empty or null world
     */
    public void testActEmptyWorld()
    {
        Acid acid = new Acid();
        acid.act();
        assertThat(acid.getStrength()).isEqualTo(100);
    }
    /**
     * Acid dissolving and being removed
     */
    public void testAfterDissolved()
    {
        Acid acid = new Acid();
        world.addObject(acid, 2, 2);
        world.addObject(new Particle(ParticleBase.GREEN, true, 1.0), 1, 2);
        world.addObject(new Particle(ParticleBase.GREEN, true, 1.0), 3, 2);
        world.addObject(new Particle(ParticleBase.GREEN, true, 1.0), 2, 1);
        world.addObject(new Particle(ParticleBase.GREEN, true, 1.0), 2, 3);
        
        for (int i = 0; i < 99; i++)
        {
            acid.weaken();
        }
        acid.act();
        assertThat(acid.getWorld()).isNull();
    }
    /**
     * Tests when dissolving particle to the right
     */
    public void testActRight()
    {
        Acid acid = new Acid();
        world.addObject(acid, 2, 2);
        for (int i = 0; i < 99; i++)
        {
            acid.weaken();
        }
        world.addObject(new Particle(ParticleBase.GREEN, false, 1.0), 1, 2);
        Particle target = new Particle(ParticleBase.GREEN, true, 1.0);
        world.addObject(target, 3, 2);
        acid.act();
        assertThat(acid.getWorld()).isNull();
        assertThat(target.getStrength()).isEqualTo(99);
    }
    /**
     * Tests the return after dissolving particle below
     */
    public void testActBelow()
    {
        Acid acid = new Acid();
        world.addObject(acid, 2, 2);
        for (int i = 0; i < 99; i++)
        {
            acid.weaken();
        }
        world.addObject(new Particle(ParticleBase.GREEN, false, 1.0), 1, 2);
        world.addObject(new Particle(ParticleBase.GREEN, false, 1.0), 3, 2);
        world.addObject(new Particle(ParticleBase.GREEN, false, 1.0), 2, 1);
        Particle target = new Particle(ParticleBase.GREEN, true, 1.0);
        world.addObject(target, 2, 3);
        acid.act();
        assertThat(acid.getWorld()).isNull();
        assertThat(target.getStrength()).isEqualTo(99);
    }
}
