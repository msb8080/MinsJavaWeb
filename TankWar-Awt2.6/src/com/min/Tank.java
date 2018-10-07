package com.min;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

/**
 *@ClassName:Tank
 *@Description:坦克类
 *@author:minshuaibo
 *@date创建时间：2018年9月30日上午10:09:41
 *@parameter
 *@since
 *@return
 */
public class Tank {

	public static final int WIDTH = 30;

	public static final int HEIGHT = 30;

	/*
	 * 坦克x轴方向上的移动速度
	 */
	private int XSPEED = 5;
	/*
	 * 坦克y轴方向上的移动速度
	 */
	private int YSPEED = 5;


	/*
	 * 该坦克所属的坦克客户端界面
	 */
	private TankClient tankClient;



	//记录按键状态的布尔量
	private boolean isL=false,  /*是否为左方向*/
			isU =false,	/*是否为上方向*/
			isR=false,	/*是否为右方向*/
			isD = false;	/*是否为下方向*/

	/*
	 * 坦克移动的方向
	 */
	private Direction direction;


	/*
	 * 坦克的炮筒方向
	 * 默认为向下
	 */
	private Direction barrelDirection = Direction.DOWN;

	/*
	 * 坦克的x,y值
	 */
	private int x,y;

	/*
	 * 表示坦克是否为好坦克
	 */
	private boolean good;

	public boolean isGood() {
		return good;
	}

	/**
	 * 坦克的轮廓
	 */

	public Rectangle getRectangle() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	/*
	 * 代表该坦克当前的存活状态
	 */
	private boolean live = true;

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	/*
	 * 用于产生敌方坦克移动的随机方向的随机数产生器
	 */
	private static Random random = new Random();

	/*
	 * 设置敌方坦克在某个方向上移动的步数，初始步数由随机数生成器产生
	 */
	private int step = random.nextInt(12)+3; 

	/*
	 * 记录坦克上一次的位置
	 */
	private int oldX,oldY;
	
	/*
	 * 坦克的生命值
	 */
	private int life = 100;

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	
	private BloodBar bloodBar = new BloodBar();
	
	/**
	 * 
	 * <p>Title: </p> 
	 * <p>Description: 坦克类的构造函数</p> 
	 * @param x
	 * @param y
	 */
	public Tank(int x, int y,boolean good) {
		this.x = x;
		this.y = y;
		this.good = good;
	}

	/**
	 * 
	 * <p>Title: </p> 
	 * <p>Description: 坦克的构造函数重载</p> 
	 * @param x
	 * @param y
	 * @param good
	 * @param direction
	 * @param tankClient
	 */
	public Tank(int x,int y,boolean good,Direction direction,TankClient tankClient) {
		this(x, y,good);
		this.direction = direction;
		this.tankClient = tankClient;
	}



	/**
	 * 
	 * @Title: draw 
	 * @Description: 坦克类自绘
	 * @param g  画笔对象
	 * @return void   
	 * @throws
	 */
	public void draw(Graphics g) {
		//如果坦克不存活，则不再绘制坦克
		if (!live)  {
			if (!good) {
				tankClient.enemyTanks.remove(this);
			}
			return;
		}
		if (this.good) {
			bloodBar.draw(g);
		}
		Color color =g.getColor();
		if(good) g.setColor(Color.RED);
		else g.setColor(Color.BLUE);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(color);
		this.barrelCreate(g);

		this.move();
	}

