
package compilador;

public class Pila {
    private NodoPila cima;
    int longitud;
    public Pila(){
        cima=null;
        longitud = 0;
    }
    
public boolean pilaVacia(){
    return cima==null;
}

public void push(String variable, String tipoDato){
    NodoPila nuevo = new NodoPila(variable,tipoDato);
    if(pilaVacia()){
        cima = nuevo;
        longitud++;
    }else{
        nuevo.siguiente=cima;
        cima=nuevo;
        longitud++;
    }
}

public String[] pop(){
    String[] contenido = {cima.variable,cima.tipoDato};
    cima = cima.siguiente;
    longitud--;
    return contenido;
}

public int tamanioPila(){
    return longitud;
}

public void limpiarPila(){
    while(!pilaVacia()){
        pop();
    }
}

public String obtenerTipo(String variable){
    String tipo = "";
     NodoPila recorrido = cima;
        while(recorrido != null){
            String[] contenido = {recorrido.variable};
            if((variable.equals(contenido[0]))){
                tipo = recorrido.tipoDato;
            }
            recorrido = recorrido.siguiente;
        }
    return tipo;
}

public boolean buscarElemento(String variable){
    boolean encontrado = false;
    if(pilaVacia()){
        return encontrado;
    }else{
        NodoPila recorrido = cima;
        while(recorrido != null){
            String[] contenido = {recorrido.variable,recorrido.tipoDato};
            if((variable.equals(contenido[0]))){
                encontrado = true;
            }
            recorrido = recorrido.siguiente;
        }
    }
   return encontrado;
}
}
