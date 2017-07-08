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
 * �������߳��࣬Ϊ�ͻ��˵������ṩ��Ӧ�ķ���
 * @author 
 *
 */
public class Server extends Thread{
	ServerSocket serverSocket = null;
	//����socket����
	Socket clientSocket = null;
	//���������
	ObjectOutputStream outStream = null;
	// ����������
	ObjectInputStream inStream = null;
	
	@Override
	public void run() {
		System.out.println("��Ʒ���۹���ϵͳ�����������");
		try {
			//����˿ں�
			serverSocket = new ServerSocket(8888);
			while(true){
				//���ܿͻ���
				clientSocket = serverSocket.accept();
				outStream = new ObjectOutputStream(clientSocket.getOutputStream());
				inStream  = new ObjectInputStream(clientSocket.getInputStream());
				//���ܿͻ��˷��͹�������Ϣ
				SysDTO dto = (SysDTO)inStream.readObject();
				
				//����dto��ҵ���ʶִ�в�ͬ��ҵ�������ҵ��ִ����Ϻ󣬸���dto��������ݣ����䵽�ͻ�����ʾ���
				dto = new ServerBiz().dealWithDTO(dto);
				//����������ݷ��ظ��ͻ���
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


