/**
 * Used to store Constants used throughout the program
 * Created by 1530B
 */

/*
 * You should never have one file with all your constants.
 * The dimensions of a board have nothing to do with the input and output streams.
 * All static finals should be in all CAPS with an _ dividing words. (i.e. DIM_X or INPUT_STREAM
 *
 */

import java.io.InputStream;
import java.io.PrintStream;

public class Constants {
    public static final int dimX = 3;
    public static final int dimY = 3;
    public static final int gridSize = dimX * dimY;
    public static final InputStream inputStream = System.in;
    public static final PrintStream outputStream = System.out;

}
