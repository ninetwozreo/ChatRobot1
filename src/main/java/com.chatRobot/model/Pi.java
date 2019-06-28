package com.chatRobot.model;

import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
//import java.util.stream.Collectors;


public class Pi {
    static BigInteger val = new BigInteger("1");

    public static void main(String[] args) {

        int[] nums = {4, 5, 6, 7, 0, 1, 2};
//        nextPermutation(nums);
        System.out.println(search(nums, 0));
//        System.out.println(removeDuplicates(nums));
//        System.out.println(fourSum(nums, 0));
//        System.out.println(threeSumClosest(nums, 1));
//        System.out.println(threeSum(nums));
//        System.out.println(maxArea(nums1));
//        System.out.println(isMatched("abbaa", "ab*ac*a"));//true
//        System.out.println(isMatch("aaa", "aaaa"));//false
//        System.out.println(isMatch("aab", "c*a*b"));//true
//        System.out.println(isMatch("ab", ".*c"));//false
//        System.out.println(isMatch("", ".*"));//false
//        System.out.println(isMatch("aa", "a*"));//true
//        System.out.println(isMatch("mississippi", "mis*is*p*."));//false
//        System.out.println(isMatch("mississippi", "mis*is*ip*."));//true
//        System.out.println(去他么的阶乘(new BigInteger("2018")));

    }

    /********************************************************************************************/
//    给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
//    你的算法时间复杂度必须是 O(log n) 级别。
//
//    如果数组中不存在目标值，返回 [-1, -1]。
//
//    示例 1:
//
//    输入: nums = [5,7,7,8,8,10], target = 8
//    输出: [3,4]
//    示例 2:
//
//    输入: nums = [5,7,7,8,8,10], target = 6
//    输出: [-1,-1]
//    private int[] searchRange(int[] nums, int target,int i) {
//        if(nums[i]==target){
//            for (int j = i; j <nums.length ; j++) {
////                if(nums)
//            }
//        }
//        if(nums[i]<target){
//            i=i/2
//        }
//    }
    public int[] searchRange(int[] nums, int target) {

        return null;
    }


    /********************************************************************************************/
//    假设按照升序排序的数组在预先未知的某个点上进行了旋转。
//
//            ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
//
//    搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
//
//    你可以假设数组中不存在重复的元素。
//
//    你的算法时间复杂度必须是 O(log n) 级别。
//
//    示例 1:
//
//    输入: nums = [4,5,6,7,0,1,2], target = 0
//    输出: 4
//    示例 2:
//
//    输入: nums = [4,5,6,7,0,1,2], target = 3
//    输出: -1
    public static int search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    private static int search(int[] nums, int low, int high, int target) {
        if (low > high)
            return -1;
        int mid = (low + high) / 2;
        if (nums[mid] == target)
            return mid;
        if (nums[mid] < nums[high]) {
            if (nums[mid] < target && target <= nums[high])
                return search(nums, mid + 1, high, target);
            else
                return search(nums, low, mid - 1, target);
        } else {
            if (nums[low] <= target && target < nums[mid])
                return search(nums, low, mid - 1, target);
            else
                return search(nums, mid + 1, high, target);
        }
    }

    /********************************************************************************************/
//    实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
//
//    如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
//
//    必须原地修改，只允许使用额外常数空间。
//
//    以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
//            1,2,3 → 1,3,2
//            3,2,1 → 1,2,3
//            1,1,5 → 1,5,1
//    [1,3,2/    输出
//[3,1,2]
//    预期结果
//[2,1,3]
//
    public static void nextPermutation(int[] nums) {//没弄懂
        int[] numss = new int[nums.length];
        numss = Arrays.copyOf(nums, numss.length);
        Arrays.sort(numss);
        int numss1 = numss[numss.length - 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < nums[i + 1]) {
                if (i == 0) {
                    Arrays.sort(nums);
                    break;

                } else {
                    int t = nums[i + 1];
                    nums[i + 1] = nums[i];
                    nums[i] = t;
                    break;
                }
            }
        }
    }
