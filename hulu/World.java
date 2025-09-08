package hulu;

import java.awt.Color;

public class World {

    // 定义世界的宽度和高度常量
    public static final int WIDTH = 22;
    public static final int HEIGHT = 7;

    // 二维数组，表示世界中的每个格子
    private Tile[][] tiles;

    // 葫芦兄弟数组
    private Hulu[] bros;

    // 排序步骤数组
    private String[] sortSteps;

    // 构造函数 - 初始化世界
    public World() {
        // 初始化格子数组
        tiles = new Tile[WIDTH][HEIGHT];

        // 为每个格子创建Tile对象
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }

        // 在世界中每个格子上放置草地
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                this.put(new Grass(this), i, j);
            }
        }

        // 初始化葫芦兄弟数组（7个兄弟）
        bros = new Hulu[7];

        // 创建并初始化7个葫芦兄弟，每个有不同的颜色和排名
        // 注意：这里不是按顺序初始化的，而是打乱顺序的
        bros[3] = new Hulu(new Color(204, 0, 0), 1, this);    // 红色，排名1
        bros[5] = new Hulu(new Color(255, 165, 0), 2, this);  // 橙色，排名2
        bros[1] = new Hulu(new Color(252, 233, 79), 3, this); // 黄色，排名3
        bros[0] = new Hulu(new Color(78, 154, 6), 4, this);   // 绿色，排名4
        bros[4] = new Hulu(new Color(50, 175, 255), 5, this); // 青色，排名5
        bros[6] = new Hulu(new Color(114, 159, 207), 6, this); // 蓝色，排名6
        bros[2] = new Hulu(new Color(173, 127, 168), 7, this); // 紫色，排名7

        // 将葫芦兄弟放置到世界中的特定位置（排成一行）
        this.put(bros[0], 4, 3);  // 绿色葫芦娃放在(4,3)
        this.put(bros[1], 6, 3);  // 黄色葫芦娃放在(6,3)
        this.put(bros[2], 8, 3);  // 紫色葫芦娃放在(8,3)
        this.put(bros[3], 10, 3); // 红色葫芦娃放在(10,3)
        this.put(bros[4], 12, 3); // 青色葫芦娃放在(12,3)
        this.put(bros[5], 14, 3); // 橙色葫芦娃放在(14,3)
        this.put(bros[6], 16, 3); // 蓝色葫芦娃放在(16,3)

        // 创建冒泡排序器并加载葫芦兄弟数组
        BubbleSorter b = new BubbleSorter();
        b.load(bros);
        // 执行排序操作
        b.sort();

        // 解析排序计划（步骤）
        sortSteps = this.parsePlan(b.getPlan());
    }

    // 解析排序计划字符串，按换行符分割成步骤数组
    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    // 执行下一步排序操作
    public void next(int step) {
        // 从排序步骤中获取当前步骤的交换对（格式如 "1<->2"）
        String[] couple = this.sortSteps[step].split("<->");
        // 获取需要交换的两个葫芦兄弟
        Hulu bro1 = getBroByRank(bros, Integer.parseInt(couple[0]));
        Hulu bro2 = getBroByRank(bros, Integer.parseInt(couple[1]));
        // 执行交换操作
        bro1.swap(bro2);
    }

    // 根据排名在葫芦兄弟数组中查找对应的葫芦娃
    private Hulu getBroByRank(Hulu[] bros, int rank) {
        for (Hulu bro : bros) {
            if (bro.getRank() == rank) {
                return bro;
            }
        }
        return null; // 如果没有找到，返回null
    }

    // 获取所有排序步骤
    public String[] steps() {
        return this.sortSteps;
    }

    // 获取葫芦兄弟数组
    public Hulu[] getBros() {
        return this.bros;
    }

    // 获取指定位置的物体
    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    // 在指定位置放置物体
    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }
     public void requestRepaint() {
        // 调用Main的静态方法
        try {
            Class.forName("Main").getMethod("requestRepaint").invoke(null);
        } catch (Exception e) {
            System.out.println("重绘调用失败: " + e.getMessage());
        }
    }
}