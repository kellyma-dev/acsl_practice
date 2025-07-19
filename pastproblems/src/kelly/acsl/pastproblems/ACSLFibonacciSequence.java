package kelly.acsl.pastproblems;

import java.util.*;

public class ACSLFibonacciSequence {

    private ArrayList<Long> fibSeq = new ArrayList<>();
    public static void main(String[] args)
    {
        ACSLFibonacciSequence fs = new ACSLFibonacciSequence();
        String str = "Python Programming is easier than programming in Java.";

        String str2 = "Fibonacci Numbers are found in many places including " +
                "the Mandelbrot Set.";

        System.out.println(fs.encode(str, 's', 0, 1));

        System.out.println(fs.encode(str2, 'a', 7, 10));

        String deco = "379 479 341 447 448 329 381 397 402 402 395 462 404 383 " +
                "425 434 446 383 469 468 405 464 408 449 433 329 390 425 429 395 " +
                "446 420 449 368 417 397 363 363 395 429 443 383 464 395 446 344 " +
                "408 458 445 431 335 367 402 394 475 419 391";

        System.out.println(fs.decoding(deco, 'z', 6, 1));
    }

    public void fibonacciSeq(long input1, long input2)
    {
        fibSeq.clear();

        fibSeq.add(input1);
        fibSeq.add(input2);

        int index = 0;
        while(fibSeq.size() <= 200)
        {
            long sum = fibSeq.get(index) + fibSeq.get(index + 1);
            fibSeq.add(sum);
            index++;
        }
    }
    private long finalShift(char key, long offset)
    {
        long n = offset % 26;
        long secondN;
        if(n < 0)
        {
            secondN = 26 + n;
        }
        else if( n > 0)
        {
            secondN = n - 26;
        }
        else
        {
            return (long)0;
        }

        if(('a' <= (key + n) && 'z' >= (key + n)) || ('A' <= (key + n) && 'Z' >= (key + n)))
        {
            return n;
        }
        else
        {
            return secondN;
        }
    }
    public String encoding(char input, char key, long offset)
    {
        //if index is even do +
        // if index is odd do -
        long ret = 0;
        ret = key + finalShift(key, offset);

        return input + 3 * ret + "";
    }

    public String encode(String message, char key, long input1, long input2)
    {
        fibonacciSeq(input1, input2);
        long place;
        String ret = "";
        for(int i = 0; i < message.length(); i++) {
            if (i % 2 == 0)
            {
                place  = fibSeq.get(i);
                ret += encoding(message.charAt(i), key, place) + " ";
            }
            else
            {
                place = -fibSeq.get(i);
                ret += encoding(message.charAt(i), key, place) + " ";
            }
        }

        return ret;
    }

    public String decoding(String input, char key, long input1, long input2)
    {
        fibonacciSeq(input1, input2);
        String[] parts = input.split(" ");
        long place;
        String strRet = "";
        for(int i = 0; i < parts.length; i++)
        {
            int ret = Integer.parseInt(parts[i]);
            if(i % 2 == 0)
            {
                place = fibSeq.get(i);
            }
            else
            {
                place = -fibSeq.get(i);
            }

            long ret2 = key + finalShift(key, place);

            ret -= (3 * ret2);
            strRet += (char)ret + "";
        }

        return strRet;
    }
}
