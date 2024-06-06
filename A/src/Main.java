public class Main {
    public static void main(String[] args) {
        System.out.println("Progress");
        Progress progres = new Progress();

        System.out.println(progres.loadKnowledgeBaseSet("Data\\J\\rules.txt"));
        System.out.println(progres.loadFactSet("Data\\J\\facts.txt"));
        System.out.println(progres.execute());
    }
}

/*
    Data\\G\\facts.txt
 */