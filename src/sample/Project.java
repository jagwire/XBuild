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

import java.util.HashMap;
import java.util.Map;
import sample.ProjectConfiguration;

/**
 *
 * @author Ryan
 */
public class Project extends WebPortal {
    public Project() {
        super();

        dependency("junit");
        dependency("mockito");
        
        source_directory("src/classes");
        test_directory("test/classes");
        

    }

    @Override
    public void app() {
        portal("my_web_portal");
        version(1,0);
        
        models("models");
        controllers("controllers");
        views("views");
    }

    @Override
    public void routes() {
        root(MyWebController.class);
        
        resource("books");
        resource("users",
                resource("cars"),
                resource("interests"));
        
        
//        namespace("apis",
//                GET("v1"),
//                POST("v1"),
//                PUT("v1"),
//                DELETE("v1"));
//        
        GET("apis", Object.class);
    }
    class MyWebController extends WebController {
        
    }
    

}


