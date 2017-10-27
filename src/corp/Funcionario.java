package corp;

public class Funcionario { //Atributos em private, utilize getters para pegar seus valores.

	String nome;
	int ocorrenciasSobResponsabilidade; //Imagine um programador escrevendo o nome dessa variavel por uma centena de vezes... Alem do que a ocorrencia pertencer ao funcionario.

	public Funcionario(String nomeFuncionario) {
		nome = nomeFuncionario;
	}

	public String obterNome() {
		return nome;
	}

	public boolean limiteDeOcorrencias() {
		return (ocorrenciasSobResponsabilidade >= 10);
	}

	public void abrirOcorrencia() { //adicionarOcorrencia seria mais adequado.
		ocorrenciasSobResponsabilidade++;
	}

	public void fecharOcorrencia() {
		ocorrenciasSobResponsabilidade--;
		
	}

}
