package com.min;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
*@ClassName:Wall
*@Description:	游戏中出现的墙
*@author:minshuaibo
*@date创建时间：2018年10月6日下午4:12:04
*@parameter
*@since
*@return
*/
public class Wall {
	
	//由于墙的宽高，每次可能都不一样，所以不能设置为常量
	private int x,y,width,height;
	//该墙所属的客户端界面
	private TankClient tankClient;
	
	
	public Wall(int x, int y, int width, int height, TankClient tankClient) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tankClient = tankClient;
	}

	/**
	 * 
	* @Title: draw 
	* @Description: 绘制墙
	* @param g  
	* @return void   
	* @throws
	 */
	public void draw(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.PINK);
		g.fill3DRect(x, y, width, height, true);
//		g.fillRoundRect(this.x, this.y, width, height, 20, 20);
		g.setColor(color);
	}
	
	/**
	 * 
	* @Title: getRectangle 
	* @Description: 获取墙所在位置的区域对象
	* @return  
	* @return Rectangle   
	* @throws
	 */
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
	
	
}
