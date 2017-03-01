//package org.xdemo.example.SpringActivemq.controller;
//
//import static org.junit.Assert.*;
//
//import javax.annotation.Resource;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.test.context.ContextConfiguration;
////import org.springframework.mock.web.MockHttpServletRequest;
////import org.springframework.mock.web.MockHttpServletResponse;
//
////@Transactional
////@TransactionConfiguration(defaultRollback = true)
//@ContextConfiguration(locations={"classpath*:applicationContext.xml"}) 
//public class ActivemqControllerTest {
//
//    @Resource
//    ActivemqController activemqController;    
//
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@Test
//	public void test() {
//		 //request.setRequestURI("createProduct.htm");    
//        //request.setMethod(HttpMethod.POST.name());    
//            
//        String mv = null;    
//        try    
//        {    
//            mv = activemqController.queueSender("测试");    
//        }    
//        catch (Exception e)    
//        {    
//            e.printStackTrace();    
//            fail("testToSearchPage failed!");    
//        }    
//    
//        assertNotNull(mv);    
//        // assertEquals(response.getStatus(), 200); 
//	}
//
//}
