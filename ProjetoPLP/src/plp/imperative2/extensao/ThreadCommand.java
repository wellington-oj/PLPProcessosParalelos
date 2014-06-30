package plp.imperative2.extensao;

import plp.imperative1.command.Comando;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;

import java.lang.Thread;

public class ThreadCommand extends Thread{

    private Comando comando;
    private AmbienteExecucaoImperativa ambiente;

    public ThreadCommand(Comando comando, AmbienteExecucaoImperativa ambiente){
        this.comando = comando;
        this.ambiente = ambiente;
    }

    public void run() {
      try {
        ambiente = comando.executar(ambiente);
      } 
      catch (IdentificadorJaDeclaradoException identJaDecl) {
    	  identJaDecl.printStackTrace();
      } 
      catch (IdentificadorNaoDeclaradoException identNaoDecl) {
    	  identNaoDecl.printStackTrace();
      } 
      catch (EntradaVaziaException entradaVazia) {
    	  entradaVazia.printStackTrace();
      }
    }
}