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

public class CaesarCipher {
    
    private static final int ALPHABET_LENGTH = 26;
    
    private static class LetterASCIICodes {
        static final int A = 65;
        static final int Z = 90;
        static final int a = 97;
        static final int z = 122;
    }

    private static boolean isUpperCaseLetterOutOfRange(int charCode, int cipherDisplacement) {
        if (charCode >= LetterASCIICodes.A && charCode <= LetterASCIICodes.Z) {

            if (charCode + cipherDisplacement > LetterASCIICodes.Z || charCode - cipherDisplacement < LetterASCIICodes.A) {
                return true;
            }
            return false;

        }
        return true;
    }
    
    private static boolean isLowerCaseOutOfRange(int charCode, int cipherDisplacement) {
        if (charCode >= LetterASCIICodes.a && charCode <= LetterASCIICodes.z) {

            if (charCode + cipherDisplacement > LetterASCIICodes.z || charCode - cipherDisplacement < LetterASCIICodes.a) {
                return true;
            }
            return false;

        }
        return true;
    }
    
    private static boolean isOutOfAlphabet(int charCode, int cipherDisplacement) {
        return isUpperCaseLetterOutOfRange(charCode, cipherDisplacement) ||
               isLowerCaseOutOfRange(charCode, cipherDisplacement);
    }

    public static String cipher(String text, int cipherDisplacement) {
        StringBuilder cipher = new StringBuilder();
        char cipheredLetter;
        int displacementToApply, currentChar;
        cipherDisplacement = cipherDisplacement % ALPHABET_LENGTH;
        
        for (int i = 0; i < text.length(); i++) {
            currentChar = text.charAt(i);

            if (isOutOfAlphabet(currentChar, cipherDisplacement)) {
                displacementToApply = cipherDisplacement - ALPHABET_LENGTH;
            } else {
                displacementToApply = cipherDisplacement;
            }

            cipheredLetter = (char) (currentChar + displacementToApply);
            cipher.append(cipheredLetter);
        }
        return cipher.toString();
    }
    
    public static String decipher(String text, int cipherDisplacement) {
        StringBuilder decipher = new StringBuilder();
        char cipheredLetter;
        int displacementToApply, currentChar;
        cipherDisplacement = -cipherDisplacement % ALPHABET_LENGTH;
        
        for (int i = 0; i < text.length(); i++) {
            currentChar = text.charAt(i);

            if (isOutOfAlphabet(currentChar, cipherDisplacement)) {
                displacementToApply = cipherDisplacement + ALPHABET_LENGTH;
            } else {
                displacementToApply = cipherDisplacement;
            }

            cipheredLetter = (char) (currentChar + displacementToApply);
            decipher.append(cipheredLetter);
        }
        return decipher.toString();
    }

    /* Quería comentar que viendo que tanto cifrar como descifrar tienen una lógica idéntica solo
    diferenciada por el operador del for se me ocurrió sacar el for a otro método que operara de x
    manera según un parámetro que se le pasara. Pero sabiendo que este tipo de cifrado sería muy
    raro que cambiara me pareció un nivel innecesario de abstracción.
    */
    
    public static void main(String[] args) {
        // Test 1
        String result1 = cipher("Hello World", 1);
        String expected1 = "Ifmmp!Xpsme";
        assert result1.equals(expected1) : 
            String.format("%s === '%s'", result1, expected1);
        
        // Test 2
        String ciphered = cipher("Hello World", 3);
        String result2 = decipher(ciphered, 3);
        String expected2 = "Hello World";
        assert result2.equals(expected2) : 
            String.format("%s === '%s'", result2, expected2);
        
        System.out.println("Todos los tests han pasado correctamente");
    }
}