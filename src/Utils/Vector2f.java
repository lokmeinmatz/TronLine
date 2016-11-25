package Utils;

public class Vector2f {
	private float x, y;
	public static Vector2f FORWARD = new Vector2f(1, 0);
	public Vector2f(){
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2f(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float length(){
		return (float) Math.sqrt(Math.pow(this.x, 2)+Math.pow(this.y, 2));
		
	}
	
	
	public static Vector2f add(Vector2f v1, Vector2f v2){
		return new Vector2f(v1.getX()+v2.getX(), v1.getY()+v2.getY());
	}
	
	public static Vector2f subtract(Vector2f v1, Vector2f v2){
		return new Vector2f(v1.getX()-v2.getX(), v1.getY()-v2.getY());
	}
	
	public static Vector2f multiply(Vector2f v1, Vector2f v2){
		return new Vector2f(v1.getX()*v2.getX(), v1.getY()*v2.getY());
	}
	
	public static Vector2f multiply(Vector2f v1, float f){
		return new Vector2f(v1.getX()*f, v1.getY()*f);
	}
	
	public static float getDegree(Vector2f v1, Vector2f v2){
		
		float Skalarprdkt = (v1.getX()*v2.getX())+(v1.getY()*v2.getY());
		
		
		return (float) ( Math.acos(Skalarprdkt/(v1.length()*v2.length()))*(180/Math.PI));
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public float[] getArray(){
		return new float[]{x, y};
	}
	
}
