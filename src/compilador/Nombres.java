
package compilador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Nombres {
    Pattern patron;
    
    
    Nombres(){
        patron = Pattern.compile("^[a-zA-Z]*+[1-9]*");
    }
    
    public boolean validarNombre(String nombre){
        boolean correcto = false;
        Matcher mat = patron.matcher(nombre);
        if(mat.matches()){
            correcto = true;
        }
        return correcto;
    }
}
