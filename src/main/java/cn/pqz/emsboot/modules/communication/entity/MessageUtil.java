package cn.pqz.emsboot.modules.communication.entity;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageUtil {

//    private static List<Message> messageList = new CopyOnWriteArrayList<>();
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public Message toMessage(String content){
        Message message = new Message();
        int id = atomicInteger.incrementAndGet();
        message.setId(id);
        String[] contents=content.split(",");
        String name=contents[0];
        String userface=contents[1];
        String text=contents[2];
        message.setUsername(name);
        message.setUserface(userface);
        message.setText(text);
        message.setDate(new Date());

        return message;
    }
}
