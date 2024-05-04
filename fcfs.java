import java.util.*;
import java.util.Scanner;
    public class fcfs{

        int[] findct(int n,int pid[],int at[], int bt[],int ct[]){

            int i,j,t1,t2,t3;
            for (i=0;i<n-1; i++){

                for(j=0;j<n-i-1; j++){

                    if(at[j]> at[j+1]){

                        t1=pid[j];
                        pid[j]=pid[j+1];
                        pid[j+1]=t1;

                        t2=at[j];
                        at[j]=at[j+1];
                        at[j+1]=t2;

                        t3=bt[j];
                        bt[j]=bt[j+1];
                        bt[j+1]=t3;
                    }
                }
            }

            ct[0]=at[0]+bt[0];
            for( i=1; i<n ;i++){

                if(at[i]>ct[i-1]){
                    ct[i]=at[i]+bt[i];
                }
                else{
                    ct[i]=bt[i]+ct[i-1];
                }
            }
            //System.out.println("Avg tat=", obj.findtat(n,ct[],at[]));
            return ct;

        }

        int[] findtat(int n,int ct[], int at[]){
            int tat[]=new int[5];
            for(int i=0; i<n ; i++){
                tat[i]=ct[i]-at[i];
            }
            return tat;

        }
        int[] findwt(int n,int tat[], int bt[]){
            int wt[]= new int[5];
            for(int i=0; i<n ; i++){
                wt[i]=tat[i]-bt[i];
            }
            return wt;

        }
        public static void main(String[] args){
        int pid[]={1,2,3,4,5},at[]={5,9,3,2,7},bt[]={6,7,1,5,6};
        int n=5;
        int[] ct= new int[5];
        int[] tat= new int[5];
        int[] wt= new int[5];
        int sum=0,sum1=0;
        fcfs obj=new fcfs();
        ct=obj.findct(n,pid,at,bt,ct);
        tat=obj.findtat(n,ct,at);
        wt=obj.findwt(n,tat,bt);
        for(int i=0;i<n;i++){
            sum=sum+tat[i];
        }
        float avgtat=sum/n;
        for(int i=0;i<n;i++){
            sum1=sum1+wt[i];
        }
        float avgwt=sum1/n;
        System.out.println("Avg tat="+avgtat);
        System.out.println("Avg wt="+avgwt);

    }

}