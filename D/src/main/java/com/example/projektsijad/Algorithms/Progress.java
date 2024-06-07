package com.example.projektsijad.Algorithms;

import com.example.projektsijad.interfaces.IProgressive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Progress implements IProgressive {
    private List<String> rules = new ArrayList<>();
    private List<String> facts = new ArrayList<>();

    @Override
    public boolean loadKnowledgeBaseSet(String filename) {
        this.eraseKnowledgeBase();

        try {
            Scanner scanner = new Scanner(new File(filename));

            while(scanner.hasNextLine()) {
                this.rules.add(scanner.nextLine());
            }

            scanner.close();

            return true;

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public boolean loadFactSet(String filename) {
        this.eraseFactSet();

        try {
            Scanner scanner = new Scanner(new File(filename));

            for(String s: scanner.nextLine().split(",")) {
                this.facts.add(s);
            }

            scanner.close();

            return true;

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public void eraseFactSet() {
        this.facts = new ArrayList<>();
    }

    @Override
    public void eraseKnowledgeBase() {
        this.rules = new ArrayList<>();
    }

    @Override
    public List<String> returnCurrentFactSet() {
        return this.facts;
    }

    @Override
    public List<String> execute() {
        List<String> activatedRules = new ArrayList<>();
        List<String> matchingRules = new ArrayList<>();

        this.rules.forEach(rule -> {
            String[] ruleSplit = rule.split("<-");

            String[] tail;

            if ( ruleSplit.length > 1 ) {
                tail = ruleSplit[1].split(",");
            } else {
                tail = new String[]{};
            }

            boolean correct = true;

            for(String element: tail) {
                if (!this.facts.contains(element)) {
                    correct = false;
                }
            }

            if (correct) {
                matchingRules.add(rule);
            }
        });

        while (matchingRules.size() > 0) {
            String r = matchingRules.remove(0);

            String[] rSplit = r.split("<-");
            String head = rSplit[0];

            activatedRules.add(r);

            if (!this.facts.contains(head)) {
                this.facts.add(head);
            }

            for (String rule : this.rules) {
                if (activatedRules.contains(rule) || matchingRules.contains(rule)) {
                    continue;
                }

                String[] ruleSplit = rule.split("<-");
                String[] tail = ruleSplit[1].split(",");

                boolean correct = true;

                for (String element : tail) {
                    if (!this.facts.contains(element)) {
                        correct = false;
                    }
                }

                if (correct) {
                    matchingRules.add(rule);
                }
            }
        }

        return this.facts;
    }
}

/*
init:
    S – zbiór reguł spełnionych, 𝑆 ⊆ 𝑅, początkowo 𝑆 ≔ ∅
    A – zbiór reguł aktywowanych, 𝐴 ⊆ 𝑅, początkowo 𝐴 ≔ ∅
begin
    𝑆: = ∅
    𝐴 ≔ ∅
    Na podstawie 𝐹 i 𝑅 wyznacz zbiór S

while 𝑆 ≠ ∅
    1. Wybierz regułę 𝑟 ∈ 𝑆 zgodnie ze strategią.
    2. Uaktywnij regułę 𝑟 i dopisz jej konkluzję do 𝐹.
    3. Dopisz regułę 𝑟 do zbioru A: 𝐴: = 𝐴 ∪ {𝑟}
    4. Na podstawie 𝐹 i 𝑅/𝐴 wyznacz nowy zbiór S
end while
end function

 */
/*
        rules

        p<-a,b,c
        a<-d
        q<-a,c
        n<-b,c
        h<-p,a
        p<-d,n


        facts

        b,c,d
     */