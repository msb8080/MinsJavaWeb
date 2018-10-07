package com.min;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
*@ClassName:Blood
*@Description:血块类
*@author:minshuaibo
*@date创建时间：2018年10月7日上午10:55:11
*@parameter
*@since
*@return
*/
public class Blood {
	int x,y,width,height;
	
	private TankClient tankClient;
	
	private boolean live = true;
	
	public boolean isLive() {
		return live;
	}



	public void setLive(boolean live) {
		this.live = live;
	}

	private int[][] positions = {
			{300,150},{300,160},{300,170},{300,180},{300,190},{300,200},{300,210},{300,220},{300,230},
			{300,230},{300,220},{300,210},{300,200},{300,190},{300,180},{300,170},{300,160},{250,150},{200,150}
	};
	
	/**
	 * 血块移动的步数
	 */
	private int step = 0;
	
	
	/**
	 * 
	* <p>Title: </p> 
	* <p>Description: 血块类的构造函数</p> 
	* @param x
	* @param y
	* @param width
	* @param height
	* @param tankClient
	 */
	public Blood() {
		this.x = positions[0][0];
		this.y = positions[0][1];
		this.width = 15;
		this.height = 15;
	}

	
	
	/**
	 * 
	* @Title: draw 
	* @Description: 血块类的绘制函数
	* @param graphics  
	* @return void   
	* @throws
	 */
	public void draw(Graphics graphics) {
		if (!live) {
			return ;
		}
		Color color = graphics.getColor();
		graphics.setColor(Color.ORANGE);
		graphics.fill3DRect(x, y, width, height, true);
		graphics.setColor(color);
		
		this.move();
	}


	/**
	 * 
	* @Title: move 
	* @Description: 更新血块下一次要移动的位置  
	* @return void   
	* @throws
	 */
	private void move() {
		step ++;
		if (step == positions.length) {
					step = 0;
		}
		this.x = positions[step][0];
		this.y = positions[step][1];
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
	
	
}
