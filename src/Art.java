import java.util.Random;

public class Art {
    static final Random rand = new Random();
    static final int WIDTH = getTerminalWidth() - 1;
    static final int PENGUIN_WIDTH = 45;
    static final int PENGUIN_HEIGHT = 40;
    
    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        int iterations = 0;
        char[][] nextRows = new char[40][WIDTH]; // store upcoming rows
        for (int i = 0; i < nextRows.length; i++) {
            nextRows[i] = emptyRow();
        }
        char[] prevRow = nextRows[0];

        while (true) {
            // At each lane now on the top row, 7% chance to add a new image
            for (int x = 0; x < WIDTH - PENGUIN_WIDTH; x += PENGUIN_WIDTH) {
                if (isBlank(prevRow, x) && rand.nextDouble() < 0.07) {
                    loadNextRowsWithImage(nextRows, x);
                }
            }
            // Print and remove the top row
            System.out.println(new String(nextRows[0]));
            prevRow = nextRows[0]; // update prev row
            // Shift all rows up
            shiftRowsUp(nextRows);
            Thread.sleep(50); // Delay in ms
            long time = System.currentTimeMillis() - startTime;
            iterations++;

            // System.err.println("average time per frame: " + (time / iterations) + " ms");
        }
        
    }

    static boolean isBlank(char[] prevRow, int x) {
        for (int i = x; i < x + 45; i++) {
            if (prevRow[i] != ' ') {
                return false;
            }
        }
        return true;
    }

    private static void loadNextRowsWithImage(char[][] nextRows, int x) {
        AsciiArt boba = new AsciiArt(getBoba());
        AsciiArt penguin = new AsciiArt(getPenguin());
        AsciiArt miffy = new AsciiArt(getMiffy());
        AsciiArt totoro = new AsciiArt(getTotoro());
        AsciiArt fish = new AsciiArt(getBigFish());
        AsciiArt pumpkin = new AsciiArt(pumpkin());

        AsciiArt[] images = { boba, penguin, miffy, totoro, fish, pumpkin};
        AsciiArt art = images[rand.nextInt(images.length)];

        for (int iy = 0; iy < art.height; iy++) { // height
            for (int ix = 0; ix < art.width; ix++) { // width
                nextRows[iy][x + ix] = art.img[iy][ix];
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

        //signature
        img[29][19] = 'E';
        img[29][20] = 'Y';
        return img;

    }

    static char[][] getPenguin() {
        char[][]img=new char [40][45];
        for(int y=0; y<40; y++ ){
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

        // signature
        img[39][4] = 'E';
        img[39][5] = 'Y';
        return img;
    }

    static char[][] getMiffy() {
        char[][]img=new char [22][23];
        for(int y=0; y<22; y++ ){
            for(int x=0;x<23;x++){
                img[y][x]=' ';
            }
        }

        // ears
        img[1][7] = '_';
        img[2][6] = '/';
        img[2][8] = '\\';
        img[3][5] = '(';
        img[3][9] = ')';
        img[4][5] = '|';
        img[4][9] = '|';
        img[5][5] = '|';
        img[5][9] = '|';

        img[1][16] = '_';
        img[2][15] = '/';
        img[2][17] = '\\';
        img[3][14] = '(';
        img[3][18] = ')';
        img[4][14] = '|';
        img[4][18] = '|';
        img[5][14] = '|';
        img[5][18] = '|';

        img[5][10] = '_';
        img[5][11] = '_';
        img[5][12] = '_';
        img[5][13] = '_';

        // head

        img[6][4] = '/';
        img[7][3] = '/';
        img[8][2] = '/';
        img[6][19] = '\\';
        img[7][20] = '\\';
        img[8][21] = '\\';

        img[9][2] = '|';
        img[9][21] = '|';

        img[10][3] = '\\';
        img[11][4] = '\\';
        img[10][20] = '/';
        img[11][19] = '/';

        for(int i = 5; i <= 18; i++){
            img[11][i] = '_';
        }

        // arms and body

        img[12][5] = '/';
        img[13][4] = '/';
        img[14][3] = '/';
        img[15][2] = '/';
        img[16][2] = '\\';
        img[17][3] = '\\';

        img[12][18] = '\\';
        img[13][19] = '\\';
        img[14][20] = '\\';
        img[15][21] = '\\';
        img[16][21] = '/';
        img[17][20] = '/';

        img[16][4] = '|';
        img[17][4] = '|';
        img[18][4] = '|';
        img[16][19] = '|';
        img[17][19] = '|';
        img[18][19] = '|';

        for(int i = 5; i <= 18; i++){
            img[18][i] = '_';
        }

        // feet

        img[19][8] = '/';
        img[19][12] = '|';
        img[19][16] = '\\';

        img[19][9] = '_';
        img[19][10] = '_';
        img[19][11] = '_';
        img[19][13] = '_';
        img[19][14] = '_';
        img[19][15] = '_';

        // face

        img[8][7] = '@';
        img[8][16] = '@';
        img[9][11] = '\\';
        img[10][12] = '\\';
        img[9][12] = '/';
        img[10][11] = '/';

        // signature
        img[21][4] = 'E';
        img[21][5] = 'Y';
        return img;

        
    }

    static char [][] getTotoro() {
        // Credits to Caroline's project!
        char[][] img = new char[16][29];
        for (int y = 0; y < 16; y++){
            for (int x = 0; x < 29; x++) {
                img[y][x] = ' ';
            }
        }
        //individual character
        img[0][9]  ='/';
        img[0][10] = '\\';
        img[0][18] = '/';
        img[0][19] = '\\';
        img[1][8] = '/';
        img[1][9] = '|';
        img[1][10] = '|';
        img[1][11] = '\\';
        img[1][17] = '/';
        img[1][18] = '|';
        img[1][19] = '|';
        img[1][20] = '\\';
        img[2][8] = '.';
        img[2][9] = '.';
        img[2][10] = '-';
        img[2][11] = '-';
        img[2][12] = '-';
        img[2][13] = '-';
        img[2][14] = '-';
        img[2][15] = '-';
        img[2][16] = '-';
        img[2][17] = '-';
        img[2][18] = '-';
        img[2][19] = '.';
        img[2][20] = '.';
        img[3][6] = '.';
        img[3][7] = '\'';
        img[3][21] = '\'';
        img[3][22] = '.';
        img[4][6] = '/';
        img[4][8] = '(';
        img[4][9] = '*';
        img[4][10] = ')';
        img[4][14] = 'v';
        img[4][18] = '(';
        img[4][19] = '*';
        img[4][20] = ')';
        img[4][22] = '\\';
        img[4][25] = '.';
        img[4][26] = '-';
        img[4][27] = '-';
        img[4][28] = '.';
        img[5][6] = '|';
        img[5][11] = '\'';
        img[5][12] = '_';  
        img[5][13] = '_';
        img[5][14] = '_';
        img[5][15] = '_';
        img[5][16] = '_';  
        img[5][17] = '\'';
        img[5][22] = '|';
        img[5][24] = '/';
        img[5][28] = '|';
        img[6][7] = '\\';
        img[6][21] = '/';
        img[6][22] = '-';
        img[6][23] = '\'';
        img[6][27] = '.';
        img[6][28] = '\'';
        img[7][4] = '.';
        img[7][5] = '-';
        img[7][6] = '\'';
        img[7][8] = '\'';
        img[7][9] = '\'';
        img[7][10] = '-';
        img[7][11] = '.';
        img[7][12] = '.';
        img[7][13] = '-';
        img[7][14] = '-';
        img[7][15] = '-';
        img[7][16] = '.';
        img[7][17] = '.';
        img[7][18] = '-';
        img[7][19] = '\'';
        img[7][21] = '\'';
        img[7][26] = '/';
        img[8][3] = '/';
        img[8][9] = '.';
        img[8][10] = '\'';
        img[8][12] = '^';
        img[8][14] = '^';
        img[8][16] = '^';
        img[8][18] = '\'';
        img[8][19] = '.';
        img[8][24] = '.';
        img[8][25] = '\'';
        img[9][2] = '/';
        img[9][8] = '/';
        img[9][10] ='^';
        img[9][12] = '^';
        img[9][14] = '^';
        img[9][16] = '^';
        img[9][18] = '^';
        img[9][20] = '\\';
        img[9][23] = '/';
        img[10][2] = '|';
        img[10][5] = '|';
        img[10][7] = '|';
        img[10][21] = '|';
        img[10][23] = '|';
        img[11][2] = '\'';
        img[11][3] = '-';
        img[11][4] = '.';
        img[11][5] = '|';
        img[11][7] = '|';
        img[11][21] = '|';
        img[11][23] = '|';
        img[12][6] = '\\';
        img[12][8] = '\\';
        img[12][20] = '/';
        img[12][22] = '/';
        img[13][7] = '\\';
        img[13][9] = '\'';
        img[13][10] = '.';
        img[13][18] = '.';
        img[13][19] = '\'';
        img[13][21] = '/';
        img[14][8] = '\\';
        img[14][11] = '\'';
        img[14][12] = '\'';
        img[14][13] = '-';
        img[14][14] = '-';
        img[14][15] = '-';
        img[14][16] = '\'';
        img[14][17] = '\'';
        img[14][20] = '/';
        img[15][7] = '.';
        img[15][8] = '\'';
        img[15][9] = '_';
        img[15][10] = '_';
        img[15][11] = '_';
        img[15][12] = '_';
        img[15][13] = '/';
        img[15][14] = '-';
        img[15][15] = '\\';
        img[15][16] = '_';
        img[15][17] = '_';
        img[15][18] = '_';
        img[15][19] = '_';
        img[15][20] = '\'';
        img[15][21] = '.';
        img[15][25] = 'C';
        img[15][26] = 'K';
        return img;
    }

      static char[][] getBigFish() {
        // Credits to Samira for this code!
        char[][] img = new char[8][20];
        // fill with empty space
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 20; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        //big fish
        //top 
         
        img[2][7] = '_';
        img[2][8] = '_'; 
        img[2][9] = '_';
        img[2][10] = '_';    
        img[1][11] = '|';   
        img[2][12] = '_';   
        img[2][13] = '_'; 
        img[2][14] = '_';   
        img[2][15] = '_';   
        img[2][16] = '_';   
        img[2][17] = '_';   

        img[3][4] = '\\';     
        img[3][6] = '/';  
        //eye
        img[3][17] = 'o';  

        img[3][18] = '\\';  

        img[4][5] = '>';  
        img[4][19] = '>';  
        img[5][4] = '/';  

        img[5][18] = '/';  
        img[4][18] = '0';  
        img[5][18] = '/';  
//bottom
        img[6][6] = '\\'; 
        img[6][7] = '_';
        img[6][8] = '_'; 
        img[6][9] = '_';
        img[6][10] = '_';    
        img[7][11] = '|';   
        img[6][12] = '_';   
        img[6][13] = '_'; 
        img[6][14] = '_';   
        img[6][15] = '_';   
        img[6][16] = '_';   
        img[6][17] = '_'; 

        img[7][5] = 'S'; 
        img[7][6] = 'F';

        return img;
    }

    public static char[][] pumpkin() {
    // Credits to Emma for this part of the code!
    int rows2 = 13, col2 = 30;
        char[][] arr = new char[rows2][col2];
        for(int y = 0; y < rows2; y++) {
            for(int x = 0; x < col2; x++) {
                arr[y][x] = ' ';
            }
        }
        arr[0][12] = '%';
        arr[1][11] = '/';
        arr[1][12] = '%';
        arr[1][13] = '%';
        arr[2][11] = '/';
        arr[3][11] = '/';
        arr[4][7] = '/';
        arr[4][8] = '\\';
        arr[4][10] = '/';
        arr[4][11] = '/';
        arr[4][12] = '\\';
        arr[4][13] = '\\';
        arr[4][15] = '/';
        arr[4][16] = '\\';
        arr[5][3] = '\\';
        arr[5][4] = '\\';
        arr[5][6] = '/';
        arr[5][9] = '/';
        arr[5][14] = '\\';
        arr[5][17] = '\\';
        arr[5][19] = '/';
        arr[5][20] = '/';
        arr[6][2] = '\\';
        arr[6][5] = '/';
        arr[6][9] = '/';
        arr[6][14] = '\\';
        arr[6][18] = '\\';
        arr[6][21] = '/';
        arr[6][28] = '6';
        arr[7][1] = '\\';
        arr[7][5] = '/';
        arr[7][9] = '/';
        arr[7][14] = '\\';
        arr[7][18] = '\\';
        arr[7][21] = '/';
        arr[7][28] = '|';
        arr[8][1] = '\\';
        arr[8][5] = '/';
        arr[8][9] = '/';
        arr[8][14] = '\\';
        arr[8][18] = '\\';
        arr[8][21] = '/';
        arr[8][24] = '6';
        arr[8][27] = '|';
        arr[8][29] = '|';
        arr[9][1] = '\\';
        arr[9][5] = '/';
        arr[9][9] = '/';
        arr[9][14] = '\\';
        arr[9][18] = '\\';
        arr[9][21] = '/';
        arr[9][24] = '|';
        arr[9][27] = '|';
        arr[9][29] = '|';
        arr[10][1] = '\\';
        arr[10][5] = '/';
        arr[10][9] = '/';
        arr[10][14] = '\\';
        arr[10][18] = '\\';
        arr[10][21] = '/';
        arr[10][23] = '|';
        arr[10][25] = '|';
        arr[10][27] = '|';
        arr[10][29] = '|';
        arr[11][2] = '\\';
        arr[11][5] = '/';
        arr[11][9] = '/';
        arr[11][14] = '\\';
        arr[11][18] = '\\';
        arr[11][20] = '/';
        arr[11][23] = '|';
        arr[11][25] = '|';
        arr[11][27] = '|';
        arr[11][29] = '|';
        arr[12][3] = '\\';
        arr[12][4] = '_';
        arr[12][5] = '/';
        arr[12][6] = '_';
        arr[12][7] = '_';
        arr[12][8] = '_';
        arr[12][9] = '/';
        arr[12][10] = '_';
        arr[12][11] = '_';
        arr[12][12] = '_';
        arr[12][13] = '_';
        arr[12][14] = '\\';
        arr[12][15] = '_';
        arr[12][16] = '_';
        arr[12][17] = '_';
        arr[12][18] = '\\';
        arr[12][19] = '/';
        arr[12][23] = '|';
        arr[12][24] = '_';
        arr[12][25] = '|';
        arr[12][27] = '|';
        arr[12][28] = '_';
        arr[12][29] = '|';

        arr[3][25] = 'E';
        arr[3][26] = 'W';
    
        return arr;

}
}