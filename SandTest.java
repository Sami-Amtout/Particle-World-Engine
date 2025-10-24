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
 *  Test class for my Sand class.
 *  
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.04
 */
public class SandTest
    extends TestCase
{
    //~ Fields ................................................................
    private ParticleWorld world;
    private Sand s;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new SandTest test object.
     */
    public SandTest()
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
        s = new Sand();
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * Tests the constructor
     */
    public void testConstructor()
    {
        assertThat(s.getDensity()).isEqualTo(2.5);
        assertThat(s.willDissolve()).isTrue();
        assertThat(s.getVelocity()).isEqualTo(0.0);
        assertThat(s.getAcceleration()).isEqualTo(1.0);
        assertThat(s.getStrength()).isEqualTo(100);
    }
    /**
     * Tests the sand falling
     */
    public void testFall()
    {
        world.addObject(s, 2, 1);
        s.fall();
        assertThat(s.getX()).isEqualTo(2);
        assertThat(s.getY()).isEqualTo(2);
    }
    /**
     * Tests when the sand is blocked when falling
     */
    public void testFallBlocked()
    {
        Sand blocker = new Sand();
        world.addObject(s, 2, 1);
        world.addObject(blocker, 2, 2);
        
        s.fall();
        assertThat(s.getY()).isEqualTo(1);
    }
}
