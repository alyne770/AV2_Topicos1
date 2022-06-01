package br.unitins.av2.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class Roupa {
	private Integer id;
	private String peca;
	@NotNull(message = "A marca não pode ser nulo.")
	private Marca marca;
	@NotNull(message = "O tamanho não pode ser nulo.")
	private String tamanho;
	@NotNull(message = "A cor não pode ser nulo.")
	private String cor;
	@NotNull(message = "O preço não pode ser nulo.")
	private Double preco;
	@NotNull(message = "O estoque não pode ser nulo.")
	private Integer estoque;
	@NotNull(message = "A data não pode ser nula.")
	private LocalDate data_Recebimento;
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Roupa other = (Roupa) obj;
		return Objects.equals(id, other.id);
	}
	
	
	public String getPeca() {
		return peca;
	}
	public void setPeca(String peca) {
		this.peca = peca;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getData_Recebimento() {
		return data_Recebimento;
	}
	public void setData_Recebimento(LocalDate data_Recebimento) {
		this.data_Recebimento = data_Recebimento;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Integer getEstoque() {
		return estoque;
	}
	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
	
	

}
