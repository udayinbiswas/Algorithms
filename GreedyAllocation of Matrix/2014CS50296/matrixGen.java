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
import java.util.*;

/**
 *
 * @author udayin-2014cs50296
 */
public class matrixGen {
    
      int n=0;
      String[] rowSum=new String[n];
      String[] colSum=new String[n];
      int[][] matrix;
      int[] sumTillNow;
      String[] copyRowSum;
      String[] copyColSum;
 








 
    public void setValues(String file) throws IOException
    {
        Scanner sc = new Scanner(new File(file));
        String nVal=sc.next();
        n=Integer.parseInt(nVal);
        String rowVal=sc.next();
        String colVal=sc.next();
        sc.close();
         rowSum=rowVal.split(",");
         colSum=colVal.split(",");
         sumTillNow=new int[n];
         matrix=new int[n][n];
         copyRowSum=rowVal.split(",");
         copyColSum=colVal.split(",");
    }

     
     

    
     //Greedy algorithm
     public void getMatrix()
     {
         //start with array[0],sort the values according to c[0][j]-a[0][j]
           for (int i=0;i<n;i++)
           {
            HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();
            for (int j=0;j<n;j++)
            {
            //  System.out.println(sumTillNow[0]);
              
              hm.put(j,Integer.parseInt(colSum[j])-sumTillNow[j]);

            }
             
            int[] diff=new int[n];
            for (int q=0;q<n;q++)
            {diff[q]=Integer.parseInt(colSum[q])-sumTillNow[q];
            }

           // int[] sorted=matrix[i];
            Arrays.sort(diff);

            int counter=n-1;

             while (Integer.parseInt(rowSum[i])!=0)
             { 
               
               Iterator<Integer> it=hm.keySet().iterator();  
               while (it.hasNext())
               {
                 int fit=it.next();
                
                 if (hm.get(fit)==diff[counter])
                 {
                
                  
                  matrix[i][fit]=1;
                  hm.remove(fit);
                  sumTillNow[fit]++;
                  
                 
                  break;
                 }
               }
               
              

               
               counter--;

               rowSum[i]=Integer.toString(Integer.parseInt(rowSum[i])-1);
             }
            
            }
     }


     public boolean checkValid()
     {
         boolean truth=true;
         for (int i=0;i<n;i++)
         {
          int sum=0;
          for (int j=0;j<n;j++)
          {
            if (matrix[i][j]==1) { sum++;  }
          }
          //System.out.println(sum+","+Integer.parseInt(copyRowSum[i]));
          if (sum!=Integer.parseInt(copyRowSum[i])){truth=false; break;}
         }

         for (int j=0;j<n;j++)
         {
          int sum=0;
          for (int i=0;i<n;i++)
          {
            if (matrix[i][j]==1) { sum++;  }
          }
          if (sum!=Integer.parseInt(colSum[j])){truth=false; break;}
         }  
         
         return truth;


     }



    public static void main(String args[]) throws IOException
    {
      

      matrixGen b=new matrixGen();
      b.setValues("input.txt");
      b.getMatrix();
      String outer="";
      if (b.checkValid())
      { outer=outer+"1 \n";
     for (int i=0;i<b.n;i++)
      {
          for (int j=0;j<b.n;j++)
          { 
            if (j<(b.n)-1)
            {outer= outer+Integer.toString(b.matrix[i][j])+",";}
            else
              {outer= outer+Integer.toString(b.matrix[i][j]);}
          }
          outer=outer+"\n";
      }
    }
      else
      {
        outer=outer+"0 \n";
      }
      


     try {

      
      
      File file = new File("output.txt");

      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }

      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(outer);
      bw.close();

      //System.out.println(a.giggs);

    } catch (IOException e) {
      e.printStackTrace();
    }


    }
    


}
