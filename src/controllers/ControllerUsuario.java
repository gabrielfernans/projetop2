package controllers;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import entidades.Item;
import entidades.ItemComparavelPorId;
import entidades.Usuario;

/**
 * Classe que representa o controlador dos usuarios cadastrados no sistema.
 * @author
 *
 */
public class ControllerUsuario {
	private ControllerDescritor controllerDescritor = new ControllerDescritor();
	private Map<String, Usuario> usuarios;
	private int cont = 0;
	private int idItem = 1;
	
	/**
	 * Construtor da classe ControllerUsuario.
	 */
	public ControllerUsuario() {
		this.usuarios = new HashMap<String, Usuario>();
	}
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {	
		if(id == null || id.trim().equals("")) 
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		
		if(usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		
		if(classe == null || classe.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		
		switch(classe) {
		
		case "PESSOA_FISICA": 
		case "ONG": 
		case "IGREJA": 
		case "ORGAO_PUBLICO_MUNICIPAL": 
		case "ORGAO_PUBLICO_FEDERAL": 
		case "ORGAO_PUBLICO_ESTADUAL":
		case "ASSOCIACAO": case "SOCIEDADE":
			usuarios.put(id, new Usuario(id, nome, email, celular, classe, "doador", cont++));
			break;
			
		default:
			throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
		}
		return id;
	}
	
	public String pesquisaUsuarioPorId(String id) {
		if(id == null || id.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		
		if(!usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		
		return usuarios.get(id).toString();
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		if(nome == null || nome.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		
		List<Usuario> users = new ArrayList<Usuario>();
		boolean userNaoExiste = true;
		
		for(Usuario u : usuarios.values()) {
			if(u.getNome().equals(nome)) {
				users.add(u);
				userNaoExiste = false;
			}
		}
		if (userNaoExiste)
			throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
		
		Collections.sort(users);
		return editaLista(users);
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) {
		
		if(id == null || id.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		
		if(!usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		
		return usuarios.get(id).atualizaUsuario(nome, email, celular);
	}

	public void removeUsuario(String id) {
		if(id == null || id.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		
		if(!usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		
		usuarios.remove(id);
	}

	public void lerReceptores(String caminho) throws IOException {
		Scanner sc = new Scanner(new File(caminho));
		String linha = null;
		
		while(sc.hasNextLine()) {
			linha = sc.nextLine();
			
			if(linha.equals("id,nome,E-mail,celular,classe"))
				continue;
			String[] dadosReceptor = linha.split(",");
			
			if(dadosReceptor.length != 5) {
				sc.close();
				throw new IOException("Campos invalidos");
			}
				
			if(caminho.split("/")[1].equals("atualizaReceptores.csv"))
				usuarios.get(dadosReceptor[0]).atualizaReceptor(dadosReceptor[1], dadosReceptor[2], dadosReceptor[3]);
			
			else if(caminho.split("/")[1].equals("novosReceptores.csv"))
				usuarios.put(dadosReceptor[0], new Usuario(dadosReceptor[0],dadosReceptor[1], dadosReceptor[2], dadosReceptor[3], dadosReceptor[4], "receptor", cont++));
		}
		sc.close();
	}

	public int cadastraItem(String idDoador, String descritor, int quantidade, String tags) {
		
		if (descritor == null || descritor.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		}
		
		if (idDoador == null || idDoador.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		
		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		
		if (controllerDescritor.contemDescritor(descritor) == false) {
			controllerDescritor.cadastraDescritor(descritor);
		}
		
		return this.usuarios.get(idDoador).cadastraItem(this.idItem++, descritor.trim().toLowerCase(), quantidade, tags);
	}
	
	public int cadastraItemNecessario(String idReceptor, String descritor, int quantidade, String tags) {
		if (descritor == null || descritor.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		}
		
		if (idReceptor == null || idReceptor.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if(!controllerDescritor.contemDescritor(descritor.trim().toLowerCase())) {
			controllerDescritor.cadastraDescritor(descritor);
		}
		return this.usuarios.get(idReceptor).cadastraItem(this.idItem++, descritor.trim().toLowerCase(), quantidade, tags);
	}
	
	public String exibeItem(int idItem, String idDoador) {
		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		return this.usuarios.get(idDoador).exibeItem(idItem);
	}
	
	public String listaItensNecessarios() {
		Map<Item, String> itens = new HashMap<>();
		for(Usuario user: usuarios.values()){
			if(user.getStatus().equals("receptor")){
				for(Item item: user.itensNec().values()){
					itens.put(item, user.getNome() + "/" + user.getIdSemFormatacao());
				}
			}
		}
		List<Item> itensLista = new ArrayList<>();
		for(Item item: itens.keySet()){
			itensLista.add(item);
		}
		Collections.sort(itensLista, new ItemComparavelPorId());
		String aux ="";
		for(Item item: itensLista){
			aux += item.getIdItem() + " - " + item.getDescritor() + ", tags: " + item.getTags() + ", quantidade: " + item.getQuantidade() + ", Receptor: " + itens.get(item) + " | ";
		}
		
		return aux.substring(0, aux.length()-3);
			
	}
	
	
//	
//	public String listaItensNecessarios() {
//		String aux = "";
//		HashMap<Item, String> mapaItensNecessarios = new HashMap<>();
//		ArrayList<Item> itensNecessarios = new ArrayList<>(); 
//		for (Usuario u : usuarios.values()) {
//			if (u.getStatus().equals("receptor")) {
//				String receptor = u.getNome() + "/" + u.getId();
//				for (Item item : u.itensNec().values()) {
//					mapaItensNecessarios.put(item, receptor);
//					itensNecessarios.add(item);
//				}
//			}
//		}
//		
//		Collections.sort(itensNecessarios, new ItemComparavelPorId());
//		
//		for (Item item : itensNecessarios) {
//			aux += item.getIdItem() + " - " + item.getDescritor() + ", tags: " + item.getTags() + ", quantidade: " + item.getQuantidade() + ", Receptor: " + mapaItensNecessarios.get(item) + " | ";
//		}
//		
//		return aux;
//	}
	
	/**
	 * Atualiza a quantidade de itens e as tags inseridas, se uma tag 
	 * @param idItem
	 * @param idDoador
	 * @param quantidade
	 * @param tags
	 */
	public String atualizaItem(int idItem, String idDoador, int quantidade, String tags) {
		if(idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		
		if (idDoador == null || idDoador.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		
		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		
		return usuarios.get(idDoador).atualizaItem(idItem, tags, quantidade);
	}
	
	public String atualizaItemNecessario(String idReceptor, int idItem, int quantidade, String tags) {
		if(idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		
		if (idReceptor == null || idReceptor.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		
		if (!this.usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
		
		return usuarios.get(idReceptor).atualizaItem(idItem, tags, quantidade);
	}
	
	/**
	 * Remove um determinado item do sistema a partir do id.
	 * @param idItem Id do item a ser retirado.
 	 * @param idDoador Documento de identificacao do doador.
	 */
	public void removeItem(int idItem, String idDoador) {
		if(idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		
		if (idDoador == null || idDoador.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		
		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		
		usuarios.get(idDoador).removeItem(idItem);
	}
	
	public void removeItemNecessario(String idReceptor, int idItem) {
		if(idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		
		if (idReceptor == null || idReceptor.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		
		if (!this.usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
		
		usuarios.get(idReceptor).removeItem(idItem);
	}
	
	private String editaLista(List<Usuario> listaDeUsuario) {
		String users = "";
		for(Usuario u : listaDeUsuario) {
			users += u.toString() + " | ";
		}
		return users.substring(0, users.length()-3);
	}
	
	public void adicionaItemDeUsuario(int idUsuario, int idItem, int quantidade, String descricao, String data, List<String> tags) {
		this.usuarios.get(idUsuario).adicionaItem(idItem, quantidade, descricao, data, tags);
	}
	
	public void atualizaItem(int idUsuario, int idItem, String novasTags, int novaQuantidade) {
		this.usuarios.get(idUsuario).atualizaItem(idItem, novasTags, novaQuantidade);
	}
	
	//erick
	public Map<String, Usuario> getUsuarios() {
		return usuarios;
	}
	
}