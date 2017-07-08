package com.lh.clientBiz;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Sounds {
	public static void playMusic(String url, int seconds){
		try{
			URL cb;
			File f = new File(url);
			cb = f.toURL();
			AudioClip aau;
			aau = Applet.newAudioClip(cb);
				//aau.loop();
			aau.play();
			//aau.stop();
			//System.in.read();
			Thread.sleep(seconds);
			
		}
		catch(MalformedURLException e){
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


