package hulu;

import java.awt.Color;

import javax.swing.Timer;

public abstract class Creature extends Thing {
    final long timeInterval = 250;
    private int targetX, targetY;
    private int startX, startY;
    private int nextX;
    private int nextY;
    private int currentStep;
    private int currentStep_y;
int length=2;//向上移动的步数，确保不越界
    Creature(Color color, char glyph, World world) {
        super(color, glyph, world);
    }

    public void moveTo(int xPos, int yPos) {
        // 保存起始和目标位置
        startX = this.getX();
        startY = this.getY();
        targetX = xPos;
        targetY = yPos;

        int totalSteps = Math.max(Math.abs(targetX - startX), Math.abs(targetY - startY));
        currentStep = 0;
        currentStep_y = 1;
        Runnable runnable = new Runnable() {

            public void run() {

                while (currentStep < totalSteps + length) {
                    // System.out.println("Hello,world");

                    // 计算下一步位置
                    if (currentStep_y <= length && currentStep == 0) {
                        nextX = startX;
                        if (targetX > startX)
                            nextY = (startY += currentStep_y);
                        else
                            nextY = (startY -= currentStep_y);
                        performSingleMove(nextX, nextY);
                        currentStep_y++;
                    }
                    if (currentStep_y > length && (currentStep != totalSteps)) {
                        nextX = calculateNextCoordinate(startX, targetX, currentStep + 1);
                        nextY = calculateNextCoordinate(startY, targetY, currentStep + 1);
                        performSingleMove(nextX, nextY);
                        currentStep++;
                    }
                    if (currentStep_y > length && currentStep_y <= 2*length && currentStep == totalSteps) {
                        nextX = targetX;
                        if (targetX > startX)
                            nextY = targetY+length - (currentStep_y-length);
                        else
                            nextY = targetY-length+(currentStep_y - length);
                        performSingleMove(nextX, nextY);
                        currentStep_y++;
                    }

                    world.requestRepaint();
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        // this.world.put(this,xPos,yPos);

    }


    public void moveTo_timer(int xPos, int yPos) {
    startX = this.getX();
    startY = this.getY();
    targetX = xPos;
    targetY = yPos;

    int totalSteps = Math.max(Math.abs(targetX - startX), Math.abs(targetY - startY));
    currentStep = 0;
     currentStep_y = 1;
    Timer timer = new Timer(300, e -> { // 固定150ms间隔
        if (currentStep >= totalSteps+length) {
            ((Timer)e.getSource()).stop();
            return;
        }
 if (currentStep_y <= length && currentStep == 0) {
                        nextX = startX;
                        if (targetX > startX)
                            nextY = (startY += currentStep_y);
                        else
                            nextY = (startY -= currentStep_y);
                        performSingleMove(nextX, nextY);
                        currentStep_y++;
                    }
                    if (currentStep_y > length && (currentStep != totalSteps)) {
                        nextX = calculateNextCoordinate(startX, targetX, currentStep + 1);
                        nextY = calculateNextCoordinate(startY, targetY, currentStep + 1);
                        performSingleMove(nextX, nextY);
                        currentStep++;
                    }
                    if (currentStep_y > length && currentStep_y <= 2*length && currentStep == totalSteps) {
                        nextX = targetX;
                        if (targetX > startX)
                            nextY = targetY+length - (currentStep_y-length);
                        else
                            nextY = targetY-length+(currentStep_y - length);
                        performSingleMove(nextX, nextY);
                        currentStep_y++;
                    }

                    world.requestRepaint();
    });
    
    timer.start();
}
//java swing 实现中间过程

    private int calculateNextCoordinate(int start, int target, int step) {
        int distance = Math.abs(target - start);
        if (step <= distance) {
            return start + (int) Math.signum(target - start) * step;
        } else {
            return target;
        }
    }

    private void performSingleMove(int nextX, int nextY) {// 执行单步移动
        int currentX = this.getX();
        int currentY = this.getY();

        // 在当前位置放置草坪
        this.world.put(new Grass(this.world), currentX, currentY);

        // 移动到新位置
        this.world.put(this, nextX, nextY);
        // System.out.println("移动到: (" + nextX + ", " + nextY + ")");
    }
}