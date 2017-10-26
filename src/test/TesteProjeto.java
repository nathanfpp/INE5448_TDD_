package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import corp.Empresa;
import corp.Ocorrencia;
import corp.Ocorrencia.EstadoDeOcorrencia;
import corp.Ocorrencia.PrioridadeDeOcorrencia;
import corp.Ocorrencia.TipoDeOcorrencia;

public class TesteProjeto {

	private Empresa empresa;
	private int idProjeto;
	private int idFuncionario;

	@Before
	public void before() {
		empresa = new Empresa();
		idProjeto = empresa.adicionarProjeto("DAJOB");
		idFuncionario = empresa.adicionarFuncionario("Mr.Slave");
	}

	@Test
	public void criarOcorrencia() throws Exception {
		int quantidadeEsperadaDeOcorrencias = 1;
		int quantidadeDeOcorrencias;
		EstadoDeOcorrencia estadoEsperado = EstadoDeOcorrencia.ABERTO, estadoCriado;
		TipoDeOcorrencia tipo = TipoDeOcorrencia.BUG, tipoCriado;
		PrioridadeDeOcorrencia prioridade = PrioridadeDeOcorrencia.ALTA, prioridadeCriada;
		String resumo = "Yepe", resumoCriado;
		Ocorrencia ocorrenciaCriada = empresa
				.criarOcorrencia(idProjeto, idFuncionario, tipo, prioridade, resumo);
		quantidadeDeOcorrencias = empresa.obterProjeto(idProjeto).quantidadeDeOcorrencias();
		estadoCriado = ocorrenciaCriada.obterEstado();
		tipoCriado = ocorrenciaCriada.obterTipo();
		prioridadeCriada = ocorrenciaCriada.obterPrioridade();
		resumoCriado = ocorrenciaCriada.obterResumo();
		assertEquals(quantidadeEsperadaDeOcorrencias, quantidadeDeOcorrencias);
		assertEquals(estadoEsperado, estadoCriado);
		assertEquals(tipo, tipoCriado);
		assertEquals(prioridade, prioridadeCriada);
		assertEquals(resumo, resumoCriado);
	}

	@Test
	public void criar10OcorrenciasComMesmoFuncionarioResponsavel() throws Exception {
		int quantidadeDeOcorrenciasEsperadas = 10, quantidadeDeOcorrenciasTotais;
		TipoDeOcorrencia tipo = TipoDeOcorrencia.BUG;
		PrioridadeDeOcorrencia prioridade = PrioridadeDeOcorrencia.ALTA;
		String resumo = "Loop";
		for (int i = 0; i < 10; i++) {
			empresa.criarOcorrencia(idProjeto, idFuncionario, tipo, prioridade, resumo + " : " + i);
		}
		quantidadeDeOcorrenciasTotais = empresa.obterProjeto(idProjeto).quantidadeDeOcorrencias();
		assertEquals(quantidadeDeOcorrenciasEsperadas, quantidadeDeOcorrenciasTotais);
	}

	@Test(expected = Exception.class)
	public void criar11OcorrenciasComMesmoFuncionarioResponsavel() throws Exception {
		TipoDeOcorrencia tipo = TipoDeOcorrencia.BUG;
		PrioridadeDeOcorrencia prioridade = PrioridadeDeOcorrencia.ALTA;
		String resumo = "Loop Breaker";
		for (int i = 0; i <= 11; i++) {
			empresa.criarOcorrencia(idProjeto, idFuncionario, tipo, prioridade, resumo + " : " + i);
		}
	}

	@Test(expected = Exception.class)
	public void criarOcorrenciaEmProjetoInexistente() throws Exception {
		int idProjetoInexistente = -1;
		TipoDeOcorrencia tipo = TipoDeOcorrencia.MELHORIA;
		PrioridadeDeOcorrencia prioridade = PrioridadeDeOcorrencia.MEDIA;
		String resumo = "Mebe";
		empresa.criarOcorrencia(idProjetoInexistente, idFuncionario, tipo, prioridade, resumo);
	}

	@Test(expected = Exception.class)
	public void criarOcorrenciaComFuncionarioResponsavelInexistente() throws Exception {
		int idFuncionarioInexistente = -1;
		TipoDeOcorrencia tipo = TipoDeOcorrencia.TAREFA;
		PrioridadeDeOcorrencia prioridade = PrioridadeDeOcorrencia.BAIXA;
		String resumo = "Nope";
		empresa.criarOcorrencia(idProjeto, idFuncionarioInexistente, tipo, prioridade, resumo);
	}

