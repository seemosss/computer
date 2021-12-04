public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        LinkedListDeque<Character> q = new LinkedListDeque<Character>();
        for(int i = 0; i < word.length(); i++){
            char d = word.charAt(i);
            q.addLast(d);
        }
        return q;
    }

    public boolean isPalindrome(String word){

    }
}
