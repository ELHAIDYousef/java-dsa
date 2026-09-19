package javaBasics.target;

public class Main {
    public static void main(String[] args) {

    }

    static boolean contains(int[] tab, int target){
        for (int i = 0; i < tab.length; i++) {
            if (target == tab[i]) return true;
        }
        return false;


        // or easily we use Arrays.binarySearch(tab, target) after sort it with Arrays.sort(tab);
    }

}
