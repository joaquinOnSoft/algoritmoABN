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
 *     Joaquín Garzón - initial implementation
 *
 */

package com.joaquinonsoft.algoritmoabn;

import com.joaquinonsoft.algoritmoabn.operations.ABNInteractiveOperation;
import com.joaquinonsoft.algoritmoabn.operations.AbstractABNOperation;

public abstract class AbstractABNInteractiveOperation
        extends AbstractABNOperation
        implements ABNInteractiveOperation {

    public static final int ROW = 0;
    public static final int COL = 1;

    protected int currentRow;
    protected int currentCol;

    public AbstractABNInteractiveOperation(int operand1, int operand2){
        super(operand1, operand2);
    }


    @Override
    public int[] getCurrentPos() {
        return new int[0];
    }

    @Override
    public int getCurrentValue() {
        return 0;
    }

    @Override
    public void reset() {

    }

    @Override
    public void reset(int row, int col) {

    }
}
