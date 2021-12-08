package com.anagrama.calculate.services;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnagramaService {

    public int calculateAnagram(String word){
        char[] charactersArray = word.toCharArray();
        int countDuplicatedCharacters = getDuplicatedCharacters(charactersArray);
        int countCharacters = charactersArray.length;
        int charactersFatorial = countCharacters;
        int characterDuplicateFatorial = countDuplicatedCharacters > 0 ? countDuplicatedCharacters : 1;
        while (countCharacters > 1){
            charactersFatorial = charactersFatorial *(countCharacters-1);
            countCharacters--;
        }
        if (countDuplicatedCharacters > 1){
            while (countDuplicatedCharacters > 1){
                characterDuplicateFatorial = characterDuplicateFatorial *(countDuplicatedCharacters-1);
                countDuplicatedCharacters--;
            }
        }
        return charactersFatorial/characterDuplicateFatorial;
    }

    public int getDuplicatedCharacters(char[] charactersArray){
        Map<Character, Integer> map = new HashMap<>();
        for (char character : charactersArray){
            if(map.containsKey(character)) {
                int counter = map.get(character);
                map.put(character, ++counter);
            } else {
                map.put(character, 1);
            }
        }
        int count = 0;
        for(char c : map.keySet()) {
            if(map.get(c) > 1) {
                count += map.get(c);
            }
        }
        return count;
    }

    public ArrayList<String> getAllAnagrams(String word, int possibleAnagramsCount){
        int anagramsCreated = 0;
        String wordShuffled = word;
        ArrayList<String> anagrams = new ArrayList<>();
        while(anagramsCreated != possibleAnagramsCount){
            wordShuffled = shuffle(wordShuffled);
            if (!anagrams.contains(wordShuffled)){
                anagrams.add(wordShuffled);
                anagramsCreated++;
            }
        }
        return anagrams;
    }

    public static String shuffle(String s) {
        List<Character> letters = s.chars().boxed().map(c -> (char) c.intValue()).collect(Collectors.toList());
        Collections.shuffle(letters);
        StringBuilder t = new StringBuilder(s.length());
        letters.forEach(t::append);
        return t.toString();
    }
}
