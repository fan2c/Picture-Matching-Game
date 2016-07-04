/**
 * create map by use algorithm
 * @author fan2c
 *
 */
import java.awt.Point;
import java.util.Random;
import java.util.Vector;

public class map {
	private static int map[][];
	private static Vector vector;
	private int level;
	int array[];
	private static int block;
	
	
	public map()
	{
		level = 16;
		array=new int[level*4];
		map = new int[10][17];
	}
	public map(int level)
	{
		this.level = level;
		array= new int[level*4];
		map = new int[10][17];
		block = level*4;		
		
	}
	
	/* set map */
    public void setMap(int numMap){
    	if(numMap==1){   //for default  map
    		
    		map[0][6]=1;map[0][7]=1;map[0][8]=1;map[0][9]=1;map[0][10]=1;map[0][11]=1;map[0][12]=1;
    		map[2][6]=1;map[2][9]=1;map[2][10]=1;
    		map[4][6]=1;;map[4][9]=1;map[4][10]=1;
    		map[6][6]=1;map[6][9]=1;map[6][10]=1;
    		map[8][6]=1;map[8][7]=1;map[8][8]=1;map[8][9]=1;map[8][10]=1;map[8][11]=1;map[8][12]=1;map[8][13]=1;
    	}
    	else if(numMap==2)   //for love map
    	{
    		map[0][2]=1;map[0][3]=1;map[0][4]=1;map[0][10]=1;map[0][11]=1;map[0][12]=1;map[0][13]=1;map[0][14]=1;
    		map[1][2]=1;map[1][3]=1;map[1][4]=1;map[1][9]=1;map[1][10]=1;map[1][11]=1;map[1][15]=1;
    		map[2][2]=1;map[2][3]=1;map[2][4]=1;map[2][9]=1;map[2][10]=1;map[2][14]=1;map[2][15]=1;
    		map[3][2]=1;map[3][3]=1;map[3][4]=1;map[3][7]=1;map[3][8]=1;map[3][9]=1;map[3][13]=1;map[3][14]=1;
    		map[4][2]=1;map[4][3]=1;map[4][4]=1;map[4][5]=1;map[4][6]=1;map[4][7]=1;map[4][8]=1;map[4][10]=1;map[4][11]=1;map[4][12]=1;map[4][13]=1;map[4][14]=1;map[4][15]=1;map[4][16]=1;
    		map[5][2]=1;map[5][3]=1;map[5][4]=1;map[5][7]=1;map[5][8]=1;map[5][10]=1;map[5][11]=1;map[5][12]=1;map[5][15]=1;//map[5][16]=1;
    		map[6][3]=1;map[6][4]=1;map[6][7]=1;map[6][10]=1;map[6][11]=1;map[6][12]=1;map[6][14]=1;map[6][16]=1;
    		map[7][3]=1;map[7][4]=1;map[7][5]=1;map[7][6]=1;map[7][10]=1;map[7][11]=1;map[7][12]=1;map[7][13]=1;map[7][14]=1;
    		map[8][4]=1;map[8][5]=1;map[8][6]=1;map[8][10]=1;map[8][11]=1;map[8][12]=1;map[8][14]=1;
    		map[9][5]=1;map[9][10]=1;map[9][11]=1;map[9][12]=1;map[9][13]=1;map[9][15]=1;map[9][16]=1;
    	}
    	else if(numMap==3)  //for heart map
    	{
    		map[0][4]=1;map[0][5]=1;map[0][11]=1;map[0][12]=1;
    		map[1][2]=1;map[1][3]=1;map[1][6]=1;map[1][7]=1;map[1][8]=1;map[1][9]=1;map[1][10]=1;map[1][11]=1;map[1][12]=1;map[1][13]=1;map[1][14]=1;
    		map[2][1]=1;map[2][8]=1;map[2][15]=1;
    		map[3][1]=1;map[3][15]=1;
    		map[4][1]=1;map[4][2]=1;map[4][15]=1;
    		map[5][2]=1;map[5][3]=1;map[5][14]=1;
    		map[6][3]=1;map[6][4]=1;map[6][12]=1;map[6][13]=1;
    		map[7][5]=1;map[7][11]=1;
    		map[8][5]=1;map[8][8]=1;map[8][9]=1;map[8][10]=1;
    		map[9][5]=1;map[9][6]=1;map[9][7]=1;map[9][8]=1;
    	}
    	
    }
	
	
	
	/*create a random array  */
	public void createArray()
	{
		for(int i=0;i<level;i++)
		{
			array[i * 4] = i + 1; //same number 4time
			array[i * 4 + 1] = i + 1;
			array[i * 4 + 2] = i + 1;
			array[i * 4 + 3] = i + 1;
		}
		random(array);
	 }
	
	public void initMap()
	{
		int count=0;
		for(int i = 0; i < 10; i++)
	    	for(int j = 0; j < 17; j++)
	    {
	        if(map[i][j]==1){
	    	  map[i][j] = array[count];
	    	  count++;
	        }
	    }
		
		
	}
	
