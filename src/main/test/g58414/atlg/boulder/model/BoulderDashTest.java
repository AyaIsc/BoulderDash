package g58414.atlg.boulder.model;

import g58414.atlg.boulder.model.elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoulderDashTest {

    private BoulderDash facade;

    @BeforeEach
    void setUp() {
        facade = new BoulderDash(0);
    }
//done
    @Test
    void playMoveRight() {
        //actual position
        Position playerPosition = facade.getBoard().getPlayer().getPosition();
        //movement
        facade.playMove(Directions.RIGHT);
        //excepted position
        Position testPosition = new Position(playerPosition.getX() + Directions.RIGHT.getX(), playerPosition.getY() + Directions.RIGHT.getY());

        assertEquals(testPosition, facade.getBoard().getPlayer().getPosition());
    }
//done
    @Test
    void playMoveLeft() {

        Position playerPosition = facade.getBoard().getPlayer().getPosition();

        facade.playMove(Directions.LEFT);

        Position testPosition = new Position(playerPosition.getX() + Directions.LEFT.getX(), playerPosition.getY() + Directions.LEFT.getY());

        assertEquals(testPosition, facade.getBoard().getPlayer().getPosition());
    }
//done
    @Test
    void playMoveDown() {

        Position playerPosition = facade.getBoard().getPlayer().getPosition();
        //4,2
        facade.playMove(Directions.DOWN);

        Position testPosition = new Position(playerPosition.getX() + Directions.DOWN.getX(), playerPosition.getY() + Directions.DOWN.getY());

        assertEquals(testPosition, facade.getBoard().getPlayer().getPosition());
    }
//done
    @Test
    void playMoveUp() {

        Position playerPosition = facade.getBoard().getPlayer().getPosition();

        facade.playMove(Directions.UP);

        Position testPosition = new Position(playerPosition.getX() + Directions.UP.getX(), playerPosition.getY() + Directions.UP.getY());

        assertEquals(testPosition, facade.getBoard().getPlayer().getPosition());
    }
    //done
    @Test
    void undo() {
        Position playerPosition = facade.getBoard().getPlayer().getPosition();

        facade.playMove(Directions.UP);

        facade.undo();

        Position undoPosition = new Position(playerPosition.getX(),playerPosition.getY());
        assertEquals(undoPosition ,  facade.getBoard().getPlayer().getPosition());

    }
    //done
    @Test
    void redo() {
        Position playerPosition = facade.getBoard().getPlayer().getPosition();

        facade.playMove(Directions.UP);

        facade.undo();
        facade.redo();

        Position redoPosition = new Position(playerPosition.getX()+Directions.UP.getX(),playerPosition.getY()+Directions.UP.getY());

        assertEquals(redoPosition , facade.getBoard().getPlayer().getPosition());
    }


    //done
    @Test
    void RockCase() {
        Position playerPosition = facade.getBoard().getPlayer().getPosition();
        facade.playMove(Directions.RIGHT);
        Element expResult = new Rock();
        Element result = facade.getBoard().getElemAt(new Position(playerPosition.getX() + Directions.RIGHT.getX()  + Directions.RIGHT.getX(), playerPosition.getY() + Directions.RIGHT.getY() + Directions.RIGHT.getY()));

        assertEquals(expResult.getClass(),result.getClass());
    }
//done
    @Test
    void diamondCase() {
        facade.playMove(Directions.LEFT);
        int expDiamond = facade.getBoard().getPlayer().getDiamonds();
        int resultDiamond = 1;
        assertEquals(expDiamond,resultDiamond);
    }


//done
    @Test
    void wallCaseDown() {
        Position playerPosition = facade.getBoard().getPlayer().getPosition();
        facade.playMove(Directions.DOWN);
        facade.playMove(Directions.DOWN);
        facade.playMove(Directions.DOWN);

        Position expPosition =  new Position(playerPosition.getX()+Directions.DOWN.getX()+Directions.DOWN.getX(), playerPosition.getY()+Directions.DOWN.getY()+Directions.DOWN.getY());

        assertEquals(expPosition,facade.getBoard().getPlayer().getPosition());
    }

    //done
    @Test
    void rockOnRockFalling() {
        Position playerPosition = facade.getBoard().getPlayer().getPosition();
        facade.playMove(Directions.LEFT);
        facade.playMove(Directions.LEFT);
        facade.playMove(Directions.UP);
        facade.playMove(Directions.RIGHT);

        Element expResult = new Rock();
        Element result = facade.getBoard().getElemAt( new Position(playerPosition.getX()+Directions.LEFT.getX()+Directions.LEFT.getX(), playerPosition.getY()+Directions.LEFT.getY()+Directions.LEFT.getY()));
        assertEquals(expResult.getClass(),result.getClass());
    }
    //done
    @Test
    void rockFalling(){
        Position playerPosition = facade.getBoard().getPlayer().getPosition();

        facade.playMove(Directions.DOWN);
        facade.playMove(Directions.RIGHT);
        facade.playMove(Directions.RIGHT);

        Element expResult = new Rock();
        Element result = facade.getBoard().getElemAt( new Position(playerPosition.getX()+Directions.DOWN.getX()+Directions.RIGHT.getX(),playerPosition.getY()+Directions.DOWN.getY()+Directions.RIGHT.getY()));
        assertEquals(expResult.getClass(),result.getClass());
    }
//done
    @Test
    void diamondFalling() {
        Position playerPosition = facade.getBoard().getPlayer().getPosition();

        facade.playMove(Directions.DOWN);
        facade.playMove(Directions.LEFT);
        facade.playMove(Directions.LEFT);

        Element expResult = new Diamond();
        Element result = facade.getBoard().getElemAt( new Position(playerPosition.getX()+Directions.DOWN.getX()+Directions.LEFT.getX(),playerPosition.getY()+Directions.DOWN.getY()+Directions.LEFT.getY()));

        assertEquals(expResult.getClass(),result.getClass());
    }
    //done
    @Test
    void diamondOnDiamondFall() {
        Position playerPosition = facade.getBoard().getPlayer().getPosition();

        facade.playMove(Directions.UP);
        facade.playMove(Directions.RIGHT);
        facade.playMove(Directions.RIGHT);
        facade.playMove(Directions.LEFT);

        Element expResult = new Diamond();
        Element result = facade.getBoard().getElemAt( new Position(playerPosition.getX()+Directions.RIGHT.getX()+Directions.RIGHT.getX(),playerPosition.getY()+Directions.RIGHT.getY()+Directions.RIGHT.getY()));

        assertEquals(expResult.getClass(),result.getClass());

    }
    //done
    @Test
    void isLevelWin() {
        facade.playMove(Directions.LEFT);

        boolean expected = true;
        boolean result = facade.isLvlWin(facade.getBoard());

        assertEquals(expected,result);
    }

    //done
    @Test
    void isPlayerOnExit() {

        facade.playMove(Directions.LEFT);
        facade.playMove(Directions.DOWN);
        facade.playMove(Directions.LEFT);
        facade.playMove(Directions.DOWN);
        facade.playMove(Directions.LEFT);
        facade.playMove(Directions.DOWN);

        boolean expected = true;
        boolean result = facade.isPlayerOnExit();

        assertEquals(expected,result);
    }

    //done
    @Test
    void moveRockAgainstDiamond() {
        Position playerPosition = facade.getBoard().getPlayer().getPosition();

        facade.playMove(Directions.RIGHT);
        facade.playMove(Directions.RIGHT);

        Element expResult = new Rock();
        Element result = facade.getBoard().getElemAt( new Position(playerPosition.getX()+Directions.RIGHT.getX()+Directions.RIGHT.getX(),playerPosition.getY()+Directions.RIGHT.getY()+Directions.RIGHT.getY()));

        assertEquals(expResult.getClass(),result.getClass());
    }

    //done
    @Test
    void liveLost() {

        facade.playMove(Directions.DOWN);
        facade.playMove(Directions.RIGHT);
        facade.playMove(Directions.DOWN);

        int expected = 2;
        int result = facade.getBoard().getPlayer().getLives();

        assertEquals(expected,result);
    }

    //done
    @Test
    void isGameOver() {
        int i = 0;
        while(i<=2) {
            facade.playMove(Directions.DOWN);
            facade.playMove(Directions.RIGHT);
            facade.playMove(Directions.DOWN);
            i++;
        }
        boolean expected = true;
        boolean result = facade.isGameOver();

        assertEquals(expected,result);
    }

    //done
    @Test
    void soilCase() {
        Position playerPosition = facade.getBoard().getPlayer().getPosition();

        facade.playMove(Directions.UP);
        facade.playMove(Directions.LEFT);

        Element expResult = new Nothing();
        Element result = facade.getBoard().getElemAt( new Position(playerPosition.getX()+Directions.UP.getX(),playerPosition.getY()+Directions.UP.getY()));

        assertEquals(expResult.getClass(),result.getClass());
    }

    //done
    @Test
    void isGameOverFalse() {

        facade.playMove(Directions.DOWN);
        facade.playMove(Directions.RIGHT);
        facade.playMove(Directions.DOWN);


        boolean expected = false;
        boolean result = facade.isGameOver();

        assertEquals(expected,result);
    }
    //done
    @Test
    void liveLostFalse() {
        facade.playMove(Directions.DOWN);


        int expected = 3;
        int result = facade.getBoard().getPlayer().getLives();

        assertEquals(expected,result);
    }

    //done
    @Test
    void caseWallUp(){
        Position playerPosition = facade.getBoard().getPlayer().getPosition();
        facade.playMove(Directions.UP);
        facade.playMove(Directions.UP);
        Position expPosition =  new Position(playerPosition.getX()+Directions.UP.getX(), playerPosition.getY()+Directions.UP.getY());
        assertEquals(expPosition,facade.getBoard().getPlayer().getPosition());
    }
    //done
    @Test
    void rockOndiamond() {
        Position playerPosition = facade.getBoard().getPlayer().getPosition();
        facade.playMove(Directions.LEFT);
        facade.playMove(Directions.RIGHT);
        facade.playMove(Directions.DOWN);
        facade.playMove(Directions.DOWN);
        facade.playMove(Directions.RIGHT);
        facade.playMove(Directions.RIGHT);
        facade.playMove(Directions.UP);
        facade.playMove(Directions.UP);

        Element expected = new Rock();
        Element result = facade.getBoard().getElemAt(new Position(playerPosition.getX()+Directions.DOWN.getX()+Directions.DOWN.getX()+Directions.RIGHT.getX()+Directions.RIGHT.getX(), playerPosition.getY()+Directions.DOWN.getY()+Directions.DOWN.getY()+Directions.RIGHT.getY()+Directions.RIGHT.getY()));
        assertEquals(expected.getClass(),result.getClass());
    }
    //done
    @Test
    void diamondOnRock() {
        Position playerPosition = facade.getBoard().getPlayer().getPosition();
        facade.playMove(Directions.LEFT);
        facade.playMove(Directions.RIGHT);
        facade.playMove(Directions.RIGHT);
        facade.playMove(Directions.DOWN);
        facade.playMove(Directions.DOWN);
        facade.playMove(Directions.LEFT);
        facade.playMove(Directions.UP);
        facade.playMove(Directions.UP);

        Element expected = new Diamond();
        Element result = facade.getBoard().getElemAt(new Position(playerPosition.getX()+Directions.DOWN.getX()+Directions.DOWN.getX(), playerPosition.getY()+Directions.DOWN.getY()+Directions.DOWN.getY()));
        assertEquals(expected.getClass(),result.getClass());

    }

}