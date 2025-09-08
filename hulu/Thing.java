package hulu;

import java.awt.Color;

/**
 * Thing类表示世界中的基本物体/实体
 * 是所有可放置在Tile上的对象的基类
 */
public class Thing {

    protected World world;  // 物体所属的世界引用
    public Tile tile;       // 物体所在的Tile引用

    /**
     * 获取物体当前的X坐标
     * @return 物体所在Tile的x坐标
     */
    public int getX() {
        return this.tile.getxPos();
    }

    /**
     * 获取物体当前的Y坐标
     * @return 物体所在Tile的y坐标
     */
    public int getY() {
        return this.tile.getyPos();
    }

    /**
     * 设置物体所在的Tile
     * 建立物体与Tile之间的关联关系
     * @param tile 要设置的Tile对象
     */
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    /**
     * Thing类的构造函数
     * @param color 物体的颜色
     * @param glyph 物体的字符表示（图形符号）
     * @param world 物体所属的世界
     */
    Thing(Color color, char glyph, World world) {
        this.color = color;
        this.glyph = glyph;
        this.world = world;
    }

    // 物体的颜色属性，使用final修饰确保一旦设置不可更改
    private final Color color;

    /**
     * 获取物体的颜色
     * @return 物体的Color对象
     */
    public Color getColor() {
        return this.color;
    }

    // 物体的字符图形符号，使用final修饰确保一旦设置不可更改
    private final char glyph;

    /**
     * 获取物体的字符表示（图形符号）
     * @return 表示物体的字符
     */
    public char getGlyph() {
        return this.glyph;
    }
}