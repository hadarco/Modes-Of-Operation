package hw3;

import java.util.ArrayList;
import java.util.Scanner;


public class ModesOfOperations
{	
	   private String IV = "mySecret";
	   private Des des = new Des();

	    public ArrayList<String> divideBlocksPad(String plaintext)
	    {
	    	ArrayList<String> block_arr = new ArrayList<String>();
	        
	        while (plaintext.length() > 8) 
	        {
	            block_arr.add(des.toBinary(plaintext.substring(0, 8), 8));
	            plaintext = plaintext.substring(8);
	        }
	        String rest = des.toBinary(plaintext, 8);
	        int len = rest.length();
	        for (int i = 0; i < 64 - len; i++) 
	        {
	            rest = "0" + rest;
	        }

	        block_arr.add(rest);
	        
	        return block_arr;
	    }
	    
	    public ArrayList<String> divideBlocks(String ciphertext)
	    {
	        ArrayList<String> block_arr = new ArrayList<String>();
	        while(ciphertext.length()>64){
	            block_arr.add(ciphertext.substring(0,64));
	            ciphertext = ciphertext.substring(64);
	        }

	        block_arr.add(ciphertext);
	        
	        return block_arr;
	    }
	   
	   public String encryptECB(String plaintext)
	   {
	    	ArrayList<String> block_arr = divideBlocksPad(plaintext);
	        String ciphertext = "";
	        for (String block : block_arr)
	            ciphertext += des.Init(block);

	        return ciphertext;
	    }

	    public String decryptECB(String ciphertext)
	    {
	    	ArrayList<String> block_arr = divideBlocks(ciphertext);
	        String plaintext = "";
	        for (String block : block_arr)
	        {
	            plaintext += des.binaryToStr(des.Final(block));
	        }
	        return plaintext;
	    }

	    public String encryptCBC(String plaintext)
	    {
	    	ArrayList<String> block_arr = divideBlocksPad(plaintext);	        
	        String ciphertext = des.toBinary(IV,8); 
	        String tmp = ciphertext; 

	        for (String b : block_arr)
	        {
	            tmp = des.Init(des.Xor(b, tmp));
	            ciphertext += tmp;
	        }

	        return ciphertext;
	    }

	    public String decryptCBC(String ciphertext)
	    {
	    	ArrayList<String> block_arr = divideBlocks(ciphertext);
	        String plaintext = "";
	        String tmp= des.toBinary(IV,8);
	        int count = 0;
	        for (String b : block_arr)
	        {
	                if(count!=0)
	                    plaintext += des.binaryToStr(des.Xor(tmp, des.Final(b)));

	                tmp = b;
	                count++;
	        }
	        return plaintext;

	    }
	    
	    public String encryptCFB(String plaintext)
	    {
	    	ArrayList<String> block_arr = divideBlocksPad(plaintext);
	        String tmp, ciphertext;

	        ciphertext = "";
	        tmp = des.Init(des.toBinary(IV,8));

	        for (String b : block_arr)
	        {
	            tmp = des.Xor(b, tmp);
	            ciphertext += tmp;
	            tmp = des.Init(tmp);
	        }

	        return ciphertext;
	    }

	       
	    public String decryptCFB(String ciphertext)
	    {
	    	ArrayList<String> block_arr = divideBlocks(ciphertext);
	        String plaintext = "";

	        String tmp= des.toBinary(IV,8);
	        for (String b : block_arr)
	        {
	            tmp = des.Xor(b, des.Init(tmp));
	            plaintext += des.binaryToStr(tmp);
	            tmp = b;
	        }
	        return plaintext;

	    }
	   
	    public String encryptOFB(String plaintext)
	    {
	    	ArrayList<String> block_arr = divideBlocksPad(plaintext);
	        String ciphertext = "";
	        String temp = des.toBinary(IV,8);

	        for (String block : block_arr) 
	        {
	            temp = des.Init(temp);
	            ciphertext += des.Xor(block,temp);
	        }

	        return ciphertext;
	    }

