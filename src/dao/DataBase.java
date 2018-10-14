package dao;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.Userif;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class DataBase {


    private Connection openConnection() {
        Connection conn = null;
        String password = "azaz9031";
        String root = "root";
        String url = "jdbc:mysql://localhost:3306/student?useSSL=false";
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, root, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * @param master_id 用户的id，用来查表
     * @return 包含图片src，图片描述的json字符串,与flag属性（标志图片出现在什么位置）
     */
    public String printDataBase_textArea(String master_id) {

        ArrayList<JSONObject> list = new ArrayList();
        Connection conn = openConnection();
        JSONArray ja = new JSONArray();

        try {
            String sql = "SELECT flag,img_src,img_description FROM web_study.img_information WHERE master_id = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, master_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                JSONObject jo = new JSONObject();
                jo.put("flag", rs.getString("flag"));
                jo.put("src", rs.getString("img_src"));
                jo.put("description", rs.getString("img_description"));
                list.add(jo);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("操作失败");
        }

        for (JSONObject aList : list) ja.add(0, aList);
        list.clear();
        return ja.toString();
    }

    /**
     * @param master_id 用户的id，用来查表。
     * @return 用户个人信息——用户名，密码，id
     */
    public ArrayList<String> printDataBase_userif(String master_id) {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = openConnection();
        try {
            String sql = "SELECT master_id,master_name,master_password FROM web_study.master_information WHERE master_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, master_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(Integer.toString(rs.getInt("master_id")));
                list.add(rs.getString("master_name"));
                list.add(rs.getString("master_password"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("操作失败");
        }
        return list;
    }

    public void signUp_user(Userif userif) {
        Connection conn = openConnection();
        String date = String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000);
        try {
            String sql = "INSERT INTO `web_study`.`master_information` " +
                    "(`master_id`, `master_name`, `master_password`, `master_interest`, `master_sex`)" +
                    " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, date);
            pst.setString(2, userif.getNickname());
            pst.setString(3, userif.getPassword());
            pst.setString(4, userif.getInterest());
            pst.setString(5, userif.getSex());
            pst.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("操作失败");
        }
    }

    public void setNewsIf(String title, String information) {

        String date = String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000);
        Connection conn = openConnection();

        try {
            String sql = "INSERT INTO `web_study`.`news_information` (`news_date`, `news_title`,`news_information`) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, date);
            pst.setString(2, title);
            pst.setString(3, information);
            pst.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("操作失败");
        }
    }

    public String getNewsIf() {
        ArrayList<JSONObject> list = new ArrayList();
        Connection conn = openConnection();
        JSONArray ja = new JSONArray();
        try {
            String sql = "SELECT `news_date`, `news_title`,`news_information` FROM web_study.news_information";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                JSONObject jo = new JSONObject();
                jo.put("date", rs.getString("news_date"));
                jo.put("title", rs.getString("news_title"));
                jo.put("news_information", rs.getString("news_information"));
                list.add(jo);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("操作失败");
        }
        for (JSONObject aList : list) ja.add(0, aList);
        list.clear();
        return ja.toString();
    }
}

