package lab;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;;

/**
 * 图形界面.
 * @author LuLiu
 *
 */
public class Gui {
    /**
     * 输出文本区域.
     */
    private JTextArea outText;
    /**
     * 输入文本区域.
     */
    private JTextField inText;
    /**
     * 图.
     */
    private Graph gra = new Graph();

    /**
     * frame的宽度.
     */
    private final int frameWidth = 350;
    /**
     * frame的高度.
     */
    private final int frameHeight = 450;
    /**
     * 按钮的高度.
     */
    private final int buttonHeight = 25;
    /**
     * 按钮的宽度80.
     */
    private final int buttonWidth0 = 80;
    /**
     * 按钮的宽度100.
     */
    private final int buttonWidth1 = 100;
    /**
     * button0的横坐标.
     */
    private final int buttonX0 = 120;
    /**
     * button0的纵坐标.
     */
    private final int buttonY0 = 20;
    /**
     * 常数10.
     */
    private final int conC10 = 10;
    /**
     * 常数60.
     */
    private final int conC60 = 60;
    /**
     * 常数80.
     */
    private final int conC80 = 80;
    /**
     * 常数25.
     */
    private final int conC25 = 25;
    /**
     * 常数100.
     */
    private final int conC100 = 100;
    /**
     * 常数20.
     */
    private final int conC20 = 20;
    /**
     * 常数180.
     */
    private final int conC180 = 180;
    /**
     * 常数40.
     */
    private final int conC40 = 40;
    /**
     * 常数140.
     */
    private final int conC140 = 140;
    /**
     * 常数230.
     */
    private final int conC230 = 230;
    /**
     * 常数345.
     */
    private final int conC345 = 345;
    /**
     * 常数170.
     */
    private final int conC170 = 170;
    /**
     * main函数.
     * @param args 参数
     */
    public static void main(final String[] args) {
        Gui gui = new Gui();
        gui.prepareGUI();
    }

