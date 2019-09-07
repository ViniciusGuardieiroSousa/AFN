import java.util.ArrayList;
import java.util.List;
/*
    Autor - Vinicius Guardieiro Sousa 01/09/2019
    Essa classe define o alfabeto do AFN
 */
public class Alfabeto {
    private List<String> simbolos = new ArrayList<>();
    public void addElemento(String simbolo){
        this.simbolos.add(simbolo);
    }
    public int posicaoSimbolo(String simbolo){
        return simbolos.indexOf(simbolo);
    }
    public int qtdElementos(){
        return this.simbolos.size();
    }
}
