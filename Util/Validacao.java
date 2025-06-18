package Util;
import java.io.*;

//Os dois desnecessarios, ate entao
import java.util.Date;
//import java.util.Calendar;

//Precisamos criar um padaro paraID's, e criar um metodo de validacao para tal;

public class Validacao implements Serializable{

    private static  BufferedReader entry = new BufferedReader(new InputStreamReader(System.in));

//validar dados inteiros
    public int validarInt(String msg, int max, int min) {

        int valor = 0;

        try {
            do {
                System.out.print(msg);
                valor = Integer.parseInt(entry.readLine());

                if (valor < min || valor > max) {
                    System.out.println("Entrada fora dos parametros\nTente novamnte");
                }
            } while (valor < min || valor > max);
        } catch (NumberFormatException e) {
            System.out.println("Por favor, Insira numeros apenas;");
            return validarInt(msg, max, min);
        } catch (IOException t) {
            System.out.println("Entrada Invalida\nTente novamente");
            return validarInt(msg, max, min);
        }
        return valor;
    }
    
    //Espera comando pra continuar
    public void esperarParaContinuar() {
        System.out.println("\nPressione Enter para continuar...");
        try {
            System.in.read(); // Espera que o usuário pressione Enter
            // Consumir quaisquer outros caracteres no buffer, como o newline
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (IOException e) {
           
        }
    }


    //Validar NumTelefone
    public String validarTelefone(String msg) throws IOException{
        String telefone = "";
        try {
            do {
                System.out.print(msg);
                telefone = entry.readLine();

                //Testar se realmente tudo e numero;
                Integer.parseInt(telefone);
                //Integer.parseInt(String.valueOf(Float.parseFloat(String.valueOf(Double.parseDouble(telefone)))));
                if((!telefone.substring(0, 2).equalsIgnoreCase("82") &&
                !telefone.substring(0, 2).equalsIgnoreCase("83") &&
                !telefone.substring(0, 2).equalsIgnoreCase("84") &&
                !telefone.substring(0, 2).equalsIgnoreCase("85") &&
                !telefone.substring(0, 2).equalsIgnoreCase("86") &&
                !telefone.substring(0, 2).equalsIgnoreCase("87")) || telefone.length() != 9) {
                    System.out.println("Telefone Invalido\nO mesmo deve conter 9 digitos, dos quais 2 referenciam a operadora(82/3, 84/5 e 86/7)\nTente novamente;");
                }
            } while ((!telefone.substring(0, 2).equalsIgnoreCase("82") &&
                      !telefone.substring(0, 2).equalsIgnoreCase("83") &&
                      !telefone.substring(0, 2).equalsIgnoreCase("84") &&
                      !telefone.substring(0, 2).equalsIgnoreCase("85") &&
                      !telefone.substring(0, 2).equalsIgnoreCase("86") &&
                      !telefone.substring(0, 2).equalsIgnoreCase("87")) || telefone.length() != 9);
        } catch (NumberFormatException t) {
            System.out.println("Ensira apenas numeros\nTente novamente;");
            return validarTelefone(msg);
        } catch (IOException e) {
            System.out.println("Erros\nTente novamente");
            return validarTelefone(msg);
        }
        return telefone;
    }

//validar dados String
    public String validarString(String msg) {

        String dado = "";
        try {
            do {
                System.out.print(msg);
                dado = entry.readLine();
                if (dado.length() < 3) {
                    System.out.println("Entrada invalida");
                }
            } while (dado.length() < 3);
            for (char c: dado.toCharArray()) {
                if (Character.isDigit(c)) {
                    System.out.println("Entrada invalida\nTente novamente");
                    return validarString(msg);
                }
            }
            
        } catch (IOException e) {
            System.out.println("Erro\nTente novamente: ");
            return validarString(msg);
        }
        return dado;
    }

// validar dados char
    public char validarChar(String msg, char val1, char val2) {

        char dado = 0;
        try {
            do {
                System.out.print(msg);
                dado = entry.readLine().charAt(0);
                if (dado != val1 && dado != val2) {
                    System.out.println("Entrada invalida");
                }
            } while (dado != val1 && dado != val2);

        } catch (IOException e) {
            System.out.println("Erro\nTente novamente");
            return validarChar(msg, val1, val2);
        }

        return dado;
    }
       
    

    //Novos matodos validarID
    //ID's devem estar na forma EST#####, DOC#####, FUN#####, LIV#####, EMP)
    public String validarID(String lastId) {
        int id = 0;
        try {
            id = Integer.parseInt(lastId.substring(3));
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException");
            return validarID(lastId);
        }
        id++;
        if(id < 10) {
            return lastId.substring(0, 3) + "0000" + Integer.toString(id);
        } else if(id < 100){
            return lastId.substring(0, 3) + "000" + Integer.toString(id);
        } else if (id < 1000) {
           return lastId.substring(0, 3) + "00" + Integer.toString(id);
        } else if(id < 10000) {
            return lastId.substring(0, 3) + "0" + Integer.toString(id);
        } else{ 
            return lastId.substring(0, 3) + Integer.toString(id);

        }

    }


    //Para gerar o primeiro ID de cada Objecto
    public String validarID(int indice) {
        if(indice == 1) {
            return "EST00001";
        } else if(indice == 2) {
            return "DOC00001";
        } else if(indice == 3) {
            return "FUN00001";
        } else if(indice == 4) {
            return "LIV00001";
        } else if(indice == 5){
            return "EXE00001";
        } else if(indice == 6) {
            return "ARE00001";
        } else if(indice == 7) {
            return "AUT00001";
        } else if(indice == 8) {
            return "EDI00001";
        } else if(indice == 9) {
            return "EMP00001";
        } else {
            return "PAL00001";
        }
    }

    //Usado para encontrar um retornar um ID, mas para casos de precisar-se de ID para encontrar um objecto
    public String validarID(String msg, String tipo) throws IOException{
        System.out.println(" ");
        int id = 0;
        try {
             id = Integer.parseInt(entry.readLine());
            
            if(id < 1) {
                System.out.println("ID Invalido\nTente Novamente");   
                return validarID(msg, tipo);
            }
        } catch (NumberFormatException e) {
            System.out.println("Insira apenas numeros\nTente novamebte");
            return validarID(msg, tipo);
        } catch (IOException t) {
            System.out.println("IOException");
            return validarID(msg, tipo);
        }
        tipo = tipo.toUpperCase();
        
        if(id < 10) {
           return tipo + "0000" + Integer.toString(id);
        } else if(id < 100){
            return tipo + "000" + Integer.toString(id);
        } else if (id < 1000) {
            return tipo + "00" + Integer.toString(id);
        } else if(id < 10000) {
            return tipo + "0" + Integer.toString(id);
        } else {
            return tipo + Integer.toString(id);
        }

    }


    //Usado pra validar data 
    public Date validarData(String msg) {
        String data = "";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        Date a = null;
        try {
            do {
                System.out.print(msg + " (formato -> ANO-MES-DIA: yyyy-MM-dd): ");
                data = entry.readLine();
                try {
                    a = sdf.parse(data);
                    break;
                } catch (java.text.ParseException e) {
                    System.out.println("Data inválida. Use o formato: ANO-MES-DIA: yyyy-MM-dd");
                }
            } while (true);
        } catch (IOException e) {
            System.out.println("Erro de entrada. Tente novamente.");
            return validarData(msg);
        } 
        return a;
    }

}