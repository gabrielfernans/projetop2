package controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ControllerUsuariosTest {

	ControllerUsuarios controllerUsuarios;
	
	@BeforeEach
	void setUp() throws Exception {
		controllerUsuarios = new ControllerUsuarios();
		controllerUsuarios.adicionaDoador("1001", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA");
		controllerUsuarios.adicionaDoador("1002", "Satya Vaswani", "satya@vishkarcorp.com", "(83) 99221-2571", "PESSOA_FISICA");
		controllerUsuarios.adicionaDoador("70594610435", "Gabriel Fernandes", "gabrielwebr@gmail.com", "(83) 98606-6330", "PESSOA_FISICA");
		controllerUsuarios.adicionaDoador("1234", "Manel", "manel@gmail.com", "(83) 98000-0000", "PESSOA_FISICA");
		controllerUsuarios.cadastraItem("1001", "cadeira de rodas", 5, "roda grande,cadeira");
		controllerUsuarios.cadastraItem("1002", "colchao", 10, "colchao kingsize,conforto,dormir");
		controllerUsuarios.cadastraItem("1234", "calca jeans", 2, "azul");
	}
	
	@DisplayName("Testa adicionaDoador null")
	@Test
	void testAdicionaDoadorNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.adicionaDoador(null, "Arthur Morgan", "arthur.morgan@vanderlyne.com", "(63) 99983-1899", "PESSOA_FISICA"));	
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.adicionaDoador("1003", "Arthur Morgan", "arthur.morgan@vanderlyne.com", "(63) 99983-1899", null));	
	}
	
	@DisplayName("Testa adicionaDoador vazio")
	@Test
	void testAdicionaDoadorVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.adicionaDoador("", "Arthur Morgan", "arthur.morgan@vanderlyne.com", "(63) 99983-1899", "PESSOA_FISICA"));	
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.adicionaDoador("1003", "Arthur Morgan", "arthur.morgan@vanderlyne.com", "(63) 99983-1899", ""));	
		
	}
	
	@DisplayName("Testa adicionaDoador doador existente")
	@Test
	void testAdicionaDoadorExistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.adicionaDoador("1001", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_FISICA"));	
	}
	
	@DisplayName("Testa adicionaDoador classe invalida")
	@Test
	void testAdicionaDoadorClasseInvalida() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.adicionaDoador("1087", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211", "PESSOA_JUDICIARIA"));	
	}
	
	@DisplayName("Testa adicionaDoador ok")
	@Test
	void testAdicionaDoadorOk() {
		assertEquals(controllerUsuarios.adicionaDoador("1010", "Vaas Montenegro", "vaas@rook.com", "(83) 99348-2481", "PESSOA_FISICA"), "1010");	
	}
	
	
	
	
	
	
	@DisplayName("Testa pesquisaUsuarioPorId null")
	@Test
	void testPesquisaUsuarioPorIdNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaUsuarioPorId(null));	
	}
	
	@DisplayName("Testa pesquisaUsuarioPorId vazio")
	@Test
	void testPesquisaUsuarioPorIdVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaUsuarioPorId(""));	
	}
	
	@DisplayName("Testa pesquisaUsuarioPorId usuario inexistente")
	@Test
	void testPesquisaUsuarioPorIdInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaUsuarioPorId("1009"));	
	}
	
	@DisplayName("Testa pesquisaUsuarioPorId ok")
	@Test
	void testPesquisaUsuarioPorIdOk() {
		assertEquals(controllerUsuarios.pesquisaUsuarioPorId("1001"), "Elizabeth Ashe/1001, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador");	
	}
	
	
	
	
	
	
	@DisplayName("Testa pesquisaUsuarioPorNome null")
	@Test
	void testPesquisaUsuarioPorNomeNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaUsuarioPorNome(null));	
	}
	
	@DisplayName("Testa pesquisaUsuarioPorNome vazio")
	@Test
	void testPesquisaUsuarioPorNomeVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaUsuarioPorNome(""));	
	}
	
	@DisplayName("Testa pesquisaUsuarioPorNome usuario inexistente")
	@Test
	void testPesquisaUsuarioPorNomeInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaUsuarioPorNome("1085"));	
	}
	
	@DisplayName("Testa pesquisaUsuarioPorNome ok")
	@Test
	void testPesquisaUsuarioPorNomeOk() {
		assertEquals(controllerUsuarios.pesquisaUsuarioPorNome("Elizabeth Ashe"), "Elizabeth Ashe/1001, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador");	
	}
	
	
	
	
	@DisplayName("Testa atualizaUsuario null")
	@Test
	void testAtualizaUsuarioNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.atualizaUsuario(null, "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211"));	
	}
	
	@DisplayName("Testa atualizaUsuario vazio")
	@Test
	void testAtualizaUsuarioVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.atualizaUsuario("", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211"));	
	}
	
	@DisplayName("Testa atualizaUsuario usuario inexistente")
	@Test
	void testAtualizaUsuarioInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.atualizaUsuario("1562", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", "(83) 92918-0211"));	
	}
	
	@DisplayName("Testa atualizaUsuario ok")
	@Test
	void testAtualizaUsuarioOk() {
		assertEquals(controllerUsuarios.atualizaUsuario("1001", "Elizabeth Ashe", "elizabethcalamity@hotmail.com", "(83) 92918-0211"), "Elizabeth Ashe/1001, elizabethcalamity@hotmail.com, (83) 92918-0211, status: doador");	
	}
	
	
	
	
	@DisplayName("Testa removeUsuario null")
	@Test
	void testRemoveUsuarioNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.removeUsuario(null));	
	}
	
	@DisplayName("Testa removeUsuario vazio")
	@Test
	void testRemoveUsuarioVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.removeUsuario(""));	
	}
	
	@DisplayName("Testa removeUsuario usuario inexistente")
	@Test
	void testRemoveUsuarioInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.removeUsuario("1526352"));	
	}
	
	@DisplayName("Testa removeUsuario ok")
	@Test
	void testRemoveUsuarioOk() {
		controllerUsuarios.removeUsuario("70594610435");
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.pesquisaUsuarioPorNome("70594610435"));
	}
	
	
	
	
	@DisplayName("Testa lerReceptores ok")
	@Test
	void testLerReceptoresOk() throws IOException {
		controllerUsuarios.lerReceptores("arquivos_sistema/novosReceptores.csv");	
	}
	
	@DisplayName("Testa lerReceptores invalido")
	@Test
	void testLerReceptoresInvalido() throws IOException {
		assertThrows(IOException.class, () -> controllerUsuarios.lerReceptores("arquivos_sistemas/invalid_file"));
	}
	
	
	
	
	@DisplayName("Testa cadastraItem null")
	@Test
	void testCadastraItemNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.cadastraItem("1001", null, 2, "all-in-one"));	
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.cadastraItem(null, "computador", 2, "all-in-one"));	
		
	}
	
	@DisplayName("Testa cadastraItem vazio")
	@Test
	void testCadastraItemVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.cadastraItem("1001", "", 2, "all-in-one"));	
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.cadastraItem("", "computador", 2, "all-in-one"));	
	}
	
	@DisplayName("Testa cadastraItem quantidade menor que zero")
	@Test
	void testCadastraItemMenorQueZero() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.cadastraItem("1001", "computador", -1, "all-in-one"));	
	}
	
	@DisplayName("Testa cadastraItem doador inexistente")
	@Test
	void testCadastraItemInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.cadastraItem("5625", "computador", 7, "all-in-one"));	
	}
	
	
	@DisplayName("Testa cadastraItem ok")
	@Test
	void testCadastraItemOk() {
		assertEquals(controllerUsuarios.cadastraItem("1234", "computador", 7, "all-in-one"), 4);	
	}
	
	
	
	
	@DisplayName("Testa exibeItem quantidade menor que zero")
	@Test
	void testExibeItemMenorQueZero() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.exibeItem(-1, "1234"));	
	}
	
	@DisplayName("Testa exibeItem null")
	@Test
	void testExibeItemNull() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.exibeItem(1, null));		
		
	}
	
	@DisplayName("Testa exibeItem vazio")
	@Test
	void testExibeItemVazio() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.exibeItem(1, ""));	
	}
	
	
	
	@DisplayName("Testa exibeItem doador inexistente")
	@Test
	void testExibeItemInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controllerUsuarios.exibeItem(4, "12543"));	
	}
	
	
	@DisplayName("Testa exibeItem ok")
	@Test
	void testExibeItemOk() {
		assertEquals(controllerUsuarios.exibeItem(3, "1234"), "3 - calca jeans, tags: [azul], quantidade: 2");	
	}
	
	
	
	
	
	
	
}
