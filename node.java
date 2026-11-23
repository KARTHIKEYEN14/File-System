import java.util.*;

public class node {
    String name;
    String type;
    List<node> children;
    node parent;

    node(String name,String type)
    {
        this.name = name;
        this.type = type;
        children = new ArrayList<>();
        parent = null;
    }

}
