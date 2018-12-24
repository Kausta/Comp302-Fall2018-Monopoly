package cabernet1.monopoly;

import cabernet1.monopoly.utils.RepresentationInvariant;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class TestBase {

    protected void testRepOK(RepresentationInvariant invariant) {
        System.out.println("Testing representation invariant: " + invariant);
        assertTrue(invariant.repOK());
    }
}
