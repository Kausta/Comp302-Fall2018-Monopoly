package cabernet1.monopoly.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cabernet1.monopoly.domain.game.board.tile.property.GroupColoredProperty;
import cabernet1.monopoly.domain.game.board.tile.property.MediterraneanAvenue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;

public class GroupColoredPropertyTest extends TestBase {

    public GroupColoredProperty p;

    @BeforeEach
    public void setUp(){
        p = new MediterraneanAvenue();
    }

    @Test
    public void testBuyHouseBlackBox() {
        assertEquals(p.getHouse().getAmount(), 0); 
        p.buyHouse();
        assertEquals(p.getHouse().getAmount(), 1); 
    }

    @Test
    public void testDemolishHouseBlackBox() {
        p.buyHouse();
        assertEquals(p.getHouse().getAmount(), 1); 
        p.demolishHouse();
        assertEquals(p.getHouse().getAmount(), 0); 
    }

    @Test
    public void testGetRentBlackBox() {
        p.buyHouse();
        p.buyHouse();
        assertEquals(p.getRent(), 30);
    }

    @Test
    public void testUpgradeBlackBox() {
        p.upgrade();
        p.upgrade();
        assertEquals(p.getHouse().getAmount(), 2);
    }

    @Test
    public void testUpgradeGlassBox() {
        p.upgrade();
        p.upgrade();
        p.upgrade();
        p.upgrade();
        p.upgrade();
        p.upgrade();
        assertEquals(p.getHouse().getAmount(), 4);
        assertEquals(p.getHotel().getAmount(), 1);
        assertEquals(p.getSkyscraper().getAmount(), 1);
    }

    @AfterEach
    public void tearDown(){
        testRepOK(p);
    }


}
