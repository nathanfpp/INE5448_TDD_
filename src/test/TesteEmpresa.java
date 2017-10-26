package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import corp.Empresa;
import corp.Projeto;

public class TesteEmpresa {

	Empresa empresa;
	
	@Before
	public void before() {
		empresa = new Empresa();
	}

	@Test
	public void adicionarProjeto(){
		String nomeProjeto = "Adicionar";
		int idProjeto = empresa.adicionarProjeto(nomeProjeto);
		int quantidadeEsperadaDeProjetos = 1;
		int quantidadeDeProjetos;
		empresa.obterProjeto(idProjeto);
		quantidadeDeProjetos = empresa.quantidadeDeProjetos();
		assertEquals(quantidadeEsperadaDeProjetos, quantidadeDeProjetos);
	}

	@Test
	public void obterProjeto() {
		String nomeProjeto = "Obter";
		int idProjeto = empresa.adicionarProjeto(nomeProjeto);
		Projeto projeto = empresa.obterProjeto(idProjeto);
		assertEquals(nomeProjeto, projeto.obterNome());
	}

	@Test
	public void obterProjetoInexistente() {
		int idProjetoInexistente = -1;
		assertNull(empresa.obterProjeto(idProjetoInexistente));
	}

	@Test
	public void adicionarFuncionario() {
		int quantidadeEsperadaDeFuncionarios = 1;
		String nomeFuncionario = "X";
		int quantidadeDeFuncionarios;
		empresa.adicionarFuncionario(nomeFuncionario);
		quantidadeDeFuncionarios = empresa.quantidadeDeFuncionarios();
		assertEquals(quantidadeEsperadaDeFuncionarios, quantidadeDeFuncionarios);
	}

	@Test
	public void obterFuncionario()  {
		String nomeFuncionario = "Obto Erfun";
		int idFuncionario = empresa.adicionarFuncionario(nomeFuncionario);
		String nomeFuncionarioCriado = empresa.obterFuncionario(idFuncionario).obterNome();
		assertEquals(nomeFuncionario, nomeFuncionarioCriado);
	}

	@Test
	public void obterFuncionarioInexistente() {
		int idFuncionarioInexistente = -1;
		assertNull(empresa.obterFuncionario(idFuncionarioInexistente));
	}
}
