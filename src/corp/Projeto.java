package corp;

import java.util.HashMap;

import corp.Ocorrencia.PrioridadeDeOcorrencia;
import corp.Ocorrencia.TipoDeOcorrencia;

public class Projeto {

	String nome;
	int ocorrenciasAbertas;
	HashMap<Integer,Ocorrencia> ocorrencias;
	
	public Projeto(String nomeProjeto) {
		nome = nomeProjeto;
		ocorrencias = new HashMap<>();
	}

	public String obterNome() {
		return nome;
	}

	public int quantidadeDeOcorrencias() {
		return ocorrencias.size();
	}
	
	public int quantidadeDeOcorrenciasAbertas() {
		return ocorrenciasAbertas;		
	}

	public Ocorrencia criarOcorrencia(int idFuncionario, TipoDeOcorrencia tipo, PrioridadeDeOcorrencia prioridade,
			String resumo) {
		int idOcorrencia = ocorrencias.size();
		Ocorrencia novaOcorrencia = new Ocorrencia(tipo, prioridade, resumo, idOcorrencia, idFuncionario);
		ocorrencias.put(idOcorrencia, novaOcorrencia);
		ocorrenciasAbertas++;
		return novaOcorrencia;
	}

	public void fecharOcorrencia(int idOcorrencia) throws Exception {
		Ocorrencia ocorrencia = ocorrencias.get(idOcorrencia);
		if(ocorrencia == null) {
			throw(new Exception("Ocorrência Inexistente"));
		}
		ocorrencia.fechar();
		ocorrenciasAbertas--;
	}

	

}
