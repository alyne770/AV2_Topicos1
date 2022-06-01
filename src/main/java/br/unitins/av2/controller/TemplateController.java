package br.unitins.av2.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.av2.application.Session;
import br.unitins.av2.application.Util;
import br.unitins.av2.model.Usuario;

@Named
@ViewScoped
public class TemplateController implements Serializable {

	private static final long serialVersionUID = -2303581758798892001L;
	
	private Usuario usuarioLogado;
	
	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null)
			usuarioLogado = (Usuario) Session.getInstance().get("usuarioLogado");
		return usuarioLogado;
		
	}

	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("login2.xhtml");
	}

}
