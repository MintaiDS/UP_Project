import java.util.*;

public class Juice {
    protected ArrayList <String> components;
    Juice (String s)
    {
        components = new ArrayList <String> ();
        StringTokenizer st = new StringTokenizer(s, " ");
        while (st.hasMoreTokens())
        {
            components.add(st.nextToken());
        }
    }
    protected static boolean isInside(Juice j1, Juice j2)
    {
        return j2.components.containsAll(j1.components);
    }
}
