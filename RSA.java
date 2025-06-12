import java.math.BigInteger;
import java.security.SecureRandom;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RSA {
    private BigInteger p, q, n, phi, e, d;
    private int bitlength;
    private SecureRandom r;

    public RSA() {
        r = new SecureRandom();
    }
    
    public BigInteger getPublicKey() {
        return e;
    }

    public BigInteger getPrivateKey() {
        return d;
    }
    public BigInteger getP(){
    	return p;
    }
    
    public BigInteger getQ(){
    	return q;
    }
    
    public BigInteger getN(){
    	return n;
    }
    
    public BigInteger getPhi(){
    	return phi;
    }

    public void initialize(int bits) {
        bitlength = bits;
        p = BigInteger.probablePrime(bitlength / 2, r);
        q = BigInteger.probablePrime(bitlength / 2, r);
        n = p.multiply(q);
        phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, r);

        while (phi.gcd(e).compareTo(BigInteger.ONE) != 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger cipher) {
        return cipher.modPow(d, n);
    }

    public BigInteger fromStringToBigInteger(String message) {
        return new BigInteger(message.getBytes());
    }

    public String fromBigIntegerToString(BigInteger bigInt) {
        return new String(bigInt.toByteArray());
    }
    
    public static String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public static void writeToFile(String filename, String content) {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.print(content); // This will overwrite the file
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}
