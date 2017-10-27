package corp;

public class Ocorrencia {

	public enum TipoDeOcorrencia {
		TAREFA, BUG, MELHORIA;
	}

	public enum EstadoDeOcorrencia {
		ABERTO, FECHADO;
	}

	public enum PrioridadeDeOcorrencia {
		ALTA, MEDIA, BAIXA;
	}

	private TipoDeOcorrencia tipo;
	private EstadoDeOcorrencia estado;
	private PrioridadeDeOcorrencia prioridade;
	private String resumo;
	private int idOcorrencia;
	private int idFuncionarioResponsavel;

	public Ocorrencia(TipoDeOcorrencia tipo, PrioridadeDeOcorrencia prioridade, String resumo, int idOcorrencia,
			int idFuncionarioResponsavel) {
		this.tipo = tipo;
		this.estado = EstadoDeOcorrencia.ABERTO;
		this.resumo = resumo;
		this.prioridade = prioridade;
		this.idOcorrencia = idOcorrencia;
		this.idFuncionarioResponsavel = idFuncionarioResponsavel;
	}

	public EstadoDeOcorrencia obterEstado() {
		return estado;
	}

	public void atualizarResponsavel(int idNovoFuncionario) throws Exception {
		if(idNovoFuncionario < 0) {
			throw (new Exception ("Id Invalido"));
		}
		idFuncionarioResponsavel = idNovoFuncionario;
	}

	public int obterFuncionarioResponsavel() {
		return idFuncionarioResponsavel;
	}

	public void atualizarPrioridade(PrioridadeDeOcorrencia novaPrioridade) {
		prioridade = novaPrioridade;		
	}

	public PrioridadeDeOcorrencia obterPrioridade() {
		return prioridade;
	}

	public int obterId() {
		return idOcorrencia;
	}

	public TipoDeOcorrencia obterTipo() {
		return tipo;
	}

	public String obterResumo() {
		return resumo;
	}

	
	public void fechar() {
		estado = EstadoDeOcorrencia.FECHADO;
		
	}


}
