import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.text.DecimalFormat;
public class MathFair
{
    public static void main(String[] args) throws InterruptedException{
        long start = System.nanoTime();
        System.out.println(babylonianMethod(35));
        long end = System.nanoTime();
        System.out.println(end-start);
        long start2 = System.nanoTime();
        System.out.println(longDivision(35));
        long end2 = System.nanoTime();
        System.out.println(end2-start2);
    }
    public static double babylonianMethod(int number){
        DecimalFormat g = new DecimalFormat("##.00000");
        double guess = new Double(g.format(number/2.0));
        double previous = new Double(g.format(number));
        while(guess != previous){
            double temp = guess;
            guess = Double.parseDouble(g.format((temp+number/temp)/2));
            previous = temp;
        }
        return previous;
    }
    public static double longDivision(int number){
        ArrayList<Integer> nums  = groupTwos(number);
        String a = "";
        int inside = 0;
        String i = "";
        int outside = 0;
        String prevOutside = "";
        int numToSubtract;
        int remainder = 0;
        String q = "";
        outside = closestSquare(nums.get(0));
        prevOutside += outside;
        inside = nums.get(0);
        numToSubtract = outside*outside;
        remainder = (int)inside - numToSubtract;
        q += prevOutside;
        for(int t = 1; t < nums.size()+5; t++){
            if(Double.parseDouble(q)*Double.parseDouble(q) == number){
                break;
            }
            if(t == nums.size()){
                q += ".";
            }
            int index = q.indexOf(".");
            if (index != -1 && q.substring(index+1).length() == 5){
                break;
            }
            if (remainder > 0){
                i = "" + remainder;
            } 
            if(t < nums.size()){
                if(nums.get(t) <= 0)
                    i += 0 + "" + -1*nums.get(t);
      	    else 
                    i += nums.get(t);
            } else {
                i += "00";
            }
            inside = Integer.parseInt(i);
            int tens = Integer.parseInt(prevOutside.substring(prevOutside.length()-1));
            int nextOutside = (Integer.parseInt(prevOutside) + tens)*10;
            int one = 0;
            for(int o = 9; o >= 0; o--){
                if((nextOutside+o)*o <= inside){
                   nextOutside = nextOutside+(o);         
                   outside = nextOutside;
                   numToSubtract = (nextOutside)*o;         
                   remainder = inside - numToSubtract;
                   q += o;
                   break;
                }
            }
            prevOutside = "" + nextOutside;
        }
        return Double.parseDouble(q);
    }

    public static ArrayList<Integer> groupTwos(int number){
        String s = "" + number;
        ArrayList<Integer> n = new ArrayList<Integer>();
        if(s.length()%2 == 0){
            for(int i = 0; i < s.length(); i+=2){
                int var = Integer.parseInt(s.substring(i,i+2));
                if(var < 10)
                    n.add(var*-1);
                else 
                    n.add(var);
            }
        } else {
            n.add(Integer.parseInt(s.substring(0,1)));
            for(int i = 1; i < s.length(); i+=2){
                int var = Integer.parseInt(s.substring(i,i+2));
                if(var < 10)
                    n.add(var*-1);
                 else 
                    n.add(var); 
            }
        }
            return n;
    }
    public static int closestSquare(int number){
       int var = 0;
       for(int i = 0; i*i <= number; i++){
             var = i;
       }
       return var;
    }
}
