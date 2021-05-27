package compilador;


public class Comprobaciones {
    
    Pila variablesDeclaradas = new Pila();
    
    public String validarDeclaracion(String cadena){
        //Si todas las variables terminan siendo true, la cadena esta bien
        boolean verifica_tipo = false;
        boolean verifica_nombre = false;
        boolean verifica_puntoycoma = false;
        boolean verifica_coincidencias = false;
        
        String mensaje_error = " ";
        Tipos tipo = new Tipos();
        Nombres nombre = new Nombres();
        /*cadenaSeparada[0] = TIPO DE VARIABLE
        *cadenaSeparada[1] = NOMBRE DE LA VARIABLE
        *cadenaSeparada[2] = PUNTO Y COMA
        */
        String[] cadenaSeparada = separarCadena(cadena,1);
        
        //Se ejecuta si hubo un error al intentar separar la cadena
        if (cadenaSeparada[0].equals("Error:5888")){
            return cadenaSeparada[1];
        }
        verifica_tipo = tipo.ValidarTipo(cadenaSeparada[0]);
        verifica_nombre = nombre.validarNombre(cadenaSeparada[1]);
        if(cadenaSeparada[2].equals(";")){
            verifica_puntoycoma = true;
        }else{
            if(mensaje_error.equals(" ")){
                mensaje_error = "Error de punto y coma";
            }else{
                mensaje_error = mensaje_error + ", Error de punto y coma";
            }
        }
        
        //si existe el elemento dentro de la pila o no
        if(variablesDeclaradas.buscarElemento(cadenaSeparada[1]) == false){
            variablesDeclaradas.push(cadenaSeparada[1], cadenaSeparada[0]);
            verifica_coincidencias = true;
        }else{
            if(mensaje_error.equals(" ")){
                mensaje_error = "Error variable ya existente";
            }else{
                mensaje_error = mensaje_error + ",Error variable ya existente";
            }
        }
       
        if(verifica_nombre == false){
            if(mensaje_error.equals(" ")){
                mensaje_error = "Error de formato de nombre";
            }else{
                mensaje_error = mensaje_error + ",Error de formato de nombre";
            }
        }
        
        if(verifica_tipo == false){
            if(mensaje_error.equals(" ")){
                mensaje_error = "Error de tipo";
            }else{
                mensaje_error = mensaje_error + ",Error de tipo";
            }
        }
        
        if(verifica_tipo && verifica_nombre && verifica_puntoycoma && verifica_coincidencias){
            mensaje_error = "Compilación correcta";
        }
        return mensaje_error;
    }
    
    public String[] separarCadena(String cadena, int queRealizar){
        String[] auxiliar = new String[2];
        String resto = "";
        String puntoycoma = "";
        if(queRealizar==1){
            auxiliar = cadena.split(" ");
        }else if(queRealizar==2){
            auxiliar = cadena.split("=");
        }else if(queRealizar==3){
            auxiliar = new String[3];
            int indice_operador;
            if(cadena.contains("+")){
               indice_operador = cadena.indexOf("+");
               auxiliar[0] = cadena.substring(0,indice_operador).trim();
               auxiliar[1] = cadena.substring(indice_operador + 1, cadena.length()).trim();
               auxiliar[2] = "+";
            }else if(cadena.contains("-")){
               indice_operador = cadena.indexOf("-");
               auxiliar[0] = cadena.substring(0,indice_operador).trim();
               auxiliar[1] = cadena.substring(indice_operador + 1, cadena.length()).trim();
               auxiliar[2] = "-";
            }else if(cadena.contains("*")){
               indice_operador = cadena.indexOf("*");
               auxiliar[0] = cadena.substring(0,indice_operador).trim();
               auxiliar[1] = cadena.substring(indice_operador + 1, cadena.length()).trim();
               auxiliar[2] = "*";
            }else if(cadena.contains("/")){
               indice_operador = cadena.indexOf("/");
               auxiliar[0] = cadena.substring(0,indice_operador).trim();
               auxiliar[1] = cadena.substring(indice_operador + 1, cadena.length()).trim();
               auxiliar[2] = "/";
            }
        }
        try{
                puntoycoma = auxiliar[1].substring(auxiliar[1].length()-1);
                resto = auxiliar[1].substring(0,auxiliar[1].length()-1);
            }catch(Exception e){
                String[] cadenaSeparada = {"Error:5888","Error de tipo"};
                return cadenaSeparada;
            }
            String[] cadenaSeparada = {auxiliar[0],resto,puntoycoma};
         return cadenaSeparada;
    }
    
