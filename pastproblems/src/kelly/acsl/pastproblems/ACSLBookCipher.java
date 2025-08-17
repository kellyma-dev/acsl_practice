package kelly.acsl.pastproblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class ACSLBookCipher {

    private Map <Character, ArrayList<String>> map = new HashMap<>();
    public static void main(String[] args)
    {
        ACSLBookCipher bc = new ACSLBookCipher();
        String str = "ACSL, or the American Computer Science League, is an " +
                "international computer science competition among more than 300 " +
                "schools.  Originally founded in 1978 as the Rhode Island" +
                " Computer Science League, it then became the New England Computer " +
                "Science League.";
        bc.buildMap(str);
        System.out.println(bc.encoding("American Computer Science League (ACSL) is fun!"));
        System.out.println();

        String str1 = "To be or not to be, that is the question - a quote by " +
                "William Shakespeare.  2B or not 2B - a hexadecimal " +
                "equivalent!  How would you write it?";
        bc.buildMap(str1);
        System.out.println(bc.encoding("Boolean is always True!"));
        System.out.println();

        String str2 = "Various programming languages are:  Java, Python, Visual " +
                "BASIC, C++, Lisp, C#, FORTRAN, R, SQL.  Javascript is the " +
                "language of the Internet!  HTML stands for Hypertext Markup " +
                "Language and is not really a coding language!  There are " +
                "over 300 languages.  Which one do you like best?";
        bc.buildMap(str2);
        System.out.println(bc.encoding("Java is the language of AP CS! Where is Lisp used?"));
        System.out.println();

        String str3 = "Four score and seven years ago our fathers brought forth on " +
                "this continent, a new nation, conceived in Liberty, and " +
                "dedicated to the proposition that all men are created " +
                "equal.  Now we are engaged in a great civil war, testing " +
                "whether that nation, or any nation so conceived and so " +
                "dedicated, can long endure.  We are met on a great " +
                "battle-field of that war.  We have come to dedicate a " +
                "portion of that field, as a final resting place for those " +
                "who here gave their lives that that nation might live.  It " +
                "is altogether fitting and proper that we should do this.  " +
                "This was written by Abraham Lincoln on 11/19/1863!";
        bc.buildMap(str3);
        System.out.println(bc.encoding("The #1 speech of all time was less than 8 minutes long!"));
        System.out.println();

        String str4 = "There are 10 kinds of people in the world: those who know " +
                "binary and those who don't!  Make sure you learn binary.  " +
                "Computers all use it.";
        bc.buildMap(str4);
        System.out.println(bc.encoding("Could you be the 0 kind or the 1 kind?"));
    }

    public void buildMap(String str)
    {
        map.clear();
        int s = 1;
        int w = 1;
        int c = 1;
        for(int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            if(ch == '.' || ch == '?' || ch == '!')
            {
                s++;
                w = 1;
                c= 1;
                i+=2;
                continue;
            }
            else if(ch == ' ')
            {
                w++;
                c = 1;
                continue;
            }
            String location = s + "." + w + "." + c;
            if(map.containsKey(ch))
            {
                ArrayList<String> list = map.get(ch);
                list.add(location);
            }
            else
            {
                ArrayList<String> list = new ArrayList<>();
                list.add(location);
                map.put(ch, list);
            }
            c++;
        }
    }

    public String encoding(String message)
    {
        String str = "";
        int spaces = 0;
        for(int i = 0; i < message.length(); i++)
        {

            char ch = message.charAt(i);
            if(!('A' <= ch && ch <= 'Z') && !('0' <= ch && ch <= '9') && !('a' <= ch && ch <= 'z') && !(ch == ' '))
            {
                str += ch;
                spaces++;
            }
            else if( ch == ' ')
            {
                str += "_ ";
                spaces++;
            }
            else
            {
                ArrayList<String> list = map.get(ch);
                int ind = i - spaces + 1;
                while(ind > list.size())
                {
                    ind /= 2;
                }
                str += list.get(ind-1);
                str += " ";
            }
        }
        return str;
    }
}
