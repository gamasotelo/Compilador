
package compilador;

public class ElementoTablaSimbolos {
    String tipo, nombre, valor;
    
    public ElementoTablaSimbolos(String t, String n, String v){
        this.tipo = t;
        this.nombre = n;
        this.valor = v;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getValor() {
        return valor;
    }
    
    
    
    
}
