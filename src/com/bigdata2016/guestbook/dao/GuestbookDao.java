package com.bigdata2016.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bigdata2016.guestbook.vo.GuestbookVo;
//
// 아이우에오!  
public class GuestbookDao {

   public Connection getConnection() throws SQLException {
      Connection conn = null;

      try {
         // 1. 드라이버 로딩
         Class.forName("oracle.jdbc.driver.OracleDriver");

         // 2. connection 얻기
         String url = "jdbc:oracle:thin:@localhost:1521:TestDB";
         String webId = "webdev";
         String webPw = "webdev";
         conn = DriverManager.getConnection(url, webId, webPw);
      } catch (ClassNotFoundException e) {
         System.out.println("JDBC 드라이버 찾을수 없음." + e);
      }
      return conn;
   }

   public void delete(GuestbookVo vo) {
      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = getConnection();

         // 3. Statement 준비
         String sql = " delete" + "   from  guestbook" + "   where no = ?" + "   and password = ?";
         pstmt = conn.prepareStatement(sql);

         // 4. binding
         pstmt.setLong(1, vo.getNo());
         pstmt.setString(2, vo.getPassword());

         // 5. query 실행
         pstmt.executeUpdate();

      } catch (SQLException ex) {
         System.out.println("sql error:" + ex);
      } finally {
         // 6. 자원 정리
         try {
            if (pstmt != null) {
               pstmt.close();
            }

            if (conn != null) {
               conn.close();
            }
         } catch (SQLException ex) {
            System.out.println("sql error:" + ex);
         }
      }
   }

   public void insert(GuestbookVo vo) {
      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
         conn = getConnection();
         // 3.statement 준비
         String sql = "insert into guestbook values (GUESTBOOK_SEQ.nextval,?,?,?,SYSDATE)";
         pstmt = conn.prepareStatement(sql);

         // 4. binding
         pstmt.setString(1, vo.getName());
         pstmt.setString(2, vo.getPassword());
         pstmt.setString(3, vo.getMessage());

         // 5. query 실행
         pstmt.executeUpdate();

         System.out.println("연결 성공");
      } catch (SQLException e) {
         System.out.println("sql error:" + e);
      } finally {
         // 6. 자원 정리
         try {
            if (pstmt != null) {
               pstmt.close();
            }
            if (conn != null) {
               conn.close();
            }
         } catch (SQLException e) {
            System.out.println("SQL error:" + e);
         }
      }
   }

   // 리스트로 로우를 가져온다
   // crud 타입에 맞게 이름을 생성해줘라
   public List<GuestbookVo> getList() {
      List<GuestbookVo> result = new ArrayList<GuestbookVo>();
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;

      try {
         conn = getConnection();
         // 3.statement 생성
         stmt = conn.createStatement();

         // 4. query 실행
         String sql = "select no, name, message, to_char(REG_DATE,'YYYY-MM-DD') from guestbook order by reg_date desc";

         // 5.
         rs = stmt.executeQuery(sql);
         while (rs.next()) {
            Long no = rs.getLong(1);
            String name = rs.getString(2);
            String message = rs.getString(3);
            String regDate = rs.getString(4);

            GuestbookVo vo = new GuestbookVo();
            vo.setNo(no);
            vo.setName(name);
            vo.setMessage(message);
            vo.setRegDate(regDate);

            result.add(vo);
         }

         System.out.println("연결 성공");
      } catch (SQLException e) {
         System.out.println("sql error:" + e);
      } finally {
         // 6. 자원 정리
         try {
            if (rs != null) {
               rs.close();
            }

            if (stmt != null) {
               stmt.close();
            }
            if (conn != null) {
               conn.close();
            }
         } catch (SQLException e) {
            System.out.println("SQL error:" + e);
         }
      }
      return result;
   }

}