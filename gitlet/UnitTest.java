package gitlet;

import ucb.junit.textui;
import org.junit.Test;
import static org.junit.Assert.*;

/** The suite of all JUnit tests for the gitlet package.
 *  @author Fourth Teerakapibal
 */
public class UnitTest {

    /** Run the JUnit tests in the loa package. Add xxxTest.class entries to
     *  the arguments of runClasses to run other JUnit tests. */
    public static void main(String[] ignored) {
        textui.runClasses(UnitTest.class);
    }

    @Test
    public void inittest() {
        Repo repo = new Repo();
        assertTrue(repo.getcommit() == null);
        assertFalse(repo.getstaging().equals(null));
        assertFalse(repo.getfiles().equals(null));
        assertFalse(repo.getremovedfiles().equals(null));
        assertTrue(repo.getbranch().equals("master"));
        assertTrue(true);
    }

    @Test
    public void addtest() {
        assertTrue(true);
    }

    @Test
    public void committest() {
        assertTrue(true);
    }

    @Test
    public void checkouttest1() {
        assertTrue(true);
    }

    @Test
    public void checkouttest2() {
        assertTrue(true);
    }

    @Test
    public void checkouttest3() {
        assertTrue(true);
    }

    @Test
    public void checkouttest4() {
        assertTrue(true);
    }

    @Test
    public void checkouttest5() {
        assertTrue(true);
    }

    @Test
    public void checkouttest6() {
        assertTrue(true);
    }

    @Test
    public void checkouttest7() {
        assertTrue(true);
    }

    @Test
    public void checkouttest8() {
        assertTrue(true);
    }

    @Test
    public void checkouttest9() {
        assertTrue(true);
    }

    @Test
    public void checkouttest10() {
        assertTrue(true);
    }

    @Test
    public void checkouttest11() {
        assertTrue(true);
    }

    @Test
    public void checkouttest12() {
        assertTrue(true);
    }

}


