package plp.imperative2.declaration;

import plp.imperative1.command.Comando;

public class DefProcedimento {
    
    private ListaDeclaracaoParametro parametrosFormais;
    
    private Comando comando;
    
    public DefProcedimento(ListaDeclaracaoParametro parametrosFormais, Comando comando) {
        this.parametrosFormais = parametrosFormais;
    	this.comando = comando;
    }

    public Comando getComando(){
        return comando;
    }

    public ListaDeclaracaoParametro getParametrosFormais(){
        return parametrosFormais;
    }
	
}




