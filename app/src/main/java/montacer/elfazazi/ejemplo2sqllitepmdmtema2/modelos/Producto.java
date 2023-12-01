package montacer.elfazazi.ejemplo2sqllitepmdmtema2.modelos;

import java.io.Serial;
import java.io.Serializable;

public class Producto {
    private String nombre;
    private int cantidad;
    private float precio;
    private float total;

    public Producto() {
    }

    public Producto(String nombre, int cantidad, float precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        calcularTotal();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularTotal();
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
        calcularTotal();
    }

    public float getTotal() {
        return total;
    }

    private void calcularTotal(){
        total = cantidad*precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", total=" + total +
                '}';
    }
}