    public String validarAsignarValor(String cadena){
        String[] cadenaSeparada = separarCadena(cadena,2);
        Tipos tipo = new Tipos();
        
        boolean variableDeclarada = false;
        boolean valorcorrecto = false;
        boolean puntoycoma = false;
        
        //Valora si la variable ya fue declarada
        variableDeclarada = variablesDeclaradas.buscarElemento(cadenaSeparada[0].trim());

        //Valora si el valor esta correcto
        if(variableDeclarada){
            String tipoGuardadoEnPila = variablesDeclaradas.obtenerTipo(cadenaSeparada[0].trim());
            valorcorrecto = tipo.ValidarValorAsignado(tipoGuardadoEnPila, cadenaSeparada[1].trim());
        }
        //Valora punto y coma
        if(cadenaSeparada[2].equals(";")){
            puntoycoma=true;
        }
        //Mensajes de error
        String mensaje_error = " ";
        if(!variableDeclarada){
            if(mensaje_error.equals(" ")){
                mensaje_error = "Error variable no declarada";
                return mensaje_error;
            }else{
                mensaje_error = mensaje_error + ",Error variable no declarada";
            }
        }
        
        if(!valorcorrecto){
            if(mensaje_error.equals(" ")){
                mensaje_error = "Error valor asignado incorrecto";
            }else{
                mensaje_error = mensaje_error + ",Error valor asignado incorrecto";
            }
        }
        
        if(!puntoycoma){
            if(mensaje_error.equals(" ")){
                mensaje_error = "Error de punto y coma";
            }else{
                mensaje_error = mensaje_error + ",Error de punto y coma";
            }
        }
        
        if(variableDeclarada && valorcorrecto && puntoycoma){
            mensaje_error = "Compilación correcta";
        }
        
        return mensaje_error;
    }
    
    public String ValidarOperaciones(String cadena){
        boolean verifica_variablesExistentes = false;
        boolean verifica_operacionPermitida = false;
        boolean verifica_puntoycoma = false;
        boolean verifica_datosCompatibles = false;
        String mensaje_error = "";
        
        //Separación de cadenas
        String[] cadenaSeparadaPorIgual = separarCadena(cadena, 2);
        String[] cadenaSeparadaPorOperador = separarCadena(cadenaSeparadaPorIgual[1],3);
        
        //Verificamos punto y coma
        if(cadenaSeparadaPorIgual[2].equals(";")){
            verifica_puntoycoma = true;
        }else{
            mensaje_error = "Error de punto y coma";
        }
        
        //Verificamos que todas la variables hayan sido declaradas previamente
        if(verifica_puntoycoma){
            boolean resultado = variablesDeclaradas.buscarElemento(cadenaSeparadaPorIgual[0].trim());
            boolean operando1 = variablesDeclaradas.buscarElemento(cadenaSeparadaPorOperador[1].trim());
            boolean operando2 = variablesDeclaradas.buscarElemento(cadenaSeparadaPorOperador[2].trim());
            System.out.println(resultado + ", " + operando1 + ", " + operando2);
            if(resultado && operando1 && operando2){
                verifica_variablesExistentes = true;
            }else{
                //mensaje_error = "Error variable no declarada";   
                mensaje_error = "Operación no valida";
            }
        }
        //Verificamos que los tipos de dato sean iguales
        if(verifica_puntoycoma && verifica_variablesExistentes){
            String resultado = variablesDeclaradas.obtenerTipo(cadenaSeparadaPorIgual[0]);
            String operando1 = variablesDeclaradas.obtenerTipo(cadenaSeparadaPorOperador[1]);
            String operando2 = variablesDeclaradas.obtenerTipo(cadenaSeparadaPorOperador[2]);
            if(resultado.equals(operando1) && operando2.equals(operando1)){
                verifica_datosCompatibles = true;
            }else{
                mensaje_error = "Tipos de dato no compatibles";
            }
        }
        
        //Verificamos que la operacion declarada sea valida
        if(verifica_puntoycoma && verifica_variablesExistentes && verifica_datosCompatibles){
            Tipos verificarOperacion = new Tipos();
            String resultado = variablesDeclaradas.obtenerTipo(cadenaSeparadaPorIgual[0]);
            verifica_operacionPermitida = verificarOperacion.validarOperacion(resultado, cadenaSeparadaPorOperador[2]);
            if(!verifica_operacionPermitida){
                mensaje_error = "Operación no valida";
            }
        }
        
        //Mensaje
        if(verifica_puntoycoma && verifica_variablesExistentes && verifica_datosCompatibles && verifica_operacionPermitida){
            mensaje_error = "Compilación correcta";
        }
        
        return mensaje_error;
    }
}
