package com.kajalrawal.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnapsackMaxProfit {

    static int[][] dpMatrix = new int[5][8];

    public static void main(String[] args) throws Exception {
        int[] w = {1,3, 4, 6};
        int[] p = {2,5, 7, 9};

        Arrays.stream(dpMatrix).forEach(row -> Arrays.fill(row, -1));

        System.out.println(maxProfitWithTopDown(p, w, 7, 4));
    }

    List<List<Integer>> result = new ArrayList<>();

    // Max Profit with Recursion
    public static int maxProfitWithRecursion(int[] price, int[] weight, int capacity, int size) {
        if(size == 0 || capacity == 0)
            return 0;
        if(weight[size-1] <= capacity){
            return Math.max(price[size-1] + maxProfitWithRecursion(price, weight, capacity-weight[size-1],size-1),
                    maxProfitWithRecursion(price, weight, capacity,size-1));
        } else {
            return maxProfitWithRecursion(price, weight, capacity,size-1);
        }
    }

    // Max Profit with Memoization
    public static int maxProfitWithMemoization(int[] price, int[] weight, int capacity, int size) {
        if(size == 0 || capacity == 0)
            return 0;
        if(dpMatrix[size][capacity] != -1)
            return dpMatrix[size][capacity];
        if(weight[size-1] <= capacity){
            dpMatrix[size][capacity] = Math.max(price[size-1] + maxProfitWithMemoization(price, weight, capacity-weight[size-1],size-1),
                    maxProfitWithMemoization(price, weight, capacity,size-1));
        } else {
            dpMatrix[size][capacity] = maxProfitWithMemoization(price, weight, capacity,size-1);
        }
        return dpMatrix[size][capacity];
    }

    // Max Profit with Top Down Approach
    public static int maxProfitWithTopDown(int[] price, int[] weight, int capacity, int size) {
        int[][] dpMatrix = new int[size + 1][capacity + 1];
        for (int i = 0; i <= size ; i++) {
            for (int j = 0; j <= capacity ; j++) {
                if (i == 0 || j == 0)
                    dpMatrix[i][j] = 0;
                else if(weight[i-1] <= j){
                    dpMatrix[i][j] = Math.max(price[i-1] + dpMatrix[i-1][j-weight[i-1]],
                            dpMatrix[i-1][j]);
                } else {
                    dpMatrix[i][j] = dpMatrix[i-1][j];
                }
            }
        }
        return dpMatrix[size][capacity];
    }

}

