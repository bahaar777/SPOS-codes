import java.util.Scanner;

public class WorstFit {
    static void worstFit(int blockSize[], int m, int processSize[], int n) {
        int allocation[] = new int[n];

        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;
        for (int i = 0; i < n; i++) {
            int worstIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (worstIdx == -1 || blockSize[worstIdx] < blockSize[j]) {
                        worstIdx = j;
                    }
                }
            }
            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blockSize[worstIdx] -= processSize[i];
            }
        }
        System.out.println("\nProcess No.\tProcess Size\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.print(allocation[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of memory blocks: ");
        int m = scanner.nextInt();
        int blockSize[] = new int[m];
        System.out.println("Enter the sizes of memory blocks:");
        for (int i = 0; i < m; i++) {
            blockSize[i] = scanner.nextInt();
        }
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        int processSize[] = new int[n];

        System.out.println("Enter the sizes of processes:");
        for (int i = 0; i < n; i++) {
            processSize[i] = scanner.nextInt();
        }
        worstFit(blockSize, m, processSize, n);
        scanner.close();
    }
}
