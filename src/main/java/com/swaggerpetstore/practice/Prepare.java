package com.swaggerpetstore.practice;

import java.util.HashMap;
import java.util.Map;

public class Prepare {


    public static void main(String[] args) {
        int[] arr = {1, 2, 6, 4, 5, 3, 8, 6, 3, 6};
        frequencyOfElements(arr);

    }

    public static void frequencyOfElements(int[] arr) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num:arr){
            frequencyMap.put(num,frequencyMap.getOrDefault(num,0)+1);
        }
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if(entry.getValue()>1){
                System.out.println("Element: " + entry.getKey() + ", Frequency: " + entry.getValue());
            }
        }


    }

}
