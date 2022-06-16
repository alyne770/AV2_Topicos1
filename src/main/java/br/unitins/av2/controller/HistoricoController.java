package br.unitins.av2.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Named;

import br.unitins.av2.application.Session;
import br.unitins.av2.application.Util;
import br.unitins.av2.dao.VendaDAO;
import br.unitins.av2.model.Usuario;
import br.unitins.av2.model.Venda;

@Named
@ViewScoped
public class HistoricoController implements Serializable{

	private static final long serialVersionUID = 8868841038332206274L;
	private List<Venda> listaVenda;
	
	public HistoricoController() {
	}

	public List<Venda> getListaVenda() {
		Usuario usuarioLogado = (Usuario) Session.getInstance().get("usuarioLogado");
		if (usuarioLogado == null) {
			listaVenda = new ArrayList<Venda>();
		} else {
			if (listaVenda == null) {
				VendaDAO dao = new VendaDAO();
				listaVenda = dao.getByUsuario(usuarioLogado);
				if (listaVenda == null)
					listaVenda = new ArrayList<Venda>();
			}
		}
		return listaVenda;
	}

	public void setListaVenda(List<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}
	
	public void datalhes(Venda venda) {
		VendaDAO dao = new VendaDAO();
		Venda vendaCompleta = dao.getByVenda(venda);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		
		flash.put("vendaFlash", venda);
		
		Util.redirect("detalhesvenda.xhtml");
				
	}
	
	
}