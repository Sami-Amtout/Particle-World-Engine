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
 *  Test class for my Fluid class.
 *
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.04
 */
public class FluidTest
    extends TestCase
{
    //~ Fields ................................................................
    private ParticleWorld world;
    private Fluid f;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new FluidTest test object.
     */
    public FluidTest()
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
        f = new Fluid(ParticleBase.GREEN, false, 1.0);
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * Tests the constructor
     */
    public void testConstructor()
    {
        assertThat(f.getDensity()).isEqualTo(1.0);
        assertThat(f.willDissolve()).isFalse();
        assertThat(f.getVelocity()).isEqualTo(0.0);
        assertThat(f.getAcceleration()).isEqualTo(1.0);
        assertThat(f.getStrength()).isEqualTo(100);
    }
    /**
     * Test to flow down
     */
    public void testFlowDown()
    {
        world.addObject(f, 2, 2);
        boolean moved = f.flow();
        assertThat(moved).isTrue();
        assertThat(f.getX()).isEqualTo(2);
        assertThat(f.getY()).isEqualTo(3);
    }
    /**
     * Test when right is blocked and flow left
     */
    public void testFlowOverrideLeft()
    {
        world.addObject(f, 2, 2);
        world.addObject(new Steel(), 2, 3);
        world.addObject(new Steel(), 1, 3);
        world.addObject(new Steel(), 3, 3);
        boolean moved = f.flow();
        assertThat(moved).isTrue();
        assertThat(f.getX()).isEqualTo(1);
        assertThat(f.getY()).isEqualTo(2);
    }
    /**
     * Test when left is blocked and flows right
     */
    public void testFlowOverrideRight()
    {
        world.addObject(f, 2, 2);
        world.addObject(new Steel(), 2, 3);
        world.addObject(new Steel(), 1, 3);
        world.addObject(new Steel(), 3, 3);
        world.addObject(new Steel(), 1, 2);
        boolean moved = f.flow();
        assertThat(moved).isTrue();
        assertThat(f.getX()).isEqualTo(3);
        assertThat(f.getY()).isEqualTo(2);
    }
    /**
     * Test when all directions are blocked
     */
    public void testFlowBlocked()
    {
        world.addObject(f, 2, 2);
        world.addObject(new Steel(), 2, 3);
        world.addObject(new Steel(), 1, 3);
        world.addObject(new Steel(), 3, 3);
        world.addObject(new Steel(), 1, 2);
        world.addObject(new Steel(), 3, 2);
        boolean moved = f.flow();
        assertThat(moved).isFalse();
        assertThat(f.getX()).isEqualTo(2);
        assertThat(f.getY()).isEqualTo(2);
    }
}
