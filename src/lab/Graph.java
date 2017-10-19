package lab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

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
     * 日志.
     */
    private static final Logger LOGGER = Logger.getLogger(Graph.class);
    /**
     * 常数.
     */
    private static final int NUMN1 = 100;
    /**
     * 字符常量：字符\".
     */
    private static final String CHARA = "字符\"";
    /**
     * 字符串常量：\" 不存在！.
     */
    private static final String CHARB = "\" 不存在！";
    /**
     * 常数1.
     */
    private static final int CONSTANTONE = 1;
    /**
     * 字符常量空格.
     */
    private static final String CONSPACE = " ";
    /**
     * 字符串常量.
     */
    private static final String CONARROW = "->";
    /**
     * 字符串常量.
     */
    private static final String CONPATHLEN = "   路径长度";
    /**
     * 字符串常量.
     */
    private static final String CONNEWLINE = "\r\n";
    /**
     * 常量,.
     */
    private static final String CONCOMMA = "，";
    /**
     * 空.
     */
    private static final Object CONNULL = null;
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
     * get.
     * @return 实例变量
     */
    public int[][] getInMatrix() {
        return Arrays.copyOf(inMatrix, inMatrix.length);
    }
    /**
     * set.
     * @param iMatrixTmp 参数
     */
    public void setInMatrix(final int[][] iMatrixTmp) {
        if (null == iMatrixTmp) {
            this.inMatrix = (int[][]) CONNULL;
        } else {
            this.inMatrix = Arrays.copyOf(iMatrixTmp, iMatrixTmp.length);
        }
    }
    /**
     * get.
     * @return 实例变量
     */
    public int[][] getOutMatrix() {
        return outMatrix;
    }
    /**
     * set.
     * @param oMatrixTmp 参数
     */
    public void setOutMatrix(final int[][] oMatrixTmp) {
        if (null == oMatrixTmp) {
            this.outMatrix = (int[][]) CONNULL;
        } else {
            this.outMatrix = Arrays.copyOf(oMatrixTmp, oMatrixTmp.length);
        }
    }
    /**
     * get.
     * @return 实例变量
     */
    public int[] getVis() {
        return Arrays.copyOf(vis, vis.length);
    }
    /**
     * set.
     * @param visTmp 参数
     */
    public void setVis(final int[] visTmp) {
        if (null == visTmp) {
            this.vis = (int[]) CONNULL;
        } else {
            this.vis = Arrays.copyOf(visTmp, visTmp.length);
        }
    }
    /**
     * get.
     * @return 实例变量
     */
    public String[] getStr() {
        return Arrays.copyOf(str, str.length);
    }
    /**
     * set.
     * @param strTmp 参数
     */
    public void setStr(final String[] strTmp) {
        if (null == strTmp) {
            this.str = (String[]) CONNULL;
        } else {
            this.str = Arrays.copyOf(strTmp, strTmp.length);
        }
    }
    /**
     * get.
     * @return 实例变量
     */
    public String[] getStrS0() {
        return Arrays.copyOf(strS0, strS0.length);
    }
    /**
     * set.
     * @param strS0Tmp 参数
     */
    public void setStrS0(final String[] strS0Tmp) {
        if (null == strS0Tmp) {
            this.strS0 = (String[]) CONNULL;
        } else {
            this.strS0 = Arrays.copyOf(strS0Tmp, strS0Tmp.length);
        }
    }
    /**
     * get.
     * @return 实例变量
     */
    public String[] getStrlist0() {
        return Arrays.copyOf(strlist0, strlist0.length);
    }
    /**
     * set.
     * @param strlist0Tmp  参数
     */
    public void setStrlist0(final String[] strlist0Tmp) {
        if (null == strlist0Tmp) {
            this.strlist0 = (String[]) CONNULL;
        } else {
            this.strlist0 = Arrays.copyOf(strlist0Tmp, strlist0Tmp.length);
        }
    }
    /**
     * 读取文件.
     * @return 读取的字符串
     */
    public String readInFile() {
        BasicConfigurator.configure();
        final File file = new File(filename);
        String wordsStr = "";
        Scanner inStr; // 按行读取字符串，并分割好
        try {
            inStr = new Scanner(file);
            while (inStr.hasNextLine()) {
                final String strTmp = inStr.nextLine();
                wordsStr = wordsStr.concat(replaceStr(strTmp));
            }

            str = new String[wordSplit(wordsStr).length];
            // 根据长度new�?个String
            str = wordSplit(wordsStr);
        } catch (FileNotFoundException e) {
            LOGGER.error("FileNotFoundException");
        }
        creategraph(); // 创建�?
        return wordsStr;
    }
    /**
     * 创建图.
     */
    private void creategraph() {

        vis = new int[str.length]; // 记录
        final Set<String> set = new HashSet<>(Arrays.asList(str));
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
        int kTemp = 0;

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
                vis[i] = kTemp;
                kTemp++;
            }
        }
        kTemp = 0;
        for (int i = 0; i < str.length; i++) {
         // 踢出str数组中重复元素，并不改变原有元素顺序
            if (vis[i] == kTemp) {
                strS0[kTemp] = str[i];
                kTemp++;
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
        int numM = -1;
        int numN = -1;


        //int[] index = new int[str.length]; // 记录桥接词的标号

        String[] list; // 桥接词字符数�?

        for (int i = 0; i < str.length; i++) {
         // 寻找单词的标�?
            if (str[i].compareTo(word1) == 0) {
                numM = vis[i];
            }
            if (str[i].compareTo(word2) == 0) {
                numN = vis[i];
            }
        }

        if (numM == -1 | numN == -1) {
         // 单词不在图中
            list = new String[0];
            //return list;
        } else {
            final ArrayList index = new ArrayList();
            int flag = 0; // 初始�?
            for (int i = 0; i < inMatrix.length; i++) {
             // 记录桥接词的下标和桥接词的�?�数
                if ((outMatrix[numN][i] < 0 || outMatrix[numN][i] > 0)
                        && (inMatrix[numM][i] < 0 || inMatrix[numM][i] > 0)) {
                    index.add(i);
                    //index[flag] = i;
                    flag++;
                } else {
                    index.add(0);
                }
            }

            if (flag == 0) {
             // 没有桥接�?
                list = new String[0];
                //return list;
            } else { // 将桥接词添加进数�?
                list = new String[flag];
                for (int i = 0; i < flag; i++) {
                    final Object[] indexTmp = index.toArray();
                    final String str0 = strS0[(int) indexTmp[i]];
                    list[i] = str0;
                }
                //return list;
            }
        }
        return list;
    }
    /**
     * 查询桥接词.
     * @param word1 1
     * @param word2 2
     * @return 桥接词
     */
    public String queryBridgeWords(final String word1, final String word2) {
        int numM = -1;
        int numN = -1;
        StringBuffer strRes;

        for (int i = 0; i < str.length; i++) {
         // 寻找单词的标�?
            if (str[i].compareTo(word1) == 0) {
                numM = vis[i];
            }
            if (str[i].compareTo(word2) == 0) {
                numN = vis[i];
            }
        }
        if (numM == -1 && numN != -1) {
         // Word1不在图中
            //return "No \"" + word1 + "\" in the graph!";
            strRes = new StringBuffer("No \"" + word1 + "\" in the graph!");
        } else if (numN == -1 && numM != -1) {
         // Word2不在图中
            //return "No \"" + word2 + "\" in the graph!";
            strRes = new StringBuffer("No \"" + word2 + "\" in the graph!");
        } else if (numN == -1 && numM == -1) {
         // Word1 ，Word2不在图中
            //return "No \"" + word1 + "\" and \"" + word2 + "\" in the graph!";
            strRes = new StringBuffer("No \"" + word1 + "\" and \""
                                + word2 + "\" in the graph!");
        } else {
            int[] index = new int[str.length]; // 记录桥接词的标号
            int flag = 0; // 初始�?
            for (int i = 0; i < inMatrix.length; i++) {
                if (outMatrix[numN][i] != 0
                        && inMatrix[numM][i] != 0) {
                 // 记录桥接词的下标和桥接词的个数
                    index[flag] = i;
                    flag++;
                }
            }

            if (flag == 0) {
             // 没有桥接�?
//                return "No bridge words from \""
//                            + word1 + "\" to \""
//                            + word2 + "\"!";
                strRes = new StringBuffer("No bridge words from \""
                            + word1 + "\" to \""
                            + word2 + "\"!");
            } else {
                final StringBuffer strr = new
                        StringBuffer("The bridge words from \""
                                            + word1 + "\" to \""
                                            + word2 + "\" are:");
                strlist0 = new String[flag];
                for (int i = 0; i < flag; i++) {

                    final String str0 = strS0[index[i]]; // 得到桥接�?
                    if (flag == CONSTANTONE) {
                     // 只有�?个桥接词
                        strr.append(str0 + CONCOMMA);
                    } else if (i < flag - 1
                            || i > flag - 1) { // 不是�?后一个桥接词
                        //strr = strr + str0 + ",";
                        strr.append("str0" + ",");
                    } else {    // �?后一个桥接词
                        //strr += "and " + str0 + ".";
                        strr.append("and" + str0 + ",");
                    }

                    strlist0[i] = str0;
                }
                //return strr.toString();
                strRes = strr;
            }
        }
        return strRes.toString();
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
        return str.replaceAll("[^a-zA-Z]", " ").toLowerCase(Locale.ENGLISH);
    }
    /**
     * 生成新文本.
     * @param inputText 原文本
     * @return  修改后的文本
     */
    public String generateNewText(final String inputText) {
        final String[] strlist = wordSplit(inputText); // 分割字符串得到一个个字符

        final StringBuffer strTmp = new StringBuffer(); // 存储新文�?

        for (int i = 0; i < strlist.length - 1; i++) {
            //strTmp = strTmp + strlist[i] + " ";
            strTmp.append(strlist[i] + CONSPACE);
            // 根据queryBridgeWords_0函数得到桥接词列�?
            strlist0 = queryBridgeWords0(
                    strlist[i].replaceAll("[^a-zA-Z]", " ")
                    .toLowerCase(Locale.ENGLISH),
                    strlist[i + 1].replaceAll("[^a-zA-Z]", " ")
                    .toLowerCase(Locale.ENGLISH));

            if (strlist0.length != 0) { // 存在桥接词，随机选择�?�?
                final double dDoub = Math.random();
                final int kLength = (int) (dDoub * strlist0.length);
                final String str1 = strlist0[kLength];
                //strTmp = strTmp + str1 + " ";
                strTmp.append(str1 + CONSPACE);
            }
        }
        //strTmp += strlist[strlist.length - 1];
        strTmp.append(strlist[strlist.length - 1]);
        return strTmp.toString();

    }
    /**
     * 展示.
     * @param graTmp 图
     */
    public void showDirectedGraph(final Graph graTmp) {
        final GraphViz gViz = new GraphViz("D:\\java_Oxygen\\Lab4\\src",
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

        gViz.run();
    }
    /**
     * 计算最短路径.
     * @param word1 1
     * @param word2 2
     * @return  结果s
     */
    public String calcShortestPath(final String word1, final String word2) {
        int numM1 = -1;
        int numM2 = -1;
        StringBuffer strRes;

        for (int i = 0; i < str.length; i++) {
            if (str[i].compareTo(word1) == 0) {
                numM1 = vis[i]; // 找到初始起点
            }
            if (str[i].compareTo(word2) == 0) {
                numM2 = vis[i]; // 找到结尾节点
            }
        }
        if (numM1 == -1 && numM2 == -1) {
            //return CHARA + word1 + "\" and \"" + word2 + CHARB;
            strRes = new StringBuffer(CHARA + word1 + "\" and \""
                                        + word2 + CHARB);
        } else if (numM1 == -1 && numM2 != -1) {
            //return CHARA + word1 + CHARB;
            strRes = new StringBuffer(CHARA + word1 + CHARB);
        } else if (numM1 != -1 && numM2 == -1) {
            //return CHARA + word2 + CHARB;
            strRes = new StringBuffer(CHARA + word2 + CHARB);
        } else {
            final int nLength = inMatrix.length;
            int[][] pVertex = new int[nLength][nLength]; // 保存经过的中间节�?
            int[][] dDPath = new int[nLength][nLength]; // 保存从i到j的最小路径�??
            for (int i = 0; i < nLength; i++) {
                for (int j = 0; j < nLength; j++) {
                    if (inMatrix[i][j] < 0 || inMatrix[i][j] > 0) {
                        pVertex[i][j] = j; // 先将图中节点连接
                    } else {
                        pVertex[i][j] = -1;
                    }
                    if (inMatrix[i][j] > 0 || inMatrix[i][j] < 0) {
                        dDPath[i][j] = inMatrix[i][j]; // 将边的权值付�?
                    } else {
                        dDPath[i][j] = NUMN1;
                    }
                }
            }

            for (int x = 0; x < nLength; x++) { // 进行Floyd算法
                for (int i = 0; i < nLength; i++) {
                    for (int j = 0; j < nLength; j++) {
                        if (dDPath[i][j] > dDPath[i][x] + dDPath[x][j]) {
                            dDPath[i][j] = dDPath[i][x] + dDPath[x][j];
                            pVertex[i][j] = pVertex[i][x];
                        }
                    }
                }
            }
            // 将结果进行输�?
            final StringBuffer strN = new StringBuffer(word1);
            int kTemp = pVertex[numM1][numM2];
            if (kTemp == -1) {
                //return "没有最短路径！";
                strRes = new StringBuffer("没有最短路径！");
            } else { // 将结果字符串末端不断添加
                String str0 = strS0[kTemp];
                //strN = strN + "->" + str0;
                strN.append(CONARROW + str0);
                while (kTemp != numM2) {
                    kTemp = pVertex[kTemp][numM2];
                    str0 = strS0[kTemp];
                    //strN = strN + "->" + str0;
                    strN.append(CONARROW + str0);
                }
                final String strA = String.valueOf(dDPath[numM1][numM2]);
                //strN = strN + "   路径长度" + strA;
                // 路径输出，并将其长度添加在末
                strN.append(CONPATHLEN + strA);
                //return strN.toString();
                strRes = strN;
            }
        }
        return strRes.toString();
    }

    /**
     * 随机游走.
     * @return 结果
     */
    public String randomWalk() {
        final int nLength = strS0.length;
        final int strLen = strS0.length;
        int[][] cpMatrix = new int[nLength][nLength]; // 新建数组，用于记录游走情况；
        StringBuffer strTmp;
        for (int i = 0; i < nLength; i++) {
            for (int j = 0; j < nLength; j++) {
                cpMatrix[i][j] = inMatrix[i][j];
            }
        }
        // 随机数的产生
        final Random rand = new Random();
        int jRand = rand.nextInt(strLen) % (strLen + 1);
        strTmp = new StringBuffer(strS0[jRand]);
        boolean flag = true;
        // 把游走路线存储到字符串中
        while (flag) {
            for (int k = 0; k < strLen; k++) {
                if (cpMatrix[jRand][k] != 0) {
                    if (cpMatrix[jRand][k] > -1 || cpMatrix[jRand][k] < -1) {
                        strTmp.append(CONSPACE + strS0[k]);
                        cpMatrix[jRand][k] = -1; // 把访问过的边值修改为 -1
                        jRand = k;
                        k = -1;
                    } else {
                        strTmp.append(CONSPACE + strS0[k]);
                        break;
                    }
                }
            }
            flag = false; // 结束路线生成
        }
        return strTmp.toString();
    }
    /**
     * 计算一个起点到其他顶点的最短路径.
     * @param word1 起点
     * @return 结果
     */
    public String calcShortestPath(final String word1) {
        int numM1 = -1;
        StringBuffer strRes;

        // 找到对应序号
        for (int i = 0; i < str.length; i++) {
            if (str[i].compareTo(word1) == 0) {
                numM1 = vis[i];
            }
        }
        if (numM1 == -1) {
            //return "字符 \"" + word1 + "\" 不存在！";
            strRes = new StringBuffer("字符 \""
                    + word1 + "\" 不存在！");
        } else {
            final int nLength = inMatrix.length;
            int[][] pMidl = new int[nLength][nLength];      // 保存经过的中间节
            int[][] dDPath = new int[nLength][nLength];      // 保存从i到j的最小路径
            for (int i = 0; i < nLength; i++) {       // 初始化D，p
                for (int j = 0; j < nLength; j++) {
                    if (inMatrix[i][j] < 0 || inMatrix[i][j] > 0) {
                        pMidl[i][j] = j;
                    } else {
                        pMidl[i][j] = -1;
                    }
                    if (inMatrix[i][j] < 0 || inMatrix[i][j] > 0) {
                        dDPath[i][j] = inMatrix[i][j];
                    } else {
                        dDPath[i][j] = NUMN1;
                    }
                }
            }

            for (int x = 0; x < nLength; x++) {
             // 进行Floyd算法，从0到n-1有可能进行遍
                for (int i = 0; i < nLength; i++) {
                    for (int j = 0; j < nLength; j++) {
                        if (dDPath[i][j] > dDPath[i][x] + dDPath[x][j]) {
                            dDPath[i][j] = dDPath[i][x] + dDPath[x][j];
                            pMidl[i][j] = pMidl[i][x];
                        }
                    }
                }
            }
            // 下面对获得的结果进行展示
            int numM2 = 0;
            final StringBuffer strN = new StringBuffer(word1);
            final StringBuffer strr = new StringBuffer("");
            for (numM2 = 0; numM2 < nLength; numM2++) {
                if (numM2 != numM1) {
                    strr.append("输出"
                                + word1 + "到"
                                + strS0[numM2] + "的最短路径：\r\n");
                    int kTemp = pMidl[numM1][numM2];
                    if (kTemp == -1) {
                        strr.append("没有短路径\r\n");
                    } else {
                        String str0 = strS0[kTemp];
                        strN.append(CONARROW + str0);
                        while (kTemp != numM2) {
                            kTemp = pMidl[kTemp][numM2];
                            str0 = strS0[kTemp];
                            strN.append(CONARROW + str0);
                        }
                        final String strA =
                                String.valueOf(dDPath[numM1][numM2]);
                        strN.append(CONPATHLEN + strA);
                        strr.append(strN + CONNEWLINE);
                        strN.setLength(0);
                        strN.append(word1);
                    }
                }
            }
            //return strr.toString();
            strRes = strr;
        }
        return strRes.toString();
    }
}
// liulu & qiaozhi lab1
