package util;

import java.io.*;

public class WebTool {


    /**
     * 2018/10/12 图片信息
     * @param jsonstring json字符串
     */
    public void jsonFW(String jsonstring){
        String fileaddress = "D:\\WebProject\\Webproject\\web\\JsonData\\tempdata.json";
        writeFile(fileaddress, jsonstring);
    }


    /**
     * 2018/10/12 测试新闻信息
     * @param jsonstring json字符串
     */
    public void jsonNF(String jsonstring){
        String fileaddress = "D:\\WebProject\\Webproject\\web\\labspace\\tempnews.json";
        writeFile(fileaddress, jsonstring);
    }


    /**
     * 2018/10/12 写入文件
     * @param fileaddress 文件路径
     * @param jsonstring json字符串
     */
    private void writeFile(String fileaddress,String jsonstring){
        File file = new File(fileaddress);
        BufferedWriter out = null;
        if (!file.exists()) try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        else {
            try {
                FileOutputStream fos = new FileOutputStream(fileaddress);
                out = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
//                fos.write(jsonstring.getBytes());
                out.write(jsonstring + "\n");
                out.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
