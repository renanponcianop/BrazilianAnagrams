package com.anagrama.calculate.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Anagram {
    private int anagramCount;
    private int anagramExistCount;
    private ArrayList<String> anagramsExist;
    private ArrayList<String> anagrams;
}
