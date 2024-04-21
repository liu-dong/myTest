package com.dong.designMode.strategyPattern;

/**
 * @author liudong 2022/3/12
 */
public class Context {

    private OperationStrategy operationStrategy;

    public Context(OperationStrategy operationStrategy){
        this.operationStrategy = operationStrategy;
    }

    public int executeStrategy(int num1, int num2){
        return operationStrategy.doOperation(num1, num2);
    }
}
