import java.util.Comparator;

public class Compare implements Comparator<String> {
    public int compare(String s1, String s2) {
        int length = Math.min(s1.length(), s2.length());
        for (int i = 0; i < length; ++i)
        {
            if (s1.charAt(i) < s2.charAt(i))
            {
                return -1;
            }
            else
            {
                if (s1.charAt(i) > s2.charAt(i))
                {
                    return 1;
                }
            }
        }
        return Integer.compare(s1.length(), s2.length());
    }
}
