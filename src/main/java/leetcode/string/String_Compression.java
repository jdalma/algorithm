package leetcode.string;

class String_Compression {
    public int compress(char[] chars) {

        if (chars == null || chars.length == 0) return 0;

        int index = 0 , i = 0 , n = chars.length;
        while (i < n) {
            char ch = chars[i];
            int j = i;
            while (j < n && chars[i] == chars[j]) { // chars[i..j - 1] are ch.
                j++;
            }
            int diff = j - i;
            chars[index++] = ch;
            if (diff != 1) {
                if (diff < 10) {
                    chars[index++] = (char)(diff + '0');
                }
                else {
                    String strFreq = String.valueOf(diff);
                    for (char chFreq : strFreq.toCharArray()) {
                        chars[index++] = chFreq;
                    }
                }
            }
            i = j;
        }

        return index;
    }
}
