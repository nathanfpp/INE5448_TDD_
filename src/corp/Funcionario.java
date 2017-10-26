package corp;

public class Funcionario {

	String nome;
	int ocorrenciasSobResponsabilidade;
	
	public Funcionario(String nomeFuncionario) {
		nome = nomeFuncionario;
	}

	public String obterNome() {
		return nome;
	}

	public boolean limiteDeOcorrencias() {
		return (ocorrenciasSobResponsabilidade >= 10);
	}

	public void abrirOcorrencia() {
		ocorrenciasSobResponsabilidade++;
	}

	public void fecharOcorrencia() {
		ocorrenciasSobResponsabilidade--;
		
	}

}
