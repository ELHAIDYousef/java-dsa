package javaBasics.charFrequency;

public class Main {
    public static void main(String[] args) {

    }
    static int charCount(String s, char character) {
        int counter = 0;

        for (int i = 0; i < s.length(); i++)
            if(s.charAt(i) == character)
                counter++;

        return counter;
    }
}
