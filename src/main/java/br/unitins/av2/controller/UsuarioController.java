package br.unitins.av2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.unitins.av2.application.Util;
import br.unitins.av2.dao.UsuarioDAO;
import br.unitins.av2.model.Perfil;
import br.unitins.av2.model.Usuario;


@Named
@ApplicationScoped

public class UsuarioController implements Serializable {
	
	private static final long serialVersionUID = -2950021283257698121L;
	private Usuario usuario;
	private List<Usuario> listaUsuario;

	public void incluir() {
		UsuarioDAO dao = new UsuarioDAO();
		// gerando o hash da senha
		String senha = getUsuario().getLogin() + getUsuario().getSenha();
		senha = Util.hash(senha);
		getUsuario().setSenha(senha);
		
		if (!dao.insert(getUsuario())) {
			Util.addMessageInfo("Erro ao tentar incluir o usu�rio.");
			return;
		}
		limpar();
		setListaUsuario(null);
		Util.addMessageInfo("Inclus�o realizada com sucesso.");
	}

	public void alterar() {
		UsuarioDAO dao = new UsuarioDAO();
		
		// gerando o hash da senha
		String senha = getUsuario().getLogin() + getUsuario().getSenha();
		senha = Util.hash(senha);
		getUsuario().setSenha(senha);
		
		if (!dao.update(getUsuario())) {
			Util.addMessageInfo("Erro ao tentar alterar o usu�rio.");
			return;
		}
		limpar();
		setListaUsuario(null);
		Util.addMessageInfo("Altera��o realizada com sucesso.");
	}

	public void excluir() {
		excluir(getUsuario().getId());
		limpar();
	}

	public void excluir(int id) {
		UsuarioDAO dao = new UsuarioDAO();
		if (!dao.delete(id)) {
			Util.addMessageInfo("Erro ao tentar excluir o usu�rio.");
			return;
		}
		setListaUsuario(null);
		Util.addMessageInfo("Exclus�o realizada com sucesso.");
	}

	public void editar(int id) {
		UsuarioDAO dao = new UsuarioDAO();
		setUsuario(dao.getById(id));
	}

	public void limpar() {
		usuario = null;
	}
	
	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			UsuarioDAO dao = new UsuarioDAO();
			listaUsuario = dao.getAll();
			if (listaUsuario == null) 
				listaUsuario = new ArrayList<Usuario>();
		}
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}


}
