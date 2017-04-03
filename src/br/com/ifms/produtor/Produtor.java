package br.com.ifms.produtor;


import br.com.ifms.interfaces.PonteAplicacao;
import java.util.ArrayList;

public class Produtor implements Runnable{
    
    private PonteAplicacao ponte;
    
    public Produtor(PonteAplicacao ponte){
        this.ponte = ponte;
    }

    
    @Override
    public void run() {
        try {
            ponte.autenticar("sergio@sergio.com", "1234");
            
            ArrayList<String> recursos = new ArrayList<>();
            recursos = ponte.getRecursos("admin");
            for (int i = 0; i < recursos.size(); i++) {
                System.out.println("Recurso Liberados [Admin] Retorno: " + recursos.get(i));
            }
            
            recursos = ponte.getRecursos("user");
            for (int i = 0; i < recursos.size(); i++) {
                System.out.println("Recurso Liberados [Users] Retorno: " + recursos.get(i));
            }
            
        } catch (InterruptedException ex) {
        }   
    }
}
