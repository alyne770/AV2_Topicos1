package br.unitins.av2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.av2.application.Util;
import br.unitins.av2.dao.MarcaDAO;
import br.unitins.av2.dao.RoupaDAO;
import br.unitins.av2.model.Marca;
import br.unitins.av2.model.Roupa;

@Named
@ViewScoped
public class Roupa2Controller implements Serializable {

	private static final long serialVersionUID = -7194388032902219137L;
	
	private Roupa roupa;
	private List<Marca> listaMarca;
	
	public Roupa2Controller() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("roupaFlash");
		setRoupa((Roupa)flash.get("roupaFlash"));
	}
	
	public List<Marca> getListaMarca() {
		if (listaMarca == null) {
			MarcaDAO dao = new MarcaDAO();
			listaMarca = dao.getAll();
			if (listaMarca == null)
				listaMarca = new ArrayList<Marca>();
		}
		return listaMarca;
	}
	
	public void voltar() {
		Util.redirect("roupa1.xhtml");
	}

	public void incluir() {
		RoupaDAO dao = new RoupaDAO();
		if (!dao.insert(getRoupa())) {
			Util.addMessageInfo("Erro ao tentar incluir o roupa.");
			return;
		}
		limpar();
		Util.addMessageInfo("Inclusão realizada com sucesso.");
	}

	public void alterar() {
		RoupaDAO dao = new RoupaDAO();
		if (!dao.update(getRoupa())) {
			Util.addMessageInfo("Erro ao tentar alterar o roupa.");
			return;
		}
		limpar();
		Util.addMessageInfo("Alteração realizada com sucesso.");

		
	}

	public void excluir() {
		RoupaDAO dao = new RoupaDAO();
		if (!dao.delete(getRoupa().getId())) {
			Util.addMessageInfo("Erro ao tentar excluir o roupa.");
			return;
		}
		Util.addMessageInfo("Exclusão realizada com sucesso.");
		limpar();
	}

	public void limpar() {
		roupa = null;
	}
	
	public Roupa getRoupa() {
		if (roupa == null) {
			roupa = new Roupa();
			roupa.setMarca(new Marca());
		}
		return roupa;
	}

	public void setRoupa(Roupa roupa) {
		this.roupa = roupa;
	}
}