    /**
     * 构造图形界面.
     */
    private void prepareGUI() {

        JFrame frame = new JFrame("实验一");

        frame.setSize(frameWidth, frameHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口尺寸

        JPanel panel = new JPanel();

        frame.add(panel);
        panel.setLayout(null);

        JButton jButton0 = new JButton("open"); // 添加open按钮，用来选择文件
        jButton0.setBounds(buttonX0, buttonY0, buttonWidth0, buttonHeight);
        jButton0.setActionCommand("open");
        jButton0.addActionListener(new ButtonClickListener());
        panel.add(jButton0);

        JLabel jLabel0 = new JLabel("输入"); // 添加输入窗口

        jLabel0.setBounds(conC10, conC60, conC80, conC25);
        panel.add(jLabel0);

        inText = new JTextField(conC20);
        inText.setBounds(conC60, conC60, conC230, conC25);
        panel.add(inText);

        JButton jButton1 = new JButton("生成图"); // 添加生成图按钮
        jButton1.setBounds(conC40, conC100, buttonWidth1, buttonHeight);
        jButton1.setActionCommand("生成图");
        jButton1.addActionListener(new ButtonClickListener());
        panel.add(jButton1);

        JButton jButton2 = new JButton("查询桥接词"); // 添加查询桥接词按钮
        jButton2.setBounds(conC180, conC100, buttonWidth1, buttonHeight);
        jButton2.setActionCommand("查询桥接词");
        jButton2.addActionListener(new ButtonClickListener());
        panel.add(jButton2);

        JButton jButton3 = new JButton("生成新文本"); // 添加生成新文本按钮
        jButton3.setBounds(conC40, conC140, buttonWidth1, buttonHeight);
        jButton3.setActionCommand("生成新文本");
        jButton3.addActionListener(new ButtonClickListener());
        panel.add(jButton3);

        JButton jButton4 = new JButton("最短路径"); // 添加最短路径按钮
        jButton4.setBounds(conC180, conC140, buttonWidth1, buttonHeight);
        jButton4.setActionCommand("最短路径");
        jButton4.addActionListener(new ButtonClickListener());
        panel.add(jButton4);

        JButton jButton5 = new JButton("随机游走"); // 添加随机游走按钮
        jButton5.setBounds(conC40, conC180, buttonWidth1, buttonHeight);
        jButton5.setActionCommand("随机游走");
        jButton5.addActionListener(new ButtonClickListener());
        panel.add(jButton5);

        JButton jButton6 = new JButton("clear"); // 添加clear按钮
        jButton6.setBounds(conC180, conC180, buttonWidth1, buttonHeight);
        jButton6.setActionCommand("clear");
        jButton6.addActionListener(new ButtonClickListener());
        panel.add(jButton6);

        outText = new JTextArea(); // 添加文本输出区域
        outText.setLineWrap(true);
        outText.setEditable(false);
        outText.setWrapStyleWord(true);

        JScrollPane jsp = new JScrollPane(outText); // 添加滚动条
        jsp.setBounds(0, conC230, conC345, conC170);
        panel.add(jsp);

        frame.setVisible(true);
    }

    /**
     * 事件监听器.
     * @author JiabinLiu
     *
     */
    private class ButtonClickListener implements ActionListener {
        /**
         * 实现actionPerformed方法.
         * @param e 事件
         */
        public void actionPerformed(final ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("open")) { // 打开文件功能

                FileDialog fd = new FileDialog(new JFrame(),
                                    "选择文件", FileDialog.LOAD); // 生成打开文件窗口
                fd.setFile("*.txt");
                fd.setVisible(true);
                String filename = fd.getFile(); // 取得文件名
                if (filename != null) {
                    gra.setFilename(fd.getDirectory() + fd.getFile());
                    // 取得文件完整路径
                    outText.setText("文件打开成功！\n" + gra.readInFile());
                }
            } else if (command.equals("生成图")) {
                if (gra.getFilename() == null) { // 未选择文件
                    outText.setText("未选择文件！");
                } else {
                    outText.setText("生成图成功！图片以保存在桌面。");
                    gra.showDirectedGraph(gra); // 展示图
                }

            } else if (command.equals("查询桥接词")) {
                if (gra.getFilename() == null) {
                 // 未选择文件
                    outText.setText("未选择文件！");
                } else {
                    String[] strx = inText.getText()
                            .replaceAll("[^a-zA-Z]", " ")
                            .toLowerCase().split("\\s+"); // 分割最小化字符串
                    if (strx.length != 2) {
                     // 如果长度不为2，输入错误
                        outText.setText("输入错误！");
                    } else {
                    // 输出桥接词
                        outText.setText(gra.queryBridgeWords(strx[0], strx[1]));
                    }

                }
            } else if (command.equals("生成新文本")) {
                if (gra.getFilename() == null) {
                 // 未选择文件
                    outText.setText("未选择文件！");
                } else {
                    String strx = inText.getText();
                    if ((strx.split("\\s+").length < 2)
                            && (strx.split("\\s+").length > 0)) {
                     // 单词数小于2，错误
                        outText.setText("输入错误！");
                    } else {
                        outText.setText(gra.generateNewText(strx)); // 输出生成的新文本
                    }
                }
            } else if (command.equals("最短路径")) {
                String[] strx = inText.getText().
                            replaceAll("[^a-zA-Z]", " ").
                            toLowerCase().split("\\s+"); // 分割最小化字符串
                if (gra.getFilename() == null) {
                 // 未选择文件
                    outText.setText("未选择文件！");
                } else {
                    if (strx.length > 2 | inText.getText() == null) {  // 单词大于2
                        outText.setText("输入错误！");
                    } else if (strx.length == 2) {
                     // 2个单词的情况
                        outText.setText(gra.calcShortestPath(strx[0], strx[1]));
                    } else if (strx.length == 1) {
                     // 1个单词的情况
                        outText.setText(gra.calcShortestPath(strx[0]));
                    }
                }
            } else if (command.equals("随机游走")) {
                if (gra.getFilename() == null) {
                 // 未选择文件
                    outText.setText("未选择文件！");
                } else {
                    outText.setText(gra.randomWalk());
                }
            } else { // 清除输入输出文本区域
                outText.setText("");
                inText.setText("");
            }
        }
    }

}
// liulu and qiaozhi lab1