//       if


    /********************************************************************************************/
//    给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
//
//    不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
//
//    示例 1:
//
//    给定数组 nums = [1,1,2],
//
//    函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
//
//    你不需要考虑数组中超出新长度后面的元素。
//    示例 2:
//
//    给定 nums = [0,0,1,1,1,2,2,3,3,4],
//
//    函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
//
//    你不需要考虑数组中超出新长度后面的元素。
//    说明:

//    为什么返回数值是整数，但输出的答案是数组呢?
//
//    请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
//
//    你可以想象内部操作如下:
//
//     nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
//    int len = removeDuplicates(nums);

//// 在函数里修改输入数组对于调用者是可见的。
//// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
//for (int i = 0; i < len; i++) {
//        print(nums[i]);
//    }
//    private static int recursion(int[] nums,int m,int n){}
//    public static int[] delete(int index, int array[]) {
//        //数组的删除其实就是覆盖前一位
////        int[] arrNew = new int[array.length - 1];
//
//        return array;
//    }
    public static int removeDuplicates(int[] nums) {
        int a = 0;
        int b = 1;
        one:
        while (a < nums.length - 1) {
            while (nums[a] == nums[b]) {
                for (int i = a; i < nums.length - 1; i++) {
                    nums[i] = nums[i + 1];
                }
                if (nums[a] == nums[nums.length - 1]) {

                    break one;
                }
            }

            a++;
            b = a + 1;
        }
        return b;
    }

//        i=

//        for (int i = 0; i < nums.length-2; i++) {
//            int m=i;
//            int n=m+1;
//            int k=n+1;
//            if(nums[i]==nums[n]){
//                while (nums[n]==nums[k]){
//                    k++;
//                }
//                nums[m]=nums[n];
//                nums[n]=nums[k];
//                ii++;
//            }
//
//        }


    /********************************************************************************************/
    //四数之和
//    给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
// 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
//
//    注意：
//
//    答案中不可以包含重复的四元组。
//
//    示例：
//
//    给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//    满足要求的四元组集合为：
//            [
//            [-1,  0, 0, 1],
//            [-2, -1, 1, 2],
//            [-2,  0, 0, 2]
//            ]

//[[-3,-2,2,3],[-3,-1,1,3],[-3,0,0,3],[-3,0,1,2],[-2,-1,0,3],[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> listList = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            for (int i1 = i + 1; i1 < length - 2; i1++) {
                for (int i2 = i1 + 1; i2 < length - 1; i2++) {
                    for (int i3 = i2 + 1; i3 < length; i3++) {
                        if (nums[i] + nums[i1] + nums[i2] + nums[i3] == target) {
                            listList.add(Arrays.asList(nums[i], nums[i1], nums[i2], nums[i3]));

                        }
                    }
                }
            }
        }
        List<List<Integer>> utilList = new ArrayList<>(listList);

        one:
        for (int i = 0; i < utilList.size(); i++) {
            for (int i1 = i + 1; i1 < utilList.size(); i1++) {
                if (ListEques(utilList.get(i1), utilList.get(i))) {
                    utilList.remove(utilList.get(i1));
                    i--;
                    continue one;
                }
            }
        }
//        List<List<Integer>> utilList = new ArrayList<>(listList);
//        List<List<Integer>> utilList1 = new ArrayList<>(listList);
//        Iterator<List<Integer>> iterator = utilList1.iterator();
//        while (iterator.hasNext()) {
//            List<Integer> integerList = iterator.next();
//
//            utilList.remove(integerList);
//            for (List<Integer> list : utilList) {
//                if (ListEques(list, integerList)) {
//                    listList.remove(integerList);
//                }
//            }
//        }
        return utilList;
    }

    //    最接近的三数之和
