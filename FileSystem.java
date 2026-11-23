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

        if(func.equals("cd"))
        {
            cdExec(arg);
        }

        if(func.equals("ls"))
        {
            lsExec();
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

    private void cdExec(String arg)
    {
        node temp = curr;
        Queue<node> que = new LinkedList<>();
        que.add(temp);
        List<String> dirLs = new ArrayList<>();
        boolean dirFound = false;

        while(que.isEmpty() != true && temp != null)
        {
            temp = que.poll();
            dirLs.add(temp.name);
            if(temp.name.equals(arg))
            {
                dirFound = true;
                curr = temp;
            }

            for(node ele : temp.children)
            {
                if(ele.type.equals("dir"))
                {
                    que.add(ele);
                }
            }
        }

        if(dirFound)
        {
            System.out.print("Current Directory > ");
            for(String ele : dirLs)
            {
                if(ele.equals("\\"))
                {
                    System.out.print("\\"+"home");
                }
                else
                    System.out.print("\\"+ele);
            }
            System.out.println();
        }
    }

    private void lsExec()
    {
        node temp = curr;
        Queue<node> que = new LinkedList<>();
        que.add(temp);
        System.out.println("Type"+" --- "+"Name");
        while(que.isEmpty() != true && temp != null)
        {
            temp = que.poll();
            System.out.println(temp.type+"  --- "+temp.name);
            for(node ele : temp.children)
            {
                que.add(ele);
            }
        }
    }
}