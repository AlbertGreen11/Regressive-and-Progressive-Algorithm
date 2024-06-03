public class Main {
    public static void main(String[] args) {
        Progres progres = new Progres();

        System.out.println(progres.loadKnowledgeBaseSet("Data\\G\\rules.txt"));
        System.out.println(progres.loadFactSet("Data\\G\\facts.txt"));
        System.out.println(progres.execute());
    }
}

/*
    Data\\G\\facts.txt
 */