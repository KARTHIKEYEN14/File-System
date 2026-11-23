import java.util.*;

class FileSystem
{
    node root;
    node curr;
    FileSystem()
    {
        root = new node("\\","dir");
        curr = root;
    }

    public void commandParser(String cmd)
    {
        String[] commands = cmd.split(" ");
        String func = commands[0];
        String arg = "";

        if(commands.length > 1)
        {
            arg = commands[1];
        }

        if(func.equals("pwd"))
        {
            pwdExec(curr);
        }

        if(func.equals("mkdir"))
        {
            mkdirExec(arg);
        }
    }

    private void pwdExec(node curr)
    {
        List<String> ls = new ArrayList<>();
        pwdHelper(curr,ls);

        for(int ind = ls.size()-1 ; ind >= 0 ; ind--)
        {
            if(ls.get(ind) == "\\")
            {
                System.out.print("\\"+"home"+"\\");
            }
            else
            {
                System.out.print(ls.get(ind)+ "\\");
            }
        }
        System.out.println();
    }

    private void pwdHelper(node curr, List<String> ls)
    {
        if(curr == null) return;
        ls.add(curr.name);
        pwdHelper(curr.parent, ls);
    }

    private void mkdirExec(String arg)
    {
        node nDir = new node(arg,"dir");
        nDir.parent = curr;
        curr.children.add(nDir);
        System.out.println("Directory added");
    }
}