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

/**
 * We understand by friends of 10 those pairs of numbers that added give the number 10.
 * This concept can also be extended to the number 100 and so we would have that the
 * friends of 100 are the pairs of numbers that add 100 to each other. In the case of
 * friends of 100, it is convenient at first to work with friends of the form (60.40),
 * (10.90), etc., to later pass to those who do not have complete tens, that is, they are
 * of the type (45.55), (63.37), etc.
 *
 * See: https://www.recursosep.com/2017/01/26/metodo-abn-amigos-del-10/
 */
class FriendsOf{
    private int target;
    protected int operand;

    FriendsOf(int target, int operand) {
        this.target = target;
        this.operand = operand;
    }

    public int getOperand() {
        return operand;
    }

    public void setOperand(int operand) {
        this.operand = operand;
    }

    public boolean isFriend(int value) {
        return ((operand + value) == target);
    }

    public int getResult() {
        return target - operand;
    }
}
