package com.anagrama.calculate.controller;

import com.anagrama.calculate.entities.Anagram;
import com.anagrama.calculate.services.AnagramaService;
import com.anagrama.calculate.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/anagram")
public class AnagramaController {

    @Autowired
    AnagramaService anagramaService;

    @Autowired
    DictionaryService dictionaryService;

    @GetMapping("/{word}")
    public ResponseEntity<?> getAnagramas(@PathVariable String word) throws IOException, InterruptedException {
        int anagramCount = anagramaService.calculateAnagram(word);
        ArrayList<String> anagramsList = anagramaService.getAllAnagrams(word, anagramCount);
        Anagram anagram = dictionaryService.getCheckedAnagrams(anagramCount, anagramsList);
        return ResponseEntity.ok().body(anagram);
    }

}