//    给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
//      使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。/这句不一样
//
//    例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
//
//    与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
    public static int threeSumClosest(int[] nums, int target) {
        int k = 0;
        int d = 0;
        int x;
        int y;
        int z;
        int abs;
        k = Math.abs(target - nums[0] - nums[1] - nums[2]);
        d = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            x = nums[i];
//             y = nums[i + 1];
//             z = nums[i + 2];
//            if (i == 0) {
//
//            }
            for (int j = i + 1; j < nums.length - 1; j++) {
                y = nums[j];
                for (int l = j + 1; l < nums.length; l++) {
                    z = nums[l];
                    abs = Math.abs(target - x - y - z);
                    if (abs < k) {
                        k = abs;
                        d = x + y + z;
                    }
                }
            }
        }
        return d;

    }

    //    最接近的三数之和
//    给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
//      使得与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
//
//    例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
//
//    与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
    public static int findone(List<Integer> newList1, int target) {
        int k = 0;
        int one = 0;
        for (int i = 0; i < newList1.size(); i++) {
            if (i == 0) {
                k = Math.abs(newList1.get(i) - target);
            } else {
                if (Math.abs(newList1.get(i) - target) < k) {
                    k = Math.abs(newList1.get(i) - target);
                    one = i;
                }
            }
        }
        return one;
    }

    public static int threeSumClosest1(int[] nums, int target) {
//        nums1=nums;
        int[] nums1 = Arrays.copyOf(nums, nums.length + 1);//数组扩容
        nums1[nums1.length - 1] = target;
//        System.arraycopy(nums2, 0, m, nums1.length, nums2.length);
        Arrays.sort(nums);
        Arrays.sort(nums1);
        List<Integer> newList = new ArrayList<Integer>();
        List<Integer> listlow = Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer> list = Arrays.stream(nums1).boxed().collect(Collectors.toList());

        int m = 3;
        int n = 3;
        List<Integer> newList1 = new ArrayList<Integer>();

        while (newList.size() < 3) {
            if (listlow.contains(target)) {
                newList.add(target);
                listlow.remove(listlow.indexOf(target));
                list.remove(list.indexOf(target));
                continue;
            }
            int i1 = list.indexOf(target);
            if (newList1.size() == 0) {
                for (int i = i1 - 1; i >= 0 && m > 0; i--) {
                    newList1.add(list.get(i));
                    m--;
                }
                for (int i = i1 + 1; i < list.size() && n > 0; i++) {
                    newList1.add(list.get(i));
                    n--;
                }
            }
            int findone = findone(newList1, target);
            newList.add(newList1.get(findone));
            newList1.remove(findone);


//            if (i1 + n < list.size()  && i1 - m >= 0) {
//                if (Math.abs(list.get(i1 - m) - target) < Math.abs(list.get(i1 + n) - target)) {
//                    newList.add(list.get(i1 - m));
//                    for (int i = i1-2; i >0 ; i--) {
//                        if(newList.size() == 3){
//                            break;
//                        }
//                       if(Math.abs(list.get(i) - target) < Math.abs(list.get(i1 + n) - target)) {
//                           newList.add(list.get(i));
//                       }else {
//
//                       }
//                    }
//                    m++;
//                } else {
//                    newList.add(list.get(i1 + n));
//                    n++;
//                }
//            }
        }


//        Map map= new HashMap();
//        for (int i = 0; i < nums.length; i++) {
//            map.put(i,Math.abs(nums[i]-target));
////            if (nums[i]<target){
////                if(b==0||b==1){
////
////                }
////                b=1;
////            }else if(nums[i]>target){
////                if(b==0||b==1){
////                    k=Math.abs(nums[i]-target)<k?Math.abs(nums[i]-target);
////                }
////                b=true;
////            }else if(nums[i]==target){
////                list.add(target);
////            }
//        }
//        for (int i = 0; i <map.size() ; i++) {
//
//        }
        return newList.get(0) + newList.get(1) + newList.get(2);
    }

    //    给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
