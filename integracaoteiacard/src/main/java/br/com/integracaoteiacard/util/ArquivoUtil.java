package br.com.integracaoteiacard.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author José
 */
public abstract class ArquivoUtil {
 
    private static final String CAMINHO_EM_DISCO = "C:\\teste\\uploads";

    public static String salvarArquivo(String caminhoRelativo, String nomeArquivo, InputStream stream) {
        try {
            String caminhoCompletoArquivo = CAMINHO_EM_DISCO + caminhoRelativo + nomeArquivo;
            criarPastasNecessarias(CAMINHO_EM_DISCO + caminhoRelativo);
            OutputStream out = new FileOutputStream(new File(caminhoCompletoArquivo));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = stream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            stream.close();
            out.flush();
            out.close();
            return caminhoCompletoArquivo;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void criarPastasNecessarias(String caminhoPasta) {
        File file = new File(caminhoPasta);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