	@Test
	public void trocarResponsavelPorOcorrencia() throws Exception {
		String nomeNovoFuncionario = "Mr.Worker";
		int idNovoFuncionario, idFuncionarioResponsavel;
		TipoDeOcorrencia tipo = TipoDeOcorrencia.MELHORIA;
		PrioridadeDeOcorrencia prioridade = PrioridadeDeOcorrencia.ALTA;
		String resumo = "MA";
		idNovoFuncionario = empresa.adicionarFuncionario(nomeNovoFuncionario);
		Ocorrencia ocorrencia = empresa.criarOcorrencia(idProjeto, idFuncionario, tipo, prioridade, resumo);
		ocorrencia.atualizarResponsavel(idNovoFuncionario);
		idFuncionarioResponsavel = ocorrencia.obterFuncionarioResponsavel();
		assertEquals(idNovoFuncionario, idFuncionarioResponsavel);
	}

	@Test(expected = Exception.class)
	public void trocarResponsavelPorOcorrenciaPorIdInexistente() throws Exception {
		int idFuncionarioInexistente = -1;
		TipoDeOcorrencia tipo = TipoDeOcorrencia.MELHORIA;
		PrioridadeDeOcorrencia prioridade = PrioridadeDeOcorrencia.ALTA;
		String resumo = "MA";
		Ocorrencia ocorrencia = empresa.criarOcorrencia(idProjeto, idFuncionario, tipo, prioridade, resumo);
		ocorrencia.atualizarResponsavel(idFuncionarioInexistente);
	}

	@Test
	public void trocarPrioridadeDeOcorrencia() throws Exception {
		TipoDeOcorrencia tipo = TipoDeOcorrencia.BUG;
		PrioridadeDeOcorrencia prioridade = PrioridadeDeOcorrencia.ALTA;
		String resumo = "BA";
		PrioridadeDeOcorrencia prioridadeEsperada = PrioridadeDeOcorrencia.BAIXA, prioridadeAtualizada;
		Ocorrencia ocorrencia = empresa.criarOcorrencia(idProjeto, idFuncionario, tipo, prioridade, resumo);
		ocorrencia.atualizarPrioridade(prioridadeEsperada);
		prioridadeAtualizada = ocorrencia.obterPrioridade();
		assertEquals(prioridadeEsperada, prioridadeAtualizada);
	}

	@Test
	public void fecharOcorrencia() throws Exception {
		int idOcorrenciaB, quantidadeEsperadaDeOcorrenciasAbertas = 1, quantidadeDeOcorrenciasAbertas;
		TipoDeOcorrencia tipo = TipoDeOcorrencia.BUG;
		PrioridadeDeOcorrencia prioridade = PrioridadeDeOcorrencia.BAIXA;
		String resumo = "BB";
		empresa.criarOcorrencia(idProjeto, idFuncionario, tipo, prioridade, resumo + 1);
		idOcorrenciaB = empresa.criarOcorrencia(idProjeto, idFuncionario, tipo, prioridade, resumo + 2).obterId();
		empresa.fecharOcorrencia(idProjeto, idFuncionario, idOcorrenciaB);
		quantidadeDeOcorrenciasAbertas = empresa.obterProjeto(idProjeto).quantidadeDeOcorrenciasAbertas();
		assertEquals(quantidadeEsperadaDeOcorrenciasAbertas, quantidadeDeOcorrenciasAbertas);
	}

	@Test(expected = Exception.class)
	public void fecharOcorrenciaInexistente() throws Exception {
		int idOcorrenciaInexistente = -1;
		TipoDeOcorrencia tipo = TipoDeOcorrencia.BUG;
		PrioridadeDeOcorrencia prioridade = PrioridadeDeOcorrencia.MEDIA;
		String resumo = "BM";
		empresa.criarOcorrencia(idProjeto, idFuncionario, tipo, prioridade, resumo + 1);
		empresa.fecharOcorrencia(idProjeto, idFuncionario, idOcorrenciaInexistente);		
	}

	@Test(expected = Exception.class)
	public void fecharOcorrenciaDeProjetoInexistente() throws Exception {
		int idProjetoInexistente = -1, idOcorrencia;
		TipoDeOcorrencia tipo = TipoDeOcorrencia.BUG;
		PrioridadeDeOcorrencia prioridade = PrioridadeDeOcorrencia.MEDIA;
		String resumo = "BM";
		idOcorrencia = empresa.criarOcorrencia(idProjeto, idFuncionario, tipo, prioridade, resumo + 1).obterId();
		empresa.fecharOcorrencia(idProjetoInexistente, idFuncionario, idOcorrencia);
	}
	
	@Test(expected = Exception.class)
	public void fecharOcorrenciaDeResponsavelInexistente() throws Exception {
		int idFuncionarioInexistente = -1, idOcorrencia;
		TipoDeOcorrencia tipo = TipoDeOcorrencia.BUG;
		PrioridadeDeOcorrencia prioridade = PrioridadeDeOcorrencia.MEDIA;
		String resumo = "BM";
		idOcorrencia = empresa.criarOcorrencia(idProjeto, idFuncionario, tipo, prioridade, resumo + 1).obterId();
		empresa.fecharOcorrencia(idProjeto, idFuncionarioInexistente, idOcorrencia);
	}
}
