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
 * ͼ�ν���.
 * @author LuLiu
 *
 */
public class CreateGui {
    /**
     * ����ı�����.
     */
    private transient JTextArea outText;
    /**
     * �����ı�����.
     */
    private transient JTextField inText;
    /**
     * ͼ.
     */
    private final transient Graph gra = new Graph();

    /**
     * frame�Ŀ��.
     */
    private static final int FRAMEWIDTH = 350;
    /**
     * frame�ĸ߶�.
     */
    private static final int FRAMEHEIGHT = 450;
    /**
     * ��ť�ĸ߶�.
     */
    private static final int BUTTONHEIGHT = 25;
    /**
     * ��ť�Ŀ��80.
     */
    private static final int BUTTONWIDTH0 = 80;
    /**
     * ��ť�Ŀ��100.
     */
    private static final int BUTTONWIDTH1 = 100;
    /**
     * button0�ĺ�����.
     */
    private static final int BUTTONX0 = 120;
    /**
     * button0��������.
     */
    private static final int BUTTONY0 = 20;
    /**
     * ����10.
     */
    private static final int CONC10 = 10;
    /**
     * ����60.
     */
    private static final int CONC60 = 60;
    /**
     * ����80.
     */
    private static final int CONC80 = 80;
    /**
     * ����25.
     */
    private static final int CONC25 = 25;
    /**
     * ����100.
     */
    private static final int CONC100 = 100;
    /**
     * ����20.
     */
    private static final int CONC20 = 20;
    /**
     * ����180.
     */
    private static final int CONC180 = 180;
    /**
     * ����40.
     */
    private static final int CONC40 = 40;
    /**
     * ����140.
     */
    private static final int CONC140 = 140;
    /**
     * ����230.
     */
    private static final int CONC230 = 230;
    /**
     * ����345.
     */
    private static final int CONC345 = 345;
    /**
     * ����170.
     */
    private static final int CONC170 = 170;
    /**
     * ����1.
     */
    private static final int ANTEMP = 1;
    /**
     * ����2.
     */
    private static final int TEMPTWO = 2;
    /**
     * �ַ�������.
     */
    private static final String NOTCHOOSEFILE = "δѡ���ļ���";
    /**
     * �ַ���������������ʽ.
     */
    private static final String REGSPACE = "\\s+";
    /**
     * main����.
     * @param args ����
     */
    public static void main(final String[] args) {
        final CreateGui gui = new CreateGui();
        gui.prepareGUI();
    }

    /**
     * ����ͼ�ν���.
     */
    private void prepareGUI() {

        final JFrame frame = new JFrame("ʵ��һ");

        frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô��ڳߴ�

        final JPanel panel = new JPanel();

        frame.add(panel);
        panel.setLayout(null);

        final JButton jButton0 = new JButton("open"); // ���open��ť������ѡ���ļ�
        jButton0.setBounds(BUTTONX0, BUTTONY0, BUTTONWIDTH0, BUTTONHEIGHT);
        jButton0.setActionCommand("open");
        jButton0.addActionListener(new ButtonClickListener());
        panel.add(jButton0);

        final JLabel jLabel0 = new JLabel("����"); // ������봰��

        jLabel0.setBounds(CONC10, CONC60, CONC80, CONC25);
        panel.add(jLabel0);

        inText = new JTextField(CONC20);
        inText.setBounds(CONC60, CONC60, CONC230, CONC25);
        panel.add(inText);

        final JButton jButton1 = new JButton("����ͼ"); // �������ͼ��ť
        jButton1.setBounds(CONC40, CONC100, BUTTONWIDTH1, BUTTONHEIGHT);
        jButton1.setActionCommand("����ͼ");
        jButton1.addActionListener(new ButtonClickListener());
        panel.add(jButton1);

        final JButton jButton2 = new JButton("��ѯ�ŽӴ�"); // ��Ӳ�ѯ�ŽӴʰ�ť
        jButton2.setBounds(CONC180, CONC100, BUTTONWIDTH1, BUTTONHEIGHT);
        jButton2.setActionCommand("��ѯ�ŽӴ�");
        jButton2.addActionListener(new ButtonClickListener());
        panel.add(jButton2);

        final JButton jButton3 = new JButton("�������ı�"); // ����������ı���ť
        jButton3.setBounds(CONC40, CONC140, BUTTONWIDTH1, BUTTONHEIGHT);
        jButton3.setActionCommand("�������ı�");
        jButton3.addActionListener(new ButtonClickListener());
        panel.add(jButton3);

        final JButton jButton4 = new JButton("���·��"); // ������·����ť
        jButton4.setBounds(CONC180, CONC140, BUTTONWIDTH1, BUTTONHEIGHT);
        jButton4.setActionCommand("���·��");
        jButton4.addActionListener(new ButtonClickListener());
        panel.add(jButton4);

        final JButton jButton5 = new JButton("�������"); // ���������߰�ť
        jButton5.setBounds(CONC40, CONC180, BUTTONWIDTH1, BUTTONHEIGHT);
        jButton5.setActionCommand("�������");
        jButton5.addActionListener(new ButtonClickListener());
        panel.add(jButton5);

        final JButton jButton6 = new JButton("clear"); // ���clear��ť
        jButton6.setBounds(CONC180, CONC180, BUTTONWIDTH1, BUTTONHEIGHT);
        jButton6.setActionCommand("clear");
        jButton6.addActionListener(new ButtonClickListener());
        panel.add(jButton6);

        outText = new JTextArea(); // ����ı��������
        outText.setLineWrap(true);
        outText.setEditable(false);
        outText.setWrapStyleWord(true);

        final JScrollPane jsp = new JScrollPane(outText); // ��ӹ�����
        jsp.setBounds(0, CONC230, CONC345, CONC170);
        panel.add(jsp);

        frame.setVisible(true);
    }

