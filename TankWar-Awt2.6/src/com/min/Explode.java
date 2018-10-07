package com.min;

import java.awt.Color;
import java.awt.Graphics;

/**
*@ClassName:Explode
*@Description:爆炸类
*@author:minshuaibo
*@date创建时间：2018年10月5日下午4:10:36
*@parameter
*@since
*@return
*/
public class Explode {
	
	/*
	 * 爆炸产生的位置左上角坐标
	 */
	int x,y;
	
	/*
	 * 爆炸是否存活
	 */
	private boolean live = true;
	
	
	TankClient tankClient = null;
	
	int[] diameter = {4,7,12,18,26,32,49,30,14,6};
	
	int step = 0;
	
	
	public Explode(int x,int y,TankClient tankClient) {
		this.x = x;
		this.y = y;
		this.tankClient = tankClient;
	}
	
	/**
	* 
	* @Title: draw 
	* @Description: 绘制爆炸图形
	* @return void   
	* @throws
	*/
	public void draw(Graphics g) {
		if (!live) {
			tankClient.explodes.remove(this);
			return;
		}
		if (step == diameter.length) {
			step=0;
			live=false;
			return;
		}
		Color color = g.getColor();
	    g.setColor(Color.ORANGE);
		g.fillOval(x, y, diameter[step], diameter[step]);
		g.setColor(color);
		step++;
		
	}
	
}
