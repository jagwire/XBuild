/*
 * The MIT License
 *
 * Copyright 2014 Ryan.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package sample;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Ryan
 */
public class LambdaReflectionTest {
    
    public LambdaReflectionTest() {
    }

    @Test
    public void nothing() throws NoSuchMethodException {
        try {
//            KeyValuePair<String, String> test = (key) -> "woah";
//            assertNotNull(test);
//          
//            for(Method me : test.getClass().getDeclaredMethods()) {
//                System.out.println(me.getName());
//                System.out.println(me);
//                System.out.println(Arrays.toString(me.getParameterTypes()));
//                System.out.println(Arrays.toString(me.getParameters()));
//            }
            
//          where(id->"DEADBEEF",
//                name->"Jagwire",
//                num->5);

               
//            Method m = test.getClass().getMethod("map", String.class);
//            assertNotNull(m);
        } catch (SecurityException ex) {
            Logger.getLogger(LambdaReflectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
    }
   
    private <R>void where(Function<Object, R>... entries) {
        for(Function<Object, R> entry: entries) {
            Method m = null;
            
            try {
                m = entry.getClass().getDeclaredMethod("apply", Object.class);
                
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(LambdaReflectionTest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(LambdaReflectionTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            final Parameter p = m.getParameters()[0];
            final String key = p.getName();
            final R value = entry.apply(null);
            System.out.println(key+"->"+value);
        }
    }
}
