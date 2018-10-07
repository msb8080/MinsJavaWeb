package com.min;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

/**
 *@ClassName:Missile
 *@Description:子弹类
 *@author:minshuaibo
 *@date创建时间：2018年10月1日上午11:21:48
 *@parameter
 *@since
 *@return
 */
public class Missile {

	private int XSPEED = 8;

	private int YSPEED = 8;


	public static final int WIDTH = 10;

	public static final int HEIGHT = 10;

	private int x,y;
	private Direction direction;

	/*
	 * 代表该炮弹的存活状态
	 */
	private boolean live = true;

	private TankClient tankClient;

	public boolean isLive() {
		return live;
	}

	/*
	 * 区分该炮弹是我方还是敌方打出
	 */
	private boolean good;


	public Missile(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public Missile(int x, int y, Direction direction,Boolean good,TankClient tankClient) {
		this(x, y, direction);
		this.good = good;
		this.tankClient = tankClient;
	}

	/**
	 * 
	 * @Title: draw 
	 * @Description: 绘制炮弹自身
	 * @param g  
	 * @return void   
	 * @throws
	 */
	public void draw(Graphics g) {
		//检测炮弹是否已经打中坦克，打中则不再绘制该炮弹,从容器中也移除该炮弹
		if (!live) {
			tankClient.missiles.remove(this);
			return;
		}

		Color color = g.getColor();
		if (good) {
			g.setColor(Color.GREEN);
		}else {
			g.setColor(Color.BLUE);
		}

		g.fillOval(x, y, 10, 10);
		g.setColor(color);

		this.move();
	}

	private void move() {
		switch (direction) {
		case LEFT:
			x -= XSPEED;
			break;
		case LEFTUP:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case UP:
			y -= YSPEED;
			break;
		case RIGHTUP:
			x += XSPEED;
			y -= YSPEED;
			break;
		case RIGHT:
			x += XSPEED;
			break;
		case RIGHTDOWN:
			x += XSPEED;
			y += YSPEED;
			break;
		case DOWN:
			y += YSPEED;
			break;
		case LEFTDOWN:
			x -= XSPEED;
			y += YSPEED;
			break;
		}
		//判断炮弹是否出界
		if (x<0||y<0||x>TankClient.WINDOW_WIDTH||y>TankClient.WINDOW_HEIGHT) {
			live = false;
			tankClient.missiles.remove(this);
		}
	}


	public Rectangle getRectangle() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	/**
	 * 
	 * @Title: hitTank 
	 * @Description: 检测炮弹是否击中了地方坦克
	 * @param tank
	 * @return  
	 * @return boolean   
	 * @throws
	 */
	public boolean hitTank(Tank tank) {
		//如果子弹消亡了，就不再执行碰撞检测操作了，当坦克还存活，并且和子弹区域相交时，则认为炮弹击中了地方坦克，炮弹不能击中相同阵营的坦克
		if (this.live && this.getRectangle().intersects(tank.getRectangle())&& tank.isLive()&&this.good != tank.isGood()) {
			if (tank.isGood()) {
				//先减去20生命值
				tank.setLife(tank.getLife()-20);
				if (tank.getLife()<=0) {
					tank.setLive(false);
				}
			}else {
				tank.setLive(false);
			}
			
			//击中后产生爆炸
			Explode explode = new Explode(x, y, tankClient);
			tankClient.explodes.add(explode);
			this.live = false;
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @Title: hitWall 
	 * @Description: 检测炮弹是否撞击了墙，如果撞到了就消亡
	 * @param wall
	 * @return  
	 * @return boolean   
	 * @throws
	 */
	public boolean hitWall(Wall wall) {
		//为我方坦克提供优先权
		if (this.live && this.getRectangle().intersects(wall.getRectangle())) {
			this.live = false;
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @Title: hitTanks 
	 * @Description: 检测当前炮弹是否击中了当前界面上的敌方坦克中的一辆
	 * @param enemyTanks
	 * @return  
	 * @return boolean   
	 * @throws
	 */
	public boolean hitTanks(List<Tank> enemyTanks) {
		for (int i = 0; i < enemyTanks.size(); i++) {
			if (this.hitTank(enemyTanks.get(i))) {
				return true;
			}
		}	
		return false;
	}




}
