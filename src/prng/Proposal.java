package prng;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

public class Proposal {
     public int round;
    
   
   public Proposal(int i){
       this.round=i;
   }
   
 
    public byte [] GenwithLFSR(int nbreround, byte [] bytestab ){
    
        int b1,b2,b3,b4,b5,b6,xoredbit, round = 0;
        byte [] newBytesTab = bytestab;
        byte [] bytesresulttab = new byte [this.round];
       
         
        newBytesTab[1] = XorReg(bytestab[1],bytestab[6] );
        newBytesTab[3] = XorReg(bytestab[3],bytestab[7] );
        
        
        while (round < nbreround ){   
           for (int iBit=0; iBit<8; iBit++){
                b1=converttobit(0, iBit, newBytesTab); b2=converttobit(1, iBit, newBytesTab);   
                b3=converttobit(2, iBit, newBytesTab); b4=converttobit(3, iBit, newBytesTab); 
                b5=converttobit(4, iBit, newBytesTab); b6=converttobit(5, iBit, newBytesTab);
                
                b2=FlipOnebit(b2); b5=FlipOnebit(b5);
                
                xoredbit= (b1^b2^b3)^(b4^b5^b6);
                
                bytesresulttab=Filltabres(bytesresulttab,round, iBit, xoredbit);
                
                newBytesTab=rotateLeft(newBytesTab, 48, 1 ,xoredbit);
            }
        round++;
        }
    return bytesresulttab;
    }
    
    
    public byte XorReg (byte reg1 , byte reg2){
        
        byte reg = 0 ;
        int b1 , b2 , xoredbit ;
        for (int i=0; i<8; i++){
            b1=converttobit(i,reg1); b2=converttobit(i,reg2);   
            xoredbit= b1^b2;
            reg = (byte) (  (reg << 1 )| xoredbit);  
        }
        return reg ; 
    }
    
    
    public int FlipOnebit(int bitflip){
        if (bitflip==0)  bitflip=1;
        else bitflip=0;
    
    return bitflip;
    }
    
    public int converttobit(int ibyte,int ibit,byte[] T){
        int b=(T[ibyte]>>(8-(ibit+1))) & 0x0001;
    return b;
    }
    
    public int converttobit(int ibit, byte T){
        int b=(T >>(8-(ibit+1))) & 0x0001;
    return b;
    }
    
    public byte [] Filltabres( byte[] bytestab,int posbyte, int posbit, int resbit){
        
        byte oldbyte=bytestab[posbyte];
        oldbyte=(byte)(((0xFF7F>>posbit)& oldbyte)& 0x00FF);
        byte newbyte=(byte)((resbit<<(8-(posbit+1)))|oldbyte);
        bytestab[posbyte]=newbyte;
         
    return bytestab;
    }
    
    public byte[] rotateLeft(byte[] in, int len, int step,int bitvalue) {
        
        int numOfBytes = (len-1)/8 + 1;
        byte[] out = new byte[numOfBytes];
        for (int i=0; i<len; i++) {
            if (i==0)setBit(out,0,bitvalue);
            else{
                int val = getBit(in,(i+step)%len);
                setBit(out,i,val);
            } 
        }
    return out;
    }
    
    public int getBit(byte[] data, int pos) {
        int posByte = pos/8; 
        int posBit = pos%8;
        byte valByte = data[posByte];
        int valInt = valByte>>(8-(posBit+1)) & 0x0001;
    
    return valInt;
    }
    
    public void setBit(byte[] data, int pos, int val) {
    
        int posByte = pos/8; 
        int posBit = pos%8;
        byte oldByte = data[posByte];
        oldByte = (byte) (((0xFF7F>>posBit) & oldByte) & 0x00FF);
        byte newByte = (byte) ((val<<(8-(posBit+1))) | oldByte);
        data[posByte] = newByte;
    
    }
    
    
    public void writeBytes(byte[] data, String out) throws Exception {
        FileOutputStream fos = new FileOutputStream(out);
        fos.write(data);
        fos.close();
    }

    public byte[] readBytes(String in) throws Exception {
        FileInputStream fis = new FileInputStream(in);
        int numOfBytes = fis.available();
        byte[] buffer = new byte[numOfBytes];
        fis.read(buffer);
        fis.close();
    
    return buffer;
    }

}



