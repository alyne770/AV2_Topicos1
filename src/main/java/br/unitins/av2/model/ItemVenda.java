package br.unitins.av2.model;

import java.util.Objects;

public class ItemVenda {
	private Integer id;
	private Double valor;
	private Integer quantidade;
	private Roupa roupa;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Roupa getRoupa() {
		return roupa;
	}
	public void setRoupa(Roupa roupa) {
		this.roupa = roupa;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(roupa);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVenda other = (ItemVenda) obj;
		return Objects.equals(roupa, other.roupa);
	}
 
	
	
	
	

}
