package com.min;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/*
 * 1.创建窗体
 * 2.设置固定大小和关闭动作
 * 3.绘制圆形
 * 4.圆形动起来 开启一个新线程使用循环不断改变左上角的坐标实现圆形移动 内部类 
 * 5.解决圆形移动闪烁问题  双缓存  虚拟图片
 * 6.将硬编码数值设置成常量  应对需求变化   代码重构   public static final 常量
 * 7.按键控制圆形移动
 * 8.增加100个坦克 
 * 9.让坦克朝8个方向走
 * 9.1 按下键时会设置其移动方向
 * 9.2 在重绘坦克时根据移动方向进行相应的移动·
 * 9.3 添加按键抬起时的事件，取消某个按键状态
 * 10 生成子弹
 * 10.1 添加子弹类 添加x,y,dir等属性和常量。添加构造方法，draw方法等必要方法，根据不同方向，进行不同的运动
 * 10.2 在TankClient中模拟一颗子弹 new一颗子弹出来，画出来
 * 10.3 按住CTRL键，打出子弹
 * 11 使得炮弹从坦克中心打出来,
 * 12 坦克在停止的时候也能打出炮弹
 *  13 使坦克能够打出多发炮弹
 * 13.1 在坦克大战客户端类中创建炮弹容器对象
 * 13.2 每当松开Ctrl键时，产生新炮弹添加到容器中
 * 13.3 在重绘时遍历容器，将炮弹进行重绘
 * 14 解决炮弹不消亡的问题 解决炮弹出界的问题
 * 14.1 设置布尔变量来记录炮弹是否消亡
 * 14.2 当炮弹出界时，从容器中去除该炮弹
 * 14.3 Debug的使用
 * 14.4 当坦克出界时，设置坦克超不出边界
 * 15 画一辆敌人的坦克
 * 15.1 在坦克类中加入good属性来区分是好坦克还是坏坦克
 * 15.2 然後在構造函數中添加good屬性初始化
 * 15.3 在绘制坦克时根据good属性来设置不同的属性
 * 16 将敌人坦克击毙——一颗子弹击中敌人坦克
 * 16.1 Missle中加入hitTank(Tank)方法，返回布尔类型
 * 16.2 碰撞检测的辅助类Rectangle
 * 16.3 为Tank和Missile都加入getRect方法
 * 16.4 当击中敌人坦克时，坦克被打死，子弹也死去
 * 16.5 增加控制Tank生死的量blive
 * 16.6 如果死去就不画了 
 * 
 *  17 加入爆炸
 * 17.1 添加爆炸类
 *      用不同直径的圆模拟爆炸
 *      加入live
 *      加入位置属性
 *      加入draw方法
 * 17.2 爆炸应该存在于集合类中
 * 		TankClient加入集合
 * 		将集合中的爆炸逐一画出（如果死去就去除）
 * 17.3 击毙一辆坦克后应该产生爆炸
 * 		hitTank时应该产生爆炸
 * 空指针出错
 * 18 添加多辆坦克
 * 18.1 创建一个容纳多辆坦克的容器
 * 18.2 将坦克加入到容器中
 * 18.3 在重绘时，判断是否有炮弹打中坦克
 * 
 * 19 让敌方坦克更加智能
 * 19.1 让敌方坦克动起来
 * 		构造函数中可以指定方向
 * 		new 敌军坦克的时候指定敌军坦克的方向
 * 19.2 让敌军坦克向随机方向移动
 * 		(Tank)静态的，添加随机数产生器java.util.Random
 * 		move完成后，如果是敌军坦克的，随机产生一个数据，来设定坦克下一个方向
 * 		Direction.values()
 * 19.3 让敌军坦克向固定方向移动几步后再改变方向
 * 		添加变量，记录随机步骤
 * 		当==0时，改变方向，否则，只是随机步骤递减
 * 19.4 让敌军坦克发射炮弹
 * 		本军炮弹不打本军
 * 		炮弹添加 好坏，根据好坏化不同颜色
 * 		修改炮弹的构造函数
 * 		修改坦克的fire方法
 * 		修改hitTank方法，好不能打好，坏不能打坏
 * 19.5 敌军炮火不能太猛烈
 * 20 添加墙
 * 	墙有位置，墙有draw方法，墙不能被穿透，getRect方法
 * 20.1 建立Wall类 建立Wall对象、画出来
 * 20.2 让每一颗子弹打击每一堵墙
 * 		hitWall()方法
 * 		注意:子弹速度不能太快，否则很容易穿过墙
 * 20.3 让坦克不能穿过墙
 * 		记录上一次的位置oldX,oldY
 * 		修改构造函数
 * 		每次move之前记录上一次位置
 * 		添加stay方法
 * 		记录移动前的位置
 * 		当撞到时回到移动前的位置
 * 		当碰到墙的时候stay
 * 21 坦克不能相互的穿越
 * 
 * 
 * 22 超级炮弹
 * 按键A 坦克在8个方向发射8个炮弹
 * 
 * 23 主战坦克的生命值
 * 为坦克类添加life属性
 * 在炮弹的hitTank方法中修改
 * 
 * 24 图形化显示主站坦克的生命值
 * 根据不同life值显示
 * 定义内部类 血条类
 * 
 * 
 * 25 血块类
 * 25.1 添加blood类
 * 25.2 添加必要的方法
 * 25.3 让blood对象固定轨迹运动，并在一定时间内消失
 * 
 * 26 最后的修正
 * 26.1 敌人死光了重新加入
 * 26.2 我军死掉了F2开始
 * 
 * 
 * 额外—— 添加注释
 * 27.1 生成javadoc文档
 * 27.2 生成jar文件
 * 目前存在的问题
 * 细小的问题
 * 退出时控制线程结束
 * 没有运用专门的game API
 * 主站坦克和机器人(敌人坦克)应当分开控制
 * 界面不漂亮
 * 
 * 网络版  20个左右的版本
 * 图形版 
 * 
 * 27 
 * 
 * */


