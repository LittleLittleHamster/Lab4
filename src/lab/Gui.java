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
 * ͼ�ν���.
 * @author LuLiu
 *
 */
public class Gui {
    /**
     * ����ı�����.
     */
    private JTextArea outText;
    /**
     * �����ı�����.
     */
    private JTextField inText;
    /**
     * ͼ.
     */
    private Graph gra = new Graph();

    /**
     * frame�Ŀ��.
     */
    private final int frameWidth = 350;
    /**
     * frame�ĸ߶�.
     */
    private final int frameHeight = 450;
    /**
     * ��ť�ĸ߶�.
     */
    private final int buttonHeight = 25;
    /**
     * ��ť�Ŀ��80.
     */
    private final int buttonWidth0 = 80;
    /**
     * ��ť�Ŀ��100.
     */
    private final int buttonWidth1 = 100;
    /**
     * button0�ĺ�����.
     */
    private final int buttonX0 = 120;
    /**
     * button0��������.
     */
    private final int buttonY0 = 20;
    /**
     * ����10.
     */
    private final int conC10 = 10;
    /**
     * ����60.
     */
    private final int conC60 = 60;
    /**
     * ����80.
     */
    private final int conC80 = 80;
    /**
     * ����25.
     */
    private final int conC25 = 25;
    /**
     * ����100.
     */
    private final int conC100 = 100;
    /**
     * ����20.
     */
    private final int conC20 = 20;
    /**
     * ����180.
     */
    private final int conC180 = 180;
    /**
     * ����40.
     */
    private final int conC40 = 40;
    /**
     * ����140.
     */
    private final int conC140 = 140;
    /**
     * ����230.
     */
    private final int conC230 = 230;
    /**
     * ����345.
     */
    private final int conC345 = 345;
    /**
     * ����170.
     */
    private final int conC170 = 170;
    /**
     * main����.
     * @param args ����
     */
    public static void main(final String[] args) {
        Gui gui = new Gui();
        gui.prepareGUI();
    }

