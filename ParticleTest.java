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
 *  Test class for my particle class
 *
 *  @author Sami Amtout (samiamtout)
 *  @version 2025.05.04
 */
public class ParticleTest
    extends TestCase
{
    //~ Fields ................................................................
    private Particle p;
    private ParticleWorld world;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new ParticleTest test object.
     */
    public ParticleTest()
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
        p = new Particle( ParticleBase.GREEN, true, 2.0 );
        world = new ParticleWorld(5, 5);
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * Tests that particle is no longer falling once it hits
     * the bottom of the world
     */
    public void testFallingBottom()
    {
        world.addObject(p, 2, world.getHeight() - 1);
        assertThat(p.isFalling()).isFalse();
    }
    /**
     * Tests that particle is no longer falling once
     * it hits another particle below
     */
    public void testIsFallingAndBlockedByOtherParticle()
    {
        Particle blocker = new Particle(ParticleBase.GREEN, false, 1.0);
        world.addObject(p, 2, 2);
        world.addObject(blocker, 2, 3);
        assertThat(p.isFalling()).isFalse();
    }
    /**
     * Tests that the particle falls down by one cell
     * when it is empty and free below
     */
    public void testFall()
    {
        world.addObject(p, 1, 1);
        p.fall();
        assertThat(p.getY()).isEqualTo(2);
    }
    /**
     * Tests that the velocity of the particle
     * is reset when it is blocked by another particle
     */
    public void testFallBlockedByOtherParticle()
    {
        Particle blocker = new Particle(ParticleBase.GREEN, false, 1.0);
        world.addObject(p, 2, 2);
        world.addObject(blocker, 2, 3);
        p.fall();
        assertThat(p.getY()).isEqualTo(2);
        assertThat(p.getVelocity()).isEqualTo(0.0);
    }
    /**
     * Tests that the velocity of the particle
     * is zero when it hits the bottom of the world
     * and can no longer fall
     */
    public void testFallAtBottom()
    {
        world.addObject(p, 3, world.getHeight() - 1);
        p.fall();
        assertThat(p.getY()).isEqualTo(world.getHeight() - 1);
        assertThat(p.getVelocity()).isEqualTo(0.0);
    }
    /**
     * Tests that the particle moves into an empty
     * cell when it's free
     */
    public void testSwapIntoEmptyCell()
    {
        world.addObject(p, 0, 0);
        boolean  moved = p.swapPlacesIfPossible(1, 0);
        assertThat(moved).isTrue();
        assertThat(p.getX()).isEqualTo(1);
    }
    /**
     * Tests that the particle swaps places with a lighter particle
     */
    public void testSwapPlacesWithLighterParticle()
    {
        Particle light = new Particle(ParticleBase.GREEN, true, 1.0);
        world.addObject(p, 1, 1);
        world.addObject(light, 1, 2);
        boolean swapped = p.swapPlacesIfPossible(1, 2);
        assertThat(swapped).isTrue();
        assertThat(p.getY()).isEqualTo(2);
        assertThat(p.getY()).isEqualTo(2);
    }
    /**
     * Test to ensure that it fails when 
     * the bounds are out-of-bounds
     */
    public void testSwapPlacesIfOutOfBounds()
    {
        world.addObject(p, 2, 2);
        assertThat(p.swapPlacesIfPossible(-1, 2)).isFalse();
        assertThat(p.swapPlacesIfPossible(2, 5)).isFalse();
    }
    /**
     * Tests that particle flows down
     * when space below is free
     */
    public void testFlowDown()
    {
        world.addObject(p, 2, 1);
        boolean moved = p.flow();
        assertThat(moved).isTrue();
        assertThat(p.getY()).isEqualTo(2);
    }
    /**
     * Tests that particle flows diagonally left
     * when down and to the right is blocked
     */
    public void testFlowDiagonalLeft()
    {
        world.addObject(p, 2, 1);
        world.addObject(new Steel(), 2, 2);
        boolean moved = p.flow();
        assertThat(moved).isTrue();
        assertThat(p.getX()).isEqualTo(1);
        assertThat(p.getY()).isEqualTo(2);
    }
    /**
     * Tests that particle flows diagonally right
     * when down and to the left are blocked
     */
    public void testFlowDiagonalRight()
    {
        world.addObject(p, 2, 1);
        world.addObject(new Steel(), 2, 2);
        world.addObject(new Steel(), 1, 2);
        boolean moved = p.flow();
        assertThat(moved).isTrue();
        assertThat(p.getX()).isEqualTo(3);
        assertThat(p.getY()).isEqualTo(2);
    }
    /**
     * Tests that particle does not move when all directions are blocked
     */
    public void testFlowBlocked()
    {
        world.addObject(p, 2, 1);
        world.addObject(new Steel(), 2, 2);
        world.addObject(new Steel(), 1, 2);
        world.addObject(new Steel(), 3, 2);
        boolean moved = p.flow();
        assertThat(moved).isFalse();
        assertThat(p.getX()).isEqualTo(2);
        assertThat(p.getY()).isEqualTo(1);
    }
    /**
     * Tests that act() calls fall() when falling is true
     */
    public void testActCallsFall()
    {
        world.addObject(p, 2, 1);
        p.act();
        assertThat(p.getY()).isEqualTo(2);
    }
    /**
     * Tests that act() calls flow() when falling is not true
     */
    public void testActFlow()
    {
        world.addObject(p, 2, 1);
        world.addObject(new Steel(), 2, 2);
        p.act();
        assertTrue(p.getY() >= 1);
    }
    /**
     * X value is negative
     */
    public void testSwapOutOfBoundsXNegative()
    {
        world.addObject(p, 2, 2);
        assertThat(p.swapPlacesIfPossible(-1, 2)).isFalse();
    }
    /**
     * X value is too big
     */
    public void testSwapOutOfBoundsXBig()
    {
        world.addObject(p, 2, 2);
        assertThat(p.swapPlacesIfPossible(world.getWidth(), 2)).isFalse();
    }
    /**
     * Y value is negative
     */
    public void testSwapOutOfBoundsYNegative()
    {
        world.addObject(p, 2, 2);
        assertThat(p.swapPlacesIfPossible(2, -1)).isFalse();
    }
    /**
     * Y value is too big
     */
    public void testSwapOutOfBoundsYBig()
    {
        world.addObject(p, 2, 2);
        assertThat(p.swapPlacesIfPossible(2, world.getHeight())).isFalse();
    }
    /**
     * Tests when a particle is falling
     * and being blocked by another particle
     */
    public void testFallingAndBlockedByParticle()
    {
        world.addObject(p, 2, 2);
        Particle blocker = new Particle(ParticleBase.GREEN, false, 1.0);
        world.addObject(blocker, 2, 3);
        assertThat(p.isFalling()).isFalse();
    }
    /**
     * Tests that when particle isn't falling, the
     * velocity is being reset.
     */
    public void testResetsVelocity()
    {
        int lastRow = world.getHeight() - 1;
        world.addObject(p, 1, lastRow - 1);
        p.fall();
        assertThat(p.getY()).isEqualTo(lastRow);
        assertThat(p.getVelocity()).isEqualTo(0.0);
    }
    /**
     * Test when particle falls when nothing
     * is below it
     */
    public void testFallingWithNothingBelow()
    {
        world.addObject(p, 2, 2);
        assertThat(p.isFalling()).isTrue();
    }
    /**
     * Tests when particle lands on block that
     * the velocity resets
     */
    public void testLandingOnBlockResetsVelocity()
    {
        world.addObject(p, 2, 2);
        world.addObject(new Particle(ParticleBase.GREEN, false, 1.0), 2, 3);
        p.fall();
        assertThat(p.getY()).isEqualTo(2);
        assertThat(p.getVelocity()).isEqualTo(0.0);
    }
    /**
     * Resets velocity
     */
    public void testFallLandingOnBlock()
    {
        world.addObject(p, 2, 1);
        Particle blocker = new Particle(ParticleBase.GREEN, false, 1.0);
        world.addObject(blocker, 2, 3);
        p.fall();
        assertThat(p.getY()).isEqualTo(2);
        assertThat(p.getVelocity()).isEqualTo(0.0);
    }
    /**
     * Both are false 
     * x is not equal ; y is not equal to y + 1
     */
    public void testIsFallingNoSideParticle()
    {
        world.addObject(p, 2, 2);
        Particle side = new Particle(ParticleBase.GREEN, false, 1.0);
        world.addObject(side, 3, 2);
        assertThat(p.isFalling()).isTrue();
    }
    /**
     * First statement is false, second is true
     * x is not equal ; y == y + 1
     */
    public void testIsFallingNoDiagonalParticle()
    {
        world.addObject(p, 2, 2);
        Particle diag = new Particle(ParticleBase.GREEN, false, 1.0);
        world.addObject(diag, 3, 3);
        assertThat(p.isFalling()).isTrue();
    }
    /**
     * First statement is true, second is false of if statement
     * x == ; y is not equal to y + 1
     */
    public void testIsFallingNoParticleAbove()
    {
        world.addObject(p, 2, 2);
        Particle above = new Particle(ParticleBase.GREEN, false, 1.0);
        world.addObject(above, 2, 1);
        assertThat(p.isFalling()).isTrue();
    }
    /**
     * Falling doesn't reset the velocity
     */
    public void testVelocityParticleFalling()
    {
        world.addObject(p, 2, 1);
        p.fall();
        assertThat(p.getVelocity()).isEqualTo(1.0);
    }
    /**
     * moved is false
     */
    public void testFallBlocked()
    {
        world.addObject(p, 2, 2);
        Particle blocker = new Particle(ParticleBase.GREEN, false, 1.0);
        world.addObject(blocker, 2, 3);
        p.fall();
        assertThat(p.getVelocity()).isEqualTo(0.0);
    }
    /**
     * moved is true ; isFalling is true
     */
    public void testFallingKeepsVelocity()
    {
        world.addObject(p, 2, 1);
        p.fall();
        assertThat(p.getVelocity()).isEqualTo(1.0);
    }
    /**
     * moved == true ; isFalling is false
     */
    public void testLandingResetsParticleVelocity()
    {
        world.addObject(p, 2, 1);
        Particle blocker = new Particle(ParticleBase.GREEN, false, 1.0);
        world.addObject(blocker, 2, 3);
        p.fall();
        assertThat(p.getY()).isEqualTo(2);
        assertThat(p.getVelocity()).isEqualTo(0.0);
    }
}
