package com.dong.interviewQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author 3hld
 */
public class InterviewQuestions {


    public static void main(String[] args) {

        InterviewQuestions interviewQuestions = new InterviewQuestions();
        /*
            Q1:数组和list互相转换的方法
                数组转list:
                    1：Collections.addAll(list, arr);
                    2: Arrays.asList(arr)
                list转数组：
                    1：list.toArray()
         */
        interviewQuestions.arrayListCast();
        /*
            Q2:依赖注入有哪几种注解
                @Autowired(Spring)、@Resource(Spring) @Inject(JAVA)
         */

        /*
            Q3:Spring实现事务的方式有哪些
                1：基于 TransactionProxyFactoryBean的声明式事务管理
                2：基于 @Transactional 的声明式事务管理
                3：基于Aspectj AOP配置事务
         */

        /*
            Q4:SpringMVC的执行流程
            1、客户端发送request请求到DispatcherServlet
            2、DispatcherServlet根据HandleMapping里面的路径找到对应的处理器Handle
            3、根据处理器Handle找到对应的处理器适配器HandleAdapter去执行Handle，即Controller
            4、Controller返回ModelAndView
            5、ModelAndView到视图解析器ViewResolver中解析出对应的视图View返回到客户端

         */

        /*
            Q5:SpringBoot的配置文件有哪几种类型,有什么区别
            yaml、properties
            区别：yaml的格式类似json，键值的形式为K: V,用：分层
                properties的格式属性赋值，键值的形式为K=V,用.分层
         */

        /*
            Q6:MyBatis中的#{}和${}的区别
            #{}:是以预编译的形式，将参数设置到sql语句中；PreparedStatement；防止sql注入
            ${}:取出的值直接拼装在sql语句中；会有安全问题；
         */

        /*
            redis是什么？有哪些应用场景
                一种非关系型数据库，支持持久化，可以将内存中的数据保存到磁盘中，具有丰富的数据结构存储，
                性能高，适用海量数据的存储，可以横向扩展
             应用场景：
                1、数据高并发的读写
                2、海量数据的读写
                3、对扩展性要求高的数据
         */
    }

    /**
        数组list互转
     */
    public void arrayListCast(){
        String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        List<String> list = new ArrayList<>();
        Collections.addAll(list, arr);

        System.out.println("数据组：" + Arrays.toString(arr));
        System.out.println("数组转列表1：" + list);
        System.out.println("数组转列表2：" + Arrays.asList(arr));
        System.out.println("列表转数组：" + Arrays.toString(list.toArray()));//.getClass().isArray()判断是否是数组
    }

}
