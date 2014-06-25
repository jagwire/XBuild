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
package edu.missouri.X;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ryan
 */
public class Base {

    private Set<String> dependencies;
    private Set<String> sourceDirectories;
    private Set<String> testDirectories;

    public Base() {
        dependencies = new HashSet<>();
        sourceDirectories = new HashSet<>();
        testDirectories = new HashSet<>();
    }

    protected void dependency(String libName) {
        dependencies.add(libName);
    }

    protected void source_directory(String path) {
        sourceDirectories.add(path);
    }

    protected void test_directory(String path) {
        testDirectories.add(path);
    }
    
    public void list_config() {
        for(String s: dependencies) {
            System.out.println("DEPENDENCY: "+s);
        }
        
        for(String s: sourceDirectories) {
            System.out.println("SOURCE DIRECTORY: "+s);
        }
        
        for(String s: testDirectories) {
            System.out.println("TEST DIRECTORY: "+s);
        }
    }
    
    private void mkdir(String local_filename) {
        File directory = new File(System.getProperty("user.dir")+File.separator+local_filename);
        if(!directory.exists()) {
            //creating directory
            System.out.println("CREATING DIRECTORY: "+local_filename);
            directory.mkdirs();
            
        } else {
            System.out.println("NO NEED TO MAKE DIRECTORY: "+local_filename);
        }
    }
    public final void build() {
        //check if lib directory exists
        mkdir("lib");
        
        //download dependencies to lib
        
        //for every source directory
        for(String s: sourceDirectories) {
            //check if it exists
            mkdir(s);
            //if not, create it
        }
        
        //for every test directory
            //check if it exists
            //if not, create it
    }
}
