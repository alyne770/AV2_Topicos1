package br.unitins.av2.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.av2.application.Session;
import br.unitins.av2.application.Util;
import br.unitins.av2.dao.UsuarioDAO;
import br.unitins.av2.model.Usuario;

@Named
@RequestScoped

public class LoginController {
private Usuario usuario;
	
	public void entrar() {
		String hash = Util.hash(getUsuario());
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.verificarLogin(getUsuario().getLogin(), hash);
		if (usuario == null) {
			Util.addMessageError("Login ou Senha invalido.");
			return;
		}
		
		Session.getInstance().set("usuarioLogado", usuario);
		
		Util.redirect("roupa1.xhtml");
	}
	
	public void limpar() {
		usuario = null;
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
