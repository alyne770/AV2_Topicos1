package br.unitins.av2.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.av2.application.Util;
import br.unitins.av2.dao.RoupaDAO;
import br.unitins.av2.dao.UsuarioDAO;
import br.unitins.av2.model.Usuario;

@Named
@ViewScoped
public class UsuarioConsultaController implements Serializable {

	private static final long serialVersionUID = -8528441610144837401L;
	
	private String filtro;
	private List<Usuario> listaUsuario;
	
	
	public void editar (int id) {
		UsuarioDAO dao = new  UsuarioDAO();
		Usuario usuario = dao.getById(id);
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("usuarioFlash", usuario);
		
		Util.redirect("usuario.xhtml");
		
	}
	
	public void novo() {
		Util.redirect("usuario.xhtml");
	}
	
	public void pesquisar() {
		UsuarioDAO dao = new UsuarioDAO();
		setListaUsuario(dao.getByNome(getFiltro()));
	}
	
	//verificarLogin

		
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	
}
