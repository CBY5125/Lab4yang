/** Project: Caesar Cipher, HMAC/SHA256 Hash
 * Purpose Details: Use Caesar Cipher and HMAC/SHA256 to encrypted and decrypted message send
 * Course:IST242
 * Author:Christina Yang
 * Date Developed: Jun14
 * Last Date Changed:Jun17
 * Rev:4

 */
package RabbitMQ;
import java.util.Scanner;

public class CaesarCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter shift value (integer): ");
        int shiftValue = scanner.nextInt();

        String encryptedText = encrypt(plaintext, shiftValue);
        System.out.println("Encrypted text: " + encryptedText);

        scanner.close();
    }

    public static String encrypt(String plaintext, int shift) {
        StringBuilder encryptedText = new StringBuilder();

        for (char character : plaintext.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                int originalAlphabetPosition = character - base;
                int newAlphabetPosition = (originalAlphabetPosition + shift) % 26;
                char newCharacter = (char) (base + newAlphabetPosition);
                encryptedText.append(newCharacter);
            } else {
                encryptedText.append(character);
            }
        }

        return encryptedText.toString();
    }
}
