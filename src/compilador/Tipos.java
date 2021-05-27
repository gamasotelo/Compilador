
package compilador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tipos {
    String[] tipos;
    Pattern patron_texto;
    Pattern patron_int;
    Pattern patron_real;
    
    Tipos(){
        tipos = new String[3];
        tipos[0] = "Entero";
        tipos[1] = "Real";
        tipos[2] = "Texto";
        patron_texto = Pattern.compile("[a-zA-Z0-9 ]*");
        patron_int = Pattern.compile("^-?\\d+$");
        patron_real = Pattern.compile("^[0-9]*+([.]?[0-9]*+)");
        
    }
    
    public boolean ValidarTipo(String tipo){
        boolean correcto = false;
        for(int i = 0; i<tipos.length;i++){
            if(tipos[i].equals(tipo)){
                correcto = true;
                break;
            }
        }
        return correcto;
    }
    
    public boolean ValidarValorAsignado(String tipo, String valor){
        boolean valorCorrecto = false;
         Matcher mat;
        switch(tipo){
            case "Entero":
                mat = patron_int.matcher(valor);
                if(mat.matches()){
                    valorCorrecto = true;
                }
                break;
            case "Texto":
                mat = patron_texto.matcher(valor);
                if(mat.matches()){
                    valorCorrecto = true;
                }
                break;
            case "Real":
                mat = patron_real.matcher(valor);
                if(mat.matches()){
                    valorCorrecto = true;
                }
                break;
        }
        return valorCorrecto;
    }
   
    public boolean validarOperacion(String tipo, String operador){
        boolean operacionPermitida = false;
        switch(tipo){
            case "Entero":
                if(operador.equals("+") || operador.equals("-") || operador.equals("*") || operador.equals("/")){
                    operacionPermitida = true;
                }
                break;
            case "Texto":
                if(operador.equals("+")){
                    operacionPermitida = true;
            }
                break;
                
            case "Real":
                if(operador.equals("+") || operador.equals("-") || operador.equals("*") || operador.equals("/")){
                    operacionPermitida = true;
                }
                break;
        }
        return operacionPermitida;
    }
}
