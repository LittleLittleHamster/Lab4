package lab;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * 图形界面.
 * @author LuLiu
 *
 */
public class CreateGui {
    /**
     * 输出文本区域.
     */
    private transient JTextArea outText;
    /**
     * 输入文本区域.
     */
    private transient JTextField inText;
    /**
     * 图.
     */
    private final transient Graph gra = new Graph();

    /**
     * frame的宽度.
     */
    private static final int FRAMEWIDTH = 350;
    /**
     * frame的高度.
     */
    private static final int FRAMEHEIGHT = 450;
    /**
     * 按钮的高度.
     */
    private static final int BUTTONHEIGHT = 25;
    /**
     * 按钮的宽度80.
     */
    private static final int BUTTONWIDTH0 = 80;
    /**
     * 按钮的宽度100.
     */
    private static final int BUTTONWIDTH1 = 100;
    /**
     * button0的横坐标.
     */
    private static final int BUTTONX0 = 120;
    /**
     * button0的纵坐标.
     */
    private static final int BUTTONY0 = 20;
    /**
     * 常数10.
     */
    private static final int CONC10 = 10;
    /**
     * 常数60.
     */
    private static final int CONC60 = 60;
    /**
     * 常数80.
     */
    private static final int CONC80 = 80;
    /**
     * 常数25.
     */
    private static final int CONC25 = 25;
    /**
     * 常数100.
     */
    private static final int CONC100 = 100;
    /**
     * 常数20.
     */
    private static final int CONC20 = 20;
    /**
     * 常数180.
     */
    private static final int CONC180 = 180;
    /**
     * 常数40.
     */
    private static final int CONC40 = 40;
    /**
     * 常数140.
     */
    private static final int CONC140 = 140;
    /**
     * 常数230.
     */
    private static final int CONC230 = 230;
    /**
     * 常数345.
     */
    private static final int CONC345 = 345;
    /**
     * 常数170.
     */
    private static final int CONC170 = 170;
    /**
     * 常数1.
     */
    private static final int ANTEMP = 1;
    /**
     * 常数2.
     */
    private static final int TEMPTWO = 2;
    /**
     * 字符串常量.
     */
    private static final String NOTCHOOSEFILE = "未选择文件！";
    /**
     * 字符串常量，正则表达式.
     */
    private static final String REGSPACE = "\\s+";
    /**
     * main函数.
     * @param args 参数
     */
    public static void main(final String[] args) {
        final CreateGui gui = new CreateGui();
        gui.prepareGUI();
    }

