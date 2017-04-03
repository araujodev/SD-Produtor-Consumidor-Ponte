package br.com.ifms.interfaces;

import java.util.ArrayList;

public interface PonteAplicacao {
    
    public void autenticar(String username, String password) throws InterruptedException;
    
    public boolean isAutenticado();
    
    public void setRecursos(ArrayList<String> recursos, String tipoRecurso) throws InterruptedException;;
    
    public ArrayList<String> getRecursos(String tipo) throws InterruptedException;; 
    
}
