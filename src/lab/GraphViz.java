package lab;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

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
    private String graph;

    /**
     * runtime.
     */
    private Runtime runtime = Runtime.getRuntime();
    /**
     * ��ʼ����־����.
     */
    private static final Logger LOGGER = Logger.getLogger(GraphViz.class);

    /**
     * dotPath��set����.
     * @param dotpath ����
     */
    public void setDotPath(final String dotpath) {
        this.dotPath = dotpath;
    }
    /**
     * dotPath��get����.
     * @return dotPath
     */
    public String getDotPath() {
        return dotPath;
    }
    /**
     * get.
     * @return runPath
     */
    public String getRunPath() {
        return runPath;
    }
    /**
     * set.
     * @param runpath runpath
     */
    public void setRunPath(final String runpath) {
        this.runPath = runpath;
    }
    /**
     * get.
     * @return dotCodeFile
     */
    public String getDotCodeFile() {
        return dotCodeFile;
    }
    /**
     * set.
     * @param dotcodeFile ����
     */
    public void setDotCodeFile(final String dotcodeFile) {
        this.dotCodeFile = dotcodeFile;
    }
    /**
     * get.
     * @return rg
     */
    public String getResultGif() {
        return resultGif;
    }
    /**
     * set.
     * @param resultgif ����
     */
    public void setResultGif(final String resultgif) {
        this.resultGif = resultgif;
    }
    /**
     * get.
     * @return grt
     */
    public Runtime getRuntime() {
        return runtime;
    }
    /**
     * set.
     * @param runtim rt
     */
    public void setRuntime(final Runtime runtim) {
        this.runtime = runtim;
    }
    /**
     * get.
     * @return runOrder
     */
    public String getRunOrder() {
        return runOrder;
    }
    /**
     * set.
     * @param runorder ����
     */
    public void setRunOrder(final String runorder) {
        this.runOrder = runorder;
    }
    /**
     * get.
     * @return graph
     */
    public String getGraph() {
        return graph;
    }
    /**
     * set.
     * @param graphTmp ����
     */
    public void setGraph(final String graphTmp) {
        this.graph = graphTmp;
    }
    /**
     * ����.
     */
    public void run() {
        BasicConfigurator.configure();
        final File file = new File(runPath);
        file.mkdirs();
        writeGraphToFile(graph, runPath);
        creatOrder();
        try {
            runtime.exec(runOrder);
            runtime.exec("cmd.exe /c " + runPath + "/dotGif.gif");
        } catch (IOException e) {
            LOGGER.error("IOException");
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
        BasicConfigurator.configure();
        try {
            final File file = new File(filename + "\\" + dotCodeFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            final FileOutputStream fos = new FileOutputStream(file);
            fos.write(dotcode.getBytes());
            fos.close();
        } catch (IOException ioe) {
            LOGGER.error("IOException");
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
        final StringBuffer strTmp = new StringBuffer(graph);
        final StringBuffer tab = new StringBuffer("\t");
        tab.append(line);
        strTmp.append(tab);
        graph = strTmp.toString();
    }

    /**
     * �������.
     * @param line ����
     */
    public void addln(final String line) {
        final StringBuffer strTmp = new StringBuffer(graph);
        strTmp.append("\t" + line + "\n");
        graph = strTmp.toString();
    }

    /**
     * �������.
     */
    public void addln() {
        final StringBuffer strTmp = new StringBuffer(graph);
        strTmp.append('\n');
        graph = strTmp.toString();
    }

    /**
     * д{.
     */
    public void startGraph() {
        final StringBuffer strTmp = new StringBuffer(graph);
        strTmp.append("digraph G {\n");
        graph = strTmp.toString();
    }

    /**
     * д}.
     */
    public void endGraph() {
        final StringBuffer strTmp = new StringBuffer(graph);
        strTmp.append('}');
        graph = strTmp.toString();
    }
}
