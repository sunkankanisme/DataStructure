package com.sunk.datastructure.chapter10.huffman;

import java.io.*;
import java.util.*;

/**
 * 使用赫夫曼编码进行数据的压缩
 */
public class HuffmanDataCompress {
    // 存放 huffman 编码的集合
    private static final Map<Byte, String> huffmanCodeMap = new HashMap<>();

    public static void main(String[] args) {
        // todo 输入 "hello world" 会出现异常输出 "hello worrrrrrrld"
        String str = "i like like like java do you like a java";

        /*
         * 编码过程
         */
        final byte[] bytes = str.getBytes();
        final byte[] compressedBytes = huffmanCompress(bytes);
        // 由 40 压缩为 17，压缩率为 (40 - 17) / 40
        // System.out.println("原始大小 " + bytes.length + ", 压缩后大小 " + compressedBytes.length);

        /*
         * 解码过程
         */
        final byte[] source = decode(huffmanCodeMap, compressedBytes);
        // System.out.println(new String(source));

        /*
         * 文件压缩
         */
        // String srcFile = "C:\\Users\\sunk\\Downloads\\dingtalk_downloader.exe";
        // String destFile = "C:\\Users\\sunk\\Downloads\\dingtalk_downloader.exe.zip";
        // zipFile(srcFile, destFile);

        /*
         * 文件解压
         */
        String destFile = "C:\\Users\\sunk\\Downloads\\dingtalk_downloader.exe.zip";
        String unzipFile = "C:\\Users\\sunk\\Downloads\\dingtalk_downloader_unzip.exe";
        unzipFile(destFile, unzipFile);
    }

    /*
     * =================================== 文件压缩 ===================================
     */

