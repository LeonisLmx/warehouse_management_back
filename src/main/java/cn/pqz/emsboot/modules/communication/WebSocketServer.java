package cn.pqz.emsboot.modules.communication;

import cn.pqz.emsboot.modules.communication.entity.Message;
import cn.pqz.emsboot.modules.communication.entity.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date: 2019/1/11 11:48
 * @Description: websocket 服务类
 */

/**
 *
 * @ServerEndpoint 这个注解有什么作用？
 *
 * 这个注解用于标识作用在类上，它的主要功能是把当前类标识成一个WebSocket的服务端
 * 注解的值用户客户端连接访问的URL地址
 *
 */

@Slf4j
@Component
@ServerEndpoint(value = "/ws/asset/{name}",
        encoders = {ServerEncoder.class})
public class WebSocketServer {

    /**
     *  与某个客户端的连接对话，需要通过它来给客户端发送消息
     */
    private Session session;

    /**
     * 标识当前连接客户端的用户名
     */
    private String name;

    /**
     *  用于存所有的连接服务的客户端，这个对象存储是安全的
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<>();


    @OnOpen
    public void OnOpen(Session session, @PathParam(value = "name") String name){
        this.session = session;
        this.name = name;
        // name是用来表示唯一客户端，如果需要指定发送，需要指定发送通过name来区分
        webSocketSet.put(name,this);
        CountPerson("C,"+String.valueOf(webSocketSet.size()));
        log.info(name+"连接成功，当前连接人数为：={}",webSocketSet.size());
    }
    @OnClose
    public void OnClose(){
        webSocketSet.remove(this.name);
        CountPerson(String.valueOf(webSocketSet.size()));
        log.info(name+"退出成功，当前连接人数为：={}",webSocketSet.size());
    }

    @OnMessage
    public void OnMessage(String message){
//        log.info("[WebSocket] 收到消息：{}",message.split(",")[2]);
        //判断是否需要指定发送，具体规则自定义
        if(message.indexOf("TOUSER") == 0){
            String name = message.substring(message.indexOf("TOUSER")+6,message.indexOf(";"));
            System.out.println(name);
            AppointSending(name,message.substring(message.indexOf(";")+1,message.length()));
        }else{
            GroupSending(message);
        }

    }

    /**
     * 群发
     * @param message
     */
    public void GroupSending(String message){
        for (String name : webSocketSet.keySet()){
            try {
                MessageUtil messageUtil=new MessageUtil();
                Message msg=messageUtil.toMessage(message);
                webSocketSet.get(name).session.getBasicRemote().sendObject(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void CountPerson(String count){
        for (String name : webSocketSet.keySet()){
            try {
                webSocketSet.get(name).session.getBasicRemote().sendText(count);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 指定发送
     * @param name
     * @param message
     */
    public void AppointSending(String name,String message){
        try {
            webSocketSet.get(name).session.getBasicRemote().sendText(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}