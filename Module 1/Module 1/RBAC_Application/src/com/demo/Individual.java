package com.demo;

import java.util.ArrayList;
import java.util.Random;

import com.algo.dynamicjobordering;

public class Individual {

    int fitness = 0;
    int[] genes = new int[4];
    int geneLength = 4;
 //   public  static  ArrayList<Integer> l=new ArrayList<>();
    public Individual(int id) {
////        Individual.l.clear();
//        Random randomGenerator = new Random();
//        int min=10,max=100;
//        for (int idx = 1; idx <= 4; ++idx){
//        int randomInt = randomGenerator.nextInt((max - min) + 1) + min;
//     Individual.l.add(randomInt);
//        
//        
//        
//    }
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.abs(dynamicjobordering.memoryloadlist.get(i) % 2);
           // System.out.println("randomInt"+dynamicjobordering.memoryloadlist.get(i)+"%"+dynamicjobordering.memoryloadlist.get(i)%2);
            
        }
        fitness = 0;
    }

    //Calculate fitness
    public void calcFitness() {

        fitness = 0;
        for (int i = 0; i < 4; i++) {
            if (genes[i] == 1) {
                ++fitness;
            }
        }
    }

}
