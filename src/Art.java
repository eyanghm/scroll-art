import java.util.Random;

public class Art {
      static final Random rand = new Random();
    static final int WIDTH = getTerminalWidth() - 1;
    
    public static void main(String[] args) throws InterruptedException {

         long startTime = System.currentTimeMillis();
        int iterations = 0;
        char[][] nextRows = new char[50][WIDTH]; // store upcoming rows
        for (int i = 0; i < nextRows.length; i++) {
            nextRows[i] = emptyRow();
        }
        char[] prevRow = nextRows[0];

        while (true) {
            // At each lane now on the top row, 7% chance to add a new image
            for (int x = 0; x < WIDTH - 50; x += 50) {
                if (isBlank(prevRow, x) && rand.nextDouble() < 0.07) {
                    loadNextRowsWithImage(nextRows, x);
                }
            }
            // Print and remove the top row
            System.out.println(new String(nextRows[0]));
            prevRow = nextRows[0]; // update prev row
            // Shift all rows up
            shiftRowsUp(nextRows);
            Thread.sleep(20); // Delay in ms
            long time = System.currentTimeMillis() - startTime;
            iterations++;

            // System.err.println("average time per frame: " + (time / iterations) + " ms");
        }
        
    }

     
    public static int getTerminalWidth() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return getUnixTerminalWidth();
        } else {
            return 80; // fallback for unknown OS
        }
    }

        private static int getUnixTerminalWidth() {
        try {
            // Try to get terminal size from environment variables first
            String columns = System.getenv("COLUMNS");
            if (columns != null && !columns.isEmpty()) {
                return Integer.parseInt(columns);
            }

            // Fallback to stty command
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "stty size </dev/tty");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String output = reader.readLine();
            if (output != null && !output.isEmpty()) {
                String[] parts = output.trim().split(" ");
                return Integer.parseInt(parts[1]); // columns
            }
        } catch (Exception ignored) {
            // Silently ignore errors and fall back to default
        }
        return 80; // fallback
    }

    static boolean isBlank(char[] row, int x) {
        for (int i = x; i < x + 50; i++) {
            if (row[i] != ' ') {
                return false;
            }
        }
        return true;
    }

    private static void loadNextRowsWithImage(char[][] nextRows, int x) {
        // triple array version to better manage all images, it's getting a little bit out of hand!! and it is not like they are all the same size anyway.
        char[][][] images = {getBoba(), getPenguin(), getMiffy()};
        char[][] img = images[rand.nextInt(images.length)];
        for (int iy = 0; iy < img.length; iy++) { // height
            for (int ix = 0; ix < img[iy].length; ix++) { // width
                nextRows[iy][x + ix] = img[iy][ix];
            }
        }
    }

    static void shiftRowsUp(char[][] nextRows) {
        for (int i = 1; i < nextRows.length; i++) {
            nextRows[i - 1] = nextRows[i];
        }
        nextRows[nextRows.length - 1] = emptyRow();
    }

    static char[] emptyRow() {
        char[] row = new char[WIDTH];
        for (int i = 0; i < WIDTH; i++) {
            row[i] = ' ';
        }
        return row;
    }

   

    
    static char[][] getBoba() {
        char[][]img=new char [30][21];
        for(int y=0; y<30; y++ ){
            for(int x=0;x<21;x++){
                img[y][x]=' ';
            }
        }

        // cup + straw 
        img[2][13] = '_';
        img[2][14] = '_';
        img[2][15] = '_';
        img[2][16] = '_';
        img[2][17] = '_';
        img[2][18] = '_';
        img[2][19] = '_';

        img[3][12] = '/';
        img[3][14] = '_';
        img[3][15] = '_';
        img[3][16] = '_';
        img[3][17] = '_';
        img[3][18] = '_';
        img[3][19] = '_';
        img[3][20] = ')';

        img[4][11] = '/';
        img[4][13] = '/';

        img[5][10] = '/';
        img[5][12] = '/';
        img[6][9] = '/';
        img[6][11] = '/';
        img[7][9] = '|';
        img[7][11] = '|';
        img[8][9] = '|';
        img[8][11] = '|';

        img[8][8] = '_';
        img[8][7] = '_';
        img[8][6] = '_';
        img[8][5] = '_';
        img[8][4] = '_';

        img[8][12] = '_';
        img[8][13] = '_';
        img[8][14] = '_';
        img[8][15] = '_';
        img[8][16] = '_';

        img[9][3] = '/';
        img[10][2] = '/';
        img[11][1] = '/';
        img[9][17] = '\\';
        img[10][18] = '\\';
        img[11][19] = '\\';

        for(int i = 12; i <= 26; i++){
            img[i][1] = '|';
            img[i][19] = '|';
        }

        img[27][1] = '\\';
        img[28][2] = '\\';
        img[27][19] = '/';
        img[28][18] = '/';

        for(int i = 3; i <= 17; i++){
            img[28][i] = '_';
        }

        for(int i = 10; i <= 21; i++){
            img[i][9] = '|';
            img[i][11] = '|';
        }

        img[12][2] = '\\';
        img[12][18] = '/';
        for(int i = 3; i <= 17; i++){
            img[12][i] = '_';
        }

        img[22][9] = '\\';
        img[22][10] = '_';
        img[22][11] = '/';
 
        // straw hole 
        img[9][8] = '(';
        img[9][9] = '_';
        img[9][10] = '_';
        img[9][11] = '_';
        img[9][12] = ')';

        // wavy liquid part 

        img[14][8] = '_';
        img[14][7] = '_';
        img[14][6] = '_';
        img[15][5] = '/';
        img[15][4] = '_';
        img[15][3] = '_';
        img[15][2] = '\\';

        img[15][12] = '\\';
        img[15][13] = '_';
        img[15][14] = '_';
        img[15][15] = '_';
        img[15][16] = '/';
        img[14][17] = '_';
        img[14][18] = '_';

        // pearls  
        img[26][2] = '@';
        img[26][3] = '@';
        img[25][2] = '@';
        img[25][3] = '@';
        img[25][4] = '@';

        img[24][9] = '@';
        img[24][8] = '@';
        img[25][9] = '@';
        img[25][8] = '@';

        img[27][7] = '@';
        img[27][8] = '@';

        img[26][13] = '@';
        img[26][14] = '@';
        img[25][13] = '@';
        img[25][14] = '@';

        img[24][18] = '@';
        img[23][18] = '@';

        img[23][4] = '@';
        img[23][5] = '@';
        img[23][6] = '@';
        img[22][5] = '@';
        img[22][6] = '@';

        img[22][12] = '@';
        img[23][12] = '@';
        img[22][13] = '@';
        img[23][13] = '@';
        img[23][14] = '@';
        return img;

    }

    static char[][] getPenguin() {
        char[][]img=new char [50][45];
        for(int y=0; y<50; y++ ){
            for(int x=0; x<45; x++){
                img[y][x]=' ';
            }
        }
        // outer body
        
        for(int i = 14; i <= 24; i++){
            img[1][i] = '_';
        }

        

        img[2][13] = '/';
        img[3][12] = '/';
        img[3][11] = '_';
        img[4][10] = '/';
        img[5][9] = '/';
        img[6][8] = '/';
        img[7][7] = '/';
        img[8][6] = '/';
        img[9][6] = '|';
        img[10][6] = '|';
        img[11][6] = '|';
        img[12][7] = '\\';
        img[13][8] = '\\';
        img[14][8] = '/';
        img[15][7] = '/';
        img[16][6] = '/';
        img[17][5] = '/';
        img[18][4] = '/';
        img[19][3] = '/';

        for(int i = 20; i <= 26; i++){
            img[i][2] = '|';
        }

        img[27][2] = '\\';
        img[28][3] = '\\';
        img[28][4] = '/';
        img[27][5] = '/';
        img[26][5] = '|';
        img[25][6] = '/';
        img[24][6] = '|';
        img[23][6] = '|';
        img[22][7] = '/';

        for(int i = 23; i <= 30; i++){
            img[i][7] = '|';
        }

        img[31][8] = '\\';
        img[32][9] = '\\';
        img[33][10] = '\\';
        img[34][11] = '\\';

        img[2][25] = '\\';
        img[3][26] = '\\';
        img[3][27] = '_';
        img[4][28] = '\\';
        img[5][29] = '\\';
        img[5][30] = '_';
        img[6][31] = '\\';

        for(int i = 32; i <= 36; i++){
            img[6][i] = '_';
        }

        img[7][37] = '\\';
        img[7][38] = '_';
        img[7][39] = '_';
        img[7][40] = '_';
        img[7][41] = '_';
        img[8][41] = '/';
        img[9][40] = '/';
    
        for(int i = 39; i >= 34; i--){
            img[9][i] = '_';
        }

        img[10][33] = '/';
        img[11][32] = '/';
        img[12][31] = '/';

        for(int i = 31; i <= 40; i++){
            img[i - 18][i] = '\\';
        }

        for(int i = 23; i <= 29; i++){
            img[i][41] = '|';
        }

        img[29][40] = '\\';
        img[28][39] = '\\';
        img[27][38] = '\\';
        img[26][37] = '\\';
        img[25][37] = '|';
        img[24][37] = '|';
        img[23][36] = '\\';
        
        for(int i = 23; i <= 30; i++){
            img[i][35] = '|';
        }

        img[31][34] = '/';
        img[32][33] = '/';
        img[33][32] = '/';
        img[34][31] = '/';

        for(int i = 12; i <= 30; i++){
            img[34][i] = '_';
        }
        // feet

        img[35][15] = '|';
        img[36][15] = '|';
        img[35][19] = '|';
        img[36][20] = '\\';
        img[37][16] = '\\';
        img[37][17] = '/';
        img[37][18] = '\\';
        img[37][19] = '/';
        img[37][20] = '\\';
        img[37][21] = '\\';

        img[35][24] = '|';
        img[36][24] = '|';
        img[35][28] = '|';
        img[36][29] = '\\';
        img[37][25] = '\\';
        img[37][26] = '/';
        img[37][27] = '\\';
        img[37][28] = '/';
        img[37][29] = '\\';
        img[37][30] = '\\';

        // inner body

        for(int i = 16; i <= 24; i++){
            img[4][i] = '_';
        }

        img[5][25] = '\\';
        img[5][26] = '_';
        img[6][27] = '\\';
        img[6][28] = '_';
        img[7][29] = '\\';
        img[8][30] = '\\';
        img[9][30] = '/';
        img[10][29] = '/';
        img[10][28] = '_';
        img[11][27] = '/';
        img[12][26] = '/';
        img[13][26] = '|';
        img[14][27] = '\\';
        img[15][28] = '\\';
        img[16][29] = '\\';
        img[17][30] = '\\';

        for(int i = 17; i <= 28; i++){
            img[i][30] = '|';
        }

        img[29][30] = '/';
        img[30][29] = '/';
        img[31][28] = '/';

        img[5][15] = '/';
        img[6][14] = '/';
        img[7][13] = '/';
        img[8][12] = '/';
        img[9][12] = '|';
        img[10][13] = '\\';
        img[11][14] = '\\';
        img[12][14] = '|';
        img[13][13] = '/';

        for(int i = 14; i <= 28; i++){
            img[i][12] = '|';
        }

        img[29][13] = '\\';
        img[30][14] = '\\';
        img[31][15] = '\\';

        for(int i = 16; i <= 27; i++){
            img[31][i] = '_';
        }

        // eye

        img[7][23] = '@';
        return img;
    }

    static char[][] getMiffy() {
        char[][]img=new char [45][45];
        for(int y=0; y<15; y++ ){
            for(int x=0;x<15;x++){
                img[y][x]=' ';
            }
        }
        return img;
    }

}