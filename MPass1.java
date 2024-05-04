
/*
MACRO
INCR &X,&Y,&REG=AREG
MOVER &REG,&X
ADD &REG,&Y
MOVEM &REG,&X
MEND
MACRO
DECR &A,&B,&REG=BREG
MOVER &REG,&A
SUB &REG,&B
MOVEM &REG,&A
MEND
START 100
READ N1
READ N2
INCR N1,N2,REG=CREG
DECR N1,N2
STOP
N1 DS 1
N2 DS 1
END */
import java.io.*;
 class mnt{
    String name;
    int addr;
    int arg_cnt;
    mnt(String nm, int addr){
        this.name=nm;
        this.addr=addr;
        this.arg_cnt=0;
    }
}
 class mdt{
    String stmnt;
    mdt(){
        stmnt="";
    }
}
  class arglist{
    String argname;
    arglist(String argument){
        this.argname=argument;
    }
}



public class MPass1 {
public static void main(String[] args) throws IOException {
BufferedReader brl=new BufferedReader (new FileReader ("macro.txt" ));
String line;
mdt[] MDT=new mdt[20];
mnt[] MNT=new mnt[4];
arglist[] ARGLIST = new arglist[10];
boolean macro_start=false, macro_end=false, fill_arglist=false;
int mdt_cnt=0, mnt_cnt=0, arglist_cnt=0;
while ((line = brl.readLine()) !=null)
{
    line=line.replaceAll(",", " ");
    String[] tokens=line.split("\\s+");
    MDT [mdt_cnt] = new mdt();
    String stmnt = "";
    for (int i=0;i<tokens.length;i++)
    {
            if (tokens[i].equalsIgnoreCase ("mend"))
            {
                MDT[mdt_cnt++].stmnt="\t"+tokens [i];
                macro_end = true;
            }
            if (tokens[i].equalsIgnoreCase ("macro"))
                {
                macro_start = true;
                macro_end = false;
                }
                
            else if(!macro_end)
            {
                if (macro_start)
                {
                    MNT[mnt_cnt++]=new mnt(tokens[i], mdt_cnt);
                    macro_start=false;
                    fill_arglist=true;
                }
                
                
                if(fill_arglist)
                {
                    while (i<tokens.length)
                    {

                    MDT[mdt_cnt].stmnt=  MDT[mdt_cnt].stmnt+ "\t" + tokens[i];
                    stmnt=stmnt +"\t"+ tokens [i];
                        if (tokens[i].matches("&[a-zA-Z]+") || tokens[i].matches("&[a-zA-Z]+[0-9]+"))
                        {
                            ARGLIST[arglist_cnt++]=new arglist(tokens[i]);
                        }
                    i++;
                    }
                    fill_arglist=false;
                }
                
                else {
                
                    if (tokens[i].matches("[a-zA-Z]+") || tokens [i].matches("[a-zA-Z]+[0-9]+") || tokens[i].matches("[0-9]+[a-zA-Z]+"))
                    {
                        MDT[mdt_cnt].stmnt = MDT[mdt_cnt].stmnt+ "\t" + tokens[i];
                        stmnt =stmnt +"\t"+ tokens[i];
                    }
                    if (tokens[i].matches("&[a-zA-Z]+") || tokens[i].matches("&[a-zA-Z]+[0-9]+"))
                    {
                        for (int j=0;j<arglist_cnt; j++)
                            if (tokens[i].equals (ARGLIST[j].argname))
                            {
                            MDT[mdt_cnt].stmnt = MDT[mdt_cnt].stmnt + "\t#"+(j+1); 
                            stmnt= stmnt +"\t"+(j+1);
                            }
                    }
            }
        }
    }
    if (stmnt!="" && !macro_end)
        mdt_cnt++;
}
brl.close();
BufferedWriter bwl=new BufferedWriter (new FileWriter ("MNT.txt"));
 System.out.println("\n\t********MACRO NAME TABLE**********");
System.out.println("\n\tINDEX\tNAME\tADDRESS");
 for (int i=0; i<mnt_cnt; i++)
{
    System.out.println("\t"+i+"\t"+MNT [i].name+"\t"+MNT [i].addr);
bwl.write (MNT[i].name+"\t"+MNT [i].addr+"\n");
}

bwl.close();


 bwl=new BufferedWriter (new FileWriter ("ARGLIST.txt")); 
System.out.println("\n\n\t********ARGUMENT LIST**********"); 
System.out.println("\n\tINDEX\tNAME\tADDRESS");
for (int i=0; i<arglist_cnt; i++)
{
    System.out.println("\t"+i+"\t"+ARGLIST[i].argname);
    bwl.write (ARGLIST[i].argname+"\n");
}
bwl.close();


System.out.println("\n\t********MACRO DEFINITION TABLE*********");
System.out.println("\n\tINDEX\t\tSTATEMENT");
bwl=new BufferedWriter (new FileWriter ("MDT.txt"));
 for (int i=0; i<mdt_cnt; i++)
{
System.out.println("\t"+i+"\t"+MDT [i].stmnt);
bwl.write (MDT[i].stmnt+"\n");
}
bwl.close();
}
}
