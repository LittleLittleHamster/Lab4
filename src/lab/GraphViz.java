package lab;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ʹ��GraphViz�Ľӿ�.
 * @author LuLiu
 *
 */
class GraphViz { // java 调用GraphViz
    /**
     * ·��.
     */
    private String runPath = "";

    /**
     * ·��.
     */
    private String dotPath = "";

    /**
     * runorder.
     */
    private String runOrder = "";

    /**
     * �ļ���.
     */
    private String dotCodeFile = "dotcode.txt";

    /**
     * ���.
     */
    private String resultGif = "dotGif";

    /**
     * ͼ.
     */
    private StringBuilder graph = new StringBuilder();

    /**
     * runtime.
     */
    private Runtime runtime = Runtime.getRuntime();

    /**
     * ����.
     */
    public void run() {
        File file = new File(runPath);
        file.mkdirs();
        writeGraphToFile(graph.toString(), runPath);
        creatOrder();
        try {
            runtime.exec(runOrder);
            runtime.exec("cmd.exe /c " + runPath + "/dotGif.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * �����µ�����.
     */

    public void creatOrder() {
        runOrder += dotPath + " ";
        runOrder += runPath;
        runOrder += "\\" + dotCodeFile + " ";
        runOrder += "-T gif ";
        runOrder += "-o ";
        runOrder += runPath;
        runOrder += "\\" + resultGif + ".gif";
    }

    /**
     * ��ͼת������.
     * @param dotcode dot����
     * @param filename  �ļ���
     */
    public void writeGraphToFile(final String dotcode, final String filename) {
        try {
            File file = new File(filename + "\\" + dotCodeFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(dotcode.getBytes());
            fos.close();
        } catch (java.io.IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * ���캯��.
     * @param runpath   ����
     * @param dotpath   ����
     */
    GraphViz(final String runpath, final String dotpath) {
        this.runPath = runpath;
        this.dotPath = dotpath;
    }

    /**
     * �������.
     * @param line ����
     */
    public void add(final String line) {
        graph.append("\t" + line);
    }

    /**
     * �������.
     * @param line ����
     */
    public void addln(final String line) {
        graph.append("\t" + line + "\n");
    }

    /**
     * �������.
     */
    public void addln() {
        graph.append('\n');
    }

    /**
     * д{.
     */
    public void startGraph() {
        graph.append("digraph G {\n");
    }

    /**
     * д}.
     */
    public void endGraph() {
        graph.append("}");
    }
}
