public class Application
{
    static Editor window;
    public static void main(String[] args)
    {
        FileManager.instance = new FileManager();

        window = new Editor();
    }
}