    /**
     * ����ͼ�ν���.
     */
    private void prepareGUI() {

        JFrame frame = new JFrame("ʵ��һ");

        frame.setSize(frameWidth, frameHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô��ڳߴ�

        JPanel panel = new JPanel();

        frame.add(panel);
        panel.setLayout(null);

        JButton jButton0 = new JButton("open"); // ���open��ť������ѡ���ļ�
        jButton0.setBounds(buttonX0, buttonY0, buttonWidth0, buttonHeight);
        jButton0.setActionCommand("open");
        jButton0.addActionListener(new ButtonClickListener());
        panel.add(jButton0);

        JLabel jLabel0 = new JLabel("����"); // ������봰��

        jLabel0.setBounds(conC10, conC60, conC80, conC25);
        panel.add(jLabel0);

        inText = new JTextField(conC20);
        inText.setBounds(conC60, conC60, conC230, conC25);
        panel.add(inText);

        JButton jButton1 = new JButton("����ͼ"); // �������ͼ��ť
        jButton1.setBounds(conC40, conC100, buttonWidth1, buttonHeight);
        jButton1.setActionCommand("����ͼ");
        jButton1.addActionListener(new ButtonClickListener());
        panel.add(jButton1);

        JButton jButton2 = new JButton("��ѯ�ŽӴ�"); // ��Ӳ�ѯ�ŽӴʰ�ť
        jButton2.setBounds(conC180, conC100, buttonWidth1, buttonHeight);
        jButton2.setActionCommand("��ѯ�ŽӴ�");
        jButton2.addActionListener(new ButtonClickListener());
        panel.add(jButton2);

        JButton jButton3 = new JButton("�������ı�"); // ����������ı���ť
        jButton3.setBounds(conC40, conC140, buttonWidth1, buttonHeight);
        jButton3.setActionCommand("�������ı�");
        jButton3.addActionListener(new ButtonClickListener());
        panel.add(jButton3);

        JButton jButton4 = new JButton("���·��"); // ������·����ť
        jButton4.setBounds(conC180, conC140, buttonWidth1, buttonHeight);
        jButton4.setActionCommand("���·��");
        jButton4.addActionListener(new ButtonClickListener());
        panel.add(jButton4);

        JButton jButton5 = new JButton("�������"); // ���������߰�ť
        jButton5.setBounds(conC40, conC180, buttonWidth1, buttonHeight);
        jButton5.setActionCommand("�������");
        jButton5.addActionListener(new ButtonClickListener());
        panel.add(jButton5);

        JButton jButton6 = new JButton("clear"); // ���clear��ť
        jButton6.setBounds(conC180, conC180, buttonWidth1, buttonHeight);
        jButton6.setActionCommand("clear");
        jButton6.addActionListener(new ButtonClickListener());
        panel.add(jButton6);

        outText = new JTextArea(); // ����ı��������
        outText.setLineWrap(true);
        outText.setEditable(false);
        outText.setWrapStyleWord(true);

        JScrollPane jsp = new JScrollPane(outText); // ��ӹ�����
        jsp.setBounds(0, conC230, conC345, conC170);
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
         * ʵ��actionPerformed����.
         * @param e �¼�
         */
        public void actionPerformed(final ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("open")) { // ���ļ�����

                FileDialog fd = new FileDialog(new JFrame(),
                                    "ѡ���ļ�", FileDialog.LOAD); // ���ɴ��ļ�����
                fd.setFile("*.txt");
                fd.setVisible(true);
                String filename = fd.getFile(); // ȡ���ļ���
                if (filename != null) {
                    gra.setFilename(fd.getDirectory() + fd.getFile());
                    // ȡ���ļ�����·��
                    outText.setText("�ļ��򿪳ɹ���\n" + gra.readInFile());
                }
            } else if (command.equals("����ͼ")) {
                if (gra.getFilename() == null) { // δѡ���ļ�
                    outText.setText("δѡ���ļ���");
                } else {
                    outText.setText("����ͼ�ɹ���ͼƬ�Ա��������档");
                    gra.showDirectedGraph(gra); // չʾͼ
                }

            } else if (command.equals("��ѯ�ŽӴ�")) {
                if (gra.getFilename() == null) {
                 // δѡ���ļ�
                    outText.setText("δѡ���ļ���");
                } else {
                    String[] strx = inText.getText()
                            .replaceAll("[^a-zA-Z]", " ")
                            .toLowerCase().split("\\s+"); // �ָ���С���ַ���
                    if (strx.length != 2) {
                     // ������Ȳ�Ϊ2���������
                        outText.setText("�������");
                    } else {
                    // ����ŽӴ�
                        outText.setText(gra.queryBridgeWords(strx[0], strx[1]));
                    }

                }
            } else if (command.equals("�������ı�")) {
                if (gra.getFilename() == null) {
                 // δѡ���ļ�
                    outText.setText("δѡ���ļ���");
                } else {
                    String strx = inText.getText();
                    if ((strx.split("\\s+").length < 2)
                            && (strx.split("\\s+").length > 0)) {
                     // ������С��2������
                        outText.setText("�������");
                    } else {
                        outText.setText(gra.generateNewText(strx)); // ������ɵ����ı�
                    }
                }
            } else if (command.equals("���·��")) {
                String[] strx = inText.getText().
                            replaceAll("[^a-zA-Z]", " ").
                            toLowerCase().split("\\s+"); // �ָ���С���ַ���
                if (gra.getFilename() == null) {
                 // δѡ���ļ�
                    outText.setText("δѡ���ļ���");
                } else {
                    if (strx.length > 2 | inText.getText() == null) {  // ���ʴ���2
                        outText.setText("�������");
                    } else if (strx.length == 2) {
                     // 2�����ʵ����
                        outText.setText(gra.calcShortestPath(strx[0], strx[1]));
                    } else if (strx.length == 1) {
                     // 1�����ʵ����
                        outText.setText(gra.calcShortestPath(strx[0]));
                    }
                }
            } else if (command.equals("�������")) {
                if (gra.getFilename() == null) {
                 // δѡ���ļ�
                    outText.setText("δѡ���ļ���");
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