	/**
	 * 
	 * @Title: barrelDirectionLocate 
	 * @Description: 根据坦克移动的方向产生对应方向的炮筒
	 * @return void   
	 * @throws
	 */
	private void barrelCreate(Graphics g) {
		switch (barrelDirection) {
		case LEFT:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x, y+Tank.HEIGHT/2);
			break;
		case LEFTUP:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x, y);
			break;
		case UP:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x + Tank.WIDTH/2, y);
			break;
		case RIGHTUP:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x + Tank.WIDTH, y);
			break;
		case RIGHT:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x + Tank.WIDTH, y+Tank.HEIGHT/2);
			break;
		case RIGHTDOWN:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2,  x + Tank.WIDTH, y+Tank.HEIGHT);
			break;
		case DOWN:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x + Tank.WIDTH/2, y+Tank.HEIGHT);
			break;
		case LEFTDOWN:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x, y+Tank.HEIGHT);
			break;
		}
	}



	/**
	 * 
	 * @Title: move 
	 * @Description: 根据坦克移动的方向改变坦克的位置值,并且根据移动方向调整枪筒的方向
	 * @return void   
	 * @throws
	 */
	private void move() {
		oldX = x;
		oldY = y;
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
		case STOP:
			break;
		}

		//如果坦克在移动中，将坦克的移动方向和炮筒方向保持一致
		if (this.direction != Direction.STOP) {
			barrelDirection = direction;
		}

		//当坦克移动至边界时，进行判断使得坦克不能越出边界
		if ( x < 0 ) x = 0;
		if(y < 30) y = 30;
		if(x > tankClient.WINDOW_WIDTH - Tank.WIDTH)  x = tankClient.WINDOW_WIDTH-Tank.WIDTH;
		if (y > tankClient.WINDOW_HEIGHT - Tank.HEIGHT) y = tankClient.WINDOW_HEIGHT-Tank.HEIGHT;

		//当坦克是敌方坦克时，其不能通过按键操作移动方向，所以使用随机数来改变其移动方向
		if (!good) {
			Direction[] directions =  direction.values();
			if (step==0) { //当移动了设定的步数后，改变方向，再进行移动
				int randomNum = random.nextInt(directions.length);
				direction = directions[randomNum];
				step = random.nextInt(12)+3;
			}
			//控制敌方炮弹发射的频率
			if (random.nextInt(40)>38) {
				this.fire();	
			}
			step--;	
		}


	}
	/**
	 * 
	 * @Title: fire 
	 * @Description: 坦克开火，产生子弹
	 * @return  
	 * @return Missile   
	 * @throws
	 */
	public Missile fire() {
		//当坦克死亡之后，就不能再发炮弹了
		if (!live) {
			return null;
		}
		int missileX = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int missileY = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
		Missile missile = new Missile(missileX, missileY,barrelDirection, good,tankClient);
		tankClient.missiles.add(missile);
		return missile; 
	}

	/**
	 * 
	* @Title: fire 
	* @Description: 根据自定义的方向打出一个炮弹
	* @param direction
	* @return  
	* @return Missile   
	* @throws
	 */
	public Missile fire(Direction direction) {
		//当坦克死亡之后，就不能再发炮弹了
		if (!live) {
			return null;
		}
		int missileX = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int missileY = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
		Missile missile = new Missile(missileX, missileY,direction, good,tankClient);
		tankClient.missiles.add(missile);
		return missile; 
	}

	/**
	 * 
	* @Title: superFire 
	* @Description: 超级开火，向8个方向分别打出一发炮弹
	* @return  
	* @return Missile   
	* @throws
	 */
	public void superFire() {
		Direction[] directions = Direction.values();
		for (int i = 1; i < directions.length; i++) {
			Direction direction = directions[i];
			fire(direction);
		}
	}




	/**
	 * @Title: keyPressed 
	 * @Description: 根据按键信息记录按键的状态
	 * @param e  
	 * @return void   
	 * @throws
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {

		case KeyEvent.VK_LEFT:
			isL = true;
			break;
		case KeyEvent.VK_RIGHT:
			isR = true;
			break;
		case KeyEvent.VK_UP:
			isU = true;
			break;
		case KeyEvent.VK_DOWN:
			isD = true;
			break;
		}
		locateDirection();
	}

	/**
	 * 
	 * @Title: locateDirection 
	 * @Description: 根据按键状态确定坦克的移动方向
	 * @return void   
	 * @throws
	 */
	private void locateDirection() {
		if (isL && !isU && !isR && !isD) direction = Direction.LEFT;
		else if(isL && isU && !isR && !isD) direction = Direction.LEFTUP;
		else if(!isL && isU && !isR && !isD) direction = Direction.UP;
		else if(!isL && isU && isR && !isD) direction = Direction.RIGHTUP;
		else if(!isL && !isU && isR && !isD) direction = Direction.RIGHT;
		else if(!isL && !isU && isR && isD) direction = Direction.RIGHTDOWN;
		else if(!isL && !isU && !isR && isD) direction = Direction.DOWN;
		else if(isL && !isU && !isR && isD) direction = Direction.LEFTDOWN;
		else if(!isL && !isU && !isR && !isD) direction = Direction.STOP;
	}

	/**
	 * 
	 * @Title: keyReleased 
	 * @Description: 根据松开的键，设置其相应的状态为false
	 * @param e  
	 * @return void   
	 * @throws
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_F2:
			if (!live) {
				life  = 100;
				live = true;
			}
			break;
		case KeyEvent.VK_CONTROL:
			//产生子弹，并装入到容器中
			fire();
			break;
		case KeyEvent.VK_LEFT:
			isL = false;
			break;
		case KeyEvent.VK_RIGHT:
			isR = false;
			break;
		case KeyEvent.VK_UP:
			isU = false;
			break;
		case KeyEvent.VK_DOWN:
			isD = false;
			break;
		case KeyEvent.VK_A:
			superFire();
			break;
		}
		locateDirection();
	}

	private void stay() {
		x = oldX;
		y = oldY;
	}




	/**
	 * 
	 * @Title: hitWall 
	 * @Description: 检测坦克是否撞击了墙,如果撞击到了，则使得坦克返回上一个位置
	 * @param wall
	 * @return  
	 * @return boolean   
	 * @throws
	 */
	public boolean collideWithWall(Wall wall) {
		if (this.live && this.getRectangle().intersects(wall.getRectangle())) {
			this.stay();
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @Title: collideWithTank 
	 * @Description: 检测两辆坦克是否相撞
	 * @param tank
	 * @return  
	 * @return boolean   
	 * @throws
	 */
	public boolean collideWithTank(Tank tank) {
		if (this.live && tank.live && this.getRectangle().intersects(tank.getRectangle())) {
			this.stay();
			tank.stay();
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @Title: collideWithTanks 
	 * @Description: 检测当前坦克和所有的其他坦克是否相撞
	 * @param enemyTanks
	 * @return  
	 * @return boolean   
	 * @throws
	 */
	public boolean collideWithTanks(List<Tank> enemyTanks) {
		for (int i = 0; i < enemyTanks.size(); i++) {
			Tank tank = enemyTanks.get(i);
			if (this != tank) {
				if(this.collideWithTank(tank)) {
					return true;
				}
			}
		}
		return false;

	}
	
	public boolean eat(Blood blood) {
		//当坦克和血块都存活时，坦克才能吃血块
		if (this.live && blood.isLive() && this.getRectangle().intersects(blood.getRectangle())) {
			this.life = 100;
			blood.setLive(false);
			return true;
		}

		return false;
	}
	
	
	
	/**
	 * 
	* @ClassName: BloodBar 
	* @Description: 血条类
	* @author min
	* @date 2018年10月7日 上午11:13:00 
	*
	 */
	private class BloodBar{
		
		/**
		 * 
		* @Title: draw 
		* @Description: 根据坦克的life画出相应的血条
		* @param g  
		* @return void   
		* @throws
		 */
		public void draw(Graphics g) {
			Color color = g.getColor();
			g.setColor(Color.MAGENTA);
			g.draw3DRect(x, y-10, WIDTH, 9, false);
//			g.drawRect(x, y-10, WIDTH, 9);
			int lifeWidth = WIDTH * life / 100;
			g.fill3DRect(x, y-10, lifeWidth, 9, true);
//			g.fillRect(x, y-10, lifeWidth, 9);
			g.setColor(color);
		}
	}

}
