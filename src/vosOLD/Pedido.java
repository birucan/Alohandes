package vosOLD;
import org.codehaus.jackson.annotate.JsonProperty;
public class Pedido {
@JsonProperty(value="timestamp")
private long timestamp;

@JsonProperty(value="idCliente")
private long idCliente;

@JsonProperty(value="idRestaurante")
private long idRestaurante;

@JsonProperty(value="idProducto")
private long idProducto;

@JsonProperty(value="idMenu")
private long idMenu;

@JsonProperty(value="aceptado")
private boolean aceptado;

@JsonProperty(value="precio")
private long precio;

public Pedido(@JsonProperty(value="timestamp")long timestamp,@JsonProperty(value="idCliente") long idCliente,@JsonProperty(value="idRestaurante") long idRestaurante,@JsonProperty(value="idProducto") long idProducto,@JsonProperty(value="idMenu") long idMenu,@JsonProperty(value="aceptado") boolean aceptado,
		@JsonProperty(value="precio")long precio) {
	super();
	this.timestamp = timestamp;
	this.idCliente = idCliente;
	this.idRestaurante = idRestaurante;
	this.idProducto = idProducto;
	this.idMenu = idMenu;
	this.aceptado = aceptado;
	this.precio = precio;
}

public long getTimestamp() {
	return timestamp;
}

public void setTimestamp(long timestamp) {
	this.timestamp = timestamp;
}

public long getIdCliente() {
	return idCliente;
}

public void setIdCliente(long idCliente) {
	this.idCliente = idCliente;
}

public long getIdRestaurante() {
	return idRestaurante;
}

public void setIdRestaurante(long idRestaurante) {
	this.idRestaurante = idRestaurante;
}

public long getIdProducto() {
	return idProducto;
}

public void setIdProducto(long idProducto) {
	this.idProducto = idProducto;
}

public long getIdMenu() {
	return idMenu;
}

public void setIdMenu(long idMenu) {
	this.idMenu = idMenu;
}

public boolean isAceptado() {
	return aceptado;
}

public void setAceptado(boolean aceptado) {
	this.aceptado = aceptado;
}

public long getPrecio() {
	return precio;
}

public void setPrecio(long precio) {
	this.precio = precio;
}

@Override
public String toString() {
	return "Pedido [timestamp=" + timestamp + ", idCliente=" + idCliente + ", idRestaurante=" + idRestaurante
			+ ", idProducto=" + idProducto + ", idMenu=" + idMenu + ", aceptado=" + aceptado + ", precio=" + precio
			+ "]";
}


}
