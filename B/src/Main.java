public class Main {
    public static void main(String[] args) {
        System.out.println("Regress\n");
        Regress regress = new Regress();

        regress.loadKnowledgeBaseSet("Data\\J\\rules.txt");
        regress.loadFactSet("Data\\J\\facts.txt");

        System.out.println(regress.returnCurrentFactSet());

        System.out.println("q: " + regress.execute("q"));
        System.out.println(regress.returnCurrentFactSet());

        regress.loadFactSet("Data\\J\\facts.txt");
        System.out.println("x: " + regress.execute("x"));
        System.out.println(regress.returnCurrentFactSet());

        regress.loadFactSet("Data\\J\\facts.txt");
        System.out.println("k: " + regress.execute("k"));
        System.out.println(regress.returnCurrentFactSet());

        regress.loadKnowledgeBaseSet("Data\\K\\rules.txt");
        regress.loadFactSet("Data\\K\\facts.txt");
        System.out.println("h: " + regress.execute("h"));
        System.out.println(regress.returnCurrentFactSet());

        regress.loadKnowledgeBaseSet("Data\\L\\rules.txt");
        regress.loadFactSet("Data\\L\\facts.txt");
        System.out.println("a: " + regress.execute("a"));
        System.out.println(regress.returnCurrentFactSet());
    }
}
