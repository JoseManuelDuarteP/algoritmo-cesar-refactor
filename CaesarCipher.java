/*
El Cifrado César es una de las técnicas de cifrado más simples y conocidas. 
Se trata de un tipo de cifrado de sustitución en el que cada letra del texto sin cifrar es reemplazada por otra letra 
que se encuentra un número fijo de posiciones hacia abajo en el alfabeto. 
Por ejemplo, con un desplazamiento hacia la derecha de 3, la letra E sería reemplazada por H, 
la F se convertiría en I, y así sucesivamente.
Esta transformación se puede representar alineando dos alfabetos: el alfabeto cifrado es el alfabeto normal 
rotado hacia la derecha un cierto número de posiciones.

A continuación tienes dos funciones que codifican y decodifican usando el cifrado César.
Tu tarea consiste en entender el código y refactorizarlo para que sea lo más limpio posible, 
según lo visto en la sesión de Clean Code
*/

import static java.lang.Character.*;

public class CaesarCipher {
    
    private static final int ALPHABET_LENGTH = 26;

    private static char spaceHandler(char currentChar, int cipherDisplacement) {
        return (char) (currentChar + cipherDisplacement);
    }

    public static String resolver(String text, int cipherDisplacement) {
        StringBuilder cipher = new StringBuilder();
        char cipheredLetter, currentChar;
        cipherDisplacement = cipherDisplacement % ALPHABET_LENGTH;

        for (int i = 0; i < text.length(); i++) {
            currentChar = text.charAt(i);

            if (isSpaceChar(currentChar) || isSpaceChar(currentChar + cipherDisplacement)) {
                cipher.append(spaceHandler(currentChar, cipherDisplacement));
                continue;
            }

            cipheredLetter = (char) (currentChar + cipherDisplacement);
            cipher.append(cipheredLetter);
        }
        return cipher.toString();
    }

    public static void main(String[] args) {
        // Test 1
        String result1 = resolver("Hello World", 1);
        String expected1 = "Ifmmp!Xpsme";
        if (!result1.equals(expected1)) {
            throw new RuntimeException(
                    String.format("Test 1 FAILED: '%s' !== '%s'", result1, expected1)
            );
        }

        // Test 2
        String ciphered2 = resolver("Hello World", 3);
        String result2 = resolver(ciphered2, -3);
        String expected2 = "Hello World";
        if (!result2.equals(expected2)) {
            throw new RuntimeException(
                    String.format("Test 2 FAILED: '%s' !== '%s'", result2, expected2)
            );
        }
        // Test 3
        String ciphered3 = resolver("¡Hola que tal...!", 28);
        String result3 = resolver(ciphered3, -28);
        String expected3 = "¡Hola que tal...!";
        if (!result3.equals(expected3)) {
            throw new RuntimeException(
                    String.format("Test 3 FAILED: '%s' !== '%s'", result3, expected3)
            );
        }


        System.out.println("Todos los tests han pasado correctamente");
    }
}