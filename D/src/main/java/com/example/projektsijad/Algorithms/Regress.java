package com.example.projektsijad.Algorithms;

import com.example.projektsijad.interfaces.IRegressive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Regress implements IRegressive {

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
    public boolean execute(String goal) {
        List<String> matchingRules = new ArrayList<>();

        boolean premiseIsTrue;

        if(this.facts.contains(goal)) {
            return true;
        }

        premiseIsTrue = false;

        this.rules.forEach(rule -> {
            String[] ruleSplit = rule.split("<-");

            String head = ruleSplit[0];

            if (head.equals(goal)) {
                matchingRules.add(rule);
            }
        });

        while(!premiseIsTrue && matchingRules.size() != 0) {
            String r = matchingRules.remove(0);
            String[] ruleSplit = r.split("<-");
            String[] tail;

            if ( ruleSplit.length > 1 ) {
                tail = ruleSplit[1].split(",");
            } else {
                tail = new String[]{};
            }

            for(String w : tail) {
                premiseIsTrue = facts.contains(w);

                if(!premiseIsTrue) {
                    premiseIsTrue = execute(w);

                    if(!premiseIsTrue) {
                        break;
                    }
                }
            }

            if(premiseIsTrue) {
                this.facts.add(goal);
            }
        }

        return premiseIsTrue;
    }

    @Override
    public List<String> returnHeadsOfRulesSet() {
        return List.of();
    }
}
/*

fuction 𝑤𝑛𝑅𝑒𝑔𝑟𝑒𝑠(𝑅, 𝐹, 𝑔): bool


 */