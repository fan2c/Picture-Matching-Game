/**
 * for case 3 use line (algorithm)
 */

import java.awt.Point;

public class line
{

    public Point a;
    public Point b;
    public int direct;

    public line()
    {
    }

    public line(int direct, Point a, Point b)
    {
        this.direct = direct;
        this.a = a;
        this.b = b;
    }
}