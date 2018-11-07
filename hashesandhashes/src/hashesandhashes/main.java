package hashesandhashes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;





public class main {
	static String nonce = "";
	static String createdTimestamp = "";
	static String password = "";
	
	public static void out(String s) {
		System.out.println(s);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		out(makeNonce());
		Random rng = new Random();
		nonce = Long.toHexString(rng.nextLong());
		
//		out(r + " " + r.length());
		
		if (args.length == 3) {
			createdTimestamp = args[0];
			password = args[1];
			String filename = args[2];
			
//			out(filename);
			
//			getFileLines(filename);
//			String nonce = 
			if (createdTimestamp.length() > 0 && password.length() > 0) {
				ArrayList<String> list = new ArrayList<String>();
				String pwd = encode(nonce+createdTimestamp+encode(password));
//				out(pwd);
				list.add("timestamp\t"+createdTimestamp);
				list.add("outputPath\t"+filename);
				list.add("base64Nonce\t"+toBase64(nonce));
				list.add("nonce\t"+nonce);
				list.add("digest\t"+pwd);
				writeToFile(filename, list);
				
				
				
			}
		}
		

	}
	
//	public static String makeNonce() {
//		 String dateTimeString = Long.toString(new Date().getTime());
//	        byte[] nonceByte = dateTimeString.getBytes();
//	        return toBase64(nonceByte);
//	}
	
	
	public static void writeToFile(String filename, List<String> lines) {
		Path file = Paths.get(filename);
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public static String toBase64(String s ) {
			return toBase64(s.getBytes());
	}
	
	public static String toBase64(byte[] bytes) {
		Base64.Encoder b64 = Base64.getEncoder();
		return b64.encodeToString(bytes);
		
	}
	
	public static byte[] fromBase64(String s) {
		Base64.Decoder b64 = Base64.getDecoder();
		return b64.decode(s);
	}

	public static String encode(String s) {
		String base64String="";
//		Base64.Encoder b64 = Base64.getEncoder();
		MessageDigest sha;
		try {
			sha = MessageDigest.getInstance("SHA-1");
			sha.reset();
			sha.update(s.getBytes());
			base64String = toBase64(sha.digest());
//			System.out.println(p + " " +p.length());
//			System.out.println(sha.digest().toString());
//			for (int i = 0; i < sha.digest().length; i += 1) {
//				System.out.println(sha.digest()[i]);
//			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return base64String;
		
	}
	
	


}
