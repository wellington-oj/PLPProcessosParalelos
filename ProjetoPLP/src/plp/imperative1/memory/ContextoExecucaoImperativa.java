package plp.imperative1.memory;

import java.util.HashMap;
import java.util.Stack;

import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.ContextoExecucao;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.imperative2.extensao.IdCanal;
import plp.imperative2.extensao.ControleCanal;

public class ContextoExecucaoImperativa extends ContextoExecucao 
	implements AmbienteExecucaoImperativa {


	public Stack<HashMap<IdCanal, ControleCanal>> pilhaCanal;	
    /**
     * A pilha de blocos de contexto.
     */    
    private ListaValor entrada;
 
    /**
     * A pilha de blocos de contexto.
     */ 
    private ListaValor saida;

    /**
     * Construtor da classe.
     */
    public ContextoExecucaoImperativa(ListaValor entrada){
    	super();
        this.entrada = entrada;
        this.saida = new ListaValor(); 
        this.pilhaCanal = new Stack<>();
    }
   
    public Valor read() throws EntradaVaziaException {
        if(entrada == null || entrada.getHead() == null) {
            throw new EntradaVaziaException();
        }
        Valor aux =  entrada.getHead();
        entrada = (ListaValor) entrada.getTail();
        return aux;
    }
   
    public ListaValor getSaida() {
        return saida;    
    }
     
    public void write(Valor v){
        saida.add(v);
    }

    /**
     * Altera o valor mapeado do id dado.
     *
     * @exception VariavelNaoDeclaradaException se não existir nenhum valor
     *          mapeado ao id dado nesta tabela.
     */
    public void changeValor(Id idArg, Valor valorId) 
        	throws VariavelNaoDeclaradaException {    
		Object result = null;
		Stack<HashMap<Id,Valor>> auxStack = new Stack<HashMap<Id,Valor>>();
		Stack<HashMap<Id,Valor>> stack = this.getPilha();
		
		while (result == null && !stack.empty()) {
			HashMap<Id,Valor> aux = stack.pop();
			auxStack.push(aux);
			result = aux.get(idArg);
			if (result != null) {
				aux.put(idArg, valorId);
			}
		}
		while (!auxStack.empty()) {
			stack.push(auxStack.pop());
		}
		if (result == null) {
			throw new VariavelNaoDeclaradaException(idArg);
		}
    }
    
    
	@Override
	public void mapCanal(IdCanal idArg, ControleCanal valor)
			throws VariavelJaDeclaradaException {
		try {
			HashMap<IdCanal,ControleCanal> aux =  pilhaCanal.peek();
	    	if (aux.put(idArg, valor) != null) {
	    		throw new IdentificadorJaDeclaradoException();
	    	}
		} catch (IdentificadorJaDeclaradoException e) {
			throw new VariavelJaDeclaradaException (idArg);
		}
		
	}

	@Override
	public ControleCanal getCanal(IdCanal idArg) throws VariavelNaoDeclaradaException {
		try {
			ControleCanal result = null;
			Stack<HashMap<IdCanal,ControleCanal>> auxStack = new Stack<HashMap<IdCanal,ControleCanal>>();
			while (result == null && !pilhaCanal.empty()) {
				HashMap<IdCanal,ControleCanal> aux = pilhaCanal.pop();
				auxStack.push(aux);
				result = (ControleCanal) aux.get(idArg);
			}
			while (!auxStack.empty()) {
				pilhaCanal.push(auxStack.pop());
			}
			if (result == null) {
				throw new IdentificadorNaoDeclaradoException();
			} 
			
			return result;
		} catch (IdentificadorNaoDeclaradoException e) {
			throw new VariavelNaoDeclaradaException(idArg);
		}
	}

	public Stack<HashMap<IdCanal, ControleCanal>> getPilhaCanal() {
		return pilhaCanal;
	}

	public void setPilhaCanal(Stack<HashMap<IdCanal, ControleCanal>> pilhaCanal) {
		this.pilhaCanal = pilhaCanal;
	}

	@Override
	public void incrementaCanal() {
		pilhaCanal.push(new HashMap<IdCanal,ControleCanal>());
	}

	@Override
	public void restauraCanal() {
		pilhaCanal.pop();
	}
    
    public Object clone(){
    	return null;
    }

	public ListaValor getEntrada() {
		return entrada;
	}

	public void setEntrada(ListaValor entrada) {
		this.entrada = entrada;
	}

	public void setSaida(ListaValor saida) {
		this.saida = saida;
	}

	@Override
	public boolean pilhaCanalTemAlgo() {
		return !pilhaCanal.isEmpty();
	}
}

