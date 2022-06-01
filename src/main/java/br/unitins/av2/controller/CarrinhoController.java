package br.unitins.av2.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.av2.application.Session;
import br.unitins.av2.model.ItemVenda;

@Named
@ViewScoped

public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = -6644143344387822746L;
	private List<ItemVenda> listaItemVenda;
	
	public CarrinhoController(){
		listaItemVenda = (List<ItemVenda>) Session.getInstance().get("carrinho");
	}

	public List<ItemVenda> getListaItemVenda() {
		return listaItemVenda;
	}

	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}

}
