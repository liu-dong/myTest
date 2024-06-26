package com.dong.designMode.strategyPattern;

/**
 * @author liudong 2022/3/12
 */
public class OperationAdd implements OperationStrategy {

    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
