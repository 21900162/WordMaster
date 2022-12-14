package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    ArrayList<Word> search_list;//단어 검색
    Scanner sc;
    final String fname = "Dictionary.txt";

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

    public void listAll(int level){
        int count=0;

        System.out.println("---------------------------------------");
        for(int i=0; i<list.size(); i++){
            int wordlevel = list.get(i).getLevel();
            if( wordlevel != level) continue;
            System.out.print(count+1+" ");
            System.out.println(list.get(i).toString());
            count++;
        }
        System.out.println("---------------------------------------");
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

    public void deleteItem() {
        String search;
        int num;

        System.out.print("=>삭제할 단어 검색: ");
        search = sc.next();
        ArrayList<Integer> idlist = this.listAll(search);

        System.out.print("삭제할 번호 선택: ");
        num = sc.nextInt();
        sc.nextLine();
        System.out.print("정말로 삭제하실래요?(Y/n) ");
        String answer = sc.next();
        if(answer.equalsIgnoreCase("y")){
            list.remove((int)idlist.get(num-1));
            System.out.println("단어가 삭제되었습니다. ");
        }
        else {
            System.out.println("취소되었습니다. ");
        }
    }

    public  void loadFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            int count=0;
            while(true){
                line = br.readLine();
                if(line == null) break;

                String data[] = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count ++;
            }

            br.close();
            System.out.println("==> "+ count + "개 로딩 완료");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(fname));
            for(Word one: list){
                pr.write(one.toFileString() +"\n");
            }
            pr.close();
            System.out.println("===> 데이터 저장 완료");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showLevel() {
        System.out.println("=> 원하는 레벨은? (1~3) ");
        int level = sc.nextInt();
        listAll(level);
    }

    public void searchWord() {
        System.out.print("=> 원하는 단어는? ");
        String word = sc.next();
        listAll(word);
    }
}
