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
 *  Test class for my Water class.
 *
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.04
 */
public class WaterTest
    extends TestCase
{
    //~ Fields ................................................................
    private ParticleWorld world;
    private Water w;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WaterTest test object.
     */
    public WaterTest()
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
        w = new Water();
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * Tests the constructor
     */
    public void testConstructor()
    {
        assertThat(w.getDensity()).isEqualTo(1.0);
        assertThat(w.willDissolve()).isFalse();
        assertThat(w.getVelocity()).isEqualTo(0.0);
        assertThat(w.getAcceleration()).isEqualTo(1.0);
        assertThat(w.getStrength()).isEqualTo(100);
    }
    /**
     * Tests the flow of the water
     */
    public void testFLow()
    {
        world.addObject(w, 2, 2);
        boolean moved = w.flow();
        assertThat(moved).isTrue();
        assertThat(w.getX()).isEqualTo(2);
        assertThat(w.getY()).isEqualTo(3);
    }
}
