package br.com.ifms.interfaces;

import br.com.ifms.dao.UsuarioDao;
import br.com.ifms.model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PonteImpl  implements PonteAplicacao{

    private ArrayList<Usuario> users;
    private ArrayList<String> recursosAdmin;
    private ArrayList<String> recursosUsuarios;
    private Usuario requestUser; 
    private boolean autenticado = false, ocupado = false;
    
    public PonteImpl(){
        users = new ArrayList<>();
        this.recursosAdmin = new ArrayList<>();
        this.recursosUsuarios = new ArrayList<>();
    }
    
    @Override
    public synchronized void autenticar(String email, String senha) throws InterruptedException {
        this.requestUser = new Usuario("", email, senha);
        
        Usuario user = null;
        try {
            UsuarioDao userDao = new UsuarioDao();
            user = userDao.getUserByUsername(email);
        } catch (SQLException ex) {
        }
        
        if(user != null){
            if(user.getEmail().equals(requestUser.getEmail()) && user.getSenha().equals(requestUser.getSenha())){
                this.autenticado = true;
            }
        }
        
    }

    @Override
    public synchronized boolean isAutenticado() {
        if(this.autenticado){
            return true;
        }else{
            return false;
        }
    }   
    
    @Override
    public synchronized void setRecursos(ArrayList<String> recursos, String tipoRecurso) throws InterruptedException{
        while(ocupado){
            wait();
        }
        
        for (int i = 0; i < recursos.size(); i++) {
            
            if(tipoRecurso == "admin"){
                this.recursosAdmin.add(recursos.get(i));
            }
            if(tipoRecurso == "user"){
                this.recursosUsuarios.add(recursos.get(i));
            }
        }
        ocupado = true;
        notifyAll();
    }
    
    @Override
    public synchronized ArrayList<String> getRecursos(String tipo) throws InterruptedException{
        while(!ocupado){
            wait();
        }
        
        ocupado = false;
        notifyAll();
        
        if(tipo == "admin"){
            return this.recursosAdmin;
        }else{
            return this.recursosUsuarios;
        }
    }
    
    public void populaArrayBancoDados(){
        this.users.add(new Usuario("Leandro Araujo", "lelesouara", "123"));
        this.users.add(new Usuario("William Goulart", "wgoulart", "1234"));
        this.users.add(new Usuario("Prof Jeferson", "jeferson", "12345"));
    }
}