	/* array shuffle*/
	private void random(int array[])
	{
		Random random = new Random();
	    for(int i = array.length; i > 0; i--)
	    {
	    	int j = random.nextInt(i);
	    	int temp = array[j];
	        array[j] = array[i - 1];
	        array[i - 1] = temp;
	    }
	}
	
	/* to clean the map's point*/
	public static void cleanBlock(Point a, Point b)
    {
        map[a.x][a.y] = 0;
        map[b.x][b.y] = 0;
        block -= 2;
    }
	public int block()
	{
		return block;
	}
	
	/*  game algorithm */
	/*  fist case: two point in a same line */
	/*  in the horizon: check whether null among two point */
	 private static boolean horizon(Point a, Point b, boolean recorder)
	    {
	        if(a.x == b.x && a.y == b.y)
	            return false;
	        int x_start = a.y <= b.y ? a.y : b.y;
	        int x_end = a.y <= b.y ? b.y : a.y;
	        for(int x = x_start + 1; x < x_end; x++)
	            if(map[a.x][x] != 0)
	                return false;

	      
	        return true;
	    }
	 /*  in the vertical: check whether null among two point */
	    private static boolean vertical(Point a, Point b, boolean recorder)
	    {
	        if(a.x == b.x && a.y == b.y)
	            return false;
	        int y_start = a.x <= b.x ? a.x : b.x;
	        int y_end = a.x <= b.x ? b.x : a.x;
	        for(int y = y_start + 1; y < y_end; y++)
	            if(map[y][a.y] != 0)
	                return false;

	    
	        return true;
	    }
  
	    /*  second case: need two line can be clean---->find the point C and D   */
	    private static boolean oneCorner(Point a, Point b)
	    {
	        Point c = new Point(a.x, b.y);
	        Point d = new Point(b.x, a.y);
	        if(map[c.x][c.y] == 0)
	        {
	            boolean method1 = horizon(a, c, false) && vertical(b, c, false);
	            if(method1)
	            {
	    
	                return method1;
	            }
	        }
	        if(map[d.x][d.y] == 0)
	        {
	            boolean method2 = vertical(a, d, false) && horizon(b, d, false);
	       
	            return method2;
	        } else
	        {
	            return false;
	        }
	    }

	    private static Vector scan(Point a, Point b)
	    {
	        Vector v = new Vector();
	        Point c = new Point(a.x, b.y);
	        Point d = new Point(b.x, a.y);
	        for(int y = a.y; y >= 0; y--)
	            if(map[a.x][y] == 0 && map[b.x][y] == 0 && vertical(new Point(a.x, y), new Point(b.x, y), false))
	                v.add(new Line(0, new Point(a.x, y), new Point(b.x, y)));

	        for(int y = a.y; y < 17; y++)
	            if(map[a.x][y] == 0 && map[b.x][y] == 0 && vertical(new Point(a.x, y), new Point(b.x, y), false))
	                v.add(new Line(0, new Point(a.x, y), new Point(b.x, y)));

	        for(int x = a.x; x >= 0; x--)
	            if(map[x][a.y] == 0 && map[x][b.y] == 0 && horizon(new Point(x, a.y), new Point(x, b.y), false))
	                v.add(new Line(1, new Point(x, a.y), new Point(x, b.y)));

	        for(int x = a.x; x < 10; x++)
	            if(map[x][a.y] == 0 && map[x][b.y] == 0 && horizon(new Point(x, a.y), new Point(x, b.y), false))
	                v.add(new Line(1, new Point(x, a.y), new Point(x, b.y)));

	        return v;
	    }
     /* third case : need three line can be clean ---->find a line among two point (use vector save)*/
	    private static boolean twoCorner(Point a, Point b)
	    {
	        vector = scan(a, b);
	        if(vector.isEmpty())
	            return false;
	        for(int index = 0; index < vector.size(); index++)
	        {
	            Line line = (Line)vector.elementAt(index);
	            if(line.direct == 1)
	            {
	                if(vertical(a, line.a, false) && vertical(b, line.b, false))
	                {
	         
	                    return true;
	                }
	            } else
	            if(horizon(a, line.a, false) && horizon(b, line.b, false))
	            {
	        
	                return true;
	            }
	        }

	        return false;
	    }
        
	
	 /* judge two point whether can be clean by using algorithm */ 
	 public static boolean test(Point a, Point b)
	 {
	     if(map[a.x][a.y] != map[b.x][b.y])
	         return false;
	     if(a.x == b.x && horizon(a, b, true))
	         return true;
	     if(a.y == b.y && vertical(a, b, true))
	         return true;
	     if(oneCorner(a, b))
	         return true;
	     else
	         return twoCorner(a, b);
	}
	
	/* return map */
	public static int[][] getMap()
	{
		return map;
	}

	/*
	//for test map 
	public static void main(String[] args){
		map m = new map();
		//m.initMap();
		
		
		for(int i=0;i<m.getMap().length;i++)
			for(int j=0;j<17;j++){
				System.out.print(" "+m.getMap()[i][j]);
				if((j+1)%17==0)
					System.out.println("");
			}
		
	}
	
	
	*/
	
	
}





