package hw3;
import java.util.ArrayList;
import java.util.List;


public class Des 
{

    private String key = "nonesense";
    private List<String> keys = new ArrayList<String>() ;

    public Des()
    {
        key = PC1Key(toBinary(key,8));
        String k = key;
        keys = new ArrayList<String>();
        for (int round=1; round<=3 ; round++){
            k = Transform(k,round);
            keys.add(k);
        }
    }

    public String toBinary(String plaintext,int bits)
    {
        String bin = "";
        for(char txt : plaintext.toCharArray()){
            String con = Integer.toBinaryString((int)txt);
            int len = bits-con.length();
            while (len>0){
                con = "0"+con;
                len--;
            }
            bin += con;
        }

        return bin;
    }

    private String Permutation(String rightValue )
    {
        int[] P = new int[]{
                16,7 ,20,21,29,12,28,17,
                1 ,15,23,26,5 ,18,31,10,
                2 ,8 ,24,14,32,27,3 ,9 ,
                19,13,30,6 ,22,11,4 ,25};
        String tmp = "";
        for(int index : P)
            tmp += rightValue.charAt(index-1);

        return tmp;

    }

    private String PC1Key(String k)
    {
        int[] PC = new int[]{
                57,49,41,33,25,17,9 ,1 ,
                58,50,42,34,26,18,10,2 ,
                59,51,43,35,27,19,11,3 ,
                60,52,44,36,63,55,47,39,
                31,23,15,7 ,62,54,46,38,
                30,22,14,6 ,61,53,45,37,
                29,21,13,5 ,28,20,12,4};
        String tmp = "";
        for(int index : PC)
            tmp += k.charAt(index-1);

        return tmp;
    }

    private String PC2Key(String k)
    {
        int[] PC = new int[]{
                14,17,11,24,1 ,5 ,3 ,28,
                15,6 ,21,10,23,19,12,4 ,
                26,8 ,16,7 ,27,20,13,2 ,
                41,52,31,37,47,55,30,40,
                51,45,33,48,44,49,39,56,
                34,53,46,42,50,36,29,32};
        String tmp = "";
        for (int index : PC)
            tmp += k.charAt(index -1);

        return tmp;
    }

    
    private String InitPerm(String bintxt)
    {
        int[] IP = new int[]
        		{
                58,50,42,34,26,18,10,2,
                60,52,44,36,28,20,12,4,
                62,54,46,38,30,22,14,6,
                64,56,48,40,32,24,16,8,
                57,49,41,33,25,17,9 ,1,
                59,51,43,35,27,19,11,3,
                61,53,45,37,29,21,13,5,
                63,55,47,39,31,23,15,7};
        String tmp = "";
        for ( int i : IP)
            tmp += bintxt.charAt(i-1);

        return tmp;
    }


    private String InversePerm(String binarytext)
    {
        int [] IP = new int[]{
                40,8,48,16,56,24,64,32,
                39,7,47,15,55,23,63,31,
                38,6,46,14,54,22,62,30,
                37,5,45,13,53,21,61,29,
                36,4,44,12,52,20,60,28,
                35,3,43,11,51,19,59,27,
                34,2,42,10,50,18,58,26,
                33,1, 41,9,49,17,57,25};
        String tmp = "";
        for (int i : IP)
            tmp += binarytext.charAt(i-1);

        return tmp;
    }


    private String ExpandPerm(String rightValue)
    {
        int[] E = new int[]{
                32,1 ,2 , 3, 4, 5,
                4 ,5 , 6, 7, 8, 9,
                8 , 9,10,11,12,13,
                12,13,14,15,16,17,
                16,17,18,19,20,21,
                20,21,22,23,24,25,
                24,25,26,27,28,29,
                28,29,30,31,32, 1} ;

        String tmp = "";
        for (int i : E)
            tmp += rightValue.charAt(i-1);

        return tmp;
    }

    private String SBox(String rightValue,int i)
    {
        String row  = "";
        row += String.valueOf(rightValue.charAt(0));
        row += String.valueOf(rightValue.charAt(5));
        String column = rightValue.substring(1,5);
        int indx = (Integer.parseInt(row,2))*16+(Integer.parseInt(column,2));
        int[] S1 = new int[]{
                14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7,
                0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8,
                4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0,
                15,12,8, 2,4,9,1,7,5,11,3,14,10,0,6,13};

        int[] S2 = new int[]{
                15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10,
                3, 13,4,7,15,2,8,14,12,0,1,10,6,9,11,5,
                0,14,7, 11,10,4,13,1,5,8,12,6,9,3,2,15,
                13,8,10,1,3, 15,4,2,11,6,7,12,0,5,14,9};
        int[] S3 = new int[]{
                10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8,
                13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1,
                13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7,
                1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12};
        int[] S4 = new int[]{
                7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15,
                13,8,11,5,6, 15,0,3,4,7,2,12,1,10,14,9,
                10,6,9,0,12,11,7,13,15,1, 3,14,5,2,8,4,
                3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14};
        int[] S5 = new int[]{
                2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9,
                14,11,2,12,4, 7,13,1,5,0,15,10,3,9,8,6,
                4,2,1,11,10,13,7,8,15,9,12, 5,6,3,0,14,
                11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3};
        int[] S6 = new int[]{
                12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11,
                10,15,4,2,7,12, 9,5,6,1,13,14,0,11,3,8,
                9,14,15,5,2,8,12,3,7,0,4,10,1, 13,11,6,
                4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13};
        int[] S7 = new int[]{
                4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1,
                13,0,11,7,4,9,1, 10,14,3,5,12,2,15,8,6,
                1,4,11,13,12,3,7,14,10,15,6,8,0,5, 9,2,
                6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12};
        int[] S8 = new int[]{
                13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7,
                1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2,
                7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8,
                2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11};
        int val =0;
        if(i==1) val = S1[indx];
        if(i==2) val = S2[indx];
        if(i==3) val = S3[indx];
        if(i==4) val = S4[indx];
        if(i==5) val = S5[indx];
        if(i==6) val = S6[indx];
        if(i==7) val = S7[indx];
        if(i==8) val = S8[indx];

        return toBinary(String.valueOf(val),4);
    }

    
    private String LeftRotation(String input,int d)
    {
        return input.substring(d)+input.substring(0,d);
    }

