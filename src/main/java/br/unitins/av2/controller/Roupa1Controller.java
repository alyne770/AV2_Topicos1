package br.unitins.av2.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.av2.application.Util;
import br.unitins.av2.dao.RoupaDAO;
import br.unitins.av2.model.Roupa;

@Named
@ViewScoped
public class Roupa1Controller implements Serializable {

	private static final long serialVersionUID = -6620874021737354536L;
	
	private String filtro;
	private List<Roupa> listaRoupa;
	
	
	public void editar (int id) {
		RoupaDAO dao = new  RoupaDAO();
		Roupa roupa = dao.getById(id);
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("roupaFlash", roupa);
		
		Util.redirect("roupa2.xhtml");
		
	}
	
	public void novo() {
		Util.redirect("roupa2.xhtml");
	}
	
	public void pesquisar() {
		RoupaDAO dao = new RoupaDAO();
		setListaRoupa(dao.getByPeca(getFiltro()));
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




}
