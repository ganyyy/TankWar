import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	//子弹的起始位置
	int x,y;
	//子弹的方向
	Tank.Direction dir;
	//子弹的速度 
	private static final int x_speed = 20,y_speed = 20;
	//拿到窗口引用
	private TankClient tc;
	//设置子弹颜色
	private Color bColor = Color.black;
	//生死标记
	private boolean live = true;
	
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean isLive) {
		this.live = isLive;
	}
	//子弹的大小
	static final int bullet_r = 10; 	
	//构造函数：子弹的起始位置
	public Bullet(int x, int y,Tank.Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	public Bullet(int x, int y,Tank.Direction dir,TankClient tc) {
		this(x, y, dir);
		this.tc = tc;
	}
	//子弹的重绘事件
	public void paint(Graphics g){
		if(!live){
			tc.bullets.remove(this);
			return;
		}
		Color color = g.getColor();
		g.setColor(bColor);
		g.fillOval(x, y, bullet_r, bullet_r);
		g.setColor(color);
		move();
	}
	//子弹的移动事件
	void move(){
		switch (dir) {
		case L:
			x -= x_speed;
			break;
		case R:
			x += x_speed;
			break;
		case U:
			y -= y_speed;
			break;
		case D:
			y += y_speed;
			break;
		case LU:
			x -= x_speed;
			y -= y_speed;
			break;
		case LD:
			x -= x_speed;
			y += y_speed;
			break;
		case RU:
			x += x_speed;
			y -= y_speed;
			break;
		case RD:
			x += x_speed;
			y += y_speed;
			break;
		default:
			break;
		}
		
		//死亡标记条件
		if(x <= 0 || y <= 0 || x >= TankClient.Game_w || y >= TankClient.Game_h){
			this.live = false;
		}
			
	}
	
	//判断
	public Rectangle getRect(){
		return new Rectangle(x, y, bullet_r, bullet_r);
	}
	
	//击中方法
	public boolean hitTank(Tank t){
		if(this.getRect().intersects(t.getRect()) && t.isLive()){
			t.setLive(false);
			this.live = false;
			return true;
		}
		return false;
	}
}
