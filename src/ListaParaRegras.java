import java.util.ArrayList;
/*
    Autor - Vinicius Guardieiro Sousa 01/09/2019
    Essa classe serve para auxiliar a classe regras, pois cada posição da tabela é uma lista
 */
public class ListaParaRegras {
    private ArrayList<Integer> estadosPossiveis = new ArrayList<Integer>();
    //retornar o elemento do estadosPossiveis
    protected int estadoPosicao(int posicao){
        return estadosPossiveis.get(posicao);
    }
    //metodo para pegar os estadosPossiveis
    protected ArrayList<Integer> getEstadosPossiveis(){
        return this.estadosPossiveis;
    }
    //metodo para adicionar na lista
    protected void addLista(Integer valor){
        estadosPossiveis.add(valor);
    }
    //metodo para remover o ultimo elemento
    protected void removeLista(int valor){
        estadosPossiveis.remove(valor);
    }

}