//
//    注意：答案中不可以包含重复的三元组。
//
//    例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//    满足要求的三元组集合为：
//            [
//            [-1, 0, 1],
//            [-1, -1, 2]
//            ]
    public static boolean ListEques(List<Integer> list2, List<Integer> list1) {
        boolean b1 = true;
        boolean b2 = true;
        for (int i = 0; i < list1.size(); i++) {
            if (list2.contains(list1.get(i))) {
            } else {
                b1 = false;
            }
        }
        if (b1) {
            for (int i = 0; i < list2.size(); i++) {
                if (list1.contains(list2.get(i))) {
                } else {
                    b2 = false;
                }
            }
        }

        return b1 && b2;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ls = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {  // 跳过可能重复的答案

                int l = i + 1, r = nums.length - 1, sum = 0 - nums[i];
                while (l < r) {
                    if (nums[l] + nums[r] == sum) {
                        ls.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        l++;
                        r--;
                    } else if (nums[l] + nums[r] < sum) {
                        while (l < r && nums[l] == nums[l + 1]) l++;   // 跳过重复值
                        l++;
                    } else {
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        r--;
                    }
                }
            }
        }
        return ls;
    }
//    public static List<List<Integer>> threeSum(int[] nums) {
////        int[] src = {1,2,3,4,5,6,7,8,9,10};
//        List<Integer> list = Arrays.stream( nums ).boxed().collect(Collectors.toList());
////        Collections.addAll(list, nums);
////        List<int[]> ints = Arrays.asList(nums);
//        List<List<Integer>> resultList=new ArrayList<>();
//        List<Integer> integerList=new ArrayList<>();
//        if (nums.length<3){
//            return resultList;
//        }
//        int mm;
//        for (int i = 0; i < list.size(); i++) {
//            List<Integer> utilList=new ArrayList<>(list);
//            integerList.add(utilList.get(i));
//            utilList.remove(utilList.get(i));
//            for (int i1 = 0; i1 < utilList.size(); i1++) {
//                mm = 0 - integerList.get(0) - utilList.get(i1);
//                integerList.add(utilList.get(i1));
//                utilList.remove(utilList.get(i1));
//                if (utilList.contains(mm)){
//
//                    integerList.add(mm);
//                    Boolean B=false;
//                    for (List<Integer> integers : resultList) {
//                        if(ListEques(integerList,integers)){
//                            B=true;
//                            break;
//                        }
//                    }
//                    if(!B){
//                        resultList.add(new ArrayList<>(integerList));
//                    }
//                    integerList.remove(2);
//                }
//                integerList.remove(1);
//                i1--;
//
//            }
//            integerList.clear();
//            list.remove(i);
//            i--;
//        }
//
//        return resultList;
//    }


//    给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

//    说明：你不能倾斜容器，且 n 的值至少为 2。


//    图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。


//    示例:
//
//    输入: [1,8,6,2,5,4,8,3,7]
//    输出: 49

    public static int maxArea(int[] height) {
        int i2 = 0;
        for (int i = 0; i < height.length; i++) {
            for (int i1 = 0; i1 < height.length; i1++) {
                int mm = Math.min(height[i1], height[i]) * Math.abs(i - i1);
                i2 = i2 < mm ? mm : i2;
            }
        }
        return i2;
    }


    //    给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
//
//    请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
//
//    你可以假设 nums1 和 nums2 不会同时为空。
//
//    示例 1:
//
//    nums1 = [1, 3]
//    nums2 = [2]
//
//    则中位数是 2.0
//    示例 2:
//
//    nums1 = [1, 2]
//    nums2 = [3, 4]
//
//    则中位数是 (2 + 3)/2 = 2.5
//    class Solution {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

//        List<Integer> integerList = new ArrayList(Arrays.asList(nums1));
//        boolean b;
//        b = Collections.addAll(integerList, nums1);
//        integerList.addAll(Arrays.asList(nums2));

//            integerList.addAll(nums1);
        //排序


        int[] m = {};
        if (nums1.length != 0 && nums2.length != 0) {

            m = Arrays.copyOf(nums1, nums1.length + nums2.length);//数组扩容
            System.arraycopy(nums2, 0, m, nums1.length, nums2.length);
        } else {
            m = nums1.length == 0 ? nums2 : nums1;
        }
