package br.unitins.av2.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.av2.model.Roupa;
import br.unitins.av2.model.Venda;

@Named
@ViewScoped

public class DetalheVendaController  implements Serializable {
	private Venda venda;
	
	private static final long serialVersionUID = 7852495147252008959L;
	
	public DetalheVendaController() {
	Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
	flash.keep("vendaFlash");
	setVenda( (Venda)flash.get("vendaFlash") );
}

public Venda getVenda() {
	return venda;
}

public void setVenda(Venda venda) {
	this.venda = venda;
}


}
