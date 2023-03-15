package servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckcodeServlet1 extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] arr = {"0","1","2","3","4","5","6","7","8","9"};
		boolean[] isused = new boolean[arr.length];
		BufferedImage image = new BufferedImage(60,20,BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random r = new Random();
		g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
		g.fillRect(0, 0, 60, 20);
		int count = 0;
		String number = "";
		while(true) {
			int rand = r.nextInt(arr.length);
			if(!isused[rand]) {
				number += arr[rand];
				isused[rand] = true;
				count++;
			}
			if(count == 5)
				break;
		}
		//将Number绑定到session对象上
		HttpSession session = request.getSession();
		session.setAttribute("number", number);
		g.setColor(new Color(0,0,0));
		g.drawString(number, 5, 15);
		for(int i = 0; i < 2; i++) {
			g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
			g.drawLine(r.nextInt(60), r.nextInt(20), r.nextInt(60), r.nextInt(20));
		}
		response.setContentType("image/jpeg");
		OutputStream output = response.getOutputStream();
		javax.imageio.ImageIO.write(image, "jpeg", output);
		
	}
}
