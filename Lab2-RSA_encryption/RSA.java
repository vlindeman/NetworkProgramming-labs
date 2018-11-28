
import java.math.BigInteger;
import java.util.Random; 

public class RSA {

    public static void main(String[] args) {

	//Length of random primes p and q
	int bit_length = 1024; 

	//Randomly generate p and q with 1024 bits each, with 100% certainty of prime 
	Random random = new Random(); 
	BigInteger p = new BigInteger(bit_length, 100, random);
	BigInteger q = new BigInteger(bit_length, 100, random);

	//n=p*q
	BigInteger n = p.multiply(q);
	//(p-1)(q-1)
	BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

	//Generate random e until 1<e<phi and e and phi are coprime
	BigInteger e;
	while(true){
	    e = new BigInteger(phi.bitLength(), random); 
	    if (e.compareTo(BigInteger.ONE) >= 0 && e.compareTo(phi) <= 0 && e.gcd(phi).equals(BigInteger.ONE)){
		break; 
	    }
	}

	//d = e^-1*mod(phi)
	BigInteger d = e.modInverse(phi);
	
	//Random message
	String message = "Hello world"; 
	System.out.println("Text message: \t \t"+message); 

	//Convert to BigInteger
	BigInteger msg = new BigInteger(message.getBytes());
	System.out.println("Message in bytes:\t"+msg); 

	//Encrypted message=msg^e*mod(n)
	BigInteger enc = msg.modPow(e, n);
	System.out.println("Encrypted bytes \t"+ enc); 

	//Decrypted message=encrypted message^d*mod(n)
	BigInteger dec = enc.modPow(d, n); 
	System.out.println("Decrypted bytes:\t"+dec); 
	
	//Convert back to string
	String s = new String(dec.toByteArray());
	System.out.println("Recieved message:\t"+s); 
	
    }

}
