
package compilador;

public class NodoPila {
    String variable;
    String tipoDato;
    NodoPila siguiente;
    
    NodoPila(String variable,String tipoDato){
        this.variable = variable;
        this.tipoDato = tipoDato;
        siguiente = null;
    }
}
