package search;

public class BinarySearch {

    // Pred: array is parsable && array.length > 1 && array is sorted && array[0] >= array[array.length - 1] = P1
    // Post: result or array.length
    public static void main(String[] args) {

        // Pred: P1
        int target = Integer.parseInt(args[0]);
        // Post: target` = args[0]

        // Pred: P1
        int[] ints = new int[args.length - 1];
        // Post: ints.length > 0


        //I: mass[i]` = (int) args[i]`
        for (int i = 1; i < args.length; i++) {
            //I && mass[i]` = (int) args[i]` && i < args.length
            ints[i - 1] = Integer.parseInt(args[i]);
            //I: mass[i]` = (int) args[i]` && i < args.length
        }
        // Pred: P1
        System.out.println(binarySearchRecursive(ints, 0, ints.length - 1, target, ints.length));
    }

    // Pred: Array is sorted down to && array.length > 0 && left >=0 && right < array.length
    // left < left` && right` < right
    // Post: result = min i: arr[i] <= x || arr.length if no such i in
    public static int binarySearchRecursive(int[] array, int left, int right, int target, int result) {

        if (left <= right) {
            // Pred: left <= right = P1

            // Pred: mid -> left + (right - left) / 2
            int mid = left + (right - left) / 2;
            // Post: P1 && I && mid == (left + (right - left) / 2)

            // Pred: P1 && I && array[mid] <= target
            if (array[mid] <= target) {
                // Pred: P1 && I && array[mid] <= target
                result = mid;
                // Post: P1 && I && array[result] <= target

                // Pred: P1 && I && array[mid] <= target && result = mid
                return binarySearchRecursive(array, left, mid - 1, target, result);
                // mid + 1 in [left, array,length)
            } else {
                // Pred: P1 && I && array[mid] > target && result = mid
                return binarySearchRecursive(array,mid + 1, right, target, result);
                // mid + 1 in [0, right]
            }
            // Post: left' > right'
        }
        return result;
        // Post: result = min i: arr[i] <= x || arr.length if no such i
    }

    // Pred: Array is sorted && array[left] >= array[right] && array.length > 0 && left >= 0 && right < array.length
    // left < left` && right` < right
    // Post: result = min i: arr[i] <= x || arr.length if no such i in
    public static int binarySearchIterative(int[] array, int target) {

        // Pred: P1
        int left = 0;
        int right = array.length - 1;
        int result = array.length;
        // Post: left = 0 && right = array.length - 1 && result = array.length

        // I: left' <= x <= right'
        while (left <= right) { // = cond
            // Inv && (2 * mid - l - r == 0) || (2 * mid + 1 == l + r)
            int mid = left + (right - left) / 2;
            // Inv && mid == middle elem
            if (array[mid] <= target) {
                // Pred: array[mid] <= target
                result = mid;
                right = mid - 1;
                // Post: result = mid && left' <= x <= right'
            } else {
                // Pred: I && array[mid] > target
                left = mid + 1;
                // Post: left' <= x <= right'
            }
        }
        return result;
        // Post: result = min i: arr[i] <= x || arr.length if no such i
    }
}