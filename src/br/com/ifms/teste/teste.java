package br.com.ifms.teste;


import br.com.ifms.consumidor.Consumidor;
import br.com.ifms.interfaces.PonteAplicacao;
import br.com.ifms.interfaces.PonteImpl;
import br.com.ifms.produtor.Produtor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aluno
 */
public class teste {
    
    public static void main(String[] args) {
        
        PonteAplicacao ponte = new PonteImpl();
        new Thread(new Produtor(ponte)).start();
        new Thread(new Consumidor(ponte)).start();
        
    }
    
}
