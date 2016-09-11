/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author udayin
 */
public class evenPath  {
    
    String giggs="v1";
    // To create the adjacency List matrix 
    LinkedList<String>[] array=new LinkedList[1001]; 
    int nodes=0;
    public void makeAdjacencyList(String file) throws IOException
    {
        Scanner sc = new Scanner(new File(file));
        String a=sc.next();
        int countin=Integer.parseInt(a);
        int countout=0;
        //System.out.println(countin);
    while (sc.hasNext() && countin!=countout) {
      String s = sc.next();
      String[] split=s.split("->");
      String vertex=split[0];
      int vertexlength=vertex.length();
      int v=Integer.parseInt(vertex.substring(1,vertexlength));
      LinkedList<String> b= new LinkedList<String>();
       for (int i=0;i<split.length;i++)
       {
           b.add(split[i]);
       }
       array[v]=b;
      countout++;
    }
    giggs=sc.next();
    sc.close();
    }

     
    int sum=0;
   // HashSet <String> evenDVertex = new HashSet <String>();
       int[] evenDVertex=new int[1001];
    public void DFS(String s)
    {
    /*        counter=counter%2;
      int y=Integer.parseInt(s.substring(1,s.length()));
      if (counter==0){ sum++; evenDVertex[y]=1; 
      } 
      LinkedList<String> b=array[y];
   Iterator x = b.listIterator(1);
   while (x.hasNext()) {
    String h=(String)x.next();
    int j=Integer.parseInt(h.substring(1,h.length()));
    if (evenDVertex[j]!=1){DFS(h,counter+1);} */

  
    int y=Integer.parseInt(s.substring(1,s.length()));
       evenDVertex[y]=1; sum++;
   LinkedList<String> b=array[y];
   Iterator x = b.listIterator(1);
   while (x.hasNext()) {
            String m=(String)x.next();
            int n=Integer.parseInt(m.substring(1,m.length()));
            LinkedList<String> c=array[n];
             Iterator z = c.listIterator(1);
             while (z.hasNext())
             {
              String d=(String)z.next();
              int l=Integer.parseInt(d.substring(1,d.length()));
              if (evenDVertex[l]!=1){DFS(d);}
             }  
   }
      
      //Go into vertex's adjacency list and do DFS on them
    }

    public static void main(String args[]) throws IOException
    {
      evenPath a=new evenPath();
      a.makeAdjacencyList("input.txt");
      a.DFS(a.giggs);
      

      String listofvertices=Integer.toString(a.sum)+"\n";

      
      int count=1;
      for (int j=1;j<a.evenDVertex.length;j++)
      {
        if (a.evenDVertex[j]==1){if (count!=a.sum){listofvertices=listofvertices.concat("v"+j+",");} 
        else {listofvertices=listofvertices.concat("v"+j);} count++;}
      }

     



     try {

      
      
      File file = new File("output.txt");

      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }

      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(listofvertices);
      bw.close();

      //System.out.println(a.giggs);

    } catch (IOException e) {
      e.printStackTrace();
    }


    }
    

 /*   counter=counter%2;
      if (counter==0){ evenDVertex.add(s); 
      } //means at even distance
      int y=Integer.parseInt(s.substring(1,s.length()));
     // System.out.println(y);
      LinkedList<String> b=array[y];
       // set Iterator at specified index

   Iterator x = b.listIterator(1);
   // print list with the iterator
   while (x.hasNext()) {
    DFS((String)x.next(),counter+1); */



}
