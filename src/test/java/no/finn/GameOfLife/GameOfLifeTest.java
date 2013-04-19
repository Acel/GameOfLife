package no.finn.GameOfLife;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: javalons
 * Date: 03/04/13
 * Time: 09:19
 * To change this template use File | Settings | File Templates.
 */
public class GameOfLifeTest {
    private GameOfLife game;

    @Before
    public void init() {
        game = new GameOfLife(5);
    }

    @Test
    public void lessThanTwoNeighbours() {
        String expected =
                        "00000\n" +
                        "00000\n" +
                        "00000\n" +
                        "00000\n" +
                        "00000\n";

        game.setCellAlive(0, 0);
        game.setCellAlive(0, 1);
        game.nextGeneration();
        assertEquals(expected, game.printCells());
    }

    @Test
    public void moreThanThreeNeighbours() {
        String expected =
                        "11100\n" +
                        "10100\n" +
                        "11100\n" +
                        "00000\n" +
                        "00000\n";

        game.setCellAlive(0, 1);
        game.setCellAlive(1, 0);
        game.setCellAlive(1, 1);
        game.setCellAlive(1, 2);
        game.setCellAlive(2, 1);
        game.nextGeneration();
        assertEquals(expected, game.printCells());
    }

    @Test
    public void liveCellTwoNeighboursLives() {
        String expected =
                        "00000\n" +
                        "11100\n" +
                        "00000\n" +
                        "00000\n" +
                        "00000\n";

        game.setCellAlive(0, 1);
        game.setCellAlive(1, 1);
        game.setCellAlive(2, 1);
        game.nextGeneration();
        assertEquals(expected, game.printCells());
    }

    @Test
    public void liveCellThreeNeighboursLives() {
        String expected =
                        "11000\n" +
                        "01100\n" +
                        "00000\n" +
                        "00000\n" +
                        "00000\n";

        game.setCellAlive(0, 0);
        game.setCellAlive(0, 1);
        game.setCellAlive(1, 1);
        game.setCellAlive(2, 1);
        game.nextGeneration();
        assertEquals(expected, game.printCells());
    }

    @Test
    public void deadCellThreeNeighbours() {
        String expected =
                        "11000\n" +
                        "11000\n" +
                        "00000\n" +
                        "00000\n" +
                        "00000\n";

        game.setCellAlive(0, 0);
        game.setCellAlive(0, 1);
        game.setCellAlive(1, 1);
        game.nextGeneration();
        assertEquals(expected, game.printCells());
    }

}
