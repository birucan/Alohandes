package vosOLD;

import org.codehaus.jackson.annotate.JsonProperty;

public class Ingreso {
	@JsonProperty(value="idRestaurante")
	private long idRestaurante;
	
	@JsonProperty(value="Balance")
	private long Balance;
	
	
	
	public Ingreso(@JsonProperty(value="idRestaurante")long idRestaurante,@JsonProperty(value="Balance") long balance) {
		super();
		this.idRestaurante = idRestaurante;
		this.Balance = balance;
	}



	public long getIdRestaurante() {
		return idRestaurante;
	}



	public void setIdRestaurante(long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}



	public long getBalance() {
		return Balance;
	}



	public void setBalance(long balance) {
		Balance = balance;
	}
	
	
}
