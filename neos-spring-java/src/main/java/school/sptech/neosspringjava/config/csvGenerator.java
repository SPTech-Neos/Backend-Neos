package school.sptech.neosspringjava.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class csvGenerator {
    public static void main(String[] args) {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataHoraFormatada = dataHoraAtual.format(formatter);
        // Dados de exemplo
        List<String[]> linhas = new ArrayList();
        
        linhas.add(new String[]{"Data", "Nome", "Idade", "Email"});
        linhas.add(new String[]{dataHoraFormatada,"João", "25", "joao@example.com"});
        linhas.add(new String[]{dataHoraFormatada,"Maria", "30", "maria@example.com"});
        linhas.add(new String[]{dataHoraFormatada,"raimundo", "30", "mundinho@nonato.com"});
        linhas.add(new String[]{dataHoraFormatada,"Josefa", "30", "josefa@nonato.com"});

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
            String caminhoArquivo = caminhoCompleto + File.separator + "Dados.csv";

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