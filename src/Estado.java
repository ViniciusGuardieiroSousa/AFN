import java.util.ArrayList;
import java.util.List;
/*
    Autor - Vinicius Guardieiro Sousa 01/09/2019
    Essa classe define os estados do AFN
 */
public class Estado {
    private List<String> estado = new ArrayList<>();
    public void addEstado(String estado){
        this.estado.add(estado);
    }
    public int posicaoEstado(String estado){
        return this.estado.indexOf(estado);
    }
    public String estadoNaPosicao(int posicao){
        return estado.get(posicao);
    }
    public int qtdElementos(){
        return this.estado.size();
    }
    public String ultimoEstado(){
        return this.estado.get(this.estado.size()-1);
    }
    public String removeUltimoEstado(){
        String a = ultimoEstado();
        this.estado.remove(a);
        return a;
    }
    public void imprimirEstados(){
        for(int i = 0; i < this.estado.size();i++){
            System.out.print(estado.get(i));
        }
    }
}
