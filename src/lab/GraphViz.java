package lab;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 * 使用GraphViz的接口.
 * @author LuLiu
 *
 */
class GraphViz { // java 璋冪敤GraphViz
    /**
     * 路径.
     */
    private String runPath = "";

    /**
     * 路径.
     */
    private String dotPath = "";
    /**
     * runorder.
     */
    private String runOrder = "";

    /**
     * 文件名.
     */
    private String dotCodeFile = "dotcode.txt";

    /**
     * 结果.
     */
    private String resultGif = "dotGif";

    /**
     * 图.
     */
    private String graph;

    /**
     * runtime.
     */
    private Runtime runtime = Runtime.getRuntime();
    /**
     * 初始化日志对象.
     */
    private static final Logger LOGGER = Logger.getLogger(GraphViz.class);

    /**
     * dotPath的set方法.
     * @param dotpath 参数
     */
    public void setDotPath(final String dotpath) {
        this.dotPath = dotpath;
    }
    /**
     * dotPath的get方法.
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
     * @param dotcodeFile 参数
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
     * @param resultgif 参数
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
     * @param runorder 参数
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
     * @param graphTmp 参数
     */
    public void setGraph(final String graphTmp) {
        this.graph = graphTmp;
    }
    /**
     * 运行.
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
     * 创建新的命令.
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
     * 将图转换文字.
     * @param dotcode dot代码
     * @param filename  文件名
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
     * 构造函数.
     * @param runpath   参数
     * @param dotpath   参数
     */
    GraphViz(final String runpath, final String dotpath) {
        this.runPath = runpath;
        this.dotPath = dotpath;
    }

    /**
     * 添加命令.
     * @param line 命令
     */
    public void add(final String line) {
        final StringBuffer strTmp = new StringBuffer(graph);
        final StringBuffer tab = new StringBuffer("\t");
        tab.append(line);
        strTmp.append(tab);
        graph = strTmp.toString();
    }

    /**
     * 添加命令.
     * @param line 命令
     */
    public void addln(final String line) {
        final StringBuffer strTmp = new StringBuffer(graph);
        strTmp.append("\t" + line + "\n");
        graph = strTmp.toString();
    }

    /**
     * 添加命令.
     */
    public void addln() {
        final StringBuffer strTmp = new StringBuffer(graph);
        strTmp.append('\n');
        graph = strTmp.toString();
    }

    /**
     * 写{.
     */
    public void startGraph() {
        final StringBuffer strTmp = new StringBuffer(graph);
        strTmp.append("digraph G {\n");
        graph = strTmp.toString();
    }

    /**
     * 写}.
     */
    public void endGraph() {
        final StringBuffer strTmp = new StringBuffer(graph);
        strTmp.append('}');
        graph = strTmp.toString();
    }
}
