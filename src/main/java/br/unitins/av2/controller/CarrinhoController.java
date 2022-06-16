package br.unitins.av2.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.av2.application.Session;
import br.unitins.av2.application.Util;
import br.unitins.av2.dao.VendaDAO;
import br.unitins.av2.model.ItemVenda;
import br.unitins.av2.model.Usuario;
import br.unitins.av2.model.Venda;

@Named
@ViewScoped
public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = 4409948921848614949L;
	private List<ItemVenda> listaItemVenda;

	public CarrinhoController() {
		listaItemVenda = (List<ItemVenda>) Session.getInstance().get("carrinho");
	}

	public void finalizar() {
		Usuario usuarioLogado = (Usuario) Session.getInstance().get("usuarioLogado");
		if (usuarioLogado == null) {
			Util.addMessageError("Faça o Login para finalizar a compra.");
			return;
		}
		if (getListaItemVenda() == null || getListaItemVenda().isEmpty()) {
			Util.addMessageError("Selecione uma roupa antes de finalizar uma compra.");
			return;
		}

		Venda venda = new Venda();
		venda.setUsuario(usuarioLogado);
		venda.setDataVenda(LocalDateTime.now());
		venda.setListaItemVenda(getListaItemVenda());

		VendaDAO dao = new VendaDAO();
		dao.insert(venda);

		Util.addMessageInfo("Compra realizada com sucesso.");

	}

	public void remover(ItemVenda venda) {
		@SuppressWarnings("unchecked")
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().get("carrinho");

		carrinho.remove(venda);
	}

	public List<ItemVenda> getListaItemVenda() {
		return listaItemVenda;
	}

	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}

}
