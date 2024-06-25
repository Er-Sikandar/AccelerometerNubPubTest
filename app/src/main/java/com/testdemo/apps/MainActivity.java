package com.testdemo.apps;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
  private String TAG="MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Todo You may assume that each input would have exactly one solution, and you may not use the same element twice.
        int[] nums = {11,5,10,4};
        int target=9;
        int[] result =twoSum(nums,target);
        Log.e(TAG, "Sum Num Indices: " + result[0] + ", " + result[1]);

        //Todo find the length of the longest substring without repeating characters.
        String str ="abcabcbb";
        Log.e(TAG, "lengthOfLongestSubstring: "+lengthOfLongestSubstring(str));


        startActivity(new Intent(this,AccelerometerActivity.class));

    }
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] { -1, -1 };
    }
        public List<List<Integer>> threeSum(int[] nums, int target) {
            Arrays.sort(nums);
            List<List<Integer>> result = new ArrayList<>();

            for (int i = 0; i < nums.length - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue; // Skip duplicates
                }

                int left = i + 1;
                int right = nums.length - 1;
                int newTarget = target - nums[i];

                while (left < right) {
                    int sum = nums[left] + nums[right];

                    if (sum == newTarget) {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        // Skip duplicates for the second number
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // Skip duplicates for the third number
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;
                    } else if (sum < newTarget) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }

            return result;
        }

    //Todo find the length of the longest substring without repeating characters.
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        if (s.isEmpty()) {
            return maxLength;
        }
        Map<Character, Integer> seen = new HashMap<>();
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (seen.containsKey(currentChar) && seen.get(currentChar) >= left) {
                left = seen.get(currentChar) + 1;
            }
            seen.put(currentChar, i);
            maxLength = Math.max(maxLength, i - left + 1);
        }
        return maxLength;
    }
    public int lengthOfLongestSubstringWithoutMap(String s) {
        int maxLength = 0;
        if (s.isEmpty()) {
            return maxLength;
        }
        int[] lastOccurrence = new int[128]; // Assuming ASCII characters
        Arrays.fill(lastOccurrence, -1); // Initialize all characters' last occurrences to -1
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            int lastSeen = lastOccurrence[currentChar];
            if (lastSeen != -1 && lastSeen >= left) {
                left = lastSeen + 1;
            }
            lastOccurrence[currentChar] = i;
            maxLength = Math.max(maxLength, i - left + 1);
        }
        return maxLength;
    }



}