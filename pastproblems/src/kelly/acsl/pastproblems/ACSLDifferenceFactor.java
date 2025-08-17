package kelly.acsl.pastproblems;

import java.util.*;

public class ACSLDifferenceFactor {

    private Stack <String> stack = new Stack <String> ();
    public static void main(String[] args)
    {
        ACSLDifferenceFactor adf = new ACSLDifferenceFactor();
        System.out.println(adf.findADF("I will go home now", "I am going home now"));

        System.out.println(adf.findADF("The big black bear bit a big black bug", "The big black bug bled black blood"));

        System.out.println(adf.findADF("Complementary angle measures sum to 90 degrees.", "The measures of supplementary angles add to 180 degrees."));

        System.out.println(adf.findADF("A Tale of Two Cities was published by Dickens in 1859.", "In 1839, Charles Dickens published Nicholas Nickleby."));

        System.out.println(adf.findADF("Connecticut is The Constitution State.", "Hartford is the capital of Connecticut."));

        // adf.removeAndStore("I am going home now", "I will go home soon", "home");
        // System.out.println(adf.findLongestString("I am going home now", "I will go home soon"));
    }

    public String findLongestString(String str1, String str2)
    {
        String comStr = findCommon(str1, str2);
        String comStr2 = findCommon(str2, str1);

        if(comStr.length() > comStr2.length())
        {
            return comStr;
        }
        else if(comStr.length() == comStr2.length())
        {
            if(comStr.compareTo(comStr2) < 0)
            {
                return comStr;
            }
            else
            {
                return comStr2;
            }
        }
        else
        {
            return comStr2;
        }
    }

    private String findCommon(String str1, String str2)
    {
        String nwStr1 = str1.toUpperCase();
        String nwStr2 = str2.toUpperCase();
        String comStr = "";
        for(int k = 0; k < nwStr1.length(); k++)
        {
            String tempStr = nwStr1.substring(k);
            String compareStr = "";
            for(int i = 0; i < Math.min(tempStr.length(), nwStr2.length()); i++)
            {
                char ch = tempStr.charAt(i);
                if('A' <= ch && ch <= 'Z' && ch == nwStr2.charAt(i))
                {
                    compareStr += tempStr.substring(i, i+1);
                }
                else
                {
                    if(compareStr.length() > comStr.length())
                    {
                        comStr = compareStr;
                    }
                    compareStr = "";
                }
            }
            if(compareStr.length() > 0)
            {
                comStr = compareStr;
            }
        }

        return comStr;
    }

    public void removeAndStore(String str1, String str2, String comStr)
    {
        int indOfFirst = str1.indexOf(comStr);
        int indOfSecond = str2.indexOf(comStr);

        String nwStr1 = str1.substring(0, indOfFirst);
        String nwStr2 = str2.substring(0, indOfSecond);

        stack.push(nwStr1);
        stack.push(nwStr2);

        nwStr1 = str1.substring((indOfFirst + comStr.length()));
        nwStr2 = str2.substring((indOfSecond + comStr.length()));

        stack.push(nwStr1);
        stack.push(nwStr2);
    }

    public int findADF(String str1, String str2)
    {
        String upStr1 = str1.toUpperCase();
        String upStr2 = str2.toUpperCase();

        stack.push(upStr1);
        stack.push(upStr2);
        int totalADF = 0;

        while(!stack.isEmpty())
        {
            String firstStr = stack.pop();
            String secondStr = stack.pop();

            String longestRet = findLongestString(firstStr, secondStr);
            totalADF += longestRet.length();

            if(longestRet.length() > 0)
            {
                removeAndStore(firstStr, secondStr, longestRet);
            }
        }

        return totalADF;
    }
}
