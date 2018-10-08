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

public class Decomposition {
    private static final int INDEX_ONES = 3;
    private static final int INDEX_TENS = 2;
    private static final int INDEX_HUNDREDS = 1;
    private static final int INDEX_THOUSANDS = 0;


    private String number;

    public Decomposition(int number){
        this.number = String.format("%04d", number);
    }

    private int getDigit(int index){
        return Character.digit(number.charAt(index), 10);
    }

    public int getThousands(){
        return getDigit(INDEX_THOUSANDS);
    }

    public int getUnitsInThousands(){
        return getThousands() * 1000;
    }

    public int getHundreds(){
        return getDigit(INDEX_HUNDREDS);
    }

    public int getUnitsInHundreds(){
        return getHundreds() * 100;
    }

    public int getTens(){
        return getDigit(INDEX_TENS);
    }

    public int getUnitsInTens(){
        return getTens() * 10;
    }


    public int getOnes(){
        return getDigit(INDEX_ONES);
    }

    @Override
    public String toString() {
        boolean first = true;
        StringBuilder str = new StringBuilder();
        int units = 1000;

        str.append(number).append(" = ");
        for(int i = INDEX_THOUSANDS; i<= INDEX_ONES; i++){
            if(first){
                first = false;
            }
            else{
                str.append(" + ");
            }
            str.append(getDigit(i) * units);
            units /= 10;
        }

        return str.toString();
    }
}
