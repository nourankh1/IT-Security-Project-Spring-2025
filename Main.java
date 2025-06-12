import java.math.BigInteger;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
	    Scanner sc = new Scanner(System.in);
	    RSA rsa = new RSA();

	    System.out.println("Enter Size");
	    int bitLength = sc.nextInt();

	    while (bitLength < 256) {
	        System.out.println("n must be greater than or equals 256");
	        System.out.println("Enter Size");
	        bitLength = sc.nextInt();
	    }

	    rsa.initialize(bitLength);
        //for debugging the p, q, n and phi
	    System.out.println("p: "+rsa.getP());
	    System.out.println("q: "+rsa.getQ());
	    System.out.println("n: "+rsa.getN());
	    System.out.println("phi: "+rsa.getPhi());
	    System.out.println("-----------------------------------------------------------------------------");
	    System.out.println("The generated public key in plaintext: " + rsa.fromBigIntegerToString(rsa.getPublicKey()));
	    System.out.println("The generated public key in big integer: " + rsa.getPublicKey());
	    System.out.println("The generated private key in plaintext: " + rsa.fromBigIntegerToString(rsa.getPrivateKey()));
	    System.out.println("The generated private key in big integer: " + rsa.getPrivateKey());


	    // Read from file 
	    String message = RSA.readFile("message.txt");
	    System.out.println("Message in plaintext: " + message);

	    BigInteger plaintext = rsa.fromStringToBigInteger(message);
	    System.out.println("Message in big integer: " + plaintext);

	    BigInteger cipher = rsa.encrypt(plaintext);
	    String cipherStr = rsa.fromBigIntegerToString(cipher);
	    System.out.println("Encrypted Cipher in plaintext: " + cipherStr);
	    System.out.println("Encrypted Cipher in big integer: " + cipher);

	    BigInteger decrypted = rsa.decrypt(cipher);
	    String decryptedMessage = rsa.fromBigIntegerToString(decrypted);
	    System.out.println("Decrypted Message in plaintext: " + decryptedMessage);
	    System.out.println("Decrypted Message in big integer: " + decrypted);

	    // Write outputs to files
	    String content = "Encrypted Cipher in plaintext: " + cipherStr + "\n"
	               	   + "Encrypted Cipher in big integer: " + cipher + "\n";
	    RSA.writeToFile("encryptedRSA.txt", content);
	    String decryptedContent = "Decrypted Message in plaintext: " + decryptedMessage + "\n"
                				+ "Decrypted Message in big integer: " + decrypted + "\n";
	    RSA.writeToFile("decryptedRSA.txt", decryptedContent);
	    sc.close();
	}
}

