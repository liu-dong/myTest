package com.dong;

/**
 * 【建议拷贝到本地环境编写后贴回考试系统，考试结束会自动收卷，请自行定时将代码拷回到系统中】
 * <p>
 * 业务背景：这是一个可以售卖多种类目商品（酒店、门票、签证）的旅游类电商系统。 这个类中主要是接收到订单状态变更消息的逻辑处理。
 * 目前有订单支付（这里的支付是直接扣款，有免密协议）和订单完成两部分逻辑。
 * <p>
 * 题目要求：希望你能够将这段代码进行重构，目标：拥有好的可读性（清晰、直观、简洁）、
 * 可扩展性（面向未来商品类的扩展、订单的状态扩展、公共逻辑扩展（比如增加入参、出参日志打印和异常控制逻辑加入））
 * 题目判分的优先级： 设计思路 > 代码结构 > 规范性 > 可读性 > 完整度
 * 参考建议：
 * 1. 可读性：
 * 1）代码的注释、命名
 * 2）枚举类
 * 3）重复代码归纳合并
 * 2. 扩展性考虑：
 * 1）设计模式 工厂、模板方法
 * 2）事件驱动方式 比如 SpringEvent 或者 EventBus。没有用过可以上网查哦。（代码中体现出结构和大概逻辑即可）
 */
public class OrderStatusMQListener {
    private OrderService orderService = new OrderService();
    private NotifyService notifyService = new NotifyService();
    private OrderStatusStrategyFactory strategyFactory = new OrderStatusStrategyFactory();

    /**
     * 消费接口
     *
     * @param orderNo 订单号
     */
    public void consume(String orderNo) {
        final OrderInfo orderInfo = orderService.getOrderInfo(orderNo);
        // 使用工厂类获取对应的处理策略
        OrderStatusStrategy strategy = strategyFactory.getStrategy(orderInfo);
        if (strategy != null) {
            strategy.processOrder(orderInfo);
        }
    }

}

/**
 * 不同订单业务类型工厂类
 */
class OrderBizTypeStrategyFactory {
    public OrderBizTypeStrategy getPayStrategy(String orderBizType) {
        switch (orderBizType) {
            case "hotel":
                return new HotelOrderBizTypeStrategy();
            case "tickets":
                return new TicketOrderBizTypeStrategy();
            case "visa":
                return new VisaOrderBizTypeStrategy();
            default:
                throw new IllegalArgumentException("Unknown order type: " + orderBizType);
        }
    }
}

/**
 * 不同订单业务类型策略接口
 */
interface OrderBizTypeStrategy {
    /**
     * 创建支付
     *
     * @param orderInfo
     */
    void processPayment(OrderInfo orderInfo);

    /**
     * 订单完成
     *
     * @param orderInfo
     */
    void processComplete(OrderInfo orderInfo);

    // 退单。。。

}

/**
 * 酒店订单业务类型
 */
class HotelOrderBizTypeStrategy implements OrderBizTypeStrategy {
    private OrderService orderService = new OrderService();
    private NotifyService notifyService = new NotifyService();
    private PayService payService = new PayService();

    /**
     * 支付
     *
     * @param orderInfo
     */
    @Override
    public void processPayment(OrderInfo orderInfo) {
        // 酒店订单支付前的检查 ......
        payPrdOfHotelVerify(orderInfo);
        // 信用支付
        long payId = payService.payOfCredit(orderInfo);
        // 如果支付失败关闭订单
        if (payId < 0) {
            payService.notPayedToClose(orderInfo);
        }
    }

    /**
     * 订单完成
     *
     * @param orderInfo
     */
    @Override
    public void processComplete(OrderInfo orderInfo) {
        // 更新订单状态
        orderService.updateOrderStatus(orderInfo.getOrderNo(), 4);
        // 给运营发钉钉通知
        notifyService.dingding("发送到酒店运营群里的内容");
        // 给用户发短信
        notifyService.sms("发送到给酒店用户的内容");
    }

    private void payPrdOfHotelVerify(OrderInfo orderInfo) {
        // 各种酒店订单支付前的检查 ...... 候选人不用关系内部逻辑
    }
}

/**
 * 门票订单业务类型
 */
class TicketOrderBizTypeStrategy implements OrderBizTypeStrategy {
    private OrderService orderService = new OrderService();
    private NotifyService notifyService = new NotifyService();
    private PayService payService = new PayService();

    /**
     * 支付
     *
     * @param orderInfo
     */
    @Override
    public void processPayment(OrderInfo orderInfo) {
        long payId = -1;
        for (int i = 0; i < 3; i++) { // 如果失败有3次重试
            // 信用支付
            payId = payService.payOfCredit(orderInfo);
            // payId >0 代表支付成功
            if (payId > 0) {
                break;
            }
        }
        // 如果支付失败关闭订单
        if (payId < 0) {
            payService.notPayedToClose(orderInfo);
        }
    }

    /**
     * 订单完成
     *
     * @param orderInfo
     */
    @Override
    public void processComplete(OrderInfo orderInfo) {
        // 更新订单状态
        orderService.updateOrderStatus(orderInfo.getOrderNo(), 4);
        // 给运营发钉钉通知
        notifyService.dingding("发送到门票运营群里的内容");
        // 给用户发短信
        notifyService.sms("发送到给门票用户的内容");
        // 给用户发微信通知
        notifyService.wechat("发送到给门票用户的内容");
    }
}

/**
 * 签证订单业务类型
 */
