package aop.afterReturn;

public class MessageWriter {
    public String write() {
        System.out.println("lava");
        return "write";
    }
}
