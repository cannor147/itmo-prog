package search;

public class BinarySearchSpan {

    public static void main(String[] args) {
        int key = Integer.parseInt(args[0]);
        int size = args.length;
        int[] arr = new int[--size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(args[i + 1]);
        }
        int rightAnswer = recursiveBinarySearch(arr, -1, size, key);
        int leftAnswer = iterativeBinarySearch(arr, -1, size, key);
        System.out.println(leftAnswer + " " + (rightAnswer - leftAnswer + 1));
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
            return left;
        } else {
            middle = (left + right) / 2;
            if (arr[middle] < key) {
                return recursiveBinarySearch(arr, left, middle, key);
            } else {
                return recursiveBinarySearch(arr, middle, right, key);
            }
        }
    }
}
