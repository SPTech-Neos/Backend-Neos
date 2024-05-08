package school.sptech.neosspringjava.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class csvGenerator {
    public static void gerarCsv(List<String[]> linhas, String nxx) {

        // Caminho do diretório "log" relativo ao diretório da classe
        String caminhoDiretorio = "log";

        // Indica se os registros devem ser acrescentados ou se um novo arquivo deve ser criado
        boolean append = true; // Defina como false para criar um novo arquivo a cada vez

        try {
            // Constrói o caminho completo do diretório "log"
            String diretorioAtual = System.getProperty("user.dir");
            String caminhoCompleto = diretorioAtual + File.separator + caminhoDiretorio;

            // Verifica se o diretório existe, caso contrário, cria o diretório
            File diretorio = new File(caminhoCompleto);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }

            // Caminho completo do arquivo CSV
            String caminhoArquivo = caminhoCompleto + File.separator + nxx+".csv";

            FileWriter writer = new FileWriter(caminhoArquivo, append);

            // Escreve as linhas no arquivo
            for (String[] linha : linhas) {
                writer.append(String.join(",", linha));
                writer.append("\n");
            }

            writer.flush();
            writer.close();

            System.out.println("Arquivo CSV gerado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao gerar o arquivo CSV: " + e.getMessage());
        }
    }
}