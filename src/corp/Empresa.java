package corp;

import java.util.HashMap;

import corp.Ocorrencia.PrioridadeDeOcorrencia;
import corp.Ocorrencia.TipoDeOcorrencia;

public class Empresa { //Atributos em private
	//
	HashMap<Integer, Projeto> projetos; //HashMap sempre a melhor opcao: desempenho O(1) + simplicidade de codigo.
	HashMap<Integer, Funcionario> funcionarios;

	public Empresa() {
		projetos = new HashMap<>();
		funcionarios = new HashMap<>();
	}

	public int adicionarFuncionario(String nomeFuncionario) {
		int idNovoFuncionario = funcionarios.size();
		funcionarios.put(idNovoFuncionario, new Funcionario(nomeFuncionario));
		return idNovoFuncionario;
	}

	public int adicionarProjeto(String nomeProjeto) {
		int idNovoProjeto = projetos.size();
		projetos.put(idNovoProjeto, new Projeto(nomeProjeto));
		return idNovoProjeto;
	}

	public Projeto obterProjeto(int idProjeto) {
		return projetos.get(idProjeto);
	}

	public Funcionario obterFuncionario(int idFuncionario) {
		return funcionarios.get(idFuncionario);
	}

	public int quantidadeDeFuncionarios() {
		return funcionarios.size();
	}

	public int quantidadeDeProjetos() {
		return projetos.size();
	}

	public Ocorrencia criarOcorrencia(int idProjeto, int idFuncionario, TipoDeOcorrencia tipo, 
			PrioridadeDeOcorrencia prioridade, String resumo) throws Exception { //Evite usar null, aqui nao eh C++, se voce nao confia na criacao do objeto realize mais testes
		Projeto projeto = obterProjeto(idProjeto);
		if (projeto == null) {
			throw (new Exception("Projeto Inexistente"));
		}
		Funcionario funcionarioResponsavel = obterFuncionario(idFuncionario);
		if (funcionarioResponsavel == null) {
			throw (new Exception("Funcionario Inexistente"));
		}
		if (funcionarioResponsavel.limiteDeOcorrencias()) {
			throw (new Exception("Funcionario já responsável por 10 ocorrências"));
		}
		funcionarioResponsavel.abrirOcorrencia();
		return projeto.criarOcorrencia(idFuncionario, tipo, prioridade, resumo);
	}

	public void fecharOcorrencia(int idProjeto, int idFuncionario, int idOcorrencia) throws Exception { //Evite usar null, aqui nao eh C++, se voce nao confia na criacao do objeto realize mais testes
		Projeto projeto = obterProjeto(idProjeto);
		if (projeto == null) {
			throw (new Exception("Projeto Inexistente"));
		}
		Funcionario funcionarioResponsavel = obterFuncionario(idFuncionario);
		if (funcionarioResponsavel == null) {
			throw (new Exception("Funcionario Inexistente"));
		}
		projeto.fecharOcorrencia(idOcorrencia);
		funcionarioResponsavel.fecharOcorrencia();
	}

}
