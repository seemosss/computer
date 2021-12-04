import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class HorribleSteve {
    @Test
    public void testflik(){
        int i = 128;
        assertTrue(Flik.isSameNumber(i, 128));
    }

    public static void main(String [] args) {
        int i = 0;
        int j = 0;
        for (; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
        }
        System.out.println("i is " + i);
        System.out.println("j is " + j);
    }
}