//        integerList.toArray(m);
        for (int i = 0; i < m.length - 1; i++) {
            int k = i;
            for (int i1 = k + 1; i1 < m.length; i1++) {
                if (m[i1] < m[k]) {
                    k = i1;
                }
            }
            if (i != k) {  //交换a[i]和a[k]
                int temp = m[i];
                m[i] = m[k];
                m[k] = temp;
            }
        }
        int length = nums1.length + nums2.length;
        double result;

        if (length % 2 == 0) {
            double first = m[(length / 2)];
            double second = m[(length / 2) - 1];
            result = (first + second) / 2;
        } else {
            result = m[(length / 2)];
        }

        return result;

    }
//    }


    /******************************************************************************************/
    private static BigInteger 去他么的阶乘(BigInteger condition) {
        BigInteger a = new BigInteger("1");
        if (condition.compareTo(val) == 1) {
            a = condition.multiply(去他么的阶乘(condition.subtract(val)));
        }
        return a;
    }

    //    给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
//
//            '.' 匹配任意单个字符。
//            '*' 匹配零个或多个前面的元素。
//    匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
//
//    说明:
//
//    s 可能为空，且只包含从 a-z 的小写字母。
//    p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
//    示例 1:
//
//    输入:
//    s = "aa"
//    p = "a"
//    输出: false
//    解释: "a" 无法匹配 "aa" 整个字符串。
//    示例 2:
//
//    输入:
//    s = "aa"
//    p = "a*"
//    输出: true
//    解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
//    示例 3:
//
//    输入:
//    s = "ab"
//    p = ".*"
//    输出: true
//    解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
//    示例 4:
//
//    输入:
//    s = "aab"
//    p = "c*a*b"
//    输出: true
//    解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
//    示例 5:
//
//    输入:
//    s = "mississippi"
//    p = "mis*is*p*."
//    s = "mississippi"
//    p = "mis*is*ip*."
//    输出: false
//    private static String
    public static boolean isMatched(String s, String p) {
        String mi = "*";
        String point = ".";
        char[] charsp = p.toCharArray();
        Map<Integer, String> points = new HashMap<Integer, String>();
        Map<Integer, String> mis = new HashMap<Integer, String>();
        if (!p.contains(mi) || p.contains(point)) {
            return s.equals(p);
        }
        if (p.contains(mi)) {
            if (p.indexOf(mi) == 0) {

            }
//            char[] charsp = p.toCharArray();
            for (int i = 0; i < charsp.length; i++) {
                if (charsp[i] == mi.charAt(0)) {
                    mis.put(i, p.substring(i - 1, i));
                }
            }

            boolean handlemap = handlemap(mis, p, 5, s);
            if (handlemap) {
                return true;
            }
        }
        if (p.contains(point)) {
            for (int i = 0; i < charsp.length; i++) {
                if (charsp[i] == point.charAt(0)) {
                    points.put(i, point);
                }
            }
        }
        return false;
    }


    private static boolean handlemap(Map<Integer, String> mMap, String p, int allTimes, String s) {
        Set<Integer> integers = mMap.keySet();
//        StringBuffer stringBufferp = new StringBuffer();
//        stringBufferp.append(p);
        for (int i = 0; i < allTimes; i++) {
            for (int i1 = 0; i1 <= i; i1++) {
                String pp = p;
                int lasttime = 2;
//                    i
                for (Integer integer : integers) {//0的问题放在上一步筛选
                    pp = handleMi(mMap.get(integer), pp, integer + lasttime - 2, i1);
                    lasttime = i1;
                    i1 += i1;
                }
                if (pp.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<List<Integer>> produceNUms(int he, int size) {//球同合不同，合可以为空列举
        List<List<Integer>> listList = new ArrayList<List<Integer>>();
        List<Integer> integerList = new ArrayList<Integer>();
//        Math.
        for (int i = 0; i < he * size; i++) {
            for (int j = 0; j < size; j++) {
//                integerList.add(j)
            }
        }
        return null;
    }

    private static String handleMi(String ms, String p, Integer integer, int times) {
//        int times=0;
        StringBuffer sbp = new StringBuffer();

//        Set<Integer> integers = mMap.keySet();
//        for (Integer integer : integers) {//0的问题放在上一步筛选
//            String ms = mMap.get(integer);
        StringBuffer real = new StringBuffer();
        for (int i = 0; i < times; i++) {
            real.append(ms);
        }

        sbp.append(p.substring(0, integer - 1))
                .append(real).
                append(p.substring(integer + 1, p.length()));

//        }
        return sbp.toString();
    }

    /***************************************************************************************************/
    List nuList = new ArrayList();
    /**private static int getMaps(String ss,String target){
     if(ss.contains(target)){
     int i=ss.indexOf(target);

     return getMaps(ss.substring(i,ss.length()),target);
     }else {
     return 0;
     }
     return
     }*/
//    public static boolean isMatch(String s, String p) {
//
//        char point = '.';
//        char mi = '*';
//        String mii = "*";
//        String pointt = ".";
//        boolean b = false;
//        if (s.equals("")) {
//            return false;
//        }
//        int pp = 0;
//        one:
//        for (int m = 0; m < p.length(); m++) {
//            two:
//            for (int i = m; i <= p.length(); i++) {
//                StringBuffer sbp = new StringBuffer("");
//                StringBuffer sbs = new StringBuffer("");
//                sbp.append(p.substring(m, i));
//                String sbpe = sbp.toString();
//                two1:
//                for (int n = 0; n < sbp.length(); n++) {
//                    if (sbpe.charAt(0) == mi) {
//                        sbpe = sbpe.substring(1, sbp.length());
//                        m++;
//                    }
//                }
////            sbs.append(s.su)
//                int sp = 0;
//
//                three:
//                for (int j = 0; j < sbpe.length() + pp; j++) {
////                    if (sp + j + 1 > s.length() && b == true) {
////                        b = false;
////                        break one;
////                    }
//                    if (sbpe.charAt(pp + j) == s.charAt(sp + j)) {
//
////                        continue three;
//
//                    } else if (sbpe.charAt(j) == point) {
////                        continue three;
//
//                    } else if (sbpe.charAt(j) == mi) {
//
//                        if (sbpe.charAt(j - 1) == s.charAt(j)) {
//                            int kk = 0;
//                            for (int k = 0; k < s.length() - j; k++) {
//                                if (sbpe.charAt(j - 1) == s.charAt(j + k)) {
//
//                                } else {
//                                    kk = k - 1;
//                                    break;
//                                }
//                            }
//                            sp += kk;
////                            continue three;
//                        }
////                        else if(sbpe.charAt(j)==point){
////                            return true;
////                        }
//                    } else {
//                        b = false;
//                        break two;
//                    }
//                    if (sp + j + 1 == s.length()) {
//                        if (m + sbpe.length() < p.length()) {
//
//                        }
//                        if (j + 1 == sbpe.length() + pp) {
//
//                            b = true;
//                        break one;
//                        }
//                    }
//                }
//            }
//        }
//        if (b) {
//            if(p.contains(pointt)||p.contains(mii)){
//                for (int i = 0; i < p.length(); i++) {
//                    StringBuffer sb = new StringBuffer();
//                    if(p.charAt(i)==point||p.charAt(i)==mi){
//
//                    }else {
//                        sb.append(p.charAt(i));
//                        if (s.contains(sb)) {
//
//                        } else if (i < p.length() - 1) {
//
//                            if (p.charAt(i + 1) == mi) {
//
//                            } else {
//                                b = false;
//                            }
//                        } else if (i == p.length() - 1) {
//                            b = false;
//                        }
//                    }
//
//                }
//            }else {
//                if (!p.equals(s)){
//                    b=false;
//                }
//            }
//
//        }
//        return b;
//    }
}
