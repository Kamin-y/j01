package hulu;

public class Tile {

    // 成员变量
    private Thing thing;  // 当前Tile上放置的物体（Thing对象）
    private int xPos;     // Tile在网格中的x坐标（列位置）
    private int yPos;     // Tile在网格中的y坐标（行位置）

    /**
     * 获取当前Tile上的物体
     * @return 当前Tile上的Thing对象，如果没有物体则返回null
     */
    public Thing getThing() {
        return thing;
    }

    /**
     * 在当前Tile上放置物体
     * 这个方法不仅设置Tile的thing属性，还会设置物体的tile属性，建立双向关联
     * @param thing 要放置的Thing对象
     */
    public void setThing(Thing thing) {
        this.thing = thing;          // 设置当前Tile的物体引用
        this.thing.setTile(this);    // 设置物体的tile属性指向当前Tile，建立双向关联
    }

    /**
     * 获取Tile的x坐标（列位置）
     * @return x坐标值
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * 设置Tile的x坐标（列位置）
     * @param xPos 新的x坐标值
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * 获取Tile的y坐标（行位置）
     * @return y坐标值
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * 设置Tile的y坐标（行位置）
     * @param yPos 新的y坐标值
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * 默认构造函数
     * 创建一个位置为(-1,-1)的Tile，表示无效位置
     * 通常用于临时对象或尚未确定位置的Tile
     */
    public Tile() {
        this.xPos = -1;  // 设置无效的x坐标
        this.yPos = -1;  // 设置无效的y坐标
    }

    /**
     * 带参数的构造函数
     * 创建具有指定位置的Tile
     * @param xPos x坐标（列位置）
     * @param yPos y坐标（行位置）
     */
    public Tile(int xPos, int yPos) {
        this.xPos = xPos;  // 设置x坐标
        this.yPos = yPos;  // 设置y坐标
    }
}