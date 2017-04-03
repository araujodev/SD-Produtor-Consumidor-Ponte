package br.com.ifms.consumidor;


import br.com.ifms.interfaces.PonteAplicacao;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor implements Runnable{
    
    private PonteAplicacao ponte;
    private boolean usuarioAutenticado = false;
    private ArrayList<String> recursosAdministrador;
    private ArrayList<String> recursosUsuario;
    
    
    public Consumidor(PonteAplicacao ponte){
        this.ponte = ponte;
        this.recursosAdministrador = new ArrayList<String>();
        this.recursosUsuario = new ArrayList<String>();
        
        this.recursosAdministrador.add("Financeiro");
        this.recursosAdministrador.add("Estoque");
        this.recursosAdministrador.add("Vendas");
        this.recursosAdministrador.add("Usuarios");
        
        this.recursosUsuario.add("Estoque");
        this.recursosUsuario.add("Vendas");
        this.recursosUsuario.add("Caixa");
    }

    @Override
    public void run() {
        System.out.println(ponte.isAutenticado() ? "Autenticado Com Sucesso!" : "Erro ao logar." );
        this.usuarioAutenticado = ponte.isAutenticado();
        if(this.usuarioAutenticado){
            try {
                ponte.setRecursos(recursosAdministrador, "admin");
                ponte.setRecursos(recursosUsuario, "user");
            } catch (InterruptedException ex) {
            }
            
        }
    }
    
}