class VisaOrderBizTypeStrategy implements OrderBizTypeStrategy {
    private OrderService orderService = new OrderService();
    private NotifyService notifyService = new NotifyService();
    private PayService payService = new PayService();

    /**
     * 支付
     *
     * @param orderInfo
     */
    @Override
    public void processPayment(OrderInfo orderInfo) {
        // 三方支付
        long payId = payService.payOfAliPay(orderInfo);
        // 如果支付失败关闭订单
        if (payId < 0) {
            payService.notPayedToClose(orderInfo);
        } else {// 如果支付成功
            // 签证订单支付后业务上要处理的一些逻辑
            payAfterOfVisaBusiness(payId, orderInfo);
        }
    }

    /**
     * 订单完成
     *
     * @param orderInfo
     */
    @Override
    public void processComplete(OrderInfo orderInfo) {
        // 更新订单状态
        orderService.updateOrderStatus(orderInfo.getOrderNo(), 4);
        // 给运营发钉钉通知
        notifyService.dingding("发送到签证运营群里的内容");
        // 给用户发微信通知
        notifyService.wechat("发送到给签证用户的内容");
    }

    private void payAfterOfVisaBusiness(long payId, OrderInfo orderInfo) {
        // 各种签证订单支付后的逻辑 ...... 候选人不用关系内部逻辑
    }
}


/**
 * 订单状态策略工厂类
 */
class OrderStatusStrategyFactory {
    public OrderStatusStrategy getStrategy(OrderInfo orderInfo) {
        // 根据订单信息返回相应的策略实例
        if (orderInfo.getStatus() == 1) {
            return new PayStrategy();
        } else if (orderInfo.getStatus() == 4) {
            return new CompleteStrategy();
        } else {
            return null; // 或者返回一个空操作策略
        }
    }
}

/**
 * 不同订单状态策略
 */
interface OrderStatusStrategy {
    void processOrder(OrderInfo orderInfo);
}

/**
 * 支付状态业务
 */
class PayStrategy implements OrderStatusStrategy {
    private PayService payService = new PayService();
    private OrderBizTypeStrategyFactory strategyFactory = new OrderBizTypeStrategyFactory();

    @Override
    public void processOrder(OrderInfo orderInfo) {
        // 获取对应的订单业务类型策略
        OrderBizTypeStrategy strategy = strategyFactory.getPayStrategy(orderInfo.getOrderBizType());
        // 执行支付逻辑
        strategy.processPayment(orderInfo);
    }
}

/**
 * 订单完成状态业务
 */
class CompleteStrategy implements OrderStatusStrategy {
    private OrderBizTypeStrategyFactory strategyFactory = new OrderBizTypeStrategyFactory();
    private NotifyService notifyService = new NotifyService();

    @Override
    public void processOrder(OrderInfo orderInfo) {
        // 获取对应的订单业务类型策略
        OrderBizTypeStrategy strategy = strategyFactory.getPayStrategy(orderInfo.getOrderBizType());
        // 执行订单完成逻辑
        strategy.processComplete(orderInfo);
        // 发送通知
        notifyService.dingding("给运营发钉钉通知");
        notifyService.sms("给用户发短信");
        notifyService.wechat("给用户发微信通知");
    }
}

/**
 * 同意退款状态业务
 */
class RefundStrategy implements OrderStatusStrategy {

    @Override
    public void processOrder(OrderInfo orderInfo) {
        // 退款业务逻辑
    }
}


// -------------------- 方便候选人本地阅读代码，↓↓↓↓↓↓↓不需要动 --------------------


/**
 * 订单服务类 (方便候选人本地阅读代码, 不需要动)
 */
class OrderService {
    OrderInfo getOrderInfo(String orderNo) {
        return new OrderInfo();
    }

    void updateOrderStatus(String orderNo, int status) {
        // 更新订单逻辑
    }

}

/**
 * 支付服务类 (方便候选人本地阅读代码, 不需要动)
 */
class PayService {

    /**
     * 信用支付
     *
     * @param orderInfo
     */
    long payOfCredit(OrderInfo orderInfo) {
        //  信用支付逻辑，候选人不用理会支付内部逻辑 ...
        return 1;
    }

    /**
     * 支付宝支付
     *
     * @param orderInfo
     */
    long payOfAliPay(OrderInfo orderInfo) {
        //  支付宝支付逻辑，候选人不用理会支付内部逻辑 ...
        return 1;
    }

    /**
     * 未支付成功后关闭
     *
     * @param orderInfo
     */
    public void notPayedToClose(OrderInfo orderInfo) {
        //  支付宝支付逻辑，候选人不用理会支付内部逻辑 ...
    }
}

class NotifyService {
    /**
     * 短信通知
     */
    void sms(String msg) {
    }

    /**
     * 钉钉通知
     */
    void dingding(String msg) {
    }

    /**
     * 微信通知
     */
    void wechat(String msg) {
    }

    /**
     * 邮件通知
     */
    void email(String msg) {
    }
}


/**
 * 订单类 (方便候选人本地阅读代码, 不需要动)
 */
class OrderInfo {
    private String orderNo;
    private String orderBizType;
    private int status;
    private String anything;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderBizType() {
        return orderBizType;
    }

    public void setOrderBizType(String orderBizType) {
        this.orderBizType = orderBizType;
    }

    public String getAnything() {
        return anything;
    }

    public void setAnything(String anything) {
        this.anything = anything;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
// -------------------- 方便候选人本地阅读代码，↑↑↑↑↑↑不需要动 --------------------
