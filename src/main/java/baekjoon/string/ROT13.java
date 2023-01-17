package baekjoon.string;

import java.io.*;

class ROT13 {
    static char[] lowerVowel = { 'a', 'i', 'y', 'e', 'o', 'u' };
    static char[] lowerConsonant = { 'b', 'k', 'x', 'z', 'n', 'h', 'd', 'c', 'w', 'g', 'p', 'v', 'j', 'q', 't', 's',
            'r', 'l', 'm', 'f' };
    static char[] upperVowel = { 'A', 'I', 'Y', 'E', 'O', 'U' };
    static char[] upperConsonant = { 'B', 'K', 'X', 'Z', 'N', 'H', 'D', 'C', 'W', 'G', 'P', 'V', 'J', 'Q', 'T', 'S',
            'R', 'L', 'M', 'F' };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line;
        while ((line = br.readLine()) != null) {
            StringBuilder result = new StringBuilder();
            char[] input = line.toCharArray();
            for (char ch : input) {
                char after = ' ';
                if (Character.isAlphabetic(ch)) {
                    // 모음
                    if (isVowel(ch)) {
                        if (Character.isUpperCase(ch)) {
                            after = convert(upperVowel, ch, 3);
                        } else {
                            after = convert(lowerVowel, ch, 3);
                        }
                    }
                    // 자음
                    else {
                        if (Character.isUpperCase(ch)) {
                            after = convert(upperConsonant, ch, 10);
                        } else {
                            after = convert(lowerConsonant, ch, 10);
                        }
                    }
                } else
                    after = ch;
                result.append(after);
            }
            result.append("\n");
            bw.append(result.toString());
            bw.flush();
        }
        bw.close();
        br.close();
    }

    public static boolean isVowel(char ch) {
        // 모음이라면 true
        char c = Character.toLowerCase(ch);
        for (int i = 0; i < lowerVowel.length; i++) {
            if (c == lowerVowel[i])
                return true;
        }
        return false;
    }

    public static char convert(char[] arr, char ch, int plusIndex) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (ch == arr[i]) {
                index = i;
                break;
            }
        }

        int sumIndex = index + plusIndex;
        if (sumIndex > arr.length - 1) {
            sumIndex = sumIndex - arr.length;
        }

        return arr[sumIndex];
    }
}
