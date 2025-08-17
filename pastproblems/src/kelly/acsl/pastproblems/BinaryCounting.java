package kelly.acsl.pastproblems;

import java.util.*;
import java.io.*;
public class BinaryCounting {
    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(new File("C:\\Users\\kelly\\APComputerScience\\ACSL2020-2021Project\\src\\file.dat"));
        BinaryCounting bc = new BinaryCounting();
        while(input.hasNext())
        {
            String inStr = input.nextLine();
            String str = bc.convertToBinaryString(inStr);
            //System.out.println(bc.convertToBinaryString(inStr));
            int rm = 0;
            while(true)
            {
                int len = str.length();
                String rem = Integer.toBinaryString(rm);
                String newStr = bc.removeFromBeginning(str, rem);
                newStr = bc.removeFromEnding(newStr, rem);
                if(newStr.length() == len)
                {
                    break;
                }
                else
                {
                    str = newStr;
                    rm++;
                }
            }
            //System.out.println(str);

            String octalStr = bc.convertToOcta(str);
            //System.out.println(octalStr);
            int rm1 = 0;
            while(true)
            {
                int len1 = octalStr.length();
                String rem1 = Integer.toOctalString(rm1);
                String newSub = bc.removeFromBeginning(octalStr, rem1);
                newSub = bc.removeFromEnding(newSub, rem1);
                if(newSub.length() == len1)
                {
                    System.out.println(rm1 - 1);
                    break;
                }
                else
                {
                    octalStr = newSub;
                    rm1++;
                }
            }
            //System.out.println(octalStr);
            //603022200104
        }

    }
    public String convertToBinaryString(String str)
    {
        String output = "";
        for(int i = 0; i < str.length(); i++)
        {
            String partial = "";
            char sub = str.charAt(i);
            partial = Integer.toBinaryString(sub);
            if(partial.length() < 8)
            {
                while(partial.length()<8)
                {
                    partial = "0" + partial;
                }
            }

            output += partial;
        }
        return output;
    }

//    public String charToBinaryString(char ch)
//    {
//        return "";
//    }

    public String removeFromBeginning(String str, String rm)
    {
        int startID = str.indexOf(rm);
        if(startID == -1)
        {
            return str;
        }
        else if(startID == 0)
        {
            return str.substring(rm.length());
        }
        String subst1 = str.substring(0, startID);
        String subst3 = str.substring(startID + rm.length());

        return subst1 + subst3;
    }

    public String removeFromEnding(String str, String rm)
    {
        int startID = str.lastIndexOf(rm);
        if(startID == -1)
        {
            return str;
        }
        else if(startID == 0)
        {
            return str.substring(rm.length());
        }
        String subst1 = str.substring(0, startID);
        String subst3 = str.substring(startID + rm.length());

        return subst1 + subst3;
    }

    public String convertToOcta(String str)
    {
        String output = "";
        String sub = "";
        int firstInd = str.indexOf("1");
        sub = str.substring(firstInd);
        //Long binStr = Long.parseLong(sub, 2);
        //output = Long.toOctalString(binStr);
        for(int i = sub.length() - 3; i >= 0; i-=3)
        {
            if(i + 3 > 0)
            {
                output = binaryToOctaString(sub.substring(i, i+3)) + output;
            }
            else
            {
                output = binaryToOctaString(sub.substring(i)) + output;
            }
        }
        if(sub.length() % 3 != 0)
        {
            output = binaryToOctaString(sub.substring(0, sub.length() % 3)) + output;
        }
        return output;
    }

    public String binaryToOctaString(String str)
    {
        String output = "";
        int bin = Integer.parseInt(str, 2);
        output = Integer.toOctalString(bin);
        return output;
    }
}