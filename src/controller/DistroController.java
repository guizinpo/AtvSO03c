package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DistroController {
	private String os() {
        return System.getProperty("os.name").toLowerCase();
    }

	 public void exibeDistro() {
	        if (!os().contains("linux")) {
	            System.out.println("Este programa só pode ser executado em um sistema Linux.");
	            return;
	        }

	        try {
	            // Comando para obter as informações da distribuição Linux
	            @SuppressWarnings("deprecation")
				Process processo = Runtime.getRuntime().exec("cat /etc/os-release");
	            BufferedReader leitor = new BufferedReader(new InputStreamReader(processo.getInputStream()));
	            String linha;
	            String nomeDistro = "";
	            String versaoDistro = "";

	            while ((linha = leitor.readLine()) != null) {
	                if (linha.startsWith("PRETTY_NAME=")) {
	                    nomeDistro = linha.split("=")[1].replace("\"", "");
	                } else if (linha.startsWith("VERSION_ID=")) {
	                    versaoDistro = linha.split("=")[1].replace("\"", "");
	                }
	            }
	            leitor.close();

	            System.out.println("Distribuição Linux: " + nomeDistro);
	            System.out.println("Versão: " + versaoDistro);

	        } catch (Exception e) {
	            System.err.println("Erro ao obter informações da distribuição: " + e.getMessage());
	        }
	 }
}
