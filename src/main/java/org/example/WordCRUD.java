package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    ArrayList<Word> search_list;//단어 검색
    Scanner sc;

    public WordCRUD(Scanner sc) {
        list = new ArrayList<>();
        this.sc = sc;
    }

    @Override
    public Object add() {

        System.out.print("난이도(1,2,3) & 새 단어 입력 : ");
        int level = sc.nextInt();
        String word = sc.nextLine();

        System.out.print("뜻 입력 : ");
        String meaning = sc.nextLine();

        return new Word(0,level, word, meaning);
    }

    public void addWord(){//list에 word 추가
        Word one = (Word) add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다.");
    }

    public void update() {
        String search;
        String meaning;
        int up_num;

        System.out.print("=>수정할 단어 검색: ");
        search = sc.next();
        ArrayList<Integer> idlist = this.listAll(search);

        System.out.print("수정할 번호 선택: ");
        up_num = sc.nextInt();
        sc.nextLine();
        System.out.print("뜻 입력: ");
        meaning = sc.nextLine();
        Word word = list.get(idlist.get(up_num-1));
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다. ");
    }

    public ArrayList<Integer> listAll(String keyword){
        ArrayList<Integer> search_list = new ArrayList<>();
        int count=0;

        System.out.println("---------------------------------------");
        for(int i=0; i<list.size(); i++){
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;
            System.out.print(count+1+" ");
            System.out.println(list.get(i).toString());
            search_list.add(i);
            count++;
        }
        System.out.println("---------------------------------------");
        return search_list;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void select(int id) {

    }

    public void listAll(){
        System.out.println("---------------------------------------");
        for(int i=0; i<list.size(); i++){
            System.out.print(i+1+" ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("---------------------------------------");
    }

}
