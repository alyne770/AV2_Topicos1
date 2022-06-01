package br.unitins.av2.application;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Session {
	private static Session session = null;
	
	private Session() {
		// NAO PERMITE UMA INSTANCIA EXTERNA 
		
	}
	 public static Session getInstance() {
		 if (session == null)
			 session = new Session();
		 return session;
		 
	 }
	 
	 private ExternalContext getExternalContext() {
		 if (FacesContext.getCurrentInstance()==null)
			 throw new RuntimeException("Opa, você não esta utilix=zando um servidor web");
		 return FacesContext.getCurrentInstance().getExternalContext();
	 }
	 
	 public void set(String key , Object value) {
		 getExternalContext().getSessionMap().put(key, value);
	 }
	 
	 public Object get(String Key) {
		 return getExternalContext().getSessionMap().get(Key);
		 
	 }
	 
	 public void invalidateSession() {
		 getExternalContext().invalidateSession();
	 }
	 
	 //Usar no loginController
	 
	 // Session.getInstance().set("usuarioLogado", usuario);
	 }
	 
	 

