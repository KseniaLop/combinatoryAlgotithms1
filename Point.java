public class Point {


    private int x_coordinate;
    private int y_coordinate;
    private int index;

    public Point(int x, int y, int point_index){
        x_coordinate = x;
        y_coordinate = y;
        index = point_index;
    }

    public int get_x_coordinate(){
        return (x_coordinate);
    }

    public int get_y_coordinate(){
        return (y_coordinate);
    }

    public int get_index(){
        return(index);
    }

    public boolean equals(Point p) {
        return (this.get_x_coordinate() == p.get_x_coordinate() && this.get_y_coordinate() == p.get_y_coordinate());
    }

}