/**
*@ClassName:Tankwar
*@Description:坦克游戏的主窗口
*@author:minshuaibo
*@date创建时间：2018年9月26日下午1:42:06
*@parameter
*@since
*@return
*/
public class TankClient extends Frame{
	//窗体宽度
	public static final int WINDOW_WIDTH = 800;
	//窗体高度
	public static final int WINDOW_HEIGHT = 600;
	//窗体刷新率
	public static final int REFRESH_RATE = 50;
    
	//自己 的坦克对象
	Tank myTank = new Tank(385, 540,true,Direction.STOP,this);
	
	//绘制两堵墙
	private Wall wallBottom = new Wall(300, 500,200,40,this),	
				 wallLeft = new Wall(60, 200,40,200,this),
				 wallRight = new Wall(700, 200, 40, 200, this);

	//保存爆炸类的容器
	List<Explode> explodes = new ArrayList<Explode>();
	
    //装填子弹的容器
    List<Missile> missiles = new ArrayList<Missile>();
    
    //容纳敌方坦克对象的容器
    List<Tank> enemyTanks = new ArrayList<Tank>();
	
	//实现双缓存的虚拟图像
	Image offScreenImage = null;
	
	//血块类队形
	private Blood blood = new Blood();
	
	/**
	 *
	*@Title: update
	*@Description:  实现双缓存
	* @param g 
	* @see java.awt.Container#update(java.awt.Graphics)
	 */
	public void update(Graphics g) {
		if (offScreenImage==null) {
			offScreenImage = this.createImage(WINDOW_WIDTH,WINDOW_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color color = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		gOffScreen.setColor(color);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	

	/**
	 * 
	*@Title: paint
	*@Description: 重写绘图函数 绘制圆形
	* @param g 
	* @see java.awt.Window#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		
		if (enemyTanks.size() <=0) {
			addEnemies(5);
		}
		
		//绘制字符串，显示当前炮弹的个数
		Color color = g.getColor();
		g.setColor(Color.GREEN);
		/*
		 * 指明当前窗口的炮弹、爆炸、坦克数量以及我方坦克的生命值
		 */
		g.drawString("missiles count:"+missiles.size(), 10, 50);
		g.drawString("explodes count:"+explodes.size(), 10, 70);
		g.drawString("Tanks count:"+enemyTanks.size(), 10, 90);
		g.drawString("MyTank's life:"+myTank.getLife(), 10, 110);
		g.setColor(color);
		//遍历装填子弹的容器，进行绘制
		for (int i = 0; i < missiles.size(); i++) {
			Missile missile  = missiles.get(i);
			missile.hitTanks(enemyTanks);
			missile.hitTank(myTank);
			missile.hitWall(wallBottom);
			missile.hitWall(wallLeft);
			missile.hitWall(wallRight);
//			if (missile.isLive()==false)  missiles.remove(missile);
		    missile.draw(g);			
		}
		
		//遍历保存爆炸对象的容器，进行绘制
		for (int i = 0; i < explodes.size(); i++) {
			Explode explode = explodes.get(i);
			explode.draw(g);
		}
		//绘制血块
		blood.draw(g);
		//遍历保存敌方坦克的容器，进行绘制
		for (int i = 0; i < enemyTanks.size(); i++) {
			Tank tank = enemyTanks.get(i);
			tank.draw(g);
			tank.collideWithWall(wallBottom);
			tank.collideWithWall(wallLeft);
			tank.collideWithWall(wallRight);
			tank.collideWithTank(myTank);
			tank.collideWithTanks(enemyTanks);
		}
		
		
		//坦克类自绘
		myTank.draw(g);	
		myTank.eat(blood);
		myTank.collideWithTanks(enemyTanks);
		wallBottom.draw(g);
		wallLeft.draw(g);
		wallRight.draw(g);
		
		
		
	}

	
	/**
	 *
	* @Title: LaunchFrame 
	* @Description:  设置窗体属性 
	* @return void   
	* @throws
	 */
	private void LaunchFrame() {
		this.setLocation(250,100);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("坦克大战-AWT");
		this.setBackground(Color.BLACK);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyMonitor());
		new Thread(new PaintThread()).start();
		
		addEnemies(8);
		
	}


	/**
	 * 
	* @Title: addEnemies 
	* @Description: 向游戏中添加指定数量的敌方坦克
	* @param enemyNum  
	* @return void   
	* @throws
	 */
	private void addEnemies(int enemyNum) {
		
		for (int i = 0; i < enemyNum; i++) {
			Tank tank = new Tank(50+(i+1)*40,50,false,Direction.DOWN,this);
			enemyTanks.add(tank);			
		}
	}
	public static void main(String[] args) {
		TankClient tankwar = new TankClient();
		tankwar.LaunchFrame();
		
	}
	
	/**
	 * 实现圆形移动 
	* @ClassName: PaintThread 
	* @Description: TODO
	* @author min
	* @date 2018年9月29日 下午9:15:29 
	*
	 */
	private class PaintThread implements Runnable {

		public void run() {
			while(true) {
				//重绘圆形
				repaint();
				try {
					Thread.sleep(REFRESH_RATE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * 键盘监听器
	* @ClassName: KeyMonitor 
	* @Description: TODO
	* @author min
	* @date 2018年9月29日 下午9:51:18 
	*
	 */
	private class KeyMonitor extends KeyAdapter{
		
		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);	
		}

		public void keyPressed(KeyEvent e) {
		    myTank.keyPressed(e);
		}	
	}
	
	
}
