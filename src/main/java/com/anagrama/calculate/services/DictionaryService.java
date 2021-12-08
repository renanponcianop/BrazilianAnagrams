package com.anagrama.calculate.services;

import com.anagrama.calculate.entities.Anagram;
import com.anagrama.calculate.entities.Dictionary;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class DictionaryService {

    public boolean checkWordExistence(String word) throws IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.dicionario-aberto.net/word/" + word)
                .build(); // defaults to GET

        Response response = client.newCall(request).execute();
        ArrayList<Dictionary> dictionaries = new ArrayList<>();
        Dictionary[] dictionary = mapper.readValue(response.body().byteStream(), Dictionary[].class);
        return dictionary.length > 0 && dictionary[0].getWord().equals(word);
    }

    public Anagram getCheckedAnagrams(int anagramCount, ArrayList<String> anagramsList) throws IOException, InterruptedException {
        Anagram anagram = new Anagram();
        anagram.setAnagramCount(anagramCount);
        anagram.setAnagrams(anagramsList);
        ArrayList<String> anagramsExistList = new ArrayList<>();
        int countAnagramsExists = 0;
        for (int i = 0; i < anagramsList.size(); i++) {
            boolean result = checkWordExistence(anagramsList.get(i));
            if (result){
                anagramsExistList.add(anagramsList.get(i));
                countAnagramsExists+= 1;
            }
        }
        anagram.setAnagramsExist(anagramsExistList);
        anagram.setAnagramExistCount(countAnagramsExists);
        return anagram;
    }
}
