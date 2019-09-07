import java.util.ArrayList;
/*
    Autor - Vinicius Guardieiro Sousa 01/09/2019
    Essa classe define o automato finito não deterministico, seus métodos tem como objetivo verificar se uma cadeia pertence à uma linguagem que pode ser descrita com esse automato
 */
public class AFN {
    //atributos, estado inicial armazena o estado Inicial do automato
    //tabelaDeRegras é para configurar as regras que foram implementadas conforme a tabela de   transições
    //conjutoDeEstado armazena todos os estados possiveis, sua posição no arrayList indica a sua posição na linha da tabela
    //estadosFinais, armazena o conjunto dos estado finaispara fins de verificação se a cadeia pertence à linguagem
    //arq serve para manipularmos arquivos, para fazer a leitura do arquivo base.txt para ler e configurar o AFN
    //alfabetoAFN é o conjunto de símbolos pertecentes ao alfabeto
    private int estadoInicial;
    private Regras tabelaDeRegras;
    private Estado conjuntoDeEstados = new Estado(), estadosFinais = new Estado();
    private ManipulacaoArquivos arq;
    private Alfabeto alfabetoAFN = new Alfabeto();
    //construtor, chama configuraAFN
    public AFN() throws Exception{
        configuraAFN();
    }

    //método para configurar o AFN conforme o base.txt
    private void configuraAFN() throws Exception{
        //leitura do arquivo
        arq = new ManipulacaoArquivos();
        ArrayList<String> textoEmArquivo = arq.lerArquivos("base.txt");
        String[] aux;

        //adicionar o alfabeto no alfabetoAFN
        String aux1 = textoEmArquivo.get(1);
        aux = aux1.split(" ");
        for(int i = 0; i < aux.length;i++){
            alfabetoAFN.addElemento(aux[i]);
        }

        //adicionar o conjunto de estados
        aux1 = textoEmArquivo.get(2);
        aux = aux1.split(" ");
        for(int i = 0; i < aux.length;i++){
            conjuntoDeEstados.addEstado(aux[i]);
        }

        //cria as regras
        this.tabelaDeRegras = new Regras(conjuntoDeEstados.qtdElementos(),alfabetoAFN.qtdElementos() );

        //separar as regras
        aux1 = textoEmArquivo.get(3);
        aux = aux1.split(" ");

        for(int i = 0; i < aux.length;i++){

            //separa cada regra nas suas tres partes, estado, simbolo e saida
            String[] aux2 = aux[i].split(";");

            //separa a parte do estado resultante
            String[] aux3 = aux2[2].split(",");

            int[] aux4 = new int[aux3.length];

            for(int j = 0; j < aux3.length; j++){
                aux4[j] = conjuntoDeEstados.posicaoEstado(aux3[j]);

            }

            tabelaDeRegras.setRegra(conjuntoDeEstados.posicaoEstado(aux2[0]),alfabetoAFN.posicaoSimbolo(aux2[1]),aux4);


        }

        //definir estado inicial
        aux1 = textoEmArquivo.get(4);
        estadoInicial = conjuntoDeEstados.posicaoEstado(aux1);

        //define conjunto estados finais
        aux1 = textoEmArquivo.get(5);
        aux = aux1.split(" ");

        for(int i = 0; i < aux.length;i++){
            estadosFinais.addEstado(aux[i]);
        }
    }

