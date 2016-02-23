package com.bigdata2016.guestbook.daotest;

import java.util.List;

import com.bigdata2016.guestbook.dao.GuestbookDao;
import com.bigdata2016.guestbook.vo.GuestbookVo;

public class GuestbookDaoTest {
   public static void main(String[] args) {
      // insert test
      // insertTest();
      // selectTest();
      deleteTest();
      selectTest();
   }

   public static void deleteTest() {
      GuestbookDao dao = new GuestbookDao();
      GuestbookVo vo = new GuestbookVo();
      vo.setNo(13L);
      vo.setPassword("1234");
      dao.delete(vo);
   }

   public static void selectTest() {
      GuestbookDao dao = new GuestbookDao();
      List<GuestbookVo> list = dao.getList();
      for (GuestbookVo vo : list) {
         System.out.println(vo);
      }
   }

   public static void insertTest() {
      GuestbookDao dao = new GuestbookDao();
      GuestbookVo vo = new GuestbookVo();
      vo.setName("soso");
      vo.setPassword("123");
      vo.setMessage("remember");

      dao.insert(vo);
   }
}