    /**
     * 解压文件
     *
     * @param zipedFile 压缩的文件
     * @param destPath  解压到哪里
     **/
    public static void unzipFile(String zipedFile, String destPath) {
        try (final FileInputStream is = new FileInputStream(zipedFile);
             final ObjectInputStream ois = new ObjectInputStream(is);
             final FileOutputStream os = new FileOutputStream(destPath)
        ) {

            // 读取数据
            final byte[] huffmanBytes = (byte[]) ois.readObject();
            // 读取编码表
            final Map<Byte, String> codes = (Map<Byte, String>) ois.readObject();

            // 解码
            final byte[] decode = decode(codes, huffmanBytes);

            // 输出数据到文件
            os.write(decode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 压缩文件
     *
     * @param srcPath  文件原路径
     * @param destFile 文件目标路径
     **/
    public static void zipFile(String srcPath, String destFile) {
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;

        try {
            // 1 创建输入流
            is = new FileInputStream(srcPath);
            final byte[] bytes = new byte[is.available()];

            // 2 读取文件加载到数组
            is.read(bytes);

            // 3 直接对源文件进行压缩
            final byte[] huffmanCompress = huffmanCompress(bytes);

            // 4 创建文件输出流
            os = new FileOutputStream(destFile);
            oos = new ObjectOutputStream(os);

            // 5 输出压缩后的数据
            // 这里直接将压缩后的数组进行输出，同时需要将编码写出去，以便恢复文件
            oos.writeObject(huffmanCompress);
            oos.writeObject(huffmanCodeMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                os.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }




    /*
     * =================================== 数据解码 ===================================
     */

    /**
     * 完成对压缩数据的解压
     *
     * @param codes        赫夫曼编码表
     * @param huffmanBytes 经过赫夫曼编码之后的字节数组
     * @return 原始字符字节数组
     */
    public static byte[] decode(Map<Byte, String> codes, byte[] huffmanBytes) {
        // 1 先得到 huffmanBytes 对应的二进制字符串
        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            builder.append(byteToBitString(!flag, huffmanBytes[i]));
        }

        // System.out.println(builder);

        // 2 翻转编码表 kv
        final HashMap<String, Byte> reverseCodes = new HashMap<>();
        for (Map.Entry<Byte, String> entry : codes.entrySet()) {
            reverseCodes.put(entry.getValue(), entry.getKey());
        }

        // System.out.println(reverseCodes);

        // 3 把字符串按照 huffman 反向编码表进行解码
        final ArrayList<Byte> arrayList = new ArrayList<>();

        StringBuilder keyBuilder = new StringBuilder();
        for (char c : builder.toString().toCharArray()) {
            keyBuilder.append(c);

            if (reverseCodes.containsKey(keyBuilder.toString())) {
                arrayList.add(reverseCodes.get(keyBuilder.toString()));
                keyBuilder = new StringBuilder();
            }
        }

        // System.out.println(arrayList);

        final byte[] bytes = new byte[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            bytes[i] = arrayList.get(i);
        }

        return bytes;
    }


    /**
     * 将一个 byte 转换为一个二进制的字符串
     *
     * @param flag 标识是否需要补高位，如果是最后一个字节则无需补高位？？？
     * @param b    传入的 byte
     * @return 返回该 byte 对应的二进制字符串（补码返回）
     */
    public static String byteToBitString(boolean flag, byte b) {
        int temp = b;

        if (flag) {
            // 按位与
            temp = temp | 256;
        }

        // 此处返回的是二进制的补码
        final String str = Integer.toBinaryString(temp);

        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }


    /*
     * =================================== 数据编码 ===================================
     */

    /*
     * 封装压缩字符串称为字节数组的方法
     */
    public static byte[] huffmanCompress(byte[] strBytes) {
        // --- 1 讲字符串转换为字节数组
        // final byte[] strBytes = str.getBytes();
        // 40
        // System.out.println(strBytes.length);

        // --- 2 将字节数组按照权重信息，生成对应的节点集合
        final List<DataNode> nodes = getNodes(strBytes);
        // [DataNode{data=32, weight=9}, DataNode{data=97, weight=5},
        //  DataNode{data=100, weight=1}, DataNode{data=101, weight=4},
        //  DataNode{data=117, weight=1}, DataNode{data=118, weight=2},
        //  DataNode{data=105, weight=5}, DataNode{data=121, weight=1},
        //  DataNode{data=106, weight=2}, DataNode{data=107, weight=4},
        //  DataNode{data=108, weight=4}, DataNode{data=111, weight=2}]
        // System.out.println(nodes);

        // --- 3 将节点集合按照权重生成相应的赫夫曼树
        final DataNode huffmanTree = createHuffmanTree(nodes);
        huffmanTree.preOrder();

        // --- 4 根据赫夫曼树生成相应的编码
        final Map<Byte, String> codes = getCodes(huffmanTree);
        // {32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
        // System.out.println(codes);

        // --- 5 使用赫夫曼编码生成对应的数据
        final byte[] zipedBytes = zip(strBytes, codes);
        // [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
        // System.out.println(Arrays.toString(zipedBytes));

        return zipedBytes;
    }

    /**
     * 使用赫夫曼编码表将字符数串字节组生成压缩后的字节数组
     *
     * @param bytes 原始字符串对应的字节数组
     * @param codes 赫夫曼编码表
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> codes) {
        // 1 利用赫夫曼编码表将传入的 byte 数组转成编码字符串
        final StringBuilder builder = new StringBuilder();

        for (byte aByte : bytes) {
            builder.append(codes.get(aByte));
        }

        // 10101000101111111100100010111111110010001011111111 \
        // 00100101001101110001110000011011101000111100101000 \
        // 101111111100110001001010011011100
        // System.out.println(builder);

        // 2 将编码字符串进行压缩，转换为 byte 数组
        // 2.1 计算新数组的长度 [ len = (builder.length() + 7) / 8 ]
        int resultBytesLen;

        final int length = builder.length();
        if (length % 8 == 0) {
            resultBytesLen = length / 8;
        } else {
            resultBytesLen = length / 8 + 1;
        }

        // 2.2 创建新的字节数组
        final byte[] by = new byte[resultBytesLen];
        int index = 0;

        // 2.3 每 8 位对应一个字节
        for (int i = 0; i < builder.length(); i += 8) {
            // 截取字符
            String strByte;

            if (i + 8 > builder.length()) {
                strByte = builder.substring(i);
            } else {
                strByte = builder.substring(i, i + 8);
            }

            // 将 strByte 转为 byte 类型
            by[index++] = (byte) Integer.parseInt(strByte, 2);
        }

        return by;
    }

    /**
     * 对 getCodes 方法的重载
     */
    public static Map<Byte, String> getCodes(DataNode root) {
        if (root == null) {
            return null;
        }

        // 处理左边
        getCodes(root.left, "0", new StringBuilder());

        // 处理右边
        getCodes(root.right, "1", new StringBuilder());

        return huffmanCodeMap;
    }

    /**
     * 根据赫夫曼树生成相应的赫夫曼编码，将传入的 node 节点的所有叶子节点的赫夫曼编码存放到 map 集合中
     *
     * @param node           传入的节点
     * @param sourcePathCode 路径编码，当前节点从左还是右过来的，左子节点为 0，右子节点为 1
     * @param pathBuilder    用于拼接路径
     */
    public static void getCodes(DataNode node, String sourcePathCode, StringBuilder pathBuilder) {
        final StringBuilder builder = new StringBuilder(pathBuilder);

        builder.append(sourcePathCode);

        if (node != null) {
            // 判断当前节点是否是叶子结点
            if (node.data == null) {
                // 非叶子节点，向左右递归处理
                getCodes(node.left, "0", builder);
                getCodes(node.right, "1", builder);
            } else {
                // 是叶子节点，完成了某个叶子结点编码的拼接
                huffmanCodeMap.put(node.data, builder.toString());
            }
        }
    }

    /*
     * 通过传入的 list<Node> 构建赫夫曼树
     */
    public static DataNode createHuffmanTree(List<DataNode> list) {
        while (list.size() > 1) {
            Collections.sort(list);

            // 获取并移除最小的两个节点
            final DataNode leftNode = list.remove(0);
            final DataNode rightNode = list.remove(0);

            // 生成新节点 & 加入到原集合中
            final DataNode newNode = new DataNode(null, leftNode.weight + rightNode.weight);
            newNode.left = leftNode;
            newNode.right = rightNode;
            list.add(newNode);
        }

        // 剩余的一个就是 root 节点
        return list.get(0);
    }

    /*
     * 传入原始数据字节数组，生成对应带有权重和数据的 Node 对象集合
     */
    public static List<DataNode> getNodes(byte[] bytes) {
        // 1 创建集合
        final ArrayList<DataNode> nodes = new ArrayList<>();

        // 2 统计每个 byte 出现的次数（使用 map 处理）
        final HashMap<Byte, Integer> map = new HashMap<Byte, Integer>();
        for (byte aByte : bytes) {
            final Integer count = map.getOrDefault(aByte, 0);
            map.put(aByte, count + 1);
        }

        // 3 遍历 map 集合，构建 Node 对象
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new DataNode(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    /*
     * 创建 Node 带有权值和数据
     */
    static class DataNode implements Comparable<DataNode> {
        // 存放数据本身，比如 ‘a’ -> 97
        Byte data;
        // 存放权值，数据字符中出现的次数
        int weight;

        DataNode left;
        DataNode right;

        public DataNode(Byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public int compareTo(DataNode o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "DataNode{" +
                    "data=" + data +
                    ", weight=" + weight +
                    '}';
        }

        public void preOrder() {
            // System.out.println(this);

            if (this.left != null) {
                this.left.preOrder();
            }

            if (this.right != null) {
                this.right.preOrder();
            }
        }
    }

}
