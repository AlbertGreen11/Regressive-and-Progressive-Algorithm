package com.example.projektsijad.interfaces;

import java.util.List;

public interface IProgressive {
    boolean loadKnowledgeBaseSet(String filename);
    boolean loadFactSet(String filename);
    void eraseFactSet();
    void eraseKnowledgeBase();
    List<String> returnCurrentFactSet();
    List<String> execute();
}


/*

h<-a,s
a<-p,q,b
b<-c,q
d<-a,z
s<-b,c

 */