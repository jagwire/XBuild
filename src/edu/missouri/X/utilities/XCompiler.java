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

package edu.missouri.X.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

/**
 *
 * @author Ryan
 */
public class XCompiler {
    private Set<File> inputFiles;
    private final JavaCompiler compiler;
    private final StandardJavaFileManager fileManager;
    
    public XCompiler() {
        
        inputFiles = new HashSet<>();
        
        compiler = ToolProvider.getSystemJavaCompiler();
        fileManager = compiler.getStandardFileManager(null, null, null);
    }
    
    public void setOutputDirectory(String outputDirectory) throws IOException {
        File dir = new File(outputDirectory);
        if(dir.exists() && !dir.isDirectory()) {
            throw new RuntimeException("SPECIFIED FILE IS NOT A DIRECTORY: "+outputDirectory);
        }
        
        if(!dir.exists()) {
            dir.mkdirs();
            
        }
        
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(dir));
    }
    
    public void addSourceFile(File source) {
        inputFiles.add(source);
    }
    
    public void compile() {
        Iterable<? extends JavaFileObject> fileObjects
                = fileManager.getJavaFileObjects(inputFiles.toArray(new File[] { }));
        compiler.getTask(null, fileManager, null, null, null, fileObjects).call();
    }
    
    public void cleanup() throws IOException {
        fileManager.close();
    }
}
