package baekjoon.structure.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class 전화번호_목록 {
    private static class TrieNode{
        private Map<Character , TrieNode> childNodes = new HashMap<>();
        private boolean isLastChar = false;

        Map<Character , TrieNode> getChildNodes(){
            return this.childNodes;
        }
        boolean isLastChar(){
            return isLastChar;
        }
        void setLastChar(boolean isLastChar){
            this.isLastChar = isLastChar;
        }
        @Override
        public String toString() {
            return "TrieNode{" +
                    "childNodes=" + childNodes +
                    ", isLastChar=" + isLastChar +
                    '}';
        }
    }

    private static class Trie{
        public TrieNode rootNode;
        Trie(){
            rootNode = new TrieNode();
        }
        void insert(String word){
            TrieNode thisNode = this.rootNode;
            for(int i = 0 ; i < word.length() ; i++){
                thisNode.setLastChar(false);
                thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i) , character -> new TrieNode());
            }
            if(thisNode.getChildNodes().size() > 0){
                thisNode.setLastChar(false);
            }
            else thisNode.setLastChar(true);

        }
        boolean contains(String word){
            TrieNode thisNode = this.rootNode;
            for(int i = 0 ; i < word.length() ; i++){
                TrieNode node = thisNode.getChildNodes().get(word.charAt(i));
//            System.out.println(node);
                if(node == null) return false;
                thisNode = node;
            }
            return thisNode.isLastChar();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        for(int loop = 0 ; loop < size ; loop++){
            int telNumSize = Integer.parseInt(br.readLine());
            Trie trie = new Trie();
            List<String> telNumList = new ArrayList<>();
            for(int i = 0 ; i < telNumSize ; i++){
                String telNum = br.readLine();
                trie.insert(telNum);
                telNumList.add(telNum);
            }
            boolean flag = true;
            for(String tmp : telNumList){
                if(flag){
                    if(!trie.contains(tmp)){
                        System.out.println("NO");
                        flag = false;
                    }
                }
            }
            if(flag) System.out.println("YES");
        }
    }
}