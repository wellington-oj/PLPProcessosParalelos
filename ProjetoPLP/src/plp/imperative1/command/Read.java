package plp.imperative1.command;

import plp.expressions2.expression.Id;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.expressions2.memory.VariavelJaDeclaradaException;

public class Read implements IO {

    private Id id;
    
    public Read ( Id id){
        this.id = id;
    }
    
    /**
     * L� da entrada padr�o.
     *
     * @param ambiente o ambiente de execu��o.
     *
     * @return o ambiente depois de modificado pela execu��o
     * do comando read.
     *
     */    
    public AmbienteExecucaoImperativa executar(AmbienteExecucaoImperativa ambiente) 
        throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException, EntradaVaziaException  {
        ambiente.changeValor(id, ambiente.read());
        return ambiente;     
    }   

    /**
     * Realiza a verificacao de tipos da entrada
     *
     * @param ambiente o ambiente de compila��o.
     * @return <code>true</code> se a express�o da entrada est� bem tipada;
     *          <code>false</code> caso contrario.
     */
    public boolean checaTipo(AmbienteCompilacaoImperativa ambiente) 
        throws VariavelNaoDeclaradaException, EntradaVaziaException, VariavelJaDeclaradaException {
        return id.getTipo(ambiente).equals(ambiente.getTipoEntrada());
    }

}