    //faz o percurso com busca em profundidade
    public Estado percurssoNoAFN(String cadeia)throws Exception{
        //declaração de variáveis
        int inicio = estadoInicial, i =0 , j, coluna; //inicio armazena o estado em que ele está, i será para percorrer a cadeia, j será para os laços de retição, coluna representa a posição do caracter na tabela de transição
        Estado buscaProfundidade = new Estado(), percurso = new Estado(); //buscaEmProfundidade serve como uma pilha para a busca em profundidade, percurso armazena o caminho feito
        ArrayList<Integer> pilhaDasPosicoesCadeia = new ArrayList<>(); //serve como pilha para armazenar a posição do caracter da cadeia correspondente ao estada do buscaEmProfundidade
        String aux;//string para pegarmos o ultimo estado do buscaEmProfundidade
        ArrayList<Integer> estadosPossiveis = new ArrayList<>();//armazena todos os estados resultantes da regra

        //tratar primeiro caso
        coluna = alfabetoAFN.posicaoSimbolo(Character.toString(cadeia.charAt(i))); //pega o valor da coluna do primeiro caracter
        if(coluna <0) throw new Exception("cadeia com caracteres diferentes do alfabeto"); //verificar se o símbolo pertence ao alfabeto
        estadosPossiveis = tabelaDeRegras.estadoResultante(inicio, coluna);//retorna os estados possiveis para o primeiro caracter com o estado inicial
        percurso.addEstado(conjuntoDeEstados.estadoNaPosicao(inicio)); //adiciona o estado inicial no percurso
        //empilha os estados resultantes e o valor do proximo caracter
        for(j = 0; j < estadosPossiveis.size();j++){
            buscaProfundidade.addEstado(conjuntoDeEstados.estadoNaPosicao(estadosPossiveis.get(j)));
            pilhaDasPosicoesCadeia.add(i+1);
        }
        //garantir que percorra todos os caminhos possíveis
        while(buscaProfundidade.qtdElementos()!=0){
            aux = buscaProfundidade.removeUltimoEstado();//remove ultimo elemento do busca em profundidade
            inicio = conjuntoDeEstados.posicaoEstado(aux); //pega o valor correspondete à sua linha
            int k = i; //verificador se houve retrocesso no i
            i = pilhaDasPosicoesCadeia.remove(pilhaDasPosicoesCadeia.size()-1);// remove o valor do i correspondete ao estado atual

            //se i for menor que k siginifica que retornou na busca em profundidade, logo, o ultimo estado alocado deve ser retirado
            if(i < k){
                percurso.removeUltimoEstado();
            }
            //pegar o valor da coluna pro caracter na posição i
            coluna = alfabetoAFN.posicaoSimbolo(Character.toString(cadeia.charAt(i)));
            if(coluna <0) throw new Exception("cadeia com caracteres diferentes do alfabeto"); //verificar se o símbolo pertence ao alfabeto

            estadosPossiveis = tabelaDeRegras.estadoResultante(inicio, coluna);

            //verifica se existe estadosResultantes
            if(estadosPossiveis.get(0)!=-1){
                percurso.addEstado(aux);
                //verifica se chegou no último elemento da cadeia
                if(i== cadeia.length()-1){
                    //percorre todos os estados resultantes e verifica se algum deles é o estado final
                    for(j = 0;j<estadosPossiveis.size();j++){
                        if(pertenceAoAFD(conjuntoDeEstados.estadoNaPosicao(estadosPossiveis.get(j)))){
                            percurso.addEstado(conjuntoDeEstados.estadoNaPosicao(estadosPossiveis.get(j)));
                            return percurso;
                        }
                    }

                }
                //caso o não seja o último elemento, empilha os estados resultantes na buscaEmProfundidade e empilha o proximo valor do i
                else{
                    for(j = 0; j < estadosPossiveis.size();j++){
                        buscaProfundidade.addEstado(conjuntoDeEstados.estadoNaPosicao(estadosPossiveis.get(j)));
                        pilhaDasPosicoesCadeia.add(i+1);
                    }
                }
            }
        }
        //se a cadeia não pertencer, retornará null
        return null;

    }

    //método para verificar se o ultimo estado é o estado final
    public Boolean pertenceAoAFD(String ultimoEstado){

        for(int i = 0; i < estadosFinais.qtdElementos();i++){
            if(ultimoEstado.equals(estadosFinais.estadoNaPosicao(i)))
                return true;
        }
        return false;
    }
}
