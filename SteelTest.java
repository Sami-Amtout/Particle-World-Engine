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
 *  Test class for my Steel class.
 *
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.04
 */
public class SteelTest
    extends TestCase
{
    //~ Fields ................................................................
    private ParticleWorld world;
    private Steel s;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new SteelTest test object.
     */
    public SteelTest()
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
        s = new Steel();
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * Tests the constructor
     */
    public void testConstructor()
    {
        assertThat(s.getDensity()).isEqualTo(7.87);
        assertThat(s.willDissolve()).isTrue();
        assertThat(s.getVelocity()).isEqualTo(0);
        assertThat(s.getAcceleration()).isEqualTo(1);
        assertThat(s.getStrength()).isEqualTo(100);
    }
    /**
     * Tests that steel is never falling
     */
    public void testIsNeverFalling()
    {
        world.addObject(s, 2, 2);
        assertThat(s.isFalling()).isFalse();
    }
    /**
     * Tests that steel cannot flow
     */
    public void testFlow()
    {
        world.addObject(s, 2, 2);
        assertThat(s.flow()).isFalse();
    }
}
