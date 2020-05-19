package search;

public class BinarySearch {

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] a = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            a[i - 1] = Integer.parseInt(args[i]);
        }
        int iterativeAnswer = iterativeBinarySearch(a, -1, a.length, x);
        int recursiveAnswer = recursiveBinarySearch(a, -1, a.length, x);
        if (iterativeAnswer != recursiveAnswer) {
            System.out.println("Mistake");
            return;
        }
        System.out.println(iterativeAnswer);
    }

    public static int iterativeBinarySearch(int[] arr, int left, int right, int key) {
        int middle;
        while (right - left > 1) {
            middle = (left + right) / 2;
            if (arr[middle] <= key) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return right;
    }

    public static int recursiveBinarySearch(int[] arr, int left, int right, int key) {
        int middle;
        if (right - left <= 1) {
            return right;
        } else {
            middle = (left + right) / 2;
            if (arr[middle] <= key) {
                return recursiveBinarySearch(arr, left, middle, key);
            } else {
                return recursiveBinarySearch(arr, middle, right, key);
            }
        }
    }
}
