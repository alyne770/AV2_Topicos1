package br.unitins.av2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.av2.application.Session;
import br.unitins.av2.application.Util;
import br.unitins.av2.dao.RoupaDAO;
import br.unitins.av2.model.ItemVenda;
import br.unitins.av2.model.Roupa;

@Named
@ViewScoped
public class VendaController implements Serializable{

	private static final long serialVersionUID = -8048218338288779032L;
	private Integer tipoFiltro;
	private String filtro;
	private List<Roupa> listaRoupa;
	
	
	public void pesquisar() {
		RoupaDAO dao = new RoupaDAO();
		setListaRoupa(dao.getByPeca(filtro));
	}
	
	public void comprar(Roupa roupa) {
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().get("carrinho");
		if (carrinho == null)
			carrinho = new ArrayList<ItemVenda>();
		
		ItemVenda item = new ItemVenda();
		item.setRoupa(roupa);
		item.setValor(roupa.getPreco());
		item.setQuantidade(1);
		
		// verificando se contem o livro no carrinho para alterar a quantidade
		if (carrinho.contains(item)) {
			int index = carrinho.indexOf(item);
			int quantidade = carrinho.get(index).getQuantidade();
			carrinho.get(index).setQuantidade(quantidade + 1);
			carrinho.get(index).setValor(roupa.getPreco());
		} else {
			carrinho.add(item);
		}
		
		// cria/ atualiza o objeto na sessao
		Session.getInstance().set("carrinho", carrinho);
		
		Util.addMessageInfo("Roupa adicionado na sessão");
		
	}
	
	public Integer getTipoFiltro() {
		return tipoFiltro;
	}
	public void setTipoFiltro(Integer tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public List<Roupa> getListaRoupa() {
		return listaRoupa;
	}
	public void setListaRoupa(List<Roupa> listaRoupa) {
		this.listaRoupa = listaRoupa;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
