package plp.imperative2.extensao;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Id;

public class IdCanal extends Id{

	ReentrantLock lock;
	Condition isEmpty;
	Condition isFull;
	Tipo tipoCanal;
	
	public Tipo getTipoCanal() {
		return tipoCanal;
	}

	public void setTipoCanal(Tipo tipoCanal) {
		this.tipoCanal = tipoCanal;
	}

	public IdCanal(String strName) {
		super(strName);
		lock = new ReentrantLock();
		isEmpty = lock.newCondition();
		isFull = lock.newCondition();
	}

}