    private String Transform(String k,int round)
    {
        String leftValue = k.substring(0,k.length()/2);
        String rightValue = k.substring(k.length()/2);
        int[] lst = new int[]{1,2,9,16};
        int d = 2;
        for (int i : lst)
        {
            if(i == round)
                d = 1;
        }
        leftValue = LeftRotation(leftValue,d);
        rightValue = LeftRotation(rightValue,d);

        return leftValue+rightValue;
    }

    
    public String Xor(String str1,String str2)
    {
        StringBuffer str_buf = new StringBuffer();
        for (int i = 0; i < str1.length(); i++)
        	str_buf.append(str1.charAt(i)^str2.charAt(i));
        
        return str_buf.toString();
    }

    private String Fval(String rightValue, String k)
    {
    	rightValue = ExpandPerm(rightValue);
        String res = Xor(rightValue,k);
        String sboxOutput = "";
        for (int i=1 ; i<=8 ; i++){
        	sboxOutput += SBox(res.substring(0,6),i);
            res = res.substring(6);
        }

        return Permutation(sboxOutput);
    }

    public String Init(String plaintext)
    {
        String lrVal = InitPerm(plaintext);
        String leftValue = lrVal.substring(0,lrVal.length()/2);
        String rightValue = lrVal.substring(lrVal.length()/2);
        for (int round = 1 ; round <= 3 ; round++)
        {
            String k = PC2Key(keys.get(round - 1));
            String tmp = leftValue;
            leftValue = rightValue;
            rightValue = Xor(tmp, Fval(rightValue, k));
        }

        return InversePerm(rightValue+leftValue);
    }

    private void KeyInit()
    {
    	key = PC1Key(toBinary(key,8));
    	String k = key;
    	keys = new ArrayList<String>();
    	for (int round=1; round<=3 ; round++)
    	{
    		k = Transform(k,round);
    		keys.add(k);
    	}
    }
    
    public String encryption(String plaintext)
    {
        ArrayList<String> block_arr = new ArrayList<String>();
        while (plaintext.length() > 8) {
            block_arr.add(toBinary(plaintext.substring(0, 8), 8));
            plaintext = plaintext.substring(8);
        }
        String check = toBinary(plaintext, 8);
        int len = check.length();
        for (int i = 0; i < 64 - len; i++) {
            check = "0" + check;
        }

        block_arr.add(check);
        String ciphertext = "";
        for (String b : block_arr)
            ciphertext += Init(b);

        return ciphertext;
    }

    public String binaryToHex(String bin)
    {
        String hex = "";
        String conv;
        int dec;
        while(bin.length()>0){
            conv = bin.substring(0,8);
            bin = bin.substring(8);
            dec = Integer.parseInt(conv,2);
            hex += Integer.toString(dec,16);
        }
        return hex;
    }

    public String Final(String ciphertext )
    {
        String lrVal = InitPerm(ciphertext);
        String leftValue = lrVal.substring(0, lrVal.length() / 2);
        String rightValue = lrVal.substring(lrVal.length() / 2);
        for (int round = 3; round > 0; round--) {
            String k = PC2Key(keys.get(round - 1));
            String tmp = leftValue;
            leftValue = rightValue;
            rightValue = Xor(tmp, Fval(rightValue, k));
        }
        
        return InversePerm(rightValue + leftValue);
    }


    public String decryption(String ciphertext)
    {
        if(keys.size()==0) 
        	KeyInit();
        
        ArrayList<String> block_arr = new ArrayList<String>();
        String plaintext = "";
        while(ciphertext.length()>64)
        {
            block_arr.add(ciphertext.substring(0, 64));
            ciphertext = ciphertext.substring(64);
        }

        block_arr.add(ciphertext);
        for (String b : block_arr)
            plaintext += binaryToStr(Final(b));
        return plaintext;
    }

    public String binaryToStr(String bin)
    {
    	String z = "00000000";
        String plaintext = "";
        char next;
        for (int i = 1; i <= 8; i++) {
            String get = bin.substring(0, 8);
            if (!get.equals(z))
            {
                next = (char) Integer.parseInt(get, 2);
                plaintext += next;

            }
            bin = bin.substring(8);
        }
        return plaintext;
    }


}


