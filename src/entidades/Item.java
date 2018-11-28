package entidades;

import java.util.ArrayList;
import java.util.List;

public class Item implements Comparable<Item>{
	private int idItem;
	private int quantidade;
	private String descritor;
	private List<String> tags;
	
	public Item(int idItem, int quantidade, String descritor, List<String> tags) {
		this.idItem = idItem;
		this.quantidade = quantidade;
		this.descritor = descritor;
		this.tags = tags;
	}
	
	public String atualizaItem(String novasTags, int novaQuantidade) {
		if (novasTags != null) {
			String[] vetorTags = novasTags.split(",");
			List<String> listaTags = new ArrayList<String>();
			
			//Adicionando as tags do vetor no ArrayList
			for (String c : vetorTags) {
				listaTags.add(c);
			}
			
			this.tags = listaTags;
			
		}
		
		if (novaQuantidade > 0) {
			this.quantidade = novaQuantidade;
		}
		return this.toString();
	}

	public String retornaDescricaoEQuantidade() {
		return this.quantidade + " - " + descritor;
	}

	public String toStringCombo() {
		return this.idItem + " - " + this.getDescritor() + ", tags: " + this.toStringDeTags() + ", quantidade: " + this.quantidade + ", "; 
	}
	
	@Override
	public String toString() {
		if (tags.size() == 0) {
			return this.idItem + " - " +this.descritor.toString() + ", quantidade: " + this.quantidade;
		}
		return this.idItem + " - " + this.descritor.toString() + ", tags: " + this.tags.toString() + ", quantidade: " + this.quantidade;
	}

	public String retornaDescricaoItemEQuantidade() {
		return this.quantidade + " - " + descritor.toString();
	}
	
	
	public String getDescritor() {
		return descritor;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public void setDescritor(String descritor) {
		this.descritor = descritor;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String toStringDeTags() {
		String lista = "[";

		for (int i=0; i < tags.size(); i++) {
			if (i == tags.size()-1) {
				lista += tags.get(i) + "]";
			}
			else {
				lista += tags.get(i) + ", ";
			}
		}
		return lista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descritor == null) ? 0 : descritor.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (descritor == null) {
			if (other.descritor != null)
				return false;
		} else if (!descritor.equals(other.descritor))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

	@Override
	public int compareTo(Item o) {
		return this.descritor.compareTo(o.getDescritor());
	}
	
	public String idItemToString() {
		return this.idItem + " - " + this.descritor + ", " + "tags: " + this.toStringDeTags() + ", quantidade: " + this.quantidade;
	}

}
