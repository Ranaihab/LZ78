//Rana Ihab Ahmed Fahmy
//ID: 20190207
//CS-2

public class Main {
    public static void main(String[] args){
        LZ78 lz78 = new LZ78();

        lz78.compress();
        System.out.println();
        System.out.print("Text after decompression: ");
        lz78.decompress();
    }
}
