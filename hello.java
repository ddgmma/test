
// 输出空心菱形
import java.util.Scanner;

public class Hello {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("输入菱形的高度：");
        int n = scanner.nextInt();

        // 上半部分
        for (int i = 1; i <= n; i++) {
            for (int k = (n - i); k >= 0; k--) {
                System.out.print(" ");
            }
            for (int x = 1; x <= (2 * i - 1); x++) {
                if (i == 1 || i == n || x == 1 || x == (2 * i - 1)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        // 下半部分
        for (int i = (n - 1); i >= 1; i--) {
            for (int k = 0; k <= (n - i); k++) {
                System.out.print(" ");
            }
            for (int x = (2 * i - 1); x >= 1; x--) {
                if (i == 1 || i == n || x == 1 || x == (2 * i - 1)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}
