package lab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 *  邻接矩阵有向图.
 * @author LuLiu
 *
 */
public class Graph {
    /**
     * 出度表,入度表.
     */
    private int[][] inMatrix, outMatrix;
    /**
     * 变量.
     */
    private int[] vis;
    /**
     * store split word.
     */
    private String[] str;
    /**
     * store word number.
     */
    private String[] strS0;
    /**
     * store bridge word.
     */
    private String[] strlist0;
    /**
     * 文件名.
     */
    private String filename;

    /**
     * get方法.
     * @return filename
     */
    public String getFilename() {
        return filename;
    }
    /**
     * set方法.
     * @param filenam 文件名
     */
    public void setFilename(final String filenam) {
        this.filename = filenam;
    }
    /**
     * 常数.
     */
    private final int numN1 = 100;
    /**
     * 读取文件.
     * @return 读取的字符串
     */
    public String readInFile() {
        File file = new File(filename);
        String wordsStr = "";
        Scanner in; // 按行读取字符串，并分割好
        try {
            in = new Scanner(file);
            while (in.hasNextLine()) {
                String strTmp = in.nextLine();
                wordsStr = wordsStr.concat(replaceStr(strTmp));
            }

            str = new String[wordSplit(wordsStr).length];
            // 根据长度new�?个String
            str = wordSplit(wordsStr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        creategraph(); // 创建�?
        return wordsStr;
    }
    /**
     * 创建图.
     */
    private void creategraph() {

        vis = new int[str.length]; // 记录
        Set<String> set = new HashSet<>(Arrays.asList(str));
        strS0 = new String[set.size()];
        inMatrix = new int[set.size()][set.size()];
        outMatrix = new int[set.size()][set.size()];
        // 初始�?
        for (int i = 0; i < strS0.length; i++) {
            // vis[i]=0;
            for (int j = 0; j < strS0.length; j++) {
                inMatrix[i][j] = 0;
                outMatrix[i][j] = 0;
            }
        }
        int k = 0;

        for (int i = 0; i < str.length; i++) {
         // 给每个单词标号，按出现顺序升序标号，用VIS数组标识�?
            int flag = 0;
            for (int j = 0; j < i; j++) {
                if (str[i].compareTo(str[j]) == 0) {
                    vis[i] = vis[j];
                    flag = 1;
                    continue;
                }
            }
            if (flag == 0) {
                vis[i] = k;
                k++;
            }
        }
        k = 0;
        for (int i = 0; i < str.length; i++) {
         // 踢出str数组中重复元素，并不改变原有元素顺序
            if (vis[i] == k) {
                strS0[k] = str[i];
                k++;
            }
        }

        for (int i = 0; i < str.length - 1; i++) {
         // 给二维数组赋值�??
            inMatrix[vis[i]][vis[i + 1]]++;
            outMatrix[vis[i + 1]][vis[i]]++;
        }
    }
    /**
     * 查询桥接词.
     * @param word1 1
     * @param word2 2
     * @return 桥接词
     */
    private String[] queryBridgeWords0(final String word1,
                                        final String word2) {
    // 查询桥接词，返回桥接词字符数�?
        int m = -1, n = -1, flag = 0; // 初始�?

        int[] index = new int[str.length]; // 记录桥接词的标号

        String[] list; // 桥接词字符数�?

        for (int i = 0; i < str.length; i++) {
         // 寻找单词的标�?
            String str0 = new String(str[i]);
            if (str0.compareTo(word1) == 0) {
                m = vis[i];
            }
            if (str0.compareTo(word2) == 0) {
                n = vis[i];
            }
        }

        if (m == -1 | n == -1) {
         // 单词不在图中
            list = new String[0];
            return list;
        }

        for (int i = 0; i < inMatrix.length; i++) {
         // 记录桥接词的下标和桥接词的�?�数
            if (outMatrix[n][i] != 0 && inMatrix[m][i] != 0) {
                index[flag] = i;
                flag++;
            }
        }

        if (flag == 0) {
         // 没有桥接�?
            list = new String[0];
            return list;
        } else { // 将桥接词添加进数�?
            list = new String[flag];
            for (int i = 0; i < flag; i++) {

                String str0 = strS0[index[i]];
                list[i] = str0;
            }
            return list;
        }

    }
    /**
     * 查询桥接词.
     * @param word1 1
     * @param word2 2
     * @return 桥接词
     */
    public String queryBridgeWords(final String word1, final String word2) {
        int m = -1, n = -1, flag = 0; // 初始�?

        int[] index = new int[str.length]; // 记录桥接词的标号
        for (int i = 0; i < str.length; i++) {
         // 寻找单词的标�?
            String str0 = new String(str[i]);
            if (str0.compareTo(word1) == 0) {
                m = vis[i];
            }
            if (str0.compareTo(word2) == 0) {
                n = vis[i];
            }
        }
        if (m == -1 && n != -1) {
         // Word1不在图中
            return "No \"" + word1 + "\" in the graph!";
        } else if (n == -1 && m != -1) {
         // Word2不在图中
            return "No \"" + word2 + "\" in the graph!";
        } else if (n == -1 && m == -1) {
         // Word1 ，Word2不在图中
            return "No \"" + word1 + "\" and \"" + word2 + "\" in the graph!";
        }

        for (int i = 0; i < inMatrix.length; i++) {
            if (outMatrix[n][i] != 0
                    && inMatrix[m][i] != 0) {
             // 记录桥接词的下标和桥接词的�?�数
                index[flag] = i;
                flag++;
            }
        }

        if (flag == 0) {
         // 没有桥接�?
            return "No bridge words from \""
                        + word1 + "\" to \""
                        + word2 + "\"!";
        } else {
            String strr = new String("The bridge words from \""
                                        + word1 + "\" to \""
                                        + word2 + "\" are:");
            strlist0 = new String[flag];
            for (int i = 0; i < flag; i++) {

                String str0 = strS0[index[i]]; // 得到桥接�?
                if (flag == 1) {
                 // 只有�?个桥接词
                    strr = strr + str0 + ".";
                } else if (i != flag - 1) { // 不是�?后一个桥接词
                    strr = strr + str0 + ",";
                } else {    // �?后一个桥接词
                    strr = strr + "and " + str0 + ".";
                }

                strlist0[i] = str0;

                continue;
            }
            return strr;
        }
    }

    /**
     *  分割字符.
     * @param str   待分割的字符
     * @return  分割后的字符
     */
    private static String[] wordSplit(final String str) {
        return str.split("\\s+");
    }
    /**
     * 全部转为小写.
     * @param str   原字符串
     * @return  处理后的字符串
     */
    private static String replaceStr(final String str) {
        return str.replaceAll("[^a-zA-Z]", " ").toLowerCase();
    }
    /**
     * 生成新文本.
     * @param inputText 原文本
     * @return  修改后的文本
     */
    public String generateNewText(final String inputText) {
        String[] strlist = wordSplit(inputText); // 分割字符串得到一个个字符

        String strTmp = new String(); // 存储新文�?

        for (int i = 0; i < strlist.length - 1; i++) {
            strTmp = strTmp + strlist[i] + " ";
            // 根据queryBridgeWords_0函数得到桥接词列�?
            strlist0 = queryBridgeWords0(
                    strlist[i].replaceAll("[^a-zA-Z]", " ").toLowerCase(),
                    strlist[i + 1].replaceAll("[^a-zA-Z]", " ").toLowerCase());

            if (strlist0.length != 0) { // 存在桥接词，随机选择�?�?
                final double d = Math.random();
                final int k = (int) (d * strlist0.length);
                String str1 = strlist0[k];
                strTmp = strTmp + str1 + " ";
            }
        }
        strTmp += strlist[strlist.length - 1];
        return strTmp;

    }
    /**
     * 展示.
     * @param g 图
     */
    public void showDirectedGraph(final Graph g) {
        GraphViz gViz = new GraphViz("D:\\java_Oxygen\\Lab4\\src",
                            "D:\\graphviz\\bin\\dot.exe");  // 设置好路�?
        gViz.startGraph();

        for (int i = 0; i < strS0.length; i++) {
         // 扫描每个点对，如果存在边就将其写入dot文件
            for (int j = 0; j < strS0.length; j++) {
                if (inMatrix[i][j] != 0) {
                    gViz.addln(strS0[i] + "->" + strS0[j]
                                + "[label=\""
                                + String.valueOf(inMatrix[i][j])
                                + "\"];"); // 按dot语法写入dot文件
                }
            }
        }

        gViz.endGraph();
        try {
            gViz.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 计算最短路径.
     * @param word1 1
     * @param word2 2
     * @return  结果s
     */
    public String calcShortestPath(final String word1, final String word2) {
        int m1 = -1, m2 = -1;
        int n = inMatrix.length;
        int[][] dD = new int[n][n]; // 保存从i到j的最小路径�??
        int[][] p = new int[n][n]; // 保存经过的中间节�?

        for (int i = 0; i < str.length; i++) {
            String str0 = new String(str[i]);
            if (str0.compareTo(word1) == 0) {
                m1 = vis[i]; // 找到初始起点
            }
            if (str0.compareTo(word2) == 0) {
                m2 = vis[i]; // 找到结尾节点
            }
        }
        if (m1 == -1 && m2 == -1) {
            return "字符 \"" + word1 + "\" and \"" + word2 + "\" 不存在！";
        } else if (m1 == -1 && m2 != -1) {
            return "字符 \"" + word1 + "\" 不存在！";
        } else if (m1 != -1 && m2 == -1) {
            return "字符 \"" + word2 + "\" 不存在！";
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (inMatrix[i][j] != 0) {
                    p[i][j] = j; // 先将图中节点连接
                } else {
                    p[i][j] = -1;
                }
                if (inMatrix[i][j] != 0) {
                    dD[i][j] = inMatrix[i][j]; // 将边的权值付�?
                } else {
                    dD[i][j] = numN1;
                }
            }
        }

        for (int x = 0; x < n; x++) { // 进行Floyd算法
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dD[i][j] > dD[i][x] + dD[x][j]) {
                        dD[i][j] = dD[i][x] + dD[x][j];
                        p[i][j] = p[i][x];
                    }
                }
            }
        }
        // 将结果进行输�?
        String strN = word1;
        int k = p[m1][m2];
        if (k == -1) {

            return "没有�?短路径！";

        } else { // 将结果字符串末端不断添加
            String str0 = strS0[k];
            strN = strN + "->" + str0;
            while (k != m2) {
                k = p[k][m2];
                str0 = strS0[k];
                strN = strN + "->" + str0;
            }
            String strA = String.valueOf(dD[m1][m2]);
            strN = strN + "   路径长度" + strA; // 路径输出，并将其长度添加在末
        }
        return strN;
    }

