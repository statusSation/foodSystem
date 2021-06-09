package com.food.api.service;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.food.utils.RedisUtil;

@ServerEndpoint(value = "/webStocket/{usrId}")
@Component
public class WebStocketService {

	public static RedisUtil redisUtil;

	@PostConstruct
	public void init() {
		//System.out.println("websocket 加载");
	}

	private static Logger log = LoggerFactory.getLogger(WebStocketService.class);
	private static final AtomicInteger OnlineCount = new AtomicInteger(0);
	// concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
	private static ConcurrentHashMap<String, Session> sessionSet = new ConcurrentHashMap<>();
	@SuppressWarnings("unused")
	private Session session;
	private String usrId = "";

	/**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("usrId") String usrId) {
    	this.session = session;
    	this.usrId = usrId;
    	session.setMaxIdleTimeout(300000);
    	JSONObject json = new JSONObject();
    	if(sessionSet.containsKey(usrId)) {
    		json.put("code",600);
    		json.put("msg","你在其他地方已经登录，请重新登录!");
    		json.put("data","");
    		SendMessage(sessionSet.get(usrId),json.toString());
    		sessionSet.remove(usrId);
    		sessionSet.put(usrId, session);
    	}else {
    		sessionSet.put(usrId, session);
    	}
        int cnt = OnlineCount.incrementAndGet(); // 在线数加1
        log.info("有连接加入，当前连接数为：{}", cnt);
        SendMessage(session, "连接成功");
    }

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		if(sessionSet.containsKey(usrId)) {
			sessionSet.remove(usrId);
		};
		int cnt = OnlineCount.decrementAndGet();
		log.info("有连接关闭，当前连接数为：{}", cnt);
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("来自客户端的消息：{}", message);
		SendMessage(session, "收到消息，消息内容：" + message);
	}

	/**
	 * 出现错误
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
		error.printStackTrace();
	}

	/**
	 * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
	 * 
	 * @param session
	 * @param message
	 */
	public static void SendMessage(Session session, String message) {
		try {
//            session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)",message,session.getId()));
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			log.error("发送消息出错：{}", e.getMessage());
		}
	}
	
	public  boolean SendMessageToSomeOne(String usrId, String message) {
		try {
//            session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)",message,session.getId()));
			if(sessionSet.containsKey(usrId)) {
				sessionSet.get(usrId).getBasicRemote().sendText(message);
				return true;
			}else {
				log.info("SendMessageToSomeOne异常:没有找到对应用户的session");
			}
			
		} catch (IOException e) {
			log.error("发送消息出错：{}", e.getMessage());
		}
		return false;
	}

	/**
	 * 群发消息
	 * 
	 * @param message
	 * @throws IOException
	 */
	public static void BroadCastInfo(String message) throws IOException {
		for (Session session : sessionSet.values()) {
			if (session.isOpen()) {
				SendMessage(session, message);
			}
		}
	}

	/**
	 * 指定Session发送消息
	 * 
	 * @param sessionId
	 * @param message
	 * @throws IOException
	 */
	public static void SendMessage(String message, String sessionId) throws IOException {
//		log.info("发送sessionSet:" + sessionSet);
//		for (Session s : sessionSet) {
//			if (s.getId().equals(sessionId)) {
//				session = s;
//				break;
//			}
//		}
//		if (session != null) {
//			SendMessage(session, message);
//		} else {
//			log.warn("没有找到你指定ID的会话：{}", sessionId);
//		}
	}
}
