public class App {
    public static void main(String[] args) {
        Classroom.INSTANCE.print();
        
        // Classroom cannot be instantiated.
        // new Classroom();
    }
}

class Classroom {
    public static final Classroom INSTANCE;
    /**
     * Private impede que sejam criadas outras instancias 
     * alem de INSTANCE que é unica.
     */
    private Classroom(){}
    /**
     * Construtor estatico.
     */
    static {
        INSTANCE = new Classroom();
    };

    public final void print() {
        System.out.println("I am a Classroom");
    }  
}

/*
class Classroom {
    public static void print() {
        System.out.println("I am a Classroom");
    }
}
*/
