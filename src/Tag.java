//Rana Ihab Ahmed Fahmy
//ID: 20190207
//CS-2

import java.util.BitSet;

public class Tag {
    private BitSet position;
    private Character nextCharacter;

    //constructor
    public Tag(int p){
        position = new BitSet(p);
    }

    //convert position value from decimal to binary and store it as bits
    public void setPosition(int num){
        int i = 0;
        while(num != 0 ){
            if(num % 2 != 0){
                position.set(i);
            }
            i++;
            num = num >>> 1;
        }
    }

    public void setNextCharacter(Character c){
        nextCharacter = c;
    }

    //convert position from binary to decimal and return it
    public int getPosition(){
        int val = 0;
        for(int i = 0; i< position.length(); i++){
            if(position.get(i)) {
                val += (1 << i);
            }
        }
        return val;
    }

    public Character getNextCharacter(){
        return nextCharacter;
    }

    @Override
    public String toString(){
        return "<" + this.getPosition() + ", " + this.getNextCharacter()+">";
    }
}
