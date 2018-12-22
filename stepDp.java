
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author suraj
 */


class stepDp {
static double max(double a, double b){
if(a>b)
    return a;
else return b;
}
   
static double[][] mean(int arr[][],int size,int type){

    double[][] res=new double[size][];

for(int i=0;i<size;i++)
    res[i]=new double[size];

 
 
for(int i=0;i<size;i++)
    if(type==0){
        float sum=0;
    for(int j=i;j<size;j++){
        if(j==i)
            res[i][j]=arr[i][1];
       else
            res[i][j]=(res[i][j-1]*(j-i)+arr[j][1])/(j-i+1);
    }
    }
    else{
    for(int j=i;j<size;j++)
    res[i][j]=findmean2(arr,i,j);}
return res;
    
        
        }


static double findmean2(int arr[][],int a,int b){
    double max,min; 
    max=Double.MIN_VALUE;
        min=Double.MAX_VALUE;
    for(int i=a;i<=b;i++)
    {
        if(arr[i][1]>max)
            max=arr[i][1];
        if(arr[i][1]<min)
        min=arr[i][1];    
    }
 return (max+min)/2;
}

static int t(double[][] err,double [][]d,int i,int j){
int temp=0;
double temperror=Float.MAX_VALUE;
if (i>=j)
    return -1;
else    
for(int k=0;k<j;k++)
{
if(temperror>(err[i-1][k]+d[k+1][j]))
        {
            temperror=err[i-1][k]+d[k+1][j];
            temp=k;
        }
}
return temp;
}

static int t2(double[][] err,double [][]d,int i,int j){
int temp=0;
double temperror=Float.MAX_VALUE;
if (i>=j)
    return -1;
else    
for(int k=0;k<j;k++)
{
if(temperror>max(err[i-1][k],d[k+1][j]))
        {
            temperror=max(err[i-1][k],d[k+1][j]);
            temp=k;
        }
}
return temp;
}
static void funcapprox(int[][] arr,double [][]d,double [][]m,int n,int k,int t,String st) throws FileNotFoundException, IOException{
int [][] s=new int[k][];
double [][] error=new double[k][];
double [][] dij=new double[k][];
for(int i=0;i<k;i++){
s[i]=new int[n];
error[i]=new double[n];
dij[i]=new double[n];
}
for(int i=0;i<n;i++){
s[0][i]=0;
error[0][i]=d[0][i];
dij[0][i]=m[0][i];
}
int tempindex;
for(int i=1;i<k;i++)
    for(int j=0;j<n;j++){
        if(t==0)
    tempindex=t(error,d,i,j);
        else
     tempindex=t2(error,d,i,j);
    if(tempindex!=-1){
        if(t==0)
    error[i][j]=error[i-1][tempindex]+d[tempindex+1][j];
        else
     error[i][j]=max(error[i-1][tempindex],d[tempindex+1][j]);
    s[i][j]=tempindex;
    dij[i][j]=m[tempindex+1][j];
    }
    else
    {
       error[i][j]=0.0f;
    s[i][j]=-1;
    dij[i][j]=m[i][j]; 
    }
    }
  /*  for(int i=0;i<k;i++)
    {for(int j=0;j<n;j++)
    
        {
            System.out.print("("+i+","+j+","+s[i][j]+","+error[i][j]+","+dij[i][j]+") ");
        }
        
   System.out.println();
    
    }*/
//    System.out.print("("+k+","+n+","+s[k-1][n-1]+","+error[k-1][n-1]+","+dij[k-1][n-1]+") ");
//System.out.println("------------------------POINTS--------------------------");
BufferedWriter wr=new BufferedWriter(new FileWriter(st));
printpoints(dij,k-1,n-1,s,arr,1,wr);
//System.out.println("------------------------POINTS--------------------------");
wr.close();
    
}
static void printpoints(double [][]dij,int k,int n,int [][]s,int [][]arr,int a,BufferedWriter st) throws IOException{
if(k==0){
st.write(a+"\n");
    //System.out.println(a);
st.append(arr[0][0]+" "+dij[0][n]+"\n");
//System.out.println(arr[0][0]+" "+dij[0][n]);
}
else 
{
   if(s[k][n]==-1){
   printpoints(dij,k-1,n-1,s,arr,a+1,st);
  // System.out.println(arr[n][0]+" "+dij[k][n]);  
   st.append(arr[n][0]+" "+dij[k][n]+"\n");
   }
   else{ 
   printpoints(dij,k-1,s[k][n],s,arr,a+1,st);
//System.out.println(arr[s[k][n]+1][0]+" "+dij[k][n]);    
st.append(arr[s[k][n]+1][0]+" "+dij[k][n]+"\n");    

   }}
}
static double[][] difference(int[][] arr,double[][] mean,int size){
    double[][] res=new double[size][];
for(int i=0;i<size;i++)
    res[i]=new double[size];
double sum=0;
double [] cum=new double[size];
    cum[0]=Math.pow(arr[0][1],2);
    for(int i=1;i<size;i++)
    cum[i]=cum[i-1]+Math.pow(arr[i][1],2);
    for(int i=0;i<size;i++)
        for(int j =i;j<size;j++){
        if(i!=0)
        res[i][j]=(cum[j]-cum[i-1]-Math.pow(mean[i][j],2)*(j-i+1));
        else{
        sum=0.0f;
            for(int k=0;k<=j;k++)
        {
        sum+=((arr[k][1]-mean[0][j])*(arr[k][1]-mean[0][j]));
        }
        res[i][j]=sum;}}
return res;}
static double[][] difference2(int[][] arr,double[][] mean,int size){
    double[][] res=new double[size][];
for(int i=0;i<size;i++)
    res[i]=new double[size];
double max,min;
    for(int i=0;i<size;i++){
        max=arr[i][1];
        min=arr[i][1];
        for(int j =i;j<size;j++){
        
        double a=Math.abs(mean[i][j]- max);
        double b=Math.abs(mean[i][j]- min);;
            if(a>b)
                res[i][j]=a;
            else
                res[i][j]=b;
            if(max<arr[j][1])
                max=arr[j][1];
            if(min>arr[j][1])
                min=arr[j][1];
        }}
    return res;}

public static void main(String[] args) throws FileNotFoundException, IOException{
    
File inFile = null;
if (1 < args.length) {
   inFile = new File(args[0]);
} else {
   System.err.println("Invalid arguments count:" + args.length);
   return;
}
Scanner s1=new Scanner(inFile);
Scanner s =new Scanner(System.in);
int k;
k=s1.nextInt();
int t;
t=s1.nextInt();
int n;
n=s1.nextInt();
int[][] arr=new int[n][];
for(int i=0;i<n;i++){
arr[i]=new int[2];
arr[i][0]=s1.nextInt();
arr[i][1]=s1.nextInt();
}if(n<=k){
BufferedWriter wr=new BufferedWriter(new FileWriter(args[1]));
wr.write(n+"\n");
for(int i=0;i<n;i++)
wr.append(arr[i][0]+" "+arr[i][1]+"\n");
wr.close();
}
else{
double[][] m=mean(arr,n,t);
/*System.out.println("------------------------MEAN--------------------------");
for(int i=0;i<n;i++){
    for(int j=i;j<n;j++){
       System.out.print("("+i+","+j+","+m[i][j]+") ");
    }
System.out.println();
}
System.out.println("------------------------MEAN--------------------------");
*/

double[][] d=null;
if(t==1)
d=difference2(arr,m,n);
else if(t==0)
    d=difference(arr,m,n);
else return;
/*System.out.println("------------------------DIFFERENCE--------------------------");
for(int i=0;i<n;i++){
    for(int j=i;j<n;j++){
       System.out.print("("+i+","+j+","+d[i][j]+") ");
    }
System.out.println();
}
System.out.println("------------------------DIFFERENCE--------------------------");
*/
funcapprox(arr,d,m,n,k,t,args[1]);
}
}
    
}

