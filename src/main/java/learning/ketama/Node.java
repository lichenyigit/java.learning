package learning.ketama;

/**
 * @author Lichenyi
 * @date 2017-7-10
 */
public class Node {

    Node(String name){
        this.name = name;
    }

    private String name;
    private String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
