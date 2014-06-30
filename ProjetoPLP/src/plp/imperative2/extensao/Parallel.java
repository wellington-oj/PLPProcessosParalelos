package plp.imperative2.extensao;

import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.imperative2.command.ListaExpressao;
import plp.imperative1.command.*;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;

public class Parallel implements Comando{

	Comando c1,c2;

	public Parallel(Comando c1,Comando c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	@Override
	public AmbienteExecucaoImperativa executar(
			AmbienteExecucaoImperativa ambiente)
					throws IdentificadorJaDeclaradoException,
					IdentificadorNaoDeclaradoException, EntradaVaziaException {
		
		Thread t1 = new ThreadCommand(c1, (AmbienteExecucaoImperativa) ambiente.clone());
		Thread t2 = new ThreadCommand(c2, (AmbienteExecucaoImperativa) ambiente.clone());

		t1.start(); t2.start();

		try {
			t2.join(); t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (this) {
			while(t1.isAlive() || t2.isAlive()){
				try {
					wait(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}	
		
		return ambiente;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		return c1.checaTipo(ambiente) && c2.checaTipo(ambiente);
	}

}
