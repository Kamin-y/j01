// 导入必要的包
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;
import hulu.World;

/**
 * 主应用程序类，继承自JFrame并实现KeyListener接口
 * 用于创建游戏窗口和处理键盘输入
 */
public class Main extends JFrame implements KeyListener {
 private static Main instance;
    // 使用AsciiPanel作为终端显示组件，用于字符图形显示
    private AsciiPanel terminal;
    
    // 当前活动的屏幕（界面）
    private Screen screen;

    /**
     * 构造函数 - 初始化应用程序窗口和组件
     */
    public Main() {
        super(); // 调用父类JFrame的构造函数
        instance=this;
        // 创建AsciiPanel终端，尺寸基于World类的宽度和高度常量
        // 使用HACK_64_64字体（64x64像素的等宽字体）
        terminal = new AsciiPanel(World.WIDTH, World.HEIGHT, AsciiFont.HACK_64_64);
        
        // 将终端组件添加到JFrame中
        add(terminal);
        
        // 自动调整窗口大小以适应终端组件
        pack();
        
        // 初始化屏幕为WorldScreen（游戏世界界面）
        screen = new WorldScreen();
        
        // 添加键盘监听器，使窗口能够接收键盘事件
        addKeyListener(this);
        
        // 触发初始绘制
        repaint();
    }

     public static void requestRepaint() {
        if (instance != null) {
            instance.repaint();
        }
    }
    /**
     * 重写repaint方法，自定义绘制逻辑
     * 每次重绘时清空终端并显示当前屏幕的内容
     */
    @Override
    public void repaint() {
        terminal.clear(); // 清空终端内容
        screen.displayOutput(terminal); // 让当前屏幕输出内容到终端
        super.repaint(); // 调用父类的repaint方法完成实际绘制
    }

    /**
     * KeyListener接口方法 - 键被键入时调用
     * 此处为空实现，不处理此事件
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // 不处理键键入事件
    }

    /**
     * KeyListener接口方法 - 键被按下时调用
     * 将用户输入传递给当前屏幕并更新屏幕状态
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // 将键盘输入传递给当前屏幕，并获取可能的屏幕切换结果
        screen = screen.respondToUserInput(e);
        
        // 重绘界面以反映可能的状态变化
        repaint();
    }

    /**
     * KeyListener接口方法 - 键被释放时调用
     * 此处为空实现，不处理此事件
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // 不处理键释放事件
    }

    /**
     * 主方法 - 应用程序入口点
     */
    public static void main(String[] args) {
        // 创建主应用程序实例
        Main app = new Main();
        
        // 设置关闭操作 - 点击关闭按钮时退出程序
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 设置窗口可见
        app.setVisible(true);
    }
}