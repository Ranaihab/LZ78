//Rana Ihab Ahmed Fahmy
//ID: 20190207
//CS-2

import java.util.*;

public class LZ78 {
    private ArrayList<Tag> tags;

    public LZ78(){
        tags= new ArrayList<>();
    }

    private void addTags(int pos, ArrayList<Character> next, ArrayList<Integer> p){
        ArrayList<Integer> position = new ArrayList<>();
        while(pos != 0 ){
            position.add(pos%2);
            pos = pos>>> 1;
        }

        for(int i =0; i<next.size(); i++){
            Tag tag=new Tag(position.size());
            tag.setNextCharacter(next.get(i));
            tag.setPosition(p.get(i));
            tags.add(tag);
        }

        System.out.println("Tags:");
        for(int i = 0; i<tags.size();i++){
            System.out.println(tags.get(i));
        }

    }

    public void compress(){
        ArrayList<String> dictionary = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.print("Text to compress: ");
        String text =  in.nextLine();

        ArrayList<Character> nextChar = new ArrayList<>();
        ArrayList<Integer> position = new ArrayList<>();
        for(int i =0; i<text.length(); i++){
            String searchText = text.substring(i, i+1);
            if(!dictionary.contains(searchText)){
                dictionary.add(searchText);
                nextChar.add(text.charAt(i));
                position.add(0);
                continue;
            }

            if(i==text.length()-1){
                position.add(dictionary.indexOf(searchText)+1);
                nextChar.add(null);
                break;
            }

            for(int j =i+1; j<text.length(); j++){
                String newSearchText = searchText + text.charAt(j);
                if(!dictionary.contains(newSearchText)){
                    dictionary.add(newSearchText);
                    nextChar.add(text.charAt(j));
                    position.add(dictionary.indexOf(searchText)+1); //+1 because "0" is for non existent elements in the dictionary and for tags 1 should be the beginning of dictionary
                    i+=searchText.length();
                    break;
                }
                if(i+j == text.length()-1){
                    position.add(dictionary.indexOf(newSearchText)+1);
                    nextChar.add(null);
                    i+=searchText.length();
                    break;
                }
                searchText = newSearchText;
            }
        }

        int maxP=0;
        for(int j = 0;j<position.size();j++){
            if(maxP<position.get(j)){
                maxP= position.get(j);
            }
        }
        addTags(maxP, nextChar, position);
    }

    public void decompress(){
        String text ="";
        ArrayList<String> dictionary = new ArrayList<>();
        for(int i =0; i<tags.size(); i++){
            if(tags.get(i).getPosition() == 0){
                text+=tags.get(i).getNextCharacter();
                dictionary.add(String.valueOf(tags.get(i).getNextCharacter()));
                continue;
            }

            if(i == tags.size()-1){
                text+=dictionary.get(tags.get(i).getPosition()-1);
                if(tags.get(i).getNextCharacter() != null){
                    text += tags.get(i).getNextCharacter();
                }
                break;
            }

            String subText = dictionary.get(tags.get(i).getPosition()-1) + tags.get(i).getNextCharacter();
            text+= subText;
            dictionary.add(subText);
        }
        System.out.println(text);
    }
}
