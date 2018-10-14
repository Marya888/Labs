package src;

public class Results {
    private double x;
    private double y;
    private int r;
    private boolean res;

    public Results(double x, double y, int r, boolean res){
        this.x=x;
        this.y=y;
        this.r=r;
        this.res=res;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public boolean isRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }
}