    /**
     * 随机游走.
     * @return 结果
     */
    public String randomWalk() {
        int n = strS0.length;
        int strLen = strS0.length;
        int[][] cpMatrix = new int[n][n]; // 新建数组，用于记录游走情况；
        String strTmp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cpMatrix[i][j] = inMatrix[i][j];
            }
        }
        // 随机数的产生
        Random rand = new Random();
        int j = rand.nextInt(strLen) % (strLen + 1);
        strTmp = strS0[j];
        boolean flag = true;
        // 把游走路线存储到字符串中
        while (flag) {
            for (int k = 0; k < strLen; k++) {
                if (cpMatrix[j][k] != 0) {
                    if (cpMatrix[j][k] != -1) {
                        strTmp = strTmp + " " + strS0[k];
                        cpMatrix[j][k] = -1; // 把访问过的边值修改为 -1
                        j = k;
                        k = -1;
                    } else {
                        strTmp = strTmp + " " + strS0[k];
                        break;
                    }
                }
            }
            flag = false; // 结束路线生成
        }
        return strTmp;
    }
    /**
     * 计算一个起点到其他顶点的最短路径.
     * @param word1 起点
     * @return 结果
     */
    public String calcShortestPath(final String word1) {
        int m1 = -1, m2 = 0;
        String strr = "";
        int n = inMatrix.length;
        int[][] dD = new int[n][n];      // 保存从i到j的最小路径
        int[][] p = new int[n][n];      // 保存经过的中间节
        // 找到对应序号
        for (int i = 0; i < str.length; i++) {
            String str0 = new String(str[i]);
            if (str0.compareTo(word1) == 0) {
                m1 = vis[i];
            }
        }
        if (m1 == -1) {
            return "字符 \"" + word1 + "\" 不存在！";
        }
        for (int i = 0; i < n; i++) {       // 初始化D，p
            for (int j = 0; j < n; j++) {
                if (inMatrix[i][j] != 0) {
                    p[i][j] = j;
                } else {
                    p[i][j] = -1;
                }
                if (inMatrix[i][j] != 0) {
                    dD[i][j] = inMatrix[i][j];
                } else {
                    dD[i][j] = numN1;
                }
            }
        }

        for (int x = 0; x < n; x++) {
         // 进行Floyd算法，从0到n-1有可能进行遍
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dD[i][j] > dD[i][x] + dD[x][j]) {
                        dD[i][j] = dD[i][x] + dD[x][j];
                        p[i][j] = p[i][x];
                    }
                }
            }
        }
        // 下面对获得的结果进行展示
        String strN = word1;
        for (m2 = 0; m2 < n; m2++) {
            if (m2 != m1) {
                strr = strr + "输出"
                            + word1 + "" + strS0[m2] + "的最短路径：\r\n";
                int k = p[m1][m2];
                if (k == -1) {
                    strr = strr + "没有短路径\r\n";
                } else {
                    String str0 = strS0[k];
                    strN = strN + "->" + str0;
                    while (k != m2) {
                        k = p[k][m2];
                        str0 = strS0[k];
                        strN = strN + "->" + str0;
                    }
                    String strA = String.valueOf(dD[m1][m2]);
                    strN = strN + "   路径长度" + strA;
                    strr = strr + strN + "\r\n";
                    strN = word1;
                }
            }
        }
        return strr;
    }
}
// liulu & qiaozhi lab1
