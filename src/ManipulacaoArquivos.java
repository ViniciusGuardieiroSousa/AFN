import java.io.*;
import java.util.ArrayList;
/*
    Autor - Vinicius Guardieiro Sousa 01/09/2019
    Essa classe serve para efetuar leitura e escrita em arquivos
 */
public class ManipulacaoArquivos {
    public void salvarArquivos(String path, String serSalvo)throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path,true));
        buffWrite.append(serSalvo);
        buffWrite.close();

    }
    public ArrayList<String> lerArquivos(String path) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(path));
        ArrayList<String> linhas1 = new ArrayList<String>();
        String linha = "";
        while (true) {
            if (linha != null) {
                linhas1.add(linha);

            } else
                break;
            linha = br.readLine();
        }
        br.close();
        return linhas1;
    }

}
