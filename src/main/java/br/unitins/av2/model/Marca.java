package br.unitins.av2.model;

import java.time.LocalDate;
import java.util.Objects;

public class Marca {
	private Integer id;
	private String nome;
	private LocalDate data_Lancamento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData_Lancamento() {
		return data_Lancamento;
	}

	public void setData_Lancamento(LocalDate data_Lancamento) {
		this.data_Lancamento = data_Lancamento;
	}

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
		Marca other = (Marca) obj;
		return Objects.equals(id, other.id);
	}



}
