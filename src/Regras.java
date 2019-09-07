import java.util.ArrayList;
/*
    Autor - Vinicius Guardieiro Sousa 01/09/2019
    Essa classe define a tabela de transições de um AFN
 */
public class Regras extends ListaParaRegras{
    private ListaParaRegras[][] tabelaDeRegras;

    //construtor para definir o tamanho da tabela de transições e colocar todos os valores como -1
    public Regras(int qtdEstados, int qtdSimbolos){
        this.tabelaDeRegras =new ListaParaRegras[qtdEstados][qtdSimbolos];
        try {

            for (int i = 0; i < qtdEstados; i++) {
                for (int j = 0; j < qtdSimbolos; j++) {
                    Integer a = new Integer(-1);
                    tabelaDeRegras[i][j] = new ListaParaRegras();
                    this.tabelaDeRegras[i][j].addLista(a);

                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //método para definirmos as regras
    public void setRegra(int estado, int simbolo,int[] estadoResultante){

        //remove o ultimo valor que é o -1
        this.tabelaDeRegras[estado][simbolo].removeLista(0);

        //adiciona os valores informados
        for(int i = 0; i <estadoResultante.length;i++)
            this.tabelaDeRegras[estado][simbolo].addLista(estadoResultante[i]);

    }
    public ArrayList<Integer> estadoResultante(int estado, int simbolo){
        return this.tabelaDeRegras[estado][simbolo].getEstadosPossiveis();
    }

}
