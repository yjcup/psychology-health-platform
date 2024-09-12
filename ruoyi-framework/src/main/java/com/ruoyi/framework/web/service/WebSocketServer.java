package com.ruoyi.framework.web.service;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.OkHttpClient;
import com.ruoyi.framework.web.domain.MessageVo;
import com.ruoyi.system.domain.Message;
import com.ruoyi.system.service.IMessageService;
import com.ruoyi.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sett
 * @date 2023年03月25日 14:28
 * @title
 */
@ServerEndpoint("/websocket/{from}/{to}")
@Component // 此注解千万千万不要忘记，它的主要作用就是将这个监听器纳入到Spring容器中进行管理
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<String, Session> sessionConcurrentHashMap = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private static String WelcomeMessage = "你好,这里是聊天机器人,很高兴为你服务!";

    //两个png
    public static String doctorUrl = "https://online-education02.oss-cn-hangzhou.aliyuncs.com/doctor%EF%BC%8C%E5%8C%BB%E7%94%9F%EF%BC%8C%E4%BA%BA%E7%89%A9%EF%BC%8C%E5%A4%B4%E5%83%8F.png";

    public static String userUrl = "https://online-education02.oss-cn-hangzhou.aliyuncs.com/%E6%82%A3%E8%80%85%E4%BF%A1%E6%81%AF.png";

    public static String RobotURl = "img\\robot.jpg";

    public static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    public static IMessageService iMessageService;


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("from") String from) {

        this.session = session;
            //这边就是从用户端发送过来的
            try {
                WebSocketServer.sendMessageToUser(WelcomeMessage,session);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sessionConcurrentHashMap.put(from, session);
        addOnlineCount();           //在线数加1
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("from") String from) {
        sessionConcurrentHashMap.remove(from);
        subOnlineCount();           //在线数减1
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     *
     *                如果 from 有token 不调用机器人
     *                如果没有就调用聊天机器人
     *
     *
     */
    @OnMessage
    public void onMessage(String message, @PathParam("to") String to, @PathParam("from") String from) {
        Session session = sessionConcurrentHashMap.get(from);
        try {
            Message message1 = new Message();
            message1.setUserId(Long.valueOf(from));
            message1.setFlag("0");
            message1.setContent(message);
            iMessageService.insertMessage(message1);
            String answer = OkHttpClient.sendMessage(message);
            sendMessageToUser(answer,session);
            Message message2 = new Message();
            message2.setUserId(Long.valueOf(from));
            message2.setFlag("1");
            message2.setContent(answer);
            iMessageService.insertMessage(message2);
        } catch (IOException e) {
            logger.warn("消息发送失败");
            e.printStackTrace();
        }
    }

    /**
     * 发生错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }


    //智能客服发送的消息
    public static void sendMessageToUser(String message,Session session) throws IOException {
        MessageVo messageVo = new MessageVo();
        messageVo.setName("智能客服");
        messageVo.setDate(new Date());
        messageVo.setImg(RobotURl);
        Map<String, String> temp = new HashMap<>();
        temp.put("text",message);
        messageVo.setText(temp);
        session.getBasicRemote().sendText(JSON.toJSONString(messageVo));
    }



    //在用户刚介入是需要发送的消息 你好 这里是人工客服 如果还还未预约请点击我去预约




    /**
     * 群发自定义消息
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
