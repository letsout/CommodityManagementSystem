package com.lh.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.lh.serverBiz.ServerBiz;
import com.lh.util.FlagType;
import com.lh.util.SysDTO;

/**
 * 服务器线程类，为客户端的请求提供对应的服务
 * @author 
 *
 */
public class Server extends Thread{
	ServerSocket serverSocket = null;
	//声明socket对象
	Socket clientSocket = null;
	//声明输出流
	ObjectOutputStream outStream = null;
	// 声明输入流
	ObjectInputStream inStream = null;
	
	@Override
	public void run() {
		System.out.println("商品零售管理系统服务端启动！");
		try {
			//定义端口号
			serverSocket = new ServerSocket(8888);
			while(true){
				//接受客户端
				clientSocket = serverSocket.accept();
				outStream = new ObjectOutputStream(clientSocket.getOutputStream());
				inStream  = new ObjectInputStream(clientSocket.getInputStream());
				//接受客户端发送过来的消息
				SysDTO dto = (SysDTO)inStream.readObject();
				
				//根据dto的业务标识执行不同的业务操作，业务执行完毕后，更新dto对象的数据，传输到客户端显示结果
				dto = new ServerBiz().dealWithDTO(dto);
				//将处理的数据发回给客户端
				outStream.writeObject(dto);
				
				outStream.flush();
				clientSocket.shutdownOutput();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}


