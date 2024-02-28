package search;

public class BinarySearchClosestD {

    // Pred: array is parsable && array.length > 1 && array is sorted down to = P1
    // Post: result or closest element for result
    public static void main(String[] args) {

        // Pred: P1
        int target = Integer.parseInt(args[0]);
        // Post: target - искомое значение

        // Pred: P1
        int[] ints = new int[args.length - 1];
        // Post: ints.length > 0

        int sum = 0;

        //I: ints[i]` = (int) args[i]`
        for (int i = 1; i < args.length; i++) {
            //I && ints[i]` = (int) args[i]` && i < args.length
            int temp = Integer.parseInt(args[i]);
            ints[i - 1] = temp;
            sum += temp;
            //I: ints[i]` = (int) args[i]` && i < args.length
        }
        // Pred: P1
        if (sum % 2 == 0) {
            System.out.println(binarySearchRecursive(ints, 0, ints.length - 1, target));
        } else {
            System.out.println(binarySearchIterative(ints, target));
        }
        // Post: result or closest element for result
    }

    // Pred: Array is sorted down to && array.length > 0 && left >=0 && right < array.length
    // left < left` && right` < right
    // Post: result = min i: arr[i] <= x || the closest elem
    public static int binarySearchRecursive(int[] array, int left, int right, int target) {
        // Pred: left <= right = P1

        // Pred: mid -> left + (right - left) / 2
        int mid = left + (right - left) / 2;
        // Post: P1 && I && mid == (left + (right - left) / 2)

        // Pred: P1 && I && array[mid] <= target
        if (left <= right) {
            if (array[mid] == target) {
                // Pred: array[mid] == target
                return array[mid];
                // Post: we find target in array -> return target
            } else if (array[mid] < target) {
                // Pred: P1 && I && array[mid] <= target && result = mid
                return binarySearchRecursive(array, left, mid - 1, target);
                // mid + 1 in [left, array,length)
            } else {
                // Pred: P1 && I && array[mid] > target && result = mid
                return binarySearchRecursive(array, mid + 1, right, target);
                // mid + 1 in [0, right]
            }
        } else {
            // Pred: left > right
            return checkElement(array, target, left, right);
        }
        // Post: if target in array -> index
        // else closest to target element
    }

    // Pred: Array is sorted down to && array.length > 0 && left >=0 && right < array.length
    // left < left` && right` < right
    // Post: result = min i: arr[i] <= x || the closest elem
    public static int binarySearchIterative(int[] array, int target) {
        // Pred: True
        int left = 0;
        int right = array.length - 1;
        // left == 0, right == array.length - 1

        // Inv: left' <= x <= right'
        while (left <= right) {
            // Inv && (2 * mid - l - r == 0) || (2 * mid + 1 == l + r)
            int mid = left + (right - left) / 2;
            // Inv && mid == middle elem

            if (array[mid] == target) {
                // Pred: array[mid] == target
                return array[mid];
                // Post: return array[mid]
            } else if (array[mid] < target) {
                // Pred: array[mid] <= target
                right = mid - 1;
                // Post: result = mid && left' <= x <= right'
            } else {
                // Pred: array[mid] > target
                left = mid + 1;
                // Post: left' <= x <= right'
            }
        }

        return checkElement(array, target, left, right);

        // Post: if target in array -> index
        // else closest to target element
    }


    // Pred: True
    // Post: closest element in array = Q
    private static int checkElement(int[] array, int target, int left, int right) {
        if (right < 0) {
            // Pred: cond
            return array[left];
            // Post: Q
        } else if (left >= array.length) {
            // Pred: cond1
            return array[right];
            // Post: Q
        } else {
            // Pred: !cond && !cond1

            if (Math.abs(array[left] - target) < Math.abs(array[right] - target)) {
                // Pred: cond2
                return array[left];
            } else {
                // Pred: cond2
                return array[right];
            }
            // Post: Q
        }
    }
}