    /**
     * �¼�������.
     * @author JiabinLiu
     *
     */
    private class ButtonClickListener implements ActionListener {
        /**
         * ���캯��.
         */
        ButtonClickListener() {
         // This constructor is intentionally empty. Nothing special is needed.
        };
        /**
         * ʵ��actionPerformed����.
         * @param update �¼�
         */
        public void actionPerformed(final ActionEvent update) {
            final String command = update.getActionCommand();
            String strx;
            String[] strxarr;

            if ("open".equals(command)) { // ���ļ�����
                final FileDialog updatefd = new FileDialog(new JFrame(),
                                    "ѡ���ļ�", FileDialog.LOAD); // ���ɴ��ļ�����
                updatefd.setFile("*.txt");
                updatefd.setVisible(true);
                final String filename = updatefd.getFile(); // ȡ���ļ���
                if (filename != null) {
                    gra.setFilename(updatefd.getDirectory()
                                    + updatefd.getFile());
                    // ȡ���ļ�����·��
                    outText.setText("�ļ��򿪳ɹ���\n" + gra.readInFile());
                }
            } else if ("����ͼ".equals(command)) {
                if (gra.getFilename() == null) { // δѡ���ļ�
                    outText.setText(NOTCHOOSEFILE);
                } else {
                    outText.setText("����ͼ�ɹ���ͼƬ�Ա��������档");
                    gra.showDirectedGraph(gra); // չʾͼ
                }

            } else if ("��ѯ�ŽӴ�".equals(command)) {
                if (gra.getFilename() == null) {
                 // δѡ���ļ�
                    outText.setText(NOTCHOOSEFILE);
                } else {
                  strxarr = inText.getText()
                            .replaceAll("[^a-zA-Z]", " ")
                            .toLowerCase(Locale.US).split(REGSPACE); // �ָ���С���ַ���
                    if (strxarr.length == TEMPTWO) {
                     // ����ŽӴ�
                        outText.setText(gra.queryBridgeWords(
                            strxarr[0], strxarr[1]));
                    } else {
                     // ������Ȳ�Ϊ2���������
                        outText.setText("�������");
                    }

                }
            } else if ("�������ı�".equals(command)) {
                if (gra.getFilename() == null) {
                 // δѡ���ļ�
                    outText.setText(NOTCHOOSEFILE);
                } else {
                    strx = inText.getText();
                    if (strx.split(REGSPACE).length < TEMPTWO
                            && strx.split(REGSPACE).length > 0) {
                     // ������С��2������
                        outText.setText("�������");
                    } else {
                        outText.setText(gra.generateNewText(strx)); // ������ɵ����ı�
                    }
                }
            } else if ("���·��".equals(command)) {
              strxarr = inText.getText().
                            replaceAll("[^a-zA-Z]", " ").
                            toLowerCase(Locale.ENGLISH).
                            split(REGSPACE);
                      // �ָ���С���ַ���
                if (gra.getFilename() == null) {
                 // δѡ���ļ�
                    outText.setText(NOTCHOOSEFILE);
                } else {
                    if (strxarr.length > TEMPTWO
                        || inText.getText() == null) {  // ���ʴ���2
                        outText.setText("�������");
                    } else if (strxarr.length == TEMPTWO) {
                     // 2�����ʵ����
                        outText.setText(gra.calcShortestPath(
                                        strxarr[0], strxarr[1]));
                    } else if (strxarr.length == ANTEMP) {
                     // 1�����ʵ����
                        outText.setText(gra.calcShortestPath(strxarr[0]));
                    }
                }
            } else if ("�������".equals(command)) {
                if (gra.getFilename() == null) {
                 // δѡ���ļ�
                    outText.setText(NOTCHOOSEFILE);
                } else {
                    outText.setText(gra.randomWalk());
                }
            } else { // �����������ı�����
                outText.setText("");
                inText.setText("");
            }
        }
    }

}
// liulu and qiaozhi lab1
