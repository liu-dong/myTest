package com.dong.designMode.strategyPattern;

/**
 * @author liudong 2024/4/21
 */
public class OperationDivide implements OperationStrategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 / num2;
    }
}
