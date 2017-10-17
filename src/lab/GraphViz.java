package lab;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
    private StringBuilder graph = new StringBuilder();

    /**
     * runtime.
     */
    private Runtime runtime = Runtime.getRuntime();

    /**
     * 运行.
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
        graph.append("\t" + line);
    }

    /**
     * 添加命令.
     * @param line 命令
     */
    public void addln(final String line) {
        graph.append("\t" + line + "\n");
    }

    /**
     * 添加命令.
     */
    public void addln() {
        graph.append('\n');
    }

    /**
     * 写{.
     */
    public void startGraph() {
        graph.append("digraph G {\n");
    }

    /**
     * 写}.
     */
    public void endGraph() {
        graph.append("}");
    }
}
