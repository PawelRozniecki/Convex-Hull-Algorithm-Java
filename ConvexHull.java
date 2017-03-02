import java.awt.*;
import java.util.*;

public class ConvexHull{


    ArrayList<Point> p;
    ArrayList<Point> U;
    ArrayList<Point> L;
    ArrayList<Point> mergeHull;





    public ArrayList<Point> algorithmConvexHull(ArrayList<Point>p){




        Comparator<Point> comp = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return (new Double(o1.getX())).compareTo(new Double(o2.getX()));
            }

        };



        Collections.sort(p,comp);




        Upper(p);
        Lower(p);





        mergeHull = union(Lower(p),Upper(p));



        mergeHull.forEach(System.out::println);

        return mergeHull;

    }



//upper hull


    public  ArrayList<Point> Upper(ArrayList<Point> p2) {
        U = new ArrayList<Point>();



        int currentUPos = -1;

        U.add(p2.get(0));
        U.add(p2.get(1));

        for (int i = 3; i <= p2.size(); i++) {



            U.add(p.get(i));

            while (U.size() > 2 && !rightTurn((U.get(currentUPos-2)), U.get(currentUPos-1), U.get(currentUPos))) {


                U.remove(currentUPos-1);
currentUPos--;

            }
        }

        return U;
    }

//lower hull

    public  ArrayList<Point> Lower(ArrayList<Point> p3) {


        L = new ArrayList<Point>();


        int currentLPos = -1;



        for (int i = p3.size()-1; i >= 0; i--) {



            while (L.size() > 2 && !rightTurn((L.get(currentLPos-2)), L.get(currentLPos-1), L.get(currentLPos))){

                L.remove(currentLPos-1);
                currentLPos--;
            }

        }
        return L;

    }
    public static ArrayList<Point> union(ArrayList<Point> a,ArrayList<Point>b){

        HashSet<Point> set = new HashSet<Point>();
        set.addAll(a);
        set.addAll(b);

        return  new ArrayList<Point>(set);

    }


    private boolean  rightTurn(Point a, Point b,Point c){

        long crossProduct = (((long)b.x - a.x) * ((long)c.y - a.y)) - (((long)b.y - a.y) * ((long)c.x - a.x));


        if (crossProduct < 0){

            return true;
        }
        else if(crossProduct>0){
            return false;
        }

        else return false;
    }






    public static void main(String[] args){


        ArrayList<Point> A = new ArrayList<>();



        Random random = new Random();
        for (int e = 0; e < 10; e++) {
            int c = random.nextInt(10)+1;
            int d = random.nextInt(10)+1;
            System.out.println("x: " + c + "    y: " + d);
            Point point = new Point(c, d);
            A.add(point);

        }



        ConvexHull ch = new ConvexHull();

        ch.algorithmConvexHull(A);








    }


}