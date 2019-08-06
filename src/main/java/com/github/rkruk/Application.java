package com.github.rkruk;

import java.util.Set;
import com.github.rkruk.hashset.StringHashSet;


public class Application {

    public static void main(String[] args) {

        Set<String> set =  new StringHashSet();
        set.add("Ala");
        set.add("Alan");
        set.add("Jakub");
        set.add("Marek");

        System.out.println(set);



    }
}