	    public String decryptOFB(String ciphertext)
	    {
	    	ArrayList<String> block_arr = divideBlocks(ciphertext);
	        String plaintext = "";
	        String zi = des.toBinary(IV,8);
	        for (String block : block_arr) {
	            zi = des.Init(zi);
	            plaintext += des.binaryToStr(des.Xor(block,zi));
	        }
	        return plaintext;
	    }

	    
	    public static void main(String[] args)
	    {
	        ModesOfOperations moo = new ModesOfOperations();
	        String text = "The Concise Oxford English Dictionary defines cryptography as \"the art of writing or solving codes.\" This\n"+
	                "is historically accurate, but does not capture the current breadth of the field or its present-day scientific\n"+
	                "undations. The definition focuses solely on the codes that have been used for centuries to en-able\n"+
	                "secret communication. But cryptography nowadays encompasses much more than this: it deals with\n"+
	                "mechanisms for ensuring integrity, techniques for exchanging secret keys, protocols for authenticating\n"+
	                "users, electronic auctions and elections, digital cash, and more. Without attempting to provide a\n"+
	                "complete characterization, we would say that modern cryptography involves the study of mathematical\n"+
	                "techniques for securing digital information, systems, and distributed computations against adversarial\n"+
	                "attacks. The dictionary definition also refers to cryptography as an art. Until late in the 20th century\n"+
	                "cryptography was, indeed, largely an art. Constructing good codes, or breaking existing ones, relied on\n"+
	                "creativity and a developed sense of how codes work. There was little theory to rely on and, for a long\n"+
	                "time, no working definition of what constitutes a good code. Beginning in the 1970s and 1980s, this\n"+
	                "picture of cryptography radically changed. A rich theory began to emerge, enabling the rigorous study of\n"+
	                "cryptography as a science and a mathematical discipline. This perspective has, in turn, influenced how\n"+
	                "researchers think about the broader field of computer security. Another very important difference\n"+
	                "between classical cryptography (say, before the 1980s) and modern cryptography relates to its\n"+
	                "adoption. Historically, the major consumers of cryptography were military organizations and\n"+
	                "governments. Today, cryptography is everywhere! If you have ever authenticated yourself by typing a\n"+
	                "password, purchased something by credit card over the Internet, or downloaded a verified update for\n"+
	                "your operating system, you have undoubtedly used cryptography. And, more and more, programmers\n"+
	                "with relatively little experience are being asked to \"secure\" the applications they write by incorporating\n"+
	                "cryptographic mechanisms\n\n"+
	                "In short, cryptography has gone from a heuristic set of tools concerned with ensuring secret\n"+
	                "communication for the military to a science that helps secure systems for ordinary people all across the\n"+
	                "globe. This also means that cryptography has become a more central topic within computer science.";
	        int tmp_ans;
	        do 
	        {
	        
        	System.out.println("\n\nfor ECB enter 1 \nfor CBC enter 2\nfor CFB enter 3\nfor OFB enter 4 \nTo Exit enter 5\n");
        	@SuppressWarnings("resource")
        	int ans = new Scanner(System.in).nextInt();
        	tmp_ans = ans;
	        String cipherText;	        
	        switch (ans)
	        {
	          case 1:
	          {
		        System.out.println("ECB");
			    cipherText = moo.encryptECB(text);
			    System.out.println(cipherText+"\n");
			    System.out.println(moo.decryptECB(cipherText));	
			    break;
	          }
	          case 2:
	          {
	            System.out.println("CBC");
	            cipherText = moo.encryptCBC(text);
		        System.out.println(cipherText+"\n");
		        System.out.println(moo.decryptCBC(cipherText));
	            break;
			  }
	          case 3:
	          { 
		        System.out.println("CFB");
			    cipherText = moo.encryptCFB(text);
			    System.out.println(cipherText+"\n");
			    System.out.println(moo.decryptCFB(cipherText));	
			    break;
			    
	          }
	          case 4:
	          {
		        System.out.println("OFB");
			    cipherText = moo.encryptOFB(text);
			    System.out.println(cipherText+"\n");
			    System.out.println(moo.decryptOFB(cipherText));
			    break;
	          }
	        }
	        } while(tmp_ans!=5);
	    
	        
	    }
	    
	    
}
