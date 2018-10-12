/*
 * (C) Copyright 2018 Joaquín On Software (http://joaquinonsoft.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *   Joaquín Garzón - initial implementation
 */
package com.joaquinonsoft.algoritmoabn.operations;

public abstract class AbstractABNOperation implements ABNOperarion {
    public static final int NUM_COLUMNS = 3;

    protected int operand1;
    protected int operand2;

    protected int steps[][];

    public AbstractABNOperation(int operand1, int operand2){
        this.operand1 = operand1;
        this.operand2 = operand2;

        steps = new int[getNumRows()][NUM_COLUMNS];
        initialize();
    }

    protected abstract void initialize();

    protected abstract int getNumRows();

    @Override
    public int getOperand1() {
        return operand1;
    }

    @Override
    public int getOperand2() {
        return operand2;
    }

    @Override
    public int setOperand1(int operand1) {
        return operand1;
    }

    @Override
    public int setOperand2(int operand2) {
        return operand2;
    }

    public boolean isValidResult(int result){
        return result == getResult();
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        int[][]steps = getSteps();

        int rows = steps.length;
        int columns = steps[0].length;
        int last=0;

        str.append("[");
        for(int i=0; i<rows; i++){
            if(i!=0){
                str.append(",");
            }

            str.append("\n");


            str.append("{");
            for (int j=0; j<columns; j++){
                if(j!=0){
                    str.append(",");
                }
                str.append(steps[i][j]);

                last = j;
            }
            str.append("}");
            if(steps[i][last] == 0){
                i=rows; //exit current loop
            }

        }
        str.append("\n]");
        return str.toString();
    }
}
