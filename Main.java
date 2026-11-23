import java.util.Scanner;

class Main 
{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FileSystem fs = new FileSystem();
        while(true)
        {
            System.out.print("FS >");
            String cmd = in.nextLine();
            if(cmd.equals("Exit") || cmd.equals("exit")) break;
            fs.commandParser(cmd);
        }
        in.close();
    }
}