    /**
     * 构造图形界面.
     */
    private void prepareGUI() {

        final JFrame frame = new JFrame("实验一");

        frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口尺寸

        final JPanel panel = new JPanel();

        frame.add(panel);
        panel.setLayout(null);

        final JButton jButton0 = new JButton("open"); // 添加open按钮，用来选择文件
        jButton0.setBounds(BUTTONX0, BUTTONY0, BUTTONWIDTH0, BUTTONHEIGHT);
        jButton0.setActionCommand("open");
        jButton0.addActionListener(new ButtonClickListener());
        panel.add(jButton0);

        final JLabel jLabel0 = new JLabel("输入"); // 添加输入窗口

        jLabel0.setBounds(CONC10, CONC60, CONC80, CONC25);
        panel.add(jLabel0);

        inText = new JTextField(CONC20);
        inText.setBounds(CONC60, CONC60, CONC230, CONC25);
        panel.add(inText);

        final JButton jButton1 = new JButton("生成图"); // 添加生成图按钮
        jButton1.setBounds(CONC40, CONC100, BUTTONWIDTH1, BUTTONHEIGHT);
        jButton1.setActionCommand("生成图");
        jButton1.addActionListener(new ButtonClickListener());
        panel.add(jButton1);

        final JButton jButton2 = new JButton("查询桥接词"); // 添加查询桥接词按钮
        jButton2.setBounds(CONC180, CONC100, BUTTONWIDTH1, BUTTONHEIGHT);
        jButton2.setActionCommand("查询桥接词");
        jButton2.addActionListener(new ButtonClickListener());
        panel.add(jButton2);

        final JButton jButton3 = new JButton("生成新文本"); // 添加生成新文本按钮
        jButton3.setBounds(CONC40, CONC140, BUTTONWIDTH1, BUTTONHEIGHT);
        jButton3.setActionCommand("生成新文本");
        jButton3.addActionListener(new ButtonClickListener());
        panel.add(jButton3);

        final JButton jButton4 = new JButton("最短路径"); // 添加最短路径按钮
        jButton4.setBounds(CONC180, CONC140, BUTTONWIDTH1, BUTTONHEIGHT);
        jButton4.setActionCommand("最短路径");
        jButton4.addActionListener(new ButtonClickListener());
        panel.add(jButton4);

        final JButton jButton5 = new JButton("随机游走"); // 添加随机游走按钮
        jButton5.setBounds(CONC40, CONC180, BUTTONWIDTH1, BUTTONHEIGHT);
        jButton5.setActionCommand("随机游走");
        jButton5.addActionListener(new ButtonClickListener());
        panel.add(jButton5);

        final JButton jButton6 = new JButton("clear"); // 添加clear按钮
        jButton6.setBounds(CONC180, CONC180, BUTTONWIDTH1, BUTTONHEIGHT);
        jButton6.setActionCommand("clear");
        jButton6.addActionListener(new ButtonClickListener());
        panel.add(jButton6);

        outText = new JTextArea(); // 添加文本输出区域
        outText.setLineWrap(true);
        outText.setEditable(false);
        outText.setWrapStyleWord(true);

        final JScrollPane jsp = new JScrollPane(outText); // 添加滚动条
        jsp.setBounds(0, CONC230, CONC345, CONC170);
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
         * 构造函数.
         */
        ButtonClickListener() {
         // This constructor is intentionally empty. Nothing special is needed.
        };
        /**
         * 实现actionPerformed方法.
         * @param update 事件
         */
        public void actionPerformed(final ActionEvent update) {
            final String command = update.getActionCommand();
            String strx;
            String[] strxarr;

            if ("open".equals(command)) { // 打开文件功能
                final FileDialog updatefd = new FileDialog(new JFrame(),
                                    "选择文件", FileDialog.LOAD); // 生成打开文件窗口
                updatefd.setFile("*.txt");
                updatefd.setVisible(true);
                final String filename = updatefd.getFile(); // 取得文件名
                if (filename != null) {
                    gra.setFilename(updatefd.getDirectory()
                                    + updatefd.getFile());
                    // 取得文件完整路径
                    outText.setText("文件打开成功！\n" + gra.readInFile());
                }
            } else if ("生成图".equals(command)) {
                if (gra.getFilename() == null) { // 未选择文件
                    outText.setText(NOTCHOOSEFILE);
                } else {
                    outText.setText("生成图成功！图片以保存在桌面。");
                    gra.showDirectedGraph(gra); // 展示图
                }

            } else if ("查询桥接词".equals(command)) {
                if (gra.getFilename() == null) {
                 // 未选择文件
                    outText.setText(NOTCHOOSEFILE);
                } else {
                  strxarr = inText.getText()
                            .replaceAll("[^a-zA-Z]", " ")
                            .toLowerCase(Locale.US).split(REGSPACE); // 分割最小化字符串
                    if (strxarr.length == TEMPTWO) {
                     // 输出桥接词
                        outText.setText(gra.queryBridgeWords(
                            strxarr[0], strxarr[1]));
                    } else {
                     // 如果长度不为2，输入错误
                        outText.setText("输入错误！");
                    }

                }
            } else if ("生成新文本".equals(command)) {
                if (gra.getFilename() == null) {
                 // 未选择文件
                    outText.setText(NOTCHOOSEFILE);
                } else {
                    strx = inText.getText();
                    if (strx.split(REGSPACE).length < TEMPTWO
                            && strx.split(REGSPACE).length > 0) {
                     // 单词数小于2，错误
                        outText.setText("输入错误！");
                    } else {
                        outText.setText(gra.generateNewText(strx)); // 输出生成的新文本
                    }
                }
            } else if ("最短路径".equals(command)) {
              strxarr = inText.getText().
                            replaceAll("[^a-zA-Z]", " ").
                            toLowerCase(Locale.ENGLISH).
                            split(REGSPACE);
                      // 分割最小化字符串
                if (gra.getFilename() == null) {
                 // 未选择文件
                    outText.setText(NOTCHOOSEFILE);
                } else {
                    if (strxarr.length > TEMPTWO
                        || inText.getText() == null) {  // 单词大于2
                        outText.setText("输入错误！");
                    } else if (strxarr.length == TEMPTWO) {
                     // 2个单词的情况
                        outText.setText(gra.calcShortestPath(
                                        strxarr[0], strxarr[1]));
                    } else if (strxarr.length == ANTEMP) {
                     // 1个单词的情况
                        outText.setText(gra.calcShortestPath(strxarr[0]));
                    }
                }
            } else if ("随机游走".equals(command)) {
                if (gra.getFilename() == null) {
                 // 未选择文件
                    outText.setText(NOTCHOOSEFILE);
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
