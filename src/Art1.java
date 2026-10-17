public class Art1 {
    public static void main(String[] args) throws Exception {
        while (true) {
            printX(70);
            printPyramid(30);
            printX(30);
        }

    }

    static void printPyramid(int cols) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            for (int col = 0; col <= i; col++) {
                System.out.print("*");
            }
            System.out.println();
            Thread.sleep(20);
        }
        for (int i = 8; i > 0; i--) {
            for (int col = 0; col <= i; col++) {
                System.out.print("*");
            }
            System.out.println();
            Thread.sleep(20);
        }
    }

    static void printX(int cols) throws InterruptedException {
        for (int i = 0; i < cols; i++) {
            for (int col = 0; col <= cols; col++) {
                if (col == i || col == cols - i) {
                    System.out.print("*"); // count = count + 1;
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
            Thread.sleep(20);
        }
    }
}

