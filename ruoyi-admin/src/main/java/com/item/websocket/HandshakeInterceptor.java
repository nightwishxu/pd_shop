//package com.item.websocket;
//
//import com.base.security.shiro.Principal;
//import com.base.security.util.UserUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
//
//import javax.servlet.http.HttpSession;
//import java.util.Map;
//
//public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
//	private static final Logger logger = LoggerFactory.getLogger(HandshakeInterceptor.class);
//    @Override
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
//    }
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes)
//            throws Exception {
//        if (request instanceof ServletServerHttpRequest) {
//            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
//            HttpSession session = servletRequest.getServletRequest().getSession(false);
//            if (session != null) {
//            	Principal principal = UserUtils.getDefaultPrincipal();
//                if(principal != null){
//                	String sys = principal.getGroupCode();
//                	Integer id = principal.getId();
//                    attributes.put(WebSocketConfig.DEFAULT_WEBSOCKET_KEY,sys+"_"+id);
//                    logger.debug("用户["+sys+"_"+id+"]成功注册websocket...");
//                }
//            }
//        }
//        return true;
//    }
//
//}
