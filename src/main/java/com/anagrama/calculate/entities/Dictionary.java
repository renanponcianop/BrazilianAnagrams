package com.anagrama.calculate.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Dictionary {
    private String word;
    private String moderator;
    private String derived_from;
    private String deletor;
    private String deleted;
    private String sense;
    private String xml;
    private String creator;
    private String normalized;
    private String word_id;
    private String revision_id;
    private String timestamp;
    private String last_revision;
}
