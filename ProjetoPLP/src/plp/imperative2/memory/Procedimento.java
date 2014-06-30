package plp.imperative2.memory;

import plp.imperative1.command.Comando;
import plp.imperative2.declaration.DefProcedimento;
import plp.imperative2.declaration.ListaDeclaracaoParametro;

public class Procedimento {

    private ListaDeclaracaoParametro parametrosFormais;
    
    private Comando comando;
    
    public Procedimento(ListaDeclaracaoParametro parametrosFormais, DefProcedimento parametro2) {
        this.parametrosFormais = parametrosFormais;
        this.comando = parametro2.getComando();
    }
    
    public ListaDeclaracaoParametro getParametrosFormais() { 
        return parametrosFormais;
    }
        
    public Comando getComando() {
        return comando;
    }
}