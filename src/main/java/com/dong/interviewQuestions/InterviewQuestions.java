package com.dong.interviewQuestions;

import java.util.*;

/**
 *
 * @author 3hld
 */
public class InterviewQuestions {


    public static void main(String[] args) {


        final int num = 2;
//        static String str = "liudong";
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        Hashtable hashtable = new Hashtable();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List list = new ArrayList();
        list.add(1);
        list.add(123);
        list.add("刘东");
//        Array array = new Array();
        System.out.println(list.toString());
        System.out.println(list.iterator().next());
//        num = 4;


        /*SString s = "liudong";
        String s1 = "liudong";
        String s2 = new String("liudong");
        String s3 = new String("liudong");

        ystem.out.println(s==s1);
        System.out.println(s.equals(s1));
        System.out.println(s==s2);
        System.out.println(s.equals(s2));
        System.out.println(s2==s3);
        System.out.println(s2.equals(s3));*/


//        InterviewQuestions interviewQuestions = new InterviewQuestions();
        /*
            Q1:数组和list互相转换的方法
                数组转list:
                    1：Collections.addAll(list, arr);
                    2: Arrays.asList(arr)
                list转数组：
                    1：list.toArray()
         */
//        interviewQuestions.arrayListCast();

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
            Q7:redis是什么？有哪些应用场景
                一种非关系型数据库，支持持久化，可以将内存中的数据保存到磁盘中，具有丰富的数据结构存储，
                性能高，适用海量数据的存储，可以横向扩展
             应用场景：
                1、数据高并发的读写
                2、海量数据的读写
                3、对扩展性要求高的数据
         */

        /*
            Q8:什么是缓存穿透？怎么解决？

                缓存中没有，数据库也没有的数据，用户一直请求，造成数据库的压力过大
                解决办法：
                    1、在接口中做出校验，过滤这种请求
                    2、设定一个null的返回值

         */

        /*
            Q9:事务的四大特性，你使用过哪一个，简单描述下使用场景。
                事务的四大特性：原子性、一致性、隔离性、持久性
                原子性：保存关联表数据时，要么同时成功要么同时失败。
                一致性：在处理精确数据计算时，比如金额，需要保持数据前后一致性。
         */

        /*
            Q10：String str = new String("abc")究竟创建了几个对象？jvm中有什么变化？
                创建了两个对象
                在jvm中，首先会在堆内存中开辟一片内存空间，变量名str指向这片空间，
                    然后在方法区中的常量池里面寻找有没有"abc"常量，如果没有则创建一个，
                    有，则直接把常量池中的和堆内存中联系起来
         */
//        interviewQuestions.stringTest();

        /*
            Q11:集群跟分布式的区别，并说CAP原则
                集群，相同业务系统部署在多个服务器上，即用多个服务器来分担系统压力。
                分布式，一个业务系统拆分成多个业务子系统部署在多个服务器上，即用拆分子系统的方法来分担系统压力。

                CAP原则：一致性，可用性，分区容错性
         */

        /*
            Q12:什么是死锁？怎样防止死锁？
                两个或多个进程循环等待，并无限等待被占有的资源。
                产生死锁的四个必要条件：互斥条件、不可抢占条件、占有并申请条件、循环等待条件

                预防死锁：预防同时产生四个必要条件


         */

        /*
            Q13:synchronized的底层实现原理
                进入和退出监视器锁（monitor）,调用monitorenter方法占用monitor，处于锁定状态
                调用monitorexit退出monitor，让出monitor的所有权

         */

        /*
            Q14:jvm有哪些垃圾回收算法
                引用计数算法、标记清除算法、复制算法、标记整理算法、分代收集算法

         */

        /*
            Q15:redis怎么实现分布式锁
        */

        /*
            Q16:怎么保证缓存和数据库的一致性
                读的时候先读缓存，如果没有再读数据库，并且更新缓存、再返回响应
                写的时候先删除缓存，再修改数据库
         */
    }

    /**
        数组list互转
     */
    public void arrayListCast(){
        /*String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        List<String> list = new ArrayList<>();
        Collections.addAll(list, arr);

        System.out.println("数据组：" + Arrays.toString(arr));
        System.out.println("数组转列表1：" + list);
        System.out.println("数组转列表2：" + Arrays.asList(arr));
        System.out.println("列表转数组：" + Arrays.toString(list.toArray()));//.getClass().isArray()判断是否是数组*/

    }

    public void stringTest(){
        String str1 = new String("liudong");
        String str2 = new String("liudong");

//        System.out.println(str1);
        System.out.println(str1);
        System.out.println(str2.equals(str1));
    }

}
