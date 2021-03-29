package com.sc.np;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Digit {
    int[][] weight = new int[][]{{1,2,3},
                                 {4,5,6},
                                 {7,8,9}};

    private List<String> rows = new ArrayList<>();

    Map<String, Integer> numberKeyMap = new HashMap<String, Integer>(){{
        put("246789", 0);
        put("69", 1);
        put("25678", 2);
        put("25689", 3);
        put("4569", 4);
        put("24589", 5);
        put("245789", 6);
        put("269", 7);
        put("2456789", 8);
        put("245689", 9);
    }
    };

    public Digit(String rows){
        this.rows.add(rows);
    }

    public List<String> getRows() {
        return rows;
    }

    public char getNumberByKey(){

        if(!isValid()){
            return (char)'?';
        }

        StringBuilder sbKey = new StringBuilder();

        for(int i=0; i<3; i++ ){
            for(int j=0; j<3;j++){
                char[] arr = rows.get(i).toCharArray();

                if(('_' == (arr[j]) || '|' == (arr[j]))){
                    sbKey.append(weight[i][j]);
                }
            }
        }

        if(numberKeyMap.containsKey(sbKey.toString())) {
            return Character.forDigit(numberKeyMap.get(sbKey.toString()),10);
        }else{
            return (char)'?';
        }
    }

    private boolean isValid(){
        if(rows.isEmpty() || rows.size() !=3) {
            return false;
        }

        return rows.stream().allMatch(a-> a.length() ==3);
    }

}
