import java.io.*;
import java.util.*;

public class JuiceMaker {
    protected ArrayList <Juice> juiceToMake;
    protected TreeSet <String> diffComp;
    protected ArrayList <ArrayList <Integer> > g;
    protected int n;
    protected ArrayList <Integer> mt;
    protected ArrayList <Boolean> used;
    JuiceMaker()
    {
        Compare comp = new Compare();
        diffComp = new TreeSet <String> (comp);
        juiceToMake = new ArrayList <Juice> ();
        try
        {
            File f = new File("juice.in");
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine())
            {
                String s = sc.nextLine();
                Juice j = new Juice(s);
                boolean same = false;
                for (Juice  al: juiceToMake)
                {
                    if (al.components.equals(j.components))
                    {
                        same = true;
                        break;
                    }
                }
                if (!same)
                {
                    juiceToMake.add(j);
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Unable to open \"juice.in\"");
        }
    }
    public void work()
    {
        PrintWriter writer;
        try
        {
            writer = new PrintWriter(new File("juice1.out"));
            for (Juice j: juiceToMake)
            {
                for (String compString: j.components)
                {
                    if (!diffComp.contains(compString))
                    {
                        writer.println(compString);
                        diffComp.add(compString);
                    }
                }
            }
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            writer = new PrintWriter(new File("juice2.out"));
            for (String s: diffComp)
            {
                writer.println(s);
            }
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            writer = new PrintWriter(new File("juice3.out"));
            createGraph();
            Kuhn();
            int kol = 0;
            for (int i = 0; i < n; ++i)
            {
                if (mt.get(i).equals(-1))
                {
                    kol++;
                }
            }
            writer.println(kol);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    protected  void createGraph()
    {
        n = juiceToMake.size();
        g = new ArrayList <ArrayList <Integer> > (n);
        mt = new ArrayList <Integer> (n);
        used = new ArrayList <Boolean> (n);
        for (int i = 0; i < n; ++i)
        {
            g.add(new ArrayList <Integer> ());
            mt.add(-1);
            used.add(false);
        }

        for (int i = 0; i < n; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (j != i && Juice.isInside(juiceToMake.get(i), juiceToMake.get(j)))
                {
                    g.get(i).add(j);
                }
            }
        }
    }
    protected boolean tryKuhn(Integer v)
    {
        if (used.get(v))
        {
            return false;
        }
        used.set(v, true);
        for (int i = 0; i < g.get(v).size(); ++i)
        {
            int to = g.get(v).get(i);
            if (mt.get(to) == -1 || tryKuhn(mt.get(to)))
            {
                mt.set(to, v);
                return true;
            }
        }
        return false;
    }
    protected void Kuhn()
    {
        for (int v = 0; v < n; ++v)
        {
            for (int i = 0; i < n; ++i)
            {
                used.set(i, false);
            }
            tryKuhn(v);
        }
    }
}
