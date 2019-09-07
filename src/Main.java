public class Main {
    public static void main(String args[]){
        Estado percurso = new Estado();
        try {
            AFN afdTeste = new AFN();
            percurso = afdTeste.percurssoNoAFN("001");
            percurso.imprimirEstados();